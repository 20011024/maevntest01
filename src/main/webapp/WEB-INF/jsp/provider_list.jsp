<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="common/head.jsp"%>

<div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>供应商管理页面</span>
        </div>
        <div class="search">
        	<form method="get" action="${pageContext.request.contextPath }/getLike">
				<input name="method" value="query" type="hidden">
				<span>供应商编码：</span>
				<input name="proCode" type="text"/>
				
				<span>供应商名称：</span>
				<input name="proName" type="text"/>
				
				<input value="查 询" type="submit" id="searchbutton">
				<a href="${pageContext.servletContext.contextPath}/toProAdd">添加供应商</a>
			</form>

        </div>
        <!--供应商操作表格-->
        <table class="providerTable" cellpadding="0" cellspacing="0">
            <tr class="firstTr">
                <th width="10%">供应商编码</th>
                <th width="20%">供应商名称</th>
                <th width="10%">联系人</th>
                <th width="10%">联系电话</th>
                <th width="10%">传真</th>
                <th width="10%">创建时间</th>
                <th width="30%">操作</th>
            </tr>
            <c:forEach var="provider" items="${plist}" varStatus="status">
				<tr>
					<td>
					<span>${provider.proCode }</span>
					</td>
					<td>
					<span>${provider.proName }</span>
					</td>
					<td>
					<span>${provider.proContact}</span>
					</td>
					<td>
					<span>${provider.proPhone}</span>
					</td>
					<td>
					<span>${provider.proFax}</span>
					</td>
					<td>
					<span>
					<fmt:formatDate value="${provider.creationDate}" pattern="yyyy-MM-dd"/>
					</span>
					</td>
					<td>
					<span><a class="viewProvider" href="${pageContext.servletContext.contextPath}/getProById?id=${provider.id}"><img src="${pageContext.request.contextPath}/images/read.png" alt="查看" title="查看"/></a></span>
					<span><a class="modifyProvider" href="${pageContext.servletContext.contextPath}/toProUpdate?id=${provider.id}"><img src="${pageContext.request.contextPath }/images/xiugai.png" alt="修改" title="修改"/></a></span>
					<span><a onclick="shan(${provider.id})" class="deleteProvider" href="#"><img src="${pageContext.request.contextPath }/images/schu.png" alt="删除" title="删除"/></a></span>
					</td>
				</tr>
			</c:forEach>
        </table>

    </div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeProv">
   <div class="removerChid">
       <h2>提示</h2>
       <div class="removeMain" >
           <p>你确定要删除该供应商吗？</p>
           <a href="#" id="yes">确定</a>
           <a href="#" id="no">取消</a>
       </div>
   </div>
</div>

<%@include file="common/foot.jsp" %>
<script >
	function shan(id) {
		var flag = confirm("是否删除该供应商");
		if (flag){
			$.ajax({
				"url":"deleteProvider",
				"type":"post",
				"data":"id="+id,
				"dateType":"text",
				"success":function (result) {

					if(result=="true"){
						alert("删除成功");
						location.href="${pageContext.servletContext.contextPath}/providerList";
					}else{
						alert("对不起，删除用户失败");
						location.href="${pageContext.servletContext.contextPath}/providerList";
					}
				}
			})
		}
	}
</script>