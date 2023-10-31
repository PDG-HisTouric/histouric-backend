package com.pdg.histouric.mapper;

import com.pdg.histouric.dto.ResponseRouteForRouteThemeDTO;
import com.pdg.histouric.dto.ResponseRouteThemeDTO;
import com.pdg.histouric.model.Route;
import com.pdg.histouric.model.RouteTheme;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RouteThemeMapper {
    ResponseRouteThemeDTO fromRouteTheme(RouteTheme routeTheme);

}
