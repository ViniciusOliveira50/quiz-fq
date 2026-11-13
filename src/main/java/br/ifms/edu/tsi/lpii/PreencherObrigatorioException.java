package br.ifms.edu.tsi.lpii;


public class PreencherObrigatorioException extends Exception{
	@Override
	public String getMessage() {
		return "Preencha todos os campos.";
	}
}
