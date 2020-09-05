<%@ include file="partials/head.jsp"%>
<body>
        <%@ include file="partials/spinner.jsp"%>
        <div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full"
             data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
            <header class="topbar" data-navbarbg="skin6">
                <%@ include file="partials/navigation.jsp"%>
            </header>
            <aside class="left-sidebar" data-sidebarbg="skin6">
               <%@ include file="partials/menu.jsp"%>
            </aside>
            <div class="page-wrapper">
                <%@ include file="partials/breadcrumb.jsp"%>
                <div class="container-fluid">
                    <div class="card-group">
                        <div class="card border-right">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@ include file="partials/scripts.jsp"%>
    </body>
</html>