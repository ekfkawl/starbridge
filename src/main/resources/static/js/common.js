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
        },
        error: function(request) {
            var res = JSON.parse(request.responseText);
            if (res.message) {
                var t = res.message.indexOf('*href=');
                if (t != -1) {
                    if (t != 0) {
                        alert(res.message.split('*href=')[0]);
                    }
                    var href = res.message.slice(t + 6);
                    location.href = href;
                }else {
                    alert(res.message);
                }
            }
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

function getParameterByName(name, url = window.location.href) {
    name = name.replace(/[\[\]]/g, '\\$&');
    var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, ' '));
}

var MD5_HASH = /^[A-Za-z0-9]{32,32}$/;