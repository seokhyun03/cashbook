<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="cash.vo.*" %>
<%
	Member member = (Member)request.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberOne</title>
<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>회원정보</h1>
			<table class="table table-bordered">
				<tr>
					<th class="table-dark">id</th>
					<td><%=member.getMemberId()%></td>
				</tr>
				<tr>
					<th class="table-dark">pw</th>
					<td>*****</td>
				</tr>
				<tr>
					<th class="table-dark">updatedate</th>
					<td><%=member.getUpdatedate()%></td>
				</tr>
				<tr>
					<th class="table-dark">createdate</th>
					<td><%=member.getCreatedate()%></td>
				</tr>
			</table>
		<a href="${pageContext.request.contextPath}/on/modifyMember" class="btn btn-dark btn-block">회원정보수정</a>
		<a href="${pageContext.request.contextPath}/on/removeMember" class="btn btn-dark btn-block">회원탈퇴</a>
	</div>
</body>
</html>