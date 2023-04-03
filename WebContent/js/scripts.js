window.onload = () => {
  let login = localStorage.getItem('login');
  if (login) {
    document.querySelector('#login').classList.add('d-none');
    document.querySelector('#logout').classList.remove('d-none');

    console.log('hello');
    // 마이페이지 로드
    userInfoShow();
  } else {
    document.querySelector('#logout').classList.add('d-none');
    document.querySelector('#login').classList.remove('d-none');
  }
};

const logout = () => {
  const person = localStorage.getItem('login');
  if (person) {
    localStorage.removeItem('login');
    console.log('로그아웃 되었습니다.');
  }
  window.location.reload();
};


const login = () => {
  const nickname = document.querySelector('#userName').value;
  const password = document.querySelector('#password').value;

  console.log(nickname + " " + password);
  if (nickname == null || password == null) {
	  alert("로그인에 실패하였습니다.");
	  return ;
  }
  console.log(root + "/user?action=login");
  
	
  location.href = root + "/user?action=login&userName="+nickname+"&password="+password;
 
};

const signupCheck = () => {
  const nickname = document.querySelector('#nickname').value;
  if (nickname === '') {
    document.querySelector('#signupCheck').innerHTML = '최소 6자에서 13자 이하를 입력해주세요';
    return;
  }
  
  fetch("http://localhost:8080/"+root+"/user?action=idCheck&nickname="+nickname).then(response => {
	    return response.json();
}).then(res=> {
		if (res.result == 0 ) {
	      document.querySelector('#signupCheck').innerHTML = '사용가능한 아이디입니다';

		}
		else {
	        document.querySelector('#signupCheck').innerHTML = '아이디가 중복되었습니다. 다시 입력해주세요';
		}
	}
)  
  
};



const signup = () => {
  const name = document.querySelector('#name').value;
  const nickname = document.querySelector('#nickname').value;
  const password = document.querySelector('#password').value;
  const password2 = document.querySelector('#passwordConfirm').value;

  const email = document.querySelector('#email').value;
  let emailSelect = document.querySelector('#inputGroupSelect01');
  let emailOption = emailSelect.options[emailSelect.selectedIndex].value;

  if (name === '') {
    alert('아이디를 다시 입력하세요');
    console.log('아이디를 다시 입력하세요');
    return;
  } 
  if (password === '') {
    alert('비밀번호를 다시 입력하세요');
    console.log('비밀번호를 다시 입력하세요');
    return;
  }
  if (password!=password2) {
	  alert('비밀번호가 같지 않습니다.');
	  console.log('비밀번호가 같지 않습니다.');
	  return;
  }

  
 location.href = root + "/user?action=signUp&name="+name+"&password="+password+"&nickname="+nickname+"&email="+email+"&emailOption="+emailOption;

};

const deleteFunc = (id) => {
	location.href = root + "/user?action=delete";
	console.log(id);
};

const modifyFunc = (id) => {
	console.log( root + "/user?action=mvModify")
	 location.href = root + "/user?action=mvModify";
};


const modifyFunc2 = (id) => {
	console.log( root + "/user?action=modify")
	
 const nickname = document.querySelector('#nickname').value;
  const password = document.querySelector('#password').value;
  const email = document.querySelector('#email').value;
  let emailSelect = document.querySelector('#inputGroupSelect01');
  let emailOption = emailSelect.options[emailSelect.selectedIndex].value;

  location.href = root + "/user?action=modify&password="+password+"&nickname="+nickname+"&email="+email+"&emailOption="+emailOption;
};


const mvPassword = ()=> {
	 location.href = root + "/user?action=mvPassword";

}
const findPassword = () => {
	 const nickname = document.querySelector('#nickname').value;
	 const name = document.querySelector('#name').value;
	 location.href = root + "/user?action=findPassword&nickname="+nickname+"&name="+name;

}