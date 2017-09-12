<%--
  Created by IntelliJ IDEA.
  User: rudeigerc
  Date: 2017/6/27
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>

<div class="container" id="index">
    <div v-if="user.length === 0 && note.length !== 0">
        <div class="card" v-for="(_note, index) in note" :id="'NB_' + _note.notebookId">
            <div class="card-block">
                <div class="row">
                    <div class="col-md-12">
                        <p style="color: #D8D8D8"><small>来自标签</small></p>
                        <div class="row" style="margin-right: 0; margin-left: 0;">
                            <div class="col-md-2 text-center mx-auto">
                                <img class="rounded" src="" :src="'<%=path%>/' + _note.cover" style="height: 100px; width: 100px;">
                            </div>
                            <div class="col-md-10" :id="'CT_NB_' + _note.notebookId">
                                <a :href="'<%=path%>/notebook?notebookId=' + _note.notebookId"><h4 class="card-title notebook-title" style="margin-bottom: 6px;">{{ _note.title }}</h4></a>
                                <i class="fa fa-tag" aria-hidden="true"></i>
                                <div style="display: inline;" v-for="tag in json(_note.tags)">
                                    <kbd class="card-subtitle">{{ tag }}</kbd>&nbsp;
                                </div>
                                <p class="card-text" style="word-break: break-all; margin-top: 16px;">
                                    {{ _note.description }}
                                </p>
                                <footer :index="index">
                                    <small>创建者 <a :href="'<%=path%>/homepage?userId=' + _note.creatorId" class="notebook-username"><strong>{{ _note.creator }}</strong></a> · 所有者 <a :href="'<%=path%>/homepage?userId=' + _note.ownerId" class="notebook-username"><strong>{{ _note.owner }}</strong></a> · 创建时间 {{ date(_note.createTime) }}</small>
                                    <br><br>
                                    <button class="btn btn-outline-secondary center-block none" :class="_note.stared? 'active' : ''" type="button" @click="star($event)">
                                        <i class="fa fa-star fa-fw" aria-hidden="true"></i>&nbsp;{{ _note.star }}
                                    </button>
                                    <button class="btn btn-outline-secondary center-block btn-collection none" :class="_note.collected? 'active' : ''" type="button" @click="collect($event)">
                                        <i class="fa fa-flag fa-fw" aria-hidden="true"></i>&nbsp;收藏
                                    </button>
                                    <!--
                                    <button class="btn btn-outline-secondary center-block btn-report none" type="button"
                                            data-toggle="collapse" :data-target="'#report-area_' + _note.notebookId" aria-expanded="false" :aria-controls="'report-area_' + _note.notebookId">
                                        <i class="fa fa-exclamation-triangle fa-fw" aria-hidden="true"></i>&nbsp;举报
                                    </button>
                                    -->
                                    <button class="btn btn-outline-secondary center-block btn-award none" type="button" @click="award($event)" data-toggle="modal">
                                        <i class="fa fa-money fa-fw" aria-hidden="true"></i>&nbsp;打赏
                                    </button>
                                    <div class="collapse" :id="'report-area_' + _note.notebookId">
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
                                </footer>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div v-else-if="user.length !== 0">
        <div class="card container-card" v-for="(_user, index) in user">
            <div class="card-block">
                <div class="row">
                    <div class="col-md-2 text-center">
                        <img class="rounded" src="" :src="'<%=path%>/' +  _user.avatar" style="height: 100px; width: 100px;">
                    </div>
                    <div class="col-md-10">
                        <a :href="'<%=path%>/homepage?userId=' + _user.userId"><h4 class="card-title notebook-title">{{ _user.username }}</h4></a>
                        <p class="card-subtitle mb-2 text-muted">
                            <i class="fa fa-tag" aria-hidden="true"></i>&nbsp;{{ _user.personalStatus }}
                        </p>
                        <p>关注人 {{ _user.followings.length }} · 关注者 {{ _user.followers.length }}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer>
        <p>&copy; 2017 TeamNote Team</p>
    </footer>
</div>

<div class="modal fade" id="qrcodeModal" tabindex="-1" role="dialog" aria-labelledby="qrcodeModalTitle" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 265px;">
            <div class="modal-header">
                <i class="fa fa-money" aria-hidden="true"></i>打赏
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only"></span>
                </button>
            </div>
            <div class="modal-body">
                <img src="" :src="'<%=path%>/' + src" style="max-width: 233px; height: 349px;">
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.jsp"%>
<script type="text/javascript">
    var index = new Vue({
        el: '#index',
        data: {
            note: [],
            user: []
        },
        created: function () {
            this.$http.get('/teamnote/recommend', { responseType: "json" }).then(function(response) {
                this.note = response.body;
            });
        },
        methods: {
            // parse tag string to json
            json: function(tag) {
                return JSON.parse(tag);
            },
            // normalize date format
            date: function(_date) {
                return moment(_date, "ddd MMM DD HH:mm:ss z YYYY").format("YYYY-MM-DD HH:mm:ss");
            },
            // functio to observe the input of report
            observe: function(e) {
                if ($(e.srcElement).val() === "") {
                    $(e.srcElement.nextElementSibling.children).attr('disabled', 'disabled');
                } else {
                    $(e.srcElement.nextElementSibling.children).removeAttr('disabled');
                }
            },
            // report
            report: function(e) {
                var report = $(e.srcElement.parentElement.previousElementSibling).val();
                var notebookId = e.srcElement.parentElement.parentElement.parentElement.parentElement.id.replace('report-area_', '');
                $.ajax({
                    url: "/teamnote/evaluate/reportNotebook",
                    type: "post",
                    data: {
                        notebookId: notebookId,
                        reason: report
                    },
                    success: function() {
                        alert("您的举报已被管理员受理，请耐心等候，谢谢。");
                        $('#' + e.srcElement.parentElement.parentElement.parentElement.parentElement.id).hide();
                    }
                })
            },
            star: function(e) {
                var notebookId = e.currentTarget.parentElement.parentElement.id.replace('CT_NB_', '');
                var _index = $(e.currentTarget.parentElement).attr('index');
                if (this.note[_index].stared) {
                    // unstar
                    $.ajax({
                        url: "/teamnote/evaluate/unstar",
                        type: "post",
                        data: {
                            notebookId: notebookId
                        },
                        success: function() {
                            index.note[_index].star--;
                            index.note[_index].stared = 0;
                        }
                    })
                } else {
                    // star
                    $.ajax({
                        url: "/teamnote/evaluate/star",
                        type: "post",
                        data: {
                            notebookId: notebookId
                        },
                        success: function() {
                            index.note[_index].star++;
                            index.note[_index].stared = 1;
                        }
                    })
                }
            },
            collect: function(e) {
                var notebookId = e.currentTarget.parentElement.parentElement.id.replace('CT_NB_', '');
                var _index = $(e.currentTarget.parentElement).attr('index');
                if (this.note[_index].collected) {
                    // uncollect
                    $.ajax({
                        url: "/teamnote/uncollectNotebook",
                        type: "post",
                        data: {
                            notebookId: notebookId
                        },
                        success: function() {
                            index.note[_index].collected = 0;
                        }
                    })
                } else {
                    // collect
                    $.ajax({
                        url: "/teamnote/collectNotebook",
                        type: "post",
                        data: {
                            notebookId: notebookId
                        },
                        success: function() {
                            index.note[_index].collected = 1;
                        }
                    })
                }
            },
            award: function(e) {
                var _index = $(e.currentTarget.parentElement).attr('index');
                var src = index.note[_index].qrcode;
                if (src.length !== 0) {
                    modal.src = src;
                    $('#qrcodeModal').modal('show');
                } else {
                    alert("该用户未开启该功能。");
                }

            }
        }
    });

    var modal = new Vue({
        el: '#qrcodeModal',
        data: {
            src: null
        }
    })

</script>