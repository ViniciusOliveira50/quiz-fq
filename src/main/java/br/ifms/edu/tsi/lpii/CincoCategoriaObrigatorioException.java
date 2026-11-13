package br.ifms.edu.tsi.lpii;

public class CincoCategoriaObrigatorioException extends Exception{
	@Override
	public String getMessage() {
		return "A categoria tem que ter no minimo 5 perguntas.";
	}
}