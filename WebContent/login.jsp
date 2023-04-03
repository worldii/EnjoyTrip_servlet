<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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
					<h2>로그인</h2>
				</div>
				<div class=" row mb-3">
					<label for="nickname" class="form-label">아이디</label> 
					<input
						class="form-control" id="userName" name="userName" />
				</div>
				<div class="row mb-3">
					<label for="password" class="form-label" >비밀번호</label>
					<input type="password" class="form-control" id="password" name="password" />
				</div>
				<div class=" mb-3">
					<button class="btn btn-light m-1"
						onclick="javascript:login()">확인</button>
					<button  class="btn btn-light m-1">아이디찾기</button>
					<button class="btn btn-light m-1" onclick="javascript:mvPassword()">비밀번호찾기</button>
				</div>

			</div>
		</div>
	</div>
	<%@ include file="/common/footer.jsp"%>

	<script src="js/scripts.js"></script>
</body>
</html>
