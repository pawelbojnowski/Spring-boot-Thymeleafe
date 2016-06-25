$(function () {
    attachEvents();
});

function attachEvents() {
    edit();
    createNew();
    remove();
    cancel();
    reloadModel();
}

function reloadModel() {
    $(document).on('click', "#reloadModel", function () {
        var url = "/other/reloadModel";
        $("#loadFragments").load(url);
    });
}

function edit() {
    $(document).on('click', "table tbody tr td .edit", function () {
        var id = $(this).data("el_id")
        var url = "/indexAjax/" + id;
        $("#editPane").load(url);
    })
}
function cancel() {
    $(document).on('click', "#cancelForm", function () {
        $("#editPane").empty();
    })
}
function createNew() {
    $(document).on('click', "#createNew", function () {
        var url = "/indexAjax/new";
        $("#editPane").load(url);
    })
}
function remove() {
    $(document).on('click', "table tbody tr td .delete", function () {

        var id = $(this).data("el_id");

        var url = "/indexAjax/delete/" + id;
        $("#table").load(url);
    })
}

function saveForm() {
    function error(data) {
        var jsonResponse = data.responseJSON.errors;
        for (instance in jsonResponse) {

            var path = "input[name='" + jsonResponse[instance].field + "']";
            $(path).parent().find(".error").text(jsonResponse[instance].defaultMessage);

        }
    }

    function succes(data) {
        if (data.status === 200) {
            var response = data.responseText;
            $("#content").replaceWith(response);
        }
    }

    function save(data, method) {
        $.ajax({
            type: method,
            contentType: "application/json",
            url: "/indexAjax/save",
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: 'application/json',
            timeout: 100000,
            asyn: true,
            complete: function (data) {
                succes(data);
            },
            error: function (data) {
                if (data.status !== 200) {
                    error(data);
                }
            }
        });
    }

    $(document).on('click', "#editPane #saveForm", function () {
        var id = $(this).data("el_id")
        var data = {}
        data["name"] = $("#editPane #name").val();
        data["number"] = $("#editPane #number").val();
        if (id === undefined) {
            save(data, "POST");
        } else {
            data["id"] = $(this).data("el_id");
            save(data, "PUT");
        }
    })
}

