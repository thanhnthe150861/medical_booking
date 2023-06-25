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
              <a class="nav-link" href="../home.jsp">About Us</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="doctors.jsp">Doctors</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="../patient/booking.jsp">Booking</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="vip.jsp">VIP</a>
            </li>
            <li class="nav-item">
              <a class="btn btn-primary ml-lg-3" href="login.html">Login / Register</a>
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
              <p><a href="viewMedicalRecord.jsp" class=""><span></span> Medical Records</a></p>
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
        <h1 class="text-center wow fadeInUp">Medicak records</h1>
        <form>
          <label for="patient-name">Tên bệnh nhân:</label>
          <input type="text" id="patient-name" name="patient-name" required>
           <label for="patient-age">Tuổi:</label>
          <input type="number" id="patient-age" name="patient-age" required>
           <label for="patient-gender">Giới tính:</label>
           <input type="text" id="patient-gender" name="patient-gender" required>
           <label for="patient-symptoms">Triệu chứng:</label>
          <textarea id="patient-symptoms" name="patient-symptoms" required></textarea>
           <label for="patient-diagnosis">Chuẩn đoán:</label>
           <input type="text" id="patient-diagnosis" name="patient-diagnosis" required>
           
        </form>
        
      </div>
    </div>
    
<style>
  form {
  max-width: 500px;
  margin: 0 auto;
}
 label {
  display: block;
  margin-top: 10px;
}
 input, select, textarea {
  display: block;
  width: 100%;
  padding: 5px;
  margin-top: 5px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 3px;
}
 button[type="submit"] {
  display: block;
  margin: 10px auto;
  padding: 10px 20px;
  background-color: #4CAF50;
  color: #fff;
  border: none;
  border-radius: 3px;
  cursor: pointer;
}
 button[type="submit"]:hover {
  background-color: #3e8e41;
}
</style>

<script>
  // Lấy dữ liệu từ cơ sở dữ liệu
const patientData = {
  name: "Nguyễn Văn A",
  age: 30,
  gender: "Nam",
  symptoms: "Đau đầu, mệt mỏi",
  diagnosis: "Mất ngủ"
};
 // Điền dữ liệu vào các phần tử trong form
document.getElementById("patient-name").value = patientData.name;
document.getElementById("patient-age").value = patientData.age;
document.getElementById("patient-gender").value = patientData.gender;
document.getElementById("patient-symptoms").value = patientData.symptoms;
document.getElementById("patient-diagnosis").value = patientData.diagnosis;

</script>
    



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

<script src="js/theme.js"></script>
  
</body>
</html>