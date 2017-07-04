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
                var json = JSON.parse(data);
                var el = document.getElementById("workgroup")
            }
        });

    });


});
