package system.pos.item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class ItemDao {

	private Map<String, Item> map = new HashMap<>();
	private NamedParameterJdbcTemplate jdbcTemplate;
	private JdbcTemplate template;
	
	private Date d = new Date();
	private SimpleDateFormat todayDate = new SimpleDateFormat("yyyy/MM/dd hh:mm");
	private String[] todayS = todayDate.format(d).split("/|\\s");
	private int nowDay = Integer.parseInt(todayS[2]);
	private int nowMonth = Integer.parseInt(todayS[1]);
	private int nowYear = Integer.parseInt(todayS[0]);
	
	public ItemDao(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.template = new JdbcTemplate(dataSource);
	}
	
	public List<Item> selectAll() {
		List<Item> results = jdbcTemplate.query("select * from items",
		(ResultSet rs, int rowNum) -> {
			Item item = new Item( rs.getString("CODE"), rs.getString("NAME"),
			rs.getInt("price"), rs.getInt("stock"), rs.getString("receiveDate"));
			return item;
		});
		return results;
	}
	
	public void receive(Item item) {
		String code = item.getCode();
		String sql = "update items set stock = ?, receiveDate = ? where code = ?";
		template.update(sql, item.getStock(), item.getReceiveDate() ,code);
	}
	public void receiveLog(Item item, int count) {
		Map<String, Object> params = new HashMap<String,Object>();
		
		params.put("code", item.getCode());
		params.put("stock", count);
		params.put("receiveDate", item.getReceiveDate());
		
		String sql = "insert into receiveLog values" + "(null, :code, :stock, :receiveDate)";

		jdbcTemplate.update(sql, params);
		
	}
	
	public void change(Item item) {
		String code = item.getCode();
		String sql = "update items set stock = ?, receiveDate = ? where code = ?";
		template.update(sql, item.getStock(), item.getReceiveDate() ,code);
	}
	
	public void insert(Item item) {
		Map<String, Object> params = new HashMap<String,Object>();
		
		params.put("code", item.getCode());
		params.put("name", item.getName());
		params.put("price", item.getPrice());
		
		String sql = "insert into items values" + "(:code, :name, :price, 0, null)";

		jdbcTemplate.update(sql, params);
	}
	
	public void insertSoldItem(Item item, int count) {
		Map<String, Object> params = new HashMap<String,Object>();
		
		Date d = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd hh:mm");
		Object nowDate = date.format(d);
		int totalPrice = item.getPrice() * count;

		params.put("code", item.getCode());
		params.put("count", count);
		params.put("totalPrice", totalPrice);
		params.put("saleDate", nowDate);
		try {
			String sql = "insert into selllog values" + "(null, :code, :count, :totalPrice, :saleDate)";
			jdbcTemplate.update(sql, params);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Item selectByCode(String code) {
		List<Item> results = template.query("select * from items where code = ?",
				new RowMapper<Item>() {
					@Override
					public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
						Item item = new Item( rs.getString("code"), rs.getString("name"),
							rs.getInt("price"), rs.getInt("stock"), rs.getString("receiveDate"));
							return item;
					}
				}, code);
				return results.isEmpty() ? null : results.get(0);
	}
	
	public void delete(Item item) {
		String code = item.getCode();
		String sql = "delete from items where code = ?";
		template.update(sql, code);
	}
	
	public void sell(Item item) {
		String code = item.getCode();
		String sql = "update items set stock = ? where code = ?";
		template.update(sql, item.getStock() ,code);
	}

	public List<SellLog> selectAllSellLog() {
		List<SellLog> results = jdbcTemplate.query("select * from selllog",
		(ResultSet rs, int rowNum) -> {
			SellLog sellLog = new SellLog( rs.getString("NUM"), rs.getString("CODE"),
			rs.getInt("COUNT"), rs.getInt("TOTALPRICE"), rs.getString("SALEDATE"));
			return sellLog;
		});
		return results;
	}
	
	public List<SellLog> selectTodaySellLog() {
		List<SellLog> results = jdbcTemplate.query("select * from selllog",
			(ResultSet rs, int rowNum) -> {			
			String date = rs.getString("SALEDATE");
			String[] s = date.split("/|\\s");
			int day = Integer.parseInt(s[2]);
			int month = Integer.parseInt(s[1]);
			
			if(nowDay == day && nowMonth == month) {
				SellLog sellLog = new SellLog( rs.getString("NUM"), rs.getString("CODE"),
				rs.getInt("COUNT"), rs.getInt("TOTALPRICE"), rs.getString("SALEDATE"));
				return sellLog;
			} else {
				return null;
			}
		});
		return results;
	}
	
	public List<SellLog> selectWeekSellLog() {
		List<SellLog> results = jdbcTemplate.query("select * from selllog",
			(ResultSet rs, int rowNum) -> {
			
			String date = rs.getString("SALEDATE");
			String[] s = date.split("/|\\s");
			int day = Integer.parseInt(s[2]);
			int month = Integer.parseInt(s[1]);
			
			//금일이 7일 이전인경우
			if(nowDay < 7 && nowMonth-month==1) {
				if(nowMonth==5 || nowMonth==7 || nowMonth==8 || nowMonth==10 || nowMonth==12) {
					if(day > 30-(7-nowDay)) {
						SellLog sellLog = new SellLog( rs.getString("NUM"), rs.getString("CODE"),
						rs.getInt("COUNT"), rs.getInt("TOTALPRICE"), rs.getString("SALEDATE"));
						return sellLog;
					}
				} else if(nowMonth == 2) {
					if((nowYear % 4 == 0 && nowYear % 100 != 0)||nowYear%400 == 0) {
						if(day > 29-(7-nowDay)) {
							SellLog sellLog = new SellLog( rs.getString("NUM"), rs.getString("CODE"),
							rs.getInt("COUNT"), rs.getInt("TOTALPRICE"), rs.getString("SALEDATE"));
							return sellLog;
						}
					} else {
						if(day > 28-(7-nowDay)) {
							SellLog sellLog = new SellLog( rs.getString("NUM"), rs.getString("CODE"),
							rs.getInt("COUNT"), rs.getInt("TOTALPRICE"), rs.getString("SALEDATE"));
							return sellLog;
						}
					}
				} else if(nowMonth == 1 || nowMonth==4 || nowMonth==6 || nowMonth==9 || nowMonth==11){
					if(day > 31-(7-nowDay)) {
						SellLog sellLog = new SellLog( rs.getString("NUM"), rs.getString("CODE"),
						rs.getInt("COUNT"), rs.getInt("TOTALPRICE"), rs.getString("SALEDATE"));
						return sellLog;
					}
				}
			} else if(nowDay >= 7 || nowDay >= day) { //금일이 7일 이후인경우
				if(nowDay-day < 7 && nowMonth==month) {
					SellLog sellLog = new SellLog( rs.getString("NUM"), rs.getString("CODE"),
					rs.getInt("COUNT"), rs.getInt("TOTALPRICE"), rs.getString("SALEDATE"));
					return sellLog;
				}	
			}
			return null;
			
			
		});
		return results;
	}
	
	public List<SellLog> selectMonthSellLog() {
		List<SellLog> results = jdbcTemplate.query("select * from selllog",
			(ResultSet rs, int rowNum) -> {			
			String date = rs.getString("SALEDATE");
			String[] s = date.split("/|\\s");
			int day = Integer.parseInt(s[2]);
			int month = Integer.parseInt(s[1]);
			
			if(nowMonth-month==0) {
				SellLog sellLog = new SellLog( rs.getString("NUM"), rs.getString("CODE"),
				rs.getInt("COUNT"), rs.getInt("TOTALPRICE"), rs.getString("SALEDATE"));
				return sellLog;
			} else if(nowMonth-month == 1 || nowMonth-month==-11) {
				if(day > nowDay) {
					SellLog sellLog = new SellLog( rs.getString("NUM"), rs.getString("CODE"),
					rs.getInt("COUNT"), rs.getInt("TOTALPRICE"), rs.getString("SALEDATE"));
					return sellLog;
				}
			}
			return null;
		});
		return results;
	}
	
}
