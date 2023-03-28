package system.pos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import system.pos.item.*;

import java.util.List;

@Controller
public class StockController {

    @Autowired private ItemDao itemDao;
    @Autowired private ItemRegisterService itemRegisterService;
    @Autowired private StockChangeService stockChangeService;

    //<-------stock------------------------------------------------->
    /**
     * 전체 재고
     */
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

    /**
     *  입고 처리
     */
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
            stockChangeService.receive(req, stock);
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

    /**
     * 제품 삭제 처리
     */
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
            itemRegisterService.delete(req);
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

    /**
     * 제품 등록 처리
     */
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
            itemRegisterService.regist(req);
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

    /**
     * 제품 검색 처리
     */
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

    /**
     * 재고 수정 처리
     */
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
            stockChangeService.change(req, stock);
            return "/stock/finishChange";
        } catch(Exception e) {
            e.printStackTrace();
            return "/";
        }
    }
//<-------stock------------------------------------------------->
}
