package com.kh.Olumgollum_Project.Controller;


import com.kh.Olumgollum_Project.LoginInfo.LoginInfoDao;
import com.kh.Olumgollum_Project.LoginInfo.LoginVo;
import com.kh.Olumgollum_Project.RoomInfo.RoomInfoDao;
import com.kh.Olumgollum_Project.RoomInfo.RoomInfoVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/RoomUp")
public class RoomUpController {
    //======================================방 올리기 페이지============================================
    @GetMapping("/RoomInsert")
    public String OlumPage(Model model) {
        // 데이터를 입력 받기 위해 빈 통을 모델에 추가
        model.addAttribute("RoomInfoVos", new RoomInfoVo());
        return "CommonHTML/RoomInfoOlumUp";

    }
    //일반 사용자의 방 등록후 돌아가기
    @PostMapping("/RoomInsert")
    public String OlumDBinsert(@ModelAttribute("RoomInfoVos") RoomInfoVo RIV, HttpSession session) {
        RoomInfoDao dao = new RoomInfoDao();
        LoginInfoDao LID = new LoginInfoDao();
        //로그인 할때 서버에 저장하고 있던 아이디 불러오기
        String ID = (String) session.getAttribute("id");
        // 방 정보에 아이디 추가
        RIV.setUser_id(ID);
        // 방 DB에 추가
        dao.RoomInsert(RIV);
        // User인지 Admin인지 확인(성공후 돌아갈 메인 페이지가 다릅니다.)
        // 서버에 저장하고 있던 권한 불러오기
        String grant =(String) session.getAttribute("grant");
        //확인을 위해 함수에 넘겨주기위한 LoginVo 형식에 ID,PW 값 입력
        //관리자 계정
        if(grant.equals("Admin")){
            //Admin의 성공화면으로 이동.
            return "AdminHTML/AdminRoomInfoRst";
        }
        //유저계정
        else {
            // 유저의 성공화면으로 이동.
            return  "UserHTML/UserRoomInfoRst";
        }
    }
}
