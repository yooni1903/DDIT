package kr.or.ddit.basic;

import java.util.Arrays;

public class HorseTest {

	public static void main(String[] args) {
		HorseSem[] horses = new HorseSem[]{
				new HorseSem("01번 말"),
				new HorseSem("02번 말"),
				new HorseSem("03번 말"),
				new HorseSem("04번 말"),
				new HorseSem("05번 말"),
				new HorseSem("06번 말"),
				new HorseSem("07번 말"),
				new HorseSem("08번 말"),
				new HorseSem("09번 말"),
				new HorseSem("10번 말")
		};
		
		GameState gs = new GameState(horses);
		System.out.println("경기 시작!!!!!!!!");
		for(HorseSem h : horses){
			h.start();
		}
		
		gs.start();
		
		for(HorseSem h : horses){
			try {
				h.join();
			} catch (InterruptedException e) {
			}
		}
		
		try {
			gs.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println();
		System.out.println("경기 끝!!!!!!!!!!!");
		System.out.println();
		
		// 경기가 끝난 후 등수순으로 정렬한다.
		Arrays.sort(horses);
		
		System.out.println("경기 결과");
		for(HorseSem h : horses){
			System.out.println(h);
		}
		
		
	}

}


class HorseSem extends Thread implements Comparable<HorseSem>{
	public static int currentRank = 0;	// 말의 등수를 정해줄 때 필요한 변수
	
	private String horseName;			// 말이름
	private int rank;					// 등수
	private int position;				// 현재 위치
	
	public HorseSem(String horseName) {
		this.horseName = horseName;
		
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "경주마 " + horseName + "은(는) " + rank
				+ "등 입니다.";
	}

	// 등수의 오름차순하는 내부 정렬
	@Override
	public int compareTo(HorseSem h) {
		return Integer.compare(rank, h.getRank());
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 50; i++){
			this.position = i;	// 말의 현재 위치
			
			try {
				Thread.sleep((int)(Math.random() * 500));
			} catch (InterruptedException e) {
			}
		}	// for문
		
		// 한 마리의 말이 경주가 끝나면 currentRank값을 증가시킨다.
		// 이 값이 현재 경주가 끝난 말의 등수가 된다.
		currentRank++;
		this.rank = currentRank;
		
		
	}
	
}



// 경기 중 말의 현재 위치를 나타내는 쓰레드
class GameState extends Thread{
	// 경기를 진행 중인 말들이 저장될 배열의 변수를 선언
	private HorseSem[] horses; 		
	
	// 생성자
	public GameState(HorseSem[] horses){
		this.horses = horses;
	}
	
	
	/*
 	01번 말: -->---------------------------------------------------
 	02번 말: ----->------------------------------------------------
 	03번 말: ---->-------------------------------------------------
 	04번 말: ----------->------------------------------------------
 	05번 말: ->----------------------------------------------------
 	06번 말: ----->------------------------------------------------
 */
	@Override
	public void run() {
		while(true){
			// currentRank가 10이 될때까지 돌리기 (10등까지 정해졌다면 경기가 끝났다는 뜻이니까)
			if(HorseSem.currentRank == horses.length) break;
			
			for(int i = 0; i < 10; i++) System.out.println();
			
			for(int i = 0; i < horses.length; i++){
				System.out.print(horses[i].getHorseName() + " : ");
				
				for(int j = 1; j <= 50; j++){
					if(horses[i].getPosition() == j){
						System.out.print("╰（‵□′）╯");
					}else{
						System.out.print("-");
					}
				}
				System.out.println();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
	}
}


