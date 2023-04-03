<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:set var="root" value="${pageContext.request.contextPath}" />
<!-- 상단 navbar start -->
<nav class="navbar navbar-expand-lg navbar-light  fixed-top">
	<div class="container">
		<a class="navbar-brand  fw-bold" href="${root}/index.jsp"> <img
			src="./assets/img/ssafy_logo.png" alt="" width="60" /> ENJOYTRIP
		</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-lg-0">
				<li class="nav-item"><a class="nav-link" aria-current="page"
					href="map.jsp">지역별 관광지</a></li>
				<li class="nav-item"><a class="nav-link" aria-current="page"
					href="plan.jsp">나의 여행계획</a></li>
				<li class="nav-item"><a class="nav-link" aria-current="page"
					href="post.jsp">핫플자랑하기</a></li>
				<li class="nav-item"><a class="nav-link" aria-current="page"
					href="${root }/HotPlace?action=list">여행정보공유</a></li>
			</ul>
			<!-- 로그인 전 -->
			<c:if test="${empty loginUser}">
				<ul class="navbar-nav mb-2 me-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link" aria-current="page"
						href="${root}/user?action=mvSignup">회원가입</a></li>
					<li class="nav-item"><a class="nav-link" aria-current="page"
						href="${root}/user?action=mvLogin">로그인</a></li>
				</ul>

			</c:if>

			<c:if test="${not empty loginUser}">

				<!-- 로그인 후 -->
				<ul class="navbar-nav mb-2 me-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link" aria-current="page"
					href="${root}/user?action=logout">로그아웃</a></li>
					<li class="nav-item"><a class="nav-link" aria-current="page"
						href="${root}/user?action=userInfo">마이페이지</a></li>
				</ul>
			</c:if>

		</div>
	</div>
</nav>