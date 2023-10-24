package com.restaurant.store.user.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import com.restaurant.store.common.domain.BaseEntity;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String userId;

    @NonNull
    @Column(nullable = false)
    private String password;

    @NonNull
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private UserRole userRole;

}


