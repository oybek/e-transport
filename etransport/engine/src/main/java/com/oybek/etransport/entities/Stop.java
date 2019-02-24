package com.oybek.etransport.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stop")
public class Stop {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "type")
    private String type;

    @ElementCollection
    @CollectionTable(name = "names", joinColumns = @JoinColumn(name = "stop_id"))
    private List<String> names;

    @Column(name = "direction")
    private String direction;

    @Column(name = "latitude")
    private float latitude;

    @Column(name = "longitude")
    private float longitude;

    @Column(name = "updated")
    private Timestamp updated;

    @OneToMany(mappedBy = "stop", cascade = CascadeType.ALL)
    private List<Reach> reaches;

    @JsonIgnore
    @JoinColumn(name = "id", updatable = false, insertable = false)
    @OneToMany
    private List<Request> requests;
}
