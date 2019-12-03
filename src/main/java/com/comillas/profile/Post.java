package com.comillas.profile;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import javassist.bytecode.Descriptor.Iterator;

public class Post {
	private String url;
	private String[] comentarios;
	
	
	public Post(String url,String[] comentarios)
	{
		this.url = url;
		this.comentarios = comentarios;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String[] getComentarios() {
		return comentarios;
	}
	public void setComentarios(String[] comentarios) {
		this.comentarios = comentarios;	
	}
}