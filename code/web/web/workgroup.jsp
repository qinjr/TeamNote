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
            <button type="button" class="btn btn-outline-secondary navbar-toggle offcanvas-toggle is-open" data-toggle="offcanvas" data-target="#left-sidebar" style="width: 45px; height: 45px;">
                <i class="fa fa-chevron-right" aria-hidden="true"></i>
            </button>
        </div>
    </div>
    <div class="col-md-8">
        <div class="container" style="">
            <label for="editor"></label>
            <textarea name="editor" id="editor">
                        This is my textarea to be replaced with CKEditor.
                    </textarea>
        </div>
    </div>
    <div class="col-md-2">
        <button class="button btn-outline-secondary" style="border: none; float: right; width: 60px;">
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

<%@ include file="footer.jsp"%>
<script type="text/javascript" src="<%=path%>/js/bootstrap.offcanvas.min.js"></script>
<script type="text/javascript" src="ckeditor4.7/ckeditor.js"></script>
<script>
    CKEDITOR.replace( 'editor' );
</script>