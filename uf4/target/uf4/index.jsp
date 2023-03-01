<html>
<head>
    <% String id = (String) request.getAttribute("id"); %>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login</title>
</head>
<body>
<h1>Iniciar sesion</h1>
<form action="Login" method="post">
    <p> Introduce tu Email:
        <input type="text" name="mail"></p>
    </br>
    <p> Introduce tu Contraseña:
        <input type="text" name="pass"></p>
    </br>
    <p> <input type="submit" value="Entrar"></p>
</form>
</body>
</html>
