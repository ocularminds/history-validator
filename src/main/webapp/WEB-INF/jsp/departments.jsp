<%@ include file="partials/head.jsp"%>

    <body>
        <%@ include file="partials/spinner.jsp"%>
            <div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full" data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
                <header class="topbar" data-navbarbg="skin6">
                    <%@ include file="partials/navigation.jsp"%>
                </header>
                <aside class="left-sidebar" data-sidebarbg="skin6">
                    <%@ include file="partials/menu.jsp"%>
                </aside>
                <div class="page-wrapper">
                    <%@ include file="partials/breadcrumb.jsp"%>
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-12">
                                    <div class="card">
                                        <div class="card-body">
                                            <h4 class="card-title">Department Records</h4>
                                            <form:form method="post" action="${pageContext.request.contextPath}/departments" modelAttribute="department">
                                                <div class="form-body">
                                                    <div class="row">
                                                        <div class="col-md-6">
                                                            <div class="row">
                                                                <div class="col-md-6">
                                                                    Code
                                                                </div>
                                                                <div class="col-md-6">
                                                                    <div class="form-group">
                                                                        <form:input path="code" type="text" class="form-control" value="" />
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-6">
                                                                    Name
                                                                </div>
                                                                <div class="col-md-6">
                                                                    <div class="form-group">
                                                                        <form:input path="name" type="text" class="form-control" value="" />
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-actions">
                                                    <div class="text-right">
                                                        <button type="submit" class="btn btn-info">Save</button>
                                                        <button type="reset" class="btn btn-dark">Reset</button>
                                                    </div>
                                                </div>
                                            </form:form>
                                            <div class="table-responsive">
                                                <table id="zero_config" class="table table-striped table-bordered no-wrap">
                                                    <thead>
                                                        <tr>
                                                            <th>Code</th>
                                                            <th>Name</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${departments}" var="s">
                                                            <tr>
                                                                <td><a href="${pageContext.request.contextPath}/departments/${s.code}">${s.code}</a></td>
                                                                <td>${s.name}</td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                </div>
            </div>
            <%@ include file="partials/scripts.jsp"%>
                <script src="${pageContext.request.contextPath}/assets/extra-libs/datatables.net/js/jquery.dataTables.min.js"></script>
                <script src="${pageContext.request.contextPath}/dist/js/pages/datatable/datatable-basic.init.js"></script>
    </body>

    </html>