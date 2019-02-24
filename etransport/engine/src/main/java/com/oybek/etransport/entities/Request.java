package com.oybek.etransport.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "request")
public class Request {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stop_id")
    private Stop stop;

    @Column(name = "time")
    private Timestamp updated;

    @Embedded
    @Column(name = "user")
    private User user;

    public Request(Stop stop, Timestamp updated, User user) {
        this.stop = stop;
        this.updated = updated;
        this.user = user;
    }
}
