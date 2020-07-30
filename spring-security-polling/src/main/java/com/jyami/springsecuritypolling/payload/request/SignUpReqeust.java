package com.jyami.springsecuritypolling.payload.request;

import com.jyami.springsecuritypolling.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Created by jyami on 2020/07/31
 */
@NoArgsConstructor
@Getter
public class SignUpReqeust {

    @NotBlank
    @Size(min = 4, max = 40)
    private String name;

    @NotBlank
    @Size(min = 3, max = 15)
    private String username;

    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

    public User toEntity(){
        return User.builder()
                .email(this.email)
                .name(this.name)
                .username(this.username)
                .password(this.password)
                .build();
    }
}
