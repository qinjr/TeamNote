$(document).ready(function() {
    var vnlist = [];
    $.ajax({
        url:"admin/getVerifyNotes",
        dataType: "text",
        success: function(data){
            var json = JSON.parse(data);
            for(var i=0;i < json.length;i++){
                if(json[i].checked === 0){
                    var oneReport = [json[i].verifyId, json[i].noteId, json[i].title, json[i].reason, json[i].reporterId, json[i].date,JSON.parse(json[i].content).content];
                    vnlist.push(oneReport);
                }
            }
            $('#reportedNotes').DataTable( {
                data: vnlist,
                destroy:true,
                columns: [
                    { title: "审核ID"},
                    { title: "笔记ID"},
                    { title: "笔记标题" },
                    { title: "举报原因" },
                    { title: "举报者ID" },
                    { title: "举报时间" },
                    { title: "笔记内容", render:function(data, type, row) { return '<button class="btn btn-link info" type="button">' + 'view' + '</a>'; } }
                ]
            } );
        }
    });

    var ulist = [];
    $.ajax({
        url: "admin/getAllUsers",
        dataType: "text",
        success: function (data) {
            data = JSON.parse(data);
            for (var i = 0; i < data.length; ++ i) {
                var user = [data[i].userId, data[i].username, data[i].deleteCount, data[i].role];
                ulist.push(user)
            }
            $('#users').DataTable( {
                data: ulist,
                destroy:true,
                columns: [
                    { title: "用户ID"},
                    { title: "用户名"},
                    { title: "举报删除次数" },
                    { title: "用户封禁状态" },
                    { title: "操作", render:function(data, type, row) { return '<button class="btn btn-link userban" type="button">' + '改变封禁状态' + '</a>'; } }
                ]
            } );
        }
    });

    var vclist = [];
    $.ajax({
        url:"admin/getVerifyComments",
        dataType: "text",
        success: function(data){
            var json = JSON.parse(data);
            for(var i=0;i < json.length;i++){
                var oneReport = [json[i].verifyId, json[i].commentId, json[i].content, json[i].reporterId, json[i].reason, json[i].date];
                vclist.push(oneReport);
            }
            $('#comments').DataTable( {
                data: vclist,
                destroy:true,
                columns: [
                    { title: "审核ID"},
                    { title: "评论ID"},
                    { title: "内容" },
                    { title: "举报者ID" },
                    { title: "举报原因" },
                    { title: "举报事件" },
                    { title: "处理", render:function(data, type, row) { return '<button class="btn btn-link bc" id="banComment" type="button">' + '封禁' + '</button>'
                                                                        + '<button class="btn btn-link ic" id="ignoreComment" type="button">' + '忽略' + '</button>'; } }
                ]
            } );
        }
    });

    $(document.body).on('click','.info',function(){
        var verifyId = $(this).parent().prev().prev().prev().prev().prev().prev().text();
        for(var i=0;i < vnlist.length;i++){
            if(vnlist[i][0] == verifyId ) {
                $("#noteContent").html(vnlist[i][6]);
                $("#contentModal").attr("verifyId", verifyId).modal("show");
            }
        }
    });

    $("#ban").click(function(){
        var verifyId = $("#contentModal").attr("verifyId");
        $.ajax({
            url: "/teamnote/admin/banNote",
            processData: true,
            dataType: "text",
            type: "post",
            data: {
                verifyId: verifyId
            },
            success: function (data) {
                var json = JSON.parse(data);
                if (json.result === "success") {
                    alert("封禁成功！");
                    location.reload();
                } else {
                    alert("封禁失败。");
                }
                $("#contentModal").modal("hide");
            }
        });
    });

    $("#ignore").click(function(){
        var verifyId = $("#contentModal").attr("verifyId");
        $.ajax({
            url: "/teamnote/admin/ignoreNote",
            processData: true,
            dataType: "text",
            type: "post",
            data: {
                verifyId: verifyId
            },
            success: function (data) {
                var json = JSON.parse(data);
                if (json.result === "success") {
                    alert("举报已忽略");
                    location.reload();
                } else {
                    alert("错误");
                }
                $("#contentModal").modal("hide");
            }
        });
    });

    $(document.body).on('click','.bc',function() {
        var verifyId = $(this).parent().prev().prev().prev().prev().prev().prev().text();
        $.ajax({
            url: "/teamnote/admin/banComment",
            processData: true,
            dataType: "text",
            type: "post",
            data: {
                verifyId: verifyId
            },
            success: function (data) {
                var json = JSON.parse(data);
                if (json.result === "success") {
                    alert("封禁成功");
                    location.reload();
                } else {
                    alert("错误");
                }
            }
        });
    });

    $(document.body).on('click','.ic',function() {
        var verifyId = $(this).parent().prev().prev().prev().prev().prev().prev().text();
        $.ajax({
            url: "/teamnote/admin/ignoreComment",
            processData: true,
            dataType: "text",
            type: "post",
            data: {
                verifyId: verifyId
            },
            success: function (data) {
                var json = JSON.parse(data);
                if (json.result === "success") {
                    alert("举报已忽略");
                    location.reload();
                } else {
                    alert("错误");
                }
            }
        });
    });


    $(document.body).on('click','.userban',function(){
        var userId = $(this).parent().prev().prev().prev().prev().text();
        $.ajax({
            url : "admin/changeUserStatus",
            dataType : "text",
            data: {
                userId: userId
            },
            success : function (data) {
                console.log(data);
            }
        });
    });
});