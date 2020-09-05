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
                                <h4 class="card-title">Statements Validations</h4>
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
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>${statement.surname}</td>
                                                <td>${statement.firstName}</td>
                                                <td>${statement.middleName}</td>
                                                <td>${statement.pin}</td>
                                                <td>${statement.employer}</td>
                                                <td>${statement.code}</td>
                                                <td>${statement.price}</td>
                                                <td>${statement.units}</td>
                                                <td>${statement.balance}</td>
                                                <td>${statement.earning}</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="table-responsive">
                                    <table id="zero_config" class="table table-striped table-bordered no-wrap">
                                        <thead>
                                            <tr>
                                                <th>PAY_RECEIVE_DATE</th>
                                                <th>RELATED_MONTH_START</th>
                                                <th>RELATED_MONTH_END</th>
                                                <th>TRANSACTION_TYPE</th>
                                                <th>EMPLOYER_CONTRIBUTION</th>
                                                <th>EMPLOYEE_CONTRIBUTION</th>
                                                <th>VOLUNTARY_CONTINGENT</th>
                                                <th>VOLUNTARY_RETIREMENT</th>
                                                <th>OTHER_INFLOWS</th>
                                                <th>TOTAL_CONTRIBUTIONS</th>
                                                <th>NUMBER_OF_UNITS</th>
                                                <th>FEES</th>
                                                <th>OTHER_WITHDRAWALS</th>
                                                <th>NET_CONTRIBUTIONS</th>
                                                <th>RELATED_PFA_CODE</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${statement.records}" var="s">
                                                <tr>
                                                    <td><fmt:formatDate value="${s.dateReceived}" pattern="dd-MMM-yyyy" /></td>
                                                    <td><fmt:formatDate value="${s.monthStart}" pattern="dd-MMM-yyyy" /></td>
                                                    <td><fmt:formatDate value="${s.monthEnd}" pattern="MMM-yyyy" /></td>
                                                    <td>${s.type}</td>
                                                    <td>${s.employer}</td>
                                                    <td>${s.contribution}</td>
                                                    <td>${s.voluntaryContigent}</td>
                                                    <td>${s.voluntaryRetirement}</td>
                                                    <td>${s.otherInflows}</td>
                                                    <td>${s.total}</td>
                                                    <td>${s.units}</td>
                                                    <td>${s.fees}</td>
                                                    <td>${s.withdrawals}</td>
                                                    <td>${s.net}</td>
                                                    <td>${s.pfa}</td>
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