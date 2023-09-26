package com.pdg.histouric.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Text {
    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "text_id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "text_start_time", nullable = false)
    private int startTime;

    @ManyToOne
    @JoinColumn(name = "history_id", nullable = false)
    private History history;
}