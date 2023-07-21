<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
								<a href="doctor_dashboard">Dash Board</a>
							</li>
						</ul>
					</div>		 
					<ul class="nav header-navbar-rht">
						<li class="nav-item contact-item">
							<div class="header-contact-img">
								<i class="far fa-hospital"></i>							
							</div>
							<div class="header-contact-detail">
								<p class="contact-header">Contact</p>
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
								<a class="dropdown-item" href="doctor_dashboard">Dashboard</a>
								<a class="dropdown-item" href="doctor_profile_settings">Profile Settings</a>
								<a class="dropdown-item" href="login">Logout</a>
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
									<li class="breadcrumb-item"><a href="doctor_dashboard">Home</a></li>
									<li class="breadcrumb-item active" aria-current="page">Profile</li>
								</ol>
							</nav>
							<h2 class="breadcrumb-title">Profile</h2>
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
													<h5 class="mb-0"><i class="fas fa-birthday-cake"></i> ${sessionScope.patient.dob}</h5>
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
												<a class="nav-link active" href="patient_profile?id=${sessionScope.patient.id}">Appointments</a>
											</li>
											<li class="nav-item">
												<a class="nav-link" href="patient_profile?id=${sessionScope.patient.id}&medical=true" ><span class="med-records">Medical Records</span></a>
											</li>
											<li class="nav-item">
												<a class="nav-link" href="patient_profile?id=${sessionScope.patient.id}&bill=true" ><span>Billing</span></a>
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
																	<th>Doctor</th>
																	<th></th>
																	<th>Booking Date</th>
																	<th></th>
																	<th></th>
																	<th>Status</th>
																	<th></th>
																</tr>
															</thead>
															<tbody>
															<c:forEach items="${requestScope.medicalRecordList}" var="m">
																<tr>
																	<td>
																		<h2 class="table-avatar">
																			<a href="#" class="avatar avatar-sm mr-2">
																				<img class="avatar-img rounded-circle" src="${m.booking.doctor.url}" alt="User Image">
																			</a>
																			<a href="#">${m.booking.doctor.name} <span>${m.booking.doctor.specialty}</span></a>
																		</h2>
																	</td>
																	<td></td>
																	<td>${m.booking.date} <span class="d-block text-info">${m.booking.slots.name} </span></td>
																	<td></td>
																	<td></td>
																	<td>
																		<span class="badge badge-pill bg-${m.booking.status eq 'Confirmed' ? 'success-light' : m.booking.status eq 'Pending' ? 'warning-light' : m.booking.status eq 'Cancelled' ? 'danger-light' : m.booking.status eq 'Completed' ? 'info-light' : ''}">
																				${m.booking.status}
																		</span>
																	</td>
																	<td class="text-right">
																		<div class="table-action">
																			<a href="javascript:void(0);" class="btn btn-sm bg-info-light">
																				<i class="far fa-eye"></i> View
																			</a>
																			<c:if test="${m.booking.status eq 'Confirmed'}">
																				<a href="patient_profile?id=${sessionScope.patient.id}&bid=${m.booking.id}&status=Completed" class="btn btn-sm bg-info-light">
																					<i class="fas fa-check"></i> Completed
																				</a>
																				<a href="patient_profile?id=${sessionScope.patient.id}&bid=${m.booking.id}&status=Cancelled" class="btn btn-sm bg-danger-light">
																					<i class="far fa-trash-alt"></i> Cancel
																				</a>
																			</c:if>
																			<c:if test="${m.booking.status eq 'Pending'}">
																				<a href="patient_profile?id=${sessionScope.patient.id}&bid=${m.booking.id}&status=Confirmed" class="btn btn-sm bg-success-light">
																					<i class="fas fa-check"></i> Confirmed
																				</a>
																				<a href="patient_profile?id=${sessionScope.patient.id}&bid=${m.booking.id}&status=Cancelled" class="btn btn-sm bg-danger-light">
																					<i class="far fa-trash-alt"></i> Cancel
																				</a>
																			</c:if>
																			<c:if test="${m.booking.status eq 'Completed'}">
																				<a href="javascript:void(0);" class="btn btn-sm bg-primary-light">
																					<i class="far fa-clock"></i> Reschedule
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
									<h2 class="footer-title">For Doctors</h2>
									<ul>
										<li><a href="doctor_appointments"><i class="fas fa-angle-double-right"></i> Appointments</a></li>
										<li><a href="doctor_dashboard"><i class="fas fa-angle-double-right"></i> Doctor Dashboard</a></li>
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
		
		<!-- Add Medical Records Modal -->
		<div class="modal fade custom-modal" id="add_medical_records">
			<div class="modal-dialog modal-dialog-centered modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h3 class="modal-title">Medical Records</h3>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					</div>
					<form>					
						<div class="modal-body">
							<div class="form-group">
								<label>Date</label>
								<input type="text" class="form-control datetimepicker" value="31-10-2019">
							</div>
							<div class="form-group">
								<label>Description ( Optional )</label>
								<textarea class="form-control"></textarea>
							</div>
							<div class="form-group">
								<label>Upload File</label> 
								<input type="file" class="form-control">
							</div>	
							<div class="submit-section text-center">
								<button type="submit" class="btn btn-primary submit-btn">Submit</button>
								<button type="button" class="btn btn-secondary submit-btn" data-dismiss="modal">Cancel</button>							
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

<!-- doccure/patient-profile.jsp  30 Nov 2019 04:12:13 GMT -->
</html>