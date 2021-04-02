/**
 * 
 */
currentPage = 1;

// 댓글 수정하기
updateReply = function(){
	$.ajax({
		url : '/board/UpdateReply.do',
		type: 'post',
		data : reply, // reply객체 - cont, renum이 저장
		dataType : 'json'
	});
};



//열려있는 수정폼 닫기
replyReset = function(){
	$('#modifyForm').parents('.rep').find('#rmodi').prop('disabled', false);
	spancont = $('#modifyForm').parent();	// 원래 댓글 내용이 있었던 span태그
	// 수정폼을 다시 body태그로 다시 잘라서 붙여넣기 한다. (append한다)
	$('body').append($('#modifyForm'));
	$('#modifyForm').hide();
	
	// modifycont - 원래 있던 내용(원래 span에 있었던 글내용)
	// 원래 내용을 가져와서 수정Form에 출력하기 위해서 <br>태그를 \n으로 바꿨었으니까
	// 다시 <span>태그로 출력하기 위해서 \n을 <br>태그로 바꿈
	modifycont = modifycont.replace(/\n/g, '<br>');
	// 그리고 바꾼 modifyCont를 원래 span위치에 찍어준다.
	$(spancont).append(modifycont);
	
};


// 댓글 삭제
deleteReply = function(but){
	$.ajax({
		url: '/board/DeleteReply.do',
		data: {"renum": vidx},
		dataType: 'json',
		success : function(res){
//			alert(res.sw);
			
			$(but).parents('.rep').remove();
		},
		error:function(xhr){
			alert("상태 : " + xhr.status);
		}
		
	});
};


replySaveServer = function(but){
	
	$.ajax({
		url : '/board/InsertReply.do',
		type : 'post',
		data : board,		// board객체 - bonum, name, cont
		dataType : 'json',
		success : function(res){
//			alert(res.sw);
			
			// 댓글 등록 후 출력
			replyListServer(but);
		},
		error: function(xhr){
			alert("상태 : " + xhr.status);
		}
		
	});
	
};


// 등록버튼 클릭, 제목 클릭할 때 동작하도록 함
replyListServer = function(but){
	
	$.ajax({
		url: '/board/ListReply.do',
		data: {'bonum' : vidx},
		dataType: 'json',
		success: function(res){
//			alert("성공");
			recode = "";
			$.each(res, function(i, v){
				recode += '<div class="panel-body rep">';
                recode += '<p class="p1">';
                
                // 수정할 때 이미 저장되어 있는 내용을 가져오려고 할 때엔 text()로 가져오면 된다 (태그가 없기 때문에)
                recode += v.name + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
                recode += v.redate + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
                recode += '<br><br><span class="cont">' + v.cont + '</span>';
                recode += '</p>';

                recode += '<p class="p2">';
                recode += '<button id="rmodi" idx="' + v.renum + '" type="button" name="r_modify" class="action">댓글 수정</button>';
                recode += '<button idx="' + v.renum + '" type="button" name="r_delete" class="action">댓글 삭제</button>';
                recode += '</p>';
                recode += '</div>';
			});
			
			$(but).parents('.panel').find('.rep').remove();				// but의 최초로 만나는 부모 panel로 올라가 그 안의 rep를 찾아 remove한다.
			$(but).parents('.panel').find('.pbody').append(recode);		// but의 최조로 만나는 부모 panel로 올라가 그 안의 pbody를 찾아서 recode를 append한다.
			
		},
		error: function(xhr){
			alert("상태 : " + xhr.status);
		}
	});
};


readHitServer = function(list){
	
	$.ajax({
		url : "/board/UpdateHit.do",
		data : {"num" : vidx},
		success : function(res){
//			alert(res.sw);
			parent = $(list).parents(".panel");
			hit = parseInt($(parent).find(".wh").text());
			
			// 
			$(parent).find('.wh').text(++hit);
		},
		error : function(xhr){
			
		},
		dataType : 'json'
	});
	
};



updateBoard = function(){
	$.ajax({
		url : '/board/UpdateBoard.do',
		data : $('#mform').serializeJSON(),
		method : 'post',
		dataType: 'json',
		success: function(res){
//			alert(res.sw);
			
			console.log($('#mform #subject').val())
			
			// 화면에서 수정한 값 출력
			// 제목
			$(parent).find('a').text($('#mform #subject').val());
			// 메일
			$(parent).find('.wm').text($('#mform #mail').val());
			// 내용
			cont = $('#mform #content').val();
			cont = cont.replace(/\r/g, "").replace(/\n/g, "<br>");
			
			$(parent).find(".wc").html(cont);
			
			// modal창 닫기
			$('#modiModal').modal("hide");
			// modal창에서 쓴 값 지우기
			$('#mform .txt').val("");
		},
		error: function(xhr){
			alert("상태  : " + xhr.status);
		}
		
	});
};


deleteBoard = function(btn){
	
	$.get(
			'/board/DeleteBoard.do',	
			{"num" : vidx},
			function(res){
//				alert(res.sw);
				// 화면에서 리스트 삭제
//				$(btn).parents('.panel').remove();	
				/* readPageServer(1)를 안 쓰는 이유 
				   -> readPageServer(1)는 DB에서 새로 불러오는 것이기 때문에 DB에 부담을 주게 된다. 
				      DB에서 한 번 불러온 상태에서 삭제한 게시글의 요소를 화면에서 지워주는 것이 효율적! */
				/*
				 	인줄 알았지만 요소를 삭제하면 5개를 보여줬던 페이지가 4개만 보여주는 오류가 생겨서 
				 	다시 불러오는게 좋다..
				 */
				readPageServer(currentPage);
			},
			'json'
	);
};


insertBoard = function(){
	$.post(
			'/board/Insert.do',
			$('#wform').serializeJSON(),
			function(res){
//				alert(res.sw);
                $('#writeModal').modal('hide');     // modal창 닫고
                $('.txt').val("");                  // 입력한 내용 지우고
                readPageServer(1);					// 등록한 글까지 보이도록 페이지를 새로 부른다.
			},
			'json'
	);
};

readPageServer = function(cpage){
	$.ajax({
		url : '/board/List.do',
	    type : 'post',
	    data : {'cpage' : cpage},
	    success : function(res){
	    	code = "<div class=\"panel-group\" id=\"accordion\">";      /*큰 따옴표가 여러 개라서 인식에 힘들다면 \를 넣어주면 해결할 수 있다.*/
            $.each(res.datas, function(i, v){
                code += '<div class="panel panel-default">';
                code += '<div class="panel-heading">';
                code += '<h4 class="panel-title">';
                code += '<a idx="' + v.num + '" name="list" class="action" data-toggle="collapse" data-parent="#accordion" href="#collapse' + v.num + '">' + v.subject +'</a>';
                code += '</h4>';
                code += '</div>';
                code += '<div id="collapse' + v.num  +'"  class="panel-collapse collapse">';
                code += '<div class="panel-body pbody">';
                code += '<p class="p1">';
                
                // 수정할 때 이미 저장되어 있는 내용을 가져오려고 할 때엔 text()로 가져오면 된다 (태그가 없기 때문에)
                code += '작성자 : <span class="wr">' + v.writer + '</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
                code += '이메일 : <span class="wm">' + v.mail + '</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
                code += '날짜 : <span class="wd">' + v.date + '</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
                code += '조회수 : <span class="wh">' + v.hit + '</span>';
                code += '</p>';

                code += '<p class="p2">';
                code += '<button idx="' + v.num + '" type="button" name="modify" class="action">수정</button>';
                code += '<button idx="' + v.num + '" type="button" name="delete" class="action">삭제</button>';
                code += '</p>';

                code += '<p class="p3">';
                code += '<span class="wc">' + v.cont + '</span>';
                code += '</p>';

                code += '<p class="p4">';
                code += '<textarea cols="60"></textarea>';
                code += '<button idx="' + v.num + '" type="button" name="reply" class="action">등록</button>';
                code += '</p>';
                code += '</div>';
                code += '</div>';
                code += '</div>';
            })
            code += '</div>';
            
            $('.box').html(code);
            
            // 이전 버튼 출력 - startp가 1보다 클 때만 있음
            $('#pagelist').empty();
            if(res.startp > 1){
            	pager = '<ul class="pul pager">';
            	pager += '<li><a class="prev" href="#">Previous</a></li>';
            	pager += '</ul>';
            	$('#pagelist').append(pager);
            }
            
            // 페이지 번호 출력
            pager = '<ul class="pul pagination pager">';
            for(i = res.startp; i <= res.endp; i++){
            	if(currentPage == i){
            		pager += '<li class="active"><a class ="paging" href="#">' + i + '</a></li>';
            	}else{
            		pager += '<li><a class ="paging" href="#">' + i + '</a></li>';
            	}
            }
            pager += '</ul>';
            $('#pagelist').append(pager);
            
            // 다음 버튼 출력 - endp가 totalp보다 작을 때만
            if(res.endp < res.totalp){
            	pager = '<ul class="pager">';
            	pager += '<li class="next"><a href="#">Next</a></li>';
            	pager += '</ul>';idx="11" 
            	$('#pagelist').append(pager);
            }
            
           
	    },
	    error : function(xhr){
	        alert("상태 : " + xhr.status);
	    },
	    dataType : 'json'
	})
	
}



readServer = function(){
    $.ajax({
        url : '/board/List.do',
        type : 'get',
        success : function(res){
//            code = '<div class="panel-group" id="accordion">';
            code = "<div class=\"panel-group\" id=\"accordion\">";      /*큰 따옴표가 여러 개라서 인식에 힘들다면 \를 넣어주면 해결할 수 있다.*/
            $.each(res, function(i, v){
                code += '<div class="panel panel-default">';
                code += '<div class="panel-heading">';
                code += '<h4 class="panel-title">';
                code += '<a data-toggle="collapse" data-parent="#accordion" href="#collapse' + v.num + '">' + v.subject +'</a>';
                code += '</h4>';
                code += '</div>';
                code += '<div id="collapse' + v.num  +'"  class="panel-collapse collapse">';
                code += '<div class="panel-body">';
                code += '<p class="p1">';
                code += '작성자 : ' + v.writer + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
                code += '이메일 : ' + v.mail + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
                code += '날짜 : ' + v.date + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
                code += '조회수 : ' + 0;
                code += '</p>';

                code += '<p class="p2">';
                code += '<button type="button" name="modify" class="action">수정</button>';
                code += '<button type="button" name="delete" class="action">삭제</button>';
                code += '</p>';

                code += '<p class="p3">';
                code += v.cont;
                code += '</p>';

                code += '<p class="p4">';
                code += '<textarea cols="60"></textarea>';
                code += '<button type="button" name="reply" class="action">등록</button>';
                code += '</p>';
                code += '</div>';
                code += '</div>';
                code += '</div>';
            })
            code += '</div>';
            
            $('.box').html(code);
        },
        error: function(xhr){
            alert("상태 : " + xhr.status);
        },
        dataType : 'json'
    })
}