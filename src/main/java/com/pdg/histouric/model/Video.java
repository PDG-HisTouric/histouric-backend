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
public class Video {
    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "video_id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "video_url", nullable = false)
    private String videoUrl;

    @Column(name = "video_start_time", nullable = false)
    private int startTime;

    @ManyToOne
    @JoinColumn(name = "history_id", nullable = false)
    private History history;
}
