package org.tikim.boot.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoogleUser {
    private String id;
    private Long longId;
    private String email;
    private String verified_email;
    private String name;
    private String given_name;
    private String family_name;
    private String picture;
    private String locate;

    public void setLongId() {
        this.longId = Long.parseLong(this.id);
    }
}
