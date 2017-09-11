<%--
  Created by IntelliJ IDEA.
  User: rudeigerc
  Date: 2017/6/30
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>
<%
    int userId = (int) request.getAttribute("userId");
%>

<div class="container">
    <div class="card container-card" id="info">
        <div class="card-block">
            <div class="row">
                <div class="col-md-2 text-center mx-auto">
                    <img class="rounded" src="" :src="'<%=path%>/' + avatar" style="height: 100px; width: 100px;">
                </div>
                <div class="col-md-7">
                    <h4 class="card-title">{{ username }}</h4>
                    <p class="card-subtitle mb-2 text-muted">
                        <i class="fa fa-tag" aria-hidden="true"></i>&nbsp;{{ personalStatus }}
                    </p>
                    <p class="card-subtitle mb-2 text-muted">
                        <i class="fa fa-envelope-o" aria-hidden="true"></i>&nbsp;{{ email }}
                    </p>
                    <p><a href="javascript:void(0)" @click="following()">关注人 {{ followingsnum }}</a> · <a href="javascript:void(0)" @click="followed()">关注者 {{ followersnum }}</a></p>
                </div>
                <div class="col-md-3" :index="userId">
                    <button v-if="isFollowed && !self" class="btn btn-primary info-follow" @mouseenter="hover($event)" @mouseleave="_hover($event)" @click="unfollow($event)">正在关注</button>
                    <button v-else-if="!isFollowed && !self" class="btn btn-outline-primary info-follow" style="width: 98px;" @click="follow()">关注</button>
                </div>
            </div>
        </div>
    </div>
    <div class="card container-card">
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
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="follow-tab" data-toggle="dropdown" href="javascript:void(0)" role="button" aria-haspopup="true" aria-expanded="false">关注</a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" data-toggle="tab" id="following-tab" role="tab" href="#following" aria-controls="following">
                            <i class="fa fa-paper-plane fa-fw" aria-hidden="true"></i>&nbsp;关注人
                        </a>
                        <a class="dropdown-item" data-toggle="tab" id="follower-tab" role="tab" href="#follower" aria-controls="follower">
                            <i class="fa fa-paper-plane-o fa-fw" aria-hidden="true"></i>&nbsp;关注者
                        </a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" data-toggle="tab" id="following-tag-tab" role="tab" href="#following-tag" aria-controls="following-tag">
                            <i class="fa fa-tag fa-fw" aria-hidden="true"></i>&nbsp;标签
                        </a>
                    </div>
                </li>
            </ul>

            <div class="tab-content" id="homepageTabContent">
                <!-- TODO: activity -->
                <div class="tab-pane fade show active" id="activity" role="tabpanel" aria-labelledby="activity-tab" aria-expanded="true">
                    <div style="margin-top: 10px; margin-bottom: 10px;">
                        <div v-for="_activity in activity">
                            <!-- TODO: upvote note -->
                            <p v-if="_activity.type === 1" style="margin-bottom: 0;">
                                <label class="activity-label">{{ date(_activity.time) }}</label>&nbsp;
                                <strong>{{ username() }}</strong>&nbsp;
                                支持了笔记&nbsp;<i class="fa fa-sticky-note" aria-hidden="true"></i>&nbsp;<a :href="'<%=path%>/notebook?notebookId=' + _activity.targetId" class="activity-target"><strong>{{ _activity.targetName }}</strong></a>
                            </p>
                            <!-- TODO: star notebook -->
                            <p v-else-if="_activity.type === 2" style="margin-bottom: 0;">
                                <label class="activity-label">{{ date(_activity.time) }}</label>&nbsp;
                                <strong>{{ username() }}</strong>&nbsp;
                                标星了笔记本&nbsp;<i class="fa fa-book" aria-hidden="true"></i>&nbsp;<a :href="'<%=path%>/notebook?notebookId=' + _activity.targetId" class="activity-target"><strong>{{ _activity.targetName }}</strong></a>
                            </p>
                            <!-- TODO: collect notebook -->
                            <p v-else-if="_activity.type === 3" style="margin-bottom: 0;">
                                <label class="activity-label">{{ date(_activity.time) }}</label>&nbsp;
                                <strong>{{ username() }}</strong>&nbsp;
                                收藏了笔记本<i class="fa fa-book" aria-hidden="true"></i>&nbsp;<a :href="'<%=path%>/notebook?notebookId=' + _activity.targetId" class="activity-target"><strong>{{ _activity.targetName }}</strong></a>
                            </p>
                            <!-- TODO: follow tag -->
                            <p v-else-if="_activity.type === 4" style="margin-bottom: 0;">

                            </p>
                            <div class="dropdown-divider"></div>
                        </div>
                    </div>
                </div>

                <!-- notebook -->
                <div class="tab-pane fade" id="notebook" role="tabpanel" aria-labelledby="notebook-tab">
                    <div v-if="self()" class="row flex-row-reverse" style="margin: 20px 0;">
                        <button class="btn btn-success" data-toggle="modal" data-target="#newNotebookModal">
                            <i class="fa fa-sticky-note fa-fw" aria-hidden="true"></i>&nbsp;新建笔记本
                        </button>
                    </div>
                    <div v-if="note.length === 0">
                        <div class="alert alert-success" role="alert" style="margin-top: 16px;">
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            该用户没有笔记本
                        </div>
                    </div>
                    <div v-else="" v-for="(_note, index) in note">
                        <div class="row" style="margin: 20px 0;">
                            <div class="col-md-2 text-center mx-auto">
                                <img class="rounded" src="" :src="'<%=path%>/' + _note.cover" style="height: 75px; width: 75px;">
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
                                    <small>创建者 <strong>{{ _note.creator }}</strong> · 所有者 <strong>{{ _note.owner }}</strong> · 创建时间 {{ n_date(_note.createTime) }}</small>
                                    <br><br>
                                    <button class="btn btn-outline-secondary center-block none" :class="_note.stared? 'active' : ''" type="button" @click="star($event)">
                                        <i class="fa fa-star fa-fw" aria-hidden="true"></i>&nbsp;{{ _note.star }}
                                    </button>
                                    <button class="btn btn-outline-secondary center-block btn-collection none" :class="_note.collected? 'active' : ''" type="button" @click="collect($event)">
                                        <i class="fa fa-flag fa-fw" aria-hidden="true"></i>&nbsp;收藏
                                    </button>
                                </footer>
                            </div>
                        </div>
                        <div class="dropdown-divider"></div>
                    </div>
                </div>

                <!-- workgroup -->
                <div class="tab-pane fade" id="workgroup" role="tabpanel" aria-labelledby="workgroup-tab">
                    <div v-if="self">
                        <div v-if="loading" style="text-align: center; padding-top: 20px;">
                            <i class="fa fa-spinner fa-pulse fa-2x fa-fw"></i>
                            <span class="sr-only">Loading...</span>
                        </div>
                        <div v-if="workgroupsdetails.length === 0">
                            <div class="alert alert-success" role="alert" style="margin-top: 16px;">
                                <i class="fa fa-info-circle" aria-hidden="true"></i>
                                该用户没有加入的工作组
                            </div>
                        </div>
                        <div v-else="" v-for="workgroupdetail in workgroupsdetails">
                            <div class="row">
                                <div class="col-md-12" style="margin-top: 20px; margin-bottom: 20px;">
                                    <div class="row">
                                        <div class="col-md-2 text-center mx-auto">
                                            <img class="rounded" src="" :src="workgroupdetail.notebook.cover" style="height: 75px; width: 75px;">
                                        </div>
                                        <div class="col-md-7">
                                            <h4 class="card-title" style="margin-bottom: 6px;">{{ workgroupdetail.notebook.title }}</h4>
                                            <i class="fa fa-tag" aria-hidden="true"></i>
                                            <div style="display: inline;" v-for="tag in workgroupdetail.tags">
                                                <kbd class="card-subtitle">{{ tag.tagName }}</kbd>&nbsp;
                                            </div>
                                            <div style="margin-top: 5px; margin-bottom: 5px;">
                                                <small>创建者 <strong>{{ workgroupdetail.creator.username }}</strong> · 所有者 <strong>{{ workgroupdetail.owner.username }}</strong> · 创建时间 {{ w_date(workgroupdetail.notebook.createTime) }}</small>
                                            </div>
                                            <div style="margin: 10px auto; display: inline;" v-for="collaborator in workgroupdetail.collaborators">
                                                <img class="rounded" :src="'<%=path%>/' + collaborator.avatar" style="width: 50px; height: 50px;">&nbsp;
                                            </div>
                                        </div>
                                        <div class="col-md-3 workgroup-btn">
                                            <a class="btn btn-outline-primary center-block" role="button" :href="'<%=path%>/cooperate/workgroup?notebookId=' + workgroupdetail.notebook.notebookId">
                                                <i class="fa fa-users fa-fw" aria-hidden="true"></i>&nbsp;进入工作组
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="dropdown-divider"></div>
                        </div>
                    </div>

                </div>

                <!-- collection -->
                <div class="tab-pane fade" id="collection" role="tabpanel" aria-labelledby="collection-tab">
                    <div v-if="collection.length === 0">
                        <div class="alert alert-success" role="alert" style="margin-top: 16px;">
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            该用户没有收藏的笔记本
                        </div>
                    </div>
                    <div v-else="" v-for="(_note, index) in collection">
                        <div class="row" style="margin: 20px 0;">
                            <div class="col-md-2 text-center mx-auto">
                                <img class="rounded" src="" :src="'<%=path%>/' + _note.cover" style="height: 75px; width: 75px;">
                            </div>
                            <div class="col-md-10" :id="'CT_NB_' + _note.notebookId">
                                <h4 class="card-title" style="margin-bottom: 6px;">{{ _note.title }}</h4>
                                <i class="fa fa-tag" aria-hidden="true"></i>
                                <div style="display: inline;" v-for="tag in json(_note.tags)">
                                    <kbd class="card-subtitle">{{ tag }}</kbd>&nbsp;
                                </div>
                                <footer :index="index" style="margin-top: 5px;">
                                    <small>创建者 <strong>{{ _note.creator }}</strong> · 所有者 <strong>{{ _note.owner }}</strong> · 创建时间 {{ n_date(_note.createTime) }}</small>
                                </footer>
                            </div>
                        </div>
                        <div class="dropdown-divider"></div>
                    </div>
                </div>

                <!-- following -->
                <div class="tab-pane fade" id="following" role="tabpanel" aria-labelledby="following-tab">
                    <div v-if="followings.length === 0">
                        <div class="alert alert-success" role="alert" style="margin-top: 16px;">
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            该用户没有关注人
                        </div>
                    </div>
                    <div v-else="" class="card-columns">
                        <div class="card user-card" v-for="(user, index) in followings" :index="index">
                            <div>
                                <img class="card-img-top img-100px rounded" src="" :src="'<%=path%>' + user.avatar" :alt="user.username" style="margin: 20px;">
                                <button v-if="user.isFollowed && !_self(user.userId)" class="btn btn-sm btn-primary btn-user-card float-right btn-following" @mouseenter="hover($event)" @mouseleave="_hover($event)" @click="unfollow($event)">正在关注</button>
                                <button v-else-if="!user.isFollowed && !_self(user.userId)" class="btn btn-sm btn-outline-primary btn-user-card float-right" style="width: 74px;" @click="follow($event)">关注</button>
                            </div>
                            <div class="card-block" style="padding-top: 0;">
                                <a class="card-username" :href="'<%=path%>/homepage?userId=' + user.userId"><h5 class="card-title">{{ user.username }}</h5></a>
                                <p class="card-text">{{ user.personalStatus }}</p>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- follower -->
                <div class="tab-pane fade" id="follower" role="tabpanel" aria-labelledby="follower-tab">
                    <div v-if="followers.length === 0">
                        <div class="alert alert-success" role="alert" style="margin-top: 16px;">
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            该用户没有关注者
                        </div>
                    </div>
                    <div v-else="" class="card-columns">
                        <div class="card user-card" v-for="(user, index) in followers" :index="index">
                            <div>
                                <img class="card-img-top img-100px rounded" src="" :src="'<%=path%>' + user.avatar" :alt="user.username" style="margin: 20px;">
                                <button v-if="user.isFollowed && !_self(user.userId)" class="btn btn-sm btn-primary btn-user-card float-right" @mouseenter="hover($event)" @mouseleave="_hover($event)" @click="unfollow($event)">正在关注</button>
                                <button v-else-if="!user.isFollowed && !_self(user.userId)" class="btn btn-sm btn-outline-primary btn-user-card float-right" style="width: 74px;" @click="follow($event)">关注</button>
                            </div>
                            <div class="card-block" style="padding-top: 0;">
                                <a class="card-username" :href="'<%=path%>/homepage?userId=' + user.userId"><h5 class="card-title">{{ user.username }}</h5></a>
                                <p class="card-text">{{ user.personalStatus }}</p>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- following tag -->
                <div class="tab-pane fade" id="following-tag" role="tabpanel" aria-labelledby="following-tag-tab">
                    <div v-if="self">
                        <div v-if="tags.length === 0">
                            <div class="alert alert-success" role="alert" style="margin-top: 16px;">
                                <i class="fa fa-info-circle" aria-hidden="true"></i>
                                该用户没有关注的标签
                            </div>
                        </div>
                        <div v-else="" style="margin-top: 20px;">
                            <div style="display: inline;" v-for="(tag, index) in tags" :id="tag.tagId" @click="unfollowTag($event)">
                                <a href="javascript:void(0);"><kbd class="card-subtitle">{{ tag.tagName }}&nbsp;<span class="badge badge-pill badge-default">{{ tag.booksOfTag.length }}</span></kbd></a>&nbsp;
                            </div>
                        </div>
                    </div>
                    <div v-else="">
                        <div v-if="tags.length === 0">
                            <div class="alert alert-success" role="alert" style="margin-top: 16px;">
                                <i class="fa fa-info-circle" aria-hidden="true"></i>
                                该用户没有关注的标签
                            </div>
                        </div>
                        <div v-else="" style="margin-top: 20px;">
                            <div style="display: inline;" v-for="(tag, index) in tags" :id="tag.tagId">
                                <kbd class="card-subtitle">{{ tag.tagName }}&nbsp;<span class="badge badge-pill badge-default">{{ tag.booksOfTag.length }}</span></kbd>&nbsp;
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

<div class="modal fade" id="newNotebookModal" tabindex="-1" role="dialog" aria-labelledby="newNotebookModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="newNotebookModalLabel">
                    <i class="fa fa-pencil" aria-hidden="true"></i>&nbsp;新建笔记本
                </h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="createNotebookForm">
                    <div class="form-group">
                        <label for="notebookTitle" class="form-control-label">笔记本标题</label>
                        <input type="text" class="form-control" id="notebookTitle" name="notebookTitle">
                    </div>
                    <div class="form-group">
                        <label for="tag" class="form-control-label">标签</label>
                        <input type="text" class="form-control" id="tag" name="tag" placeholder="请用全角分号（；）分隔标签">
                    </div>
                    <div class="form-group">
                        <label for="description" class="form-control-label">简介</label>
                        <textarea rows="3" class="form-control" id="description" name="description"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="notebookCover" class="form-control-label" id="nootbookCoverLabel">笔记本封面</label>
                        <div>
                            <img src="" id="avatar" class="img-150px">
                            <br>
                            <div class="btn-group" role="group">
                                <button type="button" class="btn btn-secondary file-chooser-label" id="file_upload_label" style="margin-top: 0;">选择上传的图片
                                    <input type="file" class="form-control-file file-chooser" id="notebookCover" name="notebookCover">
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="newNotebook">确认</button>
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.jsp"%>
<script type="text/javascript" src="<%=path%>/js/bootbox.min.js"></script>
<script type="text/javascript">

    // parse tag in url
    var tag = location.href.split('#')[1];
    if (tag !== undefined) {
        switch(tag) {
            case "notebook": {
                $('#notebook-tab').tab('show');
                break;
            }
            case "collection": {
                $('#collection-tab').tab('show');
                break;
            }
            default: {
                break;
            }
        }
    }

    // information
    var info = new Vue({
        el: '#info',
        data: {
            username: null,
            personalStatus: null,
            email: null,
            avatar : null,
            userId: null,
            followersnum : null,
            followingsnum : null,
            self : null,
            isFollowed: null
        },
        created: function() {
            this.$http.get('/teamnote/userdetail', { params: { userId: <%=userId%> } }).then(function(response){
                info.username = JSON.parse(response.body.userInfo).username;
                info.personalStatus = JSON.parse(response.body.user).personalStatus;
                info.email = JSON.parse(response.body.userInfo).email;
                info.avatar = JSON.parse(response.body.user).avatar;
                info.userId = JSON.parse(response.body.user).userId;
                info.followersnum = JSON.parse(response.body.user).followers.length;
                info.followingsnum = JSON.parse(response.body.user).followings.length;
                info.self = response.body.self;
                if (!info.self) {
                    $('#workgroup-tab').remove();
                    info.isFollowed = response.body.isFollowed;
                }
            }, function() {
                console.log("user info error")
            });
        },
        methods: {
            following: function() {
                $('#following-tab').tab('show');

            },
            followed: function() {
                $('#follower-tab').tab('show');
            },
            hover: function(e) {
                var btn = e.currentTarget;
                btn.textContent = "取消关注";
                $(btn).removeClass('btn-primary');
                $(btn).addClass('btn-danger');
            },
            _hover: function(e) {
                var btn = e.currentTarget;
                btn.textContent = "正在关注";
                $(btn).removeClass('btn-danger');
                $(btn).addClass('btn-primary');
            },
            unfollow: function(e) {
                var confirm = window.confirm("您将取消关注用户 " + this.username);
                if (confirm) {
                    var userId = this.userId;
                    this.$http.post('/teamnote/unfollow', {
                        userId: userId
                    }, {
                        responseType: "json",
                        emulateJSON: true
                    }).then(function(response) {
                        info.followingsnum--;
                        this.isFollowed = 0;
                        e.target.textContent = "关注";

                    })
                }
            },
            follow: function() {
                var userId = this.userId;
                this.$http.post('/teamnote/follow', {
                    userId: userId
                }, {
                    responseType: "json",
                    emulateJSON: true
                }).then(function(response) {
                    info.followingsnum++;
                    this.isFollowed = 1;
                })
            }
        }
    });

    var activity = new Vue({
        el: '#activity',
        data: {
            activity: []
        },
        created: function() {
            this.$http.get('/teamnote/getUserBehaviors', {
                responseType: "json",
                params: {
                    userId: <%=userId%>
                }
            }).then(function(response){
                for (var json in response.body) {
                    this.activity.push(JSON.parse(response.body[json]))
                }
            })
        },
        methods: {
            date: function(date) {
                return moment(date, "ddd MMM DD HH:mm:ss z YYYY").format("YYYY-MM-DD HH:mm:ss");
            },
            username: function() {
                return info.username;
            }
        }
    });

    var notebook = new Vue({
        el: '#notebook',
        data: {
            note: []
        },
        created: function () {
            this.$http.get('/teamnote/getNotebooksOfUser', {
                responseType: "json",
                params: {
                    userId: <%=userId%>
                }
            }).then(function(response) {
                this.note = response.body;
            });
        },
        methods: {
            self: function() {
                return (user.userId === <%=userId%>);
            },
            json: function(tag) {
                return JSON.parse(tag);
            },
            n_date: function(date) {
                return moment(date, "ddd MMM DD HH:mm:ss z YYYY").format("YYYY-MM-DD HH:mm:ss");
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
                            notebook.note[_index].star--;
                            notebook.note[_index].stared = 0;
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
                            notebook.note[_index].star++;
                            notebook.note[_index].stared = 1;
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
                            notebook.note[_index].collected = 0;
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
                            notebook.note[_index].collected = 1;
                        }
                    })
                }
            }
        }
    });

    var workgroups = new Vue({
        el: '#workgroup',
        data: {
            workgroupsdetails: [],
            self : null,
            loading: true
        },
        created: function () {
            this.$http.get('/teamnote/cooperate/allworkgroups', { params: { userId: <%=userId%> } }).then(function(response){
                this.loading = false;
                workgroups.workgroupsdetails = JSON.parse(response.body.workgroups);
                workgroups.self = response.body.self;
            }, function() {
                console.log("workgroup error");
            });
        },
        methods: {
            w_date: function(date) {
                return moment(date, "MMM D, YYYY h:mm:ss A").format("YYYY-MM-DD HH:mm:ss")
            }
        }
    });

    var collection = new Vue({
        el: '#collection',
        data: {
            collection: [],
            self: null
        },
        created: function () {
            this.$http.get('/teamnote//getCollectedNotebooks', {
                responseType: "json",
                params: {
                    userId: <%=userId%>
                }
            }).then(function(response) {
                this.collection = response.body;
            });
        },
        methods: {
            json: function(tag) {
                return JSON.parse(tag);
            },
            n_date: function(date) {
                return moment(date, "ddd MMM DD HH:mm:ss z YYYY").format("YYYY-MM-DD HH:mm:ss");
            }
        }
    });

    var following = new Vue({
        el: '#following',
        data: {
            followings: [],
            self: false
        },
        created: function () {
            this.$http.get('/teamnote/getFollowings', {
                responseType: "json",
                params: {
                    userId: <%=userId%>
                },
                emulateJSON: true
            }).then(function(response) {
                this.followings = JSON.parse(response.body.followings);
                this.self = response.body.self;
            });
        },
        methods: {
            _self: function(userId) {
                return (userId === user.userId)
            },
            hover: function(e) {
                var btn = e.currentTarget;
                btn.textContent = "取消关注";
                $(btn).removeClass('btn-primary');
                $(btn).addClass('btn-danger');
            },
            _hover: function(e) {
                var btn = e.currentTarget;
                btn.textContent = "正在关注";
                $(btn).removeClass('btn-danger');
                $(btn).addClass('btn-primary');
            },
            unfollow: function(e) {
                var index = $(e.currentTarget.parentElement.parentElement).attr('index');
                var confirm = window.confirm("您将取消关注用户 " + this.followings[index].username);
                if (confirm) {
                    var userId = this.followings[index].userId;
                    this.$http.post('/teamnote/unfollow', {
                        userId: userId
                    }, {
                        responseType: "json",
                        emulateJSON: true
                    }).then(function(response) {
                        if (following.self) {
                            following.followings.splice(index, 1);
                            info.followingsnum--;
                        }
                    })
                }
            },
            follow: function(e) {
                var index = $(e.currentTarget.parentElement.parentElement).attr('index');
                var userId = this.followings[index].userId;
                this.$http.post('/teamnote/follow', {
                    userId: userId
                }, {
                    responseType: "json",
                    emulateJSON: true
                }).then(function(response) {
                    following.followings[index].isFollowed = 1;
                    if (following.self) {
                        info.followingsnum++;
                    }
                })
            }
        }
    });

    var follower = new Vue({
        el: '#follower',
        data: {
            followers: [],
            self: false
        },
        created: function () {
            this.$http.get('/teamnote/getFollowers', {
                responseType: "json",
                params: {
                    userId: <%=userId%>
                },
                emulateJSON: true
            }).then(function(response) {
                this.followers = JSON.parse(response.body.followers);
                this.self = response.body.self;
            });
        },
        methods: {
            _self: function(userId) {
                return (userId === user.userId)
            },
            hover: function(e) {
                var btn = e.currentTarget;
                btn.textContent = "取消关注";
                $(btn).removeClass('btn-primary');
                $(btn).addClass('btn-danger');
            },
            _hover: function(e) {
                var btn = e.currentTarget;
                btn.textContent = "正在关注";
                $(btn).removeClass('btn-danger');
                $(btn).addClass('btn-primary');
            },
            unfollow: function(e) {
                var index = $(e.currentTarget.parentElement.parentElement).attr('index');
                var confirm = window.confirm("您将取消关注用户 " + this.followers[index].username);
                if (confirm) {
                    var userId = this.followers[index].userId;
                    this.$http.post('/teamnote/unfollow', {
                        userId: userId
                    }, {
                        responseType: "json",
                        emulateJSON: true
                    }).then(function(response) {
                        follower.followers[index].isFollowed = 0;
                        e.target.textContent = "关注";
                        if (following.self) {
                            info.followingsnum--;
                        }
                    })
                }
            },
            follow: function(e) {
                var index = $(e.currentTarget.parentElement.parentElement).attr('index');
                var userId = this.followers[index].userId;
                this.$http.post('/teamnote/follow', {
                    userId: userId
                }, {
                    responseType: "json",
                    emulateJSON: true
                }).then(function(response) {
                    follower.followers[index].isFollowed = 1;
                    if (follower.self) {
                        following.followings.push(response.body);
                        info.followingsnum++;
                    }
                })
            }

        }
    });

    var following_tag = new Vue({
        el: '#following-tag',
        data: {
            tags: [],
            self: null
        },
        created: function() {
            this.$http.get('/teamnote/getTagsOfUser', {
                responseType: "json",
                params: {
                    userId: <%=userId%>
                }
            }).then(function(response) {
                this.tags = response.body;
                this.self = info.self;
            });
        },
        methods: {
            unfollowTag: function(e) {
                var confirm = window.confirm("您确定要取消关注该标签吗？");
                if (confirm) {
                    var tagId = e.srcElement.parentElement.parentElement.id;
                    this.$http.get('/teamnote/unfollowTag', { params: { tagId: tagId } }).then(function() {
                        location.reload();
                    });
                }
            }
        }
    });

    document.getElementById("notebookCover").onchange = function() {
        var path = $('#notebookCover').val();
        var $input = document.getElementById("notebookCover");
        var $label = $('#nootbookCoverLabel');
        var file_name = path.split("\\")[2];
        if (file_name.length > 12) {
            file_name = file_name.substr(0, 12) + "...";
        }
        $label[0].innerText = file_name;
        $label.append($input);
    };

    $('#newNotebook').click(function(){
        var createNotebookForm = new FormData($('#createNotebookForm')[0]);
        $.ajax({
            url : "/teamnote/createNotebook",
            type : "post",
            data : createNotebookForm,
            processData : false,
            contentType : false,
            success : function(data) {
                var json = JSON.parse(data);
                if (json.result === "success")
                    location.reload();
                else {
                    alert("Error during creating notebook.")
                }
            }
        })
    })
</script>