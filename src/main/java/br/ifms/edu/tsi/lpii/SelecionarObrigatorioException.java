package br.ifms.edu.tsi.lpii;

public class SelecionarObrigatorioException extends Exception{
	@Override
	public String getMessage() {
		return "Selecione um campo.";
	}
}
