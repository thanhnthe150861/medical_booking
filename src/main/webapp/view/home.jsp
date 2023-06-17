<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <a class="navbar-brand" href="home"><span class="text-primary">Clinic</span>-TATQ</a>

            <form action="#">
                <div class="input-group input-navbar">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="icon-addon1"><span class="mai-search"></span></span>
                    </div>
                    <input type="text" class="form-control" placeholder="Enter keyword.." aria-label="Username"
                           aria-describedby="icon-addon1">
                </div>
            </form>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupport"
                    aria-controls="navbarSupport" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupport">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="home">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#'">About Us</a>
                    </li>
                    <li class="nav-item">
                    <c:if test="${sessionScope.account ne null}">
                        <c:if test="${sessionScope.account.isAdmin eq 0}">
                            <a class="nav-link" href="#">View Doctors</a>
                        </c:if>
                        <c:if test="${sessionScope.account.isAdmin eq 1}">
                            <a class="nav-link" href="#">View Doctors</a>
                        </c:if>
                        <c:if test="${sessionScope.account.isAdmin eq 2}">
                            <a class="nav-link" href="#">View Doctors</a>
                        </c:if>
                    </c:if>
                    </li>
                    <li class="nav-item">
                        <c:if test="${sessionScope.account ne null}">
                            <c:if test="${sessionScope.account.isAdmin eq 0}">
<%--                                <a class="nav-link" href="#">Booking</a>--%>
                            </c:if>
                            <c:if test="${sessionScope.account.isAdmin eq 1}">
                                <a class="nav-link" href="#">View Booking</a>
                            </c:if>
                            <c:if test="${sessionScope.account.isAdmin eq 2}">
                                <a class="nav-link" href="#">Booking</a>
                            </c:if>
                        </c:if>
                    </li>
                    <li class="nav-item">
                        <c:if test="${sessionScope.account ne null}">
                            <c:if test="${sessionScope.account.isAdmin eq 0}">
                                <a class="nav-link" href="#">View Rank User</a>
                            </c:if>
                            <c:if test="${sessionScope.account.isAdmin eq 1 || sessionScope.account.isAdmin eq 2}">
                                <a class="nav-link" href="#">View Rank Doctor</a>
                                <a class="nav-link" href="#">View Rank Client</a>
                            </c:if>
                        </c:if>
                    </li>
                    <li class="nav-item">
                        <c:if test="${sessionScope.account eq null}">
                            <a class="btn btn-primary ml-lg-3" href="login">Login / Register</a>
                        </c:if>
                        <c:if test="${sessionScope.account ne null}">
                            <c:if test="${sessionScope.account.isAdmin eq 0}">
                                <a class="btn btn-primary ml-lg-3" href="admin_dashboard">Admin</a>
                                <a class="btn btn-primary ml-lg-3" href="login">Log out</a>
                            </c:if>
                            <c:if test="${sessionScope.account.isAdmin eq 1}">
                                <a class="btn btn-primary ml-lg-3" href="doctor_dashboard">Doctor</a>
                                <a class="btn btn-primary ml-lg-3" href="login">Log out</a>
                            </c:if>
                            <c:if test="${sessionScope.account.isAdmin eq 2}">
                                <a class="btn btn-primary ml-lg-3" href="client_dashboard">Client</a>
                                <a class="btn btn-primary ml-lg-3" href="login">Log out</a>
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
            <span class="subhead">Let's make your life happier</span>
            <h1 class="display-4">Healthy Living</h1>
            <c:if test="${sessionScope.account eq null}">
                <a href="register" class="btn btn-primary">Booking</a>
            </c:if>
            <c:if test="${sessionScope.account ne null}">
                <c:if test="${sessionScope.account.isAdmin eq 2}">
                    <a href="#" class="btn btn-primary">Booking</a>
                </c:if>
            </c:if>
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
                            <span class="mai-"></span>
                        </div>

<%--                        <c:if test="${sessionScope.account eq null}">--%>
<%--                            <a class="btn btn-primary ml-lg-3" href="login">Login / Register</a>--%>
<%--                        </c:if>--%>
                        <c:if test="${sessionScope.account eq null}">
                            <p><a href="login" class=""><span></span> Dashboard</a></p>
                        </c:if>
                        <c:if test="${sessionScope.account ne null}">
                            <c:if test="${sessionScope.account.isAdmin eq 0}">
                                <p><a href="admin_dashboard" class=""><span></span> Dashboard</a></p>
                            </c:if>
                            <c:if test="${sessionScope.account.isAdmin eq 1}">
                                <p><a href="doctor_dashboard" class=""><span></span> Dashboard</a></p>
                            </c:if>
                            <c:if test="${sessionScope.account.isAdmin eq 2}">
                                <p><a href="client_dashboard" class=""><span></span> Dashboard</a></p>
                            </c:if>
                        </c:if>
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
                        <p><a href="#" class=""><span></span> View Information</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div> <!-- .page-section -->

    <div class="page-section">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-8 wow fadeInUp">
                    <h1 class="text-center mb-3">Welcome to the clinic TATQ</h1>
                    <div class="text-lg">
                        <p>Our clinic is a professional, trustworthy and dedicated place designed to meet the healthcare
                            needs of our patients. With a team of experienced and enthusiastic doctors, we are committed
                            to giving our customers the best examination and treatment experience.</p>
                        <p>Our clinic is equipped with advanced and modern equipment, as well as a clean, beautiful and
                            comfortable environment. We always work to the highest standards to ensure safety and meet
                            customer requirements.</p>
                        <p>We are committed to providing our customers with a professional and dedicated service. We
                            understand that health is priceless, so we will constantly strive to meet the most demanding
                            requirements of our customers.</p>
                        <p>If you are looking for a reliable and professional healthcare, come to our clinic. We are
                            committed to bringing you the best health!</p>
                    </div>
                </div>
                <div class="col-lg-10 mt-5">
                    <h1 class="text-center mb-5 wow fadeInUp">Our Doctors</h1>
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
    <div class="maps-container wow fadeInUp">
        <a href="https://goo.gl/maps/gWzXpy7XisLX3xQLA?coh=178572&entry=tt"><img
                src="https://scontent.fhan2-5.fna.fbcdn.net/v/t1.15752-9/348387688_824547325805226_4870617880354541066_n.png?_nc_cat=104&ccb=1-7&_nc_sid=ae9488&_nc_ohc=BmCsPzL_1EgAX9Mxoj2&_nc_oc=AQmrxsbyWbDB8KhFNNm8zF3RLa3VNKIcX_JU47j15FnFqu4VGUiOT-EPrvgfFe36-y0&_nc_ht=scontent.fhan2-5.fna&oh=03_AdRwjXuNnaDPFoRsg4mgwOuHHtUG8lV7zgEikNqwL1lrsQ&oe=649649D3"
                alt=""></a>
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
                    <h1 class="font-weight-normal mb-3">Your health is our top priority - trust us to take care of
                        you.</h1>
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

            <p id="copyright">Copyright &copy; 2020 <a href="https://macodeid.com/" target="_blank">MACode ID</a>. All
                right reserved</p>
        </div>
    </footer>

    <script src="js/jquery-3.5.1.min.js"></script>

    <script src="js/bootstrap.bundle.min.js"></script>

    <script src="vendor/owl-carousel/js/owl.carousel.min.js"></script>

    <script src="vendor/wow/wow.min.js"></script>

    <script src="js/theme.js"></script>

</body>
</html>