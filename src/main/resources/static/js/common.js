function request(url, param, method) {
    var result;
    $.ajax({
        url: url,
        type: method,
        data: JSON.stringify(param),
        dataType: 'json',
        async: false,
        contentType:"application/json",
        success: function(res) {
            if (res.message) {
                alert(res.message);
            }
            if (res.href) {
                location.href=res.href;
            }
            result = res;
        }
    });
    return result;
}

function _get(url, param) {
    return request(url, param, 'GET');
}

function _post(url, param) {
    return request(url, param, 'POST');
}

function _put(url, param) {
    return request(url, param, 'PUT');
}

function _delete(url, param) {
    return request(url, param, 'DELETE');
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