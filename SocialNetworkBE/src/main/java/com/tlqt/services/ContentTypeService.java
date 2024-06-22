/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tlqt.services;

import com.tlqt.pojo.ContentType;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ContentTypeService {

    List<ContentType> getContentTypes(); 
    
    ContentType getContentTypeById(int id);
    
    ContentType getContentTypeByPostId(int postId);
    
}
