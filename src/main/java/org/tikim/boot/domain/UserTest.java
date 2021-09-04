package org.tikim.boot.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserTest {
    private int id;
    private String account;
    private String password;
    private String nickname;
}
