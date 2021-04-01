package kr.or.ddit.enumpkg;

public enum BrowserType{
	EDG("엣지"), CHROME("크롬"), TRIDENT("익스플로러"), OTHER("기타등등");
	BrowserType(String browserName){
		this.browserName = browserName;
	}
	private String browserName;
	
	public String getBrowserName(){
		return this.browserName;
	}
	
	public static String getBrowserName(String agent) {
		agent = agent.toUpperCase();	//대문자로 바꿔주는 메서드
		// 오버로딩 메서드
		BrowserType searched = OTHER;
		for(BrowserType tmp : values()){
			if(agent.contains(tmp.name())){
				searched = tmp;
				break;
			}
		}
		String name = searched.getBrowserName();
		return name;
	}
	
}
