package br.com.WebBusca.service;

import java.io.IOException;

import br.com.WebBusca.dao.DAObusca;
import br.com.WebBusca.dominio.Busca;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ServicesBusca
 */
public class ServicesBusca extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServicesBusca() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Cargo = request.getParameter("txtCargo");
		String Descricao = request.getParameter("txtDescricao");
		String requisitos = request.getParameter("txtRequisitos");
		String Localizacao = request.getParameter("txtLocalizacao");
		String Salario = request.getParameter("Salario");
		
	
		//Instancia da classe livro
		Busca Bc = new Busca();
		//passando os dados para o objeto livro(lv)
		Bc.setCargo(Cargo);
		Bc.setDescricao(Descricao);
		Bc.setRequisitos(requisitos);
		Bc.setSalario(Float.parseFloat(Salario));
		
		//instanci da camada DAO para efetuar o cadastro do livro
		
		DAObusca dv = new DAObusca();
		if (dv.cadastrar(Bc).equals("Cadastro Realizado")){
			response.sendRedirect("confirmacao.html");
			
		}
	}

	
	

}
