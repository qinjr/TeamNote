<%--
  Created by IntelliJ IDEA.
  User: rudeigerc
  Date: 2017/6/30
  Time: 13:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-cmn-Hans">
    <head>
        <%
            String path = request.getContextPath();
        %>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>TeamNote</title>
        <meta name="author" content="TeamNote Team">
        <meta name="description" content="A platform for people to share and create notes together.">
        <link rel="icon" href="<%=path%>/icon/favicon.png">
        <link type="text/css" rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
        <link type="text/css" rel="stylesheet" href="<%=path%>/css/font-awesome.min.css"/>
        <link type="text/css" rel="stylesheet" href="<%=path%>/css/teamnote.css"/>
    </head>
    <body>
        <%
            if ("error".equals(request.getParameter("login"))) {
                response.getWriter().println("<script>alert('Invalid username or password')</script>");
            }
        %>
        <div id="particles-js">
            <div class="container container-center" style="width: 500px;">
                <p class="navbar-brand navbar-brand-center" style="font-size: 40px;">TeamNote</p>
                <nav class="nav nav-tabs" id="authTab" role="tablist">
                    <a class="nav-item nav-link active" id="login-tab" data-toggle="tab" href="#login" role="tab" aria-controls="login" aria-expanded="true">登录</a>
                    <a class="nav-item nav-link" id="signup-tab" data-toggle="tab" href="#signup" role="tab" aria-controls="signup">注册</a>
                </nav>
                <div class="tab-content" id="nav-tabContent">
                    <div class="tab-pane fade show active" id="login" role="tabpanel" aria-labelledby="login-tab">
                        <form method="post" action="login">
                            <div class="form-group">
                                <label for="username" class="col-form-label">用户名</label>
                                <input type="text" class="form-control" id="username" name="username" placeholder="用户名">
                            </div>
                            <div class="form-group">
                                <label for="password" class="col-form-label">密码</label>
                                <input type="password" class="form-control" id="password" name="password" placeholder="密码">
                            </div>
                            <div class="form-group">
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input class="form-check-input" type="checkbox"> 记住用户名与密码
                                    </label>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary">登录</button>
                        </form>
                    </div>
                    <div class="tab-pane fade" id="signup" role="tabpanel" aria-labelledby="signup-tab">
                        <form data-toggle="validator" role="form" method="post" action="register">
                            <div class="form-group has-feedback">
                                <label for="_username" class="col-form-label">用户名</label>
                                <input type="text" class="form-control" id="_username" name="_username" placeholder="用户名" required>
                                <span class="fa form-control-feedback" aria-hidden="true"></span>
                            </div>
                            <div class="form-group has-feedback">
                                <label for="_password" class="col-form-label">密码</label>
                                <input type="password" data-minlength="6" class="form-control" id="_password" name="_password" placeholder="密码" required>
                                <span class="fa form-control-feedback" aria-hidden="true"></span>
                            </div>
                            <div class="form-group has-feedback">
                                <label for="phone" class="col-form-label">手机号码</label>
                                <input type="text" pattern="^[0-9]{11}$" class="form-control" id="phone" name="phone" placeholder="手机号码" data-error="请输入正确的手机号码" required>
                                <span class="fa form-control-feedback" aria-hidden="true"></span>
                                <div class="help-block with-errors text-danger"></div>
                            </div>
                            <div class="form-group has-feedback">
                                <label for="email" class="col-form-label">邮箱</label>
                                <input type="email" class="form-control" id="email" name="email" placeholder="邮箱" data-error="请输入正确的邮箱" required>
                                <span class="fa form-control-feedback" aria-hidden="true"></span>
                                <div class="help-block with-errors text-danger"></div>
                            </div>
                            <button type="submit" class="btn btn-success">注册</button>
                        </form>
                    </div>
                </div>
                <footer>
                    <p>&copy; 2017 TeamNote Team</p>
                </footer>
            </div>
        </div>


        <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js"></script>
        <script type="text/javascript" src="https://cdn.bootcss.com/popper.js/1.9.3/umd/popper.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
        <script type="text/javascript" src="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="https://unpkg.com/vue/dist/vue.js"></script>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/particles.js/2.0.0/particles.min.js"></script>
        <script type="text/javascript" src="<%=path%>/js/validator.js"></script>
        <script type="text/javascript" src="<%=path%>/js/app.js"></script>
    </body>
</html>
