<%--
  Created by IntelliJ IDEA.
  User: rudeigerc
  Date: 2017/6/30
  Time: 16:44
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
        <title>TeamNote - rudeigerc</title>
        <meta name="author" content="TeamNote Team">
        <meta name="description" content="A platform for people to share and create notes together.">
        <link rel="icon" href="<%=path%>/icon/favicon.png">
        <link type="text/css" rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
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
                            <a class="dropdown-item" href="#">
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
        <div class="container">
            <div class="card">
                <div class="card-block">
                    <div class="row">
                        <div class="col-md-2 text-center mx-auto">
                            <img src="image/test.jpg" style="height: 100px; width: 100px;">
                        </div>
                        <div class="col-md-7">
                            <h4 class="card-title">
                                <i class="fa fa-user" aria-hidden="true"></i>&nbsp;rudeigerc
                            </h4>
                            <p class="card-subtitle mb-2 text-muted">
                                <i class="fa fa-tag" aria-hidden="true"></i>&nbsp;Shanghai Jiao Tong University Software Engineering
                            </p>
                            <p class="card-subtitle mb-2 text-muted">
                                <i class="fa fa-envelope-o" aria-hidden="true"></i>&nbsp;rudeigerc@gmail.com
                            </p>
                            <p>关注人 20 · 关注者 30</p>
                        </div>
                        <div class="col-md-3">
                            <button class="btn btn-outline-primary center-block" type="button">
                                <i class="fa Example of pencil-square-o fa-pencil-square-o fa-fw" aria-hidden="true"></i>&nbsp;编辑个人资料
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="card-block">
                    <ul class="nav nav-tabs" id="homepageTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="activity-tab" data-toggle="tab" href="#activity" role="tab" aria-controls="activity" aria-expanded="true">动态</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="notebook-tab" data-toggle="tab" href="#notebook" role="tab" aria-controls="notebook">笔记本</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="workgroup-tab" data-toggle="tab" href="#workgroup" role="tab" aria-controls="workgroup">工作组</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="collection-tab" data-toggle="tab" href="#collection" role="tab" aria-controls="collection">收藏</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="follow-tab" data-toggle="tab" href="#follow" role="tab" aria-controls="follow">关注</a>
                        </li>
                    </ul>
                    <div class="tab-content" id="myTabContent">
                        <div class="tab-pane fade show active" id="activity" role="tabpanel" aria-labelledby="activity-tab">activity</div>
                        <div class="tab-pane fade" id="notebook" role="tabpanel" aria-labelledby="notebook-tab">notebook</div>
                        <div class="tab-pane fade" id="workgroup" role="tabpanel" aria-labelledby="workgroup-tab">workgroup</div>
                        <div class="tab-pane fade" id="collection" role="tabpanel" aria-labelledby="collection-tab">collection</div>
                        <div class="tab-pane fade" id="follow" role="tabpanel" aria-labelledby="follow-tab">follow</div>
                    </div>
                </div>
            </div>
            <footer>
                <p>&copy; 2017 TeamNote Team</p>
            </footer>
        </div>

        <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js"></script>
        <script type="text/javascript" src="https://cdn.bootcss.com/popper.js/1.9.3/umd/popper.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
        <script type="text/javascript" src="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="https://unpkg.com/vue/dist/vue.js"></script>
    </body>
</html>
