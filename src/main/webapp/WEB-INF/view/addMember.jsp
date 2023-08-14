<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>add Member</title>
<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container">
		<div>
		<br><br><br><br><br><br><br><br><br><br><br><br>
		</div>
		<div class="row">
			<div class="col-sm-4">
			</div>
			<div class="col-sm-4">
				<h1  align="center">회원가입</h1>
				<br><br>
				<form method="post" action="${pageContext.request.contextPath}/off/addMember">
					<table class="table table-bordered">
						<tr>
							<th class="table-dark" width="30%">id</th>
							<td><input type="text" name="memberId" required="required" style="width:100%"></td>
						</tr>
						<tr>
							<th class="table-dark" width="30%">pw</th>
							<td><input type="password" name="memberPw" required="required" style="width:100%"></td>
						</tr>
					</table>
					<div align="center">
						<button type="submit" class="btn btn-dark btn-block">회원가입</button>
					</div>
				</form>
			</div>
			<div class="col-sm-4">
			</div>
		</div>
	</div>
</body>
</html>