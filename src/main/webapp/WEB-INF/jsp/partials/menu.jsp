<div class="scroll-sidebar" data-sidebarbg="skin6">
<nav class="sidebar-nav">
<ul id="sidebarnav">
    <li class="sidebar-item"> 
    <a class="sidebar-link sidebar-link" href="${pageContext.request.contextPath}/dashboard" aria-expanded="false"><i data-feather="home" class="feather-icon"></i><span
		class="hide-menu">Dashboard</span></a></li>
    <li class="list-divider"></li>
    <li class="nav-small-cap"><span class="hide-menu">Admin</span></li>    
    <li class="sidebar-item"> 
	<a class="sidebar-link" href="${pageContext.request.contextPath}/roles" aria-expanded="false">
	  <i data-feather="tag" class="feather-icon"></i>
	  <span class="hide-menu">User Roles</span></a>
    </li>
    <li class="sidebar-item">
	<a class="sidebar-link sidebar-link" href="${pageContext.request.contextPath}/users" aria-expanded="false">
	    <i data-feather="message-square" class="feather-icon"></i><span class="hide-menu">Users</span></a></li>
    <li class="list-divider"></li>
    <li class="nav-small-cap"><span class="hide-menu">Operations</span></li>
    <li class="sidebar-item"> 
      <a class="sidebar-link has-arrow" href="javascript:void(0)" aria-expanded="false">
      <i data-feather="file-text" class="feather-icon"></i>
      <span class="hide-menu">Statements </span></a>
	 <ul aria-expanded="true" class="collapse  first-level base-level-line">
	    <li class="sidebar-item">
	        <a href="${pageContext.request.contextPath}/statements/search" class="sidebar-link">
	           <span class="hide-menu">Generate </span>
	        </a>
	    </li>
	    <li class="sidebar-item">
	        <a href="${pageContext.request.contextPath}/statements/requests" class="sidebar-link">
	            <span class="hide-menu">Statements</span>
	        </a>
	    </li>
	    <li class="sidebar-item">
	        <a href="${pageContext.request.contextPath}/statements/reviews" class="sidebar-link">
	           <span class="hide-menu">Review</span>
	        </a>
	    </li>
	    <li class="sidebar-item">
	    <a href="${pageContext.request.contextPath}/statements/approvals" class="sidebar-link">
	      <span class="hide-menu">Approvals</span></a>
	    </li>
	</ul>
    </li>
    <li class="sidebar-item"> 
       <a class="sidebar-link has-arrow" href="javascript:void(0)" aria-expanded="false"><i data-feather="grid" class="feather-icon"></i><span
		class="hide-menu">Audits </span></a>
	<ul aria-expanded="false" class="collapse  first-level base-level-line">
	    <li class="sidebar-item">
	    <a href="${pageContext.request.contextPath}/audits" class="sidebar-link">
	    <span class="hide-menu">Activities</span></a>
	    </li>
	</ul>
    </li>
    <li class="list-divider"></li>
    <li class="nav-small-cap"><span class="hide-menu">Authentication</span></li>
    <li class="sidebar-item"> 
       <a class="sidebar-link sidebar-link" href="${pageContext.request.contextPath}/logout" aria-expanded="false">
	  <i data-feather="lock" class="feather-icon"></i>
	  <span class="hide-menu">Lock Screen </span></a>
    </li>
    <li class="sidebar-item"> 
       <a class="sidebar-link sidebar-link" href="#help" aria-expanded="false">
	  <i data-feather="edit-3" class="feather-icon"></i><span class="hide-menu">Help</span></a>
    </li>
</ul>
</nav>
</div>