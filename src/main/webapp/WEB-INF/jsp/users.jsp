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
                                            <h4 class="card-title">User Records</h4>
                                            <form:form method="post" action="${pageContext.request.contextPath}/users" modelAttribute="user">
                                                <div class="form-body">
                                                    <div class="row">
                                                        <div class="col-md-6">
                                                            <div class="row">
                                                                <div class="col-md-6">
                                                                    Id
                                                                </div>
                                                                <div class="col-md-6">
                                                                    <div class="form-group">
                                                                        <form:input path="id" type="text" class="form-control" readonly="true" />
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-6">
                                                                    Role
                                                                </div>
                                                                <div class="col-md-6">
                                                                    <div class="form-group">
                                                                        <form:select path="role">
                                                                            <c:forEach items="${roles}" var="r">
                                                                                <option value="${r}">${r}</option>
                                                                            </c:forEach>
                                                                        </form:select>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-6">
                                                                    Username
                                                                </div>
                                                                <div class="col-md-6">
                                                                    <div class="form-group">
                                                                        <form:input path="login" type="text" class="form-control" value="" />
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-6">
                                                                    Department
                                                                </div>
                                                                <div class="col-md-6">
                                                                    <div class="form-group">
                                                                        <form:select path="department">
                                                                            <c:forEach items="${departments}" var="d">
                                                                                <option value="${d.code}">${d.name}</option>
                                                                            </c:forEach>
                                                                        </form:select>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <div class="row">
                                                                <div class="col-md-6">
                                                                    Surname
                                                                </div>
                                                                <div class="col-md-6">
                                                                    <div class="form-group">
                                                                        <form:input path="surname" type="text" class="form-control" />
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-6">
                                                                    First Name
                                                                </div>
                                                                <div class="col-md-6">
                                                                    <div class="form-group">
                                                                        <form:input path="name" type="text" class="form-control" />
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
                                                            <th>USERNAME</th>
                                                            <th>SURNAME</th>
                                                            <th>FIRSTNAME</th>
                                                            <th>ROLE</th>
                                                            <th>DEPARTMENT</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${users}" var="s">
                                                            <tr>
                                                                <td><a href="${pageContext.request.contextPath}/users/${s.id}">${s.login}</a></td>
                                                                <td>${s.surname}</td>
                                                                <td>${s.name}</td>
                                                                <td>${s.role}</td>
                                                                <td>${s.department}</td>
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