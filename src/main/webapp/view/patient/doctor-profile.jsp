<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
		
		<!-- Fancybox CSS -->
		<link rel="stylesheet" href="assets/plugins/fancybox/jquery.fancybox.min.css">
		
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
									<img class="rounded-circle" src="${sessionScope.patient.url}" width="31"
                                         alt="Ryan Taylor">
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
                                <a class="dropdown-item" href="patient_dashboard">Bảng điều khiển</a>
                                        <a class="dropdown-item" href="patient_profile_settings">Cài đặt </a>
                                        <a class="dropdown-item" href="login">Đăng xuất</a>
                                    </div>
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
                                    <li class="breadcrumb-item active" aria-current="page">Thông tin bác sĩ</li>
                                </ol>
                            </nav>
                            <h2 class="breadcrumb-title">Thông tin bác sĩ</h2>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /Breadcrumb -->
			
			<!-- Page Content -->
			<div class="content">
				<div class="container">

					<!-- Doctor Widget -->
					<div class="card">
						<div class="card-body">
							<div class="doctor-widget">
								<div class="doc-info-left">
									<div class="doctor-img">
										<img src="${sessionScope.doctor.url}" class="img-fluid" alt="User Image">
									</div>
									<div class="doc-info-cont">
										<h4 class="doc-name">${sessionScope.doctor.name}</h4>
										<p class="doc-speciality">${sessionScope.doctor.specialty}</p>
									</div>
								</div>
								<div class="doc-info-right">
									<div class="clini-infos">
										<ul>
											<li>
												<i class="fas fa-birthday-cake"></i>${sessionScope.doctor.dob}
											</li>
											<li>
												<i class="fas fa-${sessionScope.doctor.gender eq 'Male' ? 'mars' : 'venus'}"></i>${sessionScope.doctor.gender}
											</li>
											<li>
												<i class="fas fa-medal"></i>${sessionScope.doctor.ranks.name}
											</li>
										</ul>
									</div>
									<div class="clinic-booking">
										<a class="apt-btn" href="booking_again?did=${sessionScope.doctor.id}">Đặt lịch hẹn</a>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- /Doctor Widget -->
								
							</div>
						</div>
					</div>
					<!-- /Doctor Details Tab -->

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

            <!-- Doctor Widget -->
            <div class="card">
                <div class="card-body">
                    <div class="doctor-widget">
                        <div class="doc-info-left">
                            <div class="doctor-img">
                                <img src="${sessionScope.doctor.url}" class="img-fluid" alt="User Image">
                            </div>
                            <div class="doc-info-cont">
                                <h4 class="doc-name">${sessionScope.doctor.name}</h4>
                                <p class="doc-speciality">${sessionScope.doctor.specialty}</p>
                            </div>
                        </div>
                        <div class="doc-info-right">
                            <div class="clini-infos">
                                <ul>
                                    <li>
                                        <i class="fas fa-birthday-cake"></i>${sessionScope.doctor.dob}
                                    </li>
                                    <li>
                                        <i class="fas fa-${sessionScope.doctor.gender eq 'Male' ? 'mars' : 'venus'}"></i>${sessionScope.doctor.gender}
                                    </li>
                                    <li>
                                        <i class="fas fa-medal"></i>${sessionScope.doctor.ranks.name}
                                    </li>
                                </ul>
                            </div>
                            <div class="clinic-booking">
                                <a class="apt-btn" href="booking_again?did=${sessionScope.doctor.id}">Book
                                    Appointment</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /Doctor Widget -->

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
                            <div class="footer-logo">
                                <img src="assets/img/footer-logo.png" alt="logo">
                            </div>
                            <div class="footer-about-content">
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
                                    incididunt ut labore et dolore magna aliqua. </p>
                                <div class="social-icon">
                                    <ul>
                                        <li>
                                            <a href="#" target="_blank"><i class="fab fa-facebook-f"></i> </a>
                                        </li>
                                        <li>
                                            <a href="#" target="_blank"><i class="fab fa-twitter"></i> </a>
                                        </li>
                                        <li>
                                            <a href="#" target="_blank"><i class="fab fa-linkedin-in"></i></a>
                                        </li>
                                        <li>
                                            <a href="#" target="_blank"><i class="fab fa-instagram"></i></a>
                                        </li>
                                        <li>
                                            <a href="#" target="_blank"><i class="fab fa-dribbble"></i> </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <!-- /Footer Widget -->

                    </div>

                    <div class="col-lg-3 col-md-6">

                        <!-- Footer Widget -->
                        <div class="footer-widget footer-menu">
                            <h2 class="footer-title">For Patients</h2>
                            <ul>
                                <li><a href="search.html"><i class="fas fa-angle-double-right"></i> Search for
                                    Doctors</a></li>
                                <li><a href="login.html"><i class="fas fa-angle-double-right"></i> Login</a></li>
                                <li><a href="register.html"><i class="fas fa-angle-double-right"></i> Register</a></li>
                                <li><a href="booking.html"><i class="fas fa-angle-double-right"></i> Booking</a></li>
                                <li><a href="patient-dashboard.html"><i class="fas fa-angle-double-right"></i> Patient
                                    Dashboard</a></li>
                            </ul>
                        </div>
                        <!-- /Footer Widget -->

                    </div>

                    <div class="col-lg-3 col-md-6">

                        <!-- Footer Widget -->
                        <div class="footer-widget footer-menu">
                            <h2 class="footer-title">For Doctors</h2>
                            <ul>
                                <li><a href="appointments.jsp"><i class="fas fa-angle-double-right"></i>
                                    Appointments</a></li>
                                <li><a href="chat.html"><i class="fas fa-angle-double-right"></i> Chat</a></li>
                                <li><a href="login.html"><i class="fas fa-angle-double-right"></i> Login</a></li>
                                <li><a href="doctor-register.html"><i class="fas fa-angle-double-right"></i>
                                    Register</a></li>
                                <li><a href="../doctor/doctor-dashboard.jsp"><i class="fas fa-angle-double-right"></i>
                                    Doctor Dashboard</a></li>
                            </ul>
                        </div>
                        <!-- /Footer Widget -->

                    </div>

                    <div class="col-lg-3 col-md-6">

                        <!-- Footer Widget -->
                        <div class="footer-widget footer-contact">
                            <h2 class="footer-title">Contact Us</h2>
                            <div class="footer-contact-info">
                                <div class="footer-address">
                                    <span><i class="fas fa-map-marker-alt"></i></span>
                                    <p> 3556 Beech Street, San Francisco,<br> California, CA 94108 </p>
                                </div>
                                <p>
                                    <i class="fas fa-phone-alt"></i>
                                    +1 315 369 5943
                                </p>
                                <p class="mb-0">
                                    <i class="fas fa-envelope"></i>
                                    doccure@example.com
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

<!-- Fancybox JS -->
<script src="assets/plugins/fancybox/jquery.fancybox.min.js"></script>

<!-- Custom JS -->
<script src="assets/js/script.js"></script>

</body>

</html>