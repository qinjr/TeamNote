$(document).ready(function() {
    var list=[];
    $("#verifyNotes").click(function() {
        $.ajax({
            url:"admin/getVerifyNotes",
            dataType: "text",
            success: function(data){
                var json = JSON.parse(data);
                console.log(json);
                for(var i=0;i < json.length;i++){
                    if(json[i].checked === 0){
                        var oneReport = [json[i].verifyId, json[i].noteId, json[i].title, json[i].reason, json[i].reporterId, json[i].date,JSON.parse(json[i].content).content];
                        list.push(oneReport);
                    }
                }
                console.log(list);
                $('#reportedNotes').DataTable( {
                    data: list,
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
        })
    });
    $(document.body).on('click','.info',function(){
        var verifyId = $(this).parent().prev().prev().prev().prev().prev().prev().text();
        for(var i=0;i < list.length;i++){
            if(list[i][0] == verifyId ) {
                $("#noteContent").html(list[i][6]);
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
    $("#allNotebooks").click(function () {
        $.ajax({
            url: "admin/getAllNotebooks",
            dataType: "text",
            success: function (data) {
                console.log(data);
            }
        });
    });
    $("#allUsers").click(function () {
        $.ajax({
            url: "admin/getAllUsers",
            dataType: "text",
            success: function (data) {
                console.log(data);
            }
        })
    });
    $("#notesOfNotebook").click(function () {
        $.ajax({
            url: "admin/getNotes",
            dataType: "text",
            data: {
                notebookId: 1
            },
            success: function (data) {
                console.log(data);
            }
        })
    });
});