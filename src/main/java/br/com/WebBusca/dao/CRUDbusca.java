package br.com.WebBusca.dao;

import java.util.List;

public interface CRUDbusca<T> {
	String cadastrar(T dados);
	List<T> listar();
	T pesquisar(T dados);
	String atualizar(T dados);
	String apagar(Integer id);
	
}
