package com.Bioaqua.global.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.Bioaqua.global.dto.BillingDto;
import com.Bioaqua.global.entity.Billing;

@Mapper(componentModel = "spring")
public interface BillingMapper {

    @Mapping(source = "totalAmount", target = "totalAmount")
    @Mapping(source = "project.projectName", target = "projectName")
    @Mapping(source = "user.userName", target = "userName")
    BillingDto map(Billing entity);

    List<BillingDto> map(List<Billing> entities);

    @Mapping(source = "totalAmount", target = "totalAmount")
    @Mapping(source = "projectName", target = "project.projectName")
    @Mapping(source = "userName", target = "user.userName")
    Billing unMap(BillingDto dto);

    @Mapping(source = "totalAmount", target = "totalAmount")
    @Mapping(source = "projectName", target = "project.projectName")
    @Mapping(source = "userName", target = "user.userName")
    void updateEntityFromDto(@MappingTarget Billing entity, BillingDto dto);
}
