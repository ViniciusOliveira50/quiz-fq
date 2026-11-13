package br.ifms.edu.tsi.lpii;

import java.util.List;
//Contrato
public interface DataSourceInterface<T> {
	public void salvar(T t) throws Exception;
	public void remover(Integer id) throws Exception;
	public List<T> listar() throws Exception;
	public T buscarPorId(Integer id) throws Exception;
}
