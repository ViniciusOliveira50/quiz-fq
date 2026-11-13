package br.ifms.edu.tsi.lpii;

import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
/**
*
* @author Vinicius de Oliveira
*/
public class JanelaSelecionar extends JFrame{
	
	private JPanel painelNorte;
	private JPanel painelCentro;
	private JPanel painelSul;

	private JTable tabelaCategoria;

	private JButton botaoVoltar;
	private JButton botaoJogar;
	
	private String categoria;
	private Perguntas categoriap;
	
	private int contadorc;
	
	private DataSourceInterface<Perguntas> dataSource;
	
	private AnnotationResolver resolverCategoria;
	private ObjectTableModel<Perguntas> tableModelCategoria;
	

	
	public JanelaSelecionar(DataSourceInterface<Perguntas> dataSource){
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
		        if (categoriasUnicas.add(p.getCategoria())) { // true só se for nova categoria
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
        
        JPanel formularioJogar = new JPanel(new GridLayout(2, 1));
        formularioJogar.add(painelTabelaCategoria);
	
		botaoJogar = new JButton("Jogar");
		JPanel linhaBaixo = new JPanel(new FlowLayout(FlowLayout.CENTER));
		linhaBaixo.add(botaoJogar);
		formularioJogar.add(linhaBaixo);
		
		painelCentro.add(formularioJogar);
	}
	
	private void initAcoesBotoes() {
		
		botaoVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				voltar();				
			}
		});
		
		botaoJogar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Validador.validarSelecao(tabelaCategoria.getSelectedRow());
					
					categoria = tableModelCategoria.getValue(tabelaCategoria.getSelectedRow()).getCategoria();
					categoriap = tableModelCategoria.getValue(tabelaCategoria.getSelectedRow());
				    List<Perguntas> todasPerguntasPergunta = dataSource.listar();
				    for (Perguntas p : todasPerguntasPergunta) {
				    	if(p.getCategoria().equals(categoriap.getCategoria())) {
				    		contadorc++;
				    	}
				    }
				    Validador.validarQuantidade(contadorc);


					jogar();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(JanelaSelecionar.this, ex.getMessage());
				}
			}
		});
		
	}
	
	
	private void voltar() {
		dispose();
        new JanelaInicio().setVisible(true);	
	}
	
	private void jogar() {
		try {
	         dispose();
	         new JanelaJogar(new DataSourceJSON(), categoria).setVisible(true);
	     } catch (IOException ex) {
	         ex.printStackTrace();
	         JOptionPane.showMessageDialog(null, "Erro ao abrir o Janela Jogar.");
	     }
	}
	
}

