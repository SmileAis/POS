package system.pos.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	/**
	 * 메인 화면
	 */
	@GetMapping({"/", "/login"})
	public String login(Model model) {
		return "login/login";
	}

	@GetMapping("/pos_main")
	public String posMain() {
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
}