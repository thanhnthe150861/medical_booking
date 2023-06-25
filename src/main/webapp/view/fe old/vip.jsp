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
              <a href="#"><span class="mai-call text-primary"></span> +00 123 4455 6666</a>
              <span class="divider">|</span>
              <a href="#"><span class="mai-mail text-primary"></span> mail@example.com</a>
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
        <a class="navbar-brand" href="#"><span class="text-primary">One</span>-Health</a>

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
            <li class="nav-item">
              <a class="nav-link" href="index.jsp">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="../home.jsp">About Us</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="doctors.jsp">Doctors</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="../patient/booking.jsp">Booking</a>
            </li>
            <li class="nav-item active">
              <a class="nav-link" href="vip.jsp">VIP</a>
            </li>
            <li class="nav-item">
              <a class="btn btn-primary ml-lg-3" href="#">Login / Register</a>
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
        <a href="../patient/booking.jsp" class="btn btn-primary">Booking</a>
      </div>
    </div>
  </div>

  <html>
  <head>
    <title>Hiển thị điểm thành viên</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="style.css">
  </head>
  <body>
    <div class="member-score-container">
      <h1>Điểm của thành viên: Nguyễn Văn A</h1>
      <form>
        <label for="member-score">Điểm</label>
        <input type="number" id="member-score" name="member-score" value="8.5" readonly>
        <input type="submit" value="Hiển thị điểm của thành viên khác">
      </form>
    </div>
    <script src="script.js"></script>
  </body>
  </html>

  <style>
  body {
    background-color: #f2f2f2;
    font-family: Arial, sans-serif;
  }
  .member-score-container {
    background-color: #fff;
    border-radius: 5px;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
    margin: 50px auto;
    padding: 20px;
    text-align: center;
    width: 400px;
  }
  h1 {
    color: #333;
    font-size: 24px;
    margin-bottom: 20px;
    text-transform: uppercase;
  }
  form {
    display: inline-block;
    text-align: left;
  }
  label {
    display: block;
    font-size: 14px;
    margin-bottom: 10px;
    text-transform: uppercase;
  }
  input[type="number"] {
    background-color: #f2f2f2;
    border: none;
    border-radius: 3px;
    box-sizing: border-box;
    display: block;
    font-size: 16px;
    margin-bottom: 20px;
    padding: 10px;
    width: 100%;
  }
  input[type="submit"] {
    background-color: #4CAF50;
    border: none;
    border-radius: 3px;
    color: #fff;
    cursor: pointer;
    font-size: 16px;
    padding: 10px;
    width: 100%;
    transition: all 0.3s ease;
  }
  input[type="submit"]:hover {
    background-color: #3e8e41;
  }
  </style>

  <script>
  const form = document.querySelector('form');
    form.addEventListener('submit', (event) => {
      event.preventDefault(); // Prevent the form from submitting
      window.location.href = '#';
    });
    </script>

  
  <div class="maps-container wow fadeInUp">
      <a href="https://goo.gl/maps/gWzXpy7XisLX3xQLA?coh=178572&entry=tt"><img src="https://scontent.fhan2-5.fna.fbcdn.net/v/t1.15752-9/348387688_824547325805226_4870617880354541066_n.png?_nc_cat=104&ccb=1-7&_nc_sid=ae9488&_nc_ohc=BmCsPzL_1EgAX9Mxoj2&_nc_oc=AQmrxsbyWbDB8KhFNNm8zF3RLa3VNKIcX_JU47j15FnFqu4VGUiOT-EPrvgfFe36-y0&_nc_ht=scontent.fhan2-5.fna&oh=03_AdRwjXuNnaDPFoRsg4mgwOuHHtUG8lV7zgEikNqwL1lrsQ&oe=649649D3" alt=""></a>
  </div>

  <div class="page-section banner-home bg-image" style="background-image: url(images/banner-pattern.svg);">
    <div class="container py-5 py-lg-0">
      <div class="row align-items-center">
        <div class="col-lg-4 wow zoomIn">
          <div class="img-banner d-none d-lg-block">
            <img src="images/mobile_app.png" alt="">
          </div>
        </div>
        <div class="col-lg-8 wow fadeInRight">
          <h1 class="font-weight-normal mb-3">Get easy access of all features using One Health Application</h1>
          <a href="#"><img src="images/google_play.svg" alt=""></a>
          <a href="#" class="ml-2"><img src="images/app_store.svg" alt=""></a>
        </div>
      </div>
    </div>
  </div> <!-- .banner-Home -->

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
    </div>
  </footer>

<script src="js/jquery-3.5.1.min.js"></script>

<script src="js/bootstrap.bundle.min.js"></script>

<script src="vendor/owl-carousel/js/owl.carousel.min.js"></script>

<script src="vendor/wow/wow.min.js"></script>

<script src="js/google-maps.js"></script>

<script src="js/theme.js"></script>

<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAIA_zqjFMsJM_sxP9-6Pde5vVCTyJmUHM&callback=initMap"></script>
  
</body>
</html>