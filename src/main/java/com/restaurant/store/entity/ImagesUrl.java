package com.restaurant.store.entity;

import com.restaurant.store.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
@Entity
@Table(name = "image_url")
public class ImagesUrl extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String url;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
