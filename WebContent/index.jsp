<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">

<%@ include file="/common/header.jsp"%>
<body>
	<%@ include file="/common/nav.jsp"%>


	<!-- 중앙 content start -->
	<div class="container">
		<div style="height: 70px"></div>
		<div class="row">
			<!-- 중앙 center content end -->
			<div class="col-md">
				<div class=" mt-3 text-center fw-bold" role="alert">
					<img src="https://picsum.photos/1080/720/?random"
						class="image-thumbnail">
				</div>
				
			</div>
		</div>
		<%@ include file="/common/footer.jsp"%>

		<!-- 중앙 content end -->
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
			crossorigin="anonymous"></script>
		<script src="js/scripts.js"></script>
</body>
</html>
