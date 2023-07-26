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
                <a href="${sessionScope.account.isAdmin == 0 ? 'admin_dashboard' : sessionScope.account.isAdmin == 1 ? 'doctor_dashboard' : sessionScope.account.isAdmin == 2 ? 'home' : sessionScope.account.isAdmin == 3 ? 'staff_dashboard' : '#'}"
                   class="navbar-brand logo">
                    <span class="text-primary">Clinic</span>-TATQ
                </a>
            </div>
            <div class="main-menu-wrapper">
                <div class="menu-header">
                    <a href="${sessionScope.account.isAdmin == 0 ? 'admin_dashboard' : sessionScope.account.isAdmin == 1 ? 'doctor_dashboard' : sessionScope.account.isAdmin == 2 ? 'home' : sessionScope.account.isAdmin == 3 ? 'staff_dashboard' : '#'}"
                       class="menu-logo">
                        <span class="text-primary" width="50" height="50">Clinic</span>
                    </a>
                    <a id="menu_close" class="menu-close" href="javascript:void(0);">
                        <i class="fas fa-times"></i>
                    </a>
                </div>
                <ul class="main-nav">
                    <li>
                        <a href="${sessionScope.account.isAdmin == 0 ? 'admin_dashboard' : sessionScope.account.isAdmin == 1 ? 'doctor_dashboard' : sessionScope.account.isAdmin == 2 ? 'patient_dashboard' : sessionScope.account.isAdmin == 3 ? 'staff_dashboard' : '#'}">Trang
                            chủ</a>
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
                                    <h6>Người quản lý</h6>
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
                            <li class="breadcrumb-item"><a href="doctor_dashboard">Trang chủ</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Chi tiết hồ sơ y tế</li>
                        </ol>
                    </nav>
                    <h2 class="breadcrumb-title">Chi tiết hồ sơ y tế</h2>
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

                    <!-- Profile Widget -->
                    <div class="card widget-profile pat-widget-profile">
                        <div class="card-body">
                            <div class="pro-widget-content">
                                <div class="profile-info-widget">
                                    <a href="#" class="booking-doc-img">
                                        <img src="${sessionScope.medicalRecord.booking.patient.url}">
                                    </a>
                                    <div class="profile-det-info">
                                        <h3>
                                            <a href="patient_profile?${sessionScope.medicalRecord.booking.patient.id}">
                                                ${sessionScope.medicalRecord.booking.patient.name}</a>
                                        </h3>
                                        <div class="patient-details">
                                            <h5><b>Bệnh nhân ID :</b> ${sessionScope.medicalRecord.booking.patient.id}
                                            </h5>
                                            <h5 class="mb-0"><i
                                                    class="fas fa-birthday-cake"></i> ${sessionScope.medicalRecord.booking.patient.dob}
                                            </h5>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /Profile Widget -->

                </div>

                <div class="col-md-7 col-lg-8 col-xl-9">
                    <div class="card">
                        <div class="card-header">
                            <h4 class="card-title mb-0">Chi tiết hồ sơ y tế</h4>
                        </div>
                        <% String messSuccess = (String) request.getAttribute("messSuccess"); %>
                        <% if (messSuccess != null && !messSuccess.isEmpty()) { %>
                        <div class="alert alert-success" role="alert">
                            <%= messSuccess %>
                        </div>
                        <% } %>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="biller-info">
                                        <h4 class="d-block">${sessionScope.medicalRecord.booking.doctor.name}</h4>
                                        <span class="d-block text-sm text-muted">${sessionScope.medicalRecord.booking.doctor.specialty}</span>
                                        <span class="d-block text-sm text-muted">${sessionScope.medicalRecord.booking.doctor.ranks.name}</span>
                                    </div>
                                </div>
                                <div class="col-sm-6 text-sm-right">
                                    <div class="billing-info">
                                        <h4 class="d-block">
                                            <p>Ngày đặt lịch: ${sessionScope.medicalRecord.booking.date}</p>
                                        </h4>
                                        <span class="d-block text-muted">
														<p>Đặt lịch ID: ${sessionScope.medicalRecord.booking.id}</p>
												</span>
                                    </div>
                                </div>
                            </div>
                            <form action="medical_record_details" method="post">
                                <!-- Item -->
                                <div class="card card-table">
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table class="table table-hover table-center">
                                                <thead>
                                                <tr>
                                                    <th style="min-width:175px;">Chẩn đoán</th>
                                                    <th style="min-width:100px;">Tập tin đính kèm</th>
                                                    <th style="min-width:175px;">Đơn thuốc</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <input type="hidden" class="form-control" name="mid"
                                                           value="${sessionScope.mid}">
                                                    <input type="hidden" class="form-control" name="bid"
                                                           value="${sessionScope.bid}">
                                                    <td>
                                                        <input type="text" class="form-control" name="diagnosis"
                                                               value="${sessionScope.medicalRecord.diagnosis}" ${sessionScope.account.isAdmin == 2 ? 'readonly' : ''}>
                                                    </td>
                                                    <td>
                                                        <input type="${sessionScope.account.isAdmin == 2 ? 'text' : 'file'}"
                                                               class="form-control"
                                                               name="url" ${sessionScope.account.isAdmin == 2 ? 'readonly' : ''}>
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control" name="prescription"
                                                               value="${sessionScope.medicalRecord.prescription}" ${sessionScope.account.isAdmin == 2 ? 'readonly' : ''}>
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td></td>
                                                    <td><img src="${sessionScope.medicalRecord.url}" alt=""></td>
                                                    <td></td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <!-- /Billing Item -->

                                <!-- Submit Section -->
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="submit-section">
                                            <c:if test="${sessionScope.account.isAdmin != 2}">
                                                <button type="submit" class="btn btn-primary submit-btn">Lưu</button>
                                            </c:if>
                                            <a href="${sessionScope.account.isAdmin == 1 ? 'doctor_appointments' : sessionScope.account.isAdmin == 0 ? 'admin_dashboard' : sessionScope.account.isAdmin == 2 ? 'patient_dashboard' : sessionScope.account.isAdmin == 3 ? 'staff_appointments' : '#'}"
                                               class="btn btn-secondary submit-btn" id="cancel-btn">Quay lại</a>
                                        </div>
                                    </div>
                                </div>
                                <!-- /Submit Section -->
                            </form>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>
    <!-- /Page Content -->

    <!-- Footer -->
    <footer class="footer">

        <!-- Footer Bottom -->
        <div class="footer-bottom">
            <div class="container-fluid">

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

<!-- Sticky Sidebar JS -->
<script src="assets/plugins/theia-sticky-sidebar/ResizeSensor.js"></script>
<script src="assets/plugins/theia-sticky-sidebar/theia-sticky-sidebar.js"></script>

<!-- Custom JS -->
<script src="assets/js/script.js"></script>


</body>

</html>