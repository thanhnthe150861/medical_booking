<%@ page import="mvc.dal.AdminDBContext" %>
<%@ page import="mvc.model.MedicalRecord" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<%
    AdminDBContext adminDBContext = new AdminDBContext();
    List<MedicalRecord> appointmentList = adminDBContext.getInforTotalAppoinment();
%>
<!-- Main Wrapper -->
<div class="main-wrapper">

    <!-- Header -->
    <div class="header">

        <!-- Logo -->
        <div class="header-left">
            <a href="admin_dashboard" class="logo">
                <span class="text-primary">Clinic</span>-TATQ
            </a>
            <a href="admin_dashboard" class="logo logo-small">
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
                                                src="view/admin/assets/img/profiles/avatar-01.jpg" width="31"
                    ></span>
                </a>
                <div class="dropdown-menu">
                    <div class="user-header">
                        <div class="avatar avatar-sm">
                            <img src="view/admin/assets/img/profiles/avatar-01.jpg"
                                 class="avatar-img rounded-circle">
                        </div>
                        <div class="user-text">
                            <h6>Administrator</h6>
                        </div>
                    </div>
                    <a class="dropdown-item" href="admin_dashboard">Hồ Sơ</a>
                    <a class="dropdown-item" href="login">Đăng Xuất</a>
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
                        <a href="admin_dashboard"><i class="fe fe-home"></i> <span>Bảng Điều Khiển</span></a>
                    </li>
                    <li class="active">
                        <a href="appointment_list"><i class="fe fe-layout"></i> <span>Cuộc Hẹn</span></a>
                    </li>
                    <li>
                        <a href="staff_list"><i class="fe fe-users"></i> <span>Nhân Viên</span></a>
                    </li>
                    <li>
                        <a href="doctor_list"><i class="fe fe-user-plus"></i> <span>Bác Sĩ</span></a>
                    </li>
                    <li>
                        <a href="patient_list"><i class="fe fe-user"></i> <span>Bệnh Nhân</span></a>
                    </li>
                    <li>
                        <a href="invoice_list"><i class="fe fe-document"></i> <span>Hóa Đơn</span></a>
                    </li>
                    <li>
                        <a href="profile"><i class="fe fe-user-plus"></i> <span>Hồ Sơ</span></a>
                    </li>
                    <li class="submenu">
                        <a href="#"><i class="fe fe-document"></i> <span>Thêm Mới/Cập Nhật</span> <span
                                class="menu-arrow"></span></a>
                        <ul style="display: none;">
                            <li><a href="form_details?str=doctor">Bác Sĩ</a></li>
                            <li><a href="form_details?str=patient">Bệnh Nhân</a></li>
                            <li><a href="form_details?str=staff">Nhân Viên</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="login">
                            <i class="fas fa-sign-out-alt"></i>
                            <span>Đăng Xuất</span>
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
                        <h3 class="page-title">Cuộc Hẹn</h3>
                        <ul class="breadcrumb">
                            <li class="breadcrumb-item"><a href="admin_dashboard">Bảng Điều Khiển</a></li>
                            <li class="breadcrumb-item active">Cuộc Hẹn</li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- /Page Header -->
            <div class="row">
                <div class="col-md-12">

                    <!-- Recent Orders -->
                    <div class="card">
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="datatable table table-hover table-center mb-0">
                                    <thead>
                                    <tr>
                                        <th>Tên Bác Sĩ</th>
                                        <th>Chuyên Khoa</th>
                                        <th>Tên Bệnh Nhân</th>
                                        <th>Thời Gian Đặt Cuộc Hẹn</th>
                                        <th class="text-center">Trạng Thái</th>
                                        <th>Thanh Toán</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <% for (MedicalRecord appointment : appointmentList) { %>
                                    <tr>
                                        <td>
                                            <h2 class="table-avatar">
                                                <a href="#" class="avatar avatar-sm mr-2"><img
                                                        class="avatar-img rounded-circle"
                                                        src="<%= appointment.getBooking().getDoctor().getUrl() %>"
                                                ></a>
                                                <a href="#"><%= appointment.getBooking().getDoctor().getName() %>
                                                </a>
                                            </h2>
                                        </td>
                                        <td><%= appointment.getBooking().getDoctor().getSpecialty() %>
                                        </td>
                                        <td>
                                            <h2 class="table-avatar">
                                                <a href="#" class="avatar avatar-sm mr-2"><img
                                                        class="avatar-img rounded-circle"
                                                        src="<%= appointment.getBooking().getPatient().getUrl() %>"
                                                ></a>
                                                <a href="#"><%= appointment.getBooking().getPatient().getName() %>
                                                </a>
                                            </h2>
                                        </td>
                                        <td><%= appointment.getBooking().getDate() %> <span
                                                class="text-primary d-block"><%= appointment.getBooking().getSlots().getName() %></span>
                                        </td>
                                        <td class="text-center">
                                            <% if (appointment.getBooking().getStatus().equals("Confirmed")) { %>
                                            <span class="badge badge-pill bg-success-light"><%= appointment.getBooking().getStatus() %></span>
                                            <% } else if (appointment.getBooking().getStatus().equals("Pending")) { %>
                                            <span class="badge badge-pill bg-warning-light"><%= appointment.getBooking().getStatus() %></span>
                                            <% } else if (appointment.getBooking().getStatus().equals("Cancelled")) { %>
                                            <span class="badge badge-pill bg-danger-light"><%= appointment.getBooking().getStatus() %></span>
                                            <% } else if (appointment.getBooking().getStatus().equals("Completed")) { %>
                                            <span class="badge badge-pill bg-info-light"><%= appointment.getBooking().getStatus() %></span>
                                            <% } %>
                                        </td>
                                        <td class="text-left">
                                            <%= appointment.getBill().getTotalPrice() %> VND
                                        </td>
                                    </tr>
                                    <% } %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!-- /Recent Orders -->

                </div>
            </div>
        </div>
    </div>
    <!-- /Page Wrapper -->

</div>
<!-- /Main Wrapper -->

<!-- jQuery -->
<script src="assets/js/jquery-3.2.1.min.js"></script>

<!-- Bootstrap Core JS -->
<script src="assets/js/popper.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>

<!-- Slimscroll JS -->
<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- Datatables JS -->
<script src="assets/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="assets/plugins/datatables/datatables.min.js"></script>

<!-- Custom JS -->
<script src="assets/js/script.js"></script>

</body>
</html>