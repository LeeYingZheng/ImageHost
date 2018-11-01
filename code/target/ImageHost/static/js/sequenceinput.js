var counter = 1;
var limit = 5;

$(document).ready(function() {
    if (counter == 1) {
        var ShowRemove = document.getElementById("remove");
        ShowRemove.style.display = 'none';
    }
});

function addSequenceInput(divName) {

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
        //newdiv.innerHTML = "<br><input type='text' name='myInputs[]'>";
        var selectHTML = "";
        selectHTML = "<select id='sequence' class='hey' name='sequence' required='required'>";


        selectHTML += "<option>" + "View incidents" + "</option>";
        selectHTML += "<option>" + "View rules" + "</option>";
        selectHTML += "<option>" + "View hash" + "</option>";
        selectHTML += "</select>";

        selectHTML +=  "<input type='button' id='remove' value='Remove'>";

        newdiv.innerHTML = selectHTML;

        document.getElementById(divName).appendChild(newdiv);

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






