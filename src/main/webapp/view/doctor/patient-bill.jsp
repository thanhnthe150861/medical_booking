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

    <!-- Datetimepicker CSS -->
    <link rel="stylesheet" href="assets/css/bootstrap-datetimepicker.min.css">

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
                        <a class="dropdown-item" href="login">Đăng kí</a>
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
                                        <img src="${sessionScope.patient.url}">
                                    </a>
                                    <div class="profile-det-info">
                                        <h3>${sessionScope.patient.name}</h3>

                                        <div class="patient-details">
                                            <h5><b>Bệnh nhân ID :</b> ${sessionScope.patient.id}</h5>
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
                                        <a class="nav-link" href="patient_profile?id=${sessionScope.patient.id}">Cuộc
                                            hẹn</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link"
                                           href="patient_profile?id=${sessionScope.patient.id}&medical=true"><span
                                                class="med-records">Hồ sơ bệnh án</span></a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link active"
                                           href="patient_profile?id=${sessionScope.patient.id}&bill=true"><span>Thanh toán</span></a>
                                    </li>
                                </ul>
                            </div>
                            <div class="tab-content">

                                <!-- Billing Tab -->
                                <div class="tab-pane fade show active" id="billing">
                                    <div class="card card-table mb-0">
                                        <div class="card-body">
                                            <div class="table-responsive">
                                                <table class="table table-hover table-center mb-0">
                                                    <thead>
                                                    <tr>
                                                        <th>Hóa đơn ID</th>
                                                        <th>Bác sĩ</th>
                                                        <th>Tổng</th>
                                                        <th>Ngày</th>
                                                        <th>Đã thanh toán</th>
                                                        <th></th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach items="${requestScope.medicalRecordList}" var="m">
                                                        <c:if test="${m.booking.status eq 'Completed' && m.id != 0}">
                                                            <tr>
                                                                <td>
                                                                    <a href="#">${m.bill.id}</a>
                                                                </td>
                                                                <td>
                                                                    <h2 class="table-avatar">
                                                                        <a href="#" class="avatar avatar-sm mr-2">
                                                                            <img class="avatar-img rounded-circle"
                                                                                 src="${m.booking.doctor.url}">
                                                                        </a>
                                                                        <a href="#">${m.booking.doctor.name}
                                                                            <span>${m.booking.doctor.specialty}</span></a>
                                                                    </h2>
                                                                </td>
                                                                <td>${m.bill.totalPrice}</td> VND
                                                                <td>${m.booking.date}</td>
                                                                <td><span
                                                                        class="badge badge-pill bg-${m.bill.payment_status eq 'Paid' ? 'success-light' : m.bill.payment_status eq 'Unpaid' ? 'danger-light' : ''}">
                                                                        ${m.bill.payment_status eq 'Paid' ? 'Đã thanh toán' : 'Chưa thanh toán' } </span>
                                                                </td>
                                                                <td class="text-right">
                                                                    <div class="table-action">
                                                                        <div class="table-action">
                                                                            <c:if test="${m.bill.id == 0}">
                                                                                <a href="invoice_details?mid=${m.id}"
                                                                                   class="btn btn-sm bg-success-light">
                                                                                    <i class="far fa-eye"></i> Chỉnh sửa
                                                                                </a>
                                                                            </c:if>
                                                                            <c:if test="${m.bill.id != 0}">
                                                                                <a href="invoice_details?bid=${m.bill.id}"
                                                                                   class="btn btn-sm bg-success-light">
                                                                                    <i class="far fa-eye"></i> Chỉnh sửa
                                                                                </a>
                                                                                <a href="invoice_view?bid=${m.bill.id}"
                                                                                   class="btn btn-sm bg-info-light">
                                                                                    <i class="far fa-eye"></i> Xem
                                                                                </a>
                                                                            </c:if>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </c:if>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- Billing Tab -->

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

    </footer>
    <!-- /Footer -->

</div>
<!-- /Main Wrapper -->

<!-- Add Medical Records Modal -->
<div class="modal fade custom-modal" id="add_medical_records">
    <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">Hồ sơ bệnh án</h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Ngày</label>
                        <input type="text" class="form-control datetimepicker" value="31-10-2019">
                    </div>
                    <div class="form-group">
                        <label>Mô tả (không bắt buộc )</label>
                        <textarea class="form-control"></textarea>
                    </div>
                    <div class="form-group">
                        <label>Tải ảnh lên</label>
                        <input type="file" class="form-control">
                    </div>
                    <div class="submit-section text-center">
                        <button type="submit" class="btn btn-primary submit-btn">Nộp</button>
                        <button type="button" class="btn btn-secondary submit-btn" data-dismiss="modal">Hủy</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- /Add Medical Records Modal -->

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
</html>