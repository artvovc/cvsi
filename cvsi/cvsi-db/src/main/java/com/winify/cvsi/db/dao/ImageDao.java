package com.winify.cvsi.db.dao;

import com.winify.cvsi.db.model.Image;

import java.util.Set;

public interface ImageDao extends CrudOperations<Image, Long> {
    Set<Image> getImages(Long productId);

    Image getDefaultImage(Long productId, Long userId);
}
