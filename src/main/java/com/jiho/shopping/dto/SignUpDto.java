package com.jiho.shopping.dto;

import com.jiho.shopping.entity.User;
import lombok.Data;

@Data
public class SignUpDto {
    private String nick;
    private String id;
    private String pw;
    private String phoneNum;
    private String address;

    public User toEntity(){
        return User.builder()
                .id(id)
                .nick(nick)
                .pw(pw)
                .phoneNum(phoneNum)
                .address(address)
                .build();
    }
}
