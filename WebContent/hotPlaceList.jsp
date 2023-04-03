<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/header.jsp" %>
</head>
<body>
<%@ include file="/common/nav.jsp"%>

<div class="row justify-content-center">
        <div class="col-lg-8 col-md-10 col-sm-12">
          <h2 class="my-3 py-3 shadow-sm bg-light text-center">
            <mark class="sky">핫플레이스 목록</mark>
          </h2>
        </div>
        <div class="col-lg-8 col-md-10 col-sm-12">
          <div class="row align-self-center mb-2">
            <div class="col-md-2 text-start">
              <button type="button" id="btn-mv-register" class="btn btn-outline-primary btn-sm">
                글쓰기
              </button>
            </div>
            <div class="col-md-7 offset-3">
              <form class="d-flex">
                <select
                  id="key"
                  class="form-select form-select-sm ms-5 me-1 w-50"
                  aria-label="검색조건"
                >
                  <option selected>검색조건</option>
                  <option value="articleno">글번호</option>
                  <option value="subject">제목</option>
                  <option value="userid">작성자</option>
                </select>
                <div class="input-group input-group-sm">
                  <input type="text" class="form-control" placeholder="검색어..." />
                  <button class="btn btn-dark" type="button">검색</button>
                </div>
              </form>
            </div>
          </div>
          <table class="table table-hover">
            <thead>
              <tr class="text-center">
                <th scope="col">글번호</th>
                <th scope="col">제목</th>
                <th scope="col">관광지 종류</th>
                <th scope="col">방문일</th>
              </tr>
            </thead>
            <tbody>
           <c:forEach var="article" items="${articles }">        
              <tr class="text-center">
                <th scope="row">${article.articleNo}</th>
                <td class="text-start">
                  <a
                    href="#"
                    class="article-title link-dark"
                    data-no="${article.articleNo}"
                    style="text-decoration: none"
                  >
                    ${article.placeName}
                  </a>
                </td>
                
                <c:if test="${article.placeType eq 12}">
                	<c:set var="placeTypeWord" value="관광지"></c:set>
                </c:if>
                <c:if test="${article.placeType eq 14}">
                	<c:set var="placeTypeWord" value="문화시설"></c:set>
                	
                </c:if>
                <c:if test="${article.placeType eq 15}">
                	<c:set var="placeTypeWord" value="축제공연행사"></c:set>
                	
                </c:if>
                <c:if test="${article.placeType eq 25}">
                	<c:set var="placeTypeWord" value="여행코스"></c:set>
                	
                </c:if>
                <c:if test="${article.placeType eq 28}">
                	<c:set var="placeTypeWord" value="레포츠"></c:set>
                	
                </c:if>
                <c:if test="${article.placeType eq 32}">
                	<c:set var="placeTypeWord" value="숙박"></c:set>
                	
                </c:if>
                <c:if test="${article.placeType eq 38}">
                	<c:set var="placeTypeWord" value="쇼핑"></c:set>
                	
                </c:if>
                <c:if test="${article.placeType eq 39}">
                	<c:set var="placeTypeWord" value="음식점"></c:set>
                	
                </c:if>
                <td>${placeTypeWord }</td>
                <td>${article.visiteDate}</td>
              </tr>
</c:forEach>    
            </tbody>
          </table>
        </div>
        <div class="row">
          <ul class="pagination justify-content-center">
            <li class="page-item">
              <a class="page-link" href="#">이전</a>
            </li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item active">
              <a class="page-link" href="#">2</a>
            </li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item"><a class="page-link" href="#">4</a></li>
            <li class="page-item"><a class="page-link" href="#">5</a></li>
            <li class="page-item"><a class="page-link" href="#">다음</a></li>
          </ul>
        </div>
      </div>
    </div>

<%@ include file="/common/footer.jsp"%>

<script>
      let titles = document.querySelectorAll(".article-title");
      titles.forEach(function (title) {
        title.addEventListener("click", function () {
          console.log(this.getAttribute("data-no"));
          location.href = "${root}/article?action=view&articleno=" + this.getAttribute("data-no");
        });
      });

      document.querySelector("#btn-mv-register").addEventListener("click", function () {
        location.href = "${root}/post.jsp";
      });
    </script>

</body>
</html>