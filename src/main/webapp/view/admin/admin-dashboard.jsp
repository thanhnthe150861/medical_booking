<%@ page import="mvc.model.MedicalRecord" %>
<%@ page import="java.util.List" %>
<%@ page import="mvc.dal.AdminDBContext" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

<!-- Mirrored from dreamguys.co.in/demo/doccure/admin/index.html by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 30 Nov 2019 04:12:20 GMT -->
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
	<title>Doccure - Dashboard</title>

	<!-- Favicon -->
	<link rel="shortcut icon" type="image/x-icon" href="view/admin/assets/img/favicon.png">

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="view/admin/assets/css/bootstrap.min.css">

	<!-- Fontawesome CSS -->
	<link rel="stylesheet" href="view/admin/assets/css/font-awesome.min.css">

	<!-- Feathericon CSS -->
	<link rel="stylesheet" href="view/admin/assets/css/feathericon.min.css">

	<link rel="stylesheet" href="view/admin/assets/plugins/morris/morris.css">

	<!-- Main CSS -->
	<link rel="stylesheet" href="view/admin/assets/css/style.css">

	<!--[if lt IE 9]>
	<script src="view/admin/assets/js/html5shiv.min.js"></script>
	<script src="view/admin/assets/js/respond.min.js"></script>
	<![endif]-->
</head>
<body>

<!-- Main Wrapper -->
<div class="main-wrapper">

	<!-- Header -->
	<div class="header">

		<!-- Logo -->
		<div class="header-left">
			<a href="admin_dashboard" class="logo">
				<span class="text-primary">Clinic</span>-TATQ
			</a>
			<a href="admin_dashboard" class="logo logo-small">
				<span class="text-primary" width="50" height="50">Clinic</span>
			</a>
		</div>
		<!-- /Logo -->

		<a href="javascript:void(0);" id="toggle_btn">
			<i class="fe fe-text-align-left"></i>
		</a>

		<!-- Mobile Menu Toggle -->
		<a class="mobile_btn" id="mobile_btn">
			<i class="fa fa-bars"></i>
		</a>
		<!-- /Mobile Menu Toggle -->

		<!-- Header Right Menu -->
		<ul class="nav user-menu">

			<!-- User Menu -->
			<li class="nav-item dropdown has-arrow">
				<a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown">
					<span class="user-img"><img class="rounded-circle" src="view/admin/assets/img/profiles/avatar-01.jpg" width="31" alt="Ryan Taylor"></span>
				</a>
				<div class="dropdown-menu">
					<div class="user-header">
						<div class="avatar avatar-sm">
							<img src="view/admin/assets/img/profiles/avatar-01.jpg" alt="User Image" class="avatar-img rounded-circle">
						</div>
						<div class="user-text">
							<h6>Administrator</h6>
						</div>
					</div>
					<a class="dropdown-item" href="admin_dashboard">My Profile</a>
					<a class="dropdown-item" href="login">Logout</a>
				</div>
			</li>
			<!-- /User Menu -->

		</ul>
		<!-- /Header Right Menu -->

	</div>
	<!-- /Header -->

	<!-- Sidebar -->
	<div class="sidebar" id="sidebar">
		<div class="sidebar-inner slimscroll">
			<div id="sidebar-menu" class="sidebar-menu">
				<ul>
					<li class="menu-title">
						<span>Main</span>
					</li>
					<li class="active">
						<a href="admin_dashboard"><i class="fe fe-home"></i> <span>Dashboard</span></a>
					</li>
					<li>
						<a href="appointment_list"><i class="fe fe-layout"></i> <span>Appointments</span></a>
					</li>
					<li>
						<a href="staff_list"><i class="fe fe-users"></i> <span>Staff</span></a>
					</li>
					<li>
						<a href="doctor_list"><i class="fe fe-user"></i> <span>Doctors</span></a>
					</li>
					<li>
						<a href="patient_list"><i class="fe fe-user"></i> <span>Patients</span></a>
					</li>
					<li>
						<a href="invoice_list"><i class="fe fe-document"></i> <span> Invoice</span></a>
					</li>
					<li>
						<a href="profile"><i class="fe fe-user-plus"></i> <span>Profile</span></a>
					</li>
					<li class="submenu">
						<a href="#"><i class="fe fe-document"></i> <span> Form Details </span> <span class="menu-arrow"></span></a>
						<ul style="display: none;">
							<li><a href="form_details?str=doctor">Doctor</a></li>
							<li><a href="form_details?str=patient">Patient</a></li>
							<li><a href="form_details?str=staff">Staff</a></li>
						</ul>
					</li>
					<li>
						<a href="login">
							<i class="fa fa-sign-out-alt"></i>
							<span>Logout</span>
						</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- /Sidebar -->

	<!-- Page Wrapper -->
	<div class="page-wrapper">

		<div class="content container-fluid">

			<!-- Page Header -->
			<div class="page-header">
				<div class="row">
					<div class="col-sm-12">
						<h3 class="page-title">Welcome Admin!</h3>
						<ul class="breadcrumb">
							<li class="breadcrumb-item active">Dashboard</li>
						</ul>
					</div>
				</div>
			</div>
			<!-- /Page Header -->
			<%
				AdminDBContext adminDBContext = new AdminDBContext();
				List<MedicalRecord> doctorListTop5 = adminDBContext.getTop5Doctor();
				List<MedicalRecord> patientListTop5 = adminDBContext.getTop5Patient();
				int totalDoctor = adminDBContext.getTotalDoctor();
				int totalPatient = adminDBContext.getTotalPatient();
				int totalStaff = adminDBContext.getTotalStaff();
				int totalAppointment = adminDBContext.getTotalAppointment();
				float totalPrice = adminDBContext.getTotalPrice();
			%>
			<div class="row">
				<div class="col-xl-3 col-sm-6 col-12">
					<div class="card">
						<div class="card-body">
							<div class="dash-widget-header">
										<span class="dash-widget-icon text-primary border-primary">
											<i class="fe fe-users"></i>
										</span>
								<div class="dash-count">
									<h3><%= totalDoctor %></h3>
								</div>
							</div>
							<div class="dash-widget-info">
								<h6 class="text-muted">Doctors</h6>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xl-3 col-sm-6 col-12">
					<div class="card">
						<div class="card-body">
							<div class="dash-widget-header">
										<span class="dash-widget-icon text-success">
											<i class="fe fe-users"></i>
										</span>
								<div class="dash-count">
									<h3><%= totalPatient %></h3>
								</div>
							</div>
							<div class="dash-widget-info">
								<h6 class="text-muted">Patients</h6>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xl-3 col-sm-6 col-12">
					<div class="card">
						<div class="card-body">
							<div class="dash-widget-header">
										<span class="dash-widget-icon text-dark">
											<i class="fe fe-users"></i>
										</span>
								<div class="dash-count">
									<h3><%= totalStaff %></h3>
								</div>
							</div>
							<div class="dash-widget-info">
								<h6 class="text-muted">Staff</h6>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xl-3 col-sm-6 col-12">
					<div class="card">
						<div class="card-body">
							<div class="dash-widget-header">
										<span class="dash-widget-icon text-danger border-danger">
											<i class="fe fe-money"></i>
										</span>
								<div class="dash-count">
									<h3><%= totalAppointment %></h3>
								</div>
							</div>
							<div class="dash-widget-info">
								<h6 class="text-muted">Appointment</h6>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xl-3 col-sm-6 col-12">
					<div class="card">
						<div class="card-body">
							<div class="dash-widget-header">
										<span class="dash-widget-icon text-warning border-warning">
											<i class="fe fe-folder"></i>
										</span>
								<div class="dash-count">
									<h3>$<%= totalPrice %></h3>
								</div>
							</div>
							<div class="dash-widget-info">

								<h6 class="text-muted">Revenue</h6>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 col-lg-6">
				</div>
				<div class="col-md-12 col-lg-6">
				</div>
			</div>
			<div class="row">
				<div class="col-md-6 d-flex">
					<!-- Recent Orders -->
					<div class="card card-table flex-fill">
						<div class="card-header">
							<h4 class="card-title">Top 5 Doctor</h4>
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-hover table-center mb-0">
									<thead>
									<tr>
										<th>Doctor Name</th>
										<th>Speciality</th>
										<th>Earned</th>
									</tr>
									</thead>
									<tbody>
									<% for (MedicalRecord doctor : doctorListTop5) { %>
									<tr>
										<td>
											<h2 class="table-avatar">
												<a href="profile.jsp" class="avatar avatar-sm mr-2"><img class="avatar-img rounded-circle" src="view/admin/assets/img/doctors/doctor-thumb-01.jpg" alt="User Image"></a>
												<a href="profile.jsp"><%= doctor.getBooking().getDoctor().getName() %></a>
											</h2>
										</td>
										<td><%= doctor.getBooking().getDoctor().getSpecialty() %></td>
										<td>$<%= doctor.getBill().getTotalPrice() %></td>
									</tr>
									<% } %>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- /Recent Orders -->

				</div>
				<div class="col-md-6 d-flex">

					<!-- Feed Activity -->
					<div class="card  card-table flex-fill">
						<div class="card-header">
							<h4 class="card-title">Top 5 Patients</h4>
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-hover table-center mb-0">
									<thead>
									<tr>
										<th>Patient Name</th>
										<th></th>
										<th>Last Visit</th>
										<th>Paid</th>
									</tr>
									</thead>
									<tbody>
									<% for (MedicalRecord patient : patientListTop5) { %>
									<tr>
										<td>
											<h2 class="table-avatar">
												<a href="profile.jsp" class="avatar avatar-sm mr-2"><img class="avatar-img rounded-circle" src="view/admin/assets/img/patients/patient1.jpg" alt="User Image"></a>
												<a href="profile.jsp"><%= patient.getBooking().getPatient().getName() %></a>
											</h2>
										</td>
										<td></td>
										<td><%= patient.getBooking().getDate() %></td>
										<td class="text-right">$<%= patient.getBill().getTotalPrice() %></td>
									</tr>
									<% } %>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- /Feed Activity -->

				</div>
			</div>

		</div>
	</div>
	<!-- /Page Wrapper -->

</div>
<!-- /Main Wrapper -->

<!-- jQuery -->
<script src="view/admin/assets/js/jquery-3.2.1.min.js"></script>

<!-- Bootstrap Core JS -->
<script src="view/admin/assets/js/popper.min.js"></script>
<script src="view/admin/assets/js/bootstrap.min.js"></script>

<!-- Slimscroll JS -->
<script src="view/admin/assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<script src="view/admin/assets/plugins/raphael/raphael.min.js"></script>
<script src="view/admin/assets/plugins/morris/morris.min.js"></script>
<script src="view/admin/assets/js/chart.morris.js"></script>

<!-- Custom JS -->
<script  src="view/admin/assets/js/script.js"></script>

</body>

<!-- Mirrored from dreamguys.co.in/demo/doccure/admin/index.html by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 30 Nov 2019 04:12:34 GMT -->
</html>