package kr.or.ddit.board.main;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.IJdbcBoardService;
import kr.or.ddit.board.service.JdbcBoardServiceImpl;
import kr.or.ddit.vo.JdbcBoardVO;



public class JdbcBoardController {
	private IJdbcBoardService boardService;
	private Scanner scan;
	
	public JdbcBoardController() {
		boardService = JdbcBoardServiceImpl.getInstance();
		scan = new Scanner(System.in);
	}
	
	public static void main(String[] args) {
		new JdbcBoardController().boardStart();
	}
	
	public void boardStart() {
		String jBoardTitle = null;
		int choice = -1;
		while(true) {
			if(choice!=3) {
				jBoardTitle = null;
			}
			
			choice = displayMenu(jBoardTitle);
			
			switch(choice){
				case 1 :  // 새글 작성
					insertBoard();
					break;
				case 2 :  // 게시글 보기
					viewBoard();
					break;
				case 3 :  // 게시글 검색
					jBoardTitle = searchBoard();
					break;
				case 0 :  // 작업 끝
					System.out.println("게시판 프로그램 종료....");
					return;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}
	}
	

	// 게시판 목록을 보여주고 메뉴를 나타내며 사용자가 입력한 메뉴 번호를 반환하는 메서드
	public int displayMenu(String jBoardTitle) {
		System.out.println();
		System.out.println("----------------------------------------------------");
		System.out.println("  No	    제 목            작성자     	조회수");
		System.out.println("----------------------------------------------------");
		
		List<JdbcBoardVO> boardList = boardService.getSearchBoardList(jBoardTitle);
		
		if(boardList==null || boardList.size()==0){
			System.out.println(" 출력할 게시글이 하나도 없습니다.");
		}else{
			for(JdbcBoardVO jboardVo : boardList){
				System.out.println(jboardVo.getBoard_no() + "	"
						+ jboardVo.getBoard_title() + "	"
						+ jboardVo.getBoard_writer() + "	"
						+ jboardVo.getBoard_cnt());
			}
		}
		System.out.println("----------------------------------------------------");
		System.out.println("메뉴 : 1. 새글작성     2. 게시글보기    3. 검색    0. 작업끝");
		System.out.print("작업선택 >> ");
		int num = scan.nextInt();
		return num;

	}
	
	// 새글을 작성하는 메서드
	public void insertBoard() {
		System.out.println();
		scan.nextLine();
		
		System.out.println("    새글 작성하기");
		System.out.println("----------------------------------------------------");
		System.out.print("- 제  목 : ");
		String boardTitle = scan.nextLine();
		
		System.out.print("- 작성자 : ");
		String boardwriter = scan.next();
		
		scan.nextLine(); // 입력 버퍼 비우기
		System.out.print("- 내  용 : ");
		String boardContent = scan.nextLine();
		
		// 입력받은 정보를 VO객체에 넣는다.
		JdbcBoardVO jBoardVo = new JdbcBoardVO();
		
		jBoardVo.setBoard_title(boardTitle);
		jBoardVo.setBoard_writer(boardwriter);
		jBoardVo.setBoard_content(boardContent);
		
		
		int cnt = boardService.insertBoard(jBoardVo);
		
		if(cnt>0){
			System.out.println("새글이 추가되었습니다.");
		}else{
			System.out.println("새글 추가 실패!!");
		}
	}
	
	
	// 게시글 내용을 보여주는 메서드
	public void viewBoard() {
		System.out.println();
		System.out.print("보기를 원하는 게시물 번호 입력 >> ");
		int boardNo = scan.nextInt();
		
		JdbcBoardVO jBoardVo = boardService.getBoard(boardNo);
		
		if(jBoardVo==null) {
			System.out.println(boardNo + "번의 게시글이 존재하지 않습니다.");
			return;
		}
		
		System.out.println();
		System.out.println(boardNo + "번글 내용");
		System.out.println("------------------------------------------------------------");
		System.out.println(" - 제  목 : " + jBoardVo.getBoard_title());
		System.out.println(" - 작성자 : " + jBoardVo.getBoard_writer());
		System.out.println(" - 내  용 : " + jBoardVo.getBoard_content());
		System.out.println(" - 작성일 : " + jBoardVo.getBoard_date());
		System.out.println(" - 조회수 : " + jBoardVo.getBoard_cnt());
		System.out.println("------------------------------------------------------------");
		System.out.println(" 메뉴 : 1. 수정    2. 삭제    3. 리스트로 가기 ");
		System.out.print(" 작업선택 >> ");
		int choice = scan.nextInt();
		
		switch(choice){
			case 1 :  // 게시글 수정
				updateBoard(boardNo);
				break;
			case 2 :  // 게시글 삭제
				deleteBoard(boardNo);
				break;
			case 3 :  // 리스트로 가기
				return;
			
		}
	}
	
	// 게시글 검색하고 검색한 결과를 반환하는 메서드
	public String searchBoard(){
		System.out.println();
		scan.nextLine();
		System.out.println("검색 작업");
		System.out.println("--------------------------------------------");
		System.out.print("- 검색할 제목 입력 : ");
		String jBoardTitle = scan.nextLine();
		
		return jBoardTitle;
	}
	
	
	// 게시글을 수정하는 메서드
	public void updateBoard(int boardNo) {
		System.out.println();
		System.out.println("수정 작업하기");
		System.out.println("-----------------------------------");
		scan.nextLine();
		System.out.print("- 제  목 : ");
		String jBoardTitle = scan.nextLine();
		
		System.out.print("- 내  용 : ");
		String jBoardContent = scan.nextLine();
		
		JdbcBoardVO jBoardVo = new JdbcBoardVO();
		jBoardVo.setBoard_title(jBoardTitle);
		jBoardVo.setBoard_content(jBoardContent);
		jBoardVo.setBoard_no(boardNo);
		
		int cnt = boardService.updateBoard(jBoardVo);
		if(cnt>0) {
			System.out.println(boardNo + "번글이 수정되었습니다.");
		}else {
			System.out.println(boardNo + "번글 수정 실패!!");
		}
	}
	
	// 게시글을 삭제하는 메서드
	public void deleteBoard(int boardNo) {
		int cnt = boardService.deleteBoard(boardNo);
		if(cnt>0) {
			System.out.println(boardNo + "번글이 삭제되었습니다.");
		}else {
			System.out.println(boardNo + "번글이 삭제 실패!!");
		}
	}
}












