<%--
  Created by IntelliJ IDEA.
  User: rudeigerc
  Date: 2017/7/18
  Time: 09:04
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
            <textarea name="editor" id="editor"></textarea>
            <footer>
                <p>&copy; 2017 TeamNote Team</p>
            </footer>
        </div>
    </div>
    <div class="col-md-2">
        <button class="button btn-outline-secondary navbar-toggle offcanvas-toggle" id="showChat" data-toggle="offcanvas" data-target="#right-sidebar" style="border: none; float: right; width: 60px;">
            TMP
        </button>

    </div>
</div>

<!-- left sidebar -->
<nav class="navbar navbar-default" role="navigation">
    <div class="navbar-offcanvas navbar-offcanvas-touch navbar-offcanvas-fade in" id="left-sidebar">
        <div class="pre-scrollable" id="left-sidebar-nav" style="padding-right: 10px;">

        </div>
        <div class="dropdown-divider"></div>
        <div class="sidebar-btn">
            <button class="btn btn-outline-warning" id="giveOwnership" style="padding: 8px">转让所有权</button>
            <button class="btn btn-outline-primary" id="inviteCollaborator">邀请用户</button>
            <button class="btn btn-secondary" id="newNote">新建笔记</button>
            <button class="btn btn-outline-primary" id="config" data-toggle="modal" data-target="#configModal">设置</button>
            <button class="btn btn-danger">取消</button>
            <button class="btn btn-success" id="callDialog">保存</button>
            <button class="btn btn-outline-success" style="display:none" id="chooseType">导出</button>
            <button class="btn btn-outline-success" id="uploadNote">导入</button>
            <button class="btn btn-outline-secondary" id="changeMode">读写</button>
        </div>
    </div>
</nav>

<!-- right sidebar -->
<nav class="navbar navbar-default" role="navigation">
    <div class="navbar-offcanvas navbar-offcanvas-touch navbar-offcanvas-fade navbar-offcanvas-right" id="right-sidebar">
        <div class="pre-scrollable" id="chatbox" style="max-height: 70%;">
            <div class="panel panel-primary" style="width: 100%; height: 70%;">
                <div class="panel-body" id="chatContent"></div>
            </div>
        </div>
        <div class="dropdown-divider" style="margin-bottom: 0;"></div>
        <div>
            <button type="button" class="btn btn-outline-secondary btn-back navbar-toggle offcanvas-toggle"
                    data-toggle="offcanvas" data-target="#right-sidebar" style="width: auto; height: auto;">
                <i class="fa fa-chevron-right" aria-hidden="true"></i>
            </button>
            <label for="msg"></label>
            <textarea rows="4" class="form-control" id="msg"></textarea>
            <input type="hidden" class="btn btn-outline-primary" value="发送" id="sendMsg">
            <!-- <input type="button" class="btn btn-secondary" value="清空" id="clearMsg"> -->
        </div>
    </div>
</nav>


<%@ include file="footer.jsp"%>
<script type="text/javascript" src="<%=path%>/js/bootstrap.offcanvas.js"></script>
<script type="text/javascript" src="<%=path%>/js/cooperate.js"></script>
<script type="text/javascript" src="https://cdn.ckeditor.com/4.7.1/full/ckeditor.js"></script>
<script>
    CKEDITOR.replace( 'editor', {
        customConfig: '<%=path%>/ckeditor/js/config.js',
        contentsCss: '<%=path%>/ckeditor/css/contents.css',
        skin: 'bootstrapck,<%=path%>/ckeditor/skins/bootstrapck/'
    });

    $('#changeMode').click(function(){
        if(!CKEDITOR.instances.editor.readOnly) {
            CKEDITOR.instances.editor.setReadOnly(true);
            $("#cke_1_top").hide();
            $("#cke_1_bottom").hide();
        } else {
            CKEDITOR.instances.editor.setReadOnly(false);
            $("#cke_1_top").show()
            $("#cke_1_bottom").show();
        }
    });
</script>