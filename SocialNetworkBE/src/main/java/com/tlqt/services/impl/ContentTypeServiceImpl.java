/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.services.impl;

import com.tlqt.services.ContentTypeService;
import com.tlqt.pojo.ContentType;
import com.tlqt.repositories.ContentTypeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class ContentTypeServiceImpl implements ContentTypeService {
    @Autowired
    ContentTypeRepository contentTypeRepo;
    
    @Override
    public List<ContentType> getContentTypes() {
        return this.contentTypeRepo.getContentTypes();
    }

    @Override
    public ContentType getContentTypeById(int id) {
        return this.contentTypeRepo.getContentTypeById(id);
    }

    @Override
    public ContentType getContentTypeByPostId(int postId) {
        return contentTypeRepo.getContentTypeByPostId(postId);
    }
}
