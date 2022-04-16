function importKeywordsByHash() {

    var checkedKeywords = $(".table input:checkbox:checked").map(function () {
        return $(this).attr("seq");
    }).get();

    if (confirm(checkedKeywords.length + "개 항목을 임포트합니다")) {
        _post("../bridge/api/room-filter-import", {
            id: getParameterByName('pull'),
            seq: checkedKeywords
        })
    }
}