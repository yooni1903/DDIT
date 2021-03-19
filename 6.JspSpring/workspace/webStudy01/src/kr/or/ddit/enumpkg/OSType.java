package kr.or.ddit.enumpkg;

public enum OSType {
	ANDROID("안드로이드"), MAC("ios"), OTHER("기타등등");
	OSType(String osName){
		this.osName = osName;
	}
	private String osName;
	
	public String getOSName() {
		return this.osName;
	}
	
	public static String getOSName(String agent) {
		agent = agent.toUpperCase();
		
		OSType searched = OTHER;
		for(OSType tmp : values()) {
			if(agent.contains(tmp.name())) {
				searched = tmp;
				break;
			}
		}
		
		String name = searched.getOSName();
		return name;
	}

}
