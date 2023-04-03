<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="/common/header.jsp"%>
<body>
	<%@ include file="/common/nav.jsp"%>


	<div class="container">
		<div style="height: 70px"></div>
		<c:if test="${not empty loginUser}">
			<div class="row pt-5">
				<div class="col-md-3">
					<img id="profile" src="assets/img/noimg.png" alt="">
					<div class="input-group mb-3 pt-4">
						<input type="file" class="form-control" accept="image/*"
							id="inputGroupFile02"> <label class="input-group-text "
							for="inputGroupFile02" onclick="javascript:imageUpload()">Upload</label>
					</div>
				</div>

				<div class="col">
					<div>
						<div class="mb-3">
							<h2>회원 정보 조회</h2>
						</div>


						<div class="mb-3">
							<span> 이름 : </span><span id="userInfoName">${loginUser.name}</span>

						</div>
						<div class="mb-3">
							<span> 아이디 : </span><span id="userInfoId">${loginUser.nickName}</span>

						</div>
						<div class="mb-3">
							<span> 이메일 : </span><span id="userInfoEmail">${loginUser.email}</span>
						</div>


					</div>
				</div>
				<div class="col-md-2">
					<button class="btn btn-light" onclick="modifyFunc(${loginUser.userId})">회원정보 수정</button>
					<button class="btn btn-light" onclick="deleteFunc(${loginUser.userId})">탈퇴</button>
				</div>
			</div>
		</c:if>
		</div>


		<%@ include file="/common/footer.jsp"%>

		<script type="text/javascript"
			src="//dapi.kakao.com/v2/maps/sdk.js?appkey= ****** 카카오맵 API키 입력 ******&libraries=services,clusterer,drawing"></script>
		<script src="js/scripts.js"></script>
</body>
</html>
