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




				<div class="row">
					<div class="d-flex justify-content-center align-items-center">
						<div>
							<div class="mb-3">
								<h2>회원 정보 수정</h2>
							</div>

							<div class="mb-3">

								<label for="nickname" class="form-label">이름</label> <input
									class="form-control" id="name" placeholder="이름"
									value="${loginUser.name}" readonly />
							</div>
			
							<div class="mb-3">

								<label for="nickname" class="form-label">아이디</label> <input
									class="form-control" id="nickname" placeholder="아이디.."
									value="${loginUser.nickName}" readonly />
							</div>


							<div class="mb-3">
								<label for="password" class="form-label">비밀번호
									수정</label> <input type="password" class="form-control" id="password"
									placeholder="비밀번호" />
							</div>
							<div class="mb-3">
								<label for="passwordConfirm" class="form-label">비밀번호확인</label>
								<input type="password" class="form-control" id="passwordConfirm"
									placeholder="비밀번호확인.." />
							</div>
							<div class="input-group mb-3">
								<input type="text" class="form-control" placeholder="이메일"
									aria-label="이메일" id="email" name="email"> <span
									class="input-group-text">@</span>
								<!-- <input type="text" class="form-control" placeholder="Server" aria-label="Server"> -->
								<!-- <label class="input-group-text" for="inputGroupSelect01">Options</label> -->
								<select class="form-select" id="inputGroupSelect01">
									<option selected>선택...</option>
									<option value="1">naver.com</option>
									<option value="2">google.com</option>
								</select>
							</div>
						</div>

						<div class="col-md-2">
							<button class="btn btn-light"
								onclick="modifyFunc2(${loginUser.userId})">회원정보 저장</button>
						</div>
					</div>


				</div>
		</c:if>


		<%@ include file="/common/footer.jsp"%>

		<script type="text/javascript"
			src="//dapi.kakao.com/v2/maps/sdk.js?appkey= ****** 카카오맵 API키 입력 ******&libraries=services,clusterer,drawing"></script>
		<script src="js/scripts.js"></script>
</body>
</html>
