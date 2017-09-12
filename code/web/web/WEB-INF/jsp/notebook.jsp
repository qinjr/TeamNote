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
            <button class="btn" :class="count >= 0? 'btn-secondary' : 'btn-outline-danger'">
                <i class="fa fa-chevron-up fa-fw" aria-hidden="true" @mouseenter="hover($event)" @mouseleave="_hover($event)" @click="upvote()"></i>
                {{ count }}
                <i class="fa fa-chevron-down fa-fw" aria-hidden="true" @mouseenter="hover($event)" @mouseleave="_hover($event)" @click="downvote()"></i>
            </button>
            <button class="btn btn-outline-primary navbar-toggle offcanvas-toggle" data-toggle="offcanvas" data-target="#comment-bar">评论</button>
            <button class="btn btn-outline-primary" id="btn-report" data-toggle="modal" data-target="#reportModal">举报</button>
            <button class="btn btn-outline-secondary" id="giveAdvice" @click="giveAdvice()">提出建议</button>
            <button class="btn btn-outline-success" id="saveAdvice" style="display:none;" @click="saveAdvice()">提交建议</button>
        </div>
    </div>
</nav>

<!-- right sidebar -->
<nav class="navbar navbar-default" role="navigation">
    <div class="navbar-offcanvas navbar-offcanvas-touch navbar-offcanvas-fade navbar-offcanvas-right" id="comment-bar">
        <div class="pre-scrollable" style="max-height: 70%;">
            <div v-for="comment in comments">
                <div style='margin-bottom: 10px;'>
                    <label>
                        <div class='media'>
                            <img class='d-flex mr-3 img-50px rounded' src="" :src="'<%=path%>/' + comment.avatar">
                            <div class='media-body'>
                                {{ comment.username }}&nbsp;
                                <a href="javascript:void(0);" @click="reportComment($event)"><small style="color: lightgrey" :id="comment.commentId">举报</small></a>
                                <br><small style="color: lightgrey">{{ date(comment.sentTime) }}</small>
                            </div>
                        </div>
                    </label>
                    <div>
                        {{ comment.content }}
                    </div>
                </div>
            </div>
        </div>
        <div class="dropdown-divider" style="margin-bottom: 0;"></div>
        <div>
            <button type="button" class="btn btn-outline-secondary btn-back navbar-toggle offcanvas-toggle"
                    data-toggle="offcanvas" data-target="#comment-bar" style="width: auto; height: auto;">
                <i class="fa fa-chevron-right" aria-hidden="true"></i>
            </button>
            <label for="comment"></label>
            <textarea rows="4" class="form-control" id="comment"></textarea>
            <input type="button" class="btn btn-outline-primary" value="发送评论" id="newComment" @click="comment()" style="width: 100%;">
        </div>
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
                        <input type="hidden" id="report-type" value="0">
                        <input type="hidden" id="report-id" value="-1">
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

<div class="modal fade" id="issueModal" tabindex="-1" role="dialog" aria-labelledby="issueModalTitle" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="issueModalTitle"></h4>
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only"></span>
                </button>
            </div>
            <div class="modal-body">
                <form role="form">
                    <div class="form-group">
                        <label for="issue" class="form-control-label">建议</label>
                        <input class="form-control" name="issue" id="issue">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
                <button type="button" class="btn btn-primary sendAdvice">发送</button>
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.jsp"%>
<script type="text/javascript" src="<%=path%>/js/bootstrap.offcanvas.js"></script>
<script type="text/javascript" src="<%=path%>/ckeditor/ckeditor.js"></script>
<script>
    CKEDITOR.replace( 'editor', {
        readOnly: 'true',
        customConfig: '<%=path%>/ckeditor/config.js',
        height: 600
    });

    CKEDITOR.instances.editor.on('instanceReady', function (e) {
        $("#cke_1_top").hide();
        $("#cke_1_bottom").hide();
    });

    $('.note').click(function(e) {
        if (CKEDITOR.instances.editor.checkDirty()) {
            if (confirm("转到其他笔记将会失去当前笔记未保存的内容，确认跳转吗？")){
            } else {
                return;
            }
        }
        $("#saveAdvice").hide();
        CKEDITOR.instances.editor.setReadOnly(true);
        $("#giveAdvice").show();
        $('#function-btn').show();
        $("#cke_1_top").hide();
        $("#cke_1_bottom").hide();
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
            giveAdvice: function () {
                CKEDITOR.instances.editor.setReadOnly(false);
                $("#cke_1_top").show();
                $("#cke_1_bottom").show();
                $("#giveAdvice").hide();
                $("#saveAdvice").show();
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
            },
            getComments: function() {
                this.$http.get('/teamnote/evaluate/getComments', {
                    params: {
                        noteId: noteId
                    },
                    responseType: "json"
                }).then(function(response) {
                    comment.comments = JSON.parse(response.body.comments);
                });
            },
            reportNote: function(e) {
                $('#report-type').val(1);
                $('#reportModal').modal('show');
            },
            saveAdvice: function() {
                $('#issueModal').modal('show').attr("content", CKEDITOR.instances.editor.getData());
                CKEDITOR.instances.editor.resetDirty();
                $.ajax({
                    url : "/teamnote/cooperate/raiseSuggestion",
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
            }
        }
    });

    $(".sendAdvice").click(function(){
        var content = CKEDITOR.instances.editor.getData();
        var issue = $('input[name="issue"]').val();
        $.ajax({
            url : "/teamnote/cooperate/raiseSuggestion",
            processData : true,
            dataType : "text",
            type : "post",
            data : {
                noteId : noteId,
                issue: issue,
                content: content
            },
            success : function(data) {
                var json = JSON.parse(data);
                if (json.result === "success") {
                    alert("建议提交成功。");
                    location.reload();
                } else {
                    alert("系统故障，提交失败。");
                }
            }
        });
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
                var type = $('#report-type').val();
                if (type === "2") {
                    $.ajax({
                        url: "/teamnote/evaluate/reportComment",
                        type: "post",
                        data: {
                            commentId: $('#report-id').val(),
                            reason: report
                        },
                        success: function() {
                            alert("您的举报已被管理员受理，请耐心等候，谢谢。");
                            $('#reportModal').modal('hide');
                            $('#report-type').val(0);
                        }
                    })
                } else if (type === "1") {
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
                            $('#report-type').val(0);
                        }
                    })
                }
            }
        }
    });

    var comment = new Vue({
        el: '#comment-bar',
        data: {
            comments: []
        },
        methods: {
            comment: function() {
                var confirm = window.confirm("是否提交该评论？");
                if (confirm) {
                    $.ajax({
                        url: "/teamnote/evaluate/newComment",
                        type: "post",
                        data: {
                            noteId: noteId,
                            content: $('#comment').val()
                        },
                        success: function() {
                            $('#comment').val("");
                            location.reload();
                        }

                    })
                }
            },
            date: function(_date) {
                return moment(_date, "ddd MMM DD HH:mm:ss z YYYY").format("YYYY-MM-DD HH:mm:ss");
            },
            reportComment: function(e) {
                var commentId = e.srcElement.id;
                $('#report-type').val(2);
                $('#report-id').val(commentId);
                $('#reportModal').modal('show');
            }
        }
    })



</script>