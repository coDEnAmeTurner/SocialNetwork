/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tlqt.repositories;

import com.tlqt.pojo.Alumnus;
import com.tlqt.pojo.Lecturer;

/**
 *
 * @author Admin
 */
public interface LecturerRepository {

    void addLecturer(Lecturer l);

    Lecturer getLecturerByTypicalUserId(int lecturerId);

    void update(Lecturer l);
}
