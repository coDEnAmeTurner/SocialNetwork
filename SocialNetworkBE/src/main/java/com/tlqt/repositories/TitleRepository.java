/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tlqt.repositories;

import com.tlqt.pojo.Title;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface TitleRepository {

    Title getTitleById(int titleId);

    List<Title> getTitles();
    
}
