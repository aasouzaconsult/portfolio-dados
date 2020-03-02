/* FichaServidores.java
 * Criado em 16/04/2019, atualizado em 02/03/2020
 * @author  Alex Souza
 */

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import java.sql.*;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

public class FichaServidores extends JFrame implements ActionListener {
    
    // Criando os Labels
	JLabel lb_Codigo, lb_Categoria, lb_Produto, lb_Nucleo, lb_Titulo, lb_Descricao,
	lb_Info, lb_Obs,	lb_Ativo, lb_Autor, lb_Data, lb_Link, lb_Caminho, lb_AlexSouza, lb_Pesquisar;
	
    // Criando os TextField
	JTextField tf_Codigo, tf_Titulo, tf_Data, tf_Link, tf_Caminho, tf_Pesquisar;
	
	JTextArea ta_Descricao, ta_Info, ta_Obs;
	
	// Criando ComboBox
	JComboBox cb_Nucleo;
	String[] Nucleo = { "Dados", "Infraestrutura", "Suporte", "Negócio", "Contratos" };
	
	JComboBox cb_Categoria;
	String[] Categoria = { "Ficha de Servidores" };
    
	JComboBox cb_Produto;
	String[] Produto = { "InfraEstrutura Local", "Datacenter", "Nuvem" };
	
	JComboBox cb_Autor;
	String[] Autor = { "Alex Souza", "Antonio Souza", "Alex Silva", "Usuário 1", "Usuário 2", "Usuário 3" };
	
	JComboBox cb_Ativo;
	String[] Ativo = { "SIM","NAO"};
	
	// Criando Botões
    JButton bt_Anterior, bt_Proximo, bt_Ultimo, bt_Novo, bt_Sair, bt_Adiciona, bt_Altera;
    JButton bt_Remove, bt_Inicio, bt_PesquisarCodigo, bt_Pesquisar, bt_Abrir, bt_Ir, bt_url;
    
    // Criando Painel
    JPanel painelPrincipal;
    
    // Criando o Scroll Pane
    JScrollPane scrollpane;
    
    
    // Criando ImageIcon
    private ImageIcon icone;
    
    // Variaveis para o Banco de Dados
    private Connection con;
	private Statement stmt;
    private ResultSet rs;
       
    public FichaServidores (String titulo)
    {
        super("Z Solutions - Ficha de Servidores");
        iniciaComponentes();
        
        //Adiciona o icone a janela
		icone = new ImageIcon("EduTECHBrasil.jpg");
		icone.setImage(icone.getImage().getScaledInstance(30, 18, 500));
		setIconImage(icone.getImage());
		
		ConeccaoDB();
		DadosDB();
    }
    
    private void iniciaComponentes()
    {
        //Instanciando Label´s e TextField´s
    	lb_Codigo      = new JLabel("Código");
    	tf_Codigo      = new JTextField();
    	tf_Codigo.setEditable(false);
    	cb_Nucleo      = new JComboBox();
    	lb_Categoria   = new JLabel("Categoria");
    	cb_Categoria   = new JComboBox();
    	lb_Produto     = new JLabel("Produto");
    	cb_Produto     = new JComboBox();
    	lb_Titulo      = new JLabel("Titulo");
    	tf_Titulo      = new JTextField();
    	lb_Descricao   = new JLabel("Descrição");
    	ta_Descricao   = new JTextArea();
    	
    	ta_Descricao.setLineWrap(true); 
        ta_Descricao.setWrapStyleWord(true);
    	JScrollPane scDesc = new JScrollPane (ta_Descricao);
    	scDesc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
       	lb_Info        = new JLabel("Info.");
       	ta_Info        = new JTextArea();
       	lb_Obs         = new JLabel("Obs");
       	ta_Obs         = new JTextArea();
       	lb_Ativo       = new JLabel("Ativo?");
       	cb_Ativo       = new JComboBox();
       	lb_Autor       = new JLabel("Autor");
       	cb_Autor       = new JComboBox();
       	lb_Data        = new JLabel("Data");
       	tf_Data        = new JTextField();
       	tf_Data.setEditable(false);
       	lb_Link        = new JLabel("Link");
       	tf_Link        = new JTextField();
       	lb_Caminho     = new JLabel("Caminho");
       	tf_Caminho     = new JTextField();
       	lb_Pesquisar   = new JLabel("Pesquise...");
       	tf_Pesquisar   = new JTextField();
       	lb_AlexSouza   = new JLabel("T.I - Tecnologia e Inovação!");
    	                                   
        // Instanciando Botoes
        bt_Inicio          = new JButton("Inicio");
    	bt_Proximo         = new JButton("Proximo");
    	bt_Anterior        = new JButton("Anterior");
    	bt_Ultimo          = new JButton("Ultimo");
    	bt_Novo            = new JButton("Novo*");
    	bt_Adiciona        = new JButton("Adiciona");
    	bt_Altera          = new JButton("Altera");
    	bt_Remove          = new JButton("Remove");
    	bt_Sair            = new JButton("Sair");
    	
    	Icon img_Explorer  = new ImageIcon("1.ico");
    	bt_Abrir           = new JButton("Explorer");
    	bt_Abrir.setIcon(img_Explorer);
    	
    	bt_Ir              = new JButton("Ver");
    	bt_url             = new JButton("Navegar");
    	
    	bt_PesquisarCodigo = new JButton("Código");
    	bt_Pesquisar       = new JButton("Texto");
        
        painelPrincipal = new JPanel();
        
        // SAIR
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
            	int sair = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Z Solutions - Confirmação", JOptionPane.YES_NO_OPTION);
            	if (sair == JOptionPane.YES_OPTION) {
            		FichaServidores.this.dispose();
            	} else if (sair == JOptionPane.NO_OPTION) {
            		System.out.println("Código: ");
            		this.windowOpened(null);
            	}
            }
        }
        );
        
        //addWindowListener(this);
        bt_Inicio.addActionListener(this);
    	bt_Proximo.addActionListener(this);
    	bt_Anterior.addActionListener(this);
    	bt_Ultimo.addActionListener(this);
    	bt_Novo.addActionListener(this);
    	bt_Adiciona.addActionListener(this);
    	bt_Altera.addActionListener(this);
    	bt_Remove.addActionListener(this);
    	bt_Sair.addActionListener(this);
    	
    	bt_Abrir.addActionListener(this);
    	bt_Ir.addActionListener(this);
    	bt_url.addActionListener(this);
    	
    	bt_PesquisarCodigo.addActionListener(this);
    	bt_Pesquisar.addActionListener(this);
        
        painelPrincipal.setLayout(null);
        
        // Montando a Tela (y(coluna), x(linha), z(largura), w(altura))
        
        // Código
        lb_Codigo.setBounds(10,10,70,25);
        painelPrincipal.add(lb_Codigo);
        tf_Codigo.setBounds(80,10,250,25);
        painelPrincipal.add(tf_Codigo);
        
        // Nucleo
        for(int i = 0; i < Nucleo.length; i++) cb_Nucleo.addItem(Nucleo[i]);
        cb_Nucleo.setBounds(340,10,160,25);
        painelPrincipal.add(cb_Nucleo);
        cb_Nucleo.setToolTipText("Aplicado pelo Núcleo...");
        
        // Categoria        
        lb_Categoria.setBounds(10,40,70,25);
        painelPrincipal.add(lb_Categoria);
        for(int i = 0; i < Categoria.length; i++) cb_Categoria.addItem(Categoria[i]);
        cb_Categoria.setBounds(80,40,150,25);
        painelPrincipal.add(cb_Categoria);
        
        // Produto
        lb_Produto.setBounds(250,40,50,25);
        painelPrincipal.add(lb_Produto);
        for(int i = 0; i < Produto.length; i++) cb_Produto.addItem(Produto[i]);
        cb_Produto.setBounds(310,40,190,25);
        painelPrincipal.add(cb_Produto);
        
        // Titulo
        lb_Titulo.setBounds(10,70,70,25);
        painelPrincipal.add(lb_Titulo);
        tf_Titulo.setBounds(80,70,420,25);
        painelPrincipal.add(tf_Titulo);

        lb_Descricao.setBounds(10,100,70,25);
        painelPrincipal.add(lb_Descricao);      
        scDesc.setBounds(80,100,420,100);
        painelPrincipal.add(scDesc);
        
        // Informacao
        lb_Info.setBounds(10,205,70,25);
        painelPrincipal.add(lb_Info);
        ta_Info.setBounds(80,205,420,200);
        painelPrincipal.add(ta_Info);
        ta_Info.setLineWrap(true); 
        ta_Info.setWrapStyleWord(true);
        
        // Observacao
        lb_Obs.setBounds(10,410,70,25);
        painelPrincipal.add(lb_Obs);
        ta_Obs.setBounds(80,410,420,50);
        painelPrincipal.add(ta_Obs);
        ta_Obs.setLineWrap(true); 
        ta_Obs.setWrapStyleWord(true);
        
        // Ativo
        lb_Ativo.setBounds(10,465,70,25);
        painelPrincipal.add(lb_Ativo);
        for(int i = 0; i < Ativo.length; i++) cb_Ativo.addItem(Ativo[i]);
        cb_Ativo.setBounds(80,465,150,25);
        painelPrincipal.add(cb_Ativo);
        cb_Ativo.setToolTipText("Informa se o procedimento ainda é válido...");
        
        // Autor
        lb_Autor.setBounds(250,465,70,25);
        painelPrincipal.add(lb_Autor);
        for(int i = 0; i < Autor.length; i++) cb_Autor.addItem(Autor[i]);
        cb_Autor.setBounds(310,465,190,25);
        painelPrincipal.add(cb_Autor);
        cb_Autor.setToolTipText("Criador do procedimento");
        
        // Data
        lb_Data.setBounds(10,495,70,25);
        painelPrincipal.add(lb_Data);
        tf_Data.setBounds(80,495,420,25);
        painelPrincipal.add(tf_Data);
        
        // Link
        lb_Link.setBounds(10,525,70,25);
        painelPrincipal.add(lb_Link);
        tf_Link.setBounds(80,525,420,25);
        painelPrincipal.add(tf_Link);
        
        // Caminho
        lb_Caminho.setBounds(10,555,70,25);
        painelPrincipal.add(lb_Caminho);
        tf_Caminho.setBounds(80,555,420,25);
        painelPrincipal.add(tf_Caminho);
        
        // Pesquisar
        lb_Pesquisar.setBounds(10,585,70,25);
        painelPrincipal.add(lb_Pesquisar);
        lb_Pesquisar.setForeground(Color.RED);      
        tf_Pesquisar.setBounds(80,585,200,25);
        painelPrincipal.add(tf_Pesquisar);
        tf_Pesquisar.setToolTipText("Digite um código (Alt + C) ou texto para pesquisar (Alt + X)");
        
        bt_PesquisarCodigo.setBounds(290,585,100,25);
        painelPrincipal.add(bt_PesquisarCodigo);
        bt_PesquisarCodigo.setForeground(Color.RED);
        bt_PesquisarCodigo.setMnemonic(KeyEvent.VK_C);
        
        bt_Pesquisar.setBounds(400,585,100,25);
        painelPrincipal.add(bt_Pesquisar);
        bt_Pesquisar.setForeground(Color.RED);
        bt_Pesquisar.setMnemonic(KeyEvent.VK_X);
        
        // Botoes
        bt_Inicio.setBounds(520,10,90,25);
        painelPrincipal.add(bt_Inicio);
        bt_Inicio.setMnemonic(KeyEvent.VK_I);
        
        bt_Proximo.setBounds(520,40,90,25);
        painelPrincipal.add(bt_Proximo);
        bt_Proximo.setMnemonic(KeyEvent.VK_P);
        
        bt_Anterior.setBounds(520,70,90,25);
        painelPrincipal.add(bt_Anterior);
        bt_Anterior.setMnemonic(KeyEvent.VK_A);
        bt_Anterior.setEnabled(false); 
        
        bt_Ultimo.setBounds(520,100,90,25);
        painelPrincipal.add(bt_Ultimo);
        bt_Ultimo.setMnemonic(KeyEvent.VK_U);
                
        bt_Novo.setBounds(520,130,90,25);
        painelPrincipal.add(bt_Novo);
        bt_Novo.setMnemonic(KeyEvent.VK_L);
        
        bt_Adiciona.setBounds(520,160,90,25);
        painelPrincipal.add(bt_Adiciona);
        bt_Adiciona.setMnemonic(KeyEvent.VK_D);
        
        bt_Altera.setBounds(520,190,90,25);
        painelPrincipal.add(bt_Altera);
        bt_Altera.setMnemonic(KeyEvent.VK_T);
        
        bt_Remove.setBounds(520,220,90,25);
        painelPrincipal.add(bt_Remove);
        bt_Remove.setMnemonic(KeyEvent.VK_R);
        
        bt_Sair.setBounds(520,250,90,25);
        painelPrincipal.add(bt_Sair);
        bt_Sair.setMnemonic(KeyEvent.VK_S);
        bt_Sair.setToolTipText("Clique aqui para sair!");
        
        bt_url.setBounds(520,525,90,25);
        painelPrincipal.add(bt_url);
        bt_url.setMnemonic(KeyEvent.VK_R);
        bt_url.setForeground(Color.RED);
        bt_url.setToolTipText("Navegar no link informado");
        
        bt_Ir.setBounds(520,555,90,25);
        painelPrincipal.add(bt_Ir);
        bt_Ir.setMnemonic(KeyEvent.VK_R);
        bt_Ir.setForeground(Color.RED);
        bt_Ir.setToolTipText("Abre o caminho informado");
        
        bt_Abrir.setRolloverIcon(icone);
        bt_Abrir.setBounds(520,585,90,25);
        painelPrincipal.add(bt_Abrir);
        bt_Abrir.setMnemonic(KeyEvent.VK_B);        
        bt_Abrir.setToolTipText("Abra qualquer arquivo do seu computador!");
        
        this.getContentPane().add(painelPrincipal);
        painelPrincipal.setPreferredSize(new Dimension(620,620)); //Largura / Altura
        
        scrollpane = new JScrollPane(painelPrincipal);
        getContentPane().add(scrollpane, BorderLayout.CENTER);
                
        pack();
    }
    
    // Método de Limpar dados do Formulario
    public void limparCampos(){
    	tf_Codigo.setText("");      
    	cb_Nucleo.setSelectedIndex(0);     
    	cb_Categoria.setSelectedIndex(0);
    	cb_Produto.setSelectedIndex(0);
    	tf_Titulo.setText("");    
    	ta_Descricao.setText("");
    	ta_Info.setText("");
    	ta_Obs.setText("");
    	cb_Ativo.setSelectedIndex(0);
    	cb_Autor.setSelectedIndex(0);
    	tf_Data.setText("");        
    	tf_Link.setText("");        
    	tf_Caminho.setText("");
    	tf_Pesquisar.setText("");
    	tf_Codigo.requestFocus();
    }
        
    //**************************************************************************
    // Conectando Banco de Dados
    
    private void ConeccaoDB()
	{
		try
		{	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		    con = DriverManager.getConnection("jdbc:sqlserver://99.99.999.99:1433;databaseName=BaseDeConhecimento;user=sa;password=xxxxxxxx");
		}
		catch (Exception e)
		{   JOptionPane.showMessageDialog(null, "Erro ao Conectar ao Banco de Dados!", "Z Solutions - Conexão BD", 0);
		}
	}
    
	private void DadosDB()
	{
		try
		{
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("SELECT * FROM TbFichaServidores ORDER BY Codigo;");
			rs.next();
            PreencheCampos();
		}
		catch (Exception e)
		{   JOptionPane.showMessageDialog(null, "Erro ao Buscar Dados!", "Z Solutions - Seleção de Dados", 0);
		}
	}
	
	private void PreencheCampos() // Começa a pegar informações do BD.
	{
        try
	       { 
        	
              tf_Codigo.setText(rs.getString("Codigo"));
              cb_Nucleo.setSelectedItem(rs.getString("Nucleo")); 
              cb_Categoria.setSelectedItem(rs.getString("Categoria"));
		      cb_Produto.setSelectedItem(rs.getString("Produto"));
		      tf_Titulo.setText(rs.getString("Titulo"));    
		      ta_Descricao.setText(rs.getString("Descricao"));
		      ta_Info.setText(rs.getString("Info"));     
		      ta_Obs.setText(rs.getString("Obs"));
		      cb_Ativo.setSelectedItem(rs.getString("Ativo"));
		      cb_Autor.setSelectedItem(rs.getString("Autor"));
		      tf_Data.setText(rs.getString("Data"));
		      tf_Link.setText(rs.getString("Link"));
		      tf_Caminho.setText(rs.getString("Caminho"));	 
		      
		      bt_Adiciona.setEnabled(false);
		      
		   }
        catch (Exception e)
           {   JOptionPane.showMessageDialog(null, "Erro ao preencher dados!", "Z Solutions - Dados", 0);
               System.out.println(e.toString());
       	   }
    }
	
    
    public void actionPerformed(java.awt.event.ActionEvent evt)
    {
        // Variaveis Globais
    	String a,b,c,d,e,f,g,h,i,j,l,m,n,p;  
    	
    	//Botão Ultimo
        if (evt.getSource()==bt_Ultimo){
        	 try
             { 
        	   bt_Altera.setEnabled(true);
               bt_Remove.setEnabled(true);
               bt_Proximo.setEnabled(false);
           	   bt_Ultimo.setEnabled(false);
           	   bt_Anterior.setEnabled(true);
           	       		 
        	   rs.last();
        	   tf_Codigo.setText(rs.getString("Codigo"));
               cb_Nucleo.setSelectedItem(rs.getString("Nucleo")); 
               cb_Categoria.setSelectedItem(rs.getString("Categoria"));
 		       cb_Produto.setSelectedItem(rs.getString("Produto"));
 		       tf_Titulo.setText(rs.getString("Titulo"));    
 		       ta_Descricao.setText(rs.getString("Descricao"));
 		       ta_Info.setText(rs.getString("Info"));     
 		       ta_Obs.setText(rs.getString("Obs"));
 		       cb_Ativo.setSelectedItem(rs.getString("Ativo"));
 		       cb_Autor.setSelectedItem(rs.getString("Autor"));
 		       tf_Data.setText(rs.getString("Data"));
 		       tf_Link.setText(rs.getString("Link"));
 		       tf_Caminho.setText(rs.getString("Caminho"));
             }
             catch (Exception x)
             {
  			  System.out.println(x.toString());
  			  System.exit(0);
  		   };
        };
        
        // Botão Novo
        if (evt.getSource()==bt_Novo)
        {
        	bt_Adiciona.setEnabled(true);
        	bt_Altera.setEnabled(false);
        	bt_Remove.setEnabled(false);
        	bt_Proximo.setEnabled(false);
        	bt_Ultimo.setEnabled(false);
        	bt_Anterior.setEnabled(false);
        	
        	System.out.println("Novo");
        	limparCampos();
        	cb_Nucleo.requestFocus();
        };
        
        if (evt.getSource() == bt_Sair)
        {
        	int sair = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Z Solutions - Confirmação", JOptionPane.YES_NO_OPTION);

        	if (sair == JOptionPane.YES_OPTION) {
        		FichaServidores.this.dispose();
        		 
        	} else if (sair == JOptionPane.NO_OPTION) {
        	   tf_Pesquisar.requestFocus();
        	}
        };        	
            
        if (evt.getSource() == bt_Proximo)
           try
           {
           	  bt_Altera.setEnabled(true);
           	  bt_Remove.setEnabled(true);
           	  bt_Proximo.setEnabled(true);
        	  bt_Ultimo.setEnabled(true);
        	  bt_Anterior.setEnabled(true);
        	         	   
        	  if (rs.next()) {
        		 tf_Codigo.setText(rs.getString("Codigo"));
                 cb_Nucleo.setSelectedItem(rs.getString("Nucleo")); 
                 cb_Categoria.setSelectedItem(rs.getString("Categoria"));
    		     cb_Produto.setSelectedItem(rs.getString("Produto"));
    		     tf_Titulo.setText(rs.getString("Titulo"));    
    		     ta_Descricao.setText(rs.getString("Descricao"));
    		     ta_Info.setText(rs.getString("Info"));     
    		     ta_Obs.setText(rs.getString("Obs"));
    		     cb_Ativo.setSelectedItem(rs.getString("Ativo"));
    		     cb_Autor.setSelectedItem(rs.getString("Autor"));
    		     tf_Data.setText(rs.getString("Data"));
    		     tf_Link.setText(rs.getString("Link"));
    		     tf_Caminho.setText(rs.getString("Caminho"));
              }
           }
           catch (Exception x)
           {  JOptionPane.showMessageDialog(null, "Registro não encontrado!", "Z Solutions - Próximo", 1);
	          tf_Codigo.requestFocus();
		   };
        	        
         if (evt.getSource() == bt_Adiciona)
            try
            {               
               stmt = con.createStatement();
               
               a = tf_Codigo.getText();
               b = (String) cb_Categoria.getSelectedItem();
               c = (String) cb_Produto.getSelectedItem();
               d = (String) cb_Nucleo.getSelectedItem();
               e = tf_Titulo.getText();
               f = ta_Descricao.getText();
               g = ta_Info.getText();
               h = ta_Obs.getText();
               i = (String) cb_Ativo.getSelectedItem();
               j = (String) cb_Autor.getSelectedItem();
               l = tf_Data.getText();            
               m = tf_Link.getText();
               n = tf_Caminho.getText();
               
               System.out.println("b = " + b);
               System.out.println("c = " + c);
               System.out.println("d = " + d);
               System.out.println("j = " + j);
               
               stmt.executeUpdate("INSERT INTO TbFichaServidores VALUES ('"+b+"','"+c+"','"+d+"','"+e+"','"+f+"','"+g+"','"+h+"','"+i+"','"+j+"',getdate(),'"+m+"','"+n+"')");
               rs = stmt.executeQuery("SELECT * FROM TbFichaServidores ORDER BY Codigo;");
               JOptionPane.showMessageDialog(null, "Registro Adicionado!", "Z Solutions - Sucesso", 1);
		       rs.next();
               PreencheCampos();
               limparCampos();
            }
            catch (SQLException x)
	        {  JOptionPane.showMessageDialog(null, "Erro ao Incluir!", "Z Solutions - Inclusão", 0);
		       tf_Codigo.requestFocus();
		    };
        
         if (evt.getSource() == bt_PesquisarCodigo)      	 
             try
             {  System.out.println("Pesq. Cód");
            	p  = tf_Pesquisar.getText();         
                rs = stmt.executeQuery("SELECT * FROM TbFichaServidores WHERE Codigo = " + p);
                
                while (rs.next()) { 
                	PreencheCampos();
                	}
           	    tf_Pesquisar.setText("");
           	    tf_Pesquisar.requestFocus();
             }
             catch (SQLException x)
 	        {  
            	 JOptionPane.showMessageDialog(null, "Não encontrado, gentileza refazer a pesquisa!", "Z Solutions - Pesquisar", 1);
            	 tf_Pesquisar.setText("");
            	 tf_Pesquisar.requestFocus();
 		    };
 		    
 	     if (evt.getSource() == bt_Pesquisar)      	 
 	         try
 	         {
 	          	p = tf_Pesquisar.getText();
 	          	
 	            if (p.trim().equals("")){ 
 	            	 JOptionPane.showMessageDialog(null, "Informe um parametro para pesquisa!", "Z Solutions - Pesquisar", 1);
 	            	 tf_Pesquisar.setText("");
 	            	 tf_Pesquisar.requestFocus();
 	            } 
 	            else {
 	          	
 	            //System.out.println("Teste:" + p);
 	          	
 	            rs = stmt.executeQuery("SELECT * FROM TbFichaServidores WHERE Titulo    like '%"+p+"%'" 
 	          	+                "UNION SELECT * FROM TbFichaServidores WHERE Descricao like '%"+p+"%'"
 	          	+                "UNION SELECT * FROM TbFichaServidores WHERE Info      like '%"+p+"%'");
 	           	 	               
 	            while (rs.next()) { 
 	            	
 	            	JFrame w = new JFrame("Resultado da pesquisa");
  	                JPanel upperPanel = new JPanel();
  	                
  	                Locale locale = new Locale("pt","BR"); 
  	       	        GregorianCalendar calendar = new GregorianCalendar(); 
  	       	        SimpleDateFormat formatador = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy' - 'HH':'mm'h'",locale); 
  	       	        System.out.println(formatador.format(calendar.getTime()));
  	       	   
  	                String content = "Resultado da Pesquisa\n"
	            		           + "-------------------------------\n"
  	                		       + "Código: "     + rs.getString(1)  + "\n"
  	                		       + "Categoria: "  + rs.getString(2)  + "\n"
  	                               + "Produto: "    + rs.getString(3)  + "\n"
  	                               + "Núcleo: "     + rs.getString(4)  + "\n"
  	                               + "Titulo: "     + rs.getString(5)  + "\n"
  	                               + "Descrição: "  + rs.getString(6)  + "\n"
  	                               + "Informações:" + rs.getString(7)  + "\n"
  	                               + "Ativo: "      + rs.getString(9)  + "\n"
  	                               + "Data: "       + rs.getString(11) + "\n"
  	                               + "Link: "       + rs.getString(12) + "\n"
  	                               + "Caminho: "    + rs.getString(13) + "\n"
  	                               + "-------------------------------\n";
  	              
  	                upperPanel.add(new JTextArea(content));  	                
  	                w.getContentPane().add(upperPanel, "North");
  	              
  	            	tf_Pesquisar.setText("");
  	            	tf_Pesquisar.requestFocus();
  	                
  	                w.pack();
  	                w.setVisible(true);	                
 	           	}                
 	           }
 	            
                   bt_Altera.setEnabled(true);
 	         	   bt_Remove.setEnabled(true);
 	         	   bt_Proximo.setEnabled(true);
 	      	       bt_Ultimo.setEnabled(true);
 	      	       bt_Anterior.setEnabled(true);
 	               stmt = con.createStatement();
 	               rs   = stmt.executeQuery("SELECT * FROM TbFichaServidores ORDER BY Codigo;");
 	            
 	         }
 	          catch (SQLException x)
 	 	      {   JOptionPane.showMessageDialog(null, "Não encontrado, gentileza refazer a pesquisa!", "Z Solutions - Pesquisar", 1); 
 	 	          tf_Pesquisar.setText("");   
 	 		  };   
        		       
 	 	 // Botão remover	  
         if (evt.getSource() == bt_Remove)
         {
        	
        	int excluir = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o registro?", "Z Solutions - Confirmação", JOptionPane.YES_NO_OPTION);

        	if (excluir == JOptionPane.YES_OPTION) {
                try
                {
                   System.out.println("Remove");
                   stmt = con.createStatement();
                   a = tf_Codigo.getText();
                   System.out.println("Removendo o registro: " + a);
                   stmt.executeUpdate("DELETE FROM TbFichaServidores WHERE Codigo = " + a);
                   rs = stmt.executeQuery("SELECT * FROM TbFichaServidores ORDER BY Codigo;");
                   rs.next();
                   PreencheCampos();
    		       JOptionPane.showMessageDialog(null, "Registro: " + a + " removido com sucesso!", "Z Solutions - Sucesso", 1);
                }
                catch (SQLException x)
    		    {  JOptionPane.showMessageDialog(null, "Informe um código!", "Z Solutions - Remover", 0);
    		       tf_Codigo.requestFocus();
                };
     	    } else if (excluir == JOptionPane.NO_OPTION) {
     	        tf_Codigo.requestFocus();
     	    }
          };
        	             
         if (evt.getSource() == bt_Altera)
         try
         {
        	System.out.println("Altera");
            
            stmt = con.createStatement();
                        
            a = tf_Codigo.getText();
            b = (String) cb_Categoria.getSelectedItem();
            c = (String) cb_Produto.getSelectedItem();
            d = (String) cb_Nucleo.getSelectedItem();
            e = tf_Titulo.getText();
            f = ta_Descricao.getText();
            g = ta_Info.getText();
            h = ta_Obs.getText();
            i = (String) cb_Ativo.getSelectedItem();
            j = (String) cb_Autor.getSelectedItem();
            l = tf_Data.getText();            
            m = tf_Link.getText();
            n = tf_Caminho.getText();
            
            stmt.executeUpdate("UPDATE TbFichaServidores "
            		         + "SET Categoria = '"+b+"'"
            		         + ",   Produto   = '"+c+"'"
            		         + ",   Nucleo    = '"+d+"'"
            		         + ",   Titulo    = '"+e+"'"
            		         + ",   Descricao = '"+f+"'"
            		         + ",   Info   = '"+g+"'"
            		         + ",   Obs       = '"+h+"'"
            		         + ",   Ativo     = '"+i+"'"
            		         + ",   Autor     = '"+j+"'"
            		         + ",   Data      = '"+l+"'"
            		         + ",   Link      = '"+m+"'"
            		         + ",   Caminho   = '"+n+"'"
            		         + "WHERE Codigo  = " + a);
            
            rs = stmt.executeQuery("SELECT * FROM TbFichaServidores WHERE Codigo >= "+a+" ORDER BY Codigo;");
            rs.next();
            JOptionPane.showMessageDialog(null, "Registro: " +a+ " alterado com sucesso!", "Z Solutions - Sucesso", 1);
         }
         catch (SQLException x)
	     {  JOptionPane.showMessageDialog(null, "Erro ao atualizar!", "Z Solutions - Update", 0);
		    tf_Codigo.requestFocus();
		 };

         if (evt.getSource() == bt_Inicio)
            try
            {  bt_Altera.setEnabled(true);
         	   bt_Remove.setEnabled(true);
         	   bt_Proximo.setEnabled(true);
      	       bt_Ultimo.setEnabled(true);
      	       bt_Anterior.setEnabled(true);
               stmt = con.createStatement();
               rs   = stmt.executeQuery("SELECT * FROM TbFichaServidores ORDER BY Codigo;");
		       rs.next();
               PreencheCampos();
            }
            catch (SQLException x)
        	{ System.out.println(x.toString());
			  System.exit(0);
			};
			
		if (evt.getSource() == bt_url) {
			Desktop desk = java.awt.Desktop.getDesktop();     
			try {    
				 desk.browse(new java.net.URI(tf_Link.getText()));
			} catch (Exception e1) {    
			     e1.printStackTrace();    
			}
		  };
		
		  // Botão - Abrir (Abre um arquivo)
	      if (evt.getSource() == bt_Abrir) {
			JFileChooser fc = new JFileChooser();
			   int res = fc.showOpenDialog(null);
			 
			   if(res == JFileChooser.APPROVE_OPTION){
		    
			    java.awt.Desktop desktop = java.awt.Desktop.getDesktop();    
			    
			    try {
				   	desktop.open(new File(fc.getSelectedFile().getPath())); // Abrir arquivo
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			    
			}  else JOptionPane.showMessageDialog(null, "Gentileza selecionar um arquivo.");
		  };
		
		  // Botão - Abrir (Abre o caminho selecionado)
	      if (evt.getSource() == bt_Ir) {   	  
	    	  
		    try {
			        java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
				   	desktop.open(new File(tf_Caminho.getText())); // Abrir arquivo
				} catch (IOException e2) {
					JOptionPane.showMessageDialog(null, "Caminho não encontrado", "Z Solutions - Não encontrado", 0);
					//e2.printStackTrace();
				}
		    };
		    
		    //Botão Voltar
	        if (evt.getSource()==bt_Anterior)
	            try
	           {
	           	  bt_Altera.setEnabled(true);
	           	  bt_Remove.setEnabled(true);
	           	  bt_Proximo.setEnabled(true);
	        	  bt_Ultimo.setEnabled(true);
	        	  bt_Anterior.setEnabled(true);
	        	         	   
	        	  if (rs.previous()) {
	                
	        		 tf_Codigo.setText(rs.getString("Codigo"));
	                 cb_Nucleo.setSelectedItem(rs.getString("Nucleo")); 
	                 cb_Categoria.setSelectedItem(rs.getString("Categoria"));
	    		     cb_Produto.setSelectedItem(rs.getString("Produto"));
	    		     tf_Titulo.setText(rs.getString("Titulo"));    
	    		     ta_Descricao.setText(rs.getString("Descricao"));
	    		     ta_Info.setText(rs.getString("Info"));     
	    		     ta_Obs.setText(rs.getString("Obs"));
	    		     cb_Ativo.setSelectedItem(rs.getString("Ativo"));
	    		     cb_Autor.setSelectedItem(rs.getString("Autor"));
	    		     tf_Data.setText(rs.getString("Data"));
	    		     tf_Link.setText(rs.getString("Link"));
	    		     tf_Caminho.setText(rs.getString("Caminho"));
	              }
	           }
	           catch (Exception x)
	           {  JOptionPane.showMessageDialog(null, "Registro não encontrado!", "Z Solutions - Próximo", 1);
		          tf_Codigo.requestFocus();
			   };
		    
        }
        
    //Metodos do WindowListener
	public void windowActivated(WindowEvent evt)
	{
	// Comentario
	}

	public void windowDeactivated(WindowEvent evt)
	{
	// Comentario
	}

	public void windowClosing(WindowEvent evt)
	{
		
	}

	public void windowClosed(WindowEvent evt)
    {
		
	}

	public void windowOpened(WindowEvent evt)
	{
	// Comentario
	}

	public void windowIconified(WindowEvent evt)
	{
	// Comentario
	}

	public void windowDeiconified(WindowEvent evt)
	{
	// Comentario
	}
	
    // Fim de Banco de Dados
    //**************************************************************************
    
    public static void main(String args[]){
        FichaServidores cli = new FichaServidores("Z Solutions - Base de Conhecimento - ITIL");
        
        cli.setVisible(true);
        cli.setResizable(true); // Não dar opcao de Maximizar a tela
        cli.setLocation(300,0);
        cli.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);  
    }
}