package br.com.WebBusca.service;

import java.io.IOException;

import br.com.WebBusca.dao.DAOUsuario;
import br.com.WebBusca.dominio.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Serviceslogin
 */
public class Serviceslogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Serviceslogin() {
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
		String usuario = request.getParameter("txtusuario");
		String senha = request.getParameter("txtsenha");
		Usuario us = new Usuario();
		us.setNomeusuario(usuario);
		us.setSenha(senha);
		
		DAOUsuario du = new DAOUsuario();
		if(du.login(us)) {
			response.sendRedirect("Home");
		}
		else {
			response.sendRedirect("Erro.html");
		}
			
	}

}