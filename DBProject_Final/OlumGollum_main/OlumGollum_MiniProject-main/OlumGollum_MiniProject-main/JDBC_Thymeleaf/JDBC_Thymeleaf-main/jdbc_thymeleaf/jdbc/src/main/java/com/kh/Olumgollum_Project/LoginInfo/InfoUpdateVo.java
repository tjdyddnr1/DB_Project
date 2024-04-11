package com.kh.Olumgollum_Project.LoginInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InfoUpdateVo {
    private String USER_MAIL;
    private String PASSWORD;
    private String NAME;
    private int PHONE_NUMBER;
    private  String ADDRESS;
    private String NICKNAME;
}
