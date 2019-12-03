
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
<title>Tonos en comentarios</title>

</head>
<body>
<%
ArrayList tonos = (ArrayList) request.getAttribute("tonos");
%>
<h1>Resultados</h1>
<div style="text-align:center;">
<table border="1" style="margin: 0 auto; cellspacing=20;">
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
	    <td style="background-color:white; "><%=tono.getNombre() %></td>
	    <td style="background-color:green;"><%=tono.getScore() %></td>
	  </tr>
<%
}
%>
 </table>
 <a href="index.jsp">Volver al inicio</a>
 </div>
</body>
</html>