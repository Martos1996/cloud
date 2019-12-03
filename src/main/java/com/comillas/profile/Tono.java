package com.comillas.profile;

public class Tono {
	float score;
	String nombre;
	
	public Tono(String nombre,float score)
	{
		this.nombre=nombre;
		this.score=score;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
