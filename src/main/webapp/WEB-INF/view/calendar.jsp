<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%><!-- subString 호출 -->
<!-- JSP컴파일시 자바코드로 변환되는 c:...(제어문법 코드) 커스텀태그 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>calendar</title>
<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<!-- 변수값or반환값 : EL 사용 $표현식 -->
	<!-- 
		속성값 대신 EL 사용 
		request.getAttribute("targetYear") -- requestScope.targetYear (requestScope 생략가능) 
		형변환연산이 필요없다(EL이 자동으로 처리
	 -->
	<!-- 자바코드(제어문) : JSTL 사용 -->
	<div class="container">
		<h1>${targetYear}년 ${targetMonth+1}월</h1>
		<a href="${pageContext.request.contextPath}/calendar?targetYear=${targetYear}&targetMonth=${targetMonth-1}">이전</a>
		<a href="${pageContext.request.contextPath}/calendar?targetYear=${targetYear}&targetMonth=${targetMonth+1}">다음</a>
		<h2>이달의 해시태그</h2>
		<c:forEach var="m" items="${hashtagList}">
			<a href="${pageContext.request.contextPath}/hashtag?targetYear=${targetYear}&targetMonth=${targetMonth}&word=${m.word}">${m.word}(${m.cnt})</a>
		</c:forEach>
		<table class="table table-bordered" style="table-layout: fixed;">
			<tr>
				<th class="table-dark">일</th>
				<th class="table-dark">월</th>
				<th class="table-dark">화</th>
				<th class="table-dark">수</th>
				<th class="table-dark">목</th>
				<th class="table-dark">금</th>
				<th class="table-dark">토</th>
			</tr>
			<tr>
				<c:forEach var="i" begin="0" end="${totalCell - 1}" step="1">
					<c:set var="d" value="${i-beginBlank+1}"></c:set>
					<c:if test="${i != 0 && i % 7 == 0}">
						</tr><tr>
					</c:if>
					<c:if test="${d < 1 || d > lastDate}">
						<c:if test="${d < 1}">
							<td class="text-muted table-light" style="height: 120px">${preMonthLastDate + d}</td>
						</c:if>
						<c:if test="${d > lastDate}">
							<td class="text-muted table-light" style="height: 120px">${d-lastDate}</td>
						</c:if>
					</c:if>
					<c:if test="${!(d < 1 || d > lastDate)}">
						<c:if test="${todayYear == targetYear && todayMonth == targetMonth && todayDate == d}">
							<td class="table-warning" style="height: 120px">
								<c:if test="${i % 7 == 0}">
									<div>
										<a class="text-danger" style="text-decoration: none;" href="${pageContext.request.contextPath}/cashbook?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${d}">
											${d}
										</a>	
									</div>
									<c:forEach var="c" items="${list}">
										<c:if test="${d == fn:substring(c.cashbookDate,8,10)}">
											<div>
												<c:if test="${c.category == '수입'}">
													<span>+${c.price}</span>
												</c:if>
												<c:if test="${c.category == '지출'}">
													<span style="color: red;">-${c.price}</span>
												</c:if>
											</div>
										</c:if>
									</c:forEach>
								</c:if>
								<c:if test="${i % 7 == 6}">
									<div>
										<a class="text-primary" style="text-decoration: none;" href="${pageContext.request.contextPath}/cashbook?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${d}">
											${d}
										</a>	
									</div>
									<c:forEach var="c" items="${list}">
										<c:if test="${d == fn:substring(c.cashbookDate,8,10)}">
											<div>
												<c:if test="${c.category == '수입'}">
													<span>+${c.price}</span>
												</c:if>
												<c:if test="${c.category == '지출'}">
													<span style="color: red;">-${c.price}</span>
												</c:if>
											</div>
										</c:if>
									</c:forEach>
								</c:if>
								<c:if test="${!(i % 7 == 0) && !(i % 7 == 6)}">
									<div>
										<a class="text-body" style="text-decoration: none;" href="${pageContext.request.contextPath}/cashbook?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${d}">
											${d}
										</a>	
									</div>
									<c:forEach var="c" items="${list}">
										<c:if test="${d == fn:substring(c.cashbookDate,8,10)}">
											<div>
												<c:if test="${c.category == '수입'}">
													<span>+${c.price}</span>
												</c:if>
												<c:if test="${c.category == '지출'}">
													<span style="color: red;">-${c.price}</span>
												</c:if>
											</div>
										</c:if>
									</c:forEach>
								</c:if>
							</td>
						</c:if>
						<c:if test="${!(todayYear == targetYear && todayMonth == targetMonth && todayDate == d)}">
							<td style="height: 120px">
								<c:if test="${i % 7 == 0}">
									<div>
										<a class="text-danger" style="text-decoration: none;" href="${pageContext.request.contextPath}/cashbook?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${d}">
											${d}
										</a>	
									</div>
									<c:forEach var="c" items="${list}">
										<c:if test="${d == fn:substring(c.cashbookDate,8,10)}">
											<div>
												<c:if test="${c.category == '수입'}">
													<span>+${c.price}</span>
												</c:if>
												<c:if test="${c.category == '지출'}">
													<span style="color: red;">-${c.price}</span>
												</c:if>
											</div>
										</c:if>
									</c:forEach>
								</c:if>
								<c:if test="${i % 7 == 6}">
									<div>
										<a class="text-primary" style="text-decoration: none;" href="${pageContext.request.contextPath}/cashbook?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${d}">
											${d}
										</a>	
									</div>
									<c:forEach var="c" items="${list}">
										<c:if test="${d == fn:substring(c.cashbookDate,8,10)}">
											<div>
												<c:if test="${c.category == '수입'}">
													<span>+${c.price}</span>
												</c:if>
												<c:if test="${c.category == '지출'}">
													<span style="color: red;">-${c.price}</span>
												</c:if>
											</div>
										</c:if>
									</c:forEach>
								</c:if>
								<c:if test="${!(i % 7 == 0) && !(i % 7 == 6)}">
									<div>
										<a class="text-body" style="text-decoration: none;" href="${pageContext.request.contextPath}/cashbook?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${d}">
											${d}
										</a>	
									</div>
									<c:forEach var="c" items="${list}">
										<c:if test="${d == fn:substring(c.cashbookDate,8,10)}">
											<div>
												<c:if test="${c.category == '수입'}">
													<span>+${c.price}</span>
												</c:if>
												<c:if test="${c.category == '지출'}">
													<span style="color: red;">-${c.price}</span>
												</c:if>
											</div>
										</c:if>
									</c:forEach>
								</c:if>
							</td>
						</c:if>
					</c:if>
				</c:forEach>
			</tr>	
		</table>
	</div>
</body>
</html>