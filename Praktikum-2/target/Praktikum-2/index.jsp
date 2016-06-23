<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"

 "http://www.w3.org/TR/html4/loose.dtd">

<%@page language="Java"%>

<%@page import="java.text.*,java.util.*"%>

<%!

 SimpleDateFormat d = null; 

%>

<html>

 <head>

 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

 <title>JSP Page</title>

 </head>

 <body>

    <%

     d = new SimpleDateFormat("EEEE, dd MMMM yyyy"); 

     out.println( d.format(new Date()) ); 

    %>

 </body>

</html>