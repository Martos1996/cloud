package asr.proyectoFinal.services;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONObject;

import com.comillas.profile.Tono;
import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;

import com.ibm.watson.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.visual_recognition.v3.model.ClassifyOptions;
import java.io.*;






public class Visual {
	public static void main(String[] args) {
		String url = new String("https%3A%2F%2Fscontent.xx.fbcdn.net%2Fv%2Ft1.0-0%2Fs130x130%2F78562311_115556683250272_6766504650230202368_n.jpg%3F_nc_cat%3D107%26_nc_ohc%3DunczvfIInx8AQktzSiRSqcOXyM1VJ6CDExq4FSKQS-hmtALCpzWLilzOw%26_nc_ht%3Dscontent.xx%26oh%3D2aec14e5956be5013558af8f1fe5b563%26oe%3D5E405CFD");
		Visual visual = new Visual();
		System.out.println(visual.getScores(url));
	}
	public static JSONObject getScores(String imageUrl)
	{

		IamAuthenticator authenticator = new IamAuthenticator("B5pTSFmuJOCmvd59fAeyGKzH7whONca8w_hNCovtcgbP");
		VisualRecognition visualRecognition = new VisualRecognition("2018-03-19", authenticator);
		visualRecognition.setServiceUrl("https://gateway.watsonplatform.net/visual-recognition/api");
		


			ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
					  .url(imageUrl)
					  .classifierIds(Arrays.asList("paisaje_338342263"))
					  .build();
					ClassifiedImages result = visualRecognition.classify(classifyOptions).execute().getResult();
					JSONObject resultado = new JSONObject(result);
					return resultado;
		
	}
}
