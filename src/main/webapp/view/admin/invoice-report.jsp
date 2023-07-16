<%@ page import="mvc.dal.AdminDBContext" %>
<%@ page import="mvc.model.MedicalRecord" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
    
<!-- Mirrored from dreamguys.co.in/demo/doccure/admin/invoice-report.jsp by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 30 Nov 2019 04:12:53 GMT -->
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
							<li>
								<a href="admin_dashboard"><i class="fe fe-home"></i> <span>Dashboard</span></a>
							</li>
							<li>
								<a href="appointment_list"><i class="fe fe-layout"></i> <span>Appointments</span></a>
							</li>
							<li>
								<a href="staff_list"><i class="fe fe-users"></i> <span>Staff</span></a>
							</li>
							<li>
								<a href="doctor_list"><i class="fe fe-user-plus"></i> <span>Doctors</span></a>
							</li>
							<li>
								<a href="patient_list"><i class="fe fe-user"></i> <span>Patients</span></a>
							</li>
							<li  class="active">
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
									<i class="fas fa-sign-out-alt"></i>
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
								<h3 class="page-title">Invoice Report</h3>
								<ul class="breadcrumb">
									<li class="breadcrumb-item"><a href="index.html">Dashboard</a></li>
									<li class="breadcrumb-item active">Invoice Report</li>
								</ul>
							</div>
						</div>
					</div>
					<!-- /Page Header -->
					<%
						AdminDBContext adminDBContext = new AdminDBContext();
						List<MedicalRecord> invoiceList = adminDBContext.invoiceList();
					%>
					<div class="row">
						<div class="col-sm-12">
							<div class="card">
								<div class="card-body">
									<div class="table-responsive">
										<table class="datatable table table-hover table-center mb-0">
											<thead>
												<tr>
													<th>Invoice ID</th>
													<th>Patient ID</th>
													<th>Patient Name</th>
													<th>Total Amount</th>
													<th>Created Date</th>
													<th class="text-center">Status</th>
													<th class="text-center">Actions</th>
												</tr>
											</thead>
											<tbody>
											<% for (MedicalRecord invoice : invoiceList) { %>
												<tr>
													<td><a href="#"><%= invoice.getBill().getId() %></a></td>
													<td><%= invoice.getBooking().getPatient().getId() %></td>
													<td>
														<h2 class="table-avatar">
															<a href="#" class="avatar avatar-sm mr-2"><img class="avatar-img rounded-circle" src="assets/img/patients/patient1.jpg" alt="User Image"></a>
															<a href="#"><%= invoice.getBooking().getPatient().getName() %></a>
														</h2>
													</td>
													<td>$<%= invoice.getBill().getTotalPrice() %></td>
													<td><%= invoice.getBooking().getDate() %></td>
													<td class="text-center">
														<span class="badge badge-pill bg-success inv-badge">Paid</span>
													</td>
													<td class="text-center">
														<div class="actions">
															<a data-toggle="modal" href="#" class="btn btn-sm bg-success-light mr-2">
																<i class="fe fe-pencil"></i> Edit
															</a>
															<a class="btn btn-sm bg-danger-light" data-toggle="modal" href="#">
																<i class="fe fe-trash"></i> Delete
															</a>
														</div>
													</td>
												</tr>
											<% } %>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>			
					</div>
					
				</div>			
			</div>
			<!-- /Page Wrapper -->

			<!-- Edit Details Modal -->
			<div class="modal fade" id="edit_invoice_report" aria-hidden="true" role="dialog">
				<div class="modal-dialog modal-dialog-centered" role="document" >
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">Edit Invoice Report</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<form>
								<div class="row form-row">
									<div class="col-12 col-sm-6">
										<div class="form-group">
											<label>Invoice Number</label>
											<input type="text" class="form-control" value="#INV-0001">
										</div>
									</div>
									<div class="col-12 col-sm-6">
										<div class="form-group">
											<label>Patient ID</label>
											<input type="text" class="form-control" value="	#PT002">
										</div>
									</div>
									<div class="col-12 col-sm-6">
										<div class="form-group">
											<label>Patient Name</label>
											<input type="text" class="form-control" value="R Amer">
										</div>
									</div>
									<div class="col-12 col-sm-6">
										<div class="form-group">
											<label>Patient Image</label>
											<input type="file"  class="form-control">
										</div>
									</div>
									<div class="col-12 col-sm-6">
										<div class="form-group">
											<label>Total Amount</label>
											<input type="text"  class="form-control" value="$200.00">
										</div>
									</div>
									<div class="col-12 col-sm-6">
										<div class="form-group">
											<label>Created Date</label>
											<input type="text"  class="form-control" value="29th Oct 2019">
										</div>
									</div>

								</div>
								<button type="submit" class="btn btn-primary btn-block">Save Changes</button>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- /Edit Details Modal -->

			<!-- Delete Modal -->
			<div class="modal fade" id="delete_modal" aria-hidden="true" role="dialog">
				<div class="modal-dialog modal-dialog-centered" role="document" >
					<div class="modal-content">
					<!--	<div class="modal-header">
							<h5 class="modal-title">Delete</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>-->
						<div class="modal-body">
							<div class="form-content p-2">
								<h4 class="modal-title">Delete</h4>
								<p class="mb-4">Are you sure want to delete?</p>
								<button type="button" class="btn btn-primary">Save </button>
								<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /Delete Modal -->
        </div>
		<!-- /Main Wrapper -->
		
		<!-- jQuery -->
        <script src="assets/js/jquery-3.2.1.min.js"></script>
		
		<!-- Bootstrap Core JS -->
        <script src="assets/js/popper.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
		
		<!-- Slimscroll JS -->
        <script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
		
		<!-- Datatables JS -->
		<script src="assets/plugins/datatables/jquery.dataTables.min.js"></script>
		<script src="assets/plugins/datatables/datatables.min.js"></script>
		
		<!-- Custom JS -->
		<script  src="assets/js/script.js"></script>
		
    </body>

<!-- Mirrored from dreamguys.co.in/demo/doccure/admin/invoice-report.jsp by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 30 Nov 2019 04:12:53 GMT -->
</html>