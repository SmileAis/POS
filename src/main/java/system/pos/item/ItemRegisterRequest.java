package system.pos.item;

public class ItemRegisterRequest {
	private String code;		// 상품 코드
	private String name;		// 상품명
	private int price;		// 가격
	private int stock;		// 재고
	private String receiveDate;		// 입고 날짜
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	public int getPrice() {
		return price;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getStock() {
		return stock;
	}
	
	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}
	public String getReceiveDate() {
		return receiveDate;
	}
}
