package br.com.WebBusca.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.WebBusca.dominio.Busca;




public class DAObusca extends conexao implements CRUDbusca<Busca> {

	@Override
	public String cadastrar(Busca dados) {
		String msg = "";
		try {
			if(abrirConexao()) {
				String sql = "insert into busca_area(cargo,descricao,requisitos,localizacao,salario)values(?,?,?,?,?)";
				//preparar a consulta para a execução
				pst = con.prepareStatement(sql);
				//passagem dos parâmetros para a consulta
				pst.setInt(1,dados.getid_Area());
				pst.setString(2, dados.getCargo());
				pst.setString(3, dados.getDescricao());
				pst.setString(4, dados.getRequisitos());
				pst.setString(5, dados.getLocalizacao());
				pst.setFloat(6, dados.getSalario());
				
				
				
				if(pst.executeUpdate() > 0) {
					msg = "Cadastro realizado";
				}
				else {
					msg = "Não foi possível cadastrar";
				}
			}
			else {
				msg = "Não foi possível estabelecer a conexão com o banco";
			}
		}
		catch(SQLException se) {
			msg = "Erro ao tentar cadastrar. "+se.getMessage();
		}
		catch(Exception e) {
			msg = "Erro inesperado. "+e.getMessage();
		}
		finally {
			fecharConexao();
		}
		return msg;
	}

	
	public String apagar(Integer id) {
		String msg = "";
		try {
			if(abrirConexao()) {
				String sql = "delete from busca_area where  id_Area=?";
				//preparar a consulta para a execução
				pst = con.prepareStatement(sql);
				//passagem dos parâmetros para a consulta
				pst.setInt(1,id);				
				
				if(pst.executeUpdate() > 0) {
					msg = "Apagado com sucesso";
				}
				else {
					msg = "Não foi possível apagar";
				}
			}
			else {
				msg = "Não foi possível estabelecer a conexão com o banco";
			}
		}
		catch(SQLException se) {
			msg = "Erro ao tentar apagar. "+se.getMessage();
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
	public List<Busca> listar() {
		
		List<Busca> lista = new ArrayList<Busca>();
		try {
			if(abrirConexao()) {
				String sql = "Select * from busca_area order by id_Areas desc";
				pst = con.prepareStatement(sql);
				rs = pst.executeQuery();
				while(rs.next()) {
					Busca Bc = new Busca();
					Bc.setId_Area(rs.getInt(1));
					Bc.setCargo(rs.getString(2));
					Bc.setDescricao(rs.getString(3));
					Bc.setRequisitos(rs.getString(4));
					Bc.setLocalizacao(rs.getString(5));
					Bc.setSalario(rs.getFloat(6));
					
					
					lista.add(Bc);
				}
			}
			else {
				new Exception("Não foi possível estabelecer a conexão com o banco");
			}
		}
		catch(SQLException se) {
			new Exception("Erro na consulta "+se.getMessage());
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
	public Busca pesquisar(Busca dados) {
		Busca Bc = null;
		
		try {
			if(abrirConexao()) {
				String sql = "Select * from busca_area where id_Areas=? or cargo=?";
				pst = con.prepareStatement(sql);
				
				pst.setInt(1, dados.getid_Area());
				pst.setString(2, dados.getCargo());
				
				rs = pst.executeQuery();
				if(rs.next()) {
					Bc = new Busca();
					Bc.setId_Area(rs.getInt(1));
					Bc.setCargo(rs.getString(2));
					Bc.setDescricao(rs.getString(3));
					Bc.setRequisitos(rs.getString(4));
					Bc.setLocalizacao(rs.getString(5));
					Bc.setSalario(rs.getFloat(6));
					
				}
			}
			else {
				new Exception("Não foi possível estabelecer a conexão com o banco");
			}
		}
		catch(SQLException se) {
			new Exception("Erro na consulta "+se.getMessage());
		}
		catch(Exception e) {
			new Exception("Erro inesperado. "+e.getMessage());
		}
		finally {
			fecharConexao();
		}
		
		return Bc;
	}
	


	@Override
	public String atualizar(Busca dados) {
		String msg = "";
		try {
			if(abrirConexao()) {
				String sql = "update busca_area set cargo=?,descricao=?,requisitos=?,localizacao=?,salario=? where id_Areas=?";
				//preparar a consulta para a execução
				pst = con.prepareStatement(sql);
				//passagem dos parâmetros para a consulta
				pst.setString(1,dados.getCargo());
				pst.setString(2, dados.getDescricao());
				pst.setString(3, dados.getRequisitos());
				pst.setString(4,dados.getLocalizacao());
				pst.setFloat(5,dados.getSalario());
				
				
				if(pst.executeUpdate() > 0) {
					msg = "Atualização realizada";
				}
				else {
					msg = "Não foi possível atualizar";
				}
			}
			else {
				msg = "Não foi possível estabelecer a conexão com o banco";
			}
		}
		catch(SQLException se) {
			msg = "Erro ao tentar atualizar. "+se.getMessage();
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
