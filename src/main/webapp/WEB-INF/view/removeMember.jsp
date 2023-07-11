<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="cash.vo.*" %>
<%
	Member member = (Member)session.getAttribute("loginMember");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>removeMember</title>
<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>회원탈퇴</h1>
		<form method="post" action="${pageContext.request.contextPath}/on/removeMember">
			<table class="table table-bordered">
				<tr>
					<th class="table-dark">id</th>
					<td><%=member.getMemberId()%></td>
				</tr>
				<tr>
					<th class="table-dark">pw확인</th>
					<td><input type="password" name="memberPw"></td>
				</tr>
			</table>
			<button type="submit" class="btn btn-dark btn-block">회원탈퇴</button>
		</form>
	</div>
</body>
</html>