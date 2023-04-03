<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%-- jstl 사용하기 위한 코드 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 프로젝트의 context 경로를 편하게 사용하기 위한 코드 --%>
<c:set var="root" value="${pageContext.request.contextPath}"/>
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
             
                <div class="alert  mt-3 text-center fw-bold" role="alert">
                    핫플 자랑
                </div>
               <div class="row">
                <div class="col-6 align-content-center">
                    <div id="map" style="width:70%;height:350px;"></div>
                    <p><em>지도를 클릭해주세요!</em></p> 
                </div>
                
                <div class="col">
                    <form method="post" action="${pageContext.request.contextPath }/HotPlace">
                    <input type="hidden" name="action" value="addHotPlace">
                        <fieldset>
                            <legend>위치</legend>
                            위도 : <input type="text" name="Latitude" id="form_lat">
                            경도 : <input type="text" name="Longitude" id="form_lng">
                        </fieldset>
                        
                        <fieldset>
                            <legend>글 작성</legend>
                            <div class="form-group">
                                <label for="exampleFormControlInput1">핫플 이름</label>
                                <input type="text" class="form-control" name="placeName" id="exampleFormControlInput1">
                              </div>
                              
                              <div class="form-group">
                                <label for="exampleFormControlSelect2">다녀온 날짜</label>
                                <input type="date" class="form-control" name="visiteDate" id="exampleFormControlSelect2">
                              </div>

                              <div class="form-group">
                                <label for="exampleFormControlSelect1">장소 유형</label>
                                <select class="form-control" name="placeType" id="exampleFormControlSelect1">
                                  <!-- 12:관광지, 14:문화시설, 15:축제공연행사, 25:여행코스, 28:레포츠, 32:숙박, 38:쇼핑, 39:음식점 -->
                                  <option value="12">관광지</option>
                                  <option value="14">문화시설</option>
                                  <option value="15">축제공연행사</option>
                                  <option value="25">여행코스</option>
                                  <option value="28">레포츠</option>
                                  <option value="32">숙박</option>
                                  <option value="38">쇼핑</option>
                                  <option value="39">음식점</option>
                                </select>
                              </div>
                              
                              <div class="form-group">
                                <label for="exampleFormControlTextarea1">핫플 상세설명</label>
                                <textarea class="form-control" name="placeDescribe" id="exampleFormControlTextarea1" rows="3"></textarea>
                              </div>
                              
                              <div>
                                <button type="reset" class="btn btn-danger">초기화</button>
                                <button type="submit" class="btn btn-primary">저장</button>
                              </div>
                        </fieldset>
                    </form>
                </div>
            </div>

                <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=51e68de4bd4fc820db0c06b1e4a075ce"></script>
                <script>
                    var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
                    mapOption = { 
                    center: new kakao.maps.LatLng(37.5012767241426, 127.039600248343), // 지도의 중심좌표
                    level: 3 // 지도의 확대 레벨
                    };

                    var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

                    // 지도를 클릭한 위치에 표출할 마커입니다
                    var marker = new kakao.maps.Marker({ 
                        // 지도 중심좌표에 마커를 생성합니다 
                        position: map.getCenter() 
                    }); 
                    // 지도에 마커를 표시합니다
                    marker.setMap(map);

                    // 지도에 클릭 이벤트를 등록합니다
                    // 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
                    kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        

                    // 클릭한 위도, 경도 정보를 가져옵니다 
                    var latlng = mouseEvent.latLng; 

                    // 마커 위치를 클릭한 위치로 옮깁니다
                    marker.setPosition(latlng);

                    
                    var inputLat=document.getElementById('form_lat');
                    inputLat.value=latlng.getLat();
                    var inputLng=document.getElementById('form_lng');
                    inputLng.value=latlng.getLng();
                });
                </script>
        <!-- 중앙 content end -->   
    </div>
      
      <%@ include file="/common/footer.jsp"%>


        <!-- 하단 footer end -->
        <script type="text/javascript"
          src="//dapi.kakao.com/v2/maps/sdk.js?appkey=11d95f6f2706a3aa9522b5ac2bde4edf&libraries=services,clusterer,drawing"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
            crossorigin="anonymous"></script>
        
</body>

</html>
