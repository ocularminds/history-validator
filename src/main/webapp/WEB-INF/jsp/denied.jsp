<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html dir="ltr">

    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <!-- Tell the browser to be responsive to screen width -->
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/assets/images/favicon.png" />
        <title>Pension Statements - Error</title>
        <link href="${pageContext.request.contextPath}/dist/css/style.min.css" rel="stylesheet" />
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
                    <div class="col-lg-12 col-md-12 bg-white">
                        <div class="p-3">
                            <h2 class="mt-3 text-center">Access Denied!</h2>
                            <p class="text-center">
                                <img src="${pageContext.request.contextPath}/assets/images/n_1.png" alt="" logo />
                            </p>
                            <p>You are not authorized to access this recource<br><br>
                                <a href="${pageContext.request.contextPath}/dashboard">Back to dashboard</a>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/assets/libs/jquery/dist/jquery.min.js "></script>
        <script src="${pageContext.request.contextPath}/assets/libs/popper.js/dist/umd/popper.min.js "></script>
        <script src="${pageContext.request.contextPath}/assets/libs/bootstrap/dist/js/bootstrap.min.js "></script>
        <script src="${pageContext.request.contextPath}/assets/extra-libs/datatables.net/js/jquery.dataTables.min.js"></script>
        <script src="${pageContext.request.contextPath}/dist/js/pages/datatable/datatable-basic.init.js"></script>
        <script>
            $(".preloader ").fadeOut();
        </script>
    </body>

    </html>