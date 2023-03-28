package system.pos.item;

public class ItemRegisterService {
	private ItemDao itemDao;

	public ItemRegisterService(ItemDao itemDao) {
		this.itemDao = itemDao;
	}
	
	public ItemRegisterService() {}

	/**
	 * 상품 등록
	 */
	public void regist(ItemRegisterRequest req) {
		Item newItem = new Item(req.getCode(), req.getName(), req.getPrice());
		itemDao.insert(newItem);
	}

	/**
	 * 판매 상품 기록
	 */
	public void insertSoldItem(ItemRegisterRequest req, int count) {
		Item newItem = new Item(req.getCode(), req.getName(), req.getPrice());
		itemDao.insertSoldItem(newItem, count);
	}

	/**
	 * 상품 삭제
	 */
	public void delete(ItemRegisterRequest req) {
		Item item = itemDao.selectByCode(req.getCode());
		itemDao.delete(item);
	}
}
