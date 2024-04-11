package com.kh.Olumgollum_Project.Controller;
import com.kh.Olumgollum_Project.RoomInfo.RoomInfoDao;
import com.kh.Olumgollum_Project.RoomInfo.RoomInfoVo;
import com.kh.Olumgollum_Project.RoomSearch.RoomSearchDao;
import com.kh.Olumgollum_Project.RoomSearch.RoomSearchVo;
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
@RequestMapping("/RoomSearch")
public class RoomSearchController {
    @GetMapping("/Search")
    public String AdminRoomSearch(Model model) {
        //입력을 안하는 요소값은 null을 가격은 맥시멈 값을 부여
        RoomSearchVo RSV = new RoomSearchVo(null, null, 0, 0, 0, 0, null);
        model.addAttribute("SearchElement", RSV);
        return "CommonHTML/RoomSearch";
    }

    @PostMapping("/Search")
    public String AdminRoomSelect(@ModelAttribute("SearchElement") RoomSearchVo RSV, HttpSession session, Model model) throws SQLException {
        RoomSearchDao dao = new RoomSearchDao();
        // 검색한 값을 BD에서 LIST 뽑아오는 부분.
        List<RoomInfoVo> Rooms = dao.RoomSelect(RSV);
        model.addAttribute("Rooms", Rooms);
        // 권한 확인
        String grant = (String) session.getAttribute("grant");
        if (grant.equals("Admin")) {
            return "AdminHTML/AdminRoomList";
        } else return "UserHTML/UserRoomList";
    }
}
