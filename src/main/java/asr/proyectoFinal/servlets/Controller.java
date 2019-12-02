package asr.proyectoFinal.servlets;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comillas.profile.*;

import asr.proyectoFinal.dao.CloudantPalabraStore;
import asr.proyectoFinal.services.Analizador;
import asr.proyectoFinal.services.Traductor;
import asr.proyectoFinal.dominio.Palabra;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = {"/listar", "/comentarios", "/fotos"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int anger,fear,joy,sadness = 0;
		
		PrintWriter out = response.getWriter();
		out.println("<html><head><meta charset=\"UTF-8\"></head><body>");
		
		CloudantPalabraStore store = new CloudantPalabraStore();
		System.out.println(request.getServletPath());
		switch(request.getServletPath())
		{
			case "/listar":
				if(store.getDB() == null)
					  out.println("No hay DB");
				else
					out.println("Palabras en la BD Cloudant:<br />" + store.getAll());
				break;
				
			case "/comentarios":
				String URL = request.getParameter("Env");
				String token = request.getParameter("token");
				//out.println(URL);
				//out.println(token);
				Profile_model gestor = new Profile_model();
				try {
					String[] comentarios = gestor.getComentarios(token,URL);
					String toAnalizar = new String("");
					//Traducimos los comentarios para analisis
					for(int i=0;i<comentarios.length;i++)
					{
						toAnalizar = toAnalizar + Traductor.translate(comentarios[i], "es", "en", false) + ".\n";	
					}
					ArrayList tonos = Analizador.getTones(toAnalizar);
					request.setAttribute("tonos", tonos); //categorylist is an arraylist      contains object of class category  
					getServletConfig().getServletContext().getRequestDispatcher("/displayTones.jsp").forward(request,response);
					//Analizar estos comentarios
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}



				break;
		}
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
