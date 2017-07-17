<%--
  Created by IntelliJ IDEA.
  User: rudeigerc
  Date: 2017/6/30
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>

<div class="container">
    <div class="card" id="info">
        <div class="card-block">
            <div class="row">
                <div class="col-md-2 text-center mx-auto">
                    <img :src="'<%=path%>/' + avatar" style="height: 100px; width: 100px;">
                </div>
                <div class="col-md-7">
                    <h4 class="card-title">{{ username }}</h4>
                    <p class="card-subtitle mb-2 text-muted">
                        <i class="fa fa-tag" aria-hidden="true"></i>&nbsp;{{ personalStatus }}
                    </p>
                    <p class="card-subtitle mb-2 text-muted">
                        <i class="fa fa-envelope-o" aria-hidden="true"></i>&nbsp;{{ email }}
                    </p>
                    <p><a href="#">关注人 {{ followingsnum }}</a> · <a href="#">关注者 {{ followersnum }}</a></p>
                </div>
                <div class="col-md-3"></div>
            </div>
        </div>
    </div>
    <div class="card">
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
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="javascript:void(0)" role="button" aria-haspopup="true" aria-expanded="false">关注</a>
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
                    <div v-for="_note in note">
                        <div class="row" style="margin-top: 20px;">
                            <div class="col-md-2 text-center mx-auto">
                                <img src="" :src="'<%=path%>/' + _note.cover" style="height: 75px; width: 75px;">
                            </div>
                            <div class="col-md-10">
                                <h4 class="card-title" style="margin-bottom: 6px;">{{ _note.title }}</h4>
                                <i class="fa fa-tag" aria-hidden="true"></i>
                                <div style="display: inline;" v-for="tag in json(_note.tags)">
                                    <kbd class="card-subtitle">{{ tag }}</kbd>&nbsp;
                                </div>
                                <p class="card-text" style="word-break: break-all; margin-top: 16px;">
                                    {{ _note.description }}
                                </p>
                                <footer>
                                    <small>创建者 <strong>{{ _note.creator }}</strong> · 所有者 <strong>{{ _note.owner }}</strong> · 创建时间 {{ _note.createTime }}</small>
                                    <br><br>
                                    <button class="btn btn-outline-secondary center-block" type="button" style="border: none;">
                                        <i class="fa fa-star fa-fw" aria-hidden="true"></i>&nbsp;{{ _note.star }}
                                    </button>
                                    <!-- TODO: comment -->
                                    <button class="btn btn-outline-secondary center-block" type="button" style="border: none;"
                                            data-toggle="collapse" :data-target="'#comment_' + _note.title" aria-expanded="false" :aria-controls="'comment_' + _note.title">
                                        <i class="fa fa-comments fa-fw" aria-hidden="true"></i>&nbsp;评论
                                    </button>
                                    <button class="btn btn-outline-secondary center-block btn-collection" type="button" style="border: none;">
                                        <i class="fa fa-flag fa-fw" aria-hidden="true"></i>&nbsp;收藏
                                    </button>
                                    <button class="btn btn-outline-secondary center-block" type="button" style="border: none;">
                                        <i class="fa fa-exclamation-triangle fa-fw" aria-hidden="true"></i>&nbsp;举报
                                    </button>
                                    <div class="collapse" :id="'comment_' + _note.title">
                                        <div class="card card-block" style="width: inherit;">
                                            Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident.
                                        </div>
                                    </div>
                                </footer>
                            </div>
                        </div>
                        <div class="dropdown-divider"></div>
                    </div>
                </div>

                <!-- TODO: workgroup loading / null-->
                <div class="tab-pane fade" id="workgroup" role="tabpanel" aria-labelledby="workgroup-tab">
                    <div v-if="loading" style="text-align: center; padding-top: 20px;">
                        <i class="fa fa-spinner fa-pulse fa-2x fa-fw"></i>
                        <span class="sr-only">Loading...</span>
                    </div>
                    <div v-if="notebooksdetails.length === 0">
                        <div class="alert alert-success" role="alert" style="margin-top: 16px;">
                            <i class="fa fa-info-circle" aria-hidden="true"></i>
                            该用户没有加入的工作组
                        </div>
                    </div>
                    <div class="row" v-else="" v-for="notebookdetail in notebooksdetails">
                        <div class="col-md-12" style="margin-top: 20px;">
                            <div class="row">
                                <div class="col-md-2 text-center mx-auto">
                                    <img :src="notebookdetail.notebook.cover" style="height: 75px; width: 75px;">
                                </div>
                                <div class="col-md-7">
                                    <h4 class="card-title" style="margin-bottom: 6px;">{{ notebookdetail.notebook.title }}</h4>
                                    <i class="fa fa-tag" aria-hidden="true"></i>
                                    <div style="display: inline;" v-for="tag in notebookdetail.tags">
                                        <kbd class="card-subtitle">{{ tag.tagName }}</kbd>&nbsp;
                                    </div>
                                    <div style="margin-top: 5px; margin-bottom: 5px;">
                                        <small>创建者 <strong>{{ notebookdetail.creator.username }}</strong> · 所有者 <strong>{{ notebookdetail.owner.username }}</strong> · 创建时间 {{ notebookdetail.notebook.createTime }}</small>
                                    </div>
                                    <div style="margin: 10px auto; display: inline;" v-for="collaborator in notebookdetail.collaborators">
                                        <img :src="'<%=path%>/' + collaborator.avatar" style="width: 50px; height: 50px;">&nbsp;
                                    </div>
                                </div>
                                <div class="col-md-3 workgroup-btn">
                                    <a class="btn btn-outline-primary center-block" role="button" :href="'<%=path%>/cooperate/workgroup?notebookId=' + notebookdetail.notebook.notebookId">
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

                <!-- TODO: collection -->
                <div class="tab-pane fade" id="collection" role="tabpanel" aria-labelledby="collection-tab">collection</div>

                <!-- TODO: follow -->
                <div class="tab-pane fade" id="following" role="tabpanel" aria-labelledby="following-tab">following</div>
                <div class="tab-pane fade" id="follower" role="tabpanel" aria-labelledby="follower-tab">follower</div>
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
    var info = new Vue({
        el: '#info',
        data: {
            username: null,
            personalStatus: null,
            email: null,
            avatar : null,
            followersnum : null,
            followingsnum : null
        },
        created: function() {
            this.$http.get('/teamnote/userdetail').then(function(response){
                info.username = JSON.parse(response.body.userInfo).username;
                info.personalStatus = JSON.parse(response.body.user).personalStatus;
                info.email = JSON.parse(response.body.userInfo).email;
                info.avatar = JSON.parse(response.body.user).avatar;
                info.followersnum = JSON.parse(response.body.user).followers.length;
                info.followingsnum = JSON.parse(response.body.user).followings.length;
            }, function() {
                console.log("user info error")
            });
        }
    });

    var notebooks = new Vue({
        el: '#workgroup',
        data: {
            notebooksdetails: null,
            loading: true
        },
        created: function () {
            this.$http.get('/teamnote/cooperate/allworkgroups').then(function(response){
                console.log("success");
                this.loading = false;
                notebooks.notebooksdetails = response.body;
            }, function() {
                console.log("workgroup error");
            });
        }
    });


    var tag = location.href.split('#')[1];
    if (tag !== undefined) {
        $('li.nav-item > a.nav-link').each(function() {
            $(this).removeClass('active');
            $(this).attr('aria-expanded', false);
        });
        $('div.tab-content > div.tab-pane').each(function() {
            $(this).removeClass('active');
            $(this).removeClass('show');
            $(this).attr('aria-expanded', false);
        });
        var $tab = null;
        var $el = null;
        switch(tag) {
            case "notebook": {
                $tab = $('#notebook-tab');
                $el = $('#notebook');
                break;
            }
            case "workgroup": {
                $tab = $('#workgroup-tab');
                $el = $('#workgroup');
                break;
            }
            case "collection": {
                $tab = $('#collection-tab');
                $el = $('#collection');
                break;
            }
            default: {
                break;
            }
        }
        $tab.addClass('active');
        $tab.attr('aria-expanded', true);
        $el.addClass('active');
        $el.addClass('show');
        $el.attr('aria-expanded', true);
    }

    $.ajax({
        url: "/teamnote/recommend",
        type: "get",
        dataType: "json",
        success: function(data) {
            index.note = data;
        }
    });

    var index = new Vue({
        el: '#notebook',
        data: {
            note: []
        },
        methods: {
            json: function(tag) {
                return JSON.parse(tag);
            }
        }
    });

</script>