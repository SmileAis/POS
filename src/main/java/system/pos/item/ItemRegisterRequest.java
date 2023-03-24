package system.pos.item;

public class ItemRegisterRequest {
	private String code;
	private String name;
	private int price;
	private int stock;
	private String receiveDate;
	
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
