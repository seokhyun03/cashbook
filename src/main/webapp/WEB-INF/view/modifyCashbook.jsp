<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modifyCashbook</title>
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
				<h1>캐시북 수정</h1>
				<form method="post" action="${pageContext.request.contextPath}/on/modifyCashbook">
					<table class="table table-bordered">
						<tr>
							<th class="table-dark" width="30%">아이디</th>
							<td>${memberId}</td>
						</tr>
						<tr>
							<th class="table-dark" width="30%">수입/지출</th>
							<td>
								<select name="category" required="required">
									<c:if test="${cashbook.category == '수입'}">
										<option>===선택하세요===</option>
										<option value="수입" selected="selected">수입</option>
										<option value="지출">지출</option>
									</c:if>
									<c:if test="${cashbook.category == '지출'}">
										<option>===선택하세요===</option>
										<option value="수입">수입</option>
										<option value="지출" selected="selected">지출</option>
									</c:if>
								</select>
							</td>
						</tr>
						<tr>
							<th class="table-dark" width="30%">날짜</th>
							<td>
								<input type="date" name="cashbookDate" value="${cashbook.cashbookDate}" readonly="readonly" style="width:100%">
							</td>
						</tr>
						<tr>
							<th class="table-dark" width="30%">금액</th>
							<td><input type="number" name="price" value="${cashbook.price}" required="required" style="width:100%"></td>
						</tr>
						<tr>
							<th class="table-dark" width="30%">메모</th>
							<td><textarea name="memo" cols="50" rows="5" style="width:100%">${cashbook.memo}</textarea></td>
						</tr>
					</table>
					<button type="submit"  class="btn btn-dark btn-block">수정</button>
				</form>
				</div>
			<div class="col-sm-4">
			</div>
		</div>
	</div>
</body>
</html>