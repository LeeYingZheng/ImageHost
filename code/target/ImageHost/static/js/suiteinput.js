var counter = 1;
var limit = 5;

$(document).ready(function() {
    if (counter == 1) {
        var ShowRemove = document.getElementById("remove");
        ShowRemove.style.display = 'none';
    }
});

function addSuiteInput(divName, nameList) {
    console.log(divName);
    if (counter == limit) {
        $.growl.error({message: "You have reached the limit of adding " + counter + " scripts"});
    }
    else {
        counter++;
        if (counter == 2) {
            var ShowRemove = document.getElementById("remove");
            ShowRemove.style.display = 'inline-block';
        }

        var newdiv = document.createElement('div');

        newdiv.innerHTML = "<select class = 'hey' id='script' name='script' required='required'>" +
            "<input type='button' id='remove' value='Remove'>";

        document.getElementById(divName).appendChild(newdiv);

        var list = document.getElementById(divName);
        var item = list.lastElementChild;
        var item2 = item.firstElementChild;
        var list2 = nameList.split(',');


        for (i = 0; i < list2.length; i++) {
            var opt = list2[i];
            var el = document.createElement("option");
            el.textContent = opt;
            el.value = opt;
            item2.appendChild(el);
        }




    }


}

$(document).ready(function () {

    $(document).on('click', '#remove', function () {

        $(this).parent().remove();
        counter--;


        if (counter == 1) {
            var ShowRemove = document.getElementById("remove");
            ShowRemove.style.display = 'none';
        }
    });
});








