package system.pos.member;

public class Member {
	private String rank;	// 직급
	private String name;	// 이름
	private String id;		// id
	private String password;	//password
	private String regDate;	//등록일
	
	public Member(String rank, String name, String id, String pass, String regDate) {
		this.rank = rank;
		this.name = name;
		this.id = id;
		this.password = pass;
		this.regDate = regDate;
	}

	public String getRank() {
		return rank;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getRegDate() {
		return regDate;
	}
}

