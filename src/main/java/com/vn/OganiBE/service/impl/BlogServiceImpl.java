package com.vn.OganiBE.service.impl;


import com.vn.OganiBE.entity.Blog;
import com.vn.OganiBE.entity.Image;
import com.vn.OganiBE.entity.Tag;
import com.vn.OganiBE.entity.User;
import com.vn.OganiBE.exception.NotFoundException;
import com.vn.OganiBE.model.request.CreateBlogRequest;
import com.vn.OganiBE.repository.BlogRepository;
import com.vn.OganiBE.repository.ImageRepository;
import com.vn.OganiBE.repository.TagRepository;
import com.vn.OganiBE.repository.UserRepository;
import com.vn.OganiBE.service.BlogService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    private final TagRepository tagRepository;

    private final ImageRepository imageRepository;

    private final UserRepository userRepository;

    public BlogServiceImpl(BlogRepository blogRepository, TagRepository tagRepository, ImageRepository imageRepository, UserRepository userRepository) {
        this.blogRepository = blogRepository;
        this.tagRepository = tagRepository;
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Blog> getList() {
        // TODO Auto-generated method stub
        return blogRepository.findAll(Sort.by("id").descending());
    }

    @Override
    public Blog getBlog(long id){
        return blogRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Blog"));
    }

    @Override
    public Blog createBlog(CreateBlogRequest request) {
        // TODO Auto-generated method stub
        Blog blog = new Blog();
        blog.setTitle(request.getTitle());
        blog.setDescription(request.getDescription());
        blog.setContent(request.getContent());
        Image image = imageRepository.findById(request.getImageId()).orElseThrow(() -> new NotFoundException("Not Found Image"));
        blog.setImage(image);
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(()-> new NotFoundException("Not Found User"));
        blog.setUser(user);
        blog.setCreateAt(new Timestamp(System.currentTimeMillis()));
        Set<Tag> tags = new HashSet<>();
        for(Long tagId : request.getTags()){
            Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new NotFoundException("Not Found Tag"));
            tags.add(tag);
        }
        blog.setTags(tags);
        blogRepository.save(blog);
        return blog;
    }

    @Override
    public Blog updateBlog(long id, CreateBlogRequest request) {
        // TODO Auto-generated method stub
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Blog"));
        blog.setTitle(request.getTitle());
        blog.setDescription(request.getDescription());
        blog.setContent(request.getContent());
        Image image = imageRepository.findById(request.getImageId()).orElseThrow(() -> new NotFoundException("Not Found Image"));
        blog.setImage(image);
        Set<Tag> tags = new HashSet<>();
        for(Long tagId : request.getTags()){
            Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new NotFoundException("Not Found Tag"));
            tags.add(tag);
        }
        blog.setTags(tags);
        blogRepository.save(blog);
        return blog;
    }

    @Override
    public void deleteBlog(long id) {
        // TODO Auto-generated method stub
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Blog"));
        blog.getTags().remove(this);
        blogRepository.delete(blog);
    }

    @Override
    public List<Blog> getListNewest(int limit) {
        // TODO Auto-generated method stub
        List<Blog> list = blogRepository.getListNewest(limit);
        return list;
    }


}
