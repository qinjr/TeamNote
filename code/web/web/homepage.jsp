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
                    <img src="image/user_6.png" style="height: 100px; width: 100px;">
                </div>
                <div class="col-md-7">
                    <h4 class="card-title">{{ username }}</h4>
                    <p class="card-subtitle mb-2 text-muted">
                        <i class="fa fa-tag" aria-hidden="true"></i>&nbsp;{{ personalStatus }}
                    </p>
                    <p class="card-subtitle mb-2 text-muted">
                        <i class="fa fa-envelope-o" aria-hidden="true"></i>&nbsp;{{ email }}
                    </p>
                    <p>关注人 20 · 关注者 30</p>
                </div>
                <div class="col-md-3">
                    <button class="btn btn-outline-primary center-block" type="button">
                        <i class="fa fa-pencil-square-o fa-fw" aria-hidden="true"></i>&nbsp;编辑个人资料
                    </button>
                </div>
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
                <li class="nav-item">
                    <a class="nav-link" id="follow-tab" data-toggle="tab" href="#follow" role="tab" aria-controls="follow">关注</a>
                </li>
            </ul>
            <div class="tab-content" id="homepageTabContent">
                <!-- TODO: activity -->
                <div class="tab-pane fade show active" id="activity" role="tabpanel" aria-labelledby="activity-tab">activity</div>
                <!-- TODO: notebook -->
                <div class="tab-pane fade" id="notebook" role="tabpanel" aria-labelledby="notebook-tab">notebook</div>
                <!-- TODO: workgroup -->
                <div class="tab-pane fade" id="workgroup" role="tabpanel" aria-labelledby="workgroup-tab">
                    <div class="row" v-for="notebookdetail in notebooksdetails">
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

                                    <br>
                                    <small>创建者 <strong>{{ notebookdetail.creator.username }}</strong> · 所有者 <strong>{{ notebookdetail.owner.username }}</strong> · 创建时间 {{ notebookdetail.notebook.createTime }}</small>
                                    <br>
                                    <div style="margin: 10px auto; display: inline;" v-for="collaborator in notebookdetail.collaborators">
                                        <img :src="collaborator.avator" style="width: 50px; height: 50px;">&nbsp;
                                    </div>
                                </div>
                                <div class="col-md-3 workgroup-btn">
                                    <a class="btn btn-outline-primary center-block" role="button" :href="'/cooperate/workgroup?notebookId=' + notebookdetail.notebook.notebookId">
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
                <div class="tab-pane fade" id="follow" role="tabpanel" aria-labelledby="follow-tab">follow</div>
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
            username: "rudeigerc",
            personalStatus: "Shanghai Jiao Tong University Software Engineering",
            email: "rudeigerc@gmail.com"
        }
    });

    var notebooks = new Vue({
        el: '#workgroup',
        data: {
            notebooksdetails: null
        },
        created: function () {
            this.$http.get('/teamnote/cooperate/allnotebooks', {
                progress: function(e) {
                    this.progress = (e.loaded / e.total) * 100;
                    console.log(this.progress);
                }
            }).then(function(response){
                console.log("success");
                notebooks.notebooksdetails = response.body;
            }, function(){
                console.log("error");
            });
        }
    });


</script>