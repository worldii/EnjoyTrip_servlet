<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<%@ include file="/common/header.jsp"%>
<body>
	<%@ include file="/common/nav.jsp"%>

	<div class="container">
		<div style="height: 70px"></div>
		<div class="d-flex justify-content-center align-items-center p-3">
			<div>
				<div class=" row mb-3">
					<h2>비밀번호 찾기</h2>
				</div>
				<div class="mb-3">
					<label for="name" class="form-label">이름 </label> <input
					 class="form-control" id="name"
						placeholder="이름" />
				</div>
				<div class=" row mb-3">
					<label for="nickname" class="form-label">아이디</label> <input
						class="form-control" id="nickname" placeholder="아이디.." />

				</div>
			
				<div class=" mb-3">
					<button class="btn btn-light m-1"
						onclick="javascript:findPassword()">확인</button>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/common/footer.jsp"%>

	<script src="js/scripts.js"></script>
</body>
</html>
