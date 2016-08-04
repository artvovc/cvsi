package com.winify.cvsi.db.dao.impl;

import com.winify.cvsi.db.dao.ImageDao;
import com.winify.cvsi.db.model.Image;
import com.winify.cvsi.db.model.enums.ImageType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Repository
@Transactional
public class ImageDaoImpl extends AbstractDao<Image, Long> implements ImageDao {
    public ImageDaoImpl() {
        super(Image.class);
    }

    @Override
    public Set<Image> getImages(Long productId) {
        return new HashSet<>(this.getCurrentSession()
                .createQuery("SELECT i FROM Image AS i WHERE i.product.id = :productId")
                .setParameter("productId", productId)
                .getResultList());
    }

    @Override
    public Image getDefaultImage(Long productId) {
        return this.getCurrentSession()
                .createQuery("SELECT i FROM Image AS i WHERE i.product.id = :productId AND i.imageType = :imageType", clazz)
                .setParameter("productId", productId)
                .setParameter("imageType", ImageType.DEFAULT_IMAGE)
                .getSingleResult();
    }
}
