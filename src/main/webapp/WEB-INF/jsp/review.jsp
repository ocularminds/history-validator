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
                            <div class="card-header">
                                <form:form method="post" action="${pageContext.request.contextPath}/statements/review/${statement.pin}" modelAttribute="approval">
				<div class="form-body">
				    <div class="row">
					<div class="col-md-6">                                                            
					    <div class="row">
						<div class="col-md-6">
						    ID
						</div>
						<div class="col-md-6">
						    <div class="form-group">
							<form:input path="requestId" type="text" class="form-control" readonly="true"/>
							<form:input path="approval" type="hidden"/>
						    </div>
						</div>
					    </div>
					</div>
					<div class="col-md-6">
					    <div class="row">
						<div class="col-md-6">
						    Comment
						</div>
						<div class="col-md-6">
						    <div class="form-group">
							<form:input path="comment" type="text" class="form-control"/>
						    </div>
						</div>
					    </div>
					</div>
				    </div>
				</div>
				<div class="form-actions">
				    <div class="text-right">
					<button type="submit" class="btn btn-info">${buttonLabel}</button>
					<button type="reset" class="btn btn-dark">Reset</button>
					<a class="btn btn-success" href="${pageContext.request.contextPath}/statements/export/${statement.pin}/statement.xlsx"> <i data-feather="file-text" class="feather-icon"></i>&nbsp;Export Excel</a>&nbsp;
					<a class="btn btn-light" href="${pageContext.request.contextPath}/statements/export/${statement.pin}/statement.json"> <i data-feather="file-text" class="feather-icon"></i>&nbsp;Export JSON</a>
				    </div>
				</div>
			    </form:form>
                            </div>
                            <div class="card-body">
                                <h4 class="card-title">Statements Validations</h4>
                                <p><a class="btn btn-success" href="${pageContext.request.contextPath}/statements/export/${statement.pin}"> <i data-feather="file-text" class="feather-icon"></i>&nbsp;Export Excel</a></p>
                                 <p><ul>
                                    <c:forEach items="${statement.comments}" var="comment">
                                        <li>${comment}</li>
                                    </c:forEach>
                                 </ul>
                                </p>
                                <div class="table-responsive">
                                    <table id="zero_config" class="table table-striped table-bordered no-wrap" style="font-size: 0.6em">
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
                                                <c:set var = "price" scope = "request" value = "${s.price}"/>
                                                <c:set var = "units" scope = "request" value = "${s.units}"/>
						<c:set var = "balance" scope = "request" value = "${s.balance}"/>
                                                <c:set var = "earning" scope = "request" value = "${s.earning}"/>
						<c:choose>
						    <c:when test = "${price lt 0}">
						    <td><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="(${price})" /></td>
						    </c:when>
						    <c:otherwise>
							<td class="text-right"><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="${price}" /></td>
						    </c:otherwise>
						</c:choose>    
						<c:choose>
						    <c:when test = "${units lt 0}">
						    <td><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="(${units})" /></td>
						    </c:when>
						    <c:otherwise>
							<td class="text-right"><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="${units}" /></td>
						    </c:otherwise>
						</c:choose>    
						<c:choose>
						    <c:when test = "${balance lt 0}">
						    <td><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="(${balance})" /></td>
						    </c:when>
						    <c:otherwise>
							<td class="text-right"><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="${balance}" /></td>
						    </c:otherwise>
						</c:choose>    
						<c:choose>
						    <c:when test = "${earning lt 0}">
						    <td><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="(${earning})" /></td>
						    </c:when>
						    <c:otherwise>
							<td class="text-right"><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="${earning}" /></td>
						    </c:otherwise>
						</c:choose> 
			    </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="table-responsive">
                                    <table id="zero_config" class="table table-striped table-bordered no-wrap" style="font-size: 0.6em">
                                        <thead>
                                            <tr>
                                                <th>PAY_DATE</th>
                                                <th>MONTH_START</th>
                                                <th>MONTH_END</th>
                                                <th>TTRAN_TYPE</th>
                                                <th>EMPLOYER</th>
                                                <th>EMPLOYEE</th>
                                                <th>TOTAL</th>
                                                <th>UNITS</th>
                                                <th>FEES</th>
                                                <th>WITHDRAWALS</th>
                                                <th>NET_CONTRIBUTIONS</th>
                                                <th>PFA_CODE</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${statement.records}" var="s">
                                                <tr>
                                                    <td><fmt:formatDate value="${s.dateReceived}" pattern="dd-MMM-yyyy" /></td>
                                                    <td><fmt:formatDate value="${s.monthStart}" pattern="dd-MMM-yyyy" /></td>
                                                    <td><fmt:formatDate value="${s.monthEnd}" pattern="MMM-yyyy" /></td>
                                                    <td>${s.type}</td> 
					        <c:set var = "employer" scope = "request" value = "${s.employer}"/>
					        <c:set var = "contribution" scope = "request" value = "${s.contribution}"/>
					        <c:set var = "total" scope = "request" value = "${s.total}"/>
					        <c:set var = "units" scope = "request" value = "${s.units}"/>
						<c:set var = "fees" scope = "request" value = "${s.fees}"/>
                                                <c:set var = "withdrawals" scope = "request" value = "${s.withdrawals}"/>                                               
					        <c:set var = "net" scope = "request" value = "${s.net}"/>
					        <td class="text-right"><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="${employer}" /></td>
						<td class="text-right"><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="${contribution}" /></td>
						<td class="text-right"><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="${total}" /></td>
						<td class="text-right"><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="${units}" /></td>
						<td class="text-right"><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="${fees}" /></td>
						<td class="text-right"><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="${withdrawals}" /></td>
						<td class="text-right"><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="${net}" /></td>  
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