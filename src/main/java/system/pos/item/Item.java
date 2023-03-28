package system.pos.item;

public class Item {
	private String code;		// 상품 코드
	private String name;		// 상품명
	private int price;		// 상품 가격
	private int stock;		// 재고
	private String receiveDate;	// 입고 날짜
	
	public Item(String code, String name, int price) {
		this.code = code;
		this.name = name;
		this.price = price;
	}
	public Item(String code, String name, int price, int stock) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.stock = stock;
	}

	public Item(String code, String name, int price, int stock, String date) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.receiveDate = date;
	}
	public String getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public int getStock() {
		return stock;
	}
	
	public String getReceiveDate() {
		return receiveDate;
	}
}
