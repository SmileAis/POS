package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import system.pos.item.Item;
import system.pos.item.ItemDao;
import system.pos.item.ItemRegisterRequest;
import system.pos.item.ItemRegisterService;
import system.pos.item.SellLog;
import system.pos.item.StockChangeRequest;
import system.pos.item.StockChangeService;
import system.pos.member.Member;
import system.pos.member.MemberDao;
import system.pos.member.MemberRegistRequest;
import system.pos.member.MemberRegistService;

@Controller
public class MainController {
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private StockChangeService stockChangeSvc;
	@Autowired
	private ItemRegisterService itemRegSvc;
	@Autowired
	private MemberRegistService memRegSvc;

	private List<Item> sellList = new ArrayList<Item>();
	private List<Integer> countList = new ArrayList<Integer>();
	
//<--------------login---------------------------------------------->
	@GetMapping({"/", "/login"})
	public String login(Model model) {
		return "login/login";
	}
	@GetMapping("/login/logincheck")
	public String logincheck(Model model, 
			@RequestParam(value="id") String id,
			@RequestParam(value="pwd") String pwd) 
	{
		Member member = memberDao.selectById(id);
		
		if(member != null && member.getPassword().equals(pwd)) {
			model.addAttribute("id", id);
			model.addAttribute("member", member);
			return "pos_main";
		}
		return "login/logincheck";
	}
//<--------------login---------------------------------------------->
	
//<--------------menu---------------------------------------------->	
	@GetMapping("/pos_main")
	public String posMain() {
		sellList.clear();
		countList.clear();
		return "pos_main";
	}
	@GetMapping("/menu/stockMenu")
	public String stockMenu(Model model) {
		return "menu/stockMenu";
	}
	@GetMapping("/menu/statMenu")
	public String statMenu(Model model) {
		return "menu/statMenu";
	}
	@GetMapping("/menu/memberMenu")
	public String memberMenu(Model model) {
		return "menu/memberMenu";
	}
//<--------------menu---------------------------------------------->
	
//<--------------sell---------------------------------------------->	
	@GetMapping("/menu/sell")
	public String sell(Model model) {
		model.addAttribute("sellList", sellList);
		model.addAttribute("countList", countList);
		
		return "menu/sell";
	}
	@GetMapping("/menu/addSell")
	public String addSell(Model model,
			@RequestParam(value="code") String code,
			@RequestParam(value="count") int count) {
	
		Item item = itemDao.selectByCode(code);
		if(item == null)
			return "menu/errorPage";
		sellList.add(item);
		countList.add(count);
		model.addAttribute("sellList", sellList);
		model.addAttribute("countList", countList);

		return "menu/addSell";
	}
	@GetMapping("/menu/finishSell")
	public String sellFinish(Model model) {
		StockChangeRequest req = new StockChangeRequest();
		ItemRegisterRequest regReq = new ItemRegisterRequest();
		try {
			for(int i=0; i<sellList.size(); i++) {
				req.setCode(sellList.get(i).getCode());
				regReq.setCode(sellList.get(i).getCode());
				regReq.setPrice(sellList.get(i).getPrice());
				stockChangeSvc.sell(req, countList.get(i));
				itemRegSvc.insertSoldItem(regReq, countList.get(i));
			}
			sellList.clear();
			countList.clear();
			return "/menu/finishSell";
		} catch(Exception e) {
			return "/";
		}
	}
//<--------------sell---------------------------------------------->	
	
//<-------stock------------------------------------------------->		
	@GetMapping("/stock/view")
	public String itemList(Model model) {
		List<Item> itemList = itemDao.selectAll();
		model.addAttribute("items", itemList);
		return "/stock/view";
	}
	
	@GetMapping("/stock/receive")
	public String stockReceive() {	
		return "stock/receive";
	}
	
	@GetMapping("/stock/finishReceive")
	public String stockReceiveFinish(Model model ,
			@RequestParam(value="code") String code,
			@RequestParam(value="stock") int stock) {
		if(itemDao.selectByCode(code)== null)
			return "/stock/errorPage";
		model.addAttribute("code", code);
		model.addAttribute("name", itemDao.selectByCode(code).getName());
		model.addAttribute("stock", stock);
		StockChangeRequest req = new StockChangeRequest();
		
		try {
			req.setCode(code);
			req.setStock(stock);
			stockChangeSvc.receive(req, stock);
			return "/stock/finishReceive";
		} catch(Exception e) {
			e.printStackTrace();
			return "/";
		}
	}
	
	@GetMapping("/stock/delete")
	public String stockDelete(Model model) {
		return "stock/delete";
	}
	
	@GetMapping("/stock/finishDelete")
	public String stockDeleteFinish(Model model,
			@RequestParam(value="code") String code) {
		if(itemDao.selectByCode(code)== null)
			return "/stock/errorPage";
		
		model.addAttribute("code", code);
		model.addAttribute("name", itemDao.selectByCode(code).getName());
		
		ItemRegisterRequest req = new ItemRegisterRequest();
		try {
			req.setCode(code);
			itemRegSvc.delete(req);
			return "/stock/finishDelete";
		} catch(Exception e) {
			e.printStackTrace();
			return "/";
		}
	}
	@GetMapping("/stock/regist")
	public String stockRegist(Model model) {	
		return "stock/regist";
	}
	@GetMapping("/stock/finishRegist")
	public String stockRegistFinish(Model model ,
			@RequestParam(value="code") String code,
			@RequestParam(value="name") String name,
			@RequestParam(value="price") int price) {
		model.addAttribute("code", code);	
		model.addAttribute("name", name);
			
		ItemRegisterRequest req = new ItemRegisterRequest();
		try {
			req.setCode(code);
			req.setName(name);
			req.setPrice(price);
			itemRegSvc.regist(req);
			return "/stock/finishRegist";
		} catch(Exception e) {
			e.printStackTrace();
			return "/";
		}
	}
	@GetMapping("/stock/search")
	public String itemSearch(Model model) {
		return "/stock/search";
	}
	@GetMapping("/stock/finishSearch")
	public String itemSearchFinish(Model model,
			@RequestParam(value="code") String code) {
		Item item = itemDao.selectByCode(code);
		if(item == null)
			return "/stock/errorPage";
		
		model.addAttribute("code", code);
		model.addAttribute("name", item.getName());
		model.addAttribute("price", item.getPrice());
		model.addAttribute("stock", item.getStock());
		model.addAttribute("receiveDate", item.getReceiveDate());
		return "/stock/finishSearch";
	}
	@GetMapping("/stock/change")
	public String stockChange() {	
		return "stock/change";
	}
	
	@GetMapping("/stock/finishChange")
	public String stockChangeinish(Model model ,
			@RequestParam(value="code") String code,
			@RequestParam(value="stock") int stock) {
		if(itemDao.selectByCode(code)== null)
			return "/stock/errorPage";
			model.addAttribute("code", code);
			model.addAttribute("name", itemDao.selectByCode(code).getName());
			model.addAttribute("stock", stock);
		StockChangeRequest req = new StockChangeRequest();
		try {
			req.setCode(code);
			req.setStock(stock);
			stockChangeSvc.change(req, stock);
			return "/stock/finishChange";
		} catch(Exception e) {
			e.printStackTrace();
			return "/";
		}
	}
//<-------stock------------------------------------------------->	

//<-------statistic---------------------------------------------->
	@GetMapping("/stat/all")
	public String statAll(Model model) {	
		List<SellLog> sellList = itemDao.selectAllSellLog();
		model.addAttribute("sellList", sellList);
		
		return "stat/all";
	}
	@GetMapping("/stat/today")
	public String statToday(Model model) {	
		List<SellLog> sellList = itemDao.selectTodaySellLog();
		while(sellList.indexOf(null) != -1){
			sellList.remove(null);
		}
		model.addAttribute("sellList", sellList);
		
		return "stat/today";
	}
	@GetMapping("/stat/week")
	public String statWeek(Model model) {	
		List<SellLog> sellList = itemDao.selectWeekSellLog();
		while(sellList.indexOf(null) != -1){
			sellList.remove(null);
		}
		model.addAttribute("sellList", sellList);
		
		return "stat/week";
	}
	@GetMapping("/stat/month")
	public String statMonth(Model model) {	
		List<SellLog> sellList = itemDao.selectMonthSellLog();
		while(sellList.indexOf(null) != -1){
			sellList.remove(null);
		}
		model.addAttribute("sellList", sellList);
		
		return "stat/month";
	}
	@GetMapping("/stat/quantity5")
	public String statQuantity(Model model) {	
		List<SellLog> sellLog = itemDao.selectAllSellLog();
		List<String> codeTop5 = new ArrayList<>();
		List<Integer> countTop5 = new ArrayList<>();
		List<String> nameTop5 = new ArrayList<>();
		List<Integer> priceTop5 = new ArrayList<>();
		Map<String, Integer>tmp = new HashMap<>();
		int idx;
		String findKey ="";
		
		for(int i=0; i<sellLog.size(); i++) {
			if(!codeTop5.contains(sellLog.get(i).getCode())) {
				codeTop5.add(sellLog.get(i).getCode());
				countTop5.add(sellLog.get(i).getCount());
			} else {
				idx = codeTop5.indexOf(sellLog.get(i).getCode());
				countTop5.set(idx, countTop5.get(idx)+sellLog.get(i).getCount());
			}
		}

		for(int i=0; i<codeTop5.size(); i++) {
			tmp.put(codeTop5.get(i), countTop5.get(i));
		}
		
		Collections.sort(countTop5, Collections.reverseOrder());
		
		for(int i=0; i<5; i++) {
			for(String key : tmp.keySet()) {
			    if(tmp.get(key).equals(countTop5.get(i))) {
			      findKey = key;
			      break;
			    }
			}
			codeTop5.set(i, findKey);
			tmp.remove(findKey);
		}
		
		for(int i=0; i<5; i++) {
			nameTop5.add(itemDao.selectByCode(codeTop5.get(i)).getName());
			priceTop5.add((itemDao.selectByCode(codeTop5.get(i)).getPrice()) * countTop5.get(i));
		}
		
		model.addAttribute("nameTop5", nameTop5);
		model.addAttribute("priceTop5", priceTop5);
		model.addAttribute("codeTop5", codeTop5);
		model.addAttribute("countTop5", countTop5);
		
		return "stat/quantity5";
	}
	@GetMapping("/stat/earning5")
	public String statEarning(Model model) {	
		List<SellLog> sellLog = itemDao.selectAllSellLog();
		List<String> codeTop5 = new ArrayList<>();
		List<Integer> countTop5 = new ArrayList<>();
		List<String> nameTop5 = new ArrayList<>();
		List<Integer> priceTop5 = new ArrayList<>();
		Map<String, Integer>tmp = new HashMap<>();
		int idx;
		String findKey ="";
		
		for(int i=0; i<sellLog.size(); i++) {
			if(!codeTop5.contains(sellLog.get(i).getCode())) {
				codeTop5.add(sellLog.get(i).getCode());
				priceTop5.add(sellLog.get(i).getTotalPrice());
			} else {
				idx = codeTop5.indexOf(sellLog.get(i).getCode());
				priceTop5.set(idx, priceTop5.get(idx)+sellLog.get(i).getTotalPrice());
			}
		}
		for(int i=0; i<codeTop5.size(); i++) {
			tmp.put(codeTop5.get(i), priceTop5.get(i));
		}
		
		Collections.sort(priceTop5, Collections.reverseOrder());
		
		for(int i=0; i<5; i++) {
			for(String key : tmp.keySet()) {
			    if(tmp.get(key).equals(priceTop5.get(i))) {
			      findKey = key;
			      break;
			    }
			}
			codeTop5.set(i, findKey);
			tmp.remove(findKey);
		}
		
		for(int i=0; i<5; i++) {
			nameTop5.add(itemDao.selectByCode(codeTop5.get(i)).getName());
			countTop5.add((Integer)(priceTop5.get(i) / itemDao.selectByCode(codeTop5.get(i)).getPrice()));
		}
		
		model.addAttribute("nameTop5", nameTop5);
		model.addAttribute("priceTop5", priceTop5);
		model.addAttribute("codeTop5", codeTop5);
		model.addAttribute("countTop5", countTop5);

		return "stat/earning5";
	}
//<-------statistic---------------------------------------------->
	
//<-------Member------------------------------------------------>	
	@GetMapping("/member/view")
	public String memberList(Model model) {
		List<Member> memberList = memberDao.selectAll();
		model.addAttribute("members", memberList);
		return "member/view";
	}
	
	@GetMapping("/member/regist")
	public String memRegist(Model model) {	
		return "member/regist";
	}
	
	@GetMapping("/member/finishRegist")
	public String memRegistFinish(Model model ,
			@RequestParam(value="rank") String rank,
			@RequestParam(value="name") String name,
			@RequestParam(value="id") String id,
			@RequestParam(value="pwd") String pwd) {
			model.addAttribute("name", name);
			model.addAttribute("id", id);
		MemberRegistRequest regReq = new MemberRegistRequest();
		try {
			regReq.setRank(rank);
			regReq.setName(name);
			regReq.setId(id);
			regReq.setPassword(pwd);
			memRegSvc.regist(regReq);
			return "/member/finishRegist";
		} catch(Exception e) {
			e.printStackTrace();
			return "/";
		}
	}
	
	@GetMapping("/member/delete")
	public String memDelete(Model model) {
		return "member/delete";
	}
	
	@GetMapping("/member/finishDelete")
	public String memDeleteFinish(Model model,
			@RequestParam(value="id") String id) {
		if(memberDao.selectById(id)== null)
			return "/member/errorPage";
		model.addAttribute("id", id);
		
		MemberRegistRequest regReq = new MemberRegistRequest();
		try {
			regReq.setId(id);
			memRegSvc.delete(regReq);
			System.out.println("삭제완료");
			return "/member/finishDelete";
		} catch(Exception e) {
			e.printStackTrace();
			return "/";
		}
	}	
//<-------Member------------------------------------------------>	
}