
<%@page import="com.comillas.profile.Profile_Bean"%>
<%@page import="com.comillas.profile.Profile_model"%>
<%@page import="org.json.*"%>
<%@page import="com.comillas.profile.Post"%><%@page import="com.comillas.profile.Tono"%>
<%@page import="com.comillas.profile.Foto"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<title style="text-align: center">Foto con resultados</title>

</head>
<body>
<%
Profile_model a = new Profile_model();
%>
<div align="center">
<h1>Resultados</h1>


<%

ArrayList score = (ArrayList)request.getAttribute("Scores");

%> 



<div align="center"><img src=<%=request.getAttribute("url") %> ></div>
<div style="text-align:center;">
<br>
<br>
<br>

<table border="1" width="30%" style="margin: 0 auto; cellspacing=20;">
<TR>
		<TH >Clase</TH>
	    	<TH >Score</TH>
</TR>
<%
Iterator it = score.iterator();

while(it.hasNext())
{
	Foto foto = (Foto) it.next();
%>
	 <tr>
	    <td style="background-color:white; "><%=foto.getNombre() %></td>
	    <td style="background-color:green;"><%=foto.getScore() %></td>
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