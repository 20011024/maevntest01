<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common/head.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户添加页面</span>
        </div>
        <div class="providerAdd">
            <form id="userForm" name="userForm" method="post" enctype="multipart/form-data" action="">
				<input type="hidden" name="method" value="add">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div>
                    <label for="userCode">用户编码：</label>
                    <input type="text" name="userCode" id="userCode" value=""> 
					<!-- 放置提示信息 -->
					<font color="red"></font>
                </div>
                <div>
                    <label for="userName">用户名称：</label>
                    <input type="text" name="userName" id="userName" value=""> 
					<font color="red"></font>
                </div>
                <div>
                    <label for="userPassword">用户密码：</label>
                    <input type="password" name="userPassword" id="userPassword" value="">
					<font color="red"></font>
                </div>
                <div>
                    <label for="ruserPassword">确认密码：</label>
                    <input type="password" name="ruserPassword" id="ruserPassword" value=""> 
					<font color="red"></font>
                </div>
                <div>
                    <label >用户性别：</label>
					<select name="gender" id="gender">
					    <option value="1" selected="selected">男</option>
					    <option value="2">女</option>
					 </select>
                </div>
                <div>
                    <label for="birthday">出生日期：</label>
                    <input type="text" Class="Wdate" id="birthday" name="birthday" 
					readonly="readonly" onclick="WdatePicker();">
					<font color="red"></font>
                </div>
                <div>
                    <label for="phone">用户电话：</label>
                    <input type="text" name="phone" id="phone" value=""> 
					<font color="red"></font>
                </div>
                <div>
                    <label for="address">用户地址：</label>
                   <input name="address" id="address"  value="">
                </div>
                <div>
                    <label >用户角色：</label>
                    <!-- 列出所有的角色分类 -->
					<select name="userRole" id="userRole">
                        <option>请选择</option>
                        <c:forEach var="role" items="${rList}" varStatus="xh">
                            <option value="${role.id}">${role.roleName}</option>
                        </c:forEach>
                    </select>
	        		<font color="red"></font>
                </div>
                <div>
                    <label> 上传图片 </label>
                    <input id="pic" name="file" type="file" class="opt_input" />
                    <br>
                    <img id="uploadImg" style="width: 300px;height: 200px;margin-left: 30px"/>
                    <font color="red"></font>
                </div>
                <div class="providerAddBtn">
                    <input type="submit" name="add" id="add" value="保存" >
					<input type="button" id="back" name="back" value="返回" >
                </div>
            </form>
        </div>
</div>
</section>
<%@include file="common/foot.jsp" %>
<script>
    //给file元素绑定change事件,实现显示客户端选中的图片
    $("#pic").change(function () {
        //获取file文件对象
        var file = $(this)[0].files[0];
        //等价于document.getElementById("pic").files[0];
        //获取一个指向该元素的地址
        var path = window.URL.createObjectURL(file);
        console.log(path);
        $("#uploadImg").attr('src', path);
    })



    $("#add").click(function (){
        //通过jquery的方法获取表单数据
        //var params = $("#newsAddForm").serialize();//key=value&key2=value2&....

        //创建表单数据，通过键值对来添加一系列表单控件，支持异步上传二进制文件
        var formData = new FormData($("#userForm")[0]);
        $.ajax({
            "url":"addUser",
            "type":"post",
            "data":formData,
            "dataType":"text",
            "contentType": false,
            "processData": false,
            "success":function (result) {
                if(result=="true"){
                    alert("添加成功！");
                    location.href="${pageContext.servletContext.contextPath}/userList";
                }
            },
            "error":function () {
                alert("添加失败！");
            }
        })
    })
    $("#userForm").submit(function (){
        $("#userCode").blur()
        $("#userName").blur()
        $("#userPassword").blur()
        $("#phone").blur()
        var a = true;
        $("#userForm font").each(function (index,item){
            if ($(item).html()!=""){
                a=false;
            }
        })
        return a;
    })

    $("#userCode").blur(function (){
        var userCode = $(this).val();

        if (userCode ==""){
            $("#userCode").next().html("请输入用户编码")
        }else {
            $.ajax({
                "url":"UserCode",
                "type":"post",
                "data":"userCode="+userCode,
                "dateType":"text",
                "success":function (result) {

                    if(result=="false"){
                        $("#userCode").next().html("该用户编码已被使用")
                    }else {
                        $("#userCode").next().html("")
                    }
                }
            })
        }
    })


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
        }else {
            $(this).next().html("")
        }
    })

</script>
