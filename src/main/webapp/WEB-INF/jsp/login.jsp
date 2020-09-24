<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <!DOCTYPE html>
    <html dir="ltr">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- Tell the browser to be responsive to screen width -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/assets/images/favicon.png">
        <title>Transaction History Validator - Login</title>
        <link href="${pageContext.request.contextPath}/dist/css/style.min.css" rel="stylesheet">
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    </head>

    <body>
        <div class="main-wrapper">
            <div class="preloader">
                <div class="lds-ripple">
                    <div class="lds-pos"></div>
                    <div class="lds-pos"></div>
                </div>
            </div>
            <div class="auth-wrapper d-flex no-block justify-content-center align-items-center position-relative">
                <div class="auth-box row">
                    <div class="col-lg-3 col-md-3">&nbsp;</div>
                    <div class="col-lg-6 col-md-8 bg-white">
                        <div class="p-3">
                            <h2 class="mt-3 text-center">Sign In</h2>
                            <p class="text-center"><img src="${pageContext.request.contextPath}/assets/images/n_1.png" alt="" logo/></p>
                            <p class="text-center">Enter your username and password to continue.</p>
                            <p style="color: red">${errorMessage}</p>
                            <form:form class="mt-4" method="post" action="${pageContext.request.contextPath}/login" modelAttribute="credentials">
                                <div class="row">
                                    <div class="col-lg-12">
                                        <div class="form-group">
                                            <label class="text-dark" for="uname">Username</label>
                                            <form:input class="form-control input-lg" id="uname" type="text" path="username" placeholder="enter your username" />
                                        </div>
                                    </div>
                                    <div class="col-lg-12">
                                        <div class="form-group">
                                            <label class="text-dark" for="pwd">Password</label>
                                            <form:input class="form-control input-lg" id="pwd" type="password" path="password" value="pass" readonly/>
                                        </div>
                                    </div>
                                    <div class="col-lg-12 text-center">
                                        <button type="submit" class="btn btn-block btn-dark">Sign In</button>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/assets/libs/jquery/dist/jquery.min.js "></script>
        <script src="${pageContext.request.contextPath}/assets/libs/popper.js/dist/umd/popper.min.js "></script>
        <script src="${pageContext.request.contextPath}/assets/libs/bootstrap/dist/js/bootstrap.min.js "></script>
        <script>
            $(".preloader ").fadeOut();
        </script>
    </body>

    </html>