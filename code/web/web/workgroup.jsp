<%--
  Created by IntelliJ IDEA.
  User: rudeigerc
  Date: 2017/7/3
  Time: 09:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>

<div class="row">
    <div class="col-md-2">
        <div style="padding-left: 24px;">
            <button type="button" class="btn btn-outline-secondary navbar-toggle offcanvas-toggle is-open" data-toggle="offcanvas" data-target="#left-sidebar" style="width: 45px; height: 45px; border: none;">
                <i class="fa fa-chevron-right" aria-hidden="true"></i>
            </button>
        </div>
    </div>
    <div class="col-md-8">
        <div class="container">
            <label for="editor"></label>
            <textarea name="editor" id="editor">

            </textarea>
        </div>
    </div>
    <div class="col-md-2">
        <button class="button btn-outline-secondary navbar-toggle offcanvas-toggle" data-toggle="offcanvas" data-target="#right-sidebar" style="border: none; float: right; width: 60px;">
            <img class="img-50px" src="image/test.jpg">
            <img class="img-50px" src="image/guest.png">
            <i class="fa fa-commenting-o fa-2x" aria-hidden="true"></i>
        </button>
    </div>
</div>

<nav class="navbar navbar-default" role="navigation">
    <div class="navbar-offcanvas navbar-offcanvas-touch navbar-offcanvas-fade in" id="left-sidebar">
        <div class="pre-scrollable">
            <img class="img-75px" src="image/card_1.png" style="margin-top: 15px; ">
            <button type="button" class="btn btn-outline-secondary btn-back navbar-toggle offcanvas-toggle" data-toggle="offcanvas" data-target="#left-sidebar">
                <i class="fa fa-chevron-left" aria-hidden="true"></i>
            </button>
            <br><br>
            <h4 class="card-title">Coursera Machine Learning 总结</h4>
            <h5>Abstract</h5>
            <h5>Logistic Regression</h5>
            <h5>Naive Bayes</h5>
            <h5>Naive Bayes</h5>
            <h5>Naive Bayes</h5>
            <h5>Naive Bayes</h5>
            <h5>Naive Bayes</h5>
            <h5>Naive Bayes</h5>
            <h5>Naive Bayes</h5>
            <h5>Naive Bayes</h5>
            <h5>Naive Bayes</h5>
        </div>
        <div class="dropdown-divider"></div>
        <div class="sidebar-btn">
            <button class="btn btn-outline-danger" data-toggle="modal" data-target="#giveOwnershipModal" style="padding: 8px">
                转让所有权
            </button>
            <button class="btn btn-outline-primary">邀请用户</button>
            <button class="btn btn-outline-warning">审核</button><br>
            <button class="btn btn-outline-success">历史记录</button>
            <button class="btn btn-outline-primary">设置</button>
            <button class="btn btn-danger">取消</button>
            <button class="btn btn-success">保存</button>
        </div>
    </div>
</nav>

<nav class="navbar navbar-default" role="navigation">
    <div class="navbar-offcanvas navbar-offcanvas-touch navbar-offcanvas-fade navbar-offcanvas-right" id="right-sidebar">
        <div class="pre-scrollable">

        </div>

    </div>
</nav>

<div class="modal fade" id="giveOwnershipModal" tabindex="-1" role="dialog" aria-labelledby="giveOwnershipModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="giveOwnershipModalLabel" style="color: #D0021B">
                    <i class="fa fa-exchange" aria-hidden="true"></i>&nbsp;转让所有权
                </h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                ...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary">确认</button>
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.jsp"%>
<script type="text/javascript" src="<%=path%>/js/bootstrap.offcanvas.min.js"></script>
<script type="text/javascript" src="https://cdn.ckeditor.com/4.7.1/full/ckeditor.js"></script>
<script type="text/javascript" src="<%=path%>/js/cooperate.js"></script>
<script>
    CKEDITOR.replace( 'editor', {
        customConfig: '<%=path%>/ckeditor/js/config.js',
        skin: 'bootstrapck,<%=path%>/ckeditor/skins/bootstrapck/'
    } );
</script>
