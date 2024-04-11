package com.kh.Olumgollum_Project.Controller;

import com.kh.Olumgollum_Project.LoginInfo.LoginInfoDao;
import com.kh.Olumgollum_Project.LoginInfo.LoginInfoVo;
import com.kh.Olumgollum_Project.LoginInfo.LoginVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/Login")
public class LoginController {
    //회원가입
    @GetMapping("/UserInsert")
    public String UserInsert(Model model){
        model.addAttribute("UserInsert",new LoginInfoVo());
        return "CommonHTML/UserInsert";
    }
    @PostMapping("/UserInsert")
    public String UserInsert(@ModelAttribute("UserInsert") LoginInfoVo LIV)
    {
        LoginInfoDao dao = new LoginInfoDao();
        dao.UserInsert(LIV);
        return "CommonHTML/UserInsertRst";

    }
    // 사용자  로그인
    @GetMapping("/LoginCheck")
    public String LoginPage(Model model) {
        model.addAttribute("ID_PASSWORD", new LoginVo());
        return "CommonHTML/LoginPage";
    }
    //========================================여기까지는 일반과 관리자가 동일=======================================
    // 일반 사용자 인지 관리자 사용자인지 확인
    @PostMapping("/LoginCheck")
    public String SetLogin(@ModelAttribute("ID_PASSWORD") LoginVo User, HttpSession session) throws InterruptedException, SQLException, SQLException {
        //서버 공간에 정보를 저장합니다.
        LoginInfoDao LID = new LoginInfoDao();
        // 관리자 사용자 ID,PW DB에서 확인하는 부분
        boolean AdminCheck = LID.AdminLoginfunc(User);
        if(AdminCheck){
            //로그인 조건일시 서버에 id,pw 저장
            session.setAttribute("id", User.getUserid());
            session.setAttribute("grant","Admin");
            return "AdminHTML/AdminMainPage";
        }
        //관리자 사용자가 아닌 경우
        else {
            // 일반 사용자 DB에서 확인하는 부분
            boolean LoginCheck = LID.UserLoginfunc(User);
            //로그인 성공!
            if (LoginCheck) {
                //로그인 조건일시 서버에 id,pw 저장
                session.setAttribute("id", User.getUserid());
                session.setAttribute("grant","User");
                return "UserHTML/UserMainPage";
            }
            //로그인에 실패했을 때 실패 문구가 뜨는 로그인페이지로 이동.
            else return "redirect:/Login/FailedCheck"; //redirect는 controller에 해당 GetMapping으로 이동!
        }
    }

    // 로그인이 실패했을 경우 계속 실패했다는 문구입력(기능됩니다.ㅋㅋ)
    @GetMapping("/FailedCheck")
    public String FailedPage(Model model) {
        model.addAttribute("ID_PASSWORD", new LoginVo());
        return "CommonHTML/FailedLoginPage";
    }

    @PostMapping("/FailedCheck")
    public String FailedLogin(@ModelAttribute("ID_PASSWORD") LoginVo User, HttpSession session) throws InterruptedException, SQLException {
        //서버 공간에 정보를 저장합니다.
        LoginInfoDao LID = new LoginInfoDao();
        // 관리자 사용자 ID,PW DB에서 확인하는 부분
        boolean AdminCheck = LID.AdminLoginfunc(User);
        if(AdminCheck){
            //로그인 조건일시 서버에 id,pw 저장
            session.setAttribute("id", User.getUserid());
            session.setAttribute("grant","Admin");
            return "AdminHTML/AdminMainPage";
        }
        //관리자 사용자가 아닌 경우
        else {
            // 일반 사용자의 로그인 확인하는 부분
            boolean LoginCheck = LID.UserLoginfunc(User);
            //로그인 성공!
            if (LoginCheck){
                //로그인 조건일시 서버에 id,pw 저장
                session.setAttribute("id", User.getUserid());
                session.setAttribute("grant","User");
                return "UserHTML/UserMainPage";
            }
            //로그인에 실패했을 때 실패 문구가 뜨는 로그인페이지로 이동.
            else return "redirect:/Login/FailedCheck";
        }
    }
}
