package com.kh.Olumgollum_Project.Controller;

import com.kh.Olumgollum_Project.LoginInfo.LoginInfoDao;
import com.kh.Olumgollum_Project.LoginInfo.LoginVo;
import com.kh.Olumgollum_Project.RoomInfo.RoomInfoDao;
import com.kh.Olumgollum_Project.RoomInfo.RoomInfoVo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/Page")
public class PageController {
    //OlumGollum 첫화면
    @GetMapping("/FirstPage")
    public String FirstPage() {
        return "CommonHTML/FirstPage";
    }

    //일반 User 메인 페이지
    @GetMapping("/UserMainPage")
    public String MainPage() {
        return "UserHTML/UserMainPage";
    }

    //관리자 계정의 메인페이지
    @GetMapping("/AdminMainPage")
    public String AdminMainPage() {

        return "AdminHTML/AdminMainPage";
    }
    @GetMapping("/AdminMyPage")
    public String AdminMyPageMain(Model model){

        return "AdminHTML/AdminMyPage";
    }
    @GetMapping("/UserMyPage")
    public String UserMyPageMain(Model model){

        return "UserHTML/UserMyPage";
    }
    @GetMapping("/AdminManage")
    public String AdminManage(Model model){

        return "AdminHTML/AdminManagePage";
    }

}