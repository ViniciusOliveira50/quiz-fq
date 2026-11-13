package br.ifms.edu.tsi.lpii;

import java.util.ArrayList;
import java.util.List;


import com.towel.el.annotation.Resolvable;


public class Perguntas{
	
	@Resolvable(colName = "ID")
	private Integer id;
	@Resolvable(colName = "Pergunta")
	private String pergunta;
	@Resolvable(colName = "Opcoes")
	private List<String> opcoes = new ArrayList<>();
	@Resolvable(colName = "IdRespostaCorreta")
	private Integer idRespostaCorreta;				
	@Resolvable(colName = "Categoria")
	private String categoria;
	
	public Perguntas() {
		
	};
	
	public Perguntas(Integer id, String pergunta, List<String> opcoes, Integer idRespostaCorreta, String categoria) {
		super();
		this.id = id;
		this.pergunta = pergunta;
		this.opcoes = opcoes;
		this.idRespostaCorreta = idRespostaCorreta;
		this.categoria = categoria;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<String> getOpcoes() {
		return opcoes;
	}
	public void setOpcoes(List<String> opcoes) {
		this.opcoes = opcoes;
	}
	public Integer getIdRespostaCorreta() {
		return idRespostaCorreta;
	}
	public void setIdRespostaCorreta(Integer idRespostaCorreta) {
		this.idRespostaCorreta = idRespostaCorreta;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getPergunta() {
		return pergunta;
	}
	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}
}
