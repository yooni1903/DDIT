
public class OopTest {
	public static void main(String[] args) {
//		Animal ani = new Animal();
//		System.out.println("getOlder �� : " + ani.age);
//		ani.getOlder();
//		System.out.println("getOlder �� : " + ani.age);
		
		Human hum = new Human();
		System.out.println("���� : " + hum.age);
		System.out.println("flag : " + hum.flag_coding);
		hum.cutHand();
		System.out.println("�� �ڸ� -> " + hum.flag_coding);
	}
}
