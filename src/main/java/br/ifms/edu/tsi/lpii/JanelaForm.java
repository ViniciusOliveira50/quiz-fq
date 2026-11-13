package br.ifms.edu.tsi.lpii;

import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.GraphicsDevice;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class JanelaForm extends JFrame {
	public static final String ESTATICO = "ESTATICO";
	public static final String NOVO = "NOVO";
	
	private String estadoAtual;
    
	private JButton botaoVoltar;
	private JButton botaoNovo;
	private JButton botaoCancelar;
	private JButton botaoEnviar;
	
	private JLabel rotuloCategoria;
	private JLabel rotuloPergunta;
	private JLabel rotuloOpcao1;
	private JLabel rotuloOpcao2;
	private JLabel rotuloOpcao3;
	private JLabel rotuloOpcao4;
	private JLabel rotuloOpcaoCorreta;
	
	private JTextField campoCategoria;
	private JTextField campoPergunta;
	private JTextField campoOpcao1;
	private JTextField campoOpcao2;
	private JTextField campoOpcao3;
	private JTextField campoOpcao4;
	private JTextField campoOpcaoCorreta;
	
	private Perguntas pergunta;
	private Integer contador;
	private List<Perguntas> listaDePerguntas;
	
	private JPanel painelNorte;
	private JPanel painelCentro;
	private JPanel painelSul;
	
	private DataSourceInterface<Perguntas> dataSource;
	
	public JanelaForm(DataSourceInterface<Perguntas> dataSource){
		this.dataSource = dataSource;
		contador = 0;
		
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
		
		
		painelNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
		painelCentro = new JPanel(new FlowLayout(FlowLayout.LEFT));
		painelSul = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		
				
		add(painelNorte, BorderLayout.NORTH);
		add(painelCentro, BorderLayout.CENTER);
		add(painelSul, BorderLayout.SOUTH);
		
		initPainelForm();
		initAcoesBotoes();
		
		
		setVisible(true);
		setEstado(ESTATICO);
	}
	
	private void initPainelForm() {
		
		Font fonte = new Font("Roboto Condensed", Font.PLAIN, 20);
		Dimension tamanhoLabel = new Dimension(300, 60);
	    Dimension tamanhoBotao = new Dimension(300, 60);
	 
	    botaoVoltar = new JButton("Voltar");
	    
	    JPanel painelTopo = new JPanel();
	    JPanel linhaTopo = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    linhaTopo.add(botaoVoltar);
		painelTopo.add(linhaTopo);
		
		painelNorte.add(painelTopo);
		
		JPanel painelForm = new JPanel(new GridLayout(8, 1));
		
		rotuloCategoria = new JLabel("Categoria:");
		rotuloPergunta = new JLabel("Pergunta: ");
		rotuloOpcao1 = new JLabel("Opção 1: ");
		rotuloOpcao2 = new JLabel("Opção 2: ");
		rotuloOpcao3 = new JLabel("Opção 3: ");
		rotuloOpcao4 = new JLabel("Opção 4: ");
		rotuloOpcaoCorreta = new JLabel("Opção correta: ");
		
		campoCategoria = new JTextField(30);
		campoPergunta = new JTextField(30);
		campoOpcao1 = new JTextField(30);
		campoOpcao2 = new JTextField(30);
		campoOpcao3 = new JTextField(30);
		campoOpcao4 = new JTextField(30);
		campoOpcaoCorreta = new JTextField(1);
		
		JPanel linhaCategoria = new JPanel(new FlowLayout(FlowLayout.LEFT));
		linhaCategoria.add(rotuloCategoria);
		linhaCategoria.add(campoCategoria);
		painelForm.add(linhaCategoria);
		
		JPanel linhaPergunta = new JPanel(new FlowLayout(FlowLayout.LEFT));
		linhaPergunta.add(rotuloPergunta);
		linhaPergunta.add(campoPergunta);
		painelForm.add(linhaPergunta);
		
		JPanel linhaOpcao1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		linhaOpcao1.add(rotuloOpcao1);
		linhaOpcao1.add(campoOpcao1);
		painelForm.add(linhaOpcao1);
		
		JPanel linhaOpcao2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		linhaOpcao2.add(rotuloOpcao2);
		linhaOpcao2.add(campoOpcao2);
		painelForm.add(linhaOpcao2);
		
		JPanel linhaOpcao3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		linhaOpcao3.add(rotuloOpcao3);
		linhaOpcao3.add(campoOpcao3);
		painelForm.add(linhaOpcao3);
		
		JPanel linhaOpcao4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		linhaOpcao4.add(rotuloOpcao4);
		linhaOpcao4.add(campoOpcao4);
		painelForm.add(linhaOpcao4);
		
		JPanel linhaOpcaoCorreta = new JPanel(new FlowLayout(FlowLayout.LEFT));
		linhaOpcaoCorreta.add(rotuloOpcaoCorreta);
		linhaOpcaoCorreta.add(campoOpcaoCorreta);
		painelForm.add(linhaOpcaoCorreta);
		
		botaoEnviar = new JButton("Enviar");
		botaoNovo = new JButton("Novo");
		botaoCancelar = new JButton("Cancelar");
		JPanel linhaFinal = new JPanel(new FlowLayout(FlowLayout.LEFT));
		linhaFinal.add(botaoNovo);
		linhaFinal.add(botaoCancelar);
		linhaFinal.add(botaoEnviar);
		painelForm.add(linhaFinal);
		
		painelCentro.add(painelForm);
		
	}
	
	private void initAcoesBotoes() {
		botaoNovo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				novo();				
			}
		});
		
		botaoCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelar();				
			}
		});
		
		botaoEnviar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enviar();				
			}
		});
		
		botaoVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				voltar();				
			}
		});
		
	}
	
	private void novo() {
		pergunta = new Perguntas();
		pergunta.setId(0);
		setEstado(NOVO);
		
	}
	
	private void enviar() { 
		String categoria = campoCategoria.getText();
		String perguntaF = campoPergunta.getText();
		String opcaoCorreta = campoOpcaoCorreta.getText();
		
		List<String> opcoes = new ArrayList<>();
		opcoes.add(campoOpcao1.getText());
		opcoes.add(campoOpcao2.getText());
		opcoes.add(campoOpcao3.getText());
		opcoes.add(campoOpcao4.getText());
		

		
		try {
			Validador.validarPreenchido(categoria);
			Validador.validarPreenchido(perguntaF);
			Validador.validarPreenchido(opcoes.get(0));
			Validador.validarPreenchido(opcoes.get(1));
			Validador.validarPreenchido(opcoes.get(2));
			Validador.validarPreenchido(opcoes.get(3));
			Validador.validarPreenchido(opcaoCorreta);
			
			pergunta.setCategoria(categoria.toLowerCase());
			pergunta.setPergunta(perguntaF.toLowerCase());
			pergunta.setOpcoes(opcoes);
			pergunta.setIdRespostaCorreta(Integer.parseInt(opcaoCorreta));
			
			dataSource.salvar(pergunta);
			JOptionPane.showMessageDialog(this, "Pergunta salvo com sucesso.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		
		
		setEstado(ESTATICO);
		
		limparForm();
		
	}
	
	private void cancelar() {
		setEstado(ESTATICO);
		limparForm();
	}
	
	private void limparForm() {
		campoCategoria.setText("");
		campoPergunta.setText("");
		campoOpcao1.setText("");
		campoOpcao2.setText("");
		campoOpcao3.setText("");
		campoOpcao4.setText("");
		campoOpcaoCorreta.setText("");
	}
	
	private void voltar() {
		dispose();
        new JanelaInicio().setVisible(true);	
	}
	
	private void setEstado(String novoEstado) {
		estadoAtual = novoEstado;
		switch (estadoAtual) {
			case ESTATICO:
				estadoEstatico();
				break;
			case NOVO:
				estadoNovo();
				break;
		}
	}
	
	private void estadoEstatico() {
		botaoNovo.setEnabled(true);
		botaoCancelar.setEnabled(false);
		botaoEnviar.setEnabled(false);
		campoCategoria.setEnabled(false);
		campoPergunta.setEnabled(false);
		campoOpcao1.setEnabled(false);
		campoOpcao2.setEnabled(false);
		campoOpcao3.setEnabled(false);
		campoOpcao4.setEnabled(false);
		campoOpcaoCorreta.setEnabled(false);
	}
	
	private void estadoNovo() {
		botaoNovo.setEnabled(false);
		botaoCancelar.setEnabled(true);
		botaoEnviar.setEnabled(true);
		campoCategoria.setEnabled(true);
		campoPergunta.setEnabled(true);
		campoOpcao1.setEnabled(true);
		campoOpcao2.setEnabled(true);
		campoOpcao3.setEnabled(true);
		campoOpcao4.setEnabled(true);
		campoOpcaoCorreta.setEnabled(true);
	}
	
	
}



