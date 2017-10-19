/**
 * Created by chubin on 2017/2/12.
 */

$(document).ready(function(){
    window.location.href("home.jsp")
});

/*
function yes() {
    var body = "";
    var users = $('#users');
    var name = $('#name').val();
    var age = $('#age').val();
    $.ajax({
        type: 'post',
        url: 'users',
        dataType: 'json',
        data:{
            name: name,
            age: age
        },
        success: function(data){
            $.each(data, function(i, user){
                var oldStr = "<p>" + user.id + ", " + user.name + ", " + user.age + "</p>";
                body += oldStr;
            });
            //alert(body);
            users.html(body);
        }
    });
}*/
