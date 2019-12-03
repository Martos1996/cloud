package asr.proyectoFinal.services;

import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.language_translator.v3.LanguageTranslator;
import com.ibm.watson.language_translator.v3.model.TranslateOptions;
import com.ibm.watson.language_translator.v3.model.TranslationResult;

public class Traductor
{
	public static JSONObject translate(String palabra, String sourceModel, String destModel, boolean conversational)
	{
		String model;
		if(sourceModel.equals("en") || sourceModel.equals("es") || destModel.equals("en") || destModel.equals("es"))
		{
			model=sourceModel+"-"+destModel;
			if(conversational) 
				model+="-conversational";
		}
		else
			model="en-es";

		Authenticator authenticator = new IamAuthenticator("ua0iuteg6pjVnYjlNM6otDc5sN1q1o8c6iMeGCakpw_F");
		LanguageTranslator languageTranslator = new LanguageTranslator("2018-05-01", authenticator);

		languageTranslator.setServiceUrl("https://gateway-lon.watsonplatform.net/language-translator/api");
		
		TranslateOptions translateOptions = new TranslateOptions.Builder()
		  .addText(palabra)
		  .modelId(model)
		  .build();
		
		TranslationResult translationResult = languageTranslator.translate(translateOptions).execute().getResult();

	
		
		String traduccionJSON = translationResult.toString();
		JSONObject rootObj = new JSONObject(traduccionJSON);
		return rootObj;	
			
		}
}