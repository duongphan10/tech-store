package com.example.techstore.domain.entity;

import com.example.techstore.domain.entity.common.UserDateAuditing;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "discount_codes")
public class DiscountCode extends UserDateAuditing {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(insertable = false, updatable = false, nullable = false, columnDefinition = "CHAR(36)")
    private String id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false, name = "discount_amount")
    private Long discountAmount;

    @Column(nullable = false, name = "start_date")
    private LocalDateTime startDate;

    @Column(nullable = false, name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(nullable = false, name = "quantity")
    private Long quantity;

    @Column(nullable = false)
    private Boolean type;

    @ManyToMany(mappedBy = "discountCodes")
    private List<User> users;

}
