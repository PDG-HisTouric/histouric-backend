package com.pdg.histouric.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "bic_history")
public class BICHistory {
    @EmbeddedId
    private BICHistoryPK id;

    @ManyToOne
    @JoinColumn(name = "bic_id", insertable = false, updatable = false)
    private BIC bic;

    @ManyToOne
    @JoinColumn(name = "history_id", insertable = false, updatable = false)
    private History history;

    @OneToMany(mappedBy = "bicHistory")
    private List<RouteBICHistory> routeBICHistories;
}
