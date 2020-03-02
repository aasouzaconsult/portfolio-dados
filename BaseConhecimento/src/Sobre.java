/* Sobre.java
 * @author Antonio Alex
 */

 
import javax.swing.*;
import java.awt.*;

public class Sobre extends JFrame {
    
    public static void main(String args[])
    {
       Sobre Sob = new Sobre();
       Sob.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public Sobre() 
    {
        super("Z Solutions - Sobre");
        
        Container tela = getContentPane();
        
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        tela.setLayout(layout);
        
        JLabel Sistema = new JLabel("Sistemas Auxiliares - TI");
        tela.add(Sistema);
        JLabel Versao = new JLabel(" - Versão 2020.1");
        tela.add(Versao);
        JLabel Autor = new JLabel("Autor: Alex Souza");
        tela.add(Autor);
        JLabel Email = new JLabel("E-mail: aasouzaconsult@gmail.com");
        tela.add(Email);
        
        setSize(300,120);
        setLocation(320,300);
        setResizable(false); // Não dar opcao de Maximizar a tela
        setVisible(true);
    }
}