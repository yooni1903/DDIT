package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class HotelTest {
	
	HashMap<Integer, Hotel> hotelMap;
	
	public HotelTest() {
		hotelMap = new HashMap<>();
		hotelMap.put(201, new Hotel(201, "싱글룸  ", "-"));
		hotelMap.put(202, new Hotel(202, "싱글룸  ", "-"));
		hotelMap.put(203, new Hotel(203, "싱글룸  ", "-"));
		hotelMap.put(204, new Hotel(204, "싱글룸  ", "-"));
		hotelMap.put(205, new Hotel(205, "싱글룸  ", "-"));
		hotelMap.put(206, new Hotel(206, "싱글룸  ", "-"));
		hotelMap.put(207, new Hotel(207, "싱글룸  ", "-"));
		hotelMap.put(208, new Hotel(208, "싱글룸  ", "-"));
		hotelMap.put(209, new Hotel(209, "싱글룸  ", "-"));
		
		hotelMap.put(301, new Hotel(301, "더블룸  ", "-"));
		hotelMap.put(302, new Hotel(302, "더블룸  ", "-"));
		hotelMap.put(303, new Hotel(303, "더블룸  ", "-"));
		hotelMap.put(304, new Hotel(304, "더블룸  ", "-"));
		hotelMap.put(305, new Hotel(305, "더블룸  ", "-"));
		hotelMap.put(306, new Hotel(306, "더블룸  ", "-"));
		hotelMap.put(307, new Hotel(307, "더블룸  ", "-"));
		hotelMap.put(308, new Hotel(308, "더블룸  ", "-"));
		hotelMap.put(309, new Hotel(309, "더블룸  ", "-"));
		
		hotelMap.put(401, new Hotel(401, "스위트룸", "-"));
		hotelMap.put(402, new Hotel(402, "스위트룸", "-"));
		hotelMap.put(403, new Hotel(403, "스위트룸", "-"));
		hotelMap.put(404, new Hotel(404, "스위트룸", "-"));
		hotelMap.put(405, new Hotel(405, "스위트룸", "-"));
		hotelMap.put(406, new Hotel(406, "스위트룸", "-"));
		hotelMap.put(407, new Hotel(407, "스위트룸", "-"));
		hotelMap.put(408, new Hotel(408, "스위트룸", "-"));
		hotelMap.put(409, new Hotel(409, "스위트룸", "-"));
		
		
	}

	public static void main(String[] args) {
		new HotelTest().hotelStart();
	}

	private void hotelStart() {
		System.out.println("*************************************************************");
		System.out.println("              호텔문을 열었습니다. 어서오십시요.");
		System.out.println("*************************************************************");
		System.out.println();
		
		while(true) {
			int choice = displayMenu();
			switch(choice) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				room();
				break;
			case 4:
				System.out.println("*************************************************************");
				System.out.println("                  호텔 문을 닫았습니다.");
				System.out.println("*************************************************************");
				return;
			default :
				System.out.println("작업 번호를 잘못 입력했습니다.");
				System.out.println("다시 입력하세요.");
			}
		}
	}

	private void room() {
		System.out.println();
		System.out.println("-------------------------------------------------------------");
		System.out.println("                      현재 객실 상태");
		System.out.println("-------------------------------------------------------------");
		System.out.println("방 번호\t\t방 종류  \t투숙객 이름");
		System.out.println("-------------------------------------------------------------");
		Set<Integer> keySet = hotelMap.keySet();
		List<Integer> keyList = new ArrayList<>(keySet);
		Collections.sort(keyList);
		for(int hotel : keyList) {
			Hotel h = hotelMap.get(hotel);
			String kind = h.getKind();
			String person = h.getPerson();
			System.out.println(hotel + "\t\t" + kind + "\t" + person);
		}
		
		System.out.println("-------------------------------------------------------------");
		System.out.println();
		
	}

	private void checkOut() {
		System.out.println("-------------------------------------------------------------");
		System.out.println("                        체크아웃 작업");
		System.out.println("-------------------------------------------------------------");
		System.out.println("               체크아웃할 방 번호를 입력하세요.");
		System.out.print("방번호 입력 >>");
		
		int roomNum = ScanUtil.nextInt();
		Hotel h = hotelMap.get(roomNum);
		
		if(!hotelMap.containsKey(roomNum)) {
			System.out.println(roomNum + "호는 존재하지 않습니다.");
			return;
		}
		if(h.getPerson().equals("-")) {
			System.out.println(roomNum + "호 객실에는 체크인 한 사람이 없습니다.");
			return;
		}
		
		hotelMap.put(roomNum, new Hotel(roomNum, h.getKind(), "-"));
		System.out.println(roomNum + "호 객실의 " +h.getPerson() + "님 체크아웃을 완료하였습니다.");
		
	}

	private void checkIn() {
		System.out.println();
		System.out.println("-------------------------------------------------------------");
		System.out.println("                        체크인 작업");
		System.out.println("-------------------------------------------------------------");
		System.out.println("                     * 201~209 : 싱글룸");
		System.out.println("                     * 301~309 : 더블룸");
		System.out.println("                     * 401~409 : 스위트룸");
		System.out.println("-------------------------------------------------------------");
		System.out.print("방 번호 입력 >>");
		int roomNum = ScanUtil.nextInt();
		
		Hotel h = hotelMap.get(roomNum);
		if(!hotelMap.containsKey(roomNum)) {
			System.out.println(roomNum + "호는 존재하지 않습니다.");
			return;
		}
		
		if(!h.getPerson().equals("-")) {
			System.out.println(roomNum + "호 객실은 이미 손님이 있습니다.");
			return;
		}
		
		System.out.print("이름 입력 >>");
		String name = ScanUtil.nextLine();
		hotelMap.put(roomNum, new Hotel(roomNum, h.getKind(), name));
		System.out.println("체크인이 완료되었습니다.");
		
		
	}

	private int displayMenu() {
		System.out.println();
		System.out.println("-------------------------------------------------------------");
		System.out.println("                 어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인\t2.체크아웃\t3.객실상태\t4.업무종료");
		System.out.println("-------------------------------------------------------------");
		System.out.print("선택 >>");
		int num = ScanUtil.nextInt();
		
		return num;
	}

}


class Hotel implements Comparable<Hotel>{
	private int roomNum;
	private String kind;
	private String person;
	
	public Hotel(int roomNum, String kind, String person) {
		super();
		this.roomNum = roomNum;
		this.kind = kind;
		this.person = person;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}
	
	@Override
	public int compareTo(Hotel o) {
		return new Integer(this.roomNum).compareTo(o.getRoomNum());
	}

	
}