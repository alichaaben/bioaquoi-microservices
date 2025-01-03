package com.Bioaqua.global.service;

import java.util.List;

import com.Bioaqua.global.entity.Components;

public interface ComponentsService {

    Components findById(Long id);

    List<Components> findAll();

    Components insert(Components Entity);

    Components update(Components Entity);

    void deleteById(Long id);
}