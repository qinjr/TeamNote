<%--
  Created by IntelliJ IDEA.
  User: rudeigerc
  Date: 2017/7/11
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>
<div class="container">
    <div>
        <div class="alert alert-danger" role="alert">
            <strong>本页面暂不开放。</strong>
        </div>
    </div>
        <p>&copy; 2017 TeamNote Team</p>
    </footer>
</div>
<%@ include file="footer.jsp"%>

<script>
    $("#allNotebooks").click(function(){
        $.ajax({
            url : "admin/getAllNotebooks",
            dataType : "text",
            success : function(data) {
                console.log(data);
            }
        });
    })
    $("#allUsers").click(function(){
        $.ajax({
            url : "admin/getAllUsers",
            dataType : "text",
            success : function(data) {
                console.log(data);
            }
        })
    })
    $("#notesOfNotebook").click(function(){
        $.ajax({
            url : "admin/getNotes",
            dataType : "text",
            data : {
                notebookId : 1
            },
            success : function (data) {
                console.log(data);
            }
        })
    })
</script>
