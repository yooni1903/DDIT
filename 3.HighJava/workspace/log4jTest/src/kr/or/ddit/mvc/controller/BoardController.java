package kr.or.ddit.mvc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.mvc.service.BoardServiceImpl;
import kr.or.ddit.mvc.service.IBoardService;
import kr.or.ddit.mvc.vo.BoardVO;

public class BoardController {
	private Scanner scan = new Scanner(System.in);
	
	private IBoardService service;
	private boolean choicechk;
	private int choice;
	public BoardController() {
		service = new BoardServiceImpl();
	}
	
	public static void main(String[] args) {
		new BoardController().boardStart();
	}

	private void boardStart() {
		
		while(true) {
			if(!choicechk) choice = displayMenu();
			
			switch(choice) {
			case 1: 
				insertBoard();
				choicechk = false;
				break;
			case 2: 
				viewBoard();
				choicechk = false;
				break;
			case 3: 
				choice = search();
				choicechk = true;
				break;
			case 0: 
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			default:
				System.out.println();
				choicechk = false;
				break;
			}
		}
		
	}

	private int search() {
		System.out.println();
		System.out.println("검색 작업");
		scan.nextLine();
		System.out.println("-----------------------------------------");
		System.out.print("검색할 제목 입력 : ");
		String input = scan.nextLine();
		if(input == "" || input.trim().length() == 0) {
			System.out.println("아무것도 입력하지 않았습니다.");
			return 111;
		}
		
		System.out.println("-----------------------------------------");
		System.out.println("No\t제 목\t작성자\t조회수");
		System.out.println("-----------------------------------------");
		List<BoardVO> boardList = service.searchBoard(input);
		if(boardList == null || boardList.size() == 0) {
			System.out.println("검색어와 일치하는 게시글이 없습니다.");
		}else {
			for(BoardVO b : boardList) {
				System.out.print(b.getBoard_no() + "\t");
				System.out.print(b.getTitle() + "\t");
				System.out.print(b.getWriter() + "\t");
				System.out.println(b.getCnt());
			}
		}
		System.out.println();
		System.out.println("메뉴 : 1.새글작성 2.게시글보기");
		System.out.println("       3.검색     0.작업 끝");
		System.out.print("작업 선택 >> ");
		int num = scan.nextInt();
		
		return num;
		
	}

	private void viewBoard() {
		System.out.println();
		System.out.println("조회할 게시물 번호를 입력하세요");
		int no = scan.nextInt();
		BoardVO bv = service.viewBoard(no);
		if(bv.getDate() == null) {
			System.out.println(no + "번에 해당되는 게시글이 존재하지 않습니다.");
			return;
		}
		
		System.out.println(no + "번글 내용");
		System.out.println("----------------------------");
		System.out.println("- 제 목 : " + bv.getTitle());
		System.out.println("- 작성자 : " + bv.getWriter());
		System.out.println("- 내 용 : " + bv.getContent());
		System.out.println("- 작성일 : " + bv.getDate());
		System.out.println("- 조회수 : " + bv.getCnt());
		System.out.println("-----------------------------");
		System.out.println("메뉴 : 1.수정   2.삭제   3.리스트로 가기");
		System.out.print("작업선택 >> ");
		int num = scan.nextInt();
		
		switch(num) {
		case 1:
			updateBoard(no);
			break;
		case 2:
			deleteBoard(no);
			break;
		case 3:
			return;
		}
	}

	private void deleteBoard(int no) {
		System.out.println();
		System.out.println(no + "번 게시글을 정말 삭제하시겠습니까? (y/n)");
		String input = scan.next();
		if(input.equals("y") || input.equals("Y")) {
			int cnt = service.deleteBoard(no);
			if(cnt > 0) {
				System.out.println(no + "번글이 삭제되었습니다.");
			}else {
				System.out.println(no + "번글 삭제에 실패했습니다.");
			}
		}else {
			System.out.println(no+ "번글 삭제를 취소합니다.");
		}
	}

	private void updateBoard(int no) {
		System.out.println("수정 작업하기");
		scan.nextLine();
		System.out.println("----------------");
		System.out.print("- 제 목 : ");
		String title = scan.nextLine();
		System.out.print("- 내 용 : ");
		String content = scan.nextLine();
		
		System.out.println("제목 : " + title);
		System.out.println("내용 : " + content);
		BoardVO bv = new BoardVO();
		bv.setTitle(title);
		bv.setContent(content);
		bv.setBoard_no(no);
		
		int cnt = service.updateBoard(bv);
		if(cnt > 0) {
			System.out.println(no + "번글이 수정되었습니다.");
		}else {
			System.out.println(no + "번글 수정에 실패했습니다.");
		}
	}

	private void insertBoard() {
		System.out.println();
		scan.nextLine();
		System.out.println("새글 작성하기");
		System.out.println("-------------------------");
		System.out.print("제 목 : ");
		String title = scan.nextLine();
		System.out.print("작성자 : ");
		String writer = scan.next();
		scan.nextLine();
		System.out.print("내 용 : ");
		String content = scan.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setTitle(title);
		bv.setWriter(writer);
		bv.setContent(content);
		
		int cnt = service.insertBoard(bv);
		if(cnt > 0) {
			System.out.println("게시글 등록 성공!");
		}else {
			System.out.println("게시글 등록 실패ㅠ");
		}
	}

	private int displayMenu() {
		System.out.println();
		System.out.println("---------------------------------------");
		System.out.println("No\t제 목\t작성자\t조회수");
		System.out.println("---------------------------------------");
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		boardList = service.boardList();
		if(boardList == null || boardList.size() == 0) {
			System.out.println("등록된 게시글이 없습니다.");
		}else {
			for(BoardVO b : boardList) {
				System.out.print(b.getBoard_no() + "\t");
				System.out.print(b.getTitle() + "\t");
				System.out.print(b.getWriter() + "\t");
				System.out.println(b.getCnt());
			}
		}
		System.out.println();
		System.out.println("메뉴 : 1.새글작성 2.게시글보기");
		System.out.println("       3.검색     0.작업 끝");
		System.out.print("작업 선택 >> ");
		int num = scan.nextInt();
		
		return num;
	}

}
