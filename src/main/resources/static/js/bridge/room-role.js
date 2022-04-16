var curOption = 3;

$("#sum").change(function() {
    $("#sum-n").text($(this).val());
    update()
});
$("#chk-sum").change(function() {
    update()
});
$("#win").change(function() {
    $("#win-n").text($(this).val());
    update()
});
$("#chk-win").change(function() {
    update()
});
$("#dis").change(function() {
    $("#dis-n").text($(this).val());
    update()
});
$("#chk-dis").change(function() {
    update()
});
function update() {
    let chk_sum = $('input:checkbox[id="chk-sum"]').is(":checked");
    let v_sum = $("#sum").val();
    let chk_win = $('input:checkbox[id="chk-win"]').is(":checked");
    let v_win = $("#win").val();
    let chk_dis = $('input:checkbox[id="chk-dis"]').is(":checked");
    let v_dis = $("#dis").val();

    _post("../bridge/api/room-role", {
        enable_history_count: chk_sum,
        history_count: v_sum,
        enable_rate: chk_win,
        rate: v_win,
        enable_dis: chk_dis,
        dis: v_dis
    });
}