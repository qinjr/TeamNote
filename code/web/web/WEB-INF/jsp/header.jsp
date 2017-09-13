<%--
  Created by IntelliJ IDEA.
  User: rudeigerc
  Date: 2017/7/3
  Time: 15:49
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
        <link type="text/css" rel="stylesheet" href="<%=path%>/css/bootstrap.offcanvas.css"/>
        <link type="text/css" rel="stylesheet" href="<%=path%>/css/font-awesome.min.css"/>
        <link type="text/css" rel="stylesheet" href="<%=path%>/css/teamnote.css"/>

        <script type="text/javascript" src="https://vuejs.org/js/vue.js"></script>
        <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/vue-resource@1.3.4"></script>
    </head>
    <body>
        <nav class="navbar navbar-toggleable-md mb-4 bg-faded">
            <button class="navbar-toggler navbar-toggler-right btn-navbar-collapse" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <i class="fa fa-bars fa-fw" aria-hidden="true"></i>
            </button>
            <a class="navbar-brand" href="<%=path%>/index">TeamNote</a>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="<%=path%>/index"><i class="fa fa-home fa-fw" aria-hidden="true"></i>&nbsp;首页</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=path%>/explore"><i class="fa fa-paint-brush fa-fw" aria-hidden="true"></i>&nbsp;探索</a>
                    </li>
                </ul>
                <div class="col-lg-5">
                    <div class="input-group" style="height: 46px;">
                        <input type="text" id="keyWord" class="form-control" placeholder="搜索" aria-label="search">
                        <select class="custom-select" id="search-filter">
                            <option selected value="notebook">笔记本</option>
                            <option value="user">用户</option>
                        </select>
                        <span class="input-group-btn">
                            <button class="btn btn-outline-secondary" id="search" type="button">
                                <i class="fa fa-search" aria-hidden="true"></i>
                            </button>
                        </span>
                    </div>
                </div>
                <button class="btn btn-outline-secondary" type="button" style="border: none" data-toggle="modal" data-target="#notificationModal">
                    <i class="fa fa-bell" aria-hidden="true"></i>
                </button>
                <button class="btn btn-outline-secondary disabled" type="button" style="border: none">
                    <i class="fa fa-envelope" aria-hidden="true"></i>
                </button>
                <ul class="navbar-nav">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                            <img class="rounded" src="<%=path%>/icon/favicon-white.png" :src="'<%=path%>/' + avatar" id="user_avatar" style="height: 30px; width: 30px;">
                        </a>
                        <div class="dropdown-menu dropdown-menu-right">
                            <p class="dropdown-item username" style="margin-bottom: 0;">
                                <i class="fa fa-user fa-fw" aria-hidden="true"></i>&nbsp;{{ username }}
                            </p>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" :href="'<%=path%>/homepage?userId=' + userId">
                                <i class="fa fa-map fa-fw" aria-hidden="true"></i>&nbsp;我的主页
                            </a>
                            <a class="dropdown-item" href="<%=path%>/settings">
                                <i class="fa fa-cog fa-spin fa-fw" aria-hidden="true"></i>&nbsp;设置
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="<%=path%>/logout" style="color: #D0021B">
                                <i class="fa fa-exclamation-circle fa-fw" aria-hidden="true"></i>&nbsp;登出
                            </a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>

        <div class="modal fade" id="notificationModal" tabindex="-1" role="dialog" aria-labelledby="notificationModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="notificationModalLabel">
                            <i class="fa fa-bell" aria-hidden="true"></i>&nbsp;通知
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="list-group">
                            <div v-for="(_notification, index) in notification">
                                <a class="list-group-item list-group-item-action flex-column align-items-start"
                                   :href="'#notification_collapse_' + index" data-toggle="collapse" aria-expanded="false" :aria-controls="'notification_collapse_' + index" :key="index">
                                    <div class="d-flex w-100 justify-content-between">
                                        <h5 class="mb-1">
                                            <i class="fa fa-user-o" aria-hidden="true"></i>&nbsp;系统管理员
                                        </h5>
                                        <small>{{ date(_notification.datetime) }}</small>
                                    </div>
                                </a>
                                <div class="collapse" :id="'notification_collapse_' + index">
                                    <div class="card card-block">
                                        {{ _notification.content }}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    <script type="text/javascript">
        var user = new Vue({
            el: '.dropdown',
            data: {
                avatar: null,
                username: null,
                userId : null
            },
            created : function () {
                this.$http.get('/teamnote/loginUserDetail').then(function(response) {
                    user.userId = JSON.parse(response.body.user).userId;
                    user.avatar = JSON.parse(response.body.user).avatar;
                    user.username = JSON.parse(response.body.user).username;
                }, function(response) {
                    console.log("navbar error");
                });
            }
        });

        $('#search').click(function(){
            var keyWord = $('#keyWord').val();
            var type = $('#search-filter').val();
            $.ajax({
                url : "/teamnote/search",
                dataType : "json",
                type : "get",
                data : {
                    keyWord : keyWord,
                    type : type
                },
                success : function(data) {
                    index.note = [];
                    index.user = [];
                    if (type === 'notebook') {
                        index.note = data;
                    } else if (type === 'user') {
                        index.user = data;
                    }

                }
            })
        });

        var notification = new Vue({
            el: '#notificationModal',
            data: {
                notification: []
            },
            created : function () {
                this.$http.get('/teamnote/getNotices').then(function(response) {
                    var json = JSON.parse(response.body.result);
                    for (var _json in json) {
                        this.notification.push(JSON.parse(json[_json]));
                    }
                }, function(response) {
                    console.log(response);
                });
            },
            methods: {
                link: function(content) {
                    var index = content.indexOf("请点击：") + 4;
                    var link = content.substring(0, index) + "http://localhost:8080/teamnote/" + content.substring(index + 1, content.length);
                    return link;
                },
                date: function(_date) {
                    return moment(_date, "ddd MMM DD HH:mm:ss z YYYY").format("YYYY-MM-DD HH:mm:ss");
                }
            }
        })
    </script>