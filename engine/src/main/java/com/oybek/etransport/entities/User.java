package com.oybek.etransport.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class User implements Serializable {

    @Column(name = "user_app")
    private String app;

    @Column(name = "user_id")
    private String id;

    public User(String app, String id) {
        this.app = app;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(app, user.app) &&
                Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(app, id);
    }
}

