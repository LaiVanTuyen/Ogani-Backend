package com.vn.OganiBE.service;


import com.vn.OganiBE.entity.Image;

import java.util.List;

public interface ImageService {
    List<Image> getListImage();

    Image getImageById(long id);

    Image save(Image image);

    List<Image> getListByUser(long userId);

    void deleteImage(long id);
}
