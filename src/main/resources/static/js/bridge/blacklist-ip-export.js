$(document).on("change", ".form-check-input", function() {
    var c = $(this).parent().parent();
    var d = c.children().eq(1).text();
    var e = c.children().eq(0).children().is(":checked");

    _put("../bridge/api/hash-export", {
        prev_hash: d,
        id: {
            hash: d
        },
        is_export: e
    })
});