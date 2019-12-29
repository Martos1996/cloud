
<%@page import="com.comillas.profile.Profile_Bean"%>
<%@page import="com.comillas.profile.Profile_model"%>
<%@page import="com.comillas.profile.Post"%><%@page import="com.comillas.profile.Tono"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<title>Tonos en comentarios</title>

</head>
<body>
<%
ArrayList tonos = (ArrayList) request.getAttribute("tonos");
%>
<div align="center">
<h1>Resultados</h1>
<div style="text-align:center;">
<br>
<br>
<br>

<table width="30%" border="1" style="margin: 0 auto; cellspacing=20;">
<TR>
		<TH >Tono</TH>
	    	<TH >Score</TH>
</TR>
<%
Iterator it = tonos.iterator();

while(it.hasNext())
{
	Tono tono = (Tono) it.next();
%>
	 <tr>
	    <td style="background-color:white;"><%=tono.getNombre() %></td>
	    <td style="background-color:green;"><%=tono.getScore() %></td>
	  </tr>
<%
}
%>
 </table>
 <br>
 <a href="index.jsp">Volver al inicio</a>
 </div>
 <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</div>
</body>
</html>