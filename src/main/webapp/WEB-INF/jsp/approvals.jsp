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
                                            <h4 class="card-title">Statements Validation Approval</h4>
                                            <div class="table-responsive">
                                                <table id="zero_config" class="table table-striped table-bordered no-wrap">
                                                    <thead>
                                                        <tr>
                                                            <th>SURNAME</th>
                                                            <th>FIRST NAME</th>
                                                            <th>MIDDLE NAME</th>
                                                            <th>RSA PIN</th>
                                                            <th>EMPLOYER CODE</th>
                                                            <th>FUND CODE</th>
                                                            <th>PRICE</th>
                                                            <th>UNITS</th>
                                                            <th>BALANCE</th>
                                                            <th>EARNING</th>
                                                            <th>STATUS</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${statements}" var="s">
                                                            <tr>
                                                                <td><a href="${pageContext.request.contextPath}/statements/requests/${s.pin}">${s.surname}</a></td>
                                                                <td>${s.firstName}</td>
                                                                <td>${s.middleName}</td>
                                                                <td>${s.pin}</td>
                                                                <td>${s.employer}</td>
                                                                <td>${s.code}</td>
                                                                <c:choose>
								    <c:when test = "${s.price lt 0}">
								    <td><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="(${s.price})" /></td>
								    </c:when>
								    <c:otherwise>
									<td class="text-right"><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="${s.price}" /></td>
								    </c:otherwise>
                                                                </c:choose>    
                                                                <c:choose>
                                                                    <c:when test = "${s.units lt 0}">
                                                                    <td><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="(${s.units})" /></td>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <td class="text-right"><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="${s.units}" /></td>
                                                                    </c:otherwise>
                                                                </c:choose>    
                                                                <c:choose>
                                                                    <c:when test = "${s.balance lt 0}">
                                                                    <td><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="(${s.balance})" /></td>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <td class="text-right"><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="${s.balance}" /></td>
                                                                    </c:otherwise>
                                                                </c:choose>    
                                                                <c:choose>
                                                                    <c:when test = "${e.earning lt 0}">
                                                                    <td><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="(${s.earning})" /></td>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <td class="text-right"><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="${s.earning}" /></td>
                                                                    </c:otherwise>
                                                                </c:choose> 
                                                                <td>${s.status}</td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                    <tfoot>
                                                        <tr>
                                                            <th>Name</th>
                                                            <th>Position</th>
                                                            <th>Office</th>
                                                            <th>Age</th>
                                                            <th>Start date</th>
                                                            <th>Salary</th>
                                                            <th>Salary</th>
                                                            <th>Salary</th>
                                                            <th>Salary</th>
                                                            <th>Salary</th>
                                                            <th>Salary</th>
                                                        </tr>
                                                    </tfoot>
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