
<%@page import="com.comillas.profile.Profile_Bean"%>
<%@page import="com.comillas.profile.Profile_model"%>
<%@page import="com.comillas.profile.Post"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Opciones de usuario</title>

</head>
<body>
<%
String access_token=(String)request.getParameter("token");
%>
<h1>Pinche en la foto para analizar comentarios</h1>

	<% 
	ArrayList posts = new ArrayList();
	Profile_model a = new Profile_model();
	posts = a.postCom(access_token);
	Iterator it = posts.iterator();
	while(it.hasNext())
	{
		Post post = (Post) it.next();
		String URL = post.getUrl();
		%>
		<form action="comentarios">
		<img src=<%=URL %>>
		<input type="hidden" name=Env value="<%=URL%>">
		<input type="hidden" name="token" value=<%=access_token%>> 
		<input type="submit"  onclick="this.name='enviar'" value="Enviar">
		</form>
		<% 
	}
	%>
</body>
</html>