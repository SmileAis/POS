package system.pos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import system.pos.member.Member;
import system.pos.member.MemberDao;

@Controller
public class LoginController {

    @Autowired private MemberDao memberDao;

    /**
     * 로그인 시도
     */
    @PostMapping(value = "/login/logincheck")
    public String logincheck(Model model,
                             @RequestParam(value="id") String id,
                             @RequestParam(value="pwd") String pwd) {

        Member member = memberDao.selectById(id);
        if(member != null && member.getPassword().equals(pwd)) {
            model.addAttribute("id", id);
            model.addAttribute("member", member);
            return "pos_main";
        }
        return "login/logincheck";
    }
}
