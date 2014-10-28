package br.edu.aplicacao;

import br.edu.contrato.OperadorBancario;
import br.edu.gui.JanelaCliente;
import br.edu.servico.BancarioService;
import br.edu.servico.BancarioServiceService;

public class App {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            BancarioServiceService acesso = new BancarioServiceService();
            BancarioService servico = acesso.getBancarioServicePort();

            OperadorBancario operador = 
                            new br.edu.servico.OperadorBancario(servico);
            
            public void run() {
                new JanelaCliente(operador).setVisible(true);
            }
        });        
    }
}
