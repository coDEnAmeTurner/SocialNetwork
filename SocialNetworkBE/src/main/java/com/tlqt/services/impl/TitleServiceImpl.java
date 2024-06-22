/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.services.impl;

import com.tlqt.pojo.Title;
import com.tlqt.repositories.TitleRepository;
import com.tlqt.services.TitleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class TitleServiceImpl implements TitleService {
    @Autowired
    private TitleRepository titleRepository;
    
    @Override
    public List<Title> getTitles() {
        return titleRepository.getTitles();
    }

    @Override
    public Title getTitleById(int titleId) {
        return titleRepository.getTitleById(titleId);
    }
}
