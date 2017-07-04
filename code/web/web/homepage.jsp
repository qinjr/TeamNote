<%--
  Created by IntelliJ IDEA.
  User: rudeigerc
  Date: 2017/6/30
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>

<div class="container">
    <div class="card">
        <div class="card-block">
            <div class="row">
                <div class="col-md-2 text-center mx-auto">
                    <img src="image/user_6.png" style="height: 100px; width: 100px;">
                </div>
                <div class="col-md-7">
                    <h4 class="card-title">rudeigerc</h4>
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
                        <i class="fa fa-pencil-square-o fa-fw" aria-hidden="true"></i>&nbsp;编辑个人资料
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
            <div class="tab-content" id="homepageTabContent">
                <!-- TODO: activity -->
                <div class="tab-pane fade show active" id="activity" role="tabpanel" aria-labelledby="activity-tab">activity</div>
                <!-- TODO: notebook -->
                <div class="tab-pane fade" id="notebook" role="tabpanel" aria-labelledby="notebook-tab">
                    <div>
                        <button class="btn btn-outline-success btn-notebook center-block" type="button">
                            <i class="fa fa-bookmark fa-fw" aria-hidden="true"></i>&nbsp;新建笔记本
                        </button>
                    </div>
                    <div class="row">
                        <div class="col-md-12" style="margin-top: 20px;">
                            <div class="row">
                                <div class="col-md-2 text-center mx-auto">
                                    <img src="image/notebook_2.png" style="height: 75px; width: 75px;">
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
                                        <small>创建者 <strong>rudeigerc</strong> · 所有者 <strong>rudeigerc</strong> · 修改时间 2017-06-04 11:32:37</small>
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
                    <div class="dropdown-divider"></div>
                </div>
                <!-- TODO: serverside processing -->
                <div class="tab-pane fade" id="workgroup" role="tabpanel" aria-labelledby="workgroup-tab">
                    <div class="alert alert-success" role="alert" style="margin-top: 16px;">
                        <i class="fa fa-info-circle" aria-hidden="true"></i>
                        该用户没有加入的工作组
                    </div>
                </div>
                <!-- TODO: collection -->
                <div class="tab-pane fade" id="collection" role="tabpanel" aria-labelledby="collection-tab">collection</div>
                <!-- TODO: follow -->
                <div class="tab-pane fade" id="follow" role="tabpanel" aria-labelledby="follow-tab">follow</div>
            </div>
        </div>
    </div>
    <footer>
        <p>&copy; 2017 TeamNote Team</p>
    </footer>
</div>

<%@ include file="footer.jsp"%>
<script type="text/javascript" src="<%=path%>/js/cooperate.js"></script>