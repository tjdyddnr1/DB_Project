package com.kh.Olumgollum_Project.Controller;

import com.kh.Olumgollum_Project.AdminInfo.AdminInfoDao;
import com.kh.Olumgollum_Project.AdminInfo.AdminInfoVo;
import com.kh.Olumgollum_Project.LoginInfo.InfoUpdateVo;
import com.kh.Olumgollum_Project.LoginInfo.LoginInfoVo;
import com.kh.Olumgollum_Project.LoginInfo.LoginVo;
import com.kh.Olumgollum_Project.LoveRoom.LoveRoomVo;
import com.kh.Olumgollum_Project.MyPage.MyPageDao;
import com.kh.Olumgollum_Project.RoomInfo.RoomInfoVo;
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
@RequestMapping("/MyPage")
public class MyPageController {
    //회원정보 수정
    @GetMapping("/InfoUpdate")
    public String InfoUpdate(Model model) {
        model.addAttribute("UpdateElements", new InfoUpdateVo());
        return "MyPageHTML/InfoUpdate";
    }
    @PostMapping("/InfoUpdate")
    public String Update(@ModelAttribute("UpdateElements") InfoUpdateVo IUV,HttpSession session,Model model)
    {
        MyPageDao dao = new MyPageDao();
        String ID = (String) session.getAttribute("id");
        //나의 정보 불러오기
        LoginInfoVo LIV = dao.ExtractInfo(ID);
        //수정된값 덮어 씌우기
        LoginInfoVo UpdateLIV = dao.Overwrite(LIV,IUV);
        //덮어 씌운값 DB에 수정하기
        dao.InfoUpdate(UpdateLIV, ID);
        model.addAttribute("MyInfos",UpdateLIV);
        return "UserHTML/UserInfoSelect";
    }
    //관리자 계정 회원정보 수정
    @GetMapping("/AdminInfoUpdate")
    public String AdminInfoUpdate(Model model) {
        model.addAttribute("UpdateElements", new AdminInfoVo());
        return "MyPageHTML/AdminInfoUpdate";
    }
    @PostMapping("/AdminInfoUpdate")
    public String AdminUpdate(@ModelAttribute("UpdateElements") AdminInfoVo AIV,HttpSession session,Model model)
    {
        MyPageDao dao = new MyPageDao();
        String ID = (String) session.getAttribute("id");
        //나의 정보 불러오기
        AdminInfoVo aiv = dao.AdminExtractInfo(ID);
        //수정된값 덮어 씌우기
        AdminInfoVo UpdateAIV = dao.AdminOverwrite(aiv,AIV);
        //덮어 씌운값 DB에 수정하기
        dao.AdminInfoUpdate(UpdateAIV, ID);
        model.addAttribute("MyInfos",UpdateAIV);
        //돌아가기를 위한 권한 확인
        return "AdminHTML/AdminInfoSelect";
    }
//    찜한방 리스트
    @GetMapping("/LoveRoomList")
    public String LoveRoomList(Model model, HttpSession session) {
        MyPageDao dao = new MyPageDao();
        String ID = (String) session.getAttribute("id");
        List<RoomInfoVo> RIV = dao.LoveList(ID);
        model.addAttribute("LoveRooms",RIV);
        String grant = (String) session.getAttribute("grant");
        if (grant.equals("Admin"))
        {
            return "MyPageHTML/AdminLoveRoomList";
        }
        else
            return "MyPageHTML/UserLoveRoomList";
    }
    // 찜한방 삭제(찜 취소)
    @GetMapping("/LoveRoomDelete")
    public String LoveRoomDelete(Model model) {
        model.addAttribute("Lovedel", new LoveRoomVo());
        return "MyPageHTML/LoveRoomDelete";
    }
    @PostMapping("/LoveRoomDelete")
    public String LoveRoomDBdelete(@ModelAttribute() LoveRoomVo LRV,HttpSession session)
    {
        MyPageDao dao = new MyPageDao();
        String ID = (String) session.getAttribute("id");
        LRV.setUser_id(ID);
        dao.LoveDelete(LRV);
        String grant = (String) session.getAttribute("grant");
        if (grant.equals("Admin"))
        {
            return "MyPageHTML/AdminLoveRoomDeleteRst";
        }
        else
            return "MyPageHTML/UserLoveRoomDeleteRst";
    }
    // 올린방 리스트
    @GetMapping("/OulmList")
    public String OulmList(Model model, HttpSession session) {
        MyPageDao dao = new MyPageDao();
        String ID = (String) session.getAttribute("id");
        List<RoomInfoVo> RIV = dao.OlumList(ID);
        model.addAttribute("OulmList", RIV);
        String grant = (String) session.getAttribute("grant");
        if (grant.equals("Admin"))
        {
            return "MyPageHTML/AdminOlumList";
        }
        else
            return "MyPageHTML/UserOlumList";
    }
    //올린방 수정
    @GetMapping("/OulmUpdate")
    public String OulmUpdate(Model model) {
        model.addAttribute("UpdateElements", new RoomInfoVo());
        return "MyPageHTML/OlumUpdate";
    }
    @PostMapping("/OulmUpdate")
    public String OulmBDupdate(@ModelAttribute() RoomInfoVo RIV,HttpSession session)
    {
        MyPageDao dao = new MyPageDao();
        String ID = (String) session.getAttribute("id");
        dao.OlumUpdate(RIV,ID);
        //권한에 따른 이동
        String grant = (String) session.getAttribute("grant");
        if (grant.equals("Admin"))
        {
            return "MyPageHTML/AdminOulmUpdateRst";
        }
        else
            return "MyPageHTML/UserOulmUpdateRst";
    }
    //올린방 삭제
    @GetMapping("/OulmDelete")
    public String OulmDelete(Model model) {
        model.addAttribute("DeleteNum", new RoomInfoVo());
        return "MyPageHTML/OulmDelete";
    }
    @PostMapping("/OulmDelete")
    public String OulmDBdelete(@ModelAttribute() RoomInfoVo RIV,HttpSession session)
    {
        MyPageDao dao = new MyPageDao();
        dao.RoomDel(RIV.getHouse_num());
        //권한에 따른 이동
        String grant = (String) session.getAttribute("grant");
        if (grant.equals("Admin"))
        {
            return "MyPageHTML/AdminOulmDeleteRst";
        }
        else
            return "MyPageHTML/UserOulmDeleteRst";
    }
    //회원탈퇴
    @GetMapping("/UserDelete")
    public String UserDelete(Model model) {
        model.addAttribute("id", new LoginVo());
        return "MyPageHTML/AccountDelete";
    }
    @PostMapping("/UserDelete")
    public String UserDBdelete(@ModelAttribute() LoginVo RIV,HttpSession session)
    {
        String ID = (String) session.getAttribute("id");
        MyPageDao dao = new MyPageDao();
        AdminInfoDao AID = new AdminInfoDao();
        //찜 목록 삭제
        dao.LoveAllDelete(ID);
        //방 데이터 삭제
        dao.RoomAllDel(ID);
        //권한에 따른 삭제
        String grant = (String) session.getAttribute("grant");
        if (grant.equals("Admin"))
        {
            //관리자 계정 삭제
            AID.AdminDelete(ID);
            return "MyPageHTML/AdminDeleteRst";
        }
        else
        {
            //User 계정 삭제
            dao.UserDeletefunc(ID);
            return "MyPageHTML/UserDeleteRst";
        }
    }

}
