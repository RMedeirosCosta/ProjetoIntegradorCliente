package br.edu.modelo;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Transacao {
    
    public static final String CRIACAO = "CRIACAO";
    public static final String SAQUE = "SAQUE";
    public static final String DEPOSITO = "DEPOSITO";
    public static final String FECHAMENTO = "FECHAMENTO";    
    
    private Long conta;
    private String tipoTransacao; 
    private String descricao;
    private Date data;
    
    public Transacao() {
        this.conta = 0l;
        this.tipoTransacao = "";
        this.descricao = "";
        this.data = new Date(System.currentTimeMillis());
    }
    
    public Long getConta() {
        return conta;
    }

    public void setConta(Long conta) {
        this.conta = conta;
    }

    public String getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(String tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    @Override
    public String toString() {
        Format formatter = new SimpleDateFormat( "dd/MM/yyyy");
        String data = formatter.format(getData());
        
        return " Conta: " + getConta().toString() + " " +
               " Data: " + data + " " +
               "Operação: " + getTipoTransacao() + " " +
               "Descrição: " + getDescricao();        
    }
    
}
