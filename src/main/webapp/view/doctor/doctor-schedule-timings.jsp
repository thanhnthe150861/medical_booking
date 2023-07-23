<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

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
		
		<!-- Select2 CSS -->
		<link rel="stylesheet" href="assets/plugins/select2/css/select2.min.css">
		
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
						<li class="nav-item dropdown has-arrow logged-item">
							<a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown">
								<span class="user-img">
									<img class="rounded-circle" src="${sessionScope.doctor.url}" width="31" alt="Darren Elder">
								</span>
							</a>
							<div class="dropdown-menu dropdown-menu-right">
								<div class="user-header">
									<div class="avatar avatar-sm">
										<img src="${sessionScope.doctor.url}" alt="User Image" class="avatar-img rounded-circle">
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
									<li class="breadcrumb-item"><a href="doctor_dashboard">Bảng điều khiển</a></li>
									<li class="breadcrumb-item active" aria-current="page">Thời gian biểu</li>
								</ol>
							</nav>
							<h2 class="breadcrumb-title">Thời gian biểu</h2>
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
											<img src="${sessionScope.doctor.url}" alt="User Image">
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
											<li class="active">
												<a href="doctor_profile_settings">
													<i class="fas fa-user-cog"></i>
													<span>Thông tin cá nhân</span>
												</a>
											</li>
											<li>
												<a href="doctor_change_password">
													<i class="fas fa-lock"></i>
													<span>Đổi mật khẩu</span>
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
						 
							<div class="row">
								<div class="col-sm-12">
									<div class="card">
										<div class="card-body">
											<h4 class="card-title">Lịch làm việc</h4>
											<div class="profile-box">
												<div class="row">

													<div class="col-lg-4">
														<form action="doctor_schedule_timings" method="post">
														<div class="form-group">
																<input type="date" id="datePicker" class="form-control"  name="datePicker" value="${date}">
														</div>
														<button class="form-control">Tìm kiếm</button>
														</form>
													</div>

												</div>     
												<div class="row">
													<div class="col-md-12">
														<div class="card schedule-widget mb-0">

															<!-- Schedule Header -->
															<div class="schedule-header">


															</div>
															<!-- /Schedule Header -->
															
															<!-- Schedule Content -->
															<div class="tab-content schedule-cont">

																<!-- Monday Slot -->
																<div id="slot_monday" class="tab-pane fade show active">
																	<h4 class="card-title d-flex justify-content-between">
																		<span>Khoảng thời gian</span>
																	</h4>

																	<!-- Slot List -->
																	<div class="doc-times">
																		<c:forEach items="${sessionScope.bookingList}" var="b">
																				<c:if test="${b.date eq date}">
																				<div class="doc-slot-list">
																					<a href="patient_profile?id=${b.patient.id}">${b.slots.name}</a>
																				</div>
																				</c:if>
																		</c:forEach>
																	</div>
																	<!-- /Slot List -->
																</div>
															</div>
															<!-- /Schedule Content -->
															
														</div>
													</div>
												</div>
											</div>
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
		
		<!-- Select2 JS -->
		<script src="assets/plugins/select2/js/select2.min.js"></script>
		
		<!-- Custom JS -->
		<script src="assets/js/script.js"></script>

	</body>

<!-- doccure/schedule-timings.html  30 Nov 2019 04:12:09 GMT -->
</html>