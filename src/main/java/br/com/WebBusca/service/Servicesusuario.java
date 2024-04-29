package br.com.WebBusca.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.WebBusca.dao.DAOUsuario;
import br.com.WebBusca.dominio.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Servicesusuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servicesusuario() {
        super();
    
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pagina = "<!DOCTYPE html>\r\n"
				+ "<html lang=\"pt-br\">\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>Usuários Cadastrados</title>\r\n"
				+ "\r\n"
				+ "    <style>\r\n"
				+ "        h1{\r\n"
				+ "            font-family: arial;\r\n"
				+ "            font-size: 30pt;\r\n"
				+ "            text-align: center;\r\n"
				+ "            color:black\r\n"
				+ "        }\r\n"
				+ "        #estrutura{\r\n"
				+ "            width: 60%;\r\n"
				+ "            margin-left: auto;\r\n"
				+ "            margin-right: auto;\r\n"
				+ "            display:flex;\r\n"
				+ "            flex-wrap:wrap;\r\n"
				+ "        }\r\n"
				+ "        .dp img{\r\n"
				+ "            width: 24px;\r\n"
				+ "            height: 24px;\r\n"
				+ "            margin:5px;\r\n"
				+ "        }\r\n"
				+ "        .dc img{\r\n"
				+ "            width: 16px;\r\n"
				+ "            height: 16px;\r\n"
				+ "            margin: 5px;\r\n"
				+ "        }\r\n"
				+ "        .us{\r\n"
				+ "            background-color: #e1f5fe;\r\n"
				+ "            padding:10px;\r\n"
				+ "            width: 250px;\r\n"
				+ "            border-radius: 5px;\r\n"
				+ "            transition: ease 0.5s;\r\n"
				+ "            margin:10px;\r\n"
				+ "        }\r\n"
				+ "        .us:hover{\r\n"
				+ "            background-color: #b3e5fc;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        h2{\r\n"
				+ "            font-family: arial;\r\n"
				+ "            font-size:12pt;\r\n"
				+ "        }\r\n"
				+ "        .dp{\r\n"
				+ "            display: flex;\r\n"
				+ "            align-items: center;\r\n"
				+ "            justify-content: space-between;\r\n"
				+ "        }\r\n"
				+ "        .dc div{\r\n"
				+ "            font-family: arial;\r\n"
				+ "            font-size: 10pt;\r\n"
				+ "            display: flex;\r\n"
				+ "            align-items: center;\r\n"
				+ "            width: 200px;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "    </style>\r\n"
				+ "\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "    <h1>Usuários Cadastrados</h1>\r\n"
				+ "    <div id=\"estrutura\">\r\n"
				+ "\r\n"
				;
		
		String conteudo = "";		
		
		
		DAOUsuario daous = new DAOUsuario();
		List<Usuario> lista = new ArrayList<Usuario>();
		lista = daous.listar();
		for( int i = 0 ; i < lista.size() ; i++) {
			conteudo+="        <div class=\"us\">\r\n"
					+ "            <div class=\"dp\">\r\n"
					+ "                <img src=\"user.png\" alt=\"\">\r\n"
					+ "                <h2>"+lista.get(i).getNomecompleto()+"</h2>\r\n"
					+ "                <a href=\"atualizar.html\">\r\n"
					+ "                    <img src=\"edit.png\" alt=\"\">\r\n"
					+ "                </a>\r\n"
					+ "            </div>\r\n"
					+ "\r\n"
					+ "            <div class=\"dc\">\r\n"
					+ "                <div><img src=\"phone.png\" alt=\"\"> "+lista.get(i).getTelefone()+"</div>\r\n"
					+ "                <div><img src=\"email.png\" alt=\"\">"+lista.get(i).getEmail()+"</div>\r\n"
					+ "            </div>\r\n"
					+ "\r\n"
					+ "        </div>\r\n"
					;
		}
		
		pagina+=conteudo;
		pagina+="\r\n"
				+ "\r\n"
				+ "    </div>\r\n"
				+ "</body>\r\n"
				+ "</html>";
		
		response.getWriter().write(pagina);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario = request.getParameter("txtusuario");
		String senha = request.getParameter("txtsenha");
		String email = request.getParameter("txtemail");
		String telefone = request.getParameter("txttelefone");
		String nome = request.getParameter("txtnome");
		String cpf = request.getParameter("txtcpf");
		
		//instância da classe Usuario que está no dominio
		Usuario us = new Usuario();
		DAOUsuario ds = new DAOUsuario();
		//Passagem dos dados enviados pelo usuário a camada dominio
		us.setNomeusuario(usuario);
		us.setSenha(senha);
		us.setEmail(email);
		us.setTelefone(telefone);
		us.setNomecompleto(nome);
		us.setCpf(cpf);
		
		//efetuar o cadastro e exibir a mensagem de retorno
		String msg = ds.cadastrar(us);
		response.getWriter().append(msg);
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}














