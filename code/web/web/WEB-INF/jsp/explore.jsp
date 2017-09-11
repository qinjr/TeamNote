<%--
  Created by IntelliJ IDEA.
  User: rudeigerc
  Date: 2017/7/20
  Time: 09:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>
<div class="container">
    <!-- tag card -->
    <div class="card" id="user-tags">
        <div class="card-block">
            <h4 class="card-title">您所关注的标签</h4>
            <p v-for="(tag, index) in tags" class="card-text h5" style="display: inline;" :id="'T_' + tag.tagId" :index="index" @click="getNotebooks($event)">
                <a href="javascript:void(0);"><kbd class="card-subtitle">{{ tag.tagName }}&nbsp;<span class="badge badge-pill badge-default">{{ tag.booksOfTag.length }}</span></kbd></a>&nbsp;
            </p>
        </div>
    </div>
    <div class="card" id="recommend-tags">
        <div class="card-block">
            <h4 class="card-title">推荐的标签</h4>
            <p v-for="tag in tags" class="card-text h5" style="display: inline;" :id="'RT_' + tag.tagId" @click="followTag($event)">
                <a href="javascript:void(0);"><kbd class="card-subtitle">{{ tag.tagName }}&nbsp;<span class="badge badge-pill badge-default">{{ tag.booksOfTag.length }}</span></kbd></a>&nbsp;
            </p>
        </div>
    </div>
    <!-- v-for note card-->
    <div id="explore" v-if="note.length !== 0">
        <div class="card" v-for="(_note, index) in note" :id="'NB_' + _note.notebookId">
            <div class="card-block">
                <div class="row">
                    <div class="col-md-12">
                        <p style="color: #D8D8D8"><small>来自标签 <strong>{{ tag }}</strong></small></p>
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
                                    <button class="btn btn-outline-secondary center-block btn-report none" type="button"
                                            data-toggle="collapse" :data-target="'#report-area_' + _note.notebookId" aria-expanded="false" :aria-controls="'report-area_' + _note.notebookId">
                                        <i class="fa fa-exclamation-triangle fa-fw" aria-hidden="true"></i>&nbsp;举报
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
    <footer>
        <p>&copy; 2017 TeamNote Team</p>
    </footer>
</div>
<%@ include file="footer.jsp"%>
<script type="text/javascript">
    var user_tags = new Vue({
        el: '#user-tags',
        data: {
            tags: []
        },
        created: function () {
            this.$http.get('/teamnote/getTagsOfUser', {
                params: {
                    userId: user.userId
                },
                responseType: "json"
            }).then(function(response) {
                this.tags = response.body;
            });
        },
        methods: {
            getNotebooks: function (e) {
                var tagId = e.currentTarget.id.replace('T_', '');
                var index = $(e.currentTarget).attr('index');
                var tagName = user_tags.tags[index].tagName;
                this.$http.get('/teamnote/getBooksOfTag', {
                    params: {
                        tagId: tagId
                    },
                    responseType: "json"
                }).then(function (response) {
                    explore.note = response.body;
                    explore.tag = tagName;
                })
            }
        }

    });

    var recommend_tags = new Vue({
        el: '#recommend-tags',
        data: {
            tags: []
        },
        created: function () {
            this.$http.get('/teamnote/recommendTag', { responseType: "json" }).then(function(response) {
                this.tags = response.body;
            });
        },
        methods: {
            followTag: function(e) {
                var confirm = window.confirm("您确定关注此标签？");
                if (confirm) {
                    var tagId = e.srcElement.parentElement.parentElement.id.replace('RT_', '');
                    this.$http.get('/teamnote/followTag', { params: { tagId: tagId } }).then(function() {
                        location.reload();
                    });
                }
            }
        }
    });

    var explore = new Vue({
        el: '#explore',
        data: {
            note: [],
            tag: null
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
                var index = $(e.currentTarget.parentElement).attr('index');
                if (this.note[index].stared) {
                    // unstar
                    $.ajax({
                        url: "/teamnote/evaluate/unstar",
                        type: "post",
                        data: {
                            notebookId: notebookId
                        },
                        success: function() {
                            explore.note[index].star--;
                            explore.note[index].stared = 0;
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
                            explore.note[index].star++;
                            explore.note[index].stared = 1;
                        }
                    })
                }
            },
            collect: function(e) {
                var notebookId = e.currentTarget.parentElement.parentElement.id.replace('CT_NB_', '');
                var index = $(e.currentTarget.parentElement).attr('index');
                if (this.note[index].collected) {
                    // uncollect
                    $.ajax({
                        url: "/teamnote/uncollectNotebook",
                        type: "post",
                        data: {
                            notebookId: notebookId
                        },
                        success: function() {
                            explore.note[index].collected = 0;
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
                            explore.note[index].collected = 1;
                        }
                    })
                }
            }
        }
    });

</script>