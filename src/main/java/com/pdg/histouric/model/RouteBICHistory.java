package com.pdg.histouric.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "route_bic_history")
public class RouteBICHistory {

    @EmbeddedId
    private RouteBICHistoryPK id;

    @Column(name = "bic_order")
    private int bicOrder;

    @ManyToOne
    @JoinColumn(name = "route_id", insertable = false, updatable = false)
    private Route route;

    @ManyToOne
    @JoinColumn(name = "bic_id", insertable = false, updatable = false)
    @JoinColumn(name = "history_id", insertable = false, updatable = false)
    private BICHistory bicHistory;

}
