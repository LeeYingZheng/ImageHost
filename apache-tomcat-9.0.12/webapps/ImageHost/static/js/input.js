var counter = 1;
var limit = 3;
var isSubmitting = false;

$(document).ready(function() {
    if (counter == 1) {
        var ShowRemove = document.getElementById("remove");
        ShowRemove.style.display = 'none';
    }
});

function addInput(divName,value) {

    if (counter == limit) {
        $.growl.error({message: "You have reached the limit of adding " + counter + " emails"});
    }
    else {
        counter++;
        if (counter == 2) {
            var ShowRemove = document.getElementById("remove");
            ShowRemove.style.display = 'inline-block';
        }

        var list = document.getElementById("emailDiv");
        var temp = list.lastElementChild;

        var newdiv = document.createElement('div');

        newdiv.innerHTML = "<input type='email' id ='email' name='email' required='required' class='hey'/>" +
            "<input type='button' id='remove' value='Remove'/>";

        document.getElementById(divName).appendChild(newdiv);

        if(value != null)
        {
            var item = list.lastElementChild;
            item.firstElementChild.value  = value;

        }
    }


}
$(document).ready(function() {

    $(document).on('click', '#remove', function () {

        $(this).parent().remove();
        counter--;

        if (counter == 1) {
            var ShowRemove = document.getElementById("remove");
            ShowRemove.style.display = 'none';
        }
    });

});

function emailSeparate(emailList) {

    var email = emailList.split(',');

    if (email.length > 1) {
        document.getElementById("email").value = email[0];
        for (i = 1; i <= email.length -1; i++) {
            addInput("emailDiv",email[i]);
        }

    }

}

function testBrowser() {

    if(isSubmitting) {
        return;
    }
    var x = document.getElementById("browser").value;
    var data = {
        browser: x
    };
    isSubmitting = true;

    $.growl({ title: "Test Case", message:" have started running" });

    $.ajax({
        type: "POST",
        url: "/Selenium/testbrowser",
        data: data,

        timeout: 100000,

        success: function (data) {
            isSubmitting = false;
            $.growl.notice({ message: " have run successfully" });
        },
        error: function (e) {
            isSubmitting = false;
            $.growl.error({ message: " error: " + e });
        },
        done: function (e) {
            console.log("DONE");
        }
    });
    return false;

}



