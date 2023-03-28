package system.pos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import system.pos.item.ItemDao;
import system.pos.item.SellLog;

import java.util.*;

@Controller
public class StatisticController {

    @Autowired private ItemDao itemDao;

    /**
     * 전체 매출 확인
     */
    @GetMapping("/stat/all")
    public String statAll(Model model) {
        List<SellLog> sellList = itemDao.selectAllSellLog();
        model.addAttribute("sellList", sellList);

        return "stat/all";
    }

    /**
     * 당일 매출 확인
     */
    @GetMapping("/stat/today")
    public String statToday(Model model) {
        List<SellLog> sellList = itemDao.selectTodaySellLog();
        while(sellList.indexOf(null) != -1){
            sellList.remove(null);
        }
        model.addAttribute("sellList", sellList);

        return "stat/today";
    }

    /**
     * 금주 매출 확인
     */
    @GetMapping("/stat/week")
    public String statWeek(Model model) {
        List<SellLog> sellList = itemDao.selectWeekSellLog();
        while(sellList.indexOf(null) != -1){
            sellList.remove(null);
        }
        model.addAttribute("sellList", sellList);

        return "stat/week";
    }

    /**
     * 한달 매출 확인
     */
    @GetMapping("/stat/month")
    public String statMonth(Model model) {
        List<SellLog> sellList = itemDao.selectMonthSellLog();
        while(sellList.indexOf(null) != -1){
            sellList.remove(null);
        }
        model.addAttribute("sellList", sellList);

        return "stat/month";
    }

    /**
     * 판매량 top5 확인
     */
    @GetMapping("/stat/quantity5")
    public String statQuantity(Model model) {
        List<SellLog> sellLog = itemDao.selectAllSellLog();
        List<String> codeTop5 = new ArrayList<>();
        List<Integer> countTop5 = new ArrayList<>();
        List<String> nameTop5 = new ArrayList<>();
        List<Integer> priceTop5 = new ArrayList<>();
        Map<String, Integer> tmp = new HashMap<>();
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

    /**
     * 판매액 top5
     */
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
}
