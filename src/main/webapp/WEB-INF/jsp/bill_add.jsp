<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common/head.jsp"%>

<div class="right">
     <div class="location">
         <strong>你现在所在的位置是:</strong>
         <span>订单管理页面 >> 订单添加页面</span>
     </div>
     <div class="providerAdd">
         <form id="billForm" name="billForm" method="post" action="${pageContext.request.contextPath }/jsp/bill.do">
             <!--div的class 为error是验证错误，ok是验证成功-->
             <input type="hidden" name="method" value="add">
             <div class="">
                 <label for="billCode">订单编码：</label>
                 <input type="text" name="billCode"  class="text" id="billCode" value="">
				 <!-- 放置提示信息 -->
				 <font color="red"></font>
             </div>
             <div>
                 <label for="productName">商品名称：</label>
                 <input type="text" name="productName" id="productName" value=""> 
				 <font color="red"></font>
             </div>
             <div>
                 <label for="productUnit">商品单位：</label>
                 <input type="text" name="productUnit" id="productUnit" value=""> 
				 <font color="red"></font>
             </div>
             <div>
                 <label for="productCount">商品数量：</label>
                 <input type="text" name="productCount" id="productCount" value=""> 
				 <font color="red"></font>
             </div>
             <div>
                 <label for="totalPrice">总金额：</label>
                 <input type="text" name="totalPrice" id="totalPrice" value=""> 
				 <font color="red"></font>
             </div>
             <div>
                 <label >供应商：</label>
                 <select name="providerId" id="providerId">
                     <option>请选择</option>
                     <c:forEach var="provider" items="${pList}" varStatus="xh">
                         <option value="${provider.id}">${provider.proName}</option>
                     </c:forEach>
		         </select>
				 <font color="red"></font>
             </div>
             <div>
                 <label >是否付款：</label>
                 <input type="radio" name="isPayment" value="1" checked="checked">未付款
				 <input type="radio" name="isPayment" value="2" >已付款
             </div>
             <div class="providerAddBtn">
                  <input type="button" name="add" id="add" value="保存">
				  <input type="button" id="back" name="back" value="返回" >
             </div>
         </form>
     </div>
 </div>
</section>
<%@include file="common/foot.jsp" %>
<script type="text/javascript" >
    $("#add").click(function (){
        var serialize = $("#billForm").serialize();
        $.ajax({
            "url":"addBill",
            "type":"post",
            "data":serialize,
            "dataType":"text",
            "success":function (result) {
                if(result=="true"){
                    alert("添加成功！");
                    location.href="${pageContext.servletContext.contextPath}/billList";
                }
            },
            "error":function () {
                alert("添加失败！");
            }
        })
    })


    $("#billCode").blur(function (){
        var billCode = $(this).val();

        if (billCode ==""){
            $("#billCode").next().html("请输入用户编码")
        }else {
            $.ajax({
                "url":"billCode",
                "type":"post",
                "data":"billCode="+billCode,
                "dateType":"text",
                "success":function (result) {

                    if(result=="false"){
                        $("#billCode").next().html("该订单编码已被使用")
                    }else {
                        $("#billCode").next().html("")
                    }
                }
            })
        }
    })
</script>