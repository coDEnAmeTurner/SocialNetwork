/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tlqt.services;

import com.tlqt.pojo.Alumnus;
import com.tlqt.pojo.Lecturer;

/**
 *
 * @author Admin
 */
public interface LecturerService {

    void addLecturer(Lecturer l);

    Lecturer getLecturerByTypicalUserId(int lecturerId);

    void update(Lecturer l);
}
