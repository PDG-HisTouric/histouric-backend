package com.pdg.histouric.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Nickname {

    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "nickname_id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @ManyToOne
    @JoinColumn(name = "bic_id")
    private BIC bic;
}
