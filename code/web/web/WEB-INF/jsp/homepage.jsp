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
                    <img src="" :src="'<%=path%>/' + avatar" style="height: 100px; width: 100px;">
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
                <div class="col-md-3"></div>
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
                <div class="tab-pane fade show active" id="activity" role="tabpanel" aria-labelledby="activity-tab" aria-expanded="true">activity</div>

                <!-- TODO: notebook -->
                <div class="tab-pane fade" id="notebook" role="tabpanel" aria-labelledby="notebook-tab">
                    <div v-if="note.length === 0">
                        <div class="alert alert-success" role="alert" style="margin-top: 16px;">
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            该用户没有笔记本
                        </div>
                    </div>
                    <div v-else="" v-for="(_note, index) in note">
                        <div class="row" style="margin: 20px 0;">
                            <div class="col-md-2 text-center mx-auto">
                                <img src="" :src="'<%=path%>/' + _note.cover" style="height: 75px; width: 75px;">
                            </div>
                            <div class="col-md-10" :id="'CT_NB_' + _note.notebookId">
                                <h4 class="card-title" style="margin-bottom: 6px;">{{ _note.title }}</h4>
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
                                    <button class="btn btn-outline-secondary center-block" type="button" style="border: none;">
                                        <i class="fa fa-exclamation-triangle fa-fw" aria-hidden="true"></i>&nbsp;举报
                                    </button>
                                </footer>
                            </div>
                        </div>
                        <div class="dropdown-divider"></div>
                    </div>
                </div>

                <!-- TODO: workgroup loading / null-->
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
                                            <img src="" :src="workgroupdetail.notebook.cover" style="height: 75px; width: 75px;">
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
                                                <img :src="'<%=path%>/' + collaborator.avatar" style="width: 50px; height: 50px;">&nbsp;
                                            </div>
                                        </div>
                                        <div class="col-md-3 workgroup-btn">
                                            <a class="btn btn-outline-primary center-block" role="button" :href="'<%=path%>/cooperate/workgroup?notebookId=' + workgroupdetail.notebook.notebookId">
                                                <i class="fa fa-users fa-fw" aria-hidden="true"></i>&nbsp;进入工作组
                                            </a>
                                            <button class="btn btn-outline-warning center-block" type="button">
                                                <i class="fa fa-envelope-open fa-fw" aria-hidden="true"></i>&nbsp;邀请用户
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="dropdown-divider"></div>
                        </div>
                    </div>

                </div>

                <!-- TODO: collection -->
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
                                <img src="" :src="'<%=path%>/' + _note.cover" style="height: 75px; width: 75px;">
                            </div>
                            <div class="col-md-10" :id="'CT_NB_' + _note.notebookId">
                                <h4 class="card-title" style="margin-bottom: 6px;">{{ _note.title }}</h4>
                                <i class="fa fa-tag" aria-hidden="true"></i>
                                <div style="display: inline;" v-for="tag in json(_note.tags)">
                                    <kbd class="card-subtitle">{{ tag }}</kbd>&nbsp;
                                </div>
                                <footer :index="index">
                                    <small>创建者 <strong>{{ _note.creator }}</strong> · 所有者 <strong>{{ _note.owner }}</strong> · 创建时间 {{ n_date(_note.createTime) }}</small>
                                </footer>
                            </div>
                        </div>
                        <div class="dropdown-divider"></div>
                    </div>
                </div>

                <!-- TODO: follow -->
                <div class="tab-pane fade" id="following" role="tabpanel" aria-labelledby="following-tab">
                    <div v-if="followings.length === 0">
                        <div class="alert alert-success" role="alert" style="margin-top: 16px;">
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            该用户没有关注人
                        </div>
                    </div>
                    <div v-else="" class="card-columns">
                        <div class="card user-card" v-for="user in followings">
                            <div>
                                <img class="card-img-top img-100px" src="" :src="'<%=path%>' + user.avatar" :alt="user.username" style="margin: 20px;">
                                <button class="btn btn-outline-primary btn-user-card float-right">正在关注</button>
                            </div>
                            <div class="card-block" style="padding-top: 0;">
                                <h4 class="card-title">{{ user.username }}</h4>
                                <p class="card-text">{{ user.personalStatus }}</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="tab-pane fade" id="follower" role="tabpanel" aria-labelledby="follower-tab">
                    <div v-if="followers.length === 0">
                        <div class="alert alert-success" role="alert" style="margin-top: 16px;">
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            该用户没有关注者
                        </div>
                    </div>
                    <div v-else="" class="card-columns">
                        <div class="card user-card" v-for="user in followers">
                            <div>
                                <img class="card-img-top img-100px" src="" :src="'<%=path%>' + user.avatar" :alt="user.username" style="margin: 20px;">
                                <button class="btn btn-outline-primary btn-user-card float-right">正在关注</button>
                            </div>
                            <div class="card-block" style="padding-top: 0;">
                                <h4 class="card-title">{{ user.username }}</h4>
                                <p class="card-text">{{ user.personalStatus }}</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="tab-pane fade" id="following-tag" role="tabpanel" aria-labelledby="following-tag-tab">following tag</div>
            </div>
        </div>
    </div>
    <footer>
        <p>&copy; 2017 TeamNote Team</p>
    </footer>
</div>

<%@ include file="footer.jsp"%>
<script type="text/javascript">

    // parse tag
    var tag = location.href.split('#')[1];
    if (tag !== undefined) {
        switch(tag) {
            case "notebook": {
                $('#notebook-tab').tab('show');
                break;
            }
            case "workgroup": {
                $('#workgroup-tab').tab('show');
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
            followersnum : null,
            followingsnum : null,
            self : null
        },
        created: function() {
            this.$http.get('/teamnote/userdetail', { params: { userId: <%=userId%> } }).then(function(response){
                info.username = JSON.parse(response.body.userInfo).username;
                info.personalStatus = JSON.parse(response.body.user).personalStatus;
                info.email = JSON.parse(response.body.userInfo).email;
                info.avatar = JSON.parse(response.body.user).avatar;
                info.followersnum = JSON.parse(response.body.user).followers.length;
                info.followingsnum = JSON.parse(response.body.user).followings.length;
                info.self = response.body.self;
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
            followings: []
        },
        created: function () {
            this.$http.get('/teamnote/getFollowings', {
                responseType: "json",
                params: {
                    userId: <%=userId%>
                }
            }).then(function(response) {
                this.followings = response.body;
            });
        }
    });

    var follower = new Vue({
        el: '#follower',
        data: {
            followers: []
        },
        created: function () {
            this.$http.get('/teamnote/getFollowers', {
                responseType: "json",
                params: {
                    userId: <%=userId%>
                }
            }).then(function(response) {
                this.followers = response.body;
            });
        }
    });




</script>