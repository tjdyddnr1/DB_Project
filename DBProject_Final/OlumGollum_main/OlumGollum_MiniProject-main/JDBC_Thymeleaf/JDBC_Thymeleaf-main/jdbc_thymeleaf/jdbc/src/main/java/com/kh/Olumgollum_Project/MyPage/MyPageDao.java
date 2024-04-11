package com.kh.Olumgollum_Project.MyPage;

import java.sql.*;


import com.kh.Olumgollum_Project.AdminInfo.AdminInfoVo;
import com.kh.Olumgollum_Project.COMMON.Common;
import com.kh.Olumgollum_Project.LoginInfo.InfoUpdateVo;
import com.kh.Olumgollum_Project.LoginInfo.LoginInfoVo;
import com.kh.Olumgollum_Project.LoveRoom.LoveRoomVo;
import com.kh.Olumgollum_Project.RoomInfo.RoomInfoVo;

import java.sql.Date;
import java.util.*;

public class MyPageDao {
    public static final String black = "\u001B[30m";
    public static final String red = "\u001B[31m";
    public static final String green = "\u001B[32m";
    public static final String yellow = "\u001B[33m";
    public static final String blue = "\u001B[34m";
    public static final String purple = "\u001B[35m";
    public static final String cyan = "\u001B[36;m";
    public static final String white = "\u001B[37m";
    public static final String exit = "\u001B[0m";
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pSmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);

    // 삭제할 방 번호 저장
    // 개인정보 수정 비밀번호 전화번호 주소 닉네임
    public void Userupdate(List<String> id_pw) {
        System.out.print(blue +
                "|======================================================================|\n" +

                "|       \uD83D\uDD2E      _____  _____  ____   _____  _____  _____        \uD83D\uDD2E     |\n" +
                "|            \uD83D\uDD2E|  |  ||  _  ||    \\ |  _  ||_   _||   __|              |\n" +
                "|              |  |  ||   __||  |  ||     |  | |  |   __|      \uD83D\uDD2E      |\n" +
                "|  \uD83D\uDD2E          |_____||__|   |____/ |__|__|  |_|  |_____|              |\n" +
                "|======================================================================|\n" +
                "| \uD83C\uDF26\uFE0F     ------------------              ------------------            |\n" +
                "|        |[1]   비밀번호    |     \uD83C\uDF38      |[2]   전화번호    |            |\n" +
                "|        ------------------              ------------------            |\n" +
                "|        ------------------              ------------------            |\n" +
                "| \uD83C\uDF38     |[3]     주소     |   \uD83C\uDF26\uFE0F        |[4]   닉네임      | \uD83C\uDF38        |\n" +
                "|        ------------------              ------------------            |\n" +
                "|        ------------------                                            |\n" +
                "|        |[5]    나가기    |         |￣￣￣￣￣￣￣|        ☁\uFE0F   ⭐      |\n" +
                "|        ------------------         | 뭐 바꿀래?  |            \uD83C\uDF38\uD83C\uDF38     |\n" +
                "|                   ☁\uFE0F              |＿＿＿＿＿＿＿|    ☁\uFE0F     \uD83C\uDF38\uD83C\uDF38\uD83C\uDF38   |\n" +
                "|                                   (\\__/) ||              \uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38   |\n" +
                "|                                   (•ㅅ•).||                  \uD83C\uDF38      |\n" +
                "|       ☁\uFE0F          \uD83C\uDF26\uFE0F              / . . . .づ                        |\n" +
                "========================================================================\n" + exit +
                yellow + "     뭐 바꿀래? : " + exit);
        int up = sc.nextInt();
        System.out.println(blue + "========================================================================" + exit);


        String sql = null;
        switch (up) {
            case 1:
                System.out.print("   수정하고자 하는 비밀번호 입력 : ");
                String pwd = sc.next();
                sql = "UPDATE INFO SET PASSWORD = ? WHERE USER_MAIL=?";
                try {
                    conn = Common.getConnection();
                    pSmt = conn.prepareStatement(sql);
                    pSmt.setString(1, pwd);
                    pSmt.setString(2, id_pw.get(0));
                    pSmt.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Common.close(pSmt);
                    Common.close(conn);
                }
                break;
            case 2:
                System.out.print("   수정하고자 하는 전화번호 입력 :");
                String pn = sc.next();
                sql = "UPDATE INFO SET PHONE_NUMBER = ? WHERE USER_MAIL=?";
                try {
                    conn = Common.getConnection();
                    pSmt = conn.prepareStatement(sql);
                    pSmt.setString(1, pn);
                    pSmt.setString(2, id_pw.get(0));
                    pSmt.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Common.close(pSmt);
                    Common.close(conn);
                }
                break;
            case 3:
                System.out.println("   수정하고자 하는 주소 입력 :");
                String ad = sc.nextLine();
                sql = "UPDATE INFO SET ADDRESS = ? WHERE USER_MAIL=?";
                try {
                    conn = Common.getConnection();
                    pSmt = conn.prepareStatement(sql);
                    pSmt.setString(1, ad);
                    pSmt.setString(2, id_pw.get(0));
                    pSmt.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Common.close(pSmt);
                    Common.close(conn);
                }
                break;
            case 4:
                System.out.println("   수정하고자 하는 닉네임 입력 :");
                String ni = sc.next();
                sql = "UPDATE INFO SET NICKNAME = ? WHERE USER_MAIL=?";
                try {
                    conn = Common.getConnection();
                    pSmt = conn.prepareStatement(sql);
                    pSmt.setString(1, ni);
                    pSmt.setString(2, id_pw.get(0));
                    pSmt.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Common.close(pSmt);
                    Common.close(conn);
                }
                break;
            case 5:
                System.exit(0);
        }
    }

    //===========================================================================================================================================================================================================
    public List<RoomInfoVo> Love_list(List<String> id_pw) { // 찜한방 목록화
        List<RoomInfoVo> love = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT rm.house_num,rm.user_id,rm.photo_url,rm.trade_method,rm.deposit,rm.MONTHLY,rm.jeonsegeum,rm.address, rm.area, rm.sale_price " +
                    "FROM ROOMINFO rm JOIN LOVE_ROOM lr " +
                    "ON rm.house_num = lr.shouse_num WHERE lr.user_id = '" + id_pw.get(0) + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //내가 찜한 방에 대한 정보들
                love.add(new RoomInfoVo(rs.getInt("house_num"), rs.getString("user_id"), rs.getString("photo_url"), rs.getString("trade_method"),
                        rs.getInt("deposit"), rs.getInt("MONTHLY"), rs.getInt("jeonsegeum"),
                        rs.getInt("sale_price"), rs.getString("area"), null, rs.getString("address"), null,
                        0, null, null));
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return love;
    }

    //==================================================================================================================================================================================================================
    //db에서 찜삭제함수
    public void Love_delete(List<String> id_pw) { // 찜한방 목록 Delete
        try {
            // love 내가 찜한 방의 정보들
            conn = Common.getConnection();
            stmt = conn.createStatement();
            System.out.print("   삭제 할 방 번호를 입력해주세요 : ");
            int num = sc.nextInt();
            // 찜한 방 목록에서 확인 후 삭제
            String sql = "DELETE FROM LOVE_ROOM WHERE USER_ID = '" + id_pw.get(0) + "'" + "AND SHOUSE_NUM = '" + num + "'";
            rs = stmt.executeQuery(sql);
            // 방목록에서 원하는방 삭제
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//==========================================================================================================================================================================

    public List<RoomInfoVo> olume_list(List<String> id_pw) { // 올룸 목록

        List<RoomInfoVo> olume = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT address, deposit, area, trade_method, photo_url, MONTHLY, jeonsegeum, sale_price " +
                    "FROM ROOMINFO WHERE USER_ID = '" + id_pw.get(0) + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                olume.add(new RoomInfoVo(0, null, rs.getString("photo_url"), rs.getString("trade_method"),
                        rs.getInt("deposit"), rs.getInt("monthly"), rs.getInt("jeonsegeum"),
                        rs.getInt("sale_price"), rs.getString("area"), null, rs.getString("address"), null,
                        0, null, null));
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return olume;
    }

    //===========================================================================================================================================================================
    // 개인정보 수정 비밀번호 전화번호 주소 닉네임
    public void print(List<RoomInfoVo> list) {
        for (RoomInfoVo e : list)
            System.out.println(e.getUser_id());
    }

    //============================================================================================================================
    //올룸 수정 함수
    // 업데이트
    public void RoomUpdate() {
        System.out.print("   수정하고 싶은 정보 [1] 방 사진 [2] 거래방식(가격) [3] 전화번호 [4] 비고 [5] 종료");
        int up = sc.nextInt();
        String sql = null;
        int honum;
        switch (up) {
            case 1:
                System.out.print("   바꾸고자 하는 매물번호 : ");
                honum = sc.nextInt();
                System.out.println("수정 사진 등록");
                String url = sc.next();
                sql = "UPDATE ROOMINFO SET photo_url = ? WHERE house_num=?";
                try {
                    conn = Common.getConnection();
                    pSmt = conn.prepareStatement(sql);
                    pSmt.setString(1, url);
                    pSmt.setInt(2, honum);
                    pSmt.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Common.close(pSmt);
                    Common.close(conn);
                }
                break;
            case 2:
                System.out.print("   바꾸고자 하는 매물번호  :");
                honum = sc.nextInt();
                System.out.println("바꾸고 싶은 거래방식 선택 [1] 매매 [2] 전세 [3] 월세 [4] 종료");
                int tr = sc.nextInt();
                if (tr == 4) {
                    break;
                }
                sql = "UPDATE ROOMINFO SET deposit = NULL, monthly = NULL, jeonsegeum = NULL , Sale_price = NULL WHERE house_num =?";
                try {
                    conn = Common.getConnection();
                    pSmt = conn.prepareStatement(sql);
                    pSmt.setInt(1, honum);
                    pSmt.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Common.close(pSmt);
                    Common.close(conn);
                }
                switch (tr) {
                    case 1:
                        System.out.print("  매매 가격을 알려주세요");
                        int sal = sc.nextInt();
                        sql = "UPDATE ROOMINFO SET trade_method = ?,Sale_price=? WHERE house_num=?";
                        try {
                            conn = Common.getConnection();
                            pSmt = conn.prepareStatement(sql);
                            pSmt.setString(1, "매매");
                            pSmt.setInt(2, sal);
                            pSmt.setInt(3, honum);
                            pSmt.executeUpdate();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            Common.close(pSmt);
                            Common.close(conn);
                        }
                        break;
                    case 2:
                        System.out.print("  전세 가격을 알려주세요");
                        int jeo = sc.nextInt();
                        sql = "UPDATE ROOMINFO SET trade_method=?,jeonsegeum=? WHERE house_num=?";
                        try {
                            conn = Common.getConnection();
                            pSmt = conn.prepareStatement(sql);
                            pSmt.setString(1, "전세");
                            pSmt.setInt(2, jeo);
                            pSmt.setInt(3, honum);
                            pSmt.executeUpdate();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            Common.close(pSmt);
                            Common.close(conn);
                        }
                        break;
                    case 3:
                        System.out.print("   보증금 가격 : ");
                        int dep = sc.nextInt();
                        System.out.print("   월세 가격 : ");
                        int mon = sc.nextInt();
                        sql = "UPDATE ROOMINFO SET trade_method=?,deposit=?,monthly=? WHERE house_num=?";
                        try {
                            conn = Common.getConnection();
                            pSmt = conn.prepareStatement(sql);
                            pSmt.setString(1, "월세");
                            pSmt.setInt(2, dep);
                            pSmt.setInt(3, mon);
                            pSmt.setInt(4, honum);
                            pSmt.executeUpdate();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            Common.close(pSmt);
                            Common.close(conn);
                        }
                        break;
                }
                break;
            case 3:
                System.out.print("   바꾸고자 하는 매물번호  :");
                honum = sc.nextInt();
                System.out.print("   수정 하고자 하는 전화번호  :");
                int phnum = sc.nextInt();
                sql = "UPDATE ROOMINFO SET phonenumber = ? WHERE house_num=?";
                try {
                    conn = Common.getConnection();
                    pSmt = conn.prepareStatement(sql);
                    pSmt.setInt(1, phnum);
                    pSmt.setInt(2, honum);
                    pSmt.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Common.close(pSmt);
                    Common.close(conn);
                }
                break;
            case 4:
                System.out.print("   바꾸고자 하는 매물번호  :");
                honum = sc.nextInt();
                sc.nextLine();
                System.out.print("   수정 할 비고  :");
                String rem = sc.nextLine();
                sql = "UPDATE ROOMINFO SET remark = ? WHERE house_num=?";
                try {
                    conn = Common.getConnection();
                    pSmt = conn.prepareStatement(sql);
                    pSmt.setString(1, rem);
                    pSmt.setInt(2, honum);
                    pSmt.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Common.close(pSmt);
                    Common.close(conn);
                }
                break;
            case 5:
                System.exit(0);
        }
    }

    //올룸 삭제 함수
    // 방삭제
    public void RoomDelete() {
        System.out.print("   삭제 할 매물번호를 입력하세요 : ");
        int num1 = sc.nextInt();
        String sql = "DELETE FROM LOVE_ROOM WHERE SHOUSE_NUM = ?";
        try {
            conn = Common.getConnection();
            pSmt = conn.prepareStatement(sql);
            pSmt.setInt(1, num1);
            pSmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Common.close(pSmt);
            Common.close(conn);
        }
    }

    //======================================================== 회원 탈퇴=======================================================
    public void UserDelete(List<String> id_pw) {
        System.out.println(red +
                "=====================================\n" +
                "   ·▄▄▄▄  ▄▄▄ .▄▄▌  ▄▄▄ .▄▄▄▄▄▄▄▄ .\n" +
                "   ██▪ ██ ▀▄.▀·██•  ▀▄.▀·•██  ▀▄.▀·\n" +
                "   ▐█· ▐█▌▐▀▀▪▄██▪  ▐▀▀▪▄ ▐█.▪▐▀▀▪▄\n" +
                "   ██. ██ ▐█▄▄▌▐█▌▐▌▐█▄▄▌ ▐█▌·▐█▄▄▌\n" +
                "   ▀▀▀▀▀•  ▀▀▀ .▀▀▀  ▀▀▀  ▀▀▀  ▀▀▀ \n" +
                "=====================================");
        System.out.println(blue + " [1]\uD83D\uDE2D탈퇴 [2] 나가기  :");
        int ud = sc.nextInt();
        String sql = "DELETE FROM INFO WHERE USER_MAIL =  ?";
        switch (ud) {
            case 1:
                try {
                    conn = Common.getConnection();
                    pSmt = conn.prepareStatement(sql);
                    pSmt.setString(1, id_pw.get(0));
                    pSmt.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Common.close(pSmt);
                    Common.close(conn);
                }
                break;
            case 2:
                break;
        }
    }

    //=========================================================================================================================================================
    public void loveprn(List<RoomInfoVo> LP) {
        System.out.println(blue + "=======================================================================" + exit);
        System.out.println(blue +
                "   —̳͟͞͞ \uD83D\uDC97    ██╗         ██╗    ███████╗    ████████╗        \uD83C\uDF38       \n" +
                "               ██║         ██║    ██╔════╝    ╚══██╔══╝               \n" +
                "               ██║         ██║    ███████╗       ██║    —̳͟͞͞ \uD83D\uDC97             \n" +
                "      \uD83C\uDF38       ██║         ██║    ╚════██║       ██║                  \n" +
                "               ███████╗    ██║    ███████║       ██║               —̳͟͞͞ \uD83D\uDC97  \n" +
                "    —̳͟͞͞ \uD83D\uDC97   ╚══════╝    ╚═╝    ╚══════╝       ╚═╝  \uD83C\uDF38                \n");
        System.out.println(blue + "=======================================================================" + exit);
        for (RoomInfoVo e : LP) {
            System.out.println(blue + "|  ⭐------------------⭐                                              " + exit);
            System.out.println(blue + "|   ▏                    ▏  \uD83C\uDFE0" + e.getAddress() + "                           " + exit);
            System.out.println(blue + "|   ▏" + (e.getPhoto_url()) + "               ▏          \uD83D\uDCB8" + e.getJeonsegeum() + "                                  " + exit);
            System.out.println(blue + "|   ▏                    ▏          \uD83D\uDCB8" + e.getSale_price() + "                                " + exit);
            System.out.println(blue + "|   ▏                    ▏          \uD83D\uDCB8" + e.getMonthly() + "                                " + exit);
            System.out.println(blue + "|  ⭐------------------⭐       \uD83D\uDFEB" + e.getArea() + "                                   " + exit);
            System.out.println(blue + "――――――――――――――――――――――――――――――――――――――――――――" + exit);
        }
    }

    //===============================================================================================================================================
    public void mprn() {
        System.out.println(blue + "========================================================================" + exit);
        System.out.println(blue +
                "|   —̳͟͞͞ \uD83D\uDC97     _____ __ __    _____ _____ _____ _____      \uD83C\uDF38        |\n" +
                "|               |     |  |  |  |  _  |  _  |   __|   __|               |\n" +
                "|         \uD83C\uDF38    | | | |_   _|  |   __|     |  |  |   __|   —̳͟͞͞ \uD83D\uDC97    |\n" +
                "|               |_|_|_| |_|    |__|  |__|__|_____|_____|               |\n" +
                "|         —̳͟͞͞ \uD83D\uDC97                                 —̳͟͞͞ \uD83D\uDC97            |");
        System.out.println(blue + "|======================================================================|" + exit);
        System.out.println(blue + "| \uD83C\uDF26\uFE0F     ------------------              ------------------            |" + exit);
        System.out.println(blue + "|       |" + exit + "[1]   회원 수정    " + blue + "|     \uD83C\uDF38      |" + exit + "[2]    찜 목록    " + blue + "|           |" + exit);
        System.out.println(blue + "|        ------------------              ------------------            |" + exit);
        System.out.println(blue + "|        ------------------              ------------------            |" + exit);
        System.out.println(blue + "| \uD83C\uDF38    |" + exit + "[3]   올룸 목록    " + blue + "|   \uD83C\uDF26\uFE0F        |" + exit + "[4]   회원 탈퇴   " + blue + "| \uD83C\uDF38        |" + exit);
        System.out.println(blue + "|        ------------------              ------------------            |" + exit);
        System.out.println(blue + "|        ------------------                                            |" + exit);
        System.out.println(blue + "|       |" + exit + "[5]    나가기      " + blue + "         |￣￣￣￣￣￣￣|     " + red + "   ☁\uFE0F   ⭐  " + blue + "    |");
        System.out.println(blue + "|        ------------------         |  선택하쇼   |     " + green + "       \uD83C\uDF38\uD83C\uDF38 " + blue + "    |");
        System.out.println(blue + "|                   ☁\uFE0F              |＿＿＿＿＿＿＿|  " + green + "  ☁\uFE0F     \uD83C\uDF38\uD83C\uDF38\uD83C\uDF38    " + blue + "|");
        System.out.println(blue + "|                                   (\\__/) || " + green + "             \uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38   " + blue + "|");
        System.out.println(blue + "|                                   (•ㅅ•).||  " + green + "                \uD83C\uDF38      " + blue + "|");
        System.out.println(blue + "|       ☁\uFE0F          \uD83C\uDF26\uFE0F              / . . . .づ  " + green + "                      " + blue + "|");
        System.out.println(blue + "========================================================================" + exit);
        System.out.print(blue + "|" + exit + "     " + yellow + "|  선택하쇼   |" + exit + "     " + blue + "|     " + exit);
    }

    public LoginInfoVo ExtractInfo(String ID) {
        LoginInfoVo LIV = new LoginInfoVo();
        String sql = "SELECT * FROM INFO WHERE USER_MAIL = ?";
        try {
            conn = Common.getConnection();
            pSmt = conn.prepareStatement(sql);
            pSmt.setString(1, ID);
            rs = pSmt.executeQuery();
            while (rs.next()) {
                String user_mail = rs.getString("USER_MAIL");
                String password = rs.getString("PASSWORD");
                String name = rs.getString("NAME");
                int first_id_cardnum = rs.getInt("FIRST_ID_CARDNUM");
                int phone_number = rs.getInt("PHONE_NUMBER");
                String address = rs.getString("ADDRESS");
                int gender = rs.getInt("GENDER");
                String nickname = rs.getString("NICKNAME");
                LIV.setUser_mail(user_mail);
                LIV.setPassword(password);
                LIV.setName(name);
                LIV.setFirst_id_cardnum(first_id_cardnum);
                LIV.setPhone_number(phone_number);
                LIV.setAddress(address);
                LIV.setGender(gender);
                LIV.setNickname(nickname);
            }
            Common.close(rs);
            Common.close(pSmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return LIV;
    }
    public AdminInfoVo AdminExtractInfo(String ID) {
        AdminInfoVo AIV = new AdminInfoVo();
        String sql = "SELECT * FROM ADMIN_INFO WHERE ADM_ID = ?";
        try {
            conn = Common.getConnection();
            pSmt = conn.prepareStatement(sql);
            pSmt.setString(1, ID);
            rs = pSmt.executeQuery();
            while (rs.next()) {
                String adm_id = rs.getString("ADM_ID");
                String adm_pw = rs.getString("ADM_PW");
                AIV.setADM_ID(adm_id);
                AIV.setADM_PW(adm_pw);
            }
            Common.close(rs);
            Common.close(pSmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return AIV;
    }

    //덮어 씌우는 함수
    public LoginInfoVo Overwrite(LoginInfoVo LIV, InfoUpdateVo IUV) {
        if (IUV.getUSER_MAIL() != null) {
            LIV.setUser_mail(IUV.getUSER_MAIL());
        } else if (IUV.getPASSWORD() != null) {
            LIV.setPassword(IUV.getPASSWORD());
        } else if (IUV.getNAME() != null) {
            LIV.setName(IUV.getNAME());
        } else if (IUV.getPHONE_NUMBER() != 0) {
            LIV.setPhone_number(IUV.getPHONE_NUMBER());
        } else if (IUV.getADDRESS() != null) {
            LIV.setAddress(IUV.getADDRESS());
        } else if (IUV.getNICKNAME() != null) {
            LIV.setNickname(IUV.getNICKNAME());
        } else return LIV;
        return LIV;
    }
    public AdminInfoVo AdminOverwrite(AdminInfoVo aiv, AdminInfoVo AIV) {
        if (AIV.getADM_ID() != null) {
            aiv.setADM_ID(AIV.getADM_ID());
        }
        else if (AIV.getADM_PW() != null) {
            aiv.setADM_PW(AIV.getADM_PW());
        }
        else return aiv;
        return aiv;
    }

    public void InfoUpdate(LoginInfoVo LIV, String ID) {
        String sql = "UPDATE INFO SET USER_MAIL = ?, PASSWORD = ?, NAME = ?," +
                "FIRST_ID_CARDNUM = ?,PHONE_NUMBER = ?, ADDRESS = ?, GENDER = ?, NICKNAME = ? " +
                "WHERE USER_MAIL = ?";
        try {
            conn = Common.getConnection();
            pSmt = conn.prepareStatement(sql);
            pSmt.setString(1, LIV.getUser_mail());
            pSmt.setString(2, LIV.getPassword());
            pSmt.setString(3, LIV.getName());
            pSmt.setInt(4, LIV.getFirst_id_cardnum());
            pSmt.setInt(5, LIV.getPhone_number());
            pSmt.setString(6, LIV.getAddress());
            pSmt.setInt(7, LIV.getGender());
            pSmt.setString(8, LIV.getNickname());
            pSmt.setString(9, ID);
            pSmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Common.close(pSmt);
            Common.close(conn);
        }
    }
    public void AdminInfoUpdate(AdminInfoVo AIV, String ID) {
        String sql = "UPDATE ADMIN_INFO SET ADM_ID = ?, ADM_PW = ? WHERE ADM_ID = ?";
        try {
            conn = Common.getConnection();
            pSmt = conn.prepareStatement(sql);
            pSmt.setString(1, AIV.getADM_ID());
            pSmt.setString(2, AIV.getADM_PW());
            pSmt.setString(3, ID);
            pSmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Common.close(pSmt);
            Common.close(conn);
        }
    }

    public void UserDeletefunc(String ID) {
        String sql = "DELETE FROM INFO WHERE USER_MAIL =  ?";
        try {
            conn = Common.getConnection();
            pSmt = conn.prepareStatement(sql);
            pSmt.setString(1, ID);
            pSmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Common.close(pSmt);
            Common.close(conn);
        }

    }

    public List<RoomInfoVo> RoomSelect() throws SQLException {
        // 일단 전부 뽑아온다.
        List<RoomInfoVo> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ROOMINFO";
            conn = Common.getConnection();
            pSmt = conn.prepareStatement(sql);
            rs = pSmt.executeQuery();
            while (rs.next()) {
                int house_num = rs.getInt("HOUSE_NUM");
                String user_id = rs.getString("USER_ID");
                String photo_url = rs.getString("PHOTO_URL");
                String trade_method = rs.getString("TRADE_METHOD");
                int deposit = rs.getInt("DEPOSIT");
                int monthly = rs.getInt("MONTHLY");
                int jeonsegeum = rs.getInt("JEONSEGEUM");
                int Sale_price = rs.getInt("SALE_PRICE");
                String area = rs.getString("AREA");
                java.sql.Date account_date = rs.getDate("ACCEPT_DATE");
                String address = rs.getString("ADDRESS");
                String floor1 = rs.getString("FLOOR1");
                int phonenumber = rs.getInt("PHONENUMBER");
                Date regit_date = rs.getDate("REGIT_DATE");
                String remark = rs.getString("REMARK");
                list.add(new RoomInfoVo(house_num, user_id, photo_url, trade_method, deposit, monthly, jeonsegeum,
                        Sale_price, area, account_date, address, floor1, phonenumber, regit_date, remark));
            }
        } catch (Exception e) {
        } finally {
            Common.close(rs);
            Common.close(pSmt);
            Common.close(conn);
        }
        return list;
    }

    public void RoomDel(int RoomNum) {
        String sql = "DELETE FROM RoomInfo WHERE HOUSE_NUM = ?";
        try {
            conn = Common.getConnection();
            pSmt = conn.prepareStatement(sql);
            pSmt.setInt(1, RoomNum);
            pSmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Common.close(pSmt);
            Common.close(conn);
        }
    }

    public List<RoomInfoVo> LoveList(String id) { // 찜한방 목록화
        List<RoomInfoVo> love = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT rm.house_num,rm.user_id,rm.photo_url,rm.trade_method,rm.deposit,rm.MONTHLY,rm.jeonsegeum,rm.address, rm.area, rm.sale_price " +
                    "FROM ROOMINFO rm JOIN LOVE_ROOM lr " +
                    "ON rm.house_num = lr.shouse_num WHERE lr.user_id = '" + id + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //내가 찜한 방에 대한 정보들
                love.add(new RoomInfoVo(rs.getInt("house_num"), rs.getString("user_id"), rs.getString("photo_url"), rs.getString("trade_method"),
                        rs.getInt("deposit"), rs.getInt("MONTHLY"), rs.getInt("jeonsegeum"),
                        rs.getInt("sale_price"), rs.getString("area"), null, rs.getString("address"), null,
                        0, null, null));
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return love;
    }

    public void LoveDelete(LoveRoomVo LRV) { // 찜한방 목록 Delete
        try {
            // love 내가 찜한 방의 정보들
            conn = Common.getConnection();
            stmt = conn.createStatement();
            // 찜한 방 목록에서 확인 후 삭제
            String sql = "DELETE FROM LOVE_ROOM WHERE USER_ID = '" + LRV.getUser_id() + "'" + "AND SHOUSE_NUM = '" + LRV.getShouse_num() + "'";
            rs = stmt.executeQuery(sql);
            // 방목록에서 원하는방 삭제
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<RoomInfoVo> OlumList(String id) { // 올룸 목록

        List<RoomInfoVo> olume = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM ROOMINFO WHERE USER_ID = '" + id + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int house_num = rs.getInt("HOUSE_NUM");
                String user_id = rs.getString("USER_ID");
                String photo_url = rs.getString("PHOTO_URL");
                String trade_method = rs.getString("TRADE_METHOD");
                int deposit = rs.getInt("DEPOSIT");
                int monthly = rs.getInt("MONTHLY");
                int jeonsegeum = rs.getInt("JEONSEGEUM");
                int sale_price = rs.getInt("SALE_PRICE");
                String area = rs.getString("AREA");
                Date accept_date =  rs.getDate("ACCEPT_DATE");
                String address =  rs.getString("ADDRESS");
                String floor1 = rs.getString("FLOOR1");
                int phonenumber =rs.getInt("PHONENUMBER");
                Date regit_date =rs.getDate("REGIT_DATE");
                String remark = rs.getString("REMARK");
                olume.add(new RoomInfoVo(house_num,user_id ,photo_url,trade_method, deposit,monthly,jeonsegeum,sale_price
                        ,area,accept_date,address,floor1, phonenumber,regit_date,remark));
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return olume;
    }

    public void OlumUpdate(RoomInfoVo RIV, String id) {

        try {
            String query = "UPDATE ROOMINFO SET PHTHO_URL = ?, TARADE_METHOD = ?, DEPOSIT = ?, MONTHLY = ?, JEONSEGEUM = ?," +
                    "SALE_PRICE = ?,AREA = ?, ACEEPT_DATE = ?,ADDRESS = ?,FlOOR1 = ?,PHONENUMBER = ?," +
                    "REGIT_DATE = ?, REMARK = ? WHERE HOUSE_NUM = ? ,USER_ID = ?";
            conn = Common.getConnection();
            pSmt = conn.prepareStatement(query);
            pSmt.setString(1, RIV.getPhoto_url());
            pSmt.setString(2, RIV.getTrade_method());
            pSmt.setInt(3, RIV.getDeposit());
            pSmt.setInt(4, RIV.getMonthly());
            pSmt.setInt(5, RIV.getJeonsegeum());
            pSmt.setInt(6, RIV.getSale_price());
            pSmt.setString(7, RIV.getArea());
            pSmt.setDate(8, RIV.getAccept_date());
            pSmt.setString(9, RIV.getAddress());
            pSmt.setString(10, RIV.getFloor1());
            pSmt.setInt(11, RIV.getPhonenumber());
            pSmt.setDate(12, RIV.getRegit_date());
            pSmt.setString(13, RIV.getRemark());
            pSmt.setInt(14, RIV.getHouse_num());
            pSmt.setString(15,id);
            pSmt.executeUpdate();
        } catch (Exception e) {
        } finally {
            Common.close(rs);
            Common.close(pSmt);
            Common.close(conn);
        }
    }
    public void LoveAllDelete(String ID) { // 찜한방 목록 Delete
        try {
            // love 내가 찜한 방의 정보들
            conn = Common.getConnection();
            stmt = conn.createStatement();
            // 찜한 방 목록에서 확인 후 삭제
            String sql = "DELETE FROM LOVE_ROOM WHERE USER_ID = '" + ID + "'";
            rs = stmt.executeQuery(sql);
            // 방목록에서 원하는방 삭제
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void RoomAllDel(String ID) {
        String sql = "DELETE FROM RoomInfo WHERE USER_ID = ?";
        try {
            conn = Common.getConnection();
            pSmt = conn.prepareStatement(sql);
            pSmt.setString(1, ID);
            pSmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Common.close(pSmt);
            Common.close(conn);
        }
    }

}
