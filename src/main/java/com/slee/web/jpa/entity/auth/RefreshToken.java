package com.slee.web.jpa.entity.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter @Setter
@NoArgsConstructor
@Table(name = "refresh_token")
@Entity
public class RefreshToken implements Persistable<String> {

    @Id
    private String key;
    private String value;

    private String test;

    public RefreshToken updateValue(String token) {
        this.value = token;
        return this;
    }

    @Builder
    public RefreshToken(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public boolean isNew() {
        return false;
    }
}
