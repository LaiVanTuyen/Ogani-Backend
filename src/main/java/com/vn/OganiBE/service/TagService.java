package com.vn.OganiBE.service;



import com.vn.OganiBE.entity.Tag;
import com.vn.OganiBE.model.request.CreateTagRequest;

import java.util.List;

public interface TagService {
    
    List<Tag> getListTag();

    Tag createTag(CreateTagRequest request);

    Tag updateTag(long id,CreateTagRequest request);

    void enableTag(long id);

    void deleleTag(long id);

}
