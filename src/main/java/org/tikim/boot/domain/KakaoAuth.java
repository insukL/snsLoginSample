package org.tikim.boot.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoAuth {
    private String token_type;
    private String access_token;
    private int expires_in;
    private String refresh_token;
    private int refresh_token_expires_in;
}
