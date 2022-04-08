function post(url, param) {
    $.ajax({
        url: url,
        type: 'POST',
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