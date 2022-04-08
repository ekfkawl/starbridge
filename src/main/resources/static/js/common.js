function request(url, param, method) {
    $.ajax({
        url: url,
        type: method,
        data: JSON.stringify(param),
        dataType: 'json',
        contentType:"application/json",
        success: function(res) {
            if (res.message) {
                alert(res.message);
            }
            if (res.href) {
                location.href=res.href;
            }
        }
    });
}

function _get(url, param) {
    request(url, param, 'GET');
}

function _post(url, param) {
    request(url, param, 'POST');
}

function _put(url, param) {
    request(url, param, 'PUT');
}

function _delete(url, param) {
    request(url, param, 'DELETE');
}

function toText(s) {
    $('#toText').html(s);
    return $('#toText').text();
}

function copyToClipboard(val) {
    var t = document.createElement("textarea");
    document.body.appendChild(t);
    t.value = val;
    t.select();
    document.execCommand('copy');
    document.body.removeChild(t);
}