// 마커를 담을 배열입니다
var markers = [];

var mapContainer = document.getElementById('map'), // 지도를 표시할 div
  mapOption = {
    center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
    level: 3, // 지도의 확대 레벨
  };

let path = [];
// 지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

// 장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places();

// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });

// 키워드로 장소를 검색합니다
searchPlaces();

// 키워드 검색을 요청하는 함수입니다
function searchPlaces() {
  var keyword = document.getElementById('keyword').value;

  if (!keyword.replace(/^\s+|\s+$/g, '')) {
    alert('키워드를 입력해주세요!');
    return false;
  }

  // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
  ps.keywordSearch(keyword, placesSearchCB);
}
let searchData = [];

// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
function placesSearchCB(data, status, pagination) {
  if (status === kakao.maps.services.Status.OK) {
    // 정상적으로 검색이 완료됐으면
    // 검색 목록과 마커를 표출합니다
    displayPlaces(data);

    searchData = data;
    // 페이지 번호를 표출합니다
    displayPagination(pagination);
  } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
    alert('검색 결과가 존재하지 않습니다.');
    return;
  } else if (status === kakao.maps.services.Status.ERROR) {
    alert('검색 결과 중 오류가 발생했습니다.');
    return;
  }
}

// 선이 그려지고 있는 상태일 때 지도를 클릭하면 호출하여
// 클릭 지점에 대한 정보 (동그라미와 클릭 지점까지의 총거리)를 표출하는 함수입니다
function displayCircleDot(position, distance) {
  // 클릭 지점을 표시할 빨간 동그라미 커스텀오버레이를 생성합니다
  var circleOverlay = new kakao.maps.CustomOverlay({
    content: '<span class="dot"></span>',
    position: position,
    zIndex: 1,
  });

  // 지도에 표시합니다
  circleOverlay.setMap(map);

  if (distance > 0) {
    // 클릭한 지점까지의 그려진 선의 총 거리를 표시할 커스텀 오버레이를 생성합니다
    var distanceOverlay = new kakao.maps.CustomOverlay({
      content: '<div class="dotOverlay">거리 <span class="number">' + distance + '</span>m</div>',
      position: position,
      yAnchor: 1,
      zIndex: 2,
    });

    // 지도에 표시합니다
    distanceOverlay.setMap(map);
  }

  // 배열에 추가합니다
  dots.push({ circle: circleOverlay, distance: distanceOverlay });
}

var drawingFlag = false; // 선이 그려지고 있는 상태를 가지고 있을 변수입니다
var dots = [];
var distanceOverlay; // 선의 거리정보를 표시할 커스텀오버레이 입니다
var clickLine; // 마우스로 클릭한 좌표로 그려질 선 객체입니다

// 검색 결과 목록과 마커를 표출하는 함수입니다
function displayPlaces(places) {
  var listEl = document.getElementById('placesList'),
    menuEl = document.getElementById('menu_wrap'),
    fragment = document.createDocumentFragment(),
    bounds = new kakao.maps.LatLngBounds(),
    listStr = '';

  // 검색 결과 목록에 추가된 항목들을 제거합니다
  removeAllChildNods(listEl);

  // 지도에 표시되고 있는 마커를 제거합니다
  removeMarker();

  for (var i = 0; i < places.length; i++) {
    // 마커를 생성하고 지도에 표시합니다
    var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
      marker = addMarker(placePosition, i),
      itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
    // LatLngBounds 객체에 좌표를 추가합니다
    bounds.extend(placePosition);

    // 마커와 검색결과 항목에 mouseover 했을때
    // 해당 장소에 인포윈도우에 장소명을 표시합니다
    // mouseout 했을 때는 인포윈도우를 닫습니다
    (function (marker, title) {
      kakao.maps.event.addListener(marker, 'mouseover', function () {
        displayInfowindow(marker, title);
      });

      kakao.maps.event.addListener(marker, 'mouseout', function () {
        infowindow.close();
      });

      itemEl.onmouseover = function () {
        displayInfowindow(marker, title);
      };

      itemEl.onmouseout = function () {
        infowindow.close();
      };

      // 클릭 이벤트 추가
    })(marker, places[i].place_name);

    fragment.appendChild(itemEl);
  }

  // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
  listEl.appendChild(fragment);
  menuEl.scrollTop = 0;

  // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
  map.setBounds(bounds);
}

// 검색결과 항목을 Element로 반환하는 함수입니다
function getListItem(index, places) {
  var el = document.createElement('li'),
    itemStr =
      '<span class="markerbg marker_' +
      (index + 1) +
      '"></span>' +
      '<div class="info">' +
      '   <h5>' +
      places.place_name +
      '</h5>';

  if (places.road_address_name) {
    itemStr +=
      '    <span>' +
      places.road_address_name +
      '</span>' +
      '   <span class="jibun gray">' +
      places.address_name +
      '</span>';
  } else {
    itemStr += '    <span>' + places.address_name + '</span>';
  }

  itemStr += '  <span class="tel">' + places.phone + '</span>';
  itemStr += `<span id="x_cor" class="d-none">${places.x} </span>`;
  itemStr += `<span id="y_cor" class="d-none"> ${places.y} </span>`;

  itemStr +=
    ` <button class="btn btn-primary" id="button_${index}" onclick="javascript:addPlaces(this)">추가</button> ` +
    '</div>';

  el.innerHTML = itemStr;
  el.className = 'item';
  return el;
}

const deletePlace = target => {
  let childNode = target.parentNode;
  let parentNode = target.parentNode.parentNode;
  var path = clickLine.getPath();

  for (var i = 0; i < parentNode.childNodes.length; i++) {
    if (parentNode.childNodes[i] === childNode) {
      path.splice(i - 1, 1);
      break;
    }
  }
  parentNode.removeChild(childNode);

  drawingFlag = false; // 선이 그려지고 있는 상태를 가지고 있을 변수입니다

  deleteClickLine();
  // 지도 위에 커스텀오버레이가 표시되고 있다면 지도에서 제거합니다
  deleteDistnce();
  // 지도 위에 선을 그리기 위해 클릭한 지점과 해당 지점의 거리정보가 표시되고 있다면 지도에서 제거합니다
  deleteCircleDot();

  if (path.length == 0) {
    drawingFlag = false;
    return;
  }

  drawingFlag = true;
  // 클릭한 위치를 기준으로 선을 생성하고 지도위에 표시합니다
  clickLine = new kakao.maps.Polyline({
    map: map, // 선을 표시할 지도입니다
    path: [path[0]], // 선을 구성하는 좌표 배열입니다 클릭한 위치를 넣어줍니다
    strokeWeight: 3, // 선의 두께입니다
    strokeColor: '#db4040', // 선의 색깔입니다
    strokeOpacity: 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
    strokeStyle: 'solid', // 선의 스타일입니다
  }); // 선이 그려지고 있을 때 마우스 움직임에 따라 선이 그려질 위치를 표시할 선을 생성합니다
  moveLine = new kakao.maps.Polyline({
    strokeWeight: 3, // 선의 두께입니다
    strokeColor: '#db4040', // 선의 색깔입니다
    strokeOpacity: 0.5, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
    strokeStyle: 'solid', // 선의 스타일입니다
  });

  displayCircleDot(path[0], 0);

  for (let i = 1; i < path.length; i++) {
    placePosition = path[i];
    var path2 = clickLine.getPath();
    // 좌표 배열에 클릭한 위치를 추가합니다

    path2.push(placePosition);
    // 다시 선에 좌표 배열을 설정하여 클릭 위치까지 선을 그리도록 설정합니다
    clickLine.setPath(path2);

    var distance = Math.round(clickLine.getLength());

    displayCircleDot(placePosition, distance);

    // 마우스 클릭으로 그려진 마지막 좌표와 마우스 커서 위치의 좌표로 선을 표시합니다
    var movepath = [path2[path2.length - 1], placePosition];
    moveLine.setPath(movepath);
    moveLine.setMap(map);

    var distance = Math.round(clickLine.getLength() + moveLine.getLength()), // 선의 총 거리를 계산합니다
      content = '<div class="dotOverlay distanceInfo">총거리 <span class="number">' + distance + '</span>m</div>'; // 커스텀오버레이에 추가될 내용입니다
    content2 = getTimeHTML(distance);
    // 거리정보를 지도에 표시합니다
    showDistance(content, placePosition);
  }
  let bounds = new kakao.maps.LatLngBounds();
  for (let i = 0; i < path.length; i++) {
    bounds.extend(path[i]);
  }
  map.setBounds(bounds);

  if (path.length > 1) showDistance(content2, path[path.length - 1]);
};

const addPlaces = target => {
  let info = target.parentNode.cloneNode(true);

  let infoTitle = info.querySelector('h5');

  let infoAddress = info.querySelectorAll('span');

  let el = document.createElement('li');
  el.classList.add('list-group-item');

  el.addEventListener('click', () => {
    let bounds = new kakao.maps.LatLngBounds();
    var placePosition = new kakao.maps.LatLng(
      target.parentNode.querySelector('#y_cor').innerHTML,
      target.parentNode.querySelector('#x_cor').innerHTML,
    );

    bounds.extend(placePosition);

    map.setBounds(bounds);
  });
  el.appendChild(infoTitle);
  infoAddress.forEach(element => {
    el.appendChild(element);
  });

  el.innerHTML += `<button id=${
    document.querySelector('#make_plan_list').childElementCount
  } onclick="javecript:deletePlace(this)">삭제</button>`;
  document.querySelector('#make_plan_list').appendChild(el);

  var x = target.parentNode.querySelector('#x_cor').innerHTML;
  var y = target.parentNode.querySelector('#y_cor').innerHTML;
  var placePosition = new kakao.maps.LatLng(y, x);

  // 지도 클릭이벤트가 발생했는데 선을 그리고있는 상태가 아니면
  if (!drawingFlag) {
    // 상태를 true로, 선이 그리고있는 상태로 변경합니다

    drawingFlag = true;

    // 지도 위에 선이 표시되고 있다면 지도에서 제거합니다
    deleteClickLine();

    // 지도 위에 커스텀오버레이가 표시되고 있다면 지도에서 제거합니다
    deleteDistnce();

    // 지도 위에 선을 그리기 위해 클릭한 지점과 해당 지점의 거리정보가 표시되고 있다면 지도에서 제거합니다
    deleteCircleDot();

    // 클릭한 위치를 기준으로 선을 생성하고 지도위에 표시합니다
    clickLine = new kakao.maps.Polyline({
      map: map, // 선을 표시할 지도입니다
      path: [placePosition], // 선을 구성하는 좌표 배열입니다 클릭한 위치를 넣어줍니다
      strokeWeight: 3, // 선의 두께입니다
      strokeColor: '#db4040', // 선의 색깔입니다
      strokeOpacity: 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
      strokeStyle: 'solid', // 선의 스타일입니다
    });

    // 선이 그려지고 있을 때 마우스 움직임에 따라 선이 그려질 위치를 표시할 선을 생성합니다
    moveLine = new kakao.maps.Polyline({
      strokeWeight: 3, // 선의 두께입니다
      strokeColor: '#db4040', // 선의 색깔입니다
      strokeOpacity: 0.5, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
      strokeStyle: 'solid', // 선의 스타일입니다
    });

    // 클릭한 지점에 대한 정보를 지도에 표시합니다
    displayCircleDot(placePosition, 0);
  } else {
    // 선이 그려지고 있는 상태이면
    // 그려지고 있는 선의 좌표 배열을 얻어옵니다
    var path = clickLine.getPath();
    // 좌표 배열에 클릭한 위치를 추가합니다

    path.push(placePosition);
    // 다시 선에 좌표 배열을 설정하여 클릭 위치까지 선을 그리도록 설정합니다
    clickLine.setPath(path);

    var distance = Math.round(clickLine.getLength());

    displayCircleDot(placePosition, distance);

    // 마우스 클릭으로 그려진 마지막 좌표와 마우스 커서 위치의 좌표로 선을 표시합니다
    var movepath = [path[path.length - 1], placePosition];
    moveLine.setPath(movepath);
    moveLine.setMap(map);

    var distance = Math.round(clickLine.getLength() + moveLine.getLength()), // 선의 총 거리를 계산합니다
      content = '<div class="dotOverlay distanceInfo">총거리 <span class="number">' + distance + '</span>m</div>'; // 커스텀오버레이에 추가될 내용입니다

    // 거리정보를 지도에 표시합니다
    showDistance(content, placePosition);

    let bounds = new kakao.maps.LatLngBounds();
    for (let i = 0; i < path.length; i++) {
      bounds.extend(path[i]);
    }
    map.setBounds(bounds);

    // if (dots[dots.length - 1].distance) {
    //   dots[dots.length - 1].distance.setMap(null);
    //   dots[dots.length - 1].distance = null;
    // }
    content2 = getTimeHTML(distance); // 커스텀오버레이에 추가될 내용입니다
    showDistance(content2, path[path.length - 1]);
  }
};

// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
function addMarker(position, idx, title) {
  var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
    imageSize = new kakao.maps.Size(36, 37), // 마커 이미지의 크기
    imgOptions = {
      spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
      spriteOrigin: new kakao.maps.Point(0, idx * 46 + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
      offset: new kakao.maps.Point(13, 37), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
    },
    markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
    marker = new kakao.maps.Marker({
      position: position, // 마커의 위치
      image: markerImage,
    });

  marker.setMap(map); // 지도 위에 마커를 표출합니다
  markers.push(marker); // 배열에 생성된 마커를 추가합니다

  return marker;
}

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
function removeMarker() {
  for (var i = 0; i < markers.length; i++) {
    markers[i].setMap(null);
  }
  markers = [];
}

// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
function displayPagination(pagination) {
  var paginationEl = document.getElementById('pagination'),
    fragment = document.createDocumentFragment(),
    i;

  // 기존에 추가된 페이지번호를 삭제합니다
  while (paginationEl.hasChildNodes()) {
    paginationEl.removeChild(paginationEl.lastChild);
  }

  for (i = 1; i <= pagination.last; i++) {
    var el = document.createElement('a');
    el.href = '#';
    el.innerHTML = i;

    if (i === pagination.current) {
      el.className = 'on';
    } else {
      el.onclick = (function (i) {
        return function () {
          pagination.gotoPage(i);
        };
      })(i);
    }

    fragment.appendChild(el);
  }
  paginationEl.appendChild(fragment);
}

// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
// 인포윈도우에 장소명을 표시합니다
function displayInfowindow(marker, title) {
  var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';
  infowindow.setContent(content);
  infowindow.open(map, marker);
}

// 검색결과 목록의 자식 Element를 제거하는 함수입니다
function removeAllChildNods(el) {
  while (el.hasChildNodes()) {
    el.removeChild(el.lastChild);
  }
}

// 마우스 드래그로 그려지고 있는 선의 총거리 정보를 표시하거
// 마우스 오른쪽 클릭으로 선 그리가 종료됐을 때 선의 정보를 표시하는 커스텀 오버레이를 생성하고 지도에 표시하는 함수입니다
function showDistance(content, position) {
  if (distanceOverlay) {
    // 커스텀오버레이가 생성된 상태이면
    // 커스텀 오버레이의 위치와 표시할 내용을 설정합니다
    distanceOverlay.setPosition(position);
    distanceOverlay.setContent(content);
  } else {
    // 커스텀 오버레이가 생성되지 않은 상태이면

    // 커스텀 오버레이를 생성하고 지도에 표시합니다
    distanceOverlay = new kakao.maps.CustomOverlay({
      map: map, // 커스텀오버레이를 표시할 지도입니다
      content: content, // 커스텀오버레이에 표시할 내용입니다
      position: position, // 커스텀오버레이를 표시할 위치입니다.
      xAnchor: 0,
      yAnchor: 0,
      zIndex: 3,
    });
  }
}

function deleteCircleDot() {
  var i;

  for (i = 0; i < dots.length; i++) {
    if (dots[i].circle) {
      dots[i].circle.setMap(null);
    }

    if (dots[i].distance) {
      dots[i].distance.setMap(null);
    }
  }

  dots = [];
}

// 클릭으로 그려진 선을 지도에서 제거하는 함수입니다
function deleteClickLine() {
  if (clickLine) {
    clickLine.setMap(null);
    clickLine = null;
  }
}

// 그려지고 있는 선의 총거리 정보와
// 선 그리가 종료됐을 때 선의 정보를 표시하는 커스텀 오버레이를 삭제하는 함수입니다
function deleteDistnce() {
  if (distanceOverlay) {
    distanceOverlay.setMap(null);
    distanceOverlay = null;
  }
}

// 마우스 우클릭 하여 선 그리기가 종료됐을 때 호출하여
// 그려진 선의 총거리 정보와 거리에 대한 도보, 자전거 시간을 계산하여
// HTML Content를 만들어 리턴하는 함수입니다
function getTimeHTML(distance) {
  // 도보의 시속은 평균 4km/h 이고 도보의 분속은 67m/min입니다
  var walkkTime = (distance / 67) | 0;
  var walkHour = '',
    walkMin = '';

  // 계산한 도보 시간이 60분 보다 크면 시간으로 표시합니다
  if (walkkTime > 60) {
    walkHour = '<span class="number">' + Math.floor(walkkTime / 60) + '</span>시간 ';
  }
  walkMin = '<span class="number">' + (walkkTime % 60) + '</span>분';

  // 자전거의 평균 시속은 16km/h 이고 이것을 기준으로 자전거의 분속은 267m/min입니다
  var bycicleTime = (distance / 227) | 0;
  var bycicleHour = '',
    bycicleMin = '';

  // 계산한 자전거 시간이 60분 보다 크면 시간으로 표출합니다
  if (bycicleTime > 60) {
    bycicleHour = '<span class="number">' + Math.floor(bycicleTime / 60) + '</span>시간 ';
  }
  bycicleMin = '<span class="number">' + (bycicleTime % 60) + '</span>분';

  // 거리와 도보 시간, 자전거 시간을 가지고 HTML Content를 만들어 리턴합니다
  var content2 = '<ul class="dotOverlay distanceInfo">';
  content2 += '    <li>';
  content2 += '        <span class="label">총거리</span><span class="number">' + distance + '</span>m';
  content2 += '    </li>';
  content2 += '    <li>';
  content2 += '        <span class="label">도보</span>' + walkHour + walkMin;
  content2 += '    </li>';
  content2 += '    <li>';
  content2 += '        <span class="label">자전거</span>' + bycicleHour + bycicleMin;
  content2 += '    </li>';
  content2 += '</ul>';

  return content2;
}
