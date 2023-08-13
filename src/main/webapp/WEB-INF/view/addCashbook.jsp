<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addCashbook</title>
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
				<h1>캐시북 추가</h1>
				<form method="post" action="${pageContext.request.contextPath}/on/addCashbook">
					<table  class="table table-bordered">
						<tr>
							<th class="table-dark" width="30%">아이디</th>
							<td>${memberId}</td>
						</tr>
						<tr>
							<th class="table-dark" width="30%">수입/지출</th>
							<td>
								<select name="category" required="required">
									<option value="">===선택하세요===</option>
									<option value="수입">수입</option>
									<option value="지출">지출</option>
								</select>
							</td>
						</tr>
						<tr>
							<th class="table-dark" width="30%">날짜</th>
							<td>
								<input type="date" name="cashbookDate" value="${cashbookDate}" readonly="readonly" style="width:100%">
							</td>
						</tr>
						<tr>
							<th class="table-dark" width="30%">금액</th>
							<td><input type="number" name="price" required="required" style="width:100%"></td>
						</tr>
						<tr>
							<th class="table-dark" width="30%">메모</th>
							<td><textarea name="memo" cols="50" rows="5" style="width:100%"></textarea></td>
						</tr>
					</table>
					<button type="submit" class="btn btn-dark btn-block">추가</button>
				</form>
				</div>
			<div class="col-sm-4">
			</div>
		</div>
	</div>
</body>
</html>