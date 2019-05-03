package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.crypto.hash.Sha256Hash;

import dao.ProfessorDAO;
import dao.ProfessorDAOImplementation;
import dao.TFGDAO;
import dao.TFGDAOImplementation;
import model.Professor;
import model.TFG;


@SuppressWarnings("serial")
@WebServlet("/CreateTFGServlet")
public class CreateTFGServlet extends HttpServlet{
	@Override
	protected void doPost (HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String name = req.getParameter( "name" );
		String email = req.getParameter( "email" );
		String password = req.getParameter( "password" );
		String title = req.getParameter( "title" );
		String advisor = req.getParameter( "advisor" );
		
		password = new Sha256Hash( password ).toString();
		
		ProfessorDAO pdao = ProfessorDAOImplementation.getInstance();
		Professor professor = pdao.read( advisor );
		
		TFG tfg = new TFG();
		tfg.setName( name );
		tfg.setEmail( email );
		tfg.setPassword( password );
		tfg.setTitle( title );
		tfg.setAdvisor( professor );
		// El primer estado del TFG es recién creado
		tfg.setStatus( 1 );
		
		TFGDAO tdao = TFGDAOImplementation.getInstance();
		tdao.create( tfg );
		
		resp.sendRedirect( req.getContextPath() + "/LoginServlet" );
	}
}
