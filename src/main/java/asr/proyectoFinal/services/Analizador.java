package asr.proyectoFinal.services;
import java.io.PrintWriter;
import java.util.*;

import javax.naming.AuthenticationException;

import org.json.JSONObject;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.json.java.JSON;
import com.ibm.watson.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.tone_analyzer.v3.model.*;



import com.comillas.profile.Tono;
import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;

public class Analizador {
	public static void main(String[] args) {
		String envio = new String("We can do it. I hate the beach");
		ArrayList a = getTones(envio);
		System.out.println(a.size());
		Iterator it = a.iterator();
		while(it.hasNext())
		{
			Tono as = (Tono) it.next();
			System.out.println(as.getScore());
		}
	}
	public static ArrayList getTones(String analisis)
	{
		
		ArrayList todo = new ArrayList();
		Authenticator authenticator = new IamAuthenticator("z31SvRKMQ4IpbtdbyU4ZDSWyJvkvuJ2GqUsBwhQaKsoh");
		ToneAnalyzer service = new ToneAnalyzer("2017-09-21", authenticator);
		service.setServiceUrl("https://gateway-lon.watsonplatform.net/tone-analyzer/api");
		
		ToneOptions toneOptions = new ToneOptions.Builder()
				  .text(analisis)
				  .build();

		ToneAnalysis tone = service.tone(toneOptions).execute().getResult();
		JSONObject all = new JSONObject(tone);
		
		JSONObject tones = all.getJSONObject("documentTone");
		org.json.JSONArray getTones = tones.getJSONArray("tones");
		 for (int j = 0; j < getTones.length(); ++j) {
			 JSONObject numero =  getTones.getJSONObject(j);
			 Tono tono = new Tono(numero.getString("toneName"), numero.getFloat("score"));
			 todo.add(tono);
		 }
		 return todo;
	}
}
