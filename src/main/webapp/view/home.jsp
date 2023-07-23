<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <meta name="copyright" content="MACode ID, https://macodeid.com/">

    <title>TATQ  CLINIC</title>

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
                        <a href="#"><span class="mai-call text-primary"></span> +84 868746275</a>
                        <span class="divider">|</span>
                        <a href="#"><span class="mai-mail text-primary"></span> quyetlbche160252@fpt.edu.vn</a>
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
            <a class="navbar-brand" href="home"><span class="text-primary">Clinic</span>-TATQ</a>

            <%--            <form action="#">--%>
            <%--                <div class="input-group input-navbar">--%>
            <%--                    <div class="input-group-prepend">--%>
            <%--                        <span class="input-group-text" id="icon-addon1"><span class="mai-search"></span></span>--%>
            <%--                    </div>--%>
            <%--                    <div>--%>
            <%--                    <input type="text" class="form-control" placeholder="Enter keyword.." aria-label="Username"--%>
            <%--                           aria-describedby="icon-addon1">--%>
            <%--                    </div>--%>
            <%--                </div>--%>
            <%--            </form>--%>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupport"
                    aria-controls="navbarSupport" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupport">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="home">Trang chủ</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#'">Về chúng tôi</a>
                    </li>
                    <li class="nav-item">
                        <c:if test="${sessionScope.account ne null}">
                            <c:if test="${sessionScope.account.isAdmin eq 2}">
                                <a class="nav-link" href="booking">Đặt lịch</a>
                            </c:if>
                        </c:if>
                    </li>
                    <li class="nav-item">
                    </li>
                    <li class="nav-item" id="myB">
                        <c:if test="${sessionScope.account eq null}">
                            <a class="btn btn-primary ml-lg-3" href="login">Đăng nhập/Đăng kí</a>
                        </c:if>
                        <c:if test="${sessionScope.account ne null}">
                            <c:if test="${sessionScope.account.isAdmin eq 2}">
                                <a class="btn btn-primary ml-lg-3"
                                   href="patient_dashboard">${sessionScope.patient.name}</a>
                                <a class="btn btn-primary ml-lg-3" href="login">Đăng xuất</a>
                            </c:if>
                        </c:if>
                    </li>
                </ul>
            </div> <!-- .navbar-collapse -->
        </div> <!-- .container -->
    </nav>
</header>

<div class="page-hero bg-image overlay-dark" style="background-image: url(images/bg_image_1.jpg);">
    <div class="hero-section">
        <div class="container text-center wow zoomIn">
            <span class="subhead">Hãy làm cho cuộc sống của bạn hạnh phúc hơn</span>
            <h1 class="display-4">Lối sống lành mạnh</h1>
            <c:if test="${sessionScope.account eq null}">
                <a href="login" class="btn btn-primary">Đặt lịch</a>
            </c:if>
            <c:if test="${sessionScope.account ne null}">
                <c:if test="${sessionScope.account.isAdmin eq 2}">
                    <a href="booking" class="btn btn-primary">Bảng điều kiển</a>
                </c:if>
            </c:if>
        </div>
    </div>
</div>

<div class="bg-light">
    <c:if test="${sessionScope.account ne null}">
    <div class="page-section py-3 mt-md-n5 custom-index">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-4 py-3 py-md-0">
                    <div class="card-service wow fadeInUp">
                        <div class="circle-shape bg-secondary text-white">
                            <span class="mai-logo-xbox"></span>
                        </div>
                        <c:if test="${sessionScope.account.isAdmin eq 2}">
                            <p><a href="patient_dashboard" class=""><span></span> Bảng điều kiển</a></p>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div> <!-- .page-section -->
    </c:if>

    <div class="page-section">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-8 wow fadeInUp">
                    <h1 class="text-center mb-3">Chào mừng đến với clinic TATQ</h1>
                    <div class="text-lg">
                        <p>Phòng khám của chúng tôi là một nơi chuyên nghiệp, đáng tin cậy và tận tâm được thiết kế để
                            đáp ứng nhu cầu chăm sóc sức khỏe
                            nhu cầu của bệnh nhân của chúng tôi. Với đội ngũ bác sĩ giàu kinh nghiệm, nhiệt tình chúng
                            tôi cam kết
                            nhằm mang đến cho khách hàng trải nghiệm khám và điều trị tốt nhất.</p>
                        <p>Phòng khám của chúng tôi được trang bị các thiết bị tiên tiến và hiện đại, cũng như không
                            gian sạch, đẹp và
                            môi trường thoải mái. Chúng tôi luôn làm việc theo tiêu chuẩn cao nhất để đảm bảo an toàn và
                            đáp ứng
                            yêu cầu của khách hàng.</p>
                        <p>Phòng khám của chúng tôi được trang bị các thiết bị tiên tiến và hiện đại, cũng như không
                            gian sạch, đẹp và
                            môi trường thoải mái. Chúng tôi luôn làm việc theo tiêu chuẩn cao nhất để đảm bảo an toàn và
                            đáp ứng
                            yêu cầu của khách hàng.</p>
                        <p>Nếu bạn đang tìm kiếm một nơi chăm sóc sức khỏe đáng tin cậy và chuyên nghiệp, hãy đến với
                            phòng khám của chúng tôi. Chúng tôi là
                            cam kết mang đến cho bạn một sức khỏe tốt nhất!</p>
                    </div>
                </div>
                <div class="col-lg-10 mt-5">
                    <h1 class="text-center mb-5 wow fadeInUp">Top bác sĩ</h1>
                    <div class="row justify-content-center">
                        <div class="col-md-6 col-lg-4 wow zoomIn">
                            <div class="card-doctor">
                                <div class="header">
                                    <img src="images/doctors/doctor_1.jpg" alt="">
                                    <div class="meta">
                                        <a href="#"><span class="mai-call"></span></a>
                                        <a href="#"><span class="mai-logo-whatsapp"></span></a>
                                    </div>
                                </div>
                                <div class="body">
                                    <p class="text-xl mb-0">Dr. Stein Albert</p>
                                    <span class="text-sm text-grey">Cardiology</span>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-lg-4 wow zoomIn">
                            <div class="card-doctor">
                                <div class="header">
                                    <img src="images/doctors/doctor_2.jpg" alt="">
                                    <div class="meta">
                                        <a href="#"><span class="mai-call"></span></a>
                                        <a href="#"><span class="mai-logo-whatsapp"></span></a>
                                    </div>
                                </div>
                                <div class="body">
                                    <p class="text-xl mb-0">Dr. Alexa Melvin</p>
                                    <span class="text-sm text-grey">Dental</span>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-lg-4 wow zoomIn">
                            <div class="card-doctor">
                                <div class="header">
                                    <img src="images/doctors/doctor_3.jpg" alt="">
                                    <div class="meta">
                                        <a href="#"><span class="mai-call"></span></a>
                                        <a href="#"><span class="mai-logo-whatsapp"></span></a>
                                    </div>
                                </div>
                                <div class="body">
                                    <p class="text-xl mb-0">Dr. Rebecca Steffany</p>
                                    <span class="text-sm text-grey">General Health</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="page-section banner-home bg-image" style="background-image: url(images/banner-pattern.svg);">
        <div class="container py-5 py-lg-0">
            <div class="row align-items-center">
                <div class="col-lg-4 wow zoomIn">
                    <div class="img-banner d-none d-lg-block">
                        <img src="https://scontent.fhan2-5.fna.fbcdn.net/v/t1.15752-9/346145030_1363155317596800_7041459971065001048_n.png?_nc_cat=109&ccb=1-7&_nc_sid=ae9488&_nc_ohc=XQmC17gatMsAX_wrWXq&_nc_ht=scontent.fhan2-5.fna&oh=03_AdRbeNHAzZDwpw6dkXuwwJHHqtkcDpF_kfN9-9N55FjC6Q&oe=6495BAB9"
                             alt="">
                    </div>
                </div>
                <div class="col-lg-8 wow fadeInRight">
                    <h1 class="font-weight-normal mb-3">Sức khỏe của bạn là ưu tiên hàng đầu của chúng tôi - hãy tin
                        tưởng để chúng tôi chăm sóc
                        bạn.</h1>
                </div>
            </div>
        </div>
    </div> <!-- .banner-Home -->


    <script src="js/jquery-3.5.1.min.js"></script>

    <script src="js/bootstrap.bundle.min.js"></script>

    <script src="vendor/owl-carousel/js/owl.carousel.min.js"></script>

    <script src="vendor/wow/wow.min.js"></script>

    <script src="js/theme.js"></script>

</body>
</html>