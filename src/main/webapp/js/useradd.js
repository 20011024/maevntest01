
$("#userName").blur(function (){
    var userName = $(this).val();

    if (userName==""){
        $(this).next().html("请输入用户名称")
    }else {
        $(this).next().html("")
    }
})
$("#userPassword").blur(function (){
    var userPassword = $(this).val();
    if (userPassword==""){
        $(this).next().html("请输入密码")
    }else {
        $(this).next().html("")
    }
})
$("#ruserPassword").blur(function (){
    var userPassword = $("#userPassword").val();
    var ruserPassword = $(this).val();
    if (ruserPassword==""){
        $(this).next().html("请输入密码")
    }else if(userPassword != ruserPassword){
        $(this).next().html("两次密码不一致")
    } else {
        $(this).next().html("")
    }
})
$("#phone").blur(function (){
    var phone = $(this).val();
    var patrn=/^(13[0-9]|15[0-9]|18[0-9])\d{8}$/;
    if (!(patrn.test(phone))){
        $(this).next().html("请输入正确的电话号码")
    }
})