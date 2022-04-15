var curOption = 1;
var prevHash = null;

$(document).on("click", ".edit-button", function() {
    var b = $(this).parent().parent();
    prevHash = b.children().eq(0).text()
});

$(document).on("click", ".save-button", function() {
    var h = $(this).parent().parent();
    var f = h.children().find('input[type="text"]');
    var g = f.eq(0).val();
    var e = f.eq(1).val();

    _post("../bridge/api/hash", {
        prev_hash: prevHash,
        id: {
            hash: g
        },
        memo: e,
        is_export: true
    });
});

$(document).on("click", ".discard-button", function() {
    prevHash = null
});

$(document).on("click", ".delete-button", function() {
    var c = $(this).parent().parent();
    var d = c.children().eq(0).text();

    _delete("../bridge/api/hash", {
        id: {
            hash : d
        }
    })
});

function getHashes() {
    return _get(`../bridge/api/hash?id=${$('#member_id').text()}`);
}

function getIpsByHash() {
    var input = prompt('임포트 대상의 해시를 입력해주세요');
    if (input && MD5_HASH.test(input)) {
        location.href=`/bridge/blacklist-ip-import?pull=${input}`;
    }
}