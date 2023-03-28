package system.pos.item;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StockChangeService {
	private ItemDao itemDao;
	
	public StockChangeService(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	/**
	 * 재고 수정 처리
	 */
	public void change(StockChangeRequest req, int stock) {
		Item item = itemDao.selectByCode(req.getCode());
		if(item == null) return;
		Item changeItem = new Item(item.getCode(), item.getName(), item.getPrice(), stock, item.getReceiveDate());
		itemDao.change(changeItem);
	}

	/**
	 * 입고 처리
	 */
	public void receive(StockChangeRequest req, int stock) {
		Item item = itemDao.selectByCode(req.getCode());
		if(item == null) return;
		Date d = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd hh:mm");
		Item receiveItem = new Item(item.getCode(), item.getName(), item.getPrice(), item.getStock()+stock, date.format(d));
		itemDao.receive(receiveItem);
		itemDao.receiveLog(receiveItem, stock);
	}

	/**
	 * 판매 처리
	 */
	public void sell(StockChangeRequest req, int count) {
		Item item = itemDao.selectByCode(req.getCode());
		if(item == null) {
			System.out.println("Can't find Item Code");
		}else {
			Item sellItem = new Item(item.getCode(), item.getName(), item.getPrice(), item.getStock()-count, item.getReceiveDate());
			itemDao.sell(sellItem);
		}
	}
	
}
