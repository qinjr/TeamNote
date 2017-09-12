$(document).ready(function() {
    var vnlist = [];
    $.ajax({
        url:"admin/getVerifyNotes",
        dataType: "text",
        success: function(data){
            var json = JSON.parse(data);
            console.log(json);
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
                    { title: "VerifyId"},
                    { title: "NoteId"},
                    { title: "Title" },
                    { title: "Reason" },
                    { title: "ReporterId" },
                    { title: "date" },
                    { title: "content", render:function(data, type, row) { return '<button class="btn btn-link info" type="button">' + 'view' + '</a>'; } }
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

    $.ajax({
        url: "admin/getAllNotebooks",
        dataType: "text",
        success: function (data) {
            console.log(data);
        }
    });

    $.ajax({
        url: "admin/getNotes",
        dataType: "text",
        data: {
            notebookId: 1
        },
        success: function (data) {
            console.log(data);
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
                    $("#verifyNotes").click();
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
                    $("#verifyNotes").click();
                } else {
                    alert("错误");
                }
                $("#contentModal").modal("hide");
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