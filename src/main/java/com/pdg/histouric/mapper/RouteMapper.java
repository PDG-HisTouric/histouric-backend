package com.pdg.histouric.mapper;

import com.pdg.histouric.dto.CreateBICForRouteDTO;
import com.pdg.histouric.dto.CreateRouteDTO;
import com.pdg.histouric.dto.ResponseRouteDTO;
import com.pdg.histouric.model.Route;
import com.pdg.histouric.model.RouteBICHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RouteMapper {
    @Mapping(target = "owner.id", source = "ownerId")
    @Mapping(target = "routeBICHistories", source = "bicList")
    Route fromDTOToRoute(CreateRouteDTO createRouteDTO);
    @Mapping(target = "id.bicHistoryPK.bicId", source = "bicId")
    @Mapping(target = "id.bicHistoryPK.historyId", source = "historyId")
    RouteBICHistory fromCreateBICForRouteDTOToRouteBICHistory(CreateBICForRouteDTO bicList);
    ResponseRouteDTO fromRouteToDTO(Route route);
}
