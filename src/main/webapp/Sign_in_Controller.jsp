
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
String access_token=(String)request.getParameter("access_token");
%>
<h1>Gracias por loguearte con Facebook</h1>

<form action="desplegaFotosAlbum.jsp">
	<input type="hidden" name="token" value=<%=access_token%>> 
	<button  type="submit" name="submit" >Evaluacion de fotos</button> 

</form> 
<form action="DesplegaFotos.jsp">
	<input type="hidden" name="token" value=<%=access_token%>> 
	<button  type=submit name="submit">Evaluacion de Comentarios</button> 
</form> 
</body>
</html>