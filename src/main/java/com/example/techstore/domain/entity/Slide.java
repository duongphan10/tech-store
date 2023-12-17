package com.example.techstore.domain.entity;


import com.example.techstore.domain.entity.common.UserDateAuditing;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "slides")
public class Slide extends UserDateAuditing {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(insertable = false, updatable = false, nullable = false, columnDefinition = "CHAR(36)")
    private String id;

    @Column(nullable = false)
    private String avatar;

    @Column(nullable = false)
    private Integer position;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "FK_PRODUCT_SLIDE"))
    private Product product;
}
