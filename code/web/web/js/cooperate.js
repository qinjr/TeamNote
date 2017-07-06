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
        console.log(noteTitle);
        alert(content);
        $.ajax({
            url : "saveFirstEdition",
            processData : true,
            dataType : "text",
            data : {
                notebookId : 1,
                noteTitle : noteTitle,
                content : content
            },
            success : function(data) {
                alert("Success");
            }
        });
        $('#modal').modal('hide');
    });
});

function enterWorkgroup() {
    var notebookId = $('#enterWorkgroup').parent().parent().parent().parent().attr("id");
    $.ajax({
        url: "cooperate/workgroup",
        dataType : "text",
        type: "post",
        data: { "notebookId" : notebookId },
        success: function(data) {
            document.open();
            document.write(data);
            document.close();
        }
    });
}