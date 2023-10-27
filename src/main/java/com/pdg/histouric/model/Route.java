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
public class Route {
    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "route_id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "route_name", nullable = false)
    private String name;

    @Column(name = "route_description", nullable = false, length = 3000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private HistouricUser owner;

    @ManyToOne
    @JoinColumn(name = "route_theme_id")
    private RouteTheme theme;

    @OneToMany(mappedBy = "route")
    private List<RouteBICHistory> routeBICHistories;
}
