<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cashbook</title>
<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>${targetYear}년 ${targetMonth+1}월 ${targetDate}일</h1>
		<a href="${pageContext.request.contextPath}/calendar" class="btn btn-dark btn-block">이전으로</a>
		<a href="${pageContext.request.contextPath}/addCashbook?cashbookDate=${cashbookDate}" class="btn btn-dark btn-block">추가</a>
		<a href="${pageContext.request.contextPath}/logout" class="btn btn-dark btn-block">로그아웃</a>
		<a href="${pageContext.request.contextPath}/memberOne" class="btn btn-dark btn-block">회원정보</a>
		<table class="table table-bordered">
			<tr>
				<th class="table-dark">수입/지출</th>
				<th class="table-dark">금액</th>
				<th class="table-dark">메모</th>
				<th class="table-dark">작성날짜</th>
			</tr>
			<c:forEach var="c" items="${list}">
				<tr>
					<td>${c.category}</td>
					<td>${c.price}</td>
					<td>${c.memo}</td>
					<td>${c.createdate}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>