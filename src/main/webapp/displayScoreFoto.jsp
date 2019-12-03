
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
<title>Foto con resultados</title>

</head>
<body>
<%
Profile_model a = new Profile_model();
%>
<h1>Resultados</h1>

<div style="text-align:center;">
<%

ArrayList score = (ArrayList)request.getAttribute("Scores");

%>



<div align="center"><img src=<%=request.getAttribute("url") %> ></div>
<div style="text-align:center;">
<table border="1" style="margin: 0 auto; cellspacing=20;">
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
 <a href="index.jsp">Volver al inicio</a>
 </div>
</body>
</html>