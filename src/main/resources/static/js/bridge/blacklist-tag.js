var curOption = 0;
var prevTag = null;

$(document).on("click", ".edit-button", function() {
    var b = $(this).parent().parent();
    prevTag = b.children().eq(0).text()
});

$(document).on("click", ".save-button", function() {
    var h = $(this).parent().parent();
    var f = h.children().find('input[type="text"]');
    var g = f.eq(0).val();
    var e = f.eq(1).val();

    _post("../bridge/api/battle-tag", {
        prev_tag: prevTag,
        id: {
            tag: g
        },
        memo: e
    });
});

$(document).on("click", ".discard-button", function() {
    prevTag = null
});

$(document).on("click", ".delete-button", function() {
    var c = $(this).parent().parent();
    var d = c.children().eq(0).text();
    _delete("../bridge/api/battle-tag", {
        id: {
            tag: d
        }
    })
});

function getTags() {
    return _get(`../bridge/api/battle-tag?id=${$('#member_id').text()}`);
}

function getTagsByHash() {
    var input = prompt('임포트 대상의 해시를 입력해주세요');
    if (input && MD5_HASH.test(input)) {
        location.href=`/bridge/blacklist-tag-import?pull=${input}`;
    }
}