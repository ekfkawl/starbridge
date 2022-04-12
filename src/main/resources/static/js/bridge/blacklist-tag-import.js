function importTagsByHash() {
    var checkedTags = $(".table input:checkbox:checked").map(function () {
        return $(this).attr("seq");
    }).get();

    if (confirm(checkedTags.length + "개 항목을 임포트합니다")) {
        _post("../bridge/api/battle-tag-import", {
            id: getParameterByName('pull'),
            seq: checkedTags
        })
    }
}