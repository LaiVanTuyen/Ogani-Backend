package com.vn.OganiBE.service.impl;


import com.vn.OganiBE.entity.Tag;
import com.vn.OganiBE.exception.NotFoundException;
import com.vn.OganiBE.model.request.CreateTagRequest;
import com.vn.OganiBE.repository.TagRepository;
import com.vn.OganiBE.service.TagService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> getListTag() {
        // TODO Auto-generated method stub
        return tagRepository.findAll(Sort.by("id").descending());
    }

    @Override
    public Tag createTag(CreateTagRequest request) {
        Tag tag = new Tag();
        tag.setName(request.getName());
        tag.setEnable(false);
        tagRepository.save(tag);
        return tag;
    }

    @Override
    public Tag updateTag(long id, CreateTagRequest request) {
        // TODO Auto-generated method stub

        Tag tag = tagRepository.findById(id).orElseThrow(()-> new NotFoundException("Not Foud Tag"));
        tag.setName(request.getName());
        tagRepository.save(tag);
        return tag;
    }

    @Override
    public void deleleTag(long id) {
        // TODO Auto-generated method stub
        Tag tag = tagRepository.findById(id).orElseThrow(()-> new NotFoundException("Not Foud Tag"));
        tagRepository.delete(tag);
    }

    @Override
    public void enableTag(long id){
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Tag With Id: " + id));
        if(tag.isEnable()){
            tag.setEnable(false);
        } else{
            tag.setEnable(true);
        }
        tagRepository.save(tag);
    }
    
}
