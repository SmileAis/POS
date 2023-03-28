package system.pos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import system.pos.item.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SellController {

    @Autowired private ItemDao itemDao;
    @Autowired private StockChangeService stockChangeService;
    @Autowired private ItemRegisterService itemRegisterService;

    private List<Item> sellList = new ArrayList<Item>();
    private List<Integer> countList = new ArrayList<Integer>();

    //<--------------sell---------------------------------------------->
    @GetMapping("/menu/sell")
    public String sell(Model model) {
        sellList.clear();
        countList.clear();
        model.addAttribute("sellList", sellList);
        model.addAttribute("countList", countList);

        return "menu/sell";
    }

    /**
     * 상품 판매 처리
     */
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

    /**
     *  판매 후 재고 처리
     */
    @GetMapping("/menu/finishSell")
    public String sellFinish(Model model) {
        StockChangeRequest req = new StockChangeRequest();
        ItemRegisterRequest regReq = new ItemRegisterRequest();
        try {
            for(int i=0; i<sellList.size(); i++) {
                req.setCode(sellList.get(i).getCode());
                regReq.setCode(sellList.get(i).getCode());
                regReq.setPrice(sellList.get(i).getPrice());
                stockChangeService.sell(req, countList.get(i));
                itemRegisterService.insertSoldItem(regReq, countList.get(i));
            }
            sellList.clear();
            countList.clear();
            return "/menu/finishSell";
        } catch(Exception e) {
            return "/";
        }
    }
}
