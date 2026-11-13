package br.ifms.edu.tsi.lpii;

import javax.swing.JOptionPane;

public class Validador {
	public static void validarSelecao(int categoria) throws SelecionarObrigatorioException{
		if(categoria == -1) {
			throw new SelecionarObrigatorioException();
		}
		
	}
	
	public static void validarQuantidade(int qtde) throws CincoCategoriaObrigatorioException{
		if(qtde < 5) {
			throw new CincoCategoriaObrigatorioException();
		}
		
	}
	
	public static void validarPreenchido(String valor) throws PreencherObrigatorioException{
		if(valor == null || valor.length() == 0) {
			throw new PreencherObrigatorioException();
		}	
	}
	
}
