/**
 * Created by lxh on 2017/7/4.
 */

$("#callDialog").click(function() {
    if (1) {
        $("input[name='noteTitle']").val("");
        $('#addNoteModal').modal('show');
    } else {
        $("input[name='message']").val("");
        $('#updateNoteModal').modal('show');
    }
});

$("#save").click(function() {
    var content = CKEDITOR.instances.editor.getData();
    var noteTitle = $("input[name='noteTitle']").val();
    $.ajax({
        url : 'editNote/saveFirstEdition',
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
    $('#addNoteModal').modal('hide');
});

$("#update").click(function() {
    var noteId = 1;
    var content = CKEDITOR.instances.editor.getData();
    var message = $("input[name='message']").val();
    $.ajax({
        url : 'editNote/updateNote',
        processData : true,
        dataType : "text",
        data : {
            noteId : 1,
            content : content,
            message : message
        },
        success : function(data) {
            alert("Success");
        }
    });
    $('#updateNotemodal').modal('hide');
})
