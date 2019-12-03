package com.comillas.profile;

public class Foto {
		String nombre;
		float score;
		
		public Foto(String nombre,float score)
		{
			this.nombre=nombre;
			this.score=score;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public float getScore() {
			return score;
		}
		public void setScore(float score) {
			this.score = score;
		}
}
