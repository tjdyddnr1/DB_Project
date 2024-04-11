package Olumgollum_Project.Main;


import Olumgollum_Project.LoginInfo.LoginInfoPage;
import Olumgollum_Project.MyPage.MyPage;
import Olumgollum_Project.RoomInfo.OlumGollumPage;

import java.text.ParseException;
import java.util.List;

public class ExcuteMain {
    public static void main(String[] args) throws InterruptedException, ParseException {
        LoginInfoPage LIM = new LoginInfoPage();
        OlumGollumPage RIM = new OlumGollumPage();
//            // 첫 화면 출력!
        List<String> id_pw = LIM.startfunc();
//        //로그인한  id_pw 정보 유저(User) 관리자 계정(Admin)
        if (id_pw.get(2).equals("Admin"))
        {
            //Admin 올룸골룸 페이지로 이동.
            RIM.Admin_Olumgollum_MainPage(id_pw);
        }
        else
        {
            //User 올룸골룸 페이지로 이동.
            RIM.User_Olumgollum_MainPage(id_pw);
        }
    }
}
