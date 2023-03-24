package system.pos.item;

public class SellLog {
	private String num;
	private String code;
	private int count;
	private int totalPrice;
	private String saleDate;
	

	public SellLog(String num, String code, int count, int totalPrice, String saleDate) {
		this.num = num;
		this.code = code;
		this.count=count;
		this.totalPrice=totalPrice;
		this.saleDate = saleDate;
	}

	public String getNum() {
		return num;
	}
	
	public String getCode() {
		return code;
	}
	
	public int getCount() {
		return count;
	}
	
	public int getTotalPrice() {
		return totalPrice;
	}
	
	public String getSaleDate() {
		return saleDate;
	}
}
