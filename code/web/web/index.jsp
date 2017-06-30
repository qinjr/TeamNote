<%--
  Created by IntelliJ IDEA.
  User: rudeigerc
  Date: 2017/6/27
  Time: 10:27
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
        <nav class="navbar navbar-toggleable-md mb-4 bg-faded">
            <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <a class="navbar-brand" href="#">TeamNote</a>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="#"><i class="fa fa-home fa-fw" aria-hidden="true"></i>&nbsp;首页</a>
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
                                <i class="fa fa-cog fa-fw" aria-hidden="true"></i>&nbsp;设置
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

            <!-- test card 1 -->
            <div class="card">
                <div class="card-block">
                    <div class="row">
                        <div class="col-md-12">
                            <p style="color: #D8D8D8"><small>来自标签 <strong>机器学习</strong></small></p>
                            <div class="row">
                                <div class="col-md-2 text-center mx-auto">
                                    <img src="image/card_1.png" style="height: 100px; width: 100px;">
                                </div>
                                <div class="col-md-10">
                                    <h4 class="card-title">Coursera Machine Learning 总结</h4>
                                    <h6 class="card-subtitle mb-2 text-muted">
                                        <i class="fa fa-tag" aria-hidden="true"></i>
                                        机器学习 · Logistic 回归
                                    </h6>
                                    <p class="card-text" style="word-break: break-all;">
                                        逻辑回归（英语：Logistic regression 或logit regression），即逻辑模型（英语：Logit model，也译作“评定模型”、“分类评定模型”）是离散选择法模型之一，属于多重变量分析范畴，是社会学、生物统计学、临床、数量心理学、计量经济学、市场营销等统计实证分析的常用方法。
                                    </p>
                                    <footer>
                                        <small>创建者 <strong>rudeigerc</strong> · 所有者 <strong>rudeigerc</strong> · 修改时间 2017-06-03 23:34:23</small>
                                        <br><br>
                                        <button class="btn btn-outline-secondary center-block" type="button" style="border: none;">
                                            <i class="fa fa-star fa-fw" aria-hidden="true"></i>&nbsp;21
                                        </button>
                                        <button class="btn btn-outline-secondary center-block" type="button" style="border: none;">
                                            <i class="fa fa-comments fa-fw" aria-hidden="true"></i>&nbsp;评论
                                        </button>
                                        <button class="btn btn-outline-secondary center-block" type="button" style="border: none;">
                                            <i class="fa fa-flag fa-fw" aria-hidden="true"></i>&nbsp;收藏
                                        </button>
                                        <button class="btn btn-outline-secondary center-block" type="button" style="border: none;">
                                            <i class="fa fa-exclamation-triangle fa-fw" aria-hidden="true"></i>&nbsp;举报
                                        </button>
                                    </footer>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- test card 2 -->
            <div class="card">
                <div class="card-block">
                    <div class="row">
                        <div class="col-md-12">
                            <p style="color: #D8D8D8"><small>来自标签 <strong>Spring</strong></small></p>
                            <div class="row">
                                <div class="col-md-2 text-center mx-auto">
                                    <img src="image/card_2.png" style="height: 100px; width: 100px;">
                                </div>
                                <div class="col-md-10">
                                    <h4 class="card-title">Spring Security 笔记</h4>
                                    <h6 class="card-subtitle mb-2 text-muted">
                                        <i class="fa fa-tag" aria-hidden="true"></i>
                                        Spring · Spring Security
                                    </h6>
                                    <p class="card-text" style="word-break: break-all;">
                                        Spring Security is a framework that focuses on providing both authentication and authorization to Java applications. Like all Spring projects, the real power of Spring Security is found in how easily it can be extended to meet custom requirements.
                                    </p>
                                    <footer>
                                        <small>创建者 <strong>rudeigerc</strong> · 所有者 <strong>rudeigerc</strong> · 修改时间 2017-06-03 23:34:23</small>
                                        <br><br>
                                        <button class="btn btn-outline-secondary center-block" type="button" style="border: none;">
                                            <i class="fa fa-star fa-fw" aria-hidden="true"></i>&nbsp;20
                                        </button>
                                        <button class="btn btn-outline-secondary center-block" type="button" style="border: none;">
                                            <i class="fa fa-comments fa-fw" aria-hidden="true"></i>&nbsp;评论
                                        </button>
                                        <button class="btn btn-outline-secondary center-block" type="button" style="border: none;">
                                            <i class="fa fa-flag fa-fw" aria-hidden="true"></i>&nbsp;收藏
                                        </button>
                                        <button class="btn btn-outline-secondary center-block" type="button" style="border: none;">
                                            <i class="fa fa-exclamation-triangle fa-fw" aria-hidden="true"></i>&nbsp;举报
                                        </button>
                                    </footer>
                                </div>
                            </div>
                        </div>
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