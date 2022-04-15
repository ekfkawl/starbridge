function importIpsByHash() {

    var checkedIps = $(".table input:checkbox:checked").map(function () {
        return $(this).attr("seq");
    }).get();

    if (confirm(checkedIps.length + "개 항목을 임포트합니다")) {
        _post("../bridge/api/hash-import", {
            id: getParameterByName('pull'),
            seq: checkedIps
        })
    }
}