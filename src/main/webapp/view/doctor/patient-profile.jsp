<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
	<title>Clinic TQTA</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">


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
                        <a href="doctor_dashboard">Bảng điều khiển</a>
                    </li>
                </ul>
            </div>
            <ul class="nav header-navbar-rht">
                <li class="nav-item contact-item">
                    <div class="header-contact-img">
                        <i class="far fa-hospital"></i>
                    </div>
                    <div class="header-contact-detail">
                        <p class="contact-header">liên hệ</p>
                        <p class="contact-info-header"> +84 868746275</p>
                    </div>
                </li>

                <!-- User Menu -->
                <li class="nav-item dropdown has-arrow logged-item">
                    <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown">
								<span class="user-img">
									<img class="rounded-circle" src="${sessionScope.doctor.url}" width="31"
                                         alt="Darren Elder">
								</span>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right">
                        <div class="user-header">
                            <div class="avatar avatar-sm">
                                <img src="${sessionScope.doctor.url}" alt="User Image"
                                     class="avatar-img rounded-circle">
                            </div>
                            <div class="user-text">
                                <h6>${sessionScope.doctor.name}</h6>
                                <p class="text-muted mb-0">${sessionScope.doctor.ranks.name}</p>
                            </div>
                        </div>
                        <a class="dropdown-item" href="doctor_dashboard">bảng điều khiển</a>
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
                            <li class="breadcrumb-item active" aria-current="page">Hồ sơ</li>
                        </ol>
                    </nav>
                    <h2 class="breadcrumb-title">Hồ sơ</h2>
                </div>
            </div>
        </div>
    </div>
    <!-- /Breadcrumb -->

    <!-- Page Content -->
    <div class="content">
        <div class="container-fluid">

            <div class="row">
                <div class="col-md-5 col-lg-4 col-xl-3 theiaStickySidebar dct-dashbd-lft">

                    <!-- Profile Widget -->
                    <div class="card widget-profile pat-widget-profile">
                        <div class="card-body">
                            <div class="pro-widget-content">
                                <div class="profile-info-widget">
                                    <a href="#" class="booking-doc-img">
                                        <img src="${sessionScope.patient.url}" alt="User Image">
                                    </a>
                                    <div class="profile-det-info">
                                        <h3>${sessionScope.patient.name}</h3>

                                        <div class="patient-details">
                                            <h5><b>Patient ID :</b> ${sessionScope.patient.id}</h5>
                                            <h5 class="mb-0"><i
                                                    class="fas fa-birthday-cake"></i> ${sessionScope.patient.dob}</h5>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /Profile Widget -->

                </div>

                <div class="col-md-7 col-lg-8 col-xl-9 dct-appoinment">
                    <div class="card">
                        <div class="card-body pt-0">
                            <div class="user-tabs">
                                <ul class="nav nav-tabs nav-tabs-bottom nav-justified flex-wrap">
                                    <li class="nav-item">
                                        <a class="nav-link active" href="patient_profile?id=${sessionScope.patient.id}">Cuộc hẹn</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link"
                                           href="patient_profile?id=${sessionScope.patient.id}&medical=true"><span
                                                class="med-records">Hồ sơ bệnh án</span></a>
                                    </li>
                                </ul>
                            </div>
                            <div class="tab-content">
                                <!-- Appointment Tab -->
                                <div id="pat_appointments" class="tab-pane fade show active">
                                    <div class="card card-table mb-0">
                                        <div class="card-body">
                                            <div class="table-responsive">
                                                <table class="table table-hover table-center mb-0">
                                                    <thead>
                                                    <tr>
                                                        <th>Bác sĩ</th>
																	<th></th>
																	<th>Ngày đặt lịch</th>
																	<th></th>
																	<th></th>
																	<th>Trạng thái </th>
																	<th></th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach items="${requestScope.medicalRecordList}" var="m">
                                                        <tr>
                                                            <td>
                                                                <h2 class="table-avatar">
                                                                    <a href="#" class="avatar avatar-sm mr-2">
                                                                        <img class="avatar-img rounded-circle"
                                                                             src="${m.booking.doctor.url}"
                                                                             alt="User Image">
                                                                    </a>
                                                                    <a href="#">${m.booking.doctor.name}
                                                                        <span>${m.booking.doctor.specialty}</span></a>
                                                                </h2>
                                                            </td>
                                                            <td></td>
                                                            <td>${m.booking.date} <span
                                                                    class="d-block text-info">${m.booking.slots.name} </span>
                                                            </td>
                                                            <td></td>
                                                            <td></td>
                                                            <td>
																		<span class="badge badge-pill bg-${m.booking.status eq 'Confirmed' ? 'success-light' : m.booking.status eq 'Pending' ? 'warning-light' : m.booking.status eq 'Cancelled' ? 'danger-light' : m.booking.status eq 'Completed' ? 'info-light' : ''}">
                                                                                ${m.booking.status}
                                                                        </span>
                                                            </td>
                                                            <td class="text-right">
                                                                <div class="table-action">
                                                                    <c:if test="${m.booking.status eq 'Confirmed'}">
                                                                        <a href="medical_record_details?bid=${m.booking.id}"
                                                                           class="btn btn-sm bg-info-light">
                                                                            <i class="fas fa-check"></i> Tạo hồ sơ bệnh
                                                                            án
                                                                        </a>
                                                                        <a href="patient_profile?id=${sessionScope.patient.id}&bid=${m.booking.id}&status=Cancelled"
                                                                           class="btn btn-sm bg-danger-light">
                                                                            <i class="far fa-trash-alt"></i> Hủy
                                                                        </a>
                                                                    </c:if>

                                                                    <c:if test="${m.booking.status eq 'Completed'}">
                                                                        <a href="medical_record_details?mid=${m.id}"
                                                                           class="btn btn-sm bg-info-light">
                                                                            <i class="far fa-eye"></i> Xem hồ sơ bệnh án
                                                                        </a>
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
                                </div>
                                <!-- /Appointment Tab -->
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
                            <h2 class="footer-title">Dành cho bác sĩ</h2>
                            <ul>
                                <li><a href="doctor_appointments"><i class="fas fa-angle-double-right"></i> Cuộc hẹn</a>
                                </li>
                                <li><a href="doctor_dashboard"><i class="fas fa-angle-double-right"></i> Bảng điều khuyển</a></li>
                            </ul>
                        </div>
                        <!-- /Footer Widget -->

                    </div>

                    <div class="col-lg-3 col-md-6">

                        <!-- Footer Widget -->
                        <div class="footer-widget footer-contact">
                            <h2 class="footer-title">Liên hệ</h2>
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

                <!-- Copyright -->
                <div class="copyright">
                    <div class="row">
                        <div class="col-md-6 col-lg-6">
                            <div class="copyright-text">
                                <p class="mb-0"><a href="templateshub.net">Templates Hub</a></p>
                            </div>
                        </div>
                        <div class="col-md-6 col-lg-6">

                            <!-- Copyright Menu -->
                            <div class="copyright-menu">
                                <ul class="policy-menu">
                                    <li><a href="term-condition.html">Terms and Conditions</a></li>
                                    <li><a href="privacy-policy.html">Policy</a></li>
                                </ul>
                            </div>
                            <!-- /Copyright Menu -->

                        </div>
                    </div>
                </div>
                <!-- /Copyright -->

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

<!-- Datetimepicker JS -->
<script src="assets/js/moment.min.js"></script>
<script src="assets/js/bootstrap-datetimepicker.min.js"></script>

<!-- Sticky Sidebar JS -->
<script src="assets/plugins/theia-sticky-sidebar/ResizeSensor.js"></script>
<script src="assets/plugins/theia-sticky-sidebar/theia-sticky-sidebar.js"></script>

<!-- Custom JS -->
<script src="assets/js/script.js"></script>

</body>

<!-- doccure/patient-profile.jsp  30 Nov 2019 04:12:13 GMT -->
</html>