
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>ENJOYTRIP</title>
    <link rel="shortcut icon" href="./assets/img/favicon.ico" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css" />
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/plan.css">
</head>
<body>
	<%@ include file="/common/nav.jsp"%>

    <!-- 상단 navbar end -->
    <!-- 중앙 content start -->
    <div class="container">
        <div style="height: 70px"></div>
        <div class="row">
            <!-- 중앙 center content end -->
            <div class="col-md">
             
                <div class="alert  mt-3 text-center fw-bold" role="alert">
                    여행 경로 세우기
                </div>
               
                
                <!-- kakao map start -->
                <div class="map_wrap">
                    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>

                    <div id="menu_wrap" class="bg_white">
                        <div class="option">
                            <div>
                                <form onsubmit="searchPlaces(); return false;">
                                    키워드 : <input type="text" value="안녕" id="keyword" size="15">
                                    <button type="submit">검색하기</button>
                                </form>
                            </div>
                        </div>
                        <hr>
                        <h2> 계획 목록</h2>
                        <ul id="placesList" >

                        </ul>
                        <div id="pagination"></div>
                    </div>
                  
                </div>
                <div class="alert  mt-3 fw-bold" role="alert">
                    <ol id="make_plan_list" class="list-group">

                    </ol>
                </div>
                <!-- kakao map end -->
                <div class="row">
                    <table class="table table-striped" style="display: none">
                        <thead>
                            <tr>
                                <th>대표이미지</th>
                                <th>관광지명</th>
                                <th>주소</th>
                                <th>위도</th>
                                <th>경도</th>
                            </tr>
                        </thead>
                        <tbody id="trip-list"></tbody>
                    </table>
                </div>
                <!-- 관광지 검색 end -->
            </div>
        </div>

        <!-- 중앙 content end -->   
    </div>

      				<%@ include file="/common/footer.jsp"%>

        <!-- 하단 footer end -->
        <script type="text/javascript"
          src="//dapi.kakao.com/v2/maps/sdk.js?appkey=11d95f6f2706a3aa9522b5ac2bde4edf&libraries=services,clusterer,drawing"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
            crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="js/plan.js"></script>
</body>

</html>