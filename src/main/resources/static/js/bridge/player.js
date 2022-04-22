var curOption = 4;

$("#chk-ping").change(function() {
    update()
});
$("#chk-disprotect").change(function() {
    update()
});
$("#chk-voting").change(function() {
    update()
});
$("#chk-turncolor").change(function() {
    update()
});
$("#chk-owner").change(function() {
    update()
});
function update() {
    let chk_ping = $('input:checkbox[id="chk-ping"]')
        .is(":checked");
    let chk_disprotect = $('input:checkbox[id="chk-disprotect"]')
        .is(":checked");
    let chk_voting = $('input:checkbox[id="chk-voting"]').is(
        ":checked");
    let chk_turncolor = $('input:checkbox[id="chk-turncolor"]').is(
        ":checked");
    let chk_owner = $('input:checkbox[id="chk-owner"]').is(
        ":checked");

    _post("../bridge/api/player", {
        enable_ping_color: chk_ping,
        enable_dis_protect: chk_disprotect,
        enable_voting: chk_voting,
        enable_turn_color: chk_turncolor,
        enable_owner: chk_owner
    });
};