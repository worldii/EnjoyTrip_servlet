<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@ include file="/common/header.jsp"%>
<body>
  <!-- 상단 navbar start -->
	<%@ include file="/common/nav.jsp"%>

    <div class="container">
      <div style="height: 70px"></div>

      <div class="row">
        <div class="d-flex justify-content-center align-items-center">
          <div>
            <div class="mb-3">
              <h2>회원가입</h2>
            </div>
            <div class="mb-3">
              <label for="name" class="form-label"
                >이름</label>
              <input
                class="form-control"
                id="name"
                placeholder="이름.."
              />
            </div>
            <div class="mb-3">

              <label for="nickname" class="form-label">아이디</label>
              <input
                class="form-control"
                id="nickname"
                placeholder="아이디.."
              />
          
            </div>
            <div class="mb-3">              
              <button type="submit" class="btn btn-light" onclick="javascript:signupCheck()">중복확인</button>
              <span id="signupCheck" class="p-5"> </span>
            </div>
            
            <div class="mb-3">
              <label for="password" class="form-label"
                >비밀번호</label>
              <input
              type="password"
                class="form-control"
                id="password"
                placeholder="비밀번호"
              />
            </div>
            <div class="mb-3">
              <label for="passwordConfirm" class="form-label"
                >비밀번호확인</label>
              <input
                            type="password"
              
                class="form-control"
                id="passwordConfirm"
                placeholder="비밀번호확인.."
              />
            </div>
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="이메일" aria-label="이메일" id="email" name="email">
                <span class="input-group-text">@</span>
                <!-- <input type="text" class="form-control" placeholder="Server" aria-label="Server"> -->
                <!-- <label class="input-group-text" for="inputGroupSelect01">Options</label> -->
                <select class="form-select" id="inputGroupSelect01">
                  <option selected>선택...</option>
                  <option value="1">naver.com</option>
                  <option value="2">google.com</option>
                </select>
              </div>
            <button class="btn btn-primary" onclick="javascript:signup()">회원가입</button>
          </div>
        </div>
      </div>
    </div>
      <%@ include file="/common/footer.jsp"%>

  <script src="js/scripts.js"></script>
  </body>
</html>
