package com.jiho.shopping.dto;

import com.jiho.shopping.entity.User;
import lombok.Data;

@Data
public class SignUpDto {
    private String nick;
    private String username;
    private String password;
    private String phoneNum;
    private String address;

    public User toEntity(){
        return User.builder()
                .username(username)
                .nick(nick)
                .password(password)
                .phoneNum(phoneNum)
                .address(address)
                .build();
    }
}
