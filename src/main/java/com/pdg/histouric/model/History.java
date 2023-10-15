package com.pdg.histouric.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class History {
    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "history_id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "history_title", nullable = false)
    private String title;

    @Column(name = "history_audiourl", nullable = false)
    private String audioUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private HistouricUser owner;

    @OneToMany(mappedBy = "history")
    private List<Video> videos;

    @OneToMany(mappedBy = "history")
    private  List<Text> texts;

    @OneToMany(mappedBy = "history")
    private List<HistoryImage> images;

    @OneToOne
    @JoinColumn(name = "audio_id")
    private Audio audio;
}