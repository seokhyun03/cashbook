<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>로그인</h1>
		<form method="post" action="${pageContext.request.contextPath}/off/login">
			<table class="table table-bordered">
				<tr>
					<th class="table-dark">id</th>
					<td><input type="text" name="memberId"></td>
				</tr>
				<tr>
					<th class="table-dark" >pw</th>
					<td><input type="password" name="memberPw"></td>
				</tr>
			</table>
			<button type="submit" class="btn btn-dark btn-block">로그인</button>
			<a href="${pageContext.request.contextPath}/off/addMember" class="btn btn-dark btn-block">회원가입</a>
		</form>
	</div>
</body>
</html>