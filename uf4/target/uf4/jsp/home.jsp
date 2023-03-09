<%@ page import="model.Post" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content="Home Usuario"/>
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
        ArrayList<Post> postArrayList = (ArrayList<Post>) request.getAttribute("listPostUsuario");
    %>

</head>
<section class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card bg-dark text-white" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">

                        <div class="mb-md-5 mt-md-4 pb-5">
                            <h2 class="fw-bold mb-2 text-uppercase">Home</h2>

                        <%for (Post p : postArrayList) { %>
                            <form action="home.do" method="post">
                                <input hidden class="form-control" type="text" name="id" value="<%=p.getId()%>">

                                <div class="form-outline form-white mb-4">
                                    <label class="form-label" name="usuario"> Usuario: <%=p.getUsuario()%> </label>

                                    <label class="form-label" name="titulo"> Titulo: <%=p.getTitulo()%> </label>

                                    <label class="form-label" name="url"> Url: <%=p.getUrl()%> </label>

                                    <label type="textarea" name="msg" class="form-control form-control-lg"> Mensaje: <%=p.getMessage()%> </label>

                                    <label class="form-label" name="date"> Fecha: <%=p.getDate()%> </label>
                                </div>
                            </form>
                            <%}%>

                        </div>

                        <div>
                            <form action="PostUserServLet.do" method="post">
                                <button class="btn btn-outline-light btn-lg px-5" type="submit">Mis posts</button>
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

