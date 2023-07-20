<%@ page import="mvc.model.MedicalRecord" %>
<%@ page import="java.util.List" %>
<%@ page import="mvc.dal.StaffDBContext" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<!-- Mirrored from dreamguys.co.in/demo/doccure/admin/patient-list.jsp by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 30 Nov 2019 04:12:51 GMT -->
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
    <title>Doccure - Patient</title>

    <!-- Favicon -->
    <link rel="shortcut icon" type="image/x-icon" href="view/admin/assets/img/favicon.png">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="view/admin/assets/css/bootstrap.min.css">

    <!-- Fontawesome CSS -->
    <link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">
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
            <a href="staff_dashboard" class="logo">
                <span class="text-primary">Clinic</span>-TATQ
            </a>
            <a href="staff_dashboard" class="logo logo-small">
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
                    <span class="user-img"><img class="rounded-circle"
                                                src="images/staff/staff1.jpg" width="31"
                                                alt="Swift Taylor"></span>
                </a>
                <div class="dropdown-menu">
                    <div class="user-header">
                        <div class="avatar avatar-sm">
                            <img src="images/staff/staff1.jpg" alt="User Image"
                                 class="avatar-img rounded-circle">
                        </div>
                        <div class="user-text">
                            <h6>Staff</h6>
                        </div>
                    </div>
                    <a class="dropdown-item" href="staff_dashboard">My Profile</a>
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
                    <li>
                        <span>Main</span>
                    </li>
                    <li>
                        <a href="staff_dashboard"><i class="fe fe-home"></i> <span>Dashboard</span></a>
                    </li>
                    <li>
                        <a href="staff_appointment"><i class="fe fe-layout"></i> <span>Appointments</span></a>
                    </li>
                    <li>
                        <a href="list_doctor"><i class="fe fe-user"></i> <span>Doctors</span></a>
                    </li>
                    <li class="active">
                        <a href="list_patient"><i class="fe fe-user"></i> <span>Patients</span></a>
                    </li>
                    <li>
                        <a href="list_invoice"><i class="fe fe-document"></i> <span>Invoice</span></a>
                    </li>
                    <li>
                        <a href="staff_profile"><i class="fe fe-user"></i><span>Profile Settings</span></a>
                    </li>
                    <li>
                        <a href="staff_change_password"><i class="fe fe-user-plus"></i> <span>Change Password</span></a>
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
                        <h3 class="page-title">List of Patient</h3>
                        <ul class="breadcrumb">
                            <li class="breadcrumb-item"><a href="staff_dashboard">Dashboard</a></li>
                            <li class="breadcrumb-item active">Patient</li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- /Page Header -->
            <%
                StaffDBContext staffDBContext = new StaffDBContext();
                List<MedicalRecord> patientList = staffDBContext.patientList();
            %>
            <div class="row">
                <div class="col-sm-12">
                    <div class="card">
                        <div class="card-body">
                            <div class="table-responsive">
                                <div class="table-responsive">
                                    <table class="datatable table table-hover table-center mb-0">
                                        <thead>
                                        <tr>
                                            <th>Patient ID</th>
                                            <th>Patient Name</th>
                                            <th>Date Of Birth</th>
                                            <th>Phone</th>
                                            <th>Email</th>
                                            <th>Last Visit</th>
                                            <th class="text-center">Status</th>
                                            <th class="text-center">Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <% for (MedicalRecord patient : patientList) { %>
                                        <tr>
                                            <td><%= patient.getBooking().getPatient().getId() %>
                                            </td>
                                            <td>
                                                <h2 class="table-avatar">
                                                    <a href="#" class="avatar avatar-sm mr-2"><img
                                                            class="avatar-img rounded-circle"
                                                            src="assets/img/patients/patient1.jpg" alt="User Image"></a>
                                                    <a href="#"><%= patient.getBooking().getPatient().getName() %>
                                                    </a>
                                                </h2>
                                            </td>
                                            <td><%= patient.getBooking().getPatient().getDob() %>
                                            </td>
                                            <td><%= patient.getBooking().getPatient().getAccount().getPhone() %>
                                            </td>
                                            <td><%= patient.getBooking().getPatient().getAccount().getEmail() %>
                                            </td>
                                            <td><%= patient.getBooking().getDate() %>
                                            </td>
                                            <td class="text-center">
                                                <% if (patient.getBooking().getPatient().getAccount().getStatus()) { %>
                                                <span class="badge badge-pill bg-success inv-badge">Active</span>
                                                <% } else { %>
                                                <span class="badge badge-pill bg-danger inv-badge">Deactive</span>
                                                <% } %>
                                            </td>
                                            <td class="text-center">
                                                <div class="actions">
                                                    <a data-toggle="modal"
                                                       href="edit_detail?pid=<%= patient.getBooking().getPatient().getId() %>"
                                                       class="btn btn-sm bg-success-light mr-2">
                                                        <i class="fe fe-pencil"></i> Edit
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
    </div>
    <!-- /Page Wrapper -->

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
<script src="assets/js/script.js"></script>

</body>

<!-- Mirrored from dreamguys.co.in/demo/doccure/admin/patient-list.jsp by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 30 Nov 2019 04:12:52 GMT -->
</html>