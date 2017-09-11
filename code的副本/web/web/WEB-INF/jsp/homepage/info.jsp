<%--
  Created by IntelliJ IDEA.
  User: rudeigerc
  Date: 2017/7/12
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="card" id="info">
    <div class="card-block">
        <div class="row">
            <div class="col-md-2 text-center mx-auto">
                <img :src="'../' + avatar" style="height: 100px; width: 100px;">
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
</script>
