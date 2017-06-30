<%--
  Created by IntelliJ IDEA.
  User: lxh
  Date: 2017/6/29
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Read</title>
</head>
<body>
    <textarea name="editor1" id="editor1">
        <%=request.getParameter("editor")%>
    </textarea>
    <script src="../../ckeditor4.7/ckeditor.js"></script>
    <script src="../js/jquery.min.js"></script>
    <script>
        $(document).ready(function(){
            CKEDITOR.replace( 'editor1', {toolbarCanCollapse: true, toolbarStartupExpanded: false});
            CKEDITOR.on('instanceReady',function(event){
                editor=event.editor;
                editor.setReadOnly(true); //只读
                editor.removeAllListeners();
                setTimeout( function(){
                        // Delay bit more if editor is still not ready.
                        if ( !editor.element ){
                            setTimeout( arguments.callee, 100 );
                            return;
                        }

                        $("#cke_1_toolbar_collapser").css("display","none")//隐藏按钮区
                        //$("#cke_1_bottom").css("display","none");//隐藏脚本区
                    },0
                );
            });
        })
    </script>
</body>
</html>
