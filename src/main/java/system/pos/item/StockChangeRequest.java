package system.pos.item;

public class StockChangeRequest {
	private String code;
	private int stock;
	private String receiveDate;
	
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getStock() {
		return stock;
	}
	public void setReceiveDate(String receiveDate) {
		this.receiveDate=receiveDate;
	}
	public String getReceiveDate() {
		return receiveDate;
	}
	
	
}
