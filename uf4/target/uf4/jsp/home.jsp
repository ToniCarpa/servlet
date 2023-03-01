<%@ page import="model.Post" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title>Home</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
     <%
        String id = (String) request.getAttribute("id");
        ArrayList<Post> listPost = (ArrayList<Post>) request.getAttribute("listPost");
    %>
    </head>
    <body>
        <h2>Home</h2>
	        <%for (Post p : listPost) { %>
		        <b>Posts:
		        <p> Usuario: </p>
		        <%= p.getUsuario()%>

   		        <p> Titulo: </p>
		        <%=p.getTitulo()%><br/>

		        <p> Url: </p>
                <%= p.getUrl()%>

		        <p> Mensaje: </p>
                <%= p.getMessage()%>

                <p> Fecha: </p>
                <%= p.getDate()%>
	        <%}%>
    </body>
</html>

<a href="mailto:<%=p.getUsuario().getEmail()%>"