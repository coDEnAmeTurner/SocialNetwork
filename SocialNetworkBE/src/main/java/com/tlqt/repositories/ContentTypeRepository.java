/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.repositories;

import com.tlqt.pojo.ContentType;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ContentTypeRepository {
    public List<ContentType> getContentTypes();
    
    ContentType getContentTypeById(int id);
    
    ContentType getContentTypeByPostId(int postId);
}
