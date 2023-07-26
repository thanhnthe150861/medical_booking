<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <a href="home" class="navbar-brand logo">
                    <span class="text-primary">Clinic</span>-TATQ
                </a>
            </div>
            <div class="main-menu-wrapper">
                <div class="menu-header">
                    <a href="home" class="menu-logo">
                        <span class="text-primary" width="50" height="50">Clinic</span>
                    </a>
                    <a id="menu_close" class="menu-close" href="javascript:void(0);">
                        <i class="fas fa-times"></i>
                    </a>
                </div>
                <ul class="main-nav">
                    <li>
                        <a href="home">Trang chủ</a>
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
                <c:if test="${sessionScope.account.isAdmin == 0}">
                    <li class="nav-item dropdown has-arrow logged-item">
                        <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown">
            <span class="user-img">
                <img class="rounded-circle" src="view/admin/assets/img/profiles/avatar-01.jpg" width="31">
            </span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right">
                            <div class="user-header">
                                <div class="avatar avatar-sm">
                                    <img src="view/admin/assets/img/profiles/avatar-01.jpg"
                                         class="avatar-img rounded-circle">
                                </div>
                                <div class="user-text">
                                    <h6>Bác sĩ</h6>
                                </div>
                            </div>
                            <a class="dropdown-item" href="admin_dashboard">Bảng điều khiển</a>
                            <a class="dropdown-item" href="profile">Thông tin cá nhân</a>
                            <a class="dropdown-item" href="login">Đăng xuất</a>
                        </div>
                    </li>
                </c:if>
                <c:if test="${sessionScope.account.isAdmin == 1}">
                    <li class="nav-item dropdown has-arrow logged-item">
                        <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown">
            <span class="user-img">
                <img class="rounded-circle" src="${sessionScope.bills.booking.doctor.url}" width="31">
            </span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right">
                            <div class="user-header">
                                <div class="avatar avatar-sm">
                                    <img src="${sessionScope.bills.booking.doctor.url}"
                                         class="avatar-img rounded-circle">
                                </div>
                                <div class="user-text">
                                    <h6>${sessionScope.bills.booking.doctor.name}</h6>
                                    <p class="text-muted mb-0">${sessionScope.bills.booking.doctor.ranks.name}</p>
                                </div>
                            </div>
                            <a class="dropdown-item" href="doctor_dashboard">Bảng điều khiển</a>
                            <a class="dropdown-item" href="doctor_profile_settings">Thông tin cá nhân</a>
                            <a class="dropdown-item" href="login">Đăng xuất</a>
                        </div>
                    </li>
                </c:if>
                <c:if test="${sessionScope.account.isAdmin == 2}">
                    <li class="nav-item dropdown has-arrow logged-item">
                        <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown">
            <span class="user-img">
                <img class="rounded-circle" src="${sessionScope.patient.url}" width="31">
            </span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right">
                            <div class="user-header">
                                <div class="avatar avatar-sm">
                                    <img src="${sessionScope.patient.url}"
                                         class="avatar-img rounded-circle">
                                </div>
                                <div class="user-text">
                                    <h6>${sessionScope.patient.name}</h6>
                                    <p class="text-muted mb-0">Rank: ${sessionScope.patient.ranks.name}</p>
                                </div>
                            </div>
                            <a class="dropdown-item" href="patient_dashboard">Bảng điều khiển</a>
                            <a class="dropdown-item" href="patient_profile_settings">Thông tin cá nhân</a>
                            <a class="dropdown-item" href="login">Đăng xuất</a>
                        </div>
                    </li>
                </c:if>
                <c:if test="${sessionScope.account.isAdmin == 3}">
                    <li class="nav-item dropdown has-arrow logged-item">
                        <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown">
            <span class="user-img">
                <img class="rounded-circle" src="${sessionScope.staff.url}" width="31">
            </span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right">
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
                </c:if>
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
                            <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Xem hóa đơn</li>
                        </ol>
                    </nav>
                    <h2 class="breadcrumb-title">Xem hóa đơn</h2>
                </div>
            </div>
        </div>
    </div>
    <!-- /Breadcrumb -->

    <!-- Page Content -->
    <div class="content">
        <div class="container-fluid">

            <div class="row">
                <div class="col-lg-8 offset-lg-2">
                    <div class="invoice-content">
                        <div class="invoice-item">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="invoice-logo">
                                        <span style="font-size: 40px" class="text-primary">Clinic</span><span
                                            style="font-size: 40px">-TATQ</span>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <p class="invoice-details">
                                        <strong>Mã lịch đặt:</strong> ${requestScope.bill.bill.id}<br>
                                        <strong>Ngày tạo đơn:</strong> ${requestScope.bill.booking.date}
                                    </p>
                                </div>
                            </div>
                        </div>

                        <!-- Invoice Item -->
                        <div class="invoice-item">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="invoice-info">
                                        <strong class="customer-text">Bác sĩ khám</strong>
                                        <p class="invoice-details invoice-details-two">
                                            ${requestScope.bill.booking.doctor.name} <br>
                                            ${requestScope.bill.booking.doctor.specialty} <br>
                                        </p>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="invoice-info invoice-info2">
                                        <strong class="customer-text">Người khám</strong>
                                        <p class="invoice-details">
                                            ${requestScope.bill.booking.patient.name} <br>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /Invoice Item -->

                        <!-- Invoice Item -->
                        <div class="invoice-item invoice-table-wrap">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="table-responsive">
                                        <table class="invoice-table table table-bordered">
                                            <thead>
                                            <tr>
                                                <th>Dịch vụ</th>
                                                <th class="text-right">Tổng</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <td>Giá dịch vụ</td>
                                                <td class="text-right">${requestScope.bill.bill.priceMedical} VND</td>
                                            </tr>
                                            <tr>
                                                <td>Giá đơn thuốc (nếu có)</td>
                                                <td class="text-right">${requestScope.bill.bill.pricePrescription} VND
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div class="col-md-6 col-xl-4 ml-auto">
                                    <div class="table-responsive">
                                        <table class="invoice-table-two table">
                                            <tbody>
                                            <tr>
                                                <th>Tổng cộng:</th>
                                                <td><span>${requestScope.bill.bill.totalPrice} VND</span></td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /Invoice Item -->

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
                    <div class="col-lg-3 col-md-6">

                    </div>
                </div>
            </div>
        </div>
        <!-- /Footer Bottom -->

    </footer>
    <!-- /Footer -->

</div>
<!-- /Main Wrapper -->

<!-- jQuery -->
<script src="assets/js/jquery.min.js"></script>

<!-- Bootstrap Core JS -->
<script src="assets/js/popper.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>

<!-- Custom JS -->
<script src="assets/js/script.js"></script>

</body>
</html>