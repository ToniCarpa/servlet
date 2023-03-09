<%@ page import="model.Usuario" %><%--
  Created by IntelliJ IDEA.
  User: a201691ac
  Date: 3/7/23
  Time: 5:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content="Blog M06"/>
    <meta name="author" content="tcp.id"/>
    <title>Blog TCP M06 UF4</title>
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico"/>
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet"
          type="text/css"/>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
          rel="stylesheet" type="text/css"/>
    <link href="css/styles.css" rel="stylesheet"/>

    <%
       Usuario u = (Usuario) request.getAttributeNames();
    %>

</head>
<section class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card bg-dark text-white" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">

                        <div class="mb-md-5 mt-md-4 pb-5">

                            <h2 class="fw-bold mb-2 text-uppercase">Registro</h2>

                            <form action="RegistroServLet.do" method="get">
                                <div class="form-outline form-white mb-4">
                                    <input type="text" name="name" class="form-control form-control-lg"/>
                                    <label class="form-label">Nombre</label>
                                </div>

                                <div class="form-outline form-white mb-4">
                                    <input type="text" name="pass" class="form-control form-control-lg"/>
                                    <label class="form-label">Password</label>
                                </div>

                                <div class="form-outline form-white mb-4">
                                    <input type="text" name="mail" class="form-control form-control-lg"/>
                                    <label class="form-label">Email</label>
                                </div>

                                <div class="form-outline form-white mb-4">
                                    <input type="text" name="pass" class="form-control form-control-lg"/>
                                    <label class="form-label">Password</label>
                                </div>

                                <div class="form-outline form-white mb-4">
                                    <input type="text" name="link" class="form-control form-control-lg"/>
                                    <label class="form-label">Linkdin</label>
                                </div>

                                <div class="form-outline form-white mb-4">
                                    <input type="text" name="git" class="form-control form-control-lg"/>
                                    <label class="form-label">Github</label>
                                </div>
                            </form>


                            <form action="HomeServLet.do" method="post">
                                <button class="btn btn-outline-light btn-lg px-5" type="submit">Registrarse</button>
                            </form>

                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
