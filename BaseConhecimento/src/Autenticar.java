/*
 * Autenticar.java
 * Created on November 15, 2004, 20:16 PM, Atualizado em 02/03/2020
 * @Author  Antonio Alex
 * Projeto Locadora
 */
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class Autenticar extends JFrame{
  Senha janela;
  String usuario = "ti", senha = "ti";
  
  public static void main(String args[]){
    Autenticar app = new Autenticar();
    app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  
  public Autenticar(){
    super("Z Solutions - Menu principal");  
                    
    // Criando Barra de Menu
    JMenuBar barra = new JMenuBar();
    setJMenuBar(barra);
               
    // Criando Menu Cadastro
    JMenu cadastro = new JMenu("Cadastro");
    cadastro.setMnemonic(KeyEvent.VK_C); //SetMnemonic -> Atalho para Teclado
                
    // Criando SubMenus para Cadastro        
    JMenuItem BaseDeConhecimento = new JMenuItem("Base de Conhecimento");
    BaseDeConhecimento.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.ALT_MASK));
	cadastro.add(BaseDeConhecimento);
	
    // Criando SubMenus para Cadastro - Ficha de Servidores       
    JMenuItem FichaServidores = new JMenuItem("Ficha de Servidores");
    FichaServidores.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.ALT_MASK));
	cadastro.add(FichaServidores);
		
    // Criando Menu Ajuda
	JMenu ajuda = new JMenu("?");
	
	// Criando SubMenus para Ajuda
	JMenuItem sobre = new JMenuItem("Sobre");
	sobre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
    ajuda.add(sobre);     
			                        
    // Montando Menus
    barra.add(cadastro);
    barra.add(ajuda);
    
    // Chamadas de Métodos (Método Base de Conhecimento)
    BaseDeConhecimento.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
    	BaseDeConhecimento bc = new BaseDeConhecimento(null);
        bc.setLocation(300,0);
        bc.setResizable(true); // Não dar opcao de Maximizar a tela
        bc.show();
        bc.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
      }
    });
    
    // Chamadas de Métodos (Método Ficha de Servidores)
    FichaServidores.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
    	FichaServidores bc = new FichaServidores(null);
        bc.setLocation(300,0);
        bc.setResizable(true); // Não dar opcao de Maximizar a tela
        bc.show();
        bc.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
      }
    });
    
    // Chamadas de Métodos (Método Sobre)
    sobre.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Sobre sob = new Sobre();
        sob.setLocation(300,0);
        sob.setResizable(false); // Não dar opcao de Maximizar a tela
        sob.show();
      }
    });       
       
    Container tela = getContentPane();
    
    FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
    tela.setLayout(layout); 
    
    janela = new Senha(null, "Z Solutions - Autenticacao", true);
    janela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    janela.setVisible(true);
    
    setLocation(300,0); // Define a Localizacao da Janela (Frame)
    setSize(620,530);     // Define o tamanho da Janela (Frame)
    setResizable(false);  // Aparece ou nao o Maximizar
    setVisible(true);  
  }
  
  public void verificar(String u, String s){
    if((u.equals(usuario)) && (s.equals(senha)))
      janela.hide();
    else{
      JOptionPane.showMessageDialog(null, "Dados Incorretos.");
      janela.txtUsuario.requestFocus();
    }
  }
  
  private class Senha extends JDialog{
    JTextField txtUsuario;
    JPasswordField txtSenha;
    JButton entrar, cancelar;
  	
  	public Senha(Frame owner, String title, boolean modal){
  	  super(owner, title, modal);
  	  
      Container tela = getContentPane();
    
      BorderLayout layout = new BorderLayout();
      tela.setLayout(layout);
    
      JPanel superior2 = new JPanel();
      
      String titulo = "Informe o nome de usuario e Senha";
      Border etched = BorderFactory.createEtchedBorder();
      Border borda = BorderFactory.createTitledBorder(etched, titulo);
      
      JLabel lblUsuario = new JLabel("Usuario:");
      JLabel lblSenha = new JLabel("Senha:");
      txtUsuario = new JTextField(10);
      txtSenha = new JPasswordField(10);
    
      JPanel superior = new JPanel();
      superior.setLayout(new GridLayout(2, 2, 5, 5));
      superior.add(lblUsuario);
      superior.add(txtUsuario);
      superior.add(lblSenha);
      superior.add(txtSenha);
      
      superior2.setBorder(borda);
      superior2.setLayout(new FlowLayout(FlowLayout.LEFT));
      superior2.add(superior);
    
      Tratador trat = new Tratador();
      
      entrar = new JButton("Entrar");
      entrar.addActionListener(trat);
      getRootPane().setDefaultButton(entrar);
      
      cancelar = new JButton("Cancelar");
      cancelar.addActionListener(trat);
      
      JPanel inferior = new JPanel();
      inferior.setLayout(new FlowLayout(FlowLayout.RIGHT));
      inferior.add(entrar);
      inferior.add(cancelar);
      
      tela.add(BorderLayout.NORTH, superior2);
      tela.add(BorderLayout.SOUTH, inferior);
      
  	  setSize(300, 150);
      setLocationRelativeTo(null);
  	}
  	
    private class Tratador implements ActionListener{
      public void actionPerformed(ActionEvent e){
        String senha = new String(txtSenha.getPassword());
        
        if(e.getSource() == entrar){
          verificar(txtUsuario.getText(), senha);
        }
        else
          System.exit(0);  
      }
    }
  }
}