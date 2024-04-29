package br.com.WebBusca.dao;

public interface CRUDusuario<T> extends CRUDbusca<T> {
	boolean login(T dados);
	String alterarSenha(T dados);

}