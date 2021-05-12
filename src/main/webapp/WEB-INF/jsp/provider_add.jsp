<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common/head.jsp"%>

<div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>供应商管理页面 >> 供应商添加页面</span>
        </div>
        <div class="providerAdd">
           <form id="providerForm"  name="providerForm" method="post" action="">
			<input type="hidden" name="method" value="add">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div class="">
                    <label for="proCode">供应商编码：</label>
                    <input type="text" name="proCode" id="proCode" value="" >
                    <font color="red"></font>
					<!-- 放置提示信息 -->

                </div>
                <div>
                    <label for="proName">供应商名称：</label>
                   <input type="text" name="proName" id="proName" value="">
                    <font color="red"></font>
                </div>
                <div>
                    <label for="proContact">联系人：</label>
                    <input type="text" name="proContact" id="proContact" value="">

                    <font color="red"></font>

                </div>
                <div>
                    <label for="proPhone">联系电话：</label>
                    <input type="text" name="proPhone" id="proPhone" value="">

                    <font color="red"></font>
                </div>
                <div>
                    <label for="proAddress">联系地址：</label>
                    <input type="text" name="proAddress" id="proAddress" value=""> 
                </div>
                <div>
                    <label for="proFax">传真：</label>
                    <input type="text" name="proFax" id="proFax" value=""> 
                </div>
                <div>
                    <label for="proDesc">描述：</label>
                    <input type="text" name="proDesc" id="proDesc" value=""> 
                </div>
                <div class="providerAddBtn">
                    <input type="submit" name="add" id="add" value="保存">
					<input type="button" id="back" name="back" value="返回" >
                </div>
            </form>
     </div>
</div>
</section>
<%@include file="common/foot.jsp" %>


<script>
    $("#add").click(function (){
        var serialize = $("#providerForm").serialize();
        $("#providerForm font").each(function (index,item){
            if ($(item).html()==""){

            }else {
                $.ajax({
                    "url":"addProvider",
                    "type":"post",
                    "data":serialize,
                    "dataType":"text",
                    "success":function (result) {
                        if(result=="true"){
                            alert("添加成功！");
                            location.href="${pageContext.servletContext.contextPath}/providerList";
                        }
                    },
                    "error":function () {
                        alert("添加失败！");
                    }
                })
            }
        })

    })
    $("#providerForm").submit(function (){
        $("#proCode").blur()
        $("#proName").blur()
        $("#proContact").blur()
        $("#proPhone").blur()
        var a = true;
        $("#providerForm font").each(function (index,item){
            if ($(item).html()!=""){
                a=false;
            }
        })
        return a;
    })
    $("#proCode").blur(function (){
        var proCode = $(this).val();
        console.log(proCode)
        $.ajax({
            "url":"jiaoyan1",
            "type":"post",
            "data":"proCode="+proCode,
            "dateType":"text",
            "success":function (result) {
                console.log(result)
                if(result=="false"){
                    $("#proCode").next().html("该供应商编码已被使用")
                }

            }
        })
    })

    $("#proName").blur(function (){
        var proName = $(this).val();

        if (proName==""){
            $(this).next().html("请输入供应商名称")
        }else {
            $(this).next().html("")
        }
    })
    $("#proContact").blur(function (){
        var proContact = $(this).val();
        if (proContact==""){
            $(this).next().html("请输入负责人名称")
        }else {
            $(this).next().html("")
        }
    })
    $("#proPhone").blur(function (){
        var proPhone = $(this).val();
        var patrn=/^(13[0-9]|15[0-9]|18[0-9])\d{8}$/;
        if (!(patrn.test(proPhone))){
            $(this).next().html("请输入正确的电话号码")
        }
    })

</script>





