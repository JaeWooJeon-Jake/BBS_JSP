 function checkid(){
	var userid = document.getElementById("userid").value;
	if(userid) {	
		url = "/BBS_JSP/member/id_check.jsp?userid="+userid;
		window.open(url,"chkid","width=300,height=150, left=800, top=300");
		return;
	} else if(!userid) {
			alert("아이디를 입력하세요");
	} else if((userid < "0" || userid > "9") && (userid < "A" || userid > "Z") && (userid < "a" || userid > "z")) {
			alert("한글 및 특수문자는 아이디로 사용하실 수 없습니다.");
			
		}
 	}  //팝업을 이용한 아이디 중복 
   
