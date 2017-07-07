/**
 * Created by rudeigerc on 2017/7/4.
 */
$(document).ready(function() {
    $('#giveOwnershipModal').on('show.bs.modal', function () {
        $.ajax({

        });
    });

    $('#giveOwnership').click(function () {
        var notebookId = document.getElementsByClassName("notebook")[0].attr("id");
        var confirm = window.confirm("警告: 所有权转让后无法还原");
        if (!confirm) return;
        $.ajax({
            url: "cooperate/giveownership",
            processData: true,
            dataType: "text",
            data: {
                newOwnerId : 56,
                notebookId : 1
            },
            success: function(data) {
                alert(data);
            }
        });
    });

    $('#callDialog').click(function () {
        $('#modalTitle').html("添加标题");
        $('input[name="noteTitle"]').val("");
        $('#modal').modal('show');
    });

    $('#save').click(function () {
        var content = CKEDITOR.instances.editor.getData();
        var noteTitle = $('input[name="noteTitle"]').val();
        var notebookId = $('.notebook').attr('id');
        $.ajax({
            url : "/teamnote/saveFirstEdition",
            processData : true,
            dataType : "text",
            type : "post",
            data : {
                notebookId : notebookId,
                noteTitle : noteTitle,
                content : content
            },
            success : function(data) {
                var json = JSON.parse(data);
                if (json.result === "success")
                    location.reload();
                else {
                    alert("error");
                }
            }
        });
        $('#modal').modal('hide');
    });

    $(".note").click(function(e) {
        var noteId = parseInt(e.target.id);
        $.ajax({
            url : "/teamnote/getNote",
            processData : true,
            dataType : "text",
            type : "post",
            data : {
                noteId : noteId
            },
            success : function(data) {
                console.log(data);
                var json = JSON.parse(data);
                CKEDITOR.instances.editor.setData(json.content);
            }
        });
    })
});