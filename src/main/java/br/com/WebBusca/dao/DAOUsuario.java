package br.com.WebBusca.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.WebBusca.dominio.Usuario;

public class DAOUsuario extends conexao implements CRUDusuario<Usuario>{

	@Override
	public String cadastrar(Usuario dados) {
		String msg = "";
		//vamos tentar cadastrar o usuário e caso dê erro, este será
		//capturado e tratado
		try {
			//verificar se a conexão com o banco de dados foi aberta ou não
			//caso tenha sido aberta, iremos executar os comandos de sql
			//para cadastrar o usuario. Senão, iremos exibir uma mensagem 
			//para o usuário de que não foi possível estabelecer comunicação
			//com o banco de dados
			if(abrirConexao()) {
				String sql = "insert into usuario(nomeusuario,senha,email,telefone,nomecompleto,cpf) values (?,?,?,?,?,?)";
				//preparar a consulta para ser executada
				pst = con.prepareStatement(sql);
				//passagem dos dados aos parâmetros da consulta, ou seja, 
				//cada ponto de interrogação irá receber um dado correspondente 
				//a um campo da tabela
				pst.setString(1,dados.getNomeusuario());
				pst.setString(2,dados.getSenha());
				pst.setString(3, dados.getEmail());
				pst.setString(4,dados.getTelefone());
				pst.setString(5, dados.getNomecompleto());
				pst.setString(6, dados.getCpf());
				
				//executar a consulta e verificar se o retorno da execução
				//é maior que 0(zero)
				if(pst.executeUpdate() > 0) {
					msg = "Cadastro realizado";
				}
				else {
					msg = "Não foi possível cadastrar";
				}
			}
			else {
				msg = "Não foi possível estabelecer a conexão com o banco de dados";
			}
		}
		catch(SQLException se) {
			msg = "Erro no cadastro. "+se.getMessage();
		}
		catch(Exception e) {
			msg = "Erro inesperado. "+e.getMessage();
		}
		finally {
			fecharConexao();
		}
		
		return msg;
	}

	@Override
	public List<Usuario> listar() {
		List<Usuario> lista = new ArrayList<Usuario>();
		
		try {
			if(abrirConexao()) {
				String sql = "Select * from usuario";
				//vamos preparar a consulta para ser executada
				pst = con.prepareStatement(sql);
				//vamos executar a consulta e guardar o resultado
				//dentro do elemento ResultSet(rs)
				rs = pst.executeQuery();
				
				/*
				 * O comando acima faz com que o resultado da consulta
				 * do select seja atribuido ao rs(ResultSet). Ele 
				 * recebe todos os dados, mas não de forma organizada.
				 * para criar a lista de usuário, nos aplicamos o 
				 * comando while para verificar se há conteúdo em
				 * rs, e, caso tenho dados, passamos os dados 
				 * para um novo objeto usuario e depois adicionamos 
				 * a uma lista de novos usuarios. Esta lista será 
				 * retornada com todos os usuarios do banco.
				 * */
				
				while(rs.next()) {
					Usuario us = new Usuario();
					us.setIdusuario(rs.getInt(1));
					us.setNomeusuario(rs.getString(2));
					us.setSenha(rs.getString(3));
					us.setEmail(rs.getString(4));
					us.setTelefone(rs.getString(5));
					us.setNomecompleto(rs.getString(6));
					us.setCpf(rs.getString(7));
					
					lista.add(us);
				}
				
				
			}
			else {
				throw new Exception("Não foi possível estabelecer a conexão com o banco");
			}
		}
		catch(SQLException se) {
			new Exception("Erro na consulta. "+se.getMessage());
		}
		catch(Exception e) {
			new Exception("Erro inesperado. "+e.getMessage());
		}
		finally {
			fecharConexao();
		}
		
		return lista;
	}

	@Override
	public Usuario pesquisar(Usuario dados) {
		
		Usuario us = new Usuario();
		
		try {
			if(abrirConexao()) {
				String sql = "Select * from usuario where idusuario=?";
				//vamos preparar a consulta para ser executada
				pst = con.prepareStatement(sql);
				
				pst.setInt(1, dados.getIdusuario());
				
				//vamos executar a consulta e guardar o resultado
				//dentro do elemento ResultSet(rs)
				rs = pst.executeQuery();
				
				
				
				if(rs.next()) {
					
					us.setIdusuario(rs.getInt(1));
					us.setNomeusuario(rs.getString(2));
					us.setSenha(rs.getString(3));
					us.setEmail(rs.getString(4));
					us.setTelefone(rs.getString(5));
					us.setNomecompleto(rs.getString(6));
					us.setCpf(rs.getString(7));
					
					
				}
				
				
			}
			else {
				throw new Exception("Não foi possível estabelecer a conexão com o banco");
			}
		}
		catch(SQLException se) {
			new Exception("Erro na consulta. "+se.getMessage());
		}
		catch(Exception e) {
			new Exception("Erro inesperado. "+e.getMessage());
		}
		finally {
			fecharConexao();
		}
		
		return us;
	}

	@Override
	public String atualizar(Usuario dados) {
		String msg = "";
		//vamos tentar atualizar o usuário e caso dê erro, este será
		//capturado e tratado
		try {
			//verificar se a conexão com o banco de dados foi aberta ou não
			//caso tenha sido aberta, iremos executar os comandos de sql
			//para cadastrar o usuario. Senão, iremos exibir uma mensagem 
			//para o usuário de que não foi possível estabelecer comunicação
			//com o banco de dados
			if(abrirConexao()) {
				String sql = "update usuario set nomeusuario=?,email=?,telefone=?,nomecompleto=? where idusuario=?";
				//preparar a consulta para ser executada
				pst = con.prepareStatement(sql);
				//passagem dos dados aos parâmetros da consulta, ou seja, 
				//cada ponto de interrogação irá receber um dado correspondente 
				//a um campo da tabela
				pst.setString(1,dados.getNomeusuario());
				pst.setString(2, dados.getEmail());
				pst.setString(3,dados.getTelefone());
				pst.setString(4, dados.getNomecompleto());
				pst.setInt(5, dados.getIdusuario());
				
				//executar a consulta e verificar se o retorno da execução
				//é maior que 0(zero)
				if(pst.executeUpdate() > 0) {
					msg = "Atualização realizada";
				}
				else {
					msg = "Não foi possível atualizar";
				}
			}
			else {
				msg = "Não foi possível estabelecer a conexão com o banco de dados";
			}
		}
		catch(SQLException se) {
			msg = "Erro ao atualizar. "+se.getMessage();
		}
		catch(Exception e) {
			msg = "Erro inesperado. "+e.getMessage();
		}
		finally {
			fecharConexao();
		}
		
		return msg;
	}

	@Override
	public String apagar(Integer id) {
		String msg = "";
		//vamos tentar deletar o usuário e caso dê erro, este será
		//capturado e tratado
		try {
			//verificar se a conexão com o banco de dados foi aberta ou não
			//caso tenha sido aberta, iremos executar os comandos de sql
			//para deletar o usuario. Senão, iremos exibir uma mensagem 
			//para o usuário de que não foi possível estabelecer comunicação
			//com o banco de dados
			if(abrirConexao()) {
				String sql = "delete from usuario where idusuario=?";
				//preparar a consulta para ser executada
				pst = con.prepareStatement(sql);
				//passagem dos dados aos parâmetros da consulta, ou seja, 
				//cada ponto de interrogação irá receber um dado correspondente 
				//a um campo da tabela
				pst.setInt(1,id);				
				
				//executar a consulta e verificar se o retorno da execução
				//é maior que 0(zero)
				if(pst.executeUpdate() > 0) {
					msg = "Usuário apagado";
				}
				else {
					msg = "Não foi possível apagar";
				}
			}
			else {
				msg = "Não foi possível estabelecer a conexão com o banco de dados";
			}
		}
		catch(SQLException se) {
			msg = "Erro ao apagar. "+se.getMessage();
		}
		catch(Exception e) {
			msg = "Erro inesperado. "+e.getMessage();
		}
		finally {
			fecharConexao();
		}
		
		return msg;
	}

	@Override
	public boolean login(Usuario dados) {
		boolean auth = true;
		
		try {
			if(abrirConexao()) {
				String sql = "Select nomeusuario,senha,email,cpf from usuario where nomeusuario=? or email=? or cpf=? and senha=?";
				//vamos preparar a consulta para ser executada
				pst = con.prepareStatement(sql);
				
				pst.setString(1, dados.getNomeusuario());
				pst.setString(2, dados.getEmail());
				pst.setString(3, dados.getCpf());
				pst.setString(4, dados.getSenha());
				
				//vamos executar a consulta e guardar o resultado
				//dentro do elemento ResultSet(rs)
				rs = pst.executeQuery();
							
				if(!rs.next()) {
					auth = false;					
				}	
			}
			else {
				throw new Exception("Não foi possível estabelecer a conexão com o banco");
			}
		}
		catch(SQLException se) {
			new Exception("Erro na consulta. "+se.getMessage());
		}
		catch(Exception e) {
			new Exception("Erro inesperado. "+e.getMessage());
		}
		finally {
			fecharConexao();
		}
		
		return auth;
	}

	@Override
	public String alterarSenha(Usuario dados) {
		String msg = "";
		//vamos tentar atualizar o usuário e caso dê erro, este será
		//capturado e tratado
		try {
			//verificar se a conexão com o banco de dados foi aberta ou não
			//caso tenha sido aberta, iremos executar os comandos de sql
			//para atualizar o usuario. Senão, iremos exibir uma mensagem 
			//para o usuário de que não foi possível estabelecer comunicação
			//com o banco de dados
			if(abrirConexao()) {
				String sql = "update usuario set senha=? where idusuario=?";
				//preparar a consulta para ser executada
				pst = con.prepareStatement(sql);
				//passagem dos dados aos parâmetros da consulta, ou seja, 
				//cada ponto de interrogação irá receber um dado correspondente 
				//a um campo da tabela
				pst.setString(1,dados.getSenha());
				pst.setInt(2, dados.getIdusuario());
				
				//executar a consulta e verificar se o retorno da execução
				//é maior que 0(zero)
				if(pst.executeUpdate() > 0) {
					msg = "Atualização realizada";
				}
				else {
					msg = "Não foi possível atualizar";
				}
			}
			else {
				msg = "Não foi possível estabelecer a conexão com o banco de dados";
			}
		}
		catch(SQLException se) {
			msg = "Erro ao atualizar. "+se.getMessage();
		}
		catch(Exception e) {
			msg = "Erro inesperado. "+e.getMessage();
		}
		finally {
			fecharConexao();
		}
		
		return msg;
	}

}
