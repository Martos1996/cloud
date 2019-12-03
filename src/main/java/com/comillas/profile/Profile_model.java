package com.comillas.profile;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ibm.cloud.sdk.core.service.exception.BadRequestException;

import asr.proyectoFinal.services.Analizador;
import asr.proyectoFinal.services.Traductor;
import asr.proyectoFinal.services.Visual;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

public class Profile_model {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
			String toAnalizar = new String("https://scontent.xx.fbcdn.net/v/t1.0-0/s130x130/78562311_115556683250272_6766504650230202368_n.jpg?_nc_cat=107&_nc_ohc=unczvfIInx8AQlcc_ClCU0nx_fIBdhfFKbIR5ncL_dhu3GrzAyLMyNsCQ&_nc_ht=scontent.xx&oh=e6a9288abae0c419084a3898858ddf70&oe=5E405CFD");
			Profile_model prof = new Profile_model();
			JSONObject resul = new JSONObject(Visual.getScores(toAnalizar).toString());
			System.out.println(resul);
			ArrayList a = prof.getScoresFoto(resul);
			System.out.println(a);
			
			
	}

	public static ArrayList descarga_fotos(String access_token) throws Exception {

		String url = "https://graph.facebook.com/v5.0/me?fields=albums.fields(id%2Cname%2Ccover_photo%2Cphotos.fields(name%2Cpicture%2Csource))&access_token="
				+ access_token;
		URL obj = new URL(url);

		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// URL de las fotos en los albums

		JSONObject respuesta = new JSONObject(response.toString());
		JSONObject album = respuesta.getJSONObject("albums");
		org.json.JSONArray data = album.getJSONArray("data");
		ArrayList a = new ArrayList();
		
		for (int i = 0; i < data.length(); ++i) {
			JSONObject albumReal = data.getJSONObject(i);
			JSONObject fotos = albumReal.getJSONObject("photos");
			org.json.JSONArray dataFotos = fotos.getJSONArray("data");
			for (int j = 0; j < dataFotos.length(); ++j) {
				String urlFoto = dataFotos.getJSONObject(j).getString("picture");
				a.add(urlFoto);
				// LLamar aqui al script para descargar las fotos
			}
		}
		return a;
	}

	public ArrayList<Post> postCom(String access_token) throws Exception {

		String url = "https://graph.facebook.com/v5.0/me/feed?fields=type%2Cfull_picture%2Cmessage%2Ccomments&access_token="
				+ access_token;

		ArrayList<Post> posts = new ArrayList<Post>();
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		String[] comentarios = new String[10000];

		// optional default is GET
		con.setRequestMethod("GET");
		// add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// Obtenemos url de foto y los comentarios
		JSONObject respuesta = new JSONObject(response.toString());
		org.json.JSONArray data = respuesta.getJSONArray("data");

		for (int i = 0; i < data.length(); ++i) {
			JSONObject foto = data.getJSONObject(i);
			String tipo = foto.getString("type");
			if (tipo.equals("photo")) {

				String urlFoto = foto.getString("full_picture");
				try {
					JSONObject coment = foto.getJSONObject("comments");
					org.json.JSONArray dataComents = coment.getJSONArray("data");
					for (int j = 0; j < dataComents.length(); ++j) {
						JSONObject comentObject = dataComents.getJSONObject(j);
						String comentario = comentObject.getString("message");
						comentarios[j] = comentario;
					}
					Post post = new Post(urlFoto, comentarios);
					posts.add(post);
				} catch (JSONException e) {
				}
			}

		}
		return posts;
	}

	public String[] getComentarios(String access_token, String url1) throws Exception {

		String url = "https://graph.facebook.com/v5.0/me/feed?fields=type%2Cfull_picture%2Cmessage%2Ccomments&access_token="
				+ access_token;

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");
		// add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// Obtenemos url de foto y los comentarios
		JSONObject respuesta = new JSONObject(response.toString());
		org.json.JSONArray data = respuesta.getJSONArray("data");
		String[] comentarios = new String[data.length()];
		for (int i = 0; i < data.length(); ++i) {
			JSONObject foto = data.getJSONObject(i);
			String tipo = foto.getString("type");
			if (tipo.equals("photo")) {

				String urlFoto = foto.getString("full_picture");
				if (urlFoto.equals(url1)) {
					try {
						JSONObject coment = foto.getJSONObject("comments");
						org.json.JSONArray dataComents = coment.getJSONArray("data");
						for (int j = 0; j < dataComents.length(); ++j) {
							JSONObject comentObject = dataComents.getJSONObject(j);
							String comentario = comentObject.getString("message");
							comentarios[j] = comentario;
						}
					} catch (JSONException e) {
					}
				}

			}

		}
		return comentarios;
	}

	public static String getURLFoto(JSONObject a) {
		String url = new String();
		org.json.JSONArray b = a.getJSONArray("images");
		for (int i = 0; i < b.length(); i++) {
			JSONObject c = b.getJSONObject(i);
			url = c.getString("sourceUrl");
		}
		return url;
	}

	public static ArrayList getScoresFoto(JSONObject a) {
		ArrayList ar = new ArrayList();
		String url = new String();
		org.json.JSONArray b = a.getJSONArray("images");
		for (int i = 0; i < b.length(); i++) {

			JSONObject c = b.getJSONObject(i);
			org.json.JSONArray d = c.getJSONArray("classifiers");
			for (int j = 0; j < d.length(); j++) {
	
				JSONObject e = d.getJSONObject(j);
				org.json.JSONArray f = e.getJSONArray("classes");
				for (int k = 0; k < f.length(); k++) {
		
					JSONObject object = f.getJSONObject(k);
					Foto fotoFinal = new Foto(object.getString("XClass"), object.getFloat("score"));
					ar.add(fotoFinal);
				}
			}
		}
		return ar;
	}
}
