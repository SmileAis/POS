package system.pos.item;

public class SellLog {
	private String num;		 // 번호
	private String code;	// 상품 코드
	private int count;		// 상품 수량
	private int totalPrice;	// 총 가격
	private String saleDate;	//판매 날짜
	

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
