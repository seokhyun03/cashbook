<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="cash.vo.*" %>
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
		<div>
		<br><br><br><br><br><br><br><br><br><br>
		</div>
		<div class="row">
			<div class="col-sm-4">
			</div>
			<div class="col-sm-4">
				<h1>회원정보</h1>
				<table class="table table-bordered">
					<tr>
						<th class="table-dark" width="30%">id</th>
						<td>${member.getMemberId()}</td>
					</tr>
					<tr>
						<th class="table-dark" width="30%">pw</th>
						<td>*****</td>
					</tr>
					<tr>
						<th class="table-dark" width="30%">updatedate</th>
						<td>${member.getUpdatedate()}</td>
					</tr>
					<tr>
						<th class="table-dark" width="30%">createdate</th>
						<td>${member.getCreatedate()}</td>
					</tr>
				</table>
				<a href="${pageContext.request.contextPath}/on/modifyMember" class="btn btn-dark btn-block">회원정보수정</a>
				<a href="${pageContext.request.contextPath}/on/removeMember" class="btn btn-dark btn-block">회원탈퇴</a>
			</div>
			<div class="col-sm-4">
			</div>
		</div>
	</div>
</body>
</html>