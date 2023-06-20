<<<<<<< HEAD
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <meta http-equiv="X-UA-Compatible" content="ie=edge">

  <meta name="copyright" content="MACode ID, https://macodeid.com/">

  <title>One Health - Medical Center HTML5 Template</title>

  <link rel="stylesheet" href="css/maicons.css">

  <link rel="stylesheet" href="css/bootstrap.css">

  <link rel="stylesheet" href="vendor/owl-carousel/css/owl.carousel.css">

  <link rel="stylesheet" href="vendor/animate/animate.css">

  <link rel="stylesheet" href="css/theme.css">
</head>
<body>

  <!-- Back to top button -->
  <div class="back-to-top"></div>

  <header>
    <div class="topbar">
      <div class="container">
        <div class="row">
          <div class="col-sm-8 text-sm">
            <div class="site-info">
              <a href="#"><span class="mai-call text-primary"></span> +08669999999</a>
              <span class="divider">|</span>
              <a href="#"><span class="mai-mail text-primary"></span> Phongkham@fpt.edu.vn</a>
            </div>
          </div>
          <div class="col-sm-4 text-right text-sm">
            <div class="social-mini-button">
              <a href="#"><span class="mai-logo-facebook-f"></span></a>
              <a href="#"><span class="mai-logo-twitter"></span></a>
              <a href="#"><span class="mai-logo-dribbble"></span></a>
              <a href="#"><span class="mai-logo-instagram"></span></a>
            </div>
          </div>
        </div> <!-- .row -->
      </div> <!-- .container -->
    </div> <!-- .topbar -->

    <nav class="navbar navbar-expand-lg navbar-light shadow-sm">
      <div class="container">
        <a class="navbar-brand" href="#"><span class="text-primary">Clinic</span>-TATQ</a>

        <form action="#">
          <div class="input-group input-navbar">
            <div class="input-group-prepend">
              <span class="input-group-text" id="icon-addon1"><span class="mai-search"></span></span>
            </div>
            <input type="text" class="form-control" placeholder="Enter keyword.." aria-label="Username" aria-describedby="icon-addon1">
          </div>
        </form>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupport" aria-controls="navbarSupport" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupport">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a class="nav-link" href="index.jsp">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="home.jsp">About Us</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="doctors.jsp">Doctors</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="booking.jsp">Booking</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="vip.jsp">VIP</a>
            </li>
            <li class="nav-item">
              <a class="btn btn-primary ml-lg-3" href="login/login.jsp">Login / Register</a>
            </li>
          </ul>
        </div> <!-- .navbar-collapse -->
      </div> <!-- .container -->
    </nav>
  </header>

  <div class="page-hero bg-image overlay-dark" style="background-image: url(images/bg_image_1.jpg);">
    <div class="hero-section">
      <div class="container text-center wow zoomIn">
        <span class="subhead">Let's make your life happier</span>
        <h1 class="display-4">Healthy Living</h1>
        <a href="booking.jsp" class="btn btn-primary">Booking</a>
      </div>
    </div>
  </div>

  <div class="bg-light">
    <div class="page-section py-3 mt-md-n5 custom-index">
      <div class="container">
        <div class="row justify-content-center">
          <div class="col-md-4 py-3 py-md-0">
            <div class="card-service wow fadeInUp">
              <div class="circle-shape bg-secondary text-white">
                <span class="mai-chatbubbles-outline"></span>
              </div>
              <p><a href="feedback.jsp" class=""><span></span> Feedback</a></p>
            </div>
          </div>
          <div class="col-md-4 py-3 py-md-0">
            <div class="card-service wow fadeInUp">
              <div class="circle-shape bg-primary text-white">
                <span class="mai-shield-checkmark"></span>
              </div>
              <p><a href="viewMedicalRecord.jsp" class=""><span></span> Medical Record</a></p>
            </div>
          </div>
          <div class="col-md-4 py-3 py-md-0">
            <div class="card-service wow fadeInUp">
              <div class="circle-shape bg-accent text-white">
                <span class="mai-basket"></span>
              </div>
              <p><a href="informationcustomer.jsp" class=""><span></span> View Information</a></p>
            </div>
          </div>
        </div>
      </div>
    </div> <!-- .page-section -->

  <div class="page-section">
    <div class="container">
      <h1 class="text-center wow fadeInUp">Make an Appointment</h1>
      <form class="main-form">
        <div class="row mt-5 ">
          <div class="col-12 col-sm-6 py-2 wow fadeInLeft">
            <input type="text" class="form-control" placeholder="Full name">
          </div>
          <div class="col-12 col-sm-6 py-2 wow fadeInRight">
            <select name="departement" id="departement" class="custom-select">
              <option value="general">Male</option>
              <option value="cardiology">Female</option>
            </select>
          </div>
          <div class="col-12 col-sm-6 py-2 wow fadeInLeft" data-wow-delay="300ms">
            <input type="date" class="form-control">
          </div>

          <div class="col-12 col-sm-6 py-2 wow fadeInLeft">
            <input type="text" class="form-control" placeholder="Address..">
          </div>
          <div class="col-12 py-2 wow fadeInUp" data-wow-delay="300ms">
            <select name="departement" id="departement" class="custom-select">
              <option value="general">Ears</option>
              <option value="cardiology">Throat</option>
              <option value="cardiology">Nose</option>
            </select>
          </div>
          <div class="col-12 col-sm-6 py-2 wow fadeInLeft">
            <input type="text" class="form-control" placeholder="Numbers..">
          </div>
          <div class="col-12 py-2 wow fadeInUp" data-wow-delay="300ms">
            <textarea name="message" id="message" class="form-control" rows="6" placeholder="Enter message.."></textarea>
          </div>
        </div>

        <button type="submit" class="btn btn-primary mt-3 wow zoomIn">Submit</button>
      </form>
    </div>
  </div> <!-- .page-section -->

  <div class="page-section banner-home bg-image" style="background-image: url(images/banner-pattern.svg);">
    <div class="container py-5 py-lg-0">
      <div class="row align-items-center">
        <div class="col-lg-4 wow zoomIn">
          <div class="img-banner d-none d-lg-block">
            <img src="https://scontent.fhan2-5.fna.fbcdn.net/v/t1.15752-9/346145030_1363155317596800_7041459971065001048_n.png?_nc_cat=109&ccb=1-7&_nc_sid=ae9488&_nc_ohc=XQmC17gatMsAX_wrWXq&_nc_ht=scontent.fhan2-5.fna&oh=03_AdRbeNHAzZDwpw6dkXuwwJHHqtkcDpF_kfN9-9N55FjC6Q&oe=6495BAB9" alt="">
          </div>
        </div>
        <div class="col-lg-8 wow fadeInRight">
          <h1 class="font-weight-normal mb-3">Your health is our top priority - trust us to take care of you.</h1>
        </div>
      </div>
    </div>
  </div> <!-- .banner-home -->

  <footer class="page-footer">
    <div class="container">
      <div class="row px-md-3">
        <div class="col-sm-6 col-lg-3 py-3">
          <h5>Company</h5>
          <ul class="footer-menu">
            <li><a href="#">About Us</a></li>
            <li><a href="#">Career</a></li>
            <li><a href="#">Editorial Team</a></li>
            <li><a href="#">Protection</a></li>
          </ul>
        </div>
        <div class="col-sm-6 col-lg-3 py-3">
          <h5>More</h5>
          <ul class="footer-menu">
            <li><a href="#">Terms & Condition</a></li>
            <li><a href="#">Privacy</a></li>
            <li><a href="#">Advertise</a></li>
            <li><a href="#">Join as Doctors</a></li>
          </ul>
        </div>
        <div class="col-sm-6 col-lg-3 py-3">
          <h5>Our partner</h5>
          <ul class="footer-menu">
            <li><a href="#">One-Fitness</a></li>
            <li><a href="#">One-Drugs</a></li>
            <li><a href="#">One-Live</a></li>
          </ul>
        </div>
        <div class="col-sm-6 col-lg-3 py-3">
          <h5>Contact</h5>
          <p class="footer-link mt-2">351 Willow Street Franklin, MA 02038</p>
          <a href="#" class="footer-link">701-573-7582</a>
          <a href="#" class="footer-link">healthcare@temporary.net</a>

          <h5 class="mt-3">Social Media</h5>
          <div class="footer-sosmed mt-3">
            <a href="#" target="_blank"><span class="mai-logo-facebook-f"></span></a>
            <a href="#" target="_blank"><span class="mai-logo-twitter"></span></a>
            <a href="#" target="_blank"><span class="mai-logo-google-plus-g"></span></a>
            <a href="#" target="_blank"><span class="mai-logo-instagram"></span></a>
            <a href="#" target="_blank"><span class="mai-logo-linkedin"></span></a>
          </div>
        </div>
      </div>

      <hr>

      <p id="copyright">Copyright &copy; 2020 <a href="https://macodeid.com/" target="_blank">MACode ID</a>. All right reserved</p>
    </div> <!-- .container -->
  </footer> <!-- .page-footer -->

<script src="js/jquery-3.5.1.min.js"></script>

<script src="js/bootstrap.bundle.min.js"></script>

<script src="vendor/owl-carousel/js/owl.carousel.min.js"></script>

<script src="vendor/wow/wow.min.js"></script>

<script src="js/theme.js"></script>
  
</body>
=======
<!DOCTYPE html> 
<html lang="en">
	
<!-- doccure/booking.jsp  30 Nov 2019 04:12:16 GMT -->
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
								<%--								<img src="assets/img/logo.png" class="img-fluid" alt="Logo">--%>
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
							<%--							<li class="has-submenu active">--%>
							<%--								<a href="#">Doctors <i class="fas fa-chevron-down"></i></a>--%>
							<%--								<ul class="submenu">--%>
							<%--									<li class="active"><a href="doctor-dashboard.jsp">Doctor Dashboard</a></li>--%>
							<%--									<li><a href="appointments.html">Appointments</a></li>--%>
							<%--									<li><a href="schedule-timings.html">Schedule Timing</a></li>--%>
							<%--									<li><a href="my-patients.html">Patients List</a></li>--%>
							<%--									<li><a href="patient-profile.html">Patients Profile</a></li>--%>
							<%--									<li><a href="chat-doctor.html">Chat</a></li>--%>
							<%--									<li><a href="invoices.html">Invoices</a></li>--%>
							<%--									<li><a href="doctor-profile-settings.jsp">Profile Settings</a></li>--%>
							<%--									<li><a href="reviews.html">Reviews</a></li>--%>
							<%--									<li><a href="doctor-register.html">Doctor Register</a></li>--%>
							<%--								</ul>--%>
							<%--							</li>	--%>
							<%--							<li class="has-submenu">--%>
							<%--								<a href="#">Patients <i class="fas fa-chevron-down"></i></a>--%>
							<%--								<ul class="submenu">--%>
							<%--									<li><a href="search.html">Search Doctor</a></li>--%>
							<%--									<li><a href="doctor-profile.jsp">Doctor Profile</a></li>--%>
							<%--									<li><a href="booking.html">Booking</a></li>--%>
							<%--									<li><a href="checkout.html">Checkout</a></li>--%>
							<%--									<li><a href="booking-success.html">Booking Success</a></li>--%>
							<%--									<li><a href="patient-dashboard.html">Patient Dashboard</a></li>--%>
							<%--									<li><a href="favourites.html">Favourites</a></li>--%>
							<%--									<li><a href="chat.html">Chat</a></li>--%>
							<%--									<li><a href="profile-settings.html">Profile Settings</a></li>--%>
							<%--									<li><a href="change-password.html">Change Password</a></li>--%>
							<%--								</ul>--%>
							<%--							</li>	--%>
							<%--							<li class="has-submenu">--%>
							<%--								<a href="#">Pages <i class="fas fa-chevron-down"></i></a>--%>
							<%--								<ul class="submenu">--%>
							<%--									<li><a href="voice-call.html">Voice Call</a></li>--%>
							<%--									<li><a href="video-call.html">Video Call</a></li>--%>
							<%--									<li><a href="search.html">Search Doctors</a></li>--%>
							<%--									<li><a href="calendar.html">Calendar</a></li>--%>
							<%--									<li><a href="components.html">Components</a></li>--%>
							<%--									<li class="has-submenu">--%>
							<%--										<a href="invoices.html">Invoices</a>--%>
							<%--										<ul class="submenu">--%>
							<%--											<li><a href="invoices.html">Invoices</a></li>--%>
							<%--											<li><a href="invoice-view.html">Invoice View</a></li>--%>
							<%--										</ul>--%>
							<%--									</li>--%>
							<%--									<li><a href="blank-page.html">Starter Page</a></li>--%>
							<%--									<li><a href="login.html">Login</a></li>--%>
							<%--									<li><a href="register.html">Register</a></li>--%>
							<%--									<li><a href="forgot-password.html">Forgot Password</a></li>--%>
							<%--								</ul>--%>
							<%--							</li>--%>
							<%--							<li>--%>
							<%--								<a href="admin/index.html" target="_blank">Admin</a>--%>
							<%--							</li>--%>
							<%--							<li class="login-link">--%>
							<%--								<a href="login.html">Login / Signup</a>--%>
							<%--							</li>--%>
						</ul>
					</div>		 
					<ul class="nav header-navbar-rht">
						<li class="nav-item contact-item">
							<div class="header-contact-img">
								<i class="far fa-hospital"></i>							
							</div>
							<div class="header-contact-detail">
								<p class="contact-header">Contact</p>
								<p class="contact-info-header"> +1 315 369 5943</p>
							</div>
						</li>
						<li class="nav-item">
							<a class="nav-link header-login" href="login.html">login / Signup </a>
						</li>
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
									<li class="breadcrumb-item"><a href="index-2.html">Home</a></li>
									<li class="breadcrumb-item active" aria-current="page">Booking</li>
								</ol>
							</nav>
							<h2 class="breadcrumb-title">Booking</h2>
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
						
							<div class="card">
								<div class="card-body">
									<div class="booking-doc-info">
										<a href="doctor-profile.html" class="booking-doc-img">
											<img src="assets/img/doctors/doctor-thumb-02.jpg" alt="User Image">
										</a>
										<div class="booking-info">
											<h4><a href="doctor-profile.html">Dr. Darren Elder</a></h4>
											<div class="rating">
												<i class="fas fa-star filled"></i>
												<i class="fas fa-star filled"></i>
												<i class="fas fa-star filled"></i>
												<i class="fas fa-star filled"></i>
												<i class="fas fa-star"></i>
												<span class="d-inline-block average-rating">35</span>
											</div>
											<p class="text-muted mb-0"><i class="fas fa-map-marker-alt"></i> Newyork, USA</p>
										</div>
									</div>
								</div>
							</div>
							
							<!-- Schedule Widget -->
							<div class="card booking-schedule schedule-widget">

								<!-- Schedule Header -->
								<div class="schedule-header">
									<div class="row">
										<div class="col-md-12">

											<!-- Day Slot -->
											<div class="day-slot">
												<ul>
													<!--													<li class="left-arrow">-->
													<!--														<a href="#">-->
													<!--															<i class="fa fa-chevron-left"></i>-->
													<!--														</a>-->
													<!--													</li>-->
													<li>
														<span><input type="date" id="datePicker"  name="datePicker" style="border: #42c0fb solid 2px; border-radius: 5px"></span>
													</li>

													<!--													<li>-->
													<!--														<span>Mon</span>-->
													<!--														<span class="slot-date">11 Nov <small class="slot-year">2019</small></span>-->
													<!--													</li>-->
													<!--													<li>-->
													<!--														<span>Tue</span>-->
													<!--														<span class="slot-date">12 Nov <small class="slot-year">2019</small></span>-->
													<!--													</li>-->
													<!--													<li>-->
													<!--														<span>Wed</span>-->
													<!--														<span class="slot-date">13 Nov <small class="slot-year">2019</small></span>-->
													<!--													</li>-->
													<!--													<li>-->
													<!--														<span>Thu</span>-->
													<!--														<span class="slot-date">14 Nov <small class="slot-year">2019</small></span>-->
													<!--													</li>-->
													<!--													<li>-->
													<!--														<span>Fri</span>-->
													<!--														<span class="slot-date">15 Nov <small class="slot-year">2019</small></span>-->
													<!--													</li>-->
													<!--													<li>-->
													<!--														<span>Sat</span>-->
													<!--														<span class="slot-date">16 Nov <small class="slot-year">2019</small></span>-->
													<!--													</li>-->
													<!--													<li>-->
													<!--														<span>Sun</span>-->
													<!--														<span class="slot-date">17 Nov <small class="slot-year">2019</small></span>-->
													<!--													</li>-->
													<!--													<li class="right-arrow">-->
													<!--														<a href="#">-->
													<!--															<i class="fa fa-chevron-right"></i>-->
													<!--														</a>-->
													<!--													</li>-->
												</ul>
											</div>
											<!-- /Day Slot -->

										</div>
									</div>
								</div>
								<!-- /Schedule Header -->

								<!-- Schedule Content -->
								<div class="schedule-cont">
									<div class="row">
										<div class="col-md-12">

											<!-- Time Slot -->
											<div class="time-slot">
												<ul class="clearfix d-flex align-items-center justify-content-center">
													<!--													Morning-->
													<li>
														<a class="timing" href="#">
															<span>9:00</span> <span>-</span><span>9:30</span> <span>AM</span>
														</a>
														<a class="timing" href="#">
															<span>10:00</span> <span>-</span><span>10:30</span> <span>AM</span>
														</a>
														<a class="timing" href="#">
															<span>11:00</span> <span>-</span><span>11:30</span> <span>AM</span>
														</a>
													</li>
													<!--													Afternoon-->
													<li>
														<a class="timing" href="#">
															<span>13:00</span> <span>-</span><span>13:30</span>  <span>PM</span>
														</a>
														<a class="timing selected" href="#">
															<span>14:00</span> <span>-</span><span>14:30</span>  <span>PM</span>
														</a>
														<a class="timing" href="#">
															<span>15:00</span> <span>-</span><span>15:30</span>  <span>PM</span>
														</a>
													</li>
												</ul>
											</div>
											<!-- /Time Slot -->

										</div>
									</div>
								</div>
								<!-- /Schedule Content -->

							</div>
							<!-- /Schedule Widget -->
							
							<!-- Submit Section -->
							<div class="submit-section proceed-btn text-right">
								<a href="checkout" class="btn btn-primary submit-btn">Proceed to Pay</a>
							</div>
							<!-- /Submit Section -->
							
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
									<div class="footer-logo">
										<img src="assets/img/footer-logo.png" alt="logo">
									</div>
									<div class="footer-about-content">
										<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </p>
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
										<li><a href="search.html"><i class="fas fa-angle-double-right"></i> Search for Doctors</a></li>
										<li><a href="login.html"><i class="fas fa-angle-double-right"></i> Login</a></li>
										<li><a href="register.html"><i class="fas fa-angle-double-right"></i> Register</a></li>
										<li><a href="booking.jsp"><i class="fas fa-angle-double-right"></i> Booking</a></li>
										<li><a href="patient-dashboard.html"><i class="fas fa-angle-double-right"></i> Patient Dashboard</a></li>
									</ul>
								</div>
								<!-- /Footer Widget -->
								
							</div>
							
							<div class="col-lg-3 col-md-6">
							
								<!-- Footer Widget -->
								<div class="footer-widget footer-menu">
									<h2 class="footer-title">For Doctors</h2>
									<ul>
										<li><a href="appointments.html"><i class="fas fa-angle-double-right"></i> Appointments</a></li>
										<li><a href="chat.html"><i class="fas fa-angle-double-right"></i> Chat</a></li>
										<li><a href="login.html"><i class="fas fa-angle-double-right"></i> Login</a></li>
										<li><a href="doctor-register.html"><i class="fas fa-angle-double-right"></i> Register</a></li>
										<li><a href="doctor-dashboard.html"><i class="fas fa-angle-double-right"></i> Doctor Dashboard</a></li>
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
											<p> 3556  Beech Street, San Francisco,<br> California, CA 94108 </p>
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
		
		<!-- Custom JS -->
		<script src="assets/js/script.js"></script>
		<script>document.getElementById('datePicker').valueAsDate = new Date();</script>
		
	</body>

<!-- doccure/booking.jsp  30 Nov 2019 04:12:16 GMT -->
>>>>>>> parent of d641032 (update fe for role)
</html>