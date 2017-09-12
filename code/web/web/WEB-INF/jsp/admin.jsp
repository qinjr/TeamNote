<%--
  Created by IntelliJ IDEA.
  User: rudeigerc
  Date: 2017/7/11
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>
<link href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css" rel="stylesheet">
<div class="container">
    <table id="reportedNotes" class="display" width="100%"></table>
    <button id="verifyNotes">verifyNotes</button>
    <button id="allNotebooks">allNotebooks</button>
    <button id="allUsers">allUsers</button>
    <button id="notesOfNotebook">notesOfNotebook</button>

    <!-- contentModal -->
    <div class="modal fade" id="contentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="contentModalTitle">笔记内容</h4>
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span><span class="sr-only"></span>
                    </button>
                </div>
                <div class="modal-body">
                    <div id="noteContent" style="word-break: break-all"></div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-success" id="ignore">忽略</button>
                    <button type="button" class="btn btn-danger" id="ban">封禁</button>
                </div>
            </div>
        </div>
    </div>
    <!-- /contentModal -->

    <footer>
        <p>&copy; 2017 TeamNote Team</p>
    </footer>
</div>
<%@ include file="footer.jsp"%>

<script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script src="<%=path%>/js/admin.js"></script>
