package kr.or.ddit.method;


/*
 * 값에 의한 호출, 참조에 의한 호출
 */
public class MethodInvocation {
	public static void main(String[] args) {
		String text1 = "original";
		StringBuffer text2 = new StringBuffer("original");
		
		callByValue(text1);
		
		System.out.println(text1);
		
		callByReference(text2);
		
		System.out.println(text2);
	}
	
	private static void callByValue(String data) {		// 파라미터로 리터럴(문자열)을 받고 있다
		data = data + "append data";
	}
	
	private static void callByReference(StringBuffer data) {	// 파라미터로 객체를 받고 있다.
		data.append("append data");
	}
}
