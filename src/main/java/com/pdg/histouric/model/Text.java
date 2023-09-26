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
public class Text {
    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "text_id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "text_content", nullable = false)
    private String content;

    @Column(name = "text_start_time", nullable = false)
    private int startTime;

    @ManyToOne
    @JoinColumn(name = "history_id", nullable = false)
    private History history;
}