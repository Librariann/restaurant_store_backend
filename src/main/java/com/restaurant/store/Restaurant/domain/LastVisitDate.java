package com.restaurant.store.Restaurant.domain;

import com.restaurant.store.common.domain.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
@Entity
@Table(name = "last_visit_date")
public class LastVisitDate extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "restaurant_id", insertable = false, updatable = false)
    private Long restaurantId;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}
