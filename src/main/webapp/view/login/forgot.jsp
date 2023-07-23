<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>Quên mật khẩu</title>
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
                <h2 class="heading-section">Clinic TQTA</h2>
            </div>
        </div>
        <div class="row justify-content-center">
            <div class="col-md-6 col-lg-4">
                <div class="login-wrap p-0">
                    <h3 class="mb-4 text-center">Quên mật khẩu</h3>
                    <% String errorMessage = (String) request.getAttribute("messError"); %>
                    <% if (errorMessage != null && !errorMessage.isEmpty()) { %>
                    <div class="alert alert-danger" role="alert">
                        <%= errorMessage %>
                    </div>
                    <% } %>
                    <form action="forgot" class="login-form" method="post">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Mật khẩu" name="username" required>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" name="email" placeholder="Email" required>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="form-control btn btn-primary submit px-3">Xác nhận</button>
                        </div>
                        <div class="form-group d-md-flex">
                            <div class="w-50 text-md-left">
                                <a href="login" style="color: #fff">Đăng nhập</a>
                            </div>
                            <div class="w-50 text-md-right">
                                <a href="register" style="color: #fff">Đăng kí</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
</section>

<script src="js/jquery.min.js"></script>
<script src="js/popper.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/main.js"></script>

</body>
</html>
