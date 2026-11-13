package br.ifms.edu.tsi.lpii;

import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.GraphicsDevice;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;

import javax.swing.BorderFactory;

import javax.swing.JOptionPane;

public class JanelaInicio extends JFrame {
    
	private JLabel rotuloTitulo;
	
	private JButton botaoJogar;
	private JButton botaoCriar;
	private JButton botaoEditar;
	private JButton botaoSair;
	
	private JPanel painelNorte;
	private JPanel painelCentro;
	private JPanel painelSul;
	
	public JanelaInicio(){
		setUndecorated(true); // Remove bordas e barra de título
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        if (gd.isFullScreenSupported()) {
            gd.setFullScreenWindow(this); // Entra em tela cheia
        } else {
            setSize(Toolkit.getDefaultToolkit().getScreenSize());
            setVisible(true);
        }
		
		
		painelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER));
		painelCentro = new JPanel();
		painelSul = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		
				
		add(painelNorte, BorderLayout.NORTH);
		add(painelCentro, BorderLayout.CENTER);
		add(painelSul, BorderLayout.SOUTH);
		
		initPainelInicio();
		initAcoesBotoes();
		
		setVisible(true);
	}
	
	private void initPainelInicio() {
		JPanel painelBotoes = new JPanel();
		painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS)); // coluna vertical

		Font fonte = new Font("Roboto Condensed", Font.PLAIN, 20);
		Dimension tamanhoLabel = new Dimension(300, 60);
		
	    rotuloTitulo = new JLabel("QUIZ FQ");
	    

	    rotuloTitulo.setFont(fonte); 
	    
	    rotuloTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
	    

	    
	    rotuloTitulo.setBorder(
	        BorderFactory.createCompoundBorder(
	            BorderFactory.createLineBorder(Color.GRAY, 2),
	            BorderFactory.createEmptyBorder(10, 110, 10, 110) // Padding interno
	        )
	    );
	    
	    Dimension tamanhoBotao = new Dimension(300, 60);
	    

	    botaoJogar = new JButton("Jogar");
	    botaoJogar.setAlignmentX(Component.CENTER_ALIGNMENT);
	    botaoCriar = new JButton("Criar");
	    botaoCriar.setAlignmentX(Component.CENTER_ALIGNMENT);
	    botaoEditar = new JButton("Editar");
	    botaoEditar.setAlignmentX(Component.CENTER_ALIGNMENT);
	    botaoSair = new JButton("Sair");
	    botaoSair.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    
	    
	    botaoJogar.setPreferredSize(tamanhoBotao);
	    botaoJogar.setMaximumSize(tamanhoBotao); // evita esticar demais
	    botaoJogar.setFont(fonte);
	    botaoCriar.setPreferredSize(tamanhoBotao);
	    botaoCriar.setMaximumSize(tamanhoBotao); // evita esticar demais
	    botaoCriar.setFont(fonte);
	    botaoEditar.setPreferredSize(tamanhoBotao);
	    botaoEditar.setMaximumSize(tamanhoBotao); // evita esticar demais
	    botaoEditar.setFont(fonte);
	    botaoSair.setPreferredSize(tamanhoBotao);
	    botaoSair.setMaximumSize(tamanhoBotao); // evita esticar demais
	    botaoSair.setFont(fonte);
	    
	    painelBotoes.add(Box.createVerticalGlue()); // empurra para baixo
	    painelBotoes.add(rotuloTitulo);
	    painelBotoes.add(Box.createRigidArea(new Dimension(0, 20))); // espaçamento
	    painelBotoes.add(botaoJogar);
	    painelBotoes.add(Box.createRigidArea(new Dimension(0, 10)));
	    painelBotoes.add(botaoCriar);
	    painelBotoes.add(Box.createRigidArea(new Dimension(0, 10)));
	    painelBotoes.add(botaoEditar);
	    painelBotoes.add(Box.createRigidArea(new Dimension(0, 10)));
	    painelBotoes.add(botaoSair);
	    painelBotoes.add(Box.createVerticalGlue()); // empurra para cima

	    painelCentro.setLayout(new GridBagLayout()); // centraliza o painelBotoes no centro
	    painelCentro.add(painelBotoes); // adiciona a coluna dentro do centro
		
	}
	
	private void initAcoesBotoes() {
		
		botaoJogar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 try {
			         dispose();
			         new JanelaSelecionar(new DataSourceJSON()).setVisible(true);
			     } catch (IOException ex) {
			         ex.printStackTrace();
			         JOptionPane.showMessageDialog(null, "Erro ao abrir o formulário Jogar.");
			     }				
			}
		});
		
		botaoCriar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		        try {
		            dispose();
		            new JanelaForm(new DataSourceJSON()).setVisible(true);
		        } catch (IOException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Erro ao abrir o formulário.");
		        }			
			}
		});
		
		botaoEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		        try {
		            dispose();
		            new JanelaEditar(new DataSourceJSON()).setVisible(true);
		        } catch (IOException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Erro ao abrir o formulário.");
		        }				
			}
		});
		
		botaoSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sair();				
			}
		});
	}
	
	private void sair() {
		System.exit(0);
	}
	
	
	
}



