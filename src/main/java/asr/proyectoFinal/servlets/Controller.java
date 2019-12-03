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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.comillas.profile.*;
import com.ibm.cloud.sdk.core.service.exception.BadRequestException;
import com.ibm.json.java.JSONArray;

import asr.proyectoFinal.dao.CloudantPalabraStore;
import asr.proyectoFinal.services.Analizador;
import asr.proyectoFinal.services.Traductor;
import asr.proyectoFinal.services.Visual;
import asr.proyectoFinal.dominio.Palabra;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = {"/fotos","/comentarios" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		
		PrintWriter out = response.getWriter();
		out.println("<html><head><meta charset=\"UTF-8\"></head><body>");
		
		
		
		System.out.println(request.getServletPath());
		switch(request.getServletPath())
		{
			
		case "/fotos":
	
		
			try {

				String url2 = request.getParameter("Env");
				Profile_model gestor = new Profile_model();
				Profile_model a = new Profile_model();
	
					
				JSONObject resul = new JSONObject(Visual.getScores(url2).toString());
				ArrayList score = a.getScoresFoto(resul);
				String url = a.getURLFoto(resul);
				request.setAttribute("url", url);
				request.setAttribute("Scores", score); //categorylist is an arraylist      contains object of class category  
				request.getRequestDispatcher("displayScoreFoto.jsp").forward(request, response);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



			break;	
			case "/comentarios":
				//out.println(URL);
				//out.println(token);
				String URL = request.getParameter("Env");
				String token = request.getParameter("token");
				Profile_model gestor = new Profile_model();
				try {
					String[] comentarios = gestor.getComentarios(token,URL);
					String toAnalizar = new String();
					//Traducimos los comentarios para analisis
					for(int i=0;i<comentarios.length;i++)
					{
						try
						{
							
						JSONObject a = Traductor.translate(comentarios[i], "es", "en", false);
						try
						{
							org.json.JSONArray ar = a.getJSONArray("translations");
							for(int j =0;j<ar.length();j++)
							{
								System.out.println(j);
									JSONObject b = ar.getJSONObject(j);
								
										toAnalizar = toAnalizar + "" + b.getString("translation") + ".\n";

							}
						}catch(BadRequestException e)
						{
							
						}
					}catch(java.lang.IllegalArgumentException e)
					{}
						
					}
					/*
					 * ArrayList tonos = Analizador.getTones(toAnalizar); Iterator it =
					 * tonos.iterator(); while(it.hasNext()) { Tono tono = (Tono) it.next();
					 * System.out.println(tono.getScore()); System.out.println(tono.getNombre()); }
					 */
					ArrayList tonos = Analizador.getTones(toAnalizar);
					request.setAttribute("tonos", tonos); //categorylist is an arraylist      contains object of class category  
					request.getRequestDispatcher("displayTones.jsp").forward(request, response);

				}catch(Exception e){
				
					System.err.print(e);
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
