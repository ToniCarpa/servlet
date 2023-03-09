<!DOCTYPE html>
<html lang="en">
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



</head>
<section class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card bg-dark text-white" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">

                        <div class="mb-md-5 mt-md-4 pb-5">

                            <h2 class="fw-bold mb-2 text-uppercase">Login</h2>
                            <p class="text-white-50 mb-5">Introduce tu email & password!</p>

                            <form action="index.do" method="post">
                                <div class="form-outline form-white mb-4">
                                    <input type="text" name="mail" class="form-control form-control-lg"/>
                                    <label class="form-label">Email</label>
                                </div>

                                <div class="form-outline form-white mb-4">
                                    <input type="text" name="pass" class="form-control form-control-lg"/>
                                    <label class="form-label">Password</label>
                                </div>
                            </form>


                            <form action="home.do" method="post">
                                <button class="btn btn-outline-light btn-lg px-5" type="submit">Login</button>
                            </form>

                            <div class="d-flex justify-content-center text-center mt-4 pt-1">
                                <a href="http://www.facebook.com" class="text-white"><i class="fab fa-facebook-f fa-lg"></i></a>
                                <a href="http://www.twitter.com" class="text-white"><i class="fab fa-twitter fa-lg mx-4 px-2"></i></a>
                                <a href="http://www.google.com" class="text-white"><i class="fab fa-google fa-lg"></i></a>
                            </div>
                        </div>

                        <div>
                            <form action="register.do" method="get">
                                <button class="btn btn-outline-light btn-lg px-5" type="submit">Registrate!</button>
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
