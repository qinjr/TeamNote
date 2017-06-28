<%--
  Created by IntelliJ IDEA.
  User: lxh
  Date: 2017/6/28
  Time: 23:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editor</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet" >
</head>
<body>
<div class="container">
    <div class="col-lg-offset-2 col-lg-8">
        <form>
        <textarea name="editor1" id="editor1">
                This is my textarea to be replaced with CKEditor.
        </textarea>
        </form>
    </div>
</div>
<script src="../ckeditor/ckeditor.js"></script>
<script>
    // Replace the <textarea id="editor1"> with a CKEditor
    // instance.
    CKEDITOR.replace( 'editor1' );
</script>
</body>
</html>
