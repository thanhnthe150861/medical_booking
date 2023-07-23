<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!-- doccure/patient-change-password.jsp  30 Nov 2019 04:12:18 GMT -->
<head>
    <meta charset="utf-8">
    <title>Clinic TQTA</title>
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
                        <a href="home">Home</a>
                    </li>
                    <li>
                        <a href="booking">Đặt lịch</a>
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
									<img class="rounded-circle" src="assets/img/patients/patient.jpg" width="31" alt="Ryan Taylor">
								</span>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right">
                        <div class="user-header">
                            <div class="avatar avatar-sm">
                                <img src="assets/img/patients/patient.jpg" alt="User Image" class="avatar-img rounded-circle">
                            </div>
                            <div class="user-text">
                                <h6>${sessionScope.patient.name}</h6>
                                <p class="text-muted mb-0">Rank: ${sessionScope.patient.ranks.name}</p>
                            </div>
                        </div>
                        <a class="dropdown-item" href="patient_dashboard">Bảng điều khiển</a>
                        <a class="dropdown-item" href="patient_profile_settings">Cài đặt </a>
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
                            <li class="breadcrumb-item"><a href="home">Clinic TQTA</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Thay đổi mật khẩu</li>
                        </ol>
                    </nav>
                    <h2 class="breadcrumb-title">Thay đổi mật khẩu</h2>
                </div>
            </div>
        </div>
    </div>
    <!-- /Breadcrumb -->

    <!-- Page Content -->
    <div class="content">
        <div class="container-fluid">

            <div class="row">

                <!-- Profile Sidebar -->
                <div class="col-md-5 col-lg-4 col-xl-3 theiaStickySidebar">
                    <div class="profile-sidebar">
                        <div class="widget-profile pro-widget-content">
                            <div class="profile-info-widget">
                                <a href="#" class="booking-doc-img">
                                    <img src="assets/img/patients/patient.jpg" alt="User Image">
                                </a>
                                <div class="profile-det-info">
                                    <h3>${sessionScope.patient.name}</h3>
                                    <div class="patient-details">
                                        <h5><i class="fas fa-birthday-cake"></i> ${sessionScope.patient.dob}</h5>
                                        <h5>
                                            <i class="fas fa-${sessionScope.patient.gender eq 'Male' ? 'mars' : 'venus'}"></i>${sessionScope.patient.gender}
                                        </h5>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="dashboard-widget">
                            <nav class="dashboard-menu">
                                <ul>
                                    <li>
                                        <a href="patient_dashboard">
                                            <i class="fas fa-columns"></i>
                                            <span>Bảng điều khiển</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="my_doctor">
                                            <i class="fas fa-user-md"></i>
                                            <span>Bác sĩ của tôi</span>
                                        </a>
                                    </li>
                                    <li class="active">
                                        <a href="invoice_patient">
                                            <i class="fas fa-file-invoice"></i>
                                            <span>Hóa đơn</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="patient_profile_settings">
                                            <i class="fas fa-user-cog"></i>
                                            <span>Thông tin cá nhân</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="patient_change_password">
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
                </div>
                <!-- / Profile Sidebar -->
                <div class="col-md-7 col-lg-8 col-xl-9">
                    <div class="card">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-12 col-lg-6">

                                    <% String errorMessage = (String) request.getAttribute("messError"); %>
                                    <% if (errorMessage != null && !errorMessage.isEmpty()) { %>
                                    <div class="alert alert-danger" role="alert">
                                        <%= errorMessage %>
                                    </div>
                                    <% } %>
                                    <% String messSuccess = (String) request.getAttribute("messSuccess"); %>
                                    <% if (messSuccess != null && !messSuccess.isEmpty()) { %>
                                    <div class="alert alert-success" role="alert">
                                        <%= messSuccess %>
                                    </div>
                                    <% } %>

                                    <!-- Change Password Form -->
                                    <form action="patient_change_password" method="post">
                                        <div class="form-group">
                                            <label>Mật khẩu cũ</label>
                                            <input type="password" class="form-control" name="old-password">
                                        </div>
                                        <div class="form-group">
                                            <label>Mật khẩu mới</label>
                                            <input type="password" class="form-control" name="new-password">
                                        </div>
                                        <div class="form-group">
                                            <label>Xác nhận mật khẩu mới</label>
                                            <input type="password" class="form-control" name="re-password">
                                        </div>
                                        <div class="submit-section">
                                            <button type="submit" class="btn btn-primary submit-btn">Lưu
                                            </button>
                                        </div>
                                    </form>
                                    <!-- /Change Password Form -->
                                </div>
                            </div>
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

<!-- doccure/patient-change-password.jsp  30 Nov 2019 04:12:18 GMT -->
</html>