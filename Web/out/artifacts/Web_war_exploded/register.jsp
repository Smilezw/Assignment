<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/12/27
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>注册</title>

    <style>
        * {
            margin: 0;
            padding: 0;
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
            color: #222222;
        }

        .default-form {
            display: block;
            position: absolute;
            top: 40%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 22.5rem;
            padding: 2.5rem;
            box-shadow: 0 0.375rem 1rem rgba(0, 0, 0, .2);
            border-radius: 3px;
        }

        .default-form__form-title {
            display: block;
            margin-bottom: 2rem;
        }

        .default-form__form-title--main-title {
            font-size: 2.5rem;
            text-align: center;
        }

        .default-form__form-title--subtitle {
            font-size: 1.125rem;
            text-align: center;
            opacity: 0.75;
            margin-top: .5rem;
        }

        .default-form__input-box, .default-form__submit-button {
            font-size: 1.125rem;
            padding: .5rem .75rem;
            border: 1px solid #ccc;
            border-radius: 3px;
            display: block;
            margin: 0 auto;
            width: 15rem;
            box-sizing: border-box;
        }

        .default-form__input-box::placeholder {
            opacity: .65;
        }

        .default-form__input-box:not(:last-child), .default-form__submit-button:not(:last-child) {
            margin-bottom: .75rem;
        }

        .default-form__submit-button {
            color: #444;
            background-color: #f0f0f0;
        }
    </style>
    <style>
        body{
            height: 100vh;
            width: 100%;
            /*background-image: url("{% static 'img/bg.jpg' %}");*/
            background-size: cover;
            background-position:center;
        }
    </style>
</head>

<body>
<form class="default-form" action="register" method="post">
    <header class="default-form__form-title">
        <h1 class="default-form__form-title--main-title">Register</h1>
        <p class="default-form__form-title--subtitle">Please submit your message</p>
    </header>
    <div class="default-form__form-main-area">
        <input class="default-form__input-box default-form__input-box--username" type="text" placeholder="Enter Username" name="username">
        <input class="default-form__input-box default-form__input-box--password" type="password" placeholder="Enter Password" name="password">
        <input class="default-form__submit-button" type="submit" value="Register!" name="submit1">
    </div>
    <br>
    <h5>  ${errorInfo}  </h5>
</form>
</body>
</html>
