<%--
  Created by IntelliJ IDEA.
  User: rudeigerc
  Date: 2017/7/10
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>
<div class="container">
    <div class="card" id="settings" style="width: 800px;">
        <div class="card-block">
            <nav class="nav nav-tabs" id="settingsTab" role="tablist">
                <a class="nav-item nav-link active" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-expanded="true">个人信息</a>
                <a class="nav-item nav-link" id="account-tab" data-toggle="tab" href="#account" role="tab" aria-controls="profile" aria-expanded="true">账号</a>
                <a class="nav-item nav-link" id="notification-tab" data-toggle="tab" href="#notification" role="tab" aria-controls="notification">通知</a>
            </nav>
            <div class="tab-content" id="settings-tabContent">
                <!-- TODO: profile -->
                <div class="tab-pane fade show active" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                    <div class="row">
                        <div class="col-md-8">
                            <form role="form" style="width: 450px;">
                                <div class="form-group">
                                    <input type="hidden" class="form-control" id="userId">
                                </div>
                                <div class="form-group">
                                    <h6><label for="username" class="form-control-label">用户名</label></h6>
                                    <input type="text" class="form-control" id="username" readonly="readonly">
                                </div>
                                <div class="form-group">
                                    <h6><label for="email" class="form-control-label">邮箱</label></h6>
                                    <input type="email" class="form-control" id="email">
                                </div>
                                <div class="form-group">
                                    <h6><label for="phone" class="form-control-label">手机号码</label></h6>
                                    <input type="text" class="form-control" id="phone">
                                </div>
                                <div class="form-group">
                                    <h6><label for="description" class="form-control-label">简介</label></h6>
                                    <textarea class="form-control" rows="3" id="description"></textarea>
                                </div>
                                <button type="submit" class="btn btn-warning">修改</button>
                            </form>
                        </div>
                        <div class="col-md-4" style="margin-top: 12px;">
                            <form role="form">
                                <h6>个人头像</h6>
                                <img src="image/user_6.png" class="img-100px">
                                <div class="form-group">
                                    <label for="inputFile"></label>
                                    <input type="file" class="form-control-file" id="inputFile" aria-describedby="fileHelp">
                                    <small id="fileHelp" class="form-text text-muted">test</small>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- TODO: account -->
                <div class="tab-pane fade" id="account" role="tabpanel" aria-labelledby="account-tab">
                    <!-- modify password -->
                    <h5 class="card-title" style="margin-top: 12px;">
                        <i class="fa fa-unlock-alt" aria-hidden="true"></i>&nbsp;修改密码
                    </h5>
                    <div class="dropdown-divider"></div>
                    <form data-toggle="validator" role="form" style="width: 300px;">
                        <div class="form-group">
                            <h6>
                                <label for="old_password" class="form-control-label">原密码</label>
                            </h6>
                            <input type="password" class="form-control" id="old_password" required>
                        </div>
                        <div class="form-group has-feedback">
                            <h6>
                                <label for="new_password" class="form-control-label">新密码</label>
                                <span class="fa form-control-feedback" aria-hidden="true"></span>
                            </h6>
                            <input type="password" data-minlength="6" class="form-control" id="new_password" data-error="密码至少应有六位数。" required>
                            <div class="help-block with-errors text-danger"></div>
                        </div>
                        <div class="form-group has-feedback">
                            <h6>
                                <label for="new_password_confirm" class="form-control-label">新密码确认</label>
                                <span class="fa form-control-feedback" aria-hidden="true"></span>
                            </h6>
                            <input type="password" data-minlength="6" class="form-control" id="new_password_confirm"
                                   data-error="" data-match="#new_password" data-match-error="两次输入的密码不一致，请重新输入。" required>
                            <div class="help-block with-errors text-danger"></div>
                        </div>
                        <button type="submit" class="btn btn-warning">修改密码</button>
                    </form>
                    <div class="dropdown-divider"></div>
                    <!-- delete account -->
                    <h5 class="card-title card-danger-kai" style="margin-top: 12px;">
                        <i class="fa fa-user-times" aria-hidden="true"></i>&nbsp;删除账号
                    </h5>
                    <div class="dropdown-divider"></div>
                    <small id="delete_warning" class="form-text text-muted" style="margin-bottom: 4px;">警告：账号删除后将会失去所有记录，请谨慎执行此命令。</small>
                    <button type="button" class="btn btn-outline-danger" aria-describedby="delete_warning">删除账号</button>
                </div>
                <!-- TODO: notification -->
                <div class="tab-pane fade" id="notification" role="tabpanel" aria-labelledby="notification-tab">
                    notification
                </div>

            </div>
        </div>
    </div>
    <footer>
        <p>&copy; 2017 TeamNote Team</p>
    </footer>
</div>

<%@ include file="footer.jsp"%>
<script type="text/javascript" src="<%=path%>/js/validator.js"></script>