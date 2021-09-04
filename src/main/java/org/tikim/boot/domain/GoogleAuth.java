package org.tikim.boot.domain;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GoogleAuth {
    private String access_token;
    private String expires_in;
    private String refresh_token;
    private String refresh_token_expires_in;
    private String scope;
    private String token_type;
    private String id_token;
}
