<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
    <title>TATQ CLINIC - EDIT DOCTOR</title>

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
                                                src="${sessionScope.staff.url}" width="31"
                    ></span>
                </a>
                <div class="dropdown-menu">
                    <div class="user-header">
                        <div class="avatar avatar-sm">
                            <img src="${sessionScope.staff.url}"
                                 class="avatar-img rounded-circle">
                        </div>
                        <div class="user-text">
                            <h6>${sessionScope.staff.name}</h6>
                        </div>
                    </div>
                    <a class="dropdown-item" href="staff_dashboard">Bảng điều khiển</a>
                    <a class="dropdown-item" href="login">Đăng xuất</a>
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
                        <a href="staff_dashboard"><i class="fe fe-home"></i> <span>Bảng điều khiển</span></a>
                    </li>
                    <li>
                        <a href="create_invoice"><i class="fe fe-edit"></i> <span>Tạo hóa đơn</span></a>
                    </li>
                    <li>
                        <a href="staff_appointment"><i class="fe fe-layout"></i> <span>Lịch hẹn</span></a>
                    </li>
                    <li class="active">
                        <a href="list_doctor"><i class="fe fe-user"></i> <span>Danh sách bác sĩ</span></a>
                    </li>
                    <li>
                        <a href="list_patient"><i class="fe fe-user"></i> <span>Danh sách bệnh nhân</span></a>
                    </li>
                    <li>
                        <a href="list_invoice"><i class="fe fe-document"></i> <span>Hóa đơn</span></a>
                    </li>
                    <li>
                        <a href="staff_profile"><i class="fe fe-edit"></i><span>Thông tin cá nhân</span></a>
                    </li>
                    <li>
                        <a href="staff_change_password"><i class="fe fe-edit"></i> <span>Đổi mật khẩu</span></a>
                    </li>
                    <li>
                        <a href="login">
                            <i class="fe fe-eject"></i>
                            <span>Đăng xuất</span>
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
                    <div class="col">
                        <h3 class="page-title">Thông tin bác sĩ</h3>
                        <ul class="breadcrumb">
                            <li class="breadcrumb-item"><a href="staff_dashboard">Bảng điều khiển</a></li>
                            <li class="breadcrumb-item active">Thông tin bác sĩ</li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- /Page Header -->

            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-header">
                            <h4 class="card-title"><c:if test="${sessionScope.doctor eq null}">THÊM MỚI</c:if><c:if
                                    test="${sessionScope.doctor ne null}">CHỈNH SỬA THÔNG TIN</c:if> BÁC SĨ</h4>
                            <!-- Place this code where you want to display the error message -->
                            <% String errorMessage = (String) request.getAttribute("messError"); %>
                            <% if (errorMessage != null && !errorMessage.isEmpty()) { %>
                            <div class="alert alert-danger" role="alert">
                                <%= errorMessage %>
                            </div>
                            <% } %>
                            <% String messSuccess = (String) request.getAttribute("messSuccess"); %>
                            <% if (messSuccess != null && !messSuccess.isEmpty()) { %>
                            <div class="alert alert-success" role="alert">
                                <%= messSuccess %>
                            </div>
                            <% } %>
                        </div>
                        <div class="card-body">
                            <form action="edit_detail" method="post" enctype="multipart/form-data">
                                <div class="form-group mb-0 row">
                                    <div class="col-md-2">
                                        <div class="change-avatar">
                                            <div class="profile-img">
                                                <img src="${sessionScope.doctor.url}"
                                                     style="width: 200px; height: 200px; object-fit: cover;">
                                            </div>
                                            <div class="upload-img">
                                                <label for="photo-upload" class="change-photo-btn">
                                                    <span><i class="fa fa-upload"></i> Tải ảnh lên</span>
                                                </label>
                                                <input type="file" id="photo-upload" class="upload" name="file"
                                                       style="display: none;">
                                                <small class="form-text text-muted">Sử dụng loại ảnh JPG, GIF or PNG.
                                                    Dung lượng tối đa 2MB</small>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2">Username</label>
                                    <div class="col-md-10">
                                        <input type="text" class="form-control" name="username" required <c:if
                                                test="${sessionScope.doctor ne null}"> readonly="readonly"
                                               value="${sessionScope.doctor.account.username}"</c:if>>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2">Tên</label>
                                    <div class="col-md-10">
                                        <input type="text" class="form-control" name="name" required
                                               value="${sessionScope.doctor.name}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2">Số điện thoại</label>
                                    <div class="col-md-10">
                                        <input type="text" class="form-control" name="phone" required
                                               value="${sessionScope.doctor.account.phone}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2">Email</label>
                                    <div class="col-md-10">
                                        <input type="text" class="form-control" name="email" required
                                               value="${sessionScope.doctor.account.email}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2">Ngày sinh</label>
                                    <div class="col-md-10">
                                        <input class="form-control" type="date" name="dob" required
                                               value="${sessionScope.doctor.dob}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2">Giới tính</label>
                                    <div class="col-md-10">
                                        <select class="form-control select" name="gender" required>
                                            <option>Select</option>
                                            <option value="Male" ${sessionScope.doctor.gender == "Male" ? "selected" : ""}>
                                                Nam
                                            </option>
                                            <option value="Female" ${sessionScope.doctor.gender == "Female" ? "selected" : ""}>
                                                Nữ
                                            </option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2">Chuyên môn</label>
                                    <div class="col-md-10">
                                        <input class="form-control" type="text" name="speciality" required
                                               value="${sessionScope.doctor.specialty}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2">Trạng thái</label>
                                    <div class="col-md-10">
                                        <select class="form-control select" name="status" required>
                                            <option value="true" ${sessionScope.doctor.account.status == true ? "selected" : ""}>
                                                Active
                                            </option>
                                            <option value="false" ${sessionScope.doctor.account.status == false ? "selected" : ""}>
                                                Deactive
                                            </option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-md-2">Xếp hạng</label>
                                    <div class="col-md-10">
                                        <select class="form-control select" name="rank" required>
                                            <c:forEach items="${sessionScope.rankListDoctor}" var="rld">
                                                <option value="${rld.id}" ${sessionScope.doctor.ranks.id == rld.id ? "selected" : ""}>${rld.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="submit-section">
                                    <button type="submit" class="btn btn-primary submit-btn">Lưu</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /Main Wrapper -->

</div>
<!-- /Main Wrapper -->

<!-- jQuery -->
<script src="view/admin/assets/js/jquery-3.2.1.min.js"></script>

<!-- Bootstrap Core JS -->
<script src="view/admin/assets/js/popper.min.js"></script>
<script src="view/admin/assets/js/bootstrap.min.js"></script>

<!-- Slimscroll JS -->
<script src="view/admin/assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- Custom JS -->
<script src="view/admin/assets/js/script.js"></script>

</body>
</html>