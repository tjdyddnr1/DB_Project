package com.kh.Olumgollum_Project.Controller;


import com.kh.Olumgollum_Project.AdminInfo.AdminInfoDao;
import com.kh.Olumgollum_Project.AdminInfo.AdminInfoVo;
import com.kh.Olumgollum_Project.LoginInfo.LoginInfoDao;
import com.kh.Olumgollum_Project.LoginInfo.LoginInfoVo;
import com.kh.Olumgollum_Project.LoginInfo.LoginVo;
import com.kh.Olumgollum_Project.MyPage.MyPageDao;
import com.kh.Olumgollum_Project.RoomInfo.RoomInfoVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.List;
@Controller
@RequestMapping("/Admin")
public class AdminController {
    @GetMapping("/UserList")
    public String UserSelectAll(Model model)
    {
        LoginInfoDao dao = new LoginInfoDao();
        List<LoginInfoVo> LIV = dao.logsel();
        model.addAttribute("UserList",LIV);
        return "AdminPageHTML/UserList";
    }
    @GetMapping("/UserDelete")
    public String UserDelete(Model model)
    {
        model.addAttribute("id",new LoginVo());
        return "AdminPageHTML/UserDelete";
    }
    @PostMapping("/UserDelete")
    public String UserDBdelete(@ModelAttribute("id") LoginVo LV, Model model)
    {
        MyPageDao dao = new MyPageDao();
        dao.UserDeletefunc(LV.getUserid());
        return "AdminPageHTML/UserDeleteRst";
    }
    @GetMapping("/RoomList")
    public String RoomSelectAll(Model model) throws SQLException {
        MyPageDao dao = new MyPageDao();
        List<RoomInfoVo> RIV = dao.RoomSelect();
        model.addAttribute("Rooms",RIV);
        return "AdminPageHTML/RoomList";
    }
    @GetMapping("/RoomDelete")
    public String RoomDelete(Model model)
    {
        model.addAttribute("RoomVo", new RoomInfoVo());
        return "AdminPageHTML/RoomDelete";
    }
    @PostMapping("/RoomDelete")
    public String RoomDBdelete(@ModelAttribute("RoomNum") RoomInfoVo RIV, Model model)
    {
        MyPageDao dao = new MyPageDao();
        dao.RoomDel(RIV.getHouse_num());
        return "AdminPageHTML/RoomDeleteRst";
    }
    @GetMapping("/AdminList")
    public String AdminList(Model model)
    {
        AdminInfoDao dao = new AdminInfoDao();
        List<AdminInfoVo> AIV = dao.admin_infoSelect();
        model.addAttribute("admin",AIV);
        return "AdminPageHTML/AdminList";
    }
    @GetMapping("/AdminInsert")
    public String AdminInsert(Model model)
    {
        model.addAttribute("AdminUp",new AdminInfoVo());
        return "AdminPageHTML/AdminInsert";
    }
    @PostMapping("/AdminInsert")
    public String AdminDBinsert(@ModelAttribute("AdminUp") AdminInfoVo AIV)
    {

        MyPageDao dao = new MyPageDao();
        AdminInfoDao AID = new AdminInfoDao();
        //관리자 DB에 추가한다.
        AID.AdminInsertfunc(AIV);
        //유저 DB에 있으면 삭제한다.
        dao.UserDeletefunc(AIV.getADM_ID());
        return "AdminPageHTML/AdminInsertRst";
    }
    @GetMapping("/AdminDelete")
    public String AdminDelete(Model model)
    {
        model.addAttribute("AdminDel",new AdminInfoVo());
        return "AdminPageHTML/AdminDelete";
    }
    @PostMapping("/AdminDelete")
    public String AdminDBDelete(@ModelAttribute("AdminDel") AdminInfoVo AIV)
    {
        AdminInfoDao dao = new AdminInfoDao();
        dao.AdminDeletefunc(AIV);
        return "AdminPageHTML/AdminDeleteRst";
    }
}
