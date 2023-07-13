<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 6/6/2023
  Time: 10:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
  <title>Login 10</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">

  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

  <link rel="stylesheet" href="css/style.css">

</head>
<body class="img js-fullheight" style="background-image: url(images/pexels-photo-48604.jpeg);">
<section class="ftco-section">
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-6 text-center mb-5">
        <h2 class="heading-section">Medical Booking</h2>
      </div>
    </div>
    <div class="row justify-content-center">
      <div class="col-md-6 col-lg-4">
        <div class="login-wrap p-0">
          <h3 class="mb-4 text-center">Reset Password</h3>
          <form action="reset" class="login-form" method="post">
            <div class="form-group">
              <input type="text" hidden="hidden" class="form-control" placeholder="Username" value="${requestScope.account.username}" name="username" required>
            </div>
            <div class="form-group">
              <input  type="text" class="form-control" name="password" placeholder="Password" required>
              <span toggle="#password-field" class="fa fa-fw fa-eye field-icon toggle-password"></span>
            </div>
            <div class="form-group">
              <input  type="text" class="form-control" name="repassword" placeholder="RePassword" required>
              <span toggle="#password-field" class="fa fa-fw fa-eye field-icon toggle-password"></span>
            </div>
            <%--                        <div class="form-group">--%>
            <%--                            <select name="roleid" class="form-control">--%>
            <%--                                <option style="color: Blue" value="2">Client</option>--%>
            <%--                                <option style="color: Blue" value="1">Doctor</option>--%>
            <%--                                <option style="color: Blue" value="0">Admin</option>--%>
            <%--                            </select>--%>
            <%--                        </div>--%>
            <div class="form-group">
              <button type="submit" class="form-control btn btn-primary submit px-3">Submit</button>
            </div>
            <div class="form-group d-md-flex">
              <%--              <div class="w-50">--%>
              <%--                <label class="checkbox-wrap checkbox-primary">Remember Me--%>
              <%--                  <input type="checkbox" checked>--%>
              <%--                  <span class="checkmark"></span>--%>
              <%--                </label>--%>
              <%--              </div>--%>
              <div class="w-50 text-md-left">
                <a href="login" style="color: #fff">Login</a>
              </div>
              <div class="w-50 text-md-right">
                <a href="register" style="color: #fff">Register</a>
              </div>
            </div>
          </form>
          <%--                    <p class="w-100 text-center">&mdash; Or Login With &mdash;</p>--%>
          <%--                    <div class="social d-flex text-center">--%>
          <%--                        <a href="#" class="px-2 py-2 mr-md-1 rounded"><span class="ion-logo-facebook mr-2"></span> Facebook</a>--%>
          <%--                        <a href="#" class="px-2 py-2 ml-md-1 rounded"><span class="ion-logo-twitter mr-2"></span> Twitter</a>--%>
          <%--                    </div>--%>
        </div>
      </div>
    </div>
  </div>
</section>

<script src="js/jquery.min.js"></script>
<script src="js/popper.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/main.js"></script>

</body>
</html>


</body>
</html>
