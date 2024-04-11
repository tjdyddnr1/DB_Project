package com.kh.Olumgollum_Project.LoginInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfoVo {
    private String user_mail; // 유저 이메일
    private String password; //비밀번호
    private String name; // 이름
    private int first_id_cardnum; //생년월일
    private int phone_number; // 전화번호
    private String address; // float 급여 정보이고 소수점 이하가 존재
    private int gender; // 성과급
    private String nickname; //닉네임
}
