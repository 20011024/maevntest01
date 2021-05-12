<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="common/head.jsp"%>

        <div class="right">
            <div class="location">
                <strong>你现在所在的位置是:</strong>
                <span>用户管理页面</span>
            </div>
            <div class="search">
           		<form method="post" action="${pageContext.request.contextPath }/userList">
					<input name="method" value="query" class="input-text" type="hidden">

					 <span>用户名：</span>
					 <input name="userName" class="input-text"	type="text" value="${queryUserName }">
					 
					 <span>用户角色：</span>
					 <select name="userRole">
						 <option value="">请选择</option>
						 <c:forEach var="role" items="${rList}" varStatus="xh">
							 <option value="${xh.count}">${role.roleName}</option>
						 </c:forEach>
	        		</select>
					 
					 <input type="hidden" name="pageIndex" value="1"/>
					 <input	value="查 询" type="submit" id="searchbutton">
					 <a href="${pageContext.request.contextPath}/toUserAdd" >添加用户</a>
				</form>
            </div>
            <!--用户-->
            <table class="providerTable" cellpadding="0" cellspacing="0">
                <tr class="firstTr">

                    <th width="10%">用户编码</th>
                    <th width="20%">用户名称</th>
                    <th width="10%">性别</th>
                    <th width="10%">年龄</th>
                    <th width="10%">电话</th>
                    <th width="10%">用户角色</th>
                    <th width="30%">操作</th>
                </tr>
                   <c:forEach var="user" items="${uList}" varStatus="status">
					<tr>

						<td>
						<span>${user.userCode }</span>
						</td>
						<td>
						<span>${user.userName }</span>
						</td>
						<td>
							<span>
								<c:if test="${user.gender==1}">男</c:if>
								<c:if test="${user.gender==2}">女</c:if>
							</span>
						</td>
						<td>
<%--						<span>${user.age}</span>--%>
						</td>
						<td>
						<span>${user.phone}</span>
						</td>
						<td>
							<span>${user.role.roleName}</span>
						</td>
						<td>
						<span><a class="viewUser" href="getUserById?id=${user.id}"><img src="${pageContext.request.contextPath }/images/read.png" alt="查看" title="查看"/></a></span>
						<span><a class="modifyUser" href="toUserUpdate?id=${user.id}"><img src="${pageContext.request.contextPath }/images/xiugai.png" alt="修改" title="修改"/></a></span>
						<span><a  onclick="shan(${user.id})" class="deleteUser" href="#" ><img src="${pageContext.request.contextPath }/images/schu.png" alt="删除" title="删除"/></a></span>
						</td>
					</tr>
				</c:forEach>
			</table>
			<input type="hidden" id="totalPageCount" value="${totalPageCount}"/>
			<div class="page-bar">
				<ul class="page-num-ul clearfix">
					<li>共${page.totalCount}条记录&nbsp;&nbsp; ${page.currPageNo }/${page.totalPageCount}页</li>
					<a href="userList?pageNo=1">首页</a>
					<a href="userList?pageNo=${page.currPageNo-1}">上一页</a>
					<a href="userList?pageNo=${page.currPageNo+1}">下一页</a>
					<a href="userList?pageNo=${page.totalPageCount}">最后一页</a>
				</ul>
				<form action="userList" method="post" id="tiaozhuan">
					跳转至
	     			<input type="text" name="pageNo" id="inputPage"/>页
					<span></span>
	    			 <input type="submit" value="GO"/>
				</form>

			</div>
		</div>
    </section>




<%@include file="common/foot.jsp" %>
<script>

	function shan(id) {
		var flag = confirm("是否删除该用户");
		if (flag){
			$.ajax({
				"url":"deleteUser",
				"type":"post",
				"data":"id="+id,
				"dateType":"text",
				"success":function (result) {
					console.log(result)
					if(result=="true"){
						alert("删除成功");
						location.href="${pageContext.servletContext.contextPath}/userList";
					}else{
						alert("对不起，删除用户失败");
						location.href="${pageContext.servletContext.contextPath}/userList";
					}
				}
			})
		}

	}


</script>