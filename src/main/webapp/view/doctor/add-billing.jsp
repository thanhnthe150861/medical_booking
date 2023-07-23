<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
		<meta charset="utf-8">
		<title>Doccure</title>
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
	<script src="js/script.js" defer></script>
	
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
								<p class="contact-info-header"> +08669999999</p>
							</div>
						</li>
						
						<!-- User Menu -->
						<c:if test="${sessionScope.account.isAdmin == 0}">
							<li class="nav-item dropdown has-arrow logged-item">
								<a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown">
								<span class="user-img">
									<img class="rounded-circle" src="view/admin/assets/img/profiles/avatar-01.jpgg" width="31" alt="Admin">
								</span>
								</a>
								<div class="dropdown-menu dropdown-menu-right">
									<div class="user-header">
										<div class="avatar avatar-sm">
											<img src="view/admin/assets/img/profiles/avatar-01.jpg" alt="User Image" class="avatar-img rounded-circle">
										</div>
										<div class="user-text">
											<h6>Quản lý</h6>
										</div>
									</div>
									<a class="dropdown-item" href="doctor_dashboard">Bảng điều khiển</a>
									<a class="dropdown-item" href="doctor_profile_settings">Sửa hồ sơ</a>
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
											<img src="${sessionScope.bills.booking.doctor.url}" alt="User Image" class="avatar-img rounded-circle">
										</div>
										<div class="user-text">
											<h6>${sessionScope.bills.booking.doctor.name}</h6>
											<p class="text-muted mb-0">${sessionScope.bills.booking.doctor.ranks.name}</p>
										</div>
									</div>
									<a class="dropdown-item" href="doctor_dashboard">Bảng điều khiển</a>
									<a class="dropdown-item" href="doctor_profile_settings">Sửa hồ sơ</a>
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
											<img src="${sessionScope.patient.url}" alt="User Image"
												 class="avatar-img rounded-circle">
										</div>
										<div class="user-text">
											<h6>${sessionScope.patient.name}</h6>
											<p class="text-muted mb-0">Rank: ${sessionScope.patient.ranks.name}</p>
										</div>
									</div>
									<a class="dropdown-item" href="doctor_dashboard">Bảng điều khiển</a>
									<a class="dropdown-item" href="doctor_profile_settings">Sửa hồ sơ</a>
									<a class="dropdown-item" href="login">Đăng xuất</a>
								</div>
							</li>
						</c:if>
						<c:if test="${sessionScope.account.isAdmin == 3}">
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
									<li class="breadcrumb-item"><a href="doctor_dashboard">Bảng điều khiển</a></li>
									<li class="breadcrumb-item active" aria-current="page">Chi tiết hóa đơn</li>
								</ol>
							</nav>
							<h2 class="breadcrumb-title">Chi tiết hóa đơn</h2>
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
												<img src="assets/img/patients/patient.jpg" alt="User Image">
											</a>
											<div class="profile-det-info">
												<h3>${sessionScope.bills.booking.patient.name}</h3>

												<div class="patient-details">
													<h5><b>Mã bệnh nhân:</b> ${sessionScope.bills.booking.patient.id}</h5>
													<h5 class="mb-0"><i class="fas fa-birthday-cake"></i> ${sessionScope.bills.booking.patient.dob}</h5>
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
									<h4 class="card-title mb-0">Chi tiết hóa đơn</h4>
								</div>
								<% String messSuccess = (String) request.getAttribute("messSuccess"); %>
								<% if (messSuccess != null && !messSuccess.isEmpty()) { %>
								<div class="alert alert-success" role="alert">
									<%= messSuccess %>
								</div>
								<% } %>
								<div class="card-body">
									<form action="invoice_details" method="post">
									<div class="row">
										<div class="col-sm-6">
											<div class="biller-info">
												<h4 class="d-block">${sessionScope.bills.booking.doctor.name}</h4>
												<span class="d-block text-sm text-muted">${sessionScope.bills.booking.doctor.specialty}</span>
												<span class="d-block text-sm text-muted">${sessionScope.bills.booking.doctor.ranks.name}</span>
											</div>
										</div>
										<div class="col-sm-6 text-sm-right">
											<div class="billing-info">
												<h4 class="d-block">
														<p>Ngày đặt lịch: ${sessionScope.bills.booking.date}</p>
												</h4>
												<span class="d-block text-muted">
														<p>Mã đặt lịch: ${sessionScope.bills.booking.id}</p>
													</span>
											</div>
										</div>
									</div>
									
									<!-- Billing Item -->
									<div class="card card-table">
										<div class="card-body">
											<div class="table-responsive">
												<table class="table table-hover table-center">
													<thead>
														<tr>
															<th style="min-width:150px;">Giá y tế</th>
															<th style="min-width:150px;">Đơn giá (nếu có)</th>
															<th style="min-width:100px;">Tổng cộng</th>
															<th style="min-width:50px;">Trạng thái</th>
														</tr>
													</thead>
													<tbody>
														<tr>
															<input type="text" hidden="hidden" class="form-control" name="mid" value="${sessionScope.mid}">
															<input type="text" hidden="hidden" class="form-control" name="bid" value="${sessionScope.bid}">
															<td>
																<input type="number" min="0" class="form-control" name="priceMedical" id="priceMedical" value="${sessionScope.bills.bill.priceMedical}" >
															</td>
															<td>
																<input type="number" min="0" class="form-control" name="pricePrescription" id="pricePrescription" value="${sessionScope.bills.bill.pricePrescription}">
															</td>
															<td>
																<input type="number" min="0" class="form-control" name="totalPrice" id="totalPrice" value="${sessionScope.bills.bill.priceMedical + sessionScope.bills.bill.pricePrescription}" readonly>
															</td>
														<td class="text-center">
															<select name="status">
																<option ${sessionScope.bills.bill.payment_status == "Paid" ? "selected" : ""} value="Paid">Paid</option>
																<option ${sessionScope.bills.bill.payment_status == "Unpaid" ? "selected" : ""} value="Unpaid">Unpaid</option>
															</select>
														</td>
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
												<button type="submit" class="btn btn-primary submit-btn">Lưu</button>
												<button type="reset" class="btn btn-secondary submit-btn">Xóa</button>
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
									<h2 class="footer-title">Cho bác sĩ</h2>
									<ul>
										<li><a href="doctor_dashboard"><i class="fas fa-angle-double-right"></i> Bảng điều khiển</a></li>
										<li><a href="doctor_appointments"><i class="fas fa-angle-double-right"></i> Lịch hẹn</a></li>
										<li><a href="my_patients"><i class="fas fa-angle-double-right"></i> Bệnh nhân</a></li>
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
					
						<!-- Copyright -->
						<div class="copyright">
							<div class="row">
								<div class="col-md-6 col-lg-6">
									<div class="copyright-text">
										<p class="mb-0">Medical Clinic-TATQ</p>
									</div>
								</div>
								<div class="col-md-6 col-lg-6">
								
									<!-- Copyright Menu -->
									<div class="copyright-menu">
										<ul class="policy-menu">
											<li><a href="term-condition.html"></a></li>
											<li><a href="privacy-policy.html"></a></li>
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
		
		<!-- Sticky Sidebar JS -->
        <script src="assets/plugins/theia-sticky-sidebar/ResizeSensor.js"></script>
        <script src="assets/plugins/theia-sticky-sidebar/theia-sticky-sidebar.js"></script>
		
		<!-- Custom JS -->
		<script src="assets/js/script.js"></script>

		
	</body>

<!-- doccure/add-billing.jsp  30 Nov 2019 04:12:37 GMT -->
</html>