/**
 * Created by lxh on 2017/7/4.
 */

$("#callDialog").click(function(e) {
    $('#modalTitle').html("添加标题");

    $("input[name='noteTitle']").val("");

    $('#modal').modal('show');
});

$("#save").click(function(e) {
    var content = CKEDITOR.instances.editor.getData();
    var noteTitle = $("input[name='noteTitle']").val();
    console.log(noteTitle);
    alert(content);
    $.ajax({
        url : 'saveFirstEdition',
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
