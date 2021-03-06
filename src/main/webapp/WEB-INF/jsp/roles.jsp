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
                                <h4 class="card-title">Statements Request</h4>                          
                                <form:form method="post" action="${pageContext.request.contextPath}/statements/search"  modelAttribute="criteria">
                                    <div class="form-body">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        Date From
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <form:input path="from" type="date" class="form-control" value="2010-01-01" placeholder="Date To"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        Date To
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <form:input path="to" type="date" class="form-control" value="2020-09-10" placeholder="Date To"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div> 
                                            <div class="col-md-6">
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        Fund ID
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <form:input path="fund" type="text" class="form-control" readonly="true"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        RSA PIN
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <form:input path="pin" type="text" class="form-control" placeholder="RSA PIN"/>
                                                        </div>
                                                    </div>
                                                </div>                                                    
                                            </div> 
                                        </div>
                                    </div>
                                    <div class="form-actions">
                                        <div class="text-right">
                                            <button type="submit" class="btn btn-info">Submit</button>
                                            <button type="reset" class="btn btn-dark">Reset</button>
                                        </div>
                                    </div>
                                </form:form>
                                <div class="table-responsive">
                                    <table id="zero_config" class="table table-striped table-bordered no-wrap">
                                        <thead>
                                            <tr>
                                                <th>PIN</th>
                                                <th>NAME</th>
                                                <th>EMPLOYER</th>
                                                <th>FUND CODE</th>
                                                <th>PRICE</th>
                                                <th>UNITS</th>
                                                <th>BALANCE</th>
                                                <th>EARNING</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${statements}" var="s">
                                                <tr>
                                                    <td><a href="${pageContext.request.contextPath}/statements/requests/${s.pin}">${s.pin}</a></td>
                                                    <td>${s.firstName} ${s.surname}</td>
                                                    <td>${s.employer}</td>
                                                    <td>${s.code}</td>
                                                    <td>${s.price}</td>
                                                    <td>${s.units}</td>
                                                    <td>${s.balance}</td>
                                                    <td>${s.earning}</td>
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