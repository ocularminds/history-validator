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
                                <c:if test="${endpoint != null}">
                                <form:form method="post" action="${pageContext.request.contextPath}/${endpoint}" modelAttribute="approval">
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
                                                    </div>
                                                </div>
                                            </form:form>
                                        </c:if>
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
					    <c:choose>
						<c:when test = "${s.contribution lt 0}">
						<td><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="(${s.contribution})" /></td>
						</c:when>
						<c:otherwise>
						    <td class="text-right"><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="${s.contribution}" /></td>
						</c:otherwise>
						</c:choose>          
						<c:choose>
						    <c:when test = "${s.total lt 0}">
						    <td><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="(${s.total})" /></td>
						    </c:when>
						    <c:otherwise>
							<td class="text-right"><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="${s.total}" /></td>
						    </c:otherwise>
						</c:choose>          
						<c:choose>
						    <c:when test = "${s.total lt 0}">
						    <td><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="(${s.total})" /></td>
						    </c:when>
						    <c:otherwise>
							<td class="text-right"><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="${s.total}" /></td>
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
						    <c:when test = "${s.fees lt 0}">
						    <td><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="(${s.fees})" /></td>
						    </c:when>
						    <c:otherwise>
							<td class="text-right"><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="${s.fees}" /></td>
						    </c:otherwise>
						</c:choose>          
						<c:choose>
						    <c:when test = "${s.withdrawals lt 0}">
						    <td><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="(${s.withdrawals})" /></td>
						    </c:when>
						    <c:otherwise>
							<td class="text-right"><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="${s.withdrawals}" /></td>
						    </c:otherwise>
						</c:choose>          
						<c:choose>
						    <c:when test = "${s.net lt 0}">
						    <td><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="(${s.net})" /></td>
						    </c:when>
						    <c:otherwise>
							<td class="text-right"><fmt:formatNumber type="number" minIntegerDigits="1" maxFractionDigits="4" value="${s.net}" /></td>
						    </c:otherwise>
						</c:choose>  
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