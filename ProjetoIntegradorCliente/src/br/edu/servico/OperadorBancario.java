package br.edu.servico;

/**
 * Classe concreta que implementa o operador bancario. Esta classe
 * realizará todas as operações bancárias e salvará os logs das transações.
 */
public class OperadorBancario implements br.edu.contrato.OperadorBancario {
    
    private BancarioService servico;
    
    public OperadorBancario(BancarioService servico) {
        this.servico = servico;         
    }

    @Override
    public Long criarConta(String nomeCliente, Double saldoInicial) 
            throws Exception {
        return servico.criarConta(nomeCliente, saldoInicial);
        // salvar transacao aqui
    }

    @Override
    public Double consultarSaldo(Long numeroConta) throws Exception {
        return servico.consultarSaldo(numeroConta);
        // salvar transacao aqui
    }

    @Override
    public String efetuarDeposito(Long numeroConta, Double valor) 
            throws Exception {
        return servico.efetuarDeposito(numeroConta, valor);
        // salvar transação aqui
    }

    @Override
    public String efetuarSaque(Long numerConta, Double valor) 
            throws Exception {
        return servico.efetuarSaque(numerConta, valor);
        // salvar transação aqui
    }
    
    @Override
    public String fecharConta(Long numeroConta) 
            throws Exception {
        return servico.fecharConta(numeroConta);
        // salvar transação aqui
    }

    @Override
    public String listarTransacoes(Long numeroConta) 
            throws Exception {
        return "NÃO IMPLEMENTADO AINDA";
    }
    
    @Override
    public String listarTransacoes() throws Exception {
        return "NÃO IMPLEMENTADO AINDA";
    }    
}
