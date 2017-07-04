/**
 * Created by rudeigerc on 2017/7/4.
 */
$(document).ready(function() {
    $('#giveOwnershipModal').on('show.bs.modal', function () {
        $.ajax({

        });
    });

    $('#giveOwnership').click(function () {
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

    $('#workgroup-tab').click(function () {
        $.ajax({
            url: "cooperate/allnotebooks",
            type: "get",
            success: function(data) {
                document.getElementById("workgroup").innerHTML = "";
                var json = JSON.parse(data);
                var notebook = json[0];
                var tags = json[1];
                var creator = json[2];
                var owner = json[3];
                var collabrators = json[4];
                for (var i in notebook) {
                    var html = '<div class="row">'+
                        '<div class="col-md-12" style="margin-top: 20px;">' +
                        '<div class="row">' +
                        '<div class="col-md-2 text-center mx-auto">' +
                        '<img id="cover" src="'+ "image/" + notebook[i].cover +'" style="height: 75px; width: 75px;">' +
                        '</div>' +
                        '<div class="col-md-7">' +
                        '<h4 class="card-title">' + notebook[i].title + '</h4>' +
                        '<h6 class="card-subtitle mb-2 text-muted"> ' +
                        // TODO: tag?
                        '<i class="fa fa-tag" aria-hidden="true"></i>&nbsp;';

                    for (var tag in tags[i]) {
                        html += '<kbd>' + tags[i][tag].tagName + '</kbd>&nbsp;'
                    }

                    html += '</h6>' +
                        // TODO: date format
                        '<small>创建者 <strong>' + creator[i].username + '</strong> · 所有者 <strong>'  + owner[i].username + '</strong> · 创建时间 ' + notebook[i].createTime + '</small>' +
                        '<br>' +
                        '<div style="margin: 10px auto;">' +
                        '<img src="image/user_6.png" style="width: 50px; height: 50px;">&nbsp;' +
                        '<img src="image/user_9.png" style="width: 50px; height: 50px;">' +
                        '</div>' +
                        '</div>' +
                        '<div class="col-md-3 workgroup-btn">' +
                        '<a class="btn btn-outline-primary center-block" role="button" href="workgroup.jsp">' +
                        '<i class="fa fa-users fa-fw" aria-hidden="true"></i>&nbsp;进入工作组' +
                        '</a>' +
                        '<button class="btn btn-outline-warning center-block" type="button">' +
                        '<i class="fa fa-envelope-open fa-fw" aria-hidden="true"></i>&nbsp;邀请用户' +
                        '</button>' +
                        '</div>' +
                        '</div>' +
                        '</div>' +
                        '</div>' +
                        '<div class="dropdown-divider"></div>';
                    $('#workgroup').append(html);
                }
            }
        });
    });
});
