<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
    <title>Doccure - Dashboard</title>

    <!-- Favicon -->
    <link rel="shortcut icon" type="image/x-icon" href="view/admin/assets/img/favicon.png">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="view/admin/assets/css/bootstrap.min.css">

    <!-- Fontawesome CSS -->
    <link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">
    <link rel="stylesheet" href="view/admin/assets/css/font-awesome.min.css">

    <!-- Feathericon CSS -->
    <link rel="stylesheet" href="view/admin/assets/css/feathericon.min.css">

    <link rel="stylesheet" href="view/admin/assets/plugins/morris/morris.css">

    <!-- Main CSS -->
    <link rel="stylesheet" href="view/admin/assets/css/style.css">

    <!--[if lt IE 9]>
    <script src="view/admin/assets/js/html5shiv.min.js"></script>
    <script src="view/admin/assets/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<!-- Main Wrapper -->
<div class="main-wrapper">

    <!-- Header -->
    <div class="header">

        <!-- Logo -->
        <div class="header-left">
            <a href="staff_dashboard" class="logo">
                <span class="text-primary">Clinic</span>-TATQ
            </a>
            <a href="staff_dashboard" class="logo logo-small">
                <span class="text-primary" width="50" height="50">Clinic</span>
            </a>
        </div>
        <!-- /Logo -->

        <a href="javascript:void(0);" id="toggle_btn">
            <i class="fe fe-text-align-left"></i>
        </a>

        <!-- Mobile Menu Toggle -->
        <a class="mobile_btn" id="mobile_btn">
            <i class="fa fa-bars"></i>
        </a>
        <!-- /Mobile Menu Toggle -->

        <!-- Header Right Menu -->
        <ul class="nav user-menu">

            <!-- User Menu -->
            <li class="nav-item dropdown has-arrow">
                <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown">
                    <span class="user-img"><img class="rounded-circle"
                                                src="images/staff/staff1.jpg" width="31"
                                                alt="Swift Taylor"></span>
                </a>
                <div class="dropdown-menu">
                    <div class="user-header">
                        <div class="avatar avatar-sm">
                            <img src="images/staff/staff1.jpg" alt="User Image"
                                 class="avatar-img rounded-circle">
                        </div>
                        <div class="user-text">
                            <h6>Staff</h6>
                        </div>
                    </div>
                    <a class="dropdown-item" href="staff_dashboard">My Profile</a>
                    <a class="dropdown-item" href="login">Logout</a>
                </div>
            </li>
            <!-- /User Menu -->

        </ul>
        <!-- /Header Right Menu -->

    </div>
    <!-- /Header -->

    <!-- Sidebar -->
    <div class="sidebar" id="sidebar">
        <div class="sidebar-inner slimscroll">
            <div id="sidebar-menu" class="sidebar-menu">
                <ul>
                    <li class="menu-title">
                        <span>Main</span>
                    </li>
                    <li>
                        <a href="staff_dashboard"><i class="fe fe-home"></i> <span>Dashboard</span></a>
                    </li>
                    <li>
                        <a href="staff_appointment"><i class="fe fe-layout"></i> <span>Appointments</span></a>
                    </li>
                    <li>
                        <a href="list_doctor"><i class="fe fe-user"></i> <span>Doctors</span></a>
                    </li>
                    <li>
                        <a href="list_patient"><i class="fe fe-user"></i> <span>Patients</span></a>
                    </li>
                    <li>
                        <a href="list_invoice"><i class="fe fe-document"></i> <span>Invoice</span></a>
                    </li>
                    <li class="active">
                        <a href="staff_profile"><i class="fe fe-user"></i><span>Profile Settings</span></a>
                    </li>
                    <li>
                        <a href="staff_change_password"><i class="fe fe-user-plus"></i> <span>Change Password</span></a>
                    </li>
                    <li>
                        <a href="login">
                            <i class="fa fa-sign-out-alt"></i>
                            <span>Logout</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!-- /Sidebar -->

    <!-- Page Content -->
    <div class="page-wrapper">
        <div class="content container-fluid">
            <!-- Page Header -->
            <div class="page-header">
                <div class="row">
                    <div class="col-sm-12">
                        <h3 class="page-title">User Profile</h3>
                        <ul class="breadcrumb">
                            <li class="breadcrumb-item"><a href="staff_dashboard">Dashboard</a></li>
                            <li class="breadcrumb-item active">User Profile</li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- /Page Header -->

            <form class="row" action="staff_profile" method="post" enctype="multipart/form-data">
                <div class="col-sm-12">
                    <!-- Basic Information -->
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">User Information</h4>
                            <div class="row form-row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <div class="change-avatar">
                                            <div class="profile-img">
                                                <img src="${sessionScope.staff.url}" alt="User Image" style="width: 200px; height: 200px; object-fit: cover;">
                                            </div>
                                            <div class="upload-img">
                                                <div class="change-photo-btn">
                                                    <span><i class="fa fa-upload"></i> Upload Photo</span>
                                                    <input type="file" class="upload" name="file">
                                                </div>
                                                <small class="form-text text-muted">Allowed JPG, GIF or PNG. Max size of 2MB</small>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Place this code where you want to display the error message -->
                                    <% String errorMessage = (String) request.getAttribute("messError"); %>
                                    <% if (errorMessage != null && !errorMessage.isEmpty()) { %>
                                    <div class="alert alert-danger" role="alert">
                                        <%= errorMessage %>
                                    </div>
                                    <% } %>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Username <span class="text-danger">*</span></label>
                                        <input type="text" class="form-control" value="${sessionScope.account.username}" readonly>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Email <span class="text-danger">*</span></label>
                                        <input type="email" class="form-control" value="${sessionScope.account.email}" readonly>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Name <span class="text-danger">*</span></label>
                                        <input type="text" class="form-control" name="name" value="${sessionScope.staff.name}">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Phone Number</label>
                                        <input type="text" class="form-control" name="phone" value="${sessionScope.account.phone}">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Gender</label>
                                        <select class="form-control select" name="gender">
                                            <option>Select</option>
                                            <option value="Male" ${sessionScope.staff.gender == "Male" ? "selected" : ""}>Male</option>
                                            <option value="Female" ${sessionScope.staff.gender == "Female" ? "selected" : ""}>Female</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group mb-0">
                                        <label>Date of Birth</label>
                                        <input type="date" class="form-control" name="dob" value="${sessionScope.staff.dob}">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="submit-section submit-btn-bottom">
                        <button type="submit" class="btn btn-primary submit-btn">Save Changes</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <!-- /Page Content -->

    <!-- /Page Wrapper -->
</div>
<!-- /Main Wrapper -->

<!-- jQuery -->
<script src="view/admin/assets/js/jquery-3.2.1.min.js"></script>

<!-- Bootstrap Core JS -->
<script src="view/admin/assets/js/popper.min.js"></script>
<script src="view/admin/assets/js/bootstrap.min.js"></script>

<!-- Slimscroll JS -->
<script src="view/admin/assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<script src="view/admin/assets/plugins/raphael/raphael.min.js"></script>
<script src="view/admin/assets/plugins/morris/morris.min.js"></script>
<script src="view/admin/assets/js/chart.morris.js"></script>

<!-- Custom JS -->
<script src="view/admin/assets/js/script.js"></script>

</body>

<!-- Mirrored from dreamguys.co.in/demo/doccure/admin/index.html by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 30 Nov 2019 04:12:34 GMT -->
</html>