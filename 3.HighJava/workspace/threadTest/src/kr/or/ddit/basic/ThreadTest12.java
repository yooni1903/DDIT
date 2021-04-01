package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 	문제) 10마리의 말들이 경주하는 경마 프로그램을 작성하시오.
 	
 	말은 Horse라는 이름의 스레드 클래스로 작성하는데, 
 	이 클래스는 말 이름(String), 등수(int), 현재 위치 값(int)을 멤버변수로 갖는다.
 	그리고 이 클래스에는 등수를 오름차순으로 처리할 수 있는 내부 정렬 기준이 있다. (Comparable  인터페이스 구현)
 	
 	경기 구간은 1~50구간으로 되어있다.
 	
 	경기가 진행되는 중간 중간에 각각의 말들의 위치를 아래와 같이 나타내시오.
 	예시)
 	01번 말: -->---------------------------------------------------
 	02번 말: ----->------------------------------------------------
 	03번 말: ---->-------------------------------------------------
 	04번 말: ----------->------------------------------------------
 	05번 말: ->----------------------------------------------------
 	06번 말: ----->------------------------------------------------
 	
 	경기가 끝나면 등수 순으로 출력한다.
 	
 	
 */

public class ThreadTest12 {
	public static int fineRank = 1;

	public static void main(String[] args) {
		List<Horse> horses = new ArrayList<>();
		int horseNum = 0;
		
		for(int i = 1; i <=10; i++) {
			horseNum++;
			String hName = "";
			if(horseNum < 10) {
				hName = "0" + horseNum + "번 말";
			}else {
				hName = horseNum + "번 말";
			}
			
			horses.add(new Horse(hName));
		}
		
		for(Horse h : horses) {
			h.start();
		}
		
		for(Horse h : horses) {
			try {
				h.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		Collections.sort(horses);
		System.out.println();
		System.out.println("         == 경기 결과 == ");
		for(Horse h : horses) {
			System.out.println(h.getRank() + "등 " + h.getHorseName());
		}
	}

}


class Horse extends Thread implements Comparable<Horse>{
	private String horseName;
	private int rank;
	private int position;
	
	public Horse(String horseName) {
		this.horseName = horseName;
		this.position = 0;
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
	public int compareTo(Horse horse) {
		return new Integer(this.rank).compareTo(horse.getRank());
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 50; i++){
			System.out.print(horseName + " : ");
			position++;
			for(int j = 1; j <= 50; j++) {
				
				if(position != j){
					System.out.print("-");
				}else{
					System.out.print(">");
				}
			}
			try {
				Thread.sleep((int)(Math.random() * 500));
			} catch (InterruptedException e) {
			}
			System.out.println();
		}
		System.out.println();
		System.out.println(horseName + " 완주!");
		
		setRank(ThreadTest12.fineRank);
		ThreadTest12.fineRank++;
	}
}
