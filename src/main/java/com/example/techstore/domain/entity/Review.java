package com.example.techstore.domain.entity;

import com.example.techstore.domain.entity.common.UserDateAuditing;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "reviews")
public class Review extends UserDateAuditing {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(insertable = false, updatable = false, nullable = false, columnDefinition = "CHAR(36)")
    private String id;

    @Column(nullable = false)
    private Integer star;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_REVIEW_USER"))
    private User user;

    @OneToOne
    @JoinColumn(name = "order_detail_id", foreignKey = @ForeignKey(name = "FK_REVIEW_ORDER_DETAIL"))
    private OrderDetail orderDetail;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "review")
    private List<ReviewFile> files;

}
