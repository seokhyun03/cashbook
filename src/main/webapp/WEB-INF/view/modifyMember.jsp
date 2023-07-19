<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modifyMember</title>
<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>비밀번호 변경</h1>
		<form method="post" action="${pageContext.request.contextPath}/on/removeMember">
			<table class="table table-bordered">
				<tr>
					<th class="table-dark">id</th>
					<td>${member.getMemberId()}</td>
				</tr>
				<tr>
					<th class="table-dark">현재pw</th>
					<td><input type="password" name="memberPw"></td>
				</tr>
				<tr>
					<th class="table-dark">새pw</th>
					<td><input type="password" name="memberNewPw"></td>
				</tr>
				<tr>
					<th class="table-dark">pw확인</th>
					<td><input type="password" name="memberNewPwCk"></td>
				</tr>
			</table>
			<button type="submit" class="btn btn-dark btn-block">비밀번호 변경</button>
		</form>
	</div>
</body>
</html>