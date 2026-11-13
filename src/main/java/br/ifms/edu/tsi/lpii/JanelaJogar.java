package br.ifms.edu.tsi.lpii;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
*
* @author Vinicius de Oliveira
*/
public class JanelaJogar extends JFrame{
	private JPanel painelNorte;
	private JPanel painelCentro;
	private JPanel painelSul;
	
	private JButton botaoVoltar;
	
	private Integer numeroAleatorio;
	private Random random;
	
	private JButton botaoQ1;
	private JButton botaoQ2;
	private JButton botaoQ3;
	private JButton botaoQ4;
	
	private DataSourceInterface<Perguntas> dataSource;
	private JLabel rotuloTitulo;
	private String categoria;
	
	private Set<Integer> numerosUnicos = new LinkedHashSet<>();
	private List<Perguntas> perguntasCategoria = new ArrayList<>();
	List<Integer> numerosAleatoriosUnicos;
	
	private int contador;

	public JanelaJogar(DataSourceInterface<Perguntas> dataSource, String categoria){
		this.dataSource = dataSource;
		
		this.categoria = categoria;
		
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
		// add(painelSul, BorderLayout.SOUTH);
		
		botaoVoltar = new JButton("Voltar");
		JPanel linhaTopo = new JPanel(new FlowLayout(FlowLayout.LEFT));
		linhaTopo.add(botaoVoltar);
		painelNorte.add(linhaTopo);
		
		initPainelJogar();
		initAcoesBotoes();
		
		setVisible(true);
		
	}
 

	public void initPainelJogar() {
		try {
			List<Perguntas> Perguntas = dataSource.listar();
			for(Perguntas pergunta : Perguntas) {
				if(pergunta.getCategoria().equals(categoria)) {
					perguntasCategoria.add(pergunta);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "deu ruim");
			
		}
		
		
	
		for (int i = 0; i < 5;) {	
		    if (numerosUnicos.add(geraNumeroAleatorio())) { // true só se for novo numero 
		    	
		    	i++;
		    }
		}
		numerosAleatoriosUnicos = new ArrayList<>(numerosUnicos);
		
		
		contador = 0;
		JPanel painelPerguntaRespostas = new JPanel(new GridLayout(3,1));
		
		rotuloTitulo = new JLabel(perguntasCategoria.get(numerosAleatoriosUnicos.get(contador)).getPergunta());
		
	    rotuloTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		     
		rotuloTitulo.setBorder(
				BorderFactory.createCompoundBorder(
				           BorderFactory.createLineBorder(Color.GRAY, 2),
				           BorderFactory.createEmptyBorder(10, 110, 10, 110) // Padding interno
				       )
				   );
		List<String> opcoes = perguntasCategoria.get(numerosAleatoriosUnicos.get(contador)).getOpcoes();
		botaoQ1 = new JButton(opcoes.get(0));
		botaoQ2 = new JButton(opcoes.get(1));
		JPanel parteQ1Q2 = new JPanel();
		parteQ1Q2.add(botaoQ1);
		parteQ1Q2.add(botaoQ2);
		botaoQ3 = new JButton(opcoes.get(2));
		botaoQ4 = new JButton(opcoes.get(3));
		JPanel parteQ3Q4 = new JPanel();
		parteQ3Q4.add(botaoQ3);
		parteQ3Q4.add(botaoQ4);
		
		painelPerguntaRespostas.add(rotuloTitulo);
		painelPerguntaRespostas.add(parteQ1Q2);
		painelPerguntaRespostas.add(parteQ3Q4);
		
		painelCentro.add(painelPerguntaRespostas);
	}
	
	public Integer geraNumeroAleatorio(){
		random = new Random();
		numeroAleatorio = random.nextInt(perguntasCategoria.size());
		return numeroAleatorio;
	
	}
	
	public void atualizarPergunta() {
		rotuloTitulo.setText(perguntasCategoria.get(numerosAleatoriosUnicos.get(contador)).getPergunta());
		List<String> opcoes = perguntasCategoria.get(numerosAleatoriosUnicos.get(contador)).getOpcoes();
		botaoQ1.setText(opcoes.get(0));
		botaoQ2.setText(opcoes.get(1));
		botaoQ3.setText(opcoes.get(2));
		botaoQ4.setText(opcoes.get(3));
		
	}
	
	
	
	private void initAcoesBotoes() {
		
		botaoQ1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				verificarResposta(1);
			}
		});
		
		botaoQ2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				verificarResposta(2);			
			}
		});
		
		botaoQ3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				verificarResposta(3);			
			}
		});
		
		botaoQ4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				verificarResposta(4);		
			}
		});
		
		botaoVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				voltar();				
			}
		});
		
		
	}
	
	public void verificarResposta(Integer idResposta) {
		if(perguntasCategoria.get(numerosAleatoriosUnicos.get(contador)).getIdRespostaCorreta().equals(idResposta)) {
			contador++;
			if(contador < 5) {
				atualizarPergunta();
			}else {
				JOptionPane.showMessageDialog(this, "Você venceu é isso!!");
				voltar();
			}
		}else {
	         dispose();
	         new JanelaInicio().setVisible(true);
	        			
		}
	}
	

	private void voltar() {
		dispose();
        new JanelaInicio().setVisible(true);	
	}
	
}
