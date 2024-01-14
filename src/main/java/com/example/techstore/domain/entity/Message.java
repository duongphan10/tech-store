package com.example.techstore.domain.entity;

import com.example.techstore.domain.entity.common.UserDateAuditing;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "messages")
public class Message extends UserDateAuditing {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(insertable = false, updatable = false, nullable = false, columnDefinition = "CHAR(36)")
    private String id;

    @Column(nullable = true)
    private String message;

    @ManyToOne
    @JoinColumn(name = "room_id", foreignKey = @ForeignKey(name = "FK_ROOM_MESSAGE"))
    private Room room;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "message")
    private List<File> files;
}