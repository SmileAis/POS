package system.pos.item;

public class ItemRegisterService {
	private ItemDao itemDao;

	public ItemRegisterService(ItemDao itemDao) {
		this.itemDao = itemDao;
	}
	
	public ItemRegisterService() {}

	public void regist(ItemRegisterRequest req) {
		Item newItem = new Item(req.getCode(), req.getName(), req.getPrice());
		itemDao.insert(newItem);
	}
	
	public void insertSoldItem(ItemRegisterRequest req, int count) {
		Item newItem = new Item(req.getCode(), req.getName(), req.getPrice());
		itemDao.insertSoldItem(newItem, count);
	}
	
	public void delete(ItemRegisterRequest req) {
		Item item = itemDao.selectByCode(req.getCode());
		itemDao.delete(item);
	}
}
