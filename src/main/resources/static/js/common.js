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