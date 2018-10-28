function expandCollapse(showHide) {

    var hideShowDiv = document.getElementById(showHide);
    var label = document.getElementById("expand");

    if (hideShowDiv.style.display == 'none') {
        label.innerHTML = label.innerHTML.replace("[+]", "[-]");
        hideShowDiv.style.display = 'block';
    } else {
        label.innerHTML = label.innerHTML.replace("[-]", "[+]");
        hideShowDiv.style.display = 'none';

    }
}