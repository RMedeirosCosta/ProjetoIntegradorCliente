package br.edu.servico;

import br.edu.modelo.Transacao;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import javax.ws.rs.core.MediaType;

/**
 * Classe concreta que implementa o operador bancario. Esta classe
 * realizará todas as operações bancárias e salvará os logs das transações.
 */
public class OperadorBancario implements br.edu.contrato.OperadorBancario {
    
    private BancarioService servico;
    private final String urlTransacao;
    private Client client;
    
    public OperadorBancario(String urlTransacao, BancarioService servico) {
        client = Client.create();
        this.servico = servico;     
        this.urlTransacao = urlTransacao;
    }

    @Override
    public Long criarConta(String nomeCliente, Double saldoInicial) 
            throws Exception {
        Long numeroConta = servico.criarConta(nomeCliente, saldoInicial);
        
        Transacao transacao = new Transacao();
        transacao.setConta(numeroConta);
        transacao.setDescricao("Criacao de conta para "+
                                nomeCliente+
                                " com saldo inicial de "+
                                saldoInicial.toString());
        transacao.setTipoTransacao(Transacao.CRIACAO);

        WebResource log = client.resource(urlTransacao);
        log.type(MediaType.APPLICATION_XML);
        // Não coloquei retorno porque não iria usar aqui
        log.post(Transacao.class, transacao);
        
        return numeroConta;        
    }

    @Override
    public Double consultarSaldo(Long numeroConta) throws Exception {
        return servico.consultarSaldo(numeroConta);
        // De acordo com o enunciado, consulta não grava no log.
    }

    @Override
    public String efetuarDeposito(Long numeroConta, Double valor) 
            throws Exception {
        String retornoWsdl = servico.efetuarDeposito(numeroConta, valor);
        Transacao transacao = new Transacao();
        transacao.setConta(numeroConta);
        transacao.setDescricao("Deposito na conta "+
                               numeroConta+
                               " no valor de "+
                               valor.toString());
        transacao.setTipoTransacao(Transacao.DEPOSITO);
        
        WebResource log = client.resource(urlTransacao);
        log.type(MediaType.APPLICATION_XML);
        // Não coloquei retorno porque não iria usar aqui
        log.post(Transacao.class, transacao);
        
        return retornoWsdl;
    }

    @Override
    public String efetuarSaque(Long numeroConta, Double valor) 
            throws Exception {
        String retornoWsdl = servico.efetuarSaque(numeroConta, valor);
        
        Transacao transacao = new Transacao();
        transacao.setConta(numeroConta);
        transacao.setDescricao("Saque da conta "+
                                numeroConta+
                                " no valor de "+
                                valor.toString());
        transacao.setTipoTransacao(Transacao.SAQUE);
        
        WebResource log = client.resource(urlTransacao);
        log.type(MediaType.APPLICATION_XML);
        // Não coloquei retorno porque não iria usar aqui
        log.post(Transacao.class, transacao);
        
        return retornoWsdl;        
    }
    
    @Override
    public String fecharConta(Long numeroConta) 
            throws Exception {
        String retornoWsdl = servico.fecharConta(numeroConta);
        Transacao transacao = new Transacao();
        transacao.setConta(numeroConta);
        transacao.setDescricao("Fechamento de conta "+numeroConta);
        transacao.setTipoTransacao(Transacao.FECHAMENTO);
        
        WebResource log = client.resource(urlTransacao);
        log.type(MediaType.APPLICATION_XML);
        // Não coloquei retorno porque não iria usar aqui
        log.post(Transacao.class, transacao);
        
        return retornoWsdl;
    }

    @Override
    public String listarTransacoes(Long numeroConta) 
            throws Exception {
        WebResource log = client.resource(urlTransacao+"/"+
                                          numeroConta.toString());
        Transacao[] transacoes = log.get(Transacao[].class);
        
        String retornoTransacoes = "";
        for (Transacao transacao : transacoes) {
            retornoTransacoes += "\n\n" + transacao;
        }
        
        return retornoTransacoes;        
    }
    
    @Override
    public String listarTransacoes() throws Exception {
        WebResource log = client.resource(urlTransacao);
        Transacao[] transacoes = log.get(Transacao[].class);
        
        String retornoTransacoes = "";
        for (Transacao transacao : transacoes) {
            retornoTransacoes += "\n\n" + transacao;
        }
        
        return retornoTransacoes;        
    }    
}
