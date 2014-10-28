package br.edu.contrato;

/**
 * Interface com intuito de salientar todas as mensagens que o cliente
 * precisa executar.
 */
public interface OperadorBancario {
    public Long criarConta(String nomeCliente, Double saldoInicial) 
            throws java.lang.Exception;            
    public Double consultarSaldo(Long numeroConta) 
            throws java.lang.Exception;
    public String efetuarDeposito(Long numeroConta, Double valor)
            throws java.lang.Exception;
    public String efetuarSaque(Long numerConta, Double valor)    
            throws java.lang.Exception;
    public String fecharConta(Long numeroConta) 
            throws java.lang.Exception;    
    public String listarTransacoes(Long numeroConta)
            throws java.lang.Exception;
    public String listarTransacoes()
            throws java.lang.Exception;    
}
