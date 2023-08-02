<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<!-- Mirrored from dreamguys.co.in/demo/doccure/admin/appointment-list.jsp by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 30 Nov 2019 04:12:46 GMT -->
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
    <title>TATQ CLINIC - APPOINTMENT</title>

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
                    ></span>
                </a>
                <div class="dropdown-menu">
                    <div class="user-header">
                        <div class="avatar avatar-sm">
                            <img src="${sessionScope.staff.url}"
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
                    <li>
                        <a href="staff_dashboard"><i class="fe fe-home"></i> <span>Bảng điều khiển</span></a>
                    </li>
                    <li class="active">
                        <a href="create_invoice"><i class="fe fe-edit"></i>
                            <span>Tạo hóa đơn</span></a>
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
                        <h3 class="page-title">Tạo hóa đơn cho lịch đã hoàn thành trong ngày</h3>
                        <ul class="breadcrumb">
                            <li class="breadcrumb-item"><a href="staff_dashboard">Bảng điều khiển</a>
                            </li>
                            <li class="breadcrumb-item active">Tạo hóa đơn</li>
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
                                        <th>Mã lịch đặt</th>
                                        <th>Tên bác sĩ</th>
                                        <th>Tên bệnh nhân</th>
                                        <th>Thời gian hẹn</th>
                                        <th>Vấn đề về</th>
                                        <th>Mô tả tình trạng</th>
                                        <th class="text-center">Trạng thái</th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${sessionScope.bookingList}" var="bl">
                                        <tr>
                                            <td>${bl.booking.id}</td>
                                            <td>
                                                <h2 class="table-avatar">
                                                    <a href="#" class="avatar avatar-sm mr-2">
                                                        <img class="avatar-img rounded-circle"
                                                             src="${bl.booking.doctor.url}"
                                                        >
                                                    </a>
                                                    <a href="#">${bl.booking.doctor.name}
                                                    </a>
                                                </h2>
                                            </td>
                                            <td>
                                                <h2 class="table-avatar">
                                                    <a href="#" class="avatar avatar-sm mr-2">
                                                        <img class="avatar-img rounded-circle"
                                                             src="${bl.booking.patient.url}"
                                                        >
                                                    </a>
                                                    <a href="#">${bl.booking.patient.name}
                                                    </a>
                                                </h2>
                                            </td>
                                            <td>${bl.booking.date}<span
                                                    class="d-block text-info">${bl.booking.slots.name}</span>
                                            </td>
                                            <td>${bl.booking.specialty.name}</td>
                                            <td>${bl.booking.booking_reason}</td>
                                            <td class="text-center">
                                                    <span class="badge badge-pill bg-${bl.booking.status == 'Confirmed' ? 'success-light' : bl.booking.status == 'Pending' ? 'warning-light' : bl.booking.status == 'Cancelled' ? 'danger-light' : bl.booking.status == 'Completed' ? 'info-light' : ''}">
                                                            ${bl.booking.status}
                                                    </span>
                                            </td>
                                            <td class="text-right">
                                                <div class="table-action">
                                                    <c:if test="${bl.booking.status == 'Completed'}">
                                                        <c:if test="${bl.bill.id == 0}">
                                                            <a href="invoice_details?mid=${bl.id}"
                                                               class="btn btn-sm bg-success-light">
                                                                <i class="far fa-edit"></i> Tạo hóa đơn
                                                            </a>
                                                        </c:if>
                                                        <c:if test="${bl.bill.id != 0}">
                                                            <a href="invoice_view?bid=${bl.bill.id}"
                                                               class="btn btn-sm bg-success-light">
                                                                <i class="far fa-eye"></i> Xem hóa đơn
                                                            </a>
                                                        </c:if>
                                                    </c:if>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
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