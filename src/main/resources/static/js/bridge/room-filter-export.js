$(document).on("change", ".form-check-input", function() {
    var c = $(this).parent().parent();
    var d = c.children().eq(1).text();
    var e = c.children().eq(0).children().is(":checked");

    _put("../bridge/api/room-filter-export", {
        prev_keyword: d,
        id: {
            keyword: d
        },
        is_export: e
    })
});