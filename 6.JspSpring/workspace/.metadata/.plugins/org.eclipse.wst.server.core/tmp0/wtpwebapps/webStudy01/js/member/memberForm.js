/**
 * 
 */
// Window가 가진 함수
// function generateMessage(message){}

// JQuery가 가진 함수
$.generateMessage = function(message){
	let messageTag = $("<span>")
	.text(message?message:"")
	.addClass("message")
	.addClass("error");
	return messageTag;
}

// blur 입력 태그 안에서 값을 쓰고 포커스를 떠날 때를 포착한다.
let idTag = $("[name='mem_id']").on("change", function(){
	idCheckBtn.trigger("click");
});
let idCheckBtn = $("#idCheck").on("click", function(){
	// 남아잇는 OK 정보로 인해 오작동 되는 경우를 방지하고자, 초기화시키는 작업
	memberForm.data("idcheck", "FAIL");
	idTag.next(".message:first").remove();
	// mem_id는 계속 바뀌는 값이기 때문에 위에서 미리 val을 받아놓으면 안된다.
	let mem_id = idTag.val();
// 		var idChk = $("#idChk");
// 		var mem_id = $("[name='mem_id']").val();
	
	$.ajax({
		url : "idCheck.do",	// 상대경로 , js파일에서는 get.ContextPath()를 사용하지 못하기 때문에 사용
		method : "post",
		data : {
			id : mem_id
		},
		dataType : "json",
		success : function(resp) {
			memberForm.data("idcheck", resp.result);
			if(resp.result != "OK"){
				let messageTag = $.generateMessage("아이디 중복")
				idTag.after(messageTag);
				idTag.focus();
// 					idChk.text(resp.result);
			}else{
// 					idChk.text(resp.result);
			}
		},
		error : function(xhr, error, msg) {
			console.log(xhr);
			console.log(error);
			console.log(msg);
		}

	});
});


let memberForm = $("#memberForm").on("submit", function(){
	let checked = $(this).data("idcheck") == "OK";	// true인지 false인지 return
	if(!checked){
		let messageTag = idTag.nex(".message:first");
		if(!messageTag || messageTag.length == 0){
			messageTag = $.generateMessage();
		}
		messageTag.text("중복체크 하세요!!!!!");
		
		idTag.after(messageTag);
	}
	return checked;
});