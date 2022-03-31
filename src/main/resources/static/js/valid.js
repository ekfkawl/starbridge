function removeChar(a) {
    a = a || window.event;
    var b = (a.which) ? a.which : a.keyCode;
    if (b == 8 || b == 46 || b == 37 || b == 39) {
        return
    } else {
        a.target.value = a.target.value.replace(/[ㄱ-ㅎㅏ-ㅡ가-핳#$%^&*()_+|<>?:{} ]/g, "")
    }
}

function removeCharForKrEn(a) {
    a = a || window.event;
    var b = (a.which) ? a.which : a.keyCode;
    if (b == 8 || b == 46 || b == 37 || b == 39) {
        return
    } else {
        a.target.value = a.target.value.replace(/[#$%^&*()_+|<>?:{} ]/g, "")
    }
}