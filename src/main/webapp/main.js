function textFixer(athis, a, b) {
    athis.value = athis.value.replace(/\,/g, ".").replace(/[^\d\.\-]/g, "");
    while (athis.value.lastIndexOf('-') > 0) {
        athis.value = athis.value.substring(0, athis.value.lastIndexOf('-'));
    }
    let x = parseFloat(athis.value);
    while (x > b || x < a) {
        athis.value = athis.value.substring(0, athis.value.length - 1);
        x = parseFloat(athis.value);
    }
    while ((athis.value[0] === '-' && (athis.value[1] === '.' || athis.value.lastIndexOf('.') > 2)) || (athis.value[0] !== '-' && (athis.value[0] === '.' || athis.value.lastIndexOf('.') > 1))) {
        athis.value = athis.value.substring(0, athis.value.lastIndexOf('.'));
    }
}

document.querySelector("#input-form\\:y-textinput").onkeyup = function () { textFixer(this, -3, 5); }

function grofikClick(event) {
    const rect = document.getElementById("the_image").getBoundingClientRect();
    const mx = event.offsetX;
    const my = event.offsetY;
    const iw = rect.width;
    const ih = rect.height;
    const x = (10 * mx / iw - 5)/4;
    const y = (5 - 10 * my / ih)/4;
    const grofikX = document.getElementById("input-form:grofikX");
    const grofikY = document.getElementById("input-form:grofikY");
    grofikX.setAttribute("value", x.toString());
    grofikY.setAttribute("value", y.toString());
}