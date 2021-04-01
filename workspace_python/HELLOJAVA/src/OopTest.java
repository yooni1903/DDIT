
public class OopTest {
	public static void main(String[] args) {
//		Animal ani = new Animal();
//		System.out.println("getOlder 전 : " + ani.age);
//		ani.getOlder();
//		System.out.println("getOlder 후 : " + ani.age);
		
		Human hum = new Human();
		System.out.println("나이 : " + hum.age);
		System.out.println("flag : " + hum.flag_coding);
		hum.cutHand();
		System.out.println("손 자름 -> " + hum.flag_coding);
	}
}
