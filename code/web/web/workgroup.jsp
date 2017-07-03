<%--
  Created by IntelliJ IDEA.
  User: rudeigerc
  Date: 2017/7/3
  Time: 09:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <%
            String path = request.getContextPath();
        %>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>TeamNote - rudeigerc</title>
        <meta name="author" content="TeamNote Team">
        <meta name="description" content="A platform for people to share and create notes together.">
        <link rel="icon" href="<%=path%>/icon/favicon.png">
        <link type="text/css" rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
        <link type="text/css" rel="stylesheet" href="<%=path%>/css/bootstrap.offcanvas.css"/>
        <link type="text/css" rel="stylesheet" href="<%=path%>/css/font-awesome.min.css"/>
        <link type="text/css" rel="stylesheet" href="<%=path%>/css/teamnote.css"/>
    </head>
    <body>
        <nav class="navbar navbar-toggleable-md mb-4 bg-faded">
            <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <a class="navbar-brand" href="index.jsp">TeamNote</a>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="index.jsp"><i class="fa fa-home fa-fw" aria-hidden="true"></i>&nbsp;首页</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#"><i class="fa fa-paint-brush fa-fw" aria-hidden="true"></i>&nbsp;探索</a>
                    </li>
                </ul>
                <div class="col-lg-4">
                    <div class="input-group" style="height: 46px;">
                        <input type="text" class="form-control" placeholder="搜索" aria-label="search">
                        <span class="input-group-btn">
                            <button class="btn btn-outline-secondary" type="button">
                                <i class="fa fa-search" aria-hidden="true"></i>
                            </button>
                        </span>
                    </div>
                </div>
                <button class="btn btn-outline-secondary" type="button" style="border: none">
                    <i class="fa fa-bell" aria-hidden="true"></i>
                </button>
                <button class="btn btn-outline-secondary" type="button" style="border: none">
                    <i class="fa fa-envelope" aria-hidden="true"></i>
                </button>
                <ul class="navbar-nav">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                            <img src="image/test.jpg" style="height: 30px; width: 30px;">
                        </a>
                        <div class="dropdown-menu dropdown-menu-right">
                            <a class="dropdown-item" href="homepage.jsp">
                                <i class="fa fa-map fa-fw" aria-hidden="true"></i>&nbsp;我的主页
                            </a>
                            <a class="dropdown-item" href="#">
                                <i class="fa fa-book fa-fw" aria-hidden="true"></i>&nbsp;我的笔记
                            </a>
                            <a class="dropdown-item" href="#">
                                <i class="fa fa-cog fa-spin fa-fw" aria-hidden="true"></i>&nbsp;设置
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#" style="color: #D0021B">
                                <i class="fa fa-exclamation-circle fa-fw" aria-hidden="true"></i>&nbsp;登出
                            </a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
        <div style="padding-left: 24px;">
            <button type="button" class="btn btn-outline-secondary navbar-toggle offcanvas-toggle is-open" data-toggle="offcanvas" data-target="#left-sidebar" style="width: 45px; height: 45px;">
                <i class="fa fa-chevron-right" aria-hidden="true"></i>
            </button>
        </div>
        <div class="container">
            <nav class="navbar navbar-default" role="navigation">
                <div class="navbar-offcanvas navbar-offcanvas-touch navbar-offcanvas-fade in" id="left-sidebar">
                    <img src="image/card_1.png" style="width: 75px; height: 75px; margin-top: 15px; ">
                    <br><br>
                    <h4 class="card-title">Coursera Machine Learning 总结</h4>
                    <h5>Abstract</h5>
                    <h5>Naive Bayes</h5>
                    <h5>Naive Bayes</h5><h5>Naive Bayes</h5><h5>Naive Bayes</h5><h5>Naive Bayes</h5><h5>Naive Bayes</h5><h5>Naive Bayes</h5>
                    <div class="dropdown-divider" style="margin-top: 100px"></div>
                    <div class="sidebar-btn">
                        <button class="btn btn-outline-danger" style="padding: 8px">转让所有权</button>
                        <button class="btn btn-outline-primary">邀请用户</button>
                        <br><br>
                        <button class="btn btn-outline-warning">审核</button>
                        <br><br>
                        <button class="btn btn-outline-success">历史记录</button>
                        <button class="btn btn-outline-primary">设置</button>
                        <br><br>
                        <button class="btn btn-danger">取消</button>
                        <button class="btn btn-success">保存</button>
                    </div>

                </div>
            </nav>

        </div>

        <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js"></script>
        <script type="text/javascript" src="https://cdn.bootcss.com/popper.js/1.9.3/umd/popper.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
        <script type="text/javascript" src="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="<%=path%>/js/bootstrap.offcanvas.min.js"></script>
        <script type="text/javascript" src="https://unpkg.com/vue/dist/vue.js"></script>
    </body>
</html>
