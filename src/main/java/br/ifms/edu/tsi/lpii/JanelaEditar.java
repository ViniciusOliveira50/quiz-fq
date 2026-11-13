package br.ifms.edu.tsi.lpii;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;

/**
*
* @author Vinicius de Oliveira
*/

public class JanelaEditar extends JFrame {
	public static final String ESTATICO = "ESTATICO";
	public static final String EDITANDO_EXISTENTE = "EDITANDO_EXISTENTE";
	public static final String ITEM_SELECIONADO = "ITEM_SELECIONADO"; 
	private String estadoAtual;
	
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
	
	private JButton botaoVoltar;
	private JButton botaoCancelar;
	private JButton botaoSalvar;
	
	private JPanel painelNorte;
	private JPanel painelCentro;
	private JPanel painelSul;
	
	private Perguntas pergunta;
	private Perguntas categoria;

	private JTable tabelaCategoria;
	private JTable tabelaPergunta;
	
	private AnnotationResolver resolverCategoria;
	private ObjectTableModel<Perguntas> tableModelCategoria;
	
	private AnnotationResolver resolverPergunta;
	private ObjectTableModel<Perguntas> tableModelPergunta;
	
	private DataSourceInterface<Perguntas> dataSource;
	
	public JanelaEditar(DataSourceInterface<Perguntas> dataSource) {
		this.dataSource = dataSource;
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
		painelCentro = new JPanel(new FlowLayout(FlowLayout.CENTER));
		painelSul = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		add(painelNorte, BorderLayout.NORTH);
		add(painelCentro, BorderLayout.CENTER);
		add(painelSul, BorderLayout.SOUTH);
		
		botaoVoltar = new JButton("Voltar");
		JPanel linhaTopo = new JPanel(new FlowLayout(FlowLayout.LEFT));
		linhaTopo.add(botaoVoltar);
		painelNorte.add(linhaTopo);
		
		initPainelTabelaCategoria();
		initPainelTabelaPergunta();
		initPainelEditaPergunta();
		initAcoesBotoes();
		
		setVisible(true);
	}
	
	private void initPainelTabelaCategoria() {
		JPanel painelTabelaCategoria = new JPanel();
		
		resolverCategoria = new AnnotationResolver(Perguntas.class);
		tableModelCategoria = new ObjectTableModel<>(resolverCategoria, "categoria");
		try {
		    List<Perguntas> todasPerguntasCategoria = dataSource.listar();
		    Set<String> categoriasUnicas = new LinkedHashSet<>();
			
		    for (Perguntas p : todasPerguntasCategoria) {
		        if (categoriasUnicas.add(p.getCategoria())) {
		            tableModelCategoria.add(p);
		        }
		    }
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		
		tabelaCategoria = new JTable(tableModelCategoria);
	
		JScrollPane scrollPane = new JScrollPane(tabelaCategoria);
        scrollPane.setPreferredSize(new Dimension(300, 500));
		
        painelTabelaCategoria.add(scrollPane);
        
		
		painelCentro.add(painelTabelaCategoria);
	}
	
	private void initPainelTabelaPergunta() {
		JPanel painelTabelaPergunta = new JPanel();
		
		resolverPergunta = new AnnotationResolver(Perguntas.class);
		tableModelPergunta = new ObjectTableModel<>(resolverPergunta, "pergunta");
		try {
		    List<Perguntas> todasPerguntasPergunta = dataSource.listar();
		    Set<String> perguntasUnicas = new LinkedHashSet<>();
			
		    for (Perguntas p : todasPerguntasPergunta) {
		        if (perguntasUnicas.add(p.getPergunta())) {
		            tableModelPergunta.add(p);
		        }
		    }
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		tabelaPergunta = new JTable(tableModelPergunta);
		
		JScrollPane scrollPane = new JScrollPane(tabelaPergunta);
        scrollPane.setPreferredSize(new Dimension(300, 500));
		
        painelTabelaPergunta.add(scrollPane);

		
		painelCentro.add(painelTabelaPergunta);
	}
	
	private void initPainelEditaPergunta() {
		
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
		
		botaoSalvar = new JButton("Salvar");
		botaoCancelar = new JButton("Cancelar");
		JPanel linhaFinal = new JPanel(new FlowLayout(FlowLayout.LEFT));
		linhaFinal.add(botaoCancelar);
		linhaFinal.add(botaoSalvar);
		painelForm.add(linhaFinal);
		
		setEstado(ESTATICO);
		
		painelCentro.add(painelForm);
	}
	
	private void selecionarPergunta() {
		pergunta = tableModelPergunta.getValue(tabelaPergunta.getSelectedRow());
		carregarDadosNoFormulario();
		setEstado(EDITANDO_EXISTENTE);
		
	}
	
	private void carregarDadosNoFormulario() {
		campoCategoria.setText(pergunta.getCategoria());
		campoPergunta.setText(pergunta.getPergunta());
		List<String> opcoes = pergunta.getOpcoes();
		campoOpcao1.setText(opcoes.get(0));
		campoOpcao2.setText(opcoes.get(1));
		campoOpcao3.setText(opcoes.get(2));
		campoOpcao4.setText(opcoes.get(3));
		campoOpcaoCorreta.setText(pergunta.getIdRespostaCorreta().toString());
	}
	
	private void selecionarCategoria() {
		categoria = tableModelCategoria.getValue(tabelaCategoria.getSelectedRow());
		carregarDadosNaTabelaPegunta();
		setEstado(ESTATICO);
	}
	
	private void carregarDadosNaTabelaPegunta() {
		tableModelPergunta.clear();
		try {
		    List<Perguntas> todasPerguntasPergunta = dataSource.listar();
		    for (Perguntas p : todasPerguntasPergunta) {
		    	if(p.getCategoria().equals(categoria.getCategoria())) {
		    		tableModelPergunta.add(p);
		    	}
		    }
		    
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	private void initAcoesBotoes() {
		
		botaoVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				voltar();				
			}
		});
		
		botaoSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salvar();				
			}
		});
		
		botaoCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelar();				
			}
		});
		
		tabelaPergunta.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
		
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				selecionarPergunta();
				
			}
		});
		
		tabelaCategoria.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
		
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				selecionarCategoria();
				
			}
		});
		
	}
	
	private void voltar() {
		dispose();
        new JanelaInicio().setVisible(true);	
	}
	
	public void salvar() {
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
			JOptionPane.showMessageDialog(this, "Pergunta editada com sucesso.");
			setEstado(ESTATICO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		

	}
	
	private void cancelar() {
		carregarDadosNoFormulario();
		setEstado(ITEM_SELECIONADO);
	}
	
	private void setEstado(String novoEstado) {
		estadoAtual = novoEstado;
		switch (estadoAtual) {
			case ESTATICO:
				estadoEstatico();
				break;
			case EDITANDO_EXISTENTE:
				estadoEditandoExistente();
		}
	}
	
	private void estadoEstatico() {
		botaoCancelar.setEnabled(false);
		botaoSalvar.setEnabled(false);
		campoCategoria.setEnabled(false);
		campoPergunta.setEnabled(false);
		campoOpcao1.setEnabled(false);
		campoOpcao2.setEnabled(false);
		campoOpcao3.setEnabled(false);
		campoOpcao4.setEnabled(false);
		campoOpcaoCorreta.setEnabled(false);
	}
	
	private void estadoEditandoExistente() {
		botaoCancelar.setEnabled(true);
		botaoSalvar.setEnabled(true);
		campoCategoria.setEnabled(true);
		campoPergunta.setEnabled(true);
		campoOpcao1.setEnabled(true);
		campoOpcao2.setEnabled(true);
		campoOpcao3.setEnabled(true);
		campoOpcao4.setEnabled(true);
		campoOpcaoCorreta.setEnabled(true);
	}
	
	
	
}
