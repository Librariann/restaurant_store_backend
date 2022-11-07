package com.restaurant.store.Restaurant.domain;

import com.restaurant.store.common.domain.BaseEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
@Entity
@Table(name = "restaurant")
public class Restaurant extends BaseEntity {
    @Id
    @Comment("자동증가값")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Comment("가게명")
    @Column(nullable = false)
    private String title;

    @NonNull
    @Comment("상세정보")
    @Column(length = 100, columnDefinition = "TEXT", nullable = false)
    private String detailInfo;

    @Comment("해시태그")
    private String hashTag;

    @Comment("가게 주소")
    private String address;

    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    @Comment("삭제여부 : 0 - 삭제, 1 - 미삭제")
    private boolean enabled;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER)
    private List<LastVisitDate> lastVisitDates = new ArrayList<>();
}
