<%--
  Created by IntelliJ IDEA.
  User: rudeigerc
  Date: 2017/7/18
  Time: 09:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>
<%@ page import="model.mongodb.Notebook" %>
<%@ page import="model.mongodb.Note" %>
<%@ page import="java.util.ArrayList" %>
<%
    Notebook notebook = (Notebook) request.getAttribute("notebook");
    ArrayList<Note> notes = (ArrayList<Note>) request.getAttribute("notes");
    String relation = (String) request.getAttribute("relation");
%>
<div class="row">
    <div class="col-md-2">
        <div style="padding-left: 24px;">
            <button type="button" class="btn btn-outline-secondary navbar-toggle offcanvas-toggle is-open" data-toggle="offcanvas" data-target="#function-bar" style="width: 45px; height: 45px; border: none;">
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
</div>

<!-- left sidebar -->
<nav class="navbar navbar-default" role="navigation">
    <div class="navbar-offcanvas navbar-offcanvas-touch navbar-offcanvas-fade in" id="function-bar">
        <div class="pre-scrollable" style="padding-right: 10px;">
            <img class="img-75px rounded" src="<%=path%>/<%=notebook.getCover()%>" style="margin-top: 15px; ">
            <button type="button" class="btn btn-outline-secondary btn-back navbar-toggle offcanvas-toggle" data-toggle="offcanvas" data-target="#function-bar">
                <i class="fa fa-chevron-left" aria-hidden="true"></i>
            </button>
            <br><br>
            <%
                if (notes != null) {
            %>
            <h5 class="card-title notebook" id="<%=notebook.getNotebookId()%>">
                <%=notebook.getTitle()%>
            </h5>
            <ul style="padding-left: 0;">
                <%
                    for (Note note : notes) {
                        int id = note.getNoteId();
                %>
                <li class="bar-note" style="padding-left: 10px; margin-top: 5px; margin-bottom: 5px;">
                    <a class="note" id="<%=id%>" style="word-break: break-all;" href="javascript:void(0)"><%=note.getTitle()%></a>
                </li>
                <%
                        }
                    }
                %>
            </ul>
        </div>
        <div class="dropdown-divider"></div>
        <div class="sidebar-btn" id="function-btn" style="display: none;" >
            <button class="btn btn-secondary">
                <i class="fa fa-chevron-up fa-fw" aria-hidden="true" @mouseenter="hover($event)" @mouseleave="_hover($event)" @click="upvote()"></i>
                {{ count }}
                <i class="fa fa-chevron-down fa-fw" aria-hidden="true" @mouseenter="hover($event)" @mouseleave="_hover($event)" @click="downvote()"></i>
            </button>
            <button class="btn btn-outline-primary navbar-toggle offcanvas-toggle" data-toggle="offcanvas" data-target="#comment-bar">评论</button>
            <button class="btn btn-outline-primary" id="btn-report" data-toggle="modal" data-target="#reportModal">举报</button>
            <button class="btn btn-outline-secondary" id="changeMode" @click="changeMode()">读写</button>
        </div>
    </div>
</nav>

<!-- right sidebar -->
<nav class="navbar navbar-default" role="navigation">
    <div class="navbar-offcanvas navbar-offcanvas-touch navbar-offcanvas-fade navbar-offcanvas-right" id="comment-bar">

    </div>
</nav>

<div class="modal fade" id="reportModal" tabindex="-1" role="dialog" aria-labelledby="reportModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div class="card card-block" style="width: inherit;">
                    <div class="input-group">
                        <span class="input-group-addon" id="basic-addon">
                            <i class="fa fa-exclamation-triangle fa-fw" aria-hidden="true"></i>
                        </span>
                        <input type="text" class="form-control" id="report" name="report" @input="observe($event)" placeholder="请输入您的举报理由" aria-describedby="basic-addon">
                        <span class="input-group-btn">
                            <button class="btn btn-secondary" type="button" disabled="disabled" @click="report($event)">提交</button>
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.jsp"%>
<script type="text/javascript" src="<%=path%>/js/bootstrap.offcanvas.js"></script>
<script type="text/javascript" src="https://cdn.ckeditor.com/4.7.1/full/ckeditor.js"></script>
<script>
    CKEDITOR.replace( 'editor', {
        customConfig: '<%=path%>/ckeditor/js/config.js',
        contentsCss: '<%=path%>/ckeditor/css/contents.css',
        skin: 'bootstrapck,<%=path%>/ckeditor/skins/bootstrapck/'
    });

    $('.note').click(function(e) {
        if (CKEDITOR.instances.editor.checkDirty()) {
            if (confirm("转到其他笔记将会失去当前笔记未保存的内容，确认跳转吗？")){
            } else {
                return;
            }
        }
        $('#function-btn').show();
        $('#btn-report').removeAttr('disabled');
        $('#chooseType').removeAttr("style");//add
        noteId = parseInt(e.target.id);
        $.ajax({
            url : "/teamnote/getNote",
            processData : true,
            dataType : "text",
            type : "post",
            data : {
                noteId : noteId
            },
            success : function(data) {
                var json = JSON.parse(data);
                var version = json.versionPointer;
                var history = json.history[version];
                var content = JSON.parse(history).content;
                f_btn.count = json.upvoters.length - json.downvoters.length;
                f_btn.status = json.evaluate;
                CKEDITOR.instances.editor.setData(content,{
                    callback: function() {
                        CKEDITOR.instances.editor.resetDirty();
                    }
                });
            }
        });
    });

    var f_btn = new Vue({
        el: '#function-btn',
        data: {
            count: 0,
            status: null
        },
        methods: {
            hover: function (e) {
                var icon = e.currentTarget;
                $(icon).css('color', '#4A90E2')
            },
            _hover: function (e) {
                var icon = e.currentTarget;
                $(icon).css('color', '')
            },
            changeMode: function () {
                if(!CKEDITOR.instances.editor.readOnly) {
                    CKEDITOR.instances.editor.setReadOnly(true);
                    $("#cke_1_top").hide();
                    $("#cke_1_bottom").hide();
                } else {
                    CKEDITOR.instances.editor.setReadOnly(false);
                    $("#cke_1_top").show();
                    $("#cke_1_bottom").show();
                }
            },
            upvote: function () {
                if (f_btn.status !== 1) {
                    this.$http.get('/teamnote/evaluate/upvoteNote', { params: { noteId: noteId }}).then(function () {
                        if (f_btn.status === 3) {
                            f_btn.count++;
                        } else if (f_btn.status === 2) {
                            f_btn.count += 2;
                        }
                        f_btn.status = 1;
                    })
                }
            },
            downvote: function () {
                if (f_btn.status !== 2) {
                    this.$http.get('/teamnote/evaluate/downvoteNote', { params: { noteId: noteId }}).then(function () {
                        if (f_btn.status === 3) {
                            f_btn.count--;
                        } else if (f_btn.status === 1) {
                            f_btn.count -= 2;
                        }
                        f_btn.status = 2;
                    })
                }
            }
        }

    });

    var report = new Vue({
        el: '#reportModal',
        methods: {
            observe: function(e) {
                if ($(e.srcElement).val() === "") {
                    $(e.srcElement.nextElementSibling.children).attr('disabled', 'disabled');
                } else {
                    $(e.srcElement.nextElementSibling.children).removeAttr('disabled');
                }
            },
            report: function(e) {
                var report = $(e.srcElement.parentElement.previousElementSibling).val();
                $.ajax({
                    url: "/teamnote/evaluate/reportNote",
                    type: "post",
                    data: {
                        noteId: noteId,
                        reason: report
                    },
                    success: function() {
                        alert("您的举报已被管理员受理，请耐心等候，谢谢。");
                        $('#reportModal').modal('hide');
                        $('#btn-report').attr('disabled', 'disabled');
                    }
                })
            }
        }
    })

</script>