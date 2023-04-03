<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
<link rel="shortcut icon" href="./assets/img/favicon.ico" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css" />
<link rel="stylesheet" href="css/style.css">
<style>
.wrap {
	position: absolute;
	left: 0;
	width: 288px;
	height: 132px;
	bottom: 10px;
	margin-left: -70px;
	text-align: left;
	overflow: hidden;
	font-size: 12px;
	font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
	line-height: 1.5;
}

.wrap * {
	padding: 0;
	margin: 0;
}

.wrap .info {
	width: 286px;
	height: 120px;
	border-radius: 5px;
	border-bottom: 2px solid #ccc;
	border-right: 1px solid #ccc;
	overflow: hidden;
	background: #fff;
	font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
}

.wrap .info:nth-child(1) {
	border: 0;
	box-shadow: 0px 1px 2px #888;
}

.info .title {
	padding: 5px 0 0 10px;
	height: 30px;
	background: #eee;
	border-bottom: 1px solid #ddd;
	font-size: 18px;
	font-weight: bold;
}

.info .close {
	position: absolute;
	top: 10px;
	right: 10px;
	color: #888;
	width: 17px;
	height: 17px;
	background:
		url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png");
}

.info .close:hover {
	cursor: pointer;
}

.info .body {
	position: relative;
	overflow: hidden;
}

.info .desc {
	position: relative;
	margin: 13px 0 0 90px;
	height: 75px;
}

.desc .ellipsis {
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}

.desc .jibun {
	font-size: 11px;
	color: #888;
	margin-top: -2px;
}

.info .img {
	position: absolute;
	top: 6px;
	left: 5px;
	width: 73px;
	height: 71px;
	border: 1px solid #ddd;
	color: #888;
	overflow: hidden;
}

.info:after {
	content: "";
	position: absolute;
	margin-left: -12px;
	left: 50%;
	bottom: 0;
	width: 22px;
	height: 12px;
	background:
		url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}

.info .link {
	color: #5085bb;
}
</style>
</head>
<body>
	<!-- 상단 navbar start -->
	<%@ include file="/common/nav.jsp"%>



	<div class="container">
		<div style="height: 70px"></div>
		<div class="col-md">
			<div class="alert  mt-3 text-center fw-bold" role="alert">전국
				관광지 정보</div>
			<!-- 관광지 검색 start -->
			<form class="d-flex my-3" onsubmit="return false;" role="search">
				<select id="search-area" class="form-select me-2">
					<option value="0" name="sidoCode" selected>검색할 시/도 선택</option>
					<option value="1">서울</option>
					<option value="2">인천</option>
					<option value="3">대전</option>
					<option value="4">대구</option>
					<option value="5">광주</option>
					<option value="6">부산</option>
					<option value="7">울산</option>
					<option value="8">세종특별자치시</option>
					<option value="31">경기도</option>
					<option value="32">강원도</option>
					<option value="33">충청북도</option>
					<option value="34">충청남도</option>
					<option value="35">경상북도</option>
					<option value="36">경상남도</option>
					<option value="37">전라북도</option>
					<option value="38">전라남도</option>
					<option value="39">제주도</option>
				</select> <select id="search-gugun" class="form-select me-2">
					<option value="0" name="gugunCode" selected>검색할 구/군 선택</option>
				</select> <select id="search-content-id" class="form-select me-2">
					<option value="0" name="contentTypeId" selected>관광지 유형</option>
					<option value="12">관광지</option>
					<option value="14">문화시설</option>
					<option value="15">축제공연행사</option>
					<option value="25">여행코스</option>
					<option value="28">레포츠</option>
					<option value="32">숙박</option>
					<option value="38">쇼핑</option>
					<option value="39">음식점</option>
				</select>
				<button id="btn-search" class="btn btn-outline-success"
					type="button">검색</button>
			</form>
			<div id="map" style="width: 100%; height: 500px"></div>
		</div>
	</div>
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=51e68de4bd4fc820db0c06b1e4a075ce"></script>
	<script>

      let currentLatitude = 37.5012767241426;
      let currentLongitude = 127.039600248343;

      navigator.geolocation.getCurrentPosition((position) => {
        currentLatitude = position.coords.latitude;
        currentLongitude = position.coords.longitude;
        console.log("현재위치 : ", currentLatitude + ", " + currentLongitude);
      });

      //호이스팅 문제로 바뀐 값이 잘 안들어가는듯
      console.log(currentLatitude + ", " + currentLongitude);
      var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
      var options = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(currentLatitude, currentLongitude), //지도의 중심좌표.
        level: 3, //지도의 레벨(확대, 축소 정도)
      };

      var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
    </script>
	<script>
//시도가 바뀌면 구군정보 얻기.
document.querySelector("#search-area").addEventListener("change", function () {
	console.log("change");
  if (this[this.selectedIndex].value) {
    let regcode = this[this.selectedIndex].value;
    sendRequest("search-gugun", regcode);
  } else {
    initOption("search-gugun");
    // initOption("dong");
  }
});

function sendRequest(selid, regcode) {
  let url;
  console.log("sendRequest");
  if (regcode == "") {
    url =`${root}/Search?action=sido`;
    console.log(url);
  } else {
    url = `${root}/Search?action=gugun&sidoCode=`+regcode;
    console.log(url);
  }
  
  fetch(url)
    .then((response) => response.json())
    .then((data) => addOption(selid, data));
}

function addOption(selid, data) {
  let opt = ``;
  initOption(selid);
  switch (selid) {
    case "search-area":
      opt += `<option value="">검색할 시/도 선택</option>`;
      data.items.forEach(function (regcode) {
        opt += `
            <option value="${regcode.code}">${regcode.name}</option>
            `;
      });
      break;
    case "search-gugun":
      opt += `<option value="">검색할 구/군 선택</option>`;
      data.items.forEach(function (regcode) {
    	  //console.log(regcode.code, regcode.name);
        opt += `
            <option value=`
            +regcode.code
            +`>`
            +regcode.name
            +`</option>`;
      });
      break;
  }
  document.querySelector(`#`+selid).innerHTML = opt;
}

function initOption(selid) {
	  let options = document.querySelector(`#`+selid);
	  options.length = 0;
	}
	
	document.querySelector("#btn-search").addEventListener("click", function(){
		let serviceKey='1WtqwaACmtkCq6zIf05q9PqNaYitlE7zCvYDrhe%2FQE%2FtwGNqPHF7zB2SJPYNlJPrO%2FPSATaLA7V6Ua5kikpZOQ%3D%3D';
		
		let sidoCode=document.getElementById('search-area').value;
		let gugunCode=document.getElementById('search-gugun').value;
		let contentTypeId=document.getElementById("search-content-id").value;
		
		
//		let searchURL='https://apis.data.go.kr/B551011/KorService1/searchKeyword1?serviceKey='
//				+serviceKey
//				+'&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&keyword='
//				+keyword
//				+'&contentTypeId='
//				+contentTypeId;

		let url=`${root}/Search?action=search&sidoCode=`
				+sidoCode
				+`&gugunCode=`
				+gugunCode
				+`&contentTypeId=`
				+contentTypeId;
		
		console.log(url);
		
		fetch(url).then((response)=>response.json()).then((data)=>makeList(data));
	});
	
	let positions;
      let markers = [];
      function makeList(data) {

    	//console.log(data);
    	//console.log(typeof(data));
        //console.log("데이터들 :", places);

        // 마커를 표시할 위치와 내용을 가지고 있는 객체 배열입니다

        positions = [];

        initMarkers();
        console.log(data.places[0].title);
        panTo();
        function panTo(){
        	var moveLatLon=new kakao.maps.LatLng(data.places[0].latitude, data.places[0].longitude);
        	map.panTo(moveLatLon);
        }
        
        data.places.forEach((element) => {
        //console.log(element.title, element.img, element.addr1, element.addr2, element.latitude, element.longitude);
          var pos = {
            content: `<div><div class="info">
                        <div class="title">`
                        +element.title
                        +`</div>
                        <div class="body">
                            <div class="img">
                                <img src=`
                                +element.img
                                +` width="73" height="70">
                            </div>
                            <div class="desc">
                                <div class="ellipsis">`
                                +element.addr1
                                +`</div>
                                <div class="jibun ellipsis">`
                                +element.addr2
                                +`</div>
                            </div>
                        </div>
                    </div></div>`,
            latlng: new kakao.maps.LatLng(element.latitude, element.longitude),
          }
          positions.push(pos);
        });
        
        for (var i = 0; i < positions.length; i++) {
          // 마커를 생성합니다
          var marker = new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: positions[i].latlng, // 마커의 위치
          });
          //console.log("positions.latlng",positions[i].latlng);
          //console.log("marker",marker);
          markers.push(marker);

          // 마커에 표시할 인포윈도우를 생성합니다
          var infowindow = new kakao.maps.InfoWindow({
            content: positions[i].content, // 인포윈도우에 표시할 내용
          });

          // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
          // 이벤트 리스너로는 클로저를 만들어 등록합니다
          // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
          kakao.maps.event.addListener(
            marker,
            "mouseover",
            makeOverListener(map, marker, infowindow)
          );
          kakao.maps.event.addListener(
            marker,
            "mouseout",
            makeOutListener(infowindow)
          );
        }

        // 인포윈도우를 표시하는 클로저를 만드는 함수입니다
        function makeOverListener(map, marker, infowindow) {
          return function () {
            infowindow.open(map, marker);
          };
        }

        // 인포윈도우를 닫는 클로저를 만드는 함수입니다
        function makeOutListener(infowindow) {
          return function () {
            infowindow.close();
          };
        }

        function initMarkers() {
          if (!markers) return;
          for (let i = 0; i < markers.length; i++) {
            markers[i].setMap(null);
          }
        }
        
        
      }

</script>
</body>
</html>
