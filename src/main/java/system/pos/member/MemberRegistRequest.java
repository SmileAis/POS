package system.pos.member;

public class MemberRegistRequest {
	private String rank;	// 직급
	private String name;	// 이름
	private String id;		// id
	private String password;	//password
	private String registerDate;	//등록일
	
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getRank() {
		return rank;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getRegisterDate() {
		return registerDate;
	}
}
