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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private HistouricUser owner;

    @OneToMany(mappedBy = "history", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Video> videos;

    @OneToMany(mappedBy = "history", orphanRemoval = true, cascade = CascadeType.ALL)
    private  List<Text> texts;

    @OneToMany(mappedBy = "history", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<HistoryImage> images;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "audio_id")
    private Audio audio;

    @OneToMany(mappedBy = "history", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<BICHistory> bicHistories;

    public History cloneHistory() {
        List<Video> videos = this.videos.stream()
                .map(Video::cloneVideo)
                .toList();
        List<HistoryImage> images = this.images.stream()
                .map(HistoryImage::cloneHistoryImage)
                .toList();
        return History.builder()
                .id(this.id)
                .title(this.title)
                .owner(this.owner)
                .videos(videos)
                .texts(this.texts)
                .images(images)
                .audio(this.audio.cloneAudio())
                .bicHistories(this.bicHistories)
                .build();
    }
}