package system.pos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import system.pos.member.Member;
import system.pos.member.MemberDao;
import system.pos.member.MemberRegistRequest;
import system.pos.member.MemberRegistService;

import java.util.List;

@Controller
public class MemberController {

    @Autowired private MemberDao memberDao;
    @Autowired private MemberRegistService memberRegistService;

    /**
     * 모든 계정 확인
     */
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

    /**
     * 맴버 등록 처리
     */
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
            memberRegistService.regist(regReq);
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

    /**
     * 계정 삭제 처리
     */
    @GetMapping("/member/finishDelete")
    public String memDeleteFinish(Model model,
                                  @RequestParam(value="id") String id) {
        if(memberDao.selectById(id)== null)
            return "/member/errorPage";
        model.addAttribute("id", id);

        MemberRegistRequest regReq = new MemberRegistRequest();
        try {
            regReq.setId(id);
            memberRegistService.delete(regReq);
            System.out.println("삭제완료");
            return "/member/finishDelete";
        } catch(Exception e) {
            e.printStackTrace();
            return "/";
        }
    }
}
