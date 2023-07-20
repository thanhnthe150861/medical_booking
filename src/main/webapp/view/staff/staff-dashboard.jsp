<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="mvc.model.MedicalRecord" %>
<%@ page import="java.util.List" %>
<%@ page import="mvc.dal.StaffDBContext" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
    <title>TATQ Clinic</title>

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
                                                src="${sessionScope.staff.url}" width="31"
                                                alt="${sessionScope.staff.name}"></span>
                </a>
                <div class="dropdown-menu">
                    <div class="user-header">
                        <div class="avatar avatar-sm">
                            <img src="${sessionScope.staff.url}" alt="User Image"
                                 class="avatar-img rounded-circle">
                        </div>
                        <div class="user-text">
                            <h6>${sessionScope.staff.name}</h6>
                        </div>
                    </div>
                    <a class="dropdown-item" href="staff_dashboard">Bảng điều khiển</a>
                    <a class="dropdown-item" href="login">Đăng xuất</a>
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
                    <li class="active">
                        <a href="staff_dashboard"><i class="fe fe-home"></i> <span>Bảng điều khiển</span></a>
                    </li>
                    <li>
                        <a href="staff_appointment"><i class="fe fe-layout"></i> <span>Lịch hẹn</span></a>
                    </li>
                    <li>
                        <a href="list_doctor"><i class="fe fe-user"></i> <span>Danh sách bác sĩ</span></a>
                    </li>
                    <li>
                        <a href="list_patient"><i class="fe fe-user"></i> <span>Danh sách bệnh nhân</span></a>
                    </li>
                    <li>
                        <a href="list_invoice"><i class="fe fe-document"></i> <span>Hóa đơn</span></a>
                    </li>
                    <li>
                        <a href="staff_profile"><i class="fe fe-edit"></i><span>Thông tin cá nhân</span></a>
                    </li>
                    <li>
                        <a href="staff_change_password"><i class="fe fe-edit"></i> <span>Đổi mật khẩu</span></a>
                    </li>
                    <li>
                        <a href="login">
                            <i class="fe fe-eject"></i>
                            <span>Đăng xuất</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!-- /Sidebar -->

    <!-- Page Wrapper -->
    <div class="page-wrapper">

        <div class="content container-fluid">

            <!-- Page Header -->
            <div class="page-header">
                <div class="row">
                    <div class="col-sm-12">
                        <h3 class="page-title">Xin chào ${sessionScope.staff.name}</h3>
                        <ul class="breadcrumb">
                            <li class="breadcrumb-item"><a href="staff_dashboard">Bảng điều khiển</a></li>
                            <li class="breadcrumb-item active">Danh sách Bác sĩ</li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- /Page Header -->

            <div class="content container-fluid">
                <%
                    StaffDBContext staffDBContext = new StaffDBContext();
                    List<MedicalRecord> doctorList = staffDBContext.doctorList();
                %>

                <div class="row">
                    <div class="col-sm-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="datatable table table-hover table-center mb-0">
                                        <thead>
                                        <tr>
                                            <th>Tên bác sĩ</th>
                                            <th>Chuyên môn</th>
                                            <th class="text-center">Trạng thái</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <% for (MedicalRecord doctor : doctorList) { %>
                                        <tr>
                                            <td>
                                                <h2 class="table-avatar">
                                                    <a href="#" class="avatar avatar-sm mr-2">
                                                        <img class="avatar-img rounded-circle"
                                                             src="<%= doctor.getBooking().getDoctor().getUrl() %>"
                                                             alt="User Image">
                                                    </a>
                                                    <a href="#"><%= doctor.getBooking().getDoctor().getName() %>
                                                    </a>
                                                </h2>
                                            </td>
                                            <td><%= doctor.getBooking().getDoctor().getSpecialty() %>
                                            </td>
                                            <td class="text-center">
                                                <% if (doctor.getBooking().getDoctor().getAccount().getStatus()) { %>
                                                <span class="badge badge-pill bg-success inv-badge">Active</span>
                                                <% } else { %>
                                                <span class="badge badge-pill bg-danger inv-badge">Deactive</span>
                                                <% } %>
                                            </td>
                                        </tr>
                                        <% } %>
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