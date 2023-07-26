<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="mvc.model.Doctor" %>
<%@ page import="mvc.dal.DoctorDBContext" %>
<%@ page import="mvc.model.MedicalRecord" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>TATQ Clinic</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">

    <!-- Favicons -->
    <link href="assets/img/favicon.png" rel="icon">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">

    <!-- Fontawesome CSS -->
    <link rel="stylesheet" href="assets/plugins/fontawesome/css/fontawesome.min.css">
    <link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">

    <!-- Main CSS -->
    <link rel="stylesheet" href="assets/css/style.css">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="assets/js/html5shiv.min.js"></script>
    <script src="assets/js/respond.min.js"></script>
    <![endif]-->

</head>
<body>

<!-- Main Wrapper -->
<div class="main-wrapper">

    <!-- Header -->
    <header class="header">
        <nav class="navbar navbar-expand-lg header-nav">
            <div class="navbar-header">
                <a id="mobile_btn" href="javascript:void(0);">
							<span class="bar-icon">
								<span></span>
								<span></span>
								<span></span>
							</span>
                </a>
                <a href="doctor_dashboard" class="navbar-brand logo">
                    <span class="text-primary">Clinic</span>-TATQ
                </a>
            </div>
            <div class="main-menu-wrapper">
                <div class="menu-header">
                    <a href="doctor_dashboard" class="menu-logo">
                        <span class="text-primary" width="50" height="50">Clinic</span>
                    </a>
                    <a id="menu_close" class="menu-close" href="javascript:void(0);">
                        <i class="fas fa-times"></i>
                    </a>
                </div>
                <ul class="main-nav">
                    <li>
                        <a href="doctor_dashboard">Trang chủ</a>
                    </li>
                </ul>
            </div>
            <ul class="nav header-navbar-rht">
                <li class="nav-item contact-item">
                    <div class="header-contact-img">
                        <i class="far fa-hospital"></i>
                    </div>
                    <div class="header-contact-detail">
                        <p class="contact-header">Liên hệ</p>
                        <p class="contact-info-header"> +84 868746275</p>
                    </div>
                </li>

                <!-- User Menu -->
                <li class="nav-item dropdown has-arrow logged-item">
                    <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown">
								<span class="user-img">
									<img class="rounded-circle" src="${sessionScope.doctor.url}" width="31">
								</span>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right">
                        <div class="user-header">
                            <div class="avatar avatar-sm">
                                <img src="${sessionScope.doctor.url}"
                                     class="avatar-img rounded-circle">
                            </div>
                            <div class="user-text">
                                <h6>${sessionScope.doctor.name}</h6>
                                <p class="text-muted mb-0">${sessionScope.doctor.ranks.name}</p>
                            </div>
                        </div>
                        <a class="dropdown-item" href="doctor_dashboard">Bảng điều khiển</a>
                        <a class="dropdown-item" href="doctor_profile_settings">Thông tin cá nhân</a>
                        <a class="dropdown-item" href="login">Đăng xuất</a>
                    </div>
                </li>
                <!-- /User Menu -->

            </ul>
        </nav>
    </header>
    <!-- /Header -->

    <!-- Breadcrumb -->
    <div class="breadcrumb-bar">
        <div class="container-fluid">
            <div class="row align-items-center">
                <div class="col-md-12 col-12">
                    <nav aria-label="breadcrumb" class="page-breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="doctor_dashboard">Trang chủ</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Hóa đơn</li>
                        </ol>
                    </nav>
                    <h2 class="breadcrumb-title">Hóa đơn</h2>
                </div>
            </div>
        </div>
    </div>
    <!-- /Breadcrumb -->

    <!-- Page Content -->
    <div class="content">
        <div class="container-fluid">

            <div class="row">
                <div class="col-md-5 col-lg-4 col-xl-3 theiaStickySidebar">

                    <!-- Profile Sidebar -->
                    <div class="profile-sidebar">
                        <div class="widget-profile pro-widget-content">
                            <div class="profile-info-widget">
                                <a href="#" class="booking-doc-img">
                                    <img src="${sessionScope.doctor.url}">
                                </a>
                                <div class="profile-det-info">
                                    <h3> ${sessionScope.doctor.name}</h3>

                                    <div class="patient-details">
                                        <h5 class="mb-0">${sessionScope.doctor.specialty}</h5>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="dashboard-widget">
                            <nav class="dashboard-menu">
                                <ul>
                                    <li>
                                        <a href="doctor_dashboard">
                                            <i class="fas fa-columns"></i>
                                            <span>Bảng điều khiển</span>
                                        </a>
                                    </li>
                                    <li class="active">
                                        <a href="doctor_appointments">
                                            <i class="fas fa-calendar-check"></i>
                                            <span>Cuộc hẹn</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="my_patients">
                                            <i class="fas fa-user-injured"></i>
                                            <span>Bệnh nhân của tôi</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="doctor_schedule_timings">
                                            <i class="fas fa-hourglass-start"></i>
                                            <span>Lịch làm việc</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="invoice_doctor">
                                            <i class="fas fa-file-invoice"></i>
                                            <span>Hóa đơn</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="doctor_profile_settings">
                                            <i class="fas fa-user-cog"></i>
                                            <span>Thông tin cá nhân</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="doctor_change_password">
                                            <i class="fas fa-lock"></i>
                                            <span>Thay đổi mật khẩu</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="login">
                                            <i class="fas fa-sign-out-alt"></i>
                                            <span>Đăng xuất</span>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                    <!-- /Profile Sidebar -->
                </div>
                <div class="col-md-7 col-lg-8 col-xl-9">
                    <div class="card card-table">
                        <div class="card-body">

                            <!-- Invoice Table -->
                            <div class="table-responsive">
                                <table class="table table-hover table-center mb-0">
                                    <thead>
                                    <tr>
                                        <th>Mã cuộc hẹn</th>
                                        <th>Bệnh nhân</th>
                                        <th>Ngày khám</th>
                                        <th>Bác sĩ</th>
                                        <th>Trạng thái</th>
                                        <th>Thanh toán</th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:if test="${ not empty requestScope.bookingList}">
                                        <c:forEach items="${requestScope.bookingList}" var="bl">
                                            <tr>
                                                <td>
                                                    <a href="#">${bl.booking.id}</a>
                                                </td>
                                                <td>
                                                    <h2 class="table-avatar">
                                                        <a href="patient_profile?id=${bl.booking.patient.id}"
                                                           class="avatar avatar-sm mr-2">
                                                            <img class="avatar-img rounded-circle"
                                                                 src="${bl.booking.patient.url}">
                                                        </a>
                                                        <a href="patient_profile?id=${bl.booking.patient.id}">${bl.booking.patient.name}</a>
                                                    </h2>
                                                </td>
                                                <td>${bl.booking.date}</td>
                                                <td>
                                                    <h2 class="table-avatar">
                                                        <a href="doctor_profile?id=${bl.booking.doctor.id}"
                                                           class="avatar avatar-sm mr-2">
                                                            <img class="avatar-img rounded-circle"
                                                                 src="${bl.booking.doctor.url}">
                                                        </a>
                                                        <a href="doctor_profile?id=${bl.booking.doctor.id}">${bl.booking.doctor.name}</a>
                                                    </h2>
                                                </td>
                                                <td>
            <span class="badge badge-pill bg-${bl.booking.status eq 'Confirmed' ? 'success-light' : bl.booking.status eq 'Pending' ? 'warning-light' : bl.booking.status eq 'Cancelled' ? 'danger-light' : bl.booking.status eq 'Completed' ? 'info-light' : ''}">
                    ${bl.booking.status}
            </span>
                                                </td>
                                                <td>${bl.bill.totalPrice} VND</td>
                                                <td class="text-right">
                                                    <div class="table-action">
                                                        <c:choose>
                                                            <c:when test="${bl.booking.status eq 'Confirmed'}">
                                                                <a href="medical_record_details?bid=${bl.booking.id}"
                                                                   class="btn btn-sm bg-info-light">
                                                                    <i class="fas fa-check"></i> Tạo hồ sơ bệnh án
                                                                </a>
                                                                <a href="doctor_appointments?id=${bl.booking.id}&status=Cancelled"
                                                                   class="btn btn-sm bg-danger-light">
                                                                    <i class="far fa-trash-alt"></i> Hủy
                                                                </a>
                                                            </c:when>
                                                            <c:when test="${bl.booking.status eq 'Completed'}">
                                                                <a href="medical_record_details?mid=${bl.id}"
                                                                   class="btn btn-sm bg-info-light">
                                                                    <i class="far fa-eye"></i> Xem hồ sơ bệnh án
                                                                </a>
                                                            </c:when>
                                                        </c:choose>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                    </tbody>
                                </table>
                            </div>
                            <!-- /Invoice Table -->

                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>
    <!-- /Page Content -->

    <!-- Footer -->
    <footer class="footer">

        <!-- Footer Top -->
        <div class="footer-top">
            <div class="container-fluid">
                <div class="row">

                </div>
            </div>
        </div>
        <!-- /Footer Top -->

    </footer>
    <!-- /Footer -->

</div>
<!-- /Main Wrapper -->

<!-- jQuery -->
<script src="assets/js/jquery.min.js"></script>

<!-- Bootstrap Core JS -->
<script src="assets/js/popper.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>

<!-- Sticky Sidebar JS -->
<script src="assets/plugins/theia-sticky-sidebar/ResizeSensor.js"></script>
<script src="assets/plugins/theia-sticky-sidebar/theia-sticky-sidebar.js"></script>

<!-- Custom JS -->
<script src="assets/js/script.js"></script>

</body>
</html>