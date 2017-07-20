/**
 * Created by rudeigerc on 2017/7/4.
 */
var noteId = -1;
var websocket;

$(document).ready(function() {
    var notebookId = $('.notebook').attr("id");
    var avatar = $('#user_avatar').attr('src');
    /* chatting record */
    var lastChat = -1;


    /* WebSocket */
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8080/teamnote/chat");
    } else if ('MozWebSocket' in window) {
        websocket = new MozWebSocket("ws://localhost:8080/teamnote/chat");
    } else {
        websocket = new SockJS("http://localhost:8080/teamnote/chat/sockjs");
    }

    websocket.onopen = function (event) {
        console.log("WebSocket: connected");
        //console.log(event);
    };

    websocket.onclose = function (event) {
        console.log("WebSocket: disconnected");
        //console.log(event);
    };

    websocket.onmessage = function (msg) {
        var data = JSON.parse(msg.data);
        addContent("#4A90E2", "/teamnote/" +data.avatar, data.fromName, data.date, data.text);
        $('#chat_icon').attr('style', 'color: #4A90E2');
        $('#chatbox').scrollTop($('#chatbox')[0].scrollHeight);
    };

    /* Date */
    Date.prototype.Format = function (fmt) {
        var o = {
            "M+": this.getMonth() + 1,
            "d+": this.getDate(),
            "h+": this.getHours(),
            "m+": this.getMinutes(),
            "s+": this.getSeconds(),
            "q+": Math.floor((this.getMonth() + 3) / 3),
            "S": this.getMilliseconds()
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    };

    $('#showChat').click(function() {
        $('#chat_icon').removeAttr('style');
        var chat, chatContent;
        if(document.getElementById("chatContent").innerHTML === "") {
            $.ajax({
                url: "/teamnote/cooperate/getGroupChat",
                processData: true,
                dataType: "text",
                data: {
                    notebookId: notebookId,
                    lastChat : lastChat
                },
                success: function (data) {
                    var json = JSON.parse(data);
                    lastChat = json.lastChat;
                    var currentUser = json.currentUser;
                    var chatList = JSON.parse(json.result);
                    for (var i = 0; i < chatList.length; i++) {
                        if(chatList[i].uid === currentUser){
                            prependContent("#00cc7d", "/teamnote/" +chatList[i].avatar, chatList[i].username, chatList[i].datetime, chatList[i].content);
                        } else {
                            prependContent("#4A90E2", "/teamnote/" +chatList[i].avatar, chatList[i].username, chatList[i].datetime, chatList[i].content);
                        }
                    }
                    if(lastChat !== -2) {
                        $("#chatContent").prepend("<div style='text-align: center;'><button type='button' class='btn btn-link more-record' id='moreChat'>更多记录</button></div>");
                    } else {
                        $("#chatContent").prepend("<div class='no-more-record'>没有更多记录</div>");
                    }
                    $('#chatbox').scrollTop($('#chatbox')[0].scrollHeight);
                }
            })
        }
    });

    $("#chatContent").on("click","#moreChat",function(){
        $("#moreChat").remove();
        $.ajax({
            url: "/teamnote/cooperate/getGroupChat",
            processData: true,
            dataType: "text",
            data: {
                notebookId: notebookId,
                lastChat : lastChat
            },
            success: function (data) {
                var json = JSON.parse(data);
                lastChat = json.lastChat;
                var currentUser = json.currentUser;
                var chatList = JSON.parse(json.result);
                var originScrollHeight = $('#chatbox')[0].scrollHeight;
                for (var i = 0; i < chatList.length; i++) {
                    if(chatList[i].uid === currentUser){
                        prependContent("#00cc7d", "/teamnote/" +chatList[i].avatar, chatList[i].username, chatList[i].datetime, chatList[i].content);
                    } else {
                        prependContent("#4A90E2", "/teamnote/" +chatList[i].avatar, chatList[i].username, chatList[i].datetime, chatList[i].content);
                    }
                }
                $('#chatbox').scrollTop($('#chatbox')[0].scrollHeight - originScrollHeight);
                if(lastChat !== -2) {
                    $("#chatContent").prepend("<div style='text-align: center;'><button type='button' class='btn btn-link more-record' id='moreChat'>更多记录</button></div>");
                } else {
                    $("#chatContent").prepend("<div class='no-more-record'>没有更多记录</div>");
                }
            }
        })
    });

    function addContent(colorCode, avatar, name, date, text) {
        $("#chatContent").append("<div style='margin-bottom: 10px;'>" +
            "<label style='color:" + colorCode + "'>" +
            "<div class='media'>" +
            "<img class='d-flex mr-3 img-50px rounded' src='" + avatar + "'>" +
            "<div class='media-body'>" +
            name + "<br>" + "<small style='color: lightgrey'>" + date + "</small>" +
            "</div>" +
            "</div>" +
            "</label>" +
            "<div>" + text.replace(/\n/g, '<br>') + "</div>" +
            "</div>");
    }

    function prependContent(colorCode, avatar, name, date, text) {
        $("#chatContent").prepend("<div style='margin-bottom: 10px;'>" +
            "<label style='color:" + colorCode + "'>" +
            "<div class='media'>" +
            "<img class='d-flex mr-3 img-50px rounded' src='" + avatar + "'>" +
            "<div class='media-body'>" +
            name + "<br>" + "<small style='color: lightgrey'>" + date + "</small>" +
            "</div>" +
            "</div>" +
            "</label>" +
            "<div>" + text.replace(/\n/g, '<br>') + "</div>" +
            "</div>");
    }

    $('#sendMsg').click(function() {
        //var notebookId = $('.notebook').attr("id");
        var text = $("#msg").val();
        var re = /\n/g;
        var check = text.replace(re, "");
        if (check === "") {
            $("#msg").val("");
            return;
        } else {
            $.ajax({
                url: "/teamnote/cooperate/sendMsg",
                processData: true,
                dataType: "text",
                type: "post",
                data: {
                    notebookId: notebookId,
                    text: text
                },
                success: function (data) {
                    var json = JSON.parse(data);
                    if (json.result === "success") {
                        addContent("#00cc7d", avatar, json.sender, new Date().Format("yyyy-MM-dd hh:mm:ss"), text);
                        $("#msg").val("");
                        $('#chatbox').scrollTop($('#chatbox')[0].scrollHeight);
                    } else {
                        alert("消息发送失败，请稍后再试。");
                    }
                }
            });
        }
    });

    /* keydown */
    $(document).keydown(function(event){
        if (event.shiftKey && event.keyCode === 13) {
            return;
        }

        if( event.keyCode === 13 ) {
            $("#sendMsg").trigger("click");
        }
    });

    $('#clearMsg').click(function(){
        $("#chatContent").empty();
    });

    /* give ownership */
    $('.giveOwnership').click(function() {
        var notebookId = $('.notebook').attr('id');
        var newOwnerName = $('input[name="newOwner"]').val();
        var confirm = window.confirm("警告: 所有权转让后无法还原。");
        if (!confirm) return;
        $.ajax({
            url: "/teamnote/cooperate/giveownership",
            processData: true,
            dataType: "text",
            data: {
                newOwnerName : newOwnerName,
                notebookId : notebookId
            },
            success: function(data) {
                var json = JSON.parse(data);
                if (json.result === "success") {
                    alert(json.newOwner + " 已被钦定为新的拥有者。");
                }
                else {
                    alert("你不是笔记本所有者，无法转让所有权。");
                }
                $('#giveOwnershipModal').modal('hide');
            }
        });
    });

    $('#giveOwnership').click(function () {
        $('input[name="newOwner"]').val("");
        $('#giveOwnershipModal').modal('show');
    });

    /* call dialog */
    $('#callDialog').click(function () {
        if (noteId === -1) {
            $('#newNoteModalTitle').html("添加标题");
            $('input[name="noteTitle"]').val("");
            $('#newNoteModal').modal('show');
        }
        else {
            $('#updateNoteModalTitle').html("更新笔记");
            $('input[name="message"]').val("");
            $('#updateNoteModal').modal('show');
        }
    });

    /* savenote */
    $('.savenote').click(function () {
        var content = CKEDITOR.instances.editor.getData();
        CKEDITOR.instances.editor.resetDirty();
        var notebookId = $('.notebook').attr('id');
        if (noteId === -1) {
            var noteTitle = $('input[name="noteTitle"]').val();
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
                    if (json.result === "success") {
                        location.reload();
                    } else if(json.result === "sensitive") {
                        alert("根据相关法规政策，无法进行该操作，请修改您的笔记内容。");
                    } else {
                        alert("操作失败，请稍后再试。");
                    }
                }
            });
            $('#modal').modal('hide');
        }
        else {
            var message = $('input[name="message"]').val();
            $.ajax({
                url : "/teamnote/updateNote",
                processData : true,
                dataType : "text",
                type : "post",
                data : {
                    noteId : noteId,
                    content : content,
                    message : message
                },
                success : function(data) {
                    var json = JSON.parse(data);
                    if (json.result === "success") {
                        location.reload();
                    } else if(json.result === "sensitive") {
                        alert("根据相关法规政策，无法进行该操作，请修改您的笔记内容。");
                    } else {
                        alert("操作失败，请稍后再试。");
                    }
                }
            });
            $('#modal').modal('hide');
        }
    });

    /* export note */
    $('#chooseType').click(function(){
        //TODO
        if (CKEDITOR.instances.editor.checkDirty()) {
            alert("导出笔记前请先保存笔记");
        } else {
            $('#exportModalTitle').html("选择导出内容");
            $('#exportType').val("pdf");
            $('#exportContent').val("note");
            $('#exportModal').modal('show');
        }
    });

    $('.export').click(function () {
        var exportType = $('#exportType').val();
        var exportContent = $('#exportContent').val();
        if(exportContent === "note") {
            window.location.href = "/teamnote/exportNote?type=" + exportType + "&noteId=" + noteId;
        } else {
            window.location.href = "/teamnote/exportNotebook?type=" + exportType + "&notebookId=" + notebookId;
        }
        $('#exportModal').modal('hide');
    });

    /* import note */
    $('#uploadNote').click(function () {
        $('#uploadModalTitle').html("选择文件");
        $('#uploadModal').modal('show');
    });

    $('#upload').click(function() {
        var uploadForm = new FormData($('#uploadForm')[0]);
        $.ajax({
            url : "/teamnote/uploadNote",
            type : "post",
            data : uploadForm,
            processData : false,
            contentType : false,
            success : function(data) {
                var json = JSON.parse(data);
                if (json.result === "success")
                    location.reload();
                else if (json.result === "wrongType"){
                    alert("不支持该格式");
                } else {
                    alert("上传文件失败")
                }
            }
        })
    });

    $('.note').click(function(e) {
        if(CKEDITOR.instances.editor.checkDirty()) {
            if(confirm("转到其他笔记将会失去当前笔记未保存的内容，确认跳转吗？")){
            }
            else{
                return;
            }
        }
        $('#chooseType').removeAttr("style");//add
        noteId = parseInt(e.target.id);
        $.ajax({
            url : "/teamnote/getNote",
            processData : true,
            dataType : "text",
            type : "post",
            data : {
                noteId : noteId
            },
            success : function(data) {
                var json = JSON.parse(data);
                var version = json.versionPointer;
                var history = json.history[version];
                var content = JSON.parse(history).content;
                CKEDITOR.instances.editor.setData(content,{
                    callback: function() {
                        CKEDITOR.instances.editor.resetDirty();
                    }
                });
            }
        });
    });

    $('#newNote').click(function() {
        $('#chooseType').attr("style","display:none");   // add
        noteId = -1;
        $('a.note').each(function() {
            if ($(this).hasClass("active")) {
                $(this).removeClass("active");
            }
        });
        CKEDITOR.instances.editor.setData("");
    });

    /* invite */
    $("#inviteCollaborator").click(function () {
        $('#inviteCollaboratorModalTitle').html('邀请用户');
        $('input[name="inviteUsername"]').val("");
        $('input[name="inviteDescription"]').val("");
        $('#inviteCollaboratorModal').modal('show');
    });

    $(".invite").click(function() {
        var inviteUsername = $('input[name="inviteUsername"]').val();
        var inviteDescription = $('input[name="inviteDescription"]').val();
        var notebookId = $('.notebook').attr('id');
        $.ajax({
            url : "/teamnote/cooperate/invite",
            processData : true,
            dataType : "text",
            type : "post",
            data : {
                inviteUsername : inviteUsername,
                inviteDescription : inviteDescription,
                notebookId : notebookId
            },
            success : function (data) {
                console.log(data);
                var json = JSON.parse(data);
                if (json.result === "success") {
                    $('#inviteCollaboratorModal').modal('hide');
                    alert("请求已发送，等待对方接受邀请");
                }
                else {
                    $('#inviteCollaboratorModal').modal('hide');
                    alert("请求发送失败");
                }
            }
        });
    });

    /* edit */
    $('.btn-edit').click(function() {
        var noteId = parseInt(this.parentNode.previousElementSibling.id);
        var noteTitle = this.parentNode.previousElementSibling.text;
        $('#noteId-edit').val(noteId);
        $('#noteTitle-edit').val(noteTitle);

    });

    $('.btn-edit-confirm').click(function() {
        var noteId = $('#noteId-edit').val();
        var newNoteTitle = $('#noteTitle-edit').val();
        $.ajax({
            url: "/teamnote/updateNoteTitle",
            dataType: "text",
            type: "post",
            data: {
                noteId: noteId,
                newNoteTitle: newNoteTitle
            },
            success: function () {
                location.reload();
            }
        })
    });

    /* delete */
    $('.btn-delete').click(function() {
        var confirm = window.confirm("该笔记将被删除且无法还原");
        if (!confirm) return;
        var noteId = parseInt(this.parentNode.previousElementSibling.id);
        $.ajax({
            url: "/teamnote/deleteNote",
            dataType: "text",
            type: "post",
            data: {
                noteId: noteId
            },
            success: function () {
                alert("该笔记已被删除");
                location.reload();
            }
        })
    });

    /* config */
    $('#config').click(function() {
        var notebookId = $('.notebook').attr('id');
        $.ajax({
            url: "/teamnote/getNotebookDetail",
            data: { notebookId: notebookId },
            type: "get",
            success: function(data) {
                var json = JSON.parse(data);
                var description = json.description;
                var tags = json.tags;
                var title = json.title;
                $('#noteTitle').val(title);
                $('#noteDescription').val(description);
            }
        })
    });

    $('#config-confirm').click(function () {
        var notebookId = $('.notebook').attr('id');
        var newTitle = $('#noteTitle').val();
        var newDescription = $('#noteDescription').val();
        $.ajax({
            url: "/teamnote/updateNotebookDetail",
            dataType: "text",
            type: "post",
            data: {
                notebookId: notebookId,
                newTitle: newTitle,
                newDescription: newDescription
            },
            success: function () {
                alert("修改成功");
                location.reload();
            }
        })
    });

    var history = new Vue({
        el: '#historyModal',
        data: {
            history: []
        },
        methods: {
            switchVersion: function(e) {
                var versionPointer = parseInt(e.srcElement.parentElement.parentElement.parentElement.id.replace("history_collapse_", ""));
                var confirm = window.confirm("您将切换至版本" + versionPointer + "，是否确定切换？");
                if (!confirm) return;
                var noteId = $('#noteId').val();
                $.ajax({
                    url: '/teamnote/changeVersion',
                    dataType: "text",
                    type: "post",
                    data: {
                        versionPointer: versionPointer,
                        noteId: noteId
                    },
                    success: function() {
                        alert("版本切换成功。");
                        location.reload();
                    }
                });
            }
        }
    });

    $('.btn-history').click(function() {
        var noteId = parseInt(this.parentNode.previousElementSibling.id);
        $('#noteId').val(noteId);
        $.ajax({
            url: '/teamnote/getHistory',
            dataType: "text",
            type: "get",
            data: {
                noteId: noteId
            },
            success: function (response) {
                history.history = [];
                var json = JSON.parse(JSON.parse(response).history);
                for (var _json in json) {
                    history.history.push(JSON.parse(json[_json]));
                }
            }
        });
    });

    var suggestion = new Vue({
        el: '#suggestionModal',
        data: {
            suggestion: []
        },
        methods: {
            mergeSuggestion: function(e) {
                var suggestionId = parseInt(e.srcElement.parentElement.parentElement.parentElement.id.replace("suggestion_collapse_", ""));
                var confirm = window.confirm("您将合并该建议，请确定是否执行。");
                if (!confirm) return;
                var noteId = $('#_noteId').val();
                $.ajax({
                    url: '/teamnote/cooperate/mergeSuggestion',
                    dataType: "text",
                    type: "post",
                    data: {
                        suggestionId: suggestionId,
                        noteId: noteId
                    },
                    success: function() {
                        alert("合并成功。");
                        location.reload();
                    }
                });
            },
            ignoreSuggestion: function(e) {
                var suggestionId = parseInt(e.srcElement.parentElement.parentElement.parentElement.id.replace("suggestion_collapse_", ""));
                var confirm = window.confirm("您将忽略该建议，请确定是否执行。");
                if (!confirm) return;
                $.ajax({
                    url: '/teamnote/cooperate/ignoreSuggestion',
                    dataType: "text",
                    type: "post",
                    data: {
                        suggestionId: suggestionId
                    },
                    success: function() {
                        alert("该建议已被忽略。");
                        location.reload();
                    }
                });
            }
        }
    });

    $('.btn-suggestion').click(function() {
        var noteId = parseInt(this.parentNode.previousElementSibling.id);
        $('#_noteId').val(noteId);
        $.ajax({
            url: '/teamnote/cooperate/getSuggestions',
            dataType: "text",
            type: "get",
            data: {
                noteId: noteId
            },
            success: function (response) {
                suggestion.suggestion = [];
                if (JSON.parse(JSON.parse(response).suggestions).length === 0) return;
                var json = JSON.parse(JSON.parse(response).suggestions);
                for (var _json in json) {
                    suggestion.suggestion.push(json[_json]);
                }
            }
        });
    });
});