<nav class="navbar top-navbar navbar-expand-md">
    <div class="navbar-header" data-logobg="skin6">
        <a class="nav-toggler waves-effect waves-light d-block d-md-none" href="javascript:void(0)"><i
                class="ti-menu ti-close"></i></a>
        <div class="navbar-brand">
            <a href="dashboard">
                <b class="logo-icon">
                    <img src="${pageContext.request.contextPath}/assets/images/n_1.png" alt="homepage" class="dark-logo" />
                    <img src="${pageContext.request.contextPath}/assets/images/n_1.png" alt="homepage" class="light-logo" />
                </b>
                <span class="logo-text">
                    <img src="${pageContext.request.contextPath}/assets/images/n_1" alt="homepage" class="dark-logo" />
                    <img src="${pageContext.request.contextPath}/assets/images/n_1.png" class="light-logo" alt="homepage" />
                </span>
            </a>
        </div>
        <a class="topbartoggler d-block d-md-none waves-effect waves-light" href="javascript:void(0)"
           data-toggle="collapse" data-target="#navbarSupportedContent"
           aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><i
                class="ti-more"></i></a>
    </div>
    <div class="navbar-collapse collapse" id="navbarSupportedContent">
        <ul class="navbar-nav float-left mr-auto ml-3 pl-1">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle pl-md-3 position-relative" href="javascript:void(0)"
                   id="bell" role="button" data-toggle="dropdown" aria-haspopup="true"
                   aria-expanded="false">
                    <span><i data-feather="bell" class="svg-icon"></i></span>
                    <span class="badge badge-primary notify-no rounded-circle">5</span>
                </a>
                <div class="dropdown-menu dropdown-menu-left mailbox animated bounceInDown">
                    <ul class="list-style-none">
                    </ul>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i data-feather="settings" class="svg-icon"></i>
                </a>
            </li>
            <li class="nav-item d-none d-md-block">
                <a class="nav-link" href="javascript:void(0)">
                    <div class="customize-input">
                        <select
                            class="custom-select form-control bg-white custom-radius custom-shadow border-0">
                            <option selected>EN</option>
                            <option value="1">AB</option>
                            <option value="2">AK</option>
                            <option value="3">BE</option>
                        </select>
                    </div>
                </a>
            </li>
        </ul>
        <ul class="navbar-nav float-right">
            <li class="nav-item d-none d-md-block">
                <a class="nav-link" href="javascript:void(0)">
                    <form>
                        <div class="customize-input">
                            <input class="form-control custom-shadow custom-radius border-0 bg-white"
                                   type="search" placeholder="Search" aria-label="Search">
                            <i class="form-control-icon" data-feather="search"></i>
                        </div>
                    </form>
                </a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="javascript:void(0)" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    <img src="${pageContext.request.contextPath}/assets/images/users/profile-pic.jpg" alt="user" class="rounded-circle"
                         width="40">
                    <span class="ml-2 d-none d-lg-inline-block"><span>Hello,</span> <span
                            class="text-dark">${fullname}</span> <i data-feather="chevron-down"
                            class="svg-icon"></i></span>
                </a>
                <div class="dropdown-menu dropdown-menu-right user-dd animated flipInY">
                    <a class="dropdown-item" href="javascript:void(0)">
                        <i data-feather="user" class="svg-icon mr-2 ml-1"></i>
                        My Profile</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/profile"><i data-feather="settings"
                                                                                                  class="svg-icon mr-2 ml-1"></i>
                        Account Setting</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">
                        <i data-feather="power" class="svg-icon mr-2 ml-1"></i>
                        Logout</a>
                    <div class="dropdown-divider"></div>
                    <div class="pl-4 p-3">
                        <a href="${pageContext.request.contextPath}/profile" class="btn btn-sm btn-info">View Profile</a></div>
                </div>
            </li>
        </ul>
    </div>
</nav>