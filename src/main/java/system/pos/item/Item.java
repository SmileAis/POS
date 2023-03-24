package system.pos.item;

public class Item {
	private String code;
	private String name;
	private int price;
	private int stock;
	private String receiveDate;
	
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
