<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                            <li class="breadcrumb-item active" aria-current="page">Đặt lịch</li>
                        </ol>
                    </nav>
                    <h2 class="breadcrumb-title">Đặt lịch</h2>
                </div>
            </div>
        </div>
    </div>
    <!-- /Breadcrumb -->

    <!-- Page Content -->
    <div class="content">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <!-- Schedule Widget -->
                    <div class="card booking-schedule schedule-widget">
                        <!-- Schedule Header -->
                        <div class="schedule-header">
                            <div class="row">
                                <div class="col-md-12">
                                    <% String errorMessage = (String) request.getAttribute("messError"); %>
                                    <% if (errorMessage != null && !errorMessage.isEmpty()) { %>
                                    <div class="alert alert-danger" role="alert">
                                        <%= errorMessage %>
                                    </div>
                                    <% } %>
                                    <!-- Day Slot -->
                                    <div class="day-slot">
                                        <ul>
                                            <li>
                                                <form action="booking" method="GET" id="get">
                                                    <div style="display: flex; align-items: center;">
                                                        <input type="date" id="datePicker" value="${sessionScope.date}"
                                                               name="datePicker"
                                                               style="border: 2px solid #42c0fb; border-radius: 5px; padding: 5px;">
                                                        <input type="submit" form="get" value="Select Date"
                                                               style="border: 2px solid #42c0fb; border-radius: 5px; padding: 5px; margin-left: 10px">
                                                    </div>
                                                </form>
                                            </li>
                                        </ul>
                                    </div>
                                    <!-- /Day Slot -->

                                </div>
                            </div>
                        </div>
                        <!-- /Schedule Header -->
                        <form action="booking" method="post">
                            <select name="diseaseGroup" required
                                    style="font-size: 16px; padding: 10px; border-radius: 5px; border: 1px solid #ccc;">
                                <option value="" disabled selected>Bạn đến khám gì?
                                </option>
                                <c:forEach items="${sessionScope.listSp}" var="ls">
                                    <option value="${ls.id}">${ls.name}</option>
                                </c:forEach>
                            </select>
                            <!-- Schedule Content -->
                            <div class="schedule-cont">
                                <div class="row">
                                    <div class="col-md-12">
                                        <!-- Time Slot -->
                                        <div class="time-slot">
                                            <ul class="clearfix d-flex align-items-center justify-content-center">
                                                <!-- Morning -->
                                                <li>
                                                    <c:forEach items="${sessionScope.slotList}" var="sl">
                                                        <c:if test="${sl.id lt 4 && sl.id gt 0}">
                                                            <a class="timing <c:if test='${sessionScope.selectedSlot eq sl.id}'>selected</c:if>"
                                                               href="booking?datePicker=${sessionScope.date}&selectedSlot=${sl.id}">
                                                                <span>${sl.name}</span>
                                                            </a>
                                                        </c:if>
                                                    </c:forEach>
                                                </li>
                                                <!-- Afternoon -->
                                                <li>
                                                    <c:forEach items="${sessionScope.slotList}" var="sl">
                                                        <c:if test="${sl.id gt 3}">
                                                            <a class="timing <c:if test="${sessionScope.selectedSlot eq sl.id}">selected</c:if>"
                                                               href="booking?datePicker=${sessionScope.date}&selectedSlot=${sl.id}">
                                                                <span>${sl.name}</span>
                                                            </a>
                                                        </c:if>
                                                    </c:forEach>
                                                </li>
                                            </ul>
                                        </div>
                                        <!-- /Time Slot -->
                                        <div class="card"
                                             style="margin-top: 20px; box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);">
                                            <div class="card-body" style="padding: 20px;">
                                                <h2 style="font-size: 20px; color: #333; margin-top: 20px;">Mô tả tình
                                                    trạng bệnh</h2>
                                                <input required type="text" id="textReason" name="textReason"
                                                       style="width: 100%; padding: 10px; font-size: 16px; border-radius: 5px; border: 1px solid #ccc;">
                                            </div>
                                        </div>

                                        <%----%>
                                    </div>
                                </div>
                            </div>
                            <!-- /Schedule Content -->
                            <!-- Submit Section -->
                            <div class="submit-section proceed-btn text-right">
                                <button class="btn btn-primary submit-btn">Đặt lịch</button>
                            </div>
                            <!-- /Submit Section -->
                        </form>
                    </div>
                    <!-- /Schedule Widget -->
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

                        <!-- Footer Widget -->
                        <div class="footer-widget footer-about">
                            <a href="home" class="navbar-brand logo">
                                <span class="text-primary">Clinic</span>-TATQ
                            </a>
                        </div>
                        <!-- /Footer Widget -->
                    </div>

                    <div class="col-lg-3 col-md-6">
                        <!-- Footer Widget -->
                        <div class="footer-widget footer-menu">
                            <h2 class="footer-title">Đối với bệnh nhân</h2>
                            <ul>
                                <li><a href="my_doctor"><i class="fas fa-angle-double-right"></i>Bác sĩ của tôi</a>
                                </li>
                                <li><a href="booking"><i class="fas fa-angle-double-right"></i> Đặt lịch</a></li>
                                <li><a href="patient_dashboard"><i class="fas fa-angle-double-right"></i> Bảng điều
                                    khiển </a></li>
                            </ul>
                        </div>
                        <!-- /Footer Widget -->
                    </div>


                    <div class="col-lg-3 col-md-6">

                        <!-- Footer Widget -->
                        <div class="footer-widget footer-contact">
                            <h2 class="footer-title">Liên hệ chúng tôi</h2>
                            <div class="footer-contact-info">
                                <div class="footer-address">
                                    <span><i class="fas fa-map-marker-alt"></i></span>
                                    <p> FPT University<br> Hòa Lạc, Hà Nội </p>
                                </div>
                                <p>
                                    <i class="fas fa-phone-alt"></i>
                                    +84 868746275
                                </p>
                                <p class="mb-0">
                                    <i class="fas fa-envelope"></i>
                                    quyetlbche160252@fpt.edu.vn
                                </p>
                            </div>
                        </div>
                        <!-- /Footer Widget -->
                    </div>

                </div>
            </div>
        </div>
        <!-- /Footer Top -->

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

<!-- Custom JS -->
<script src="assets/js/script.js"></script>

</body>

</html>