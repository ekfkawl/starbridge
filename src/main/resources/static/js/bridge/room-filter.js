var curOption = 2;
var prevKeyword = null;

$(document).on("click", ".edit-button", function() {
    var b = $(this).parent().parent();
    prevKeyword = b.children().eq(0).text()
});

$(document).on("click", ".save-button", function() {
    var f = $(this).parent().parent();
    var e = f.children().find('input[type="text"]');
    var d = e.eq(0).val();

    _post("../bridge/api/room-filter", {
        prev_keyword: prevKeyword,
        id: {
            keyword: d
        },
        is_export: true
    });
});

$(document).on("click", ".discard-button", function() {
    prevKeyword = null
});

$(document).on("click", ".delete-button", function() {
    var c = $(this).parent().parent();
    var d = c.children().eq(0).text();

    _delete("../bridge/api/room-filter", {
        id: {
            keyword : d
        }
    })
});

function getKeywords() {
    return _get(`../bridge/api/room-filter?id=${$('#member_id').text()}`);
}

function getKeywordsByHash() {
    var input = prompt('임포트 대상의 해시를 입력해주세요');
    if (input && MD5_HASH.test(input)) {
        location.href=`/bridge/room-filter-import?pull=${input}`;
    }
}