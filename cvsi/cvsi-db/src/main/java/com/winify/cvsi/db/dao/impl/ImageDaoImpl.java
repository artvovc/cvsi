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
    public Set<Image> getImages(Long productId, Long userId) {
        return new HashSet<>(this.getCurrentSession()
                .createQuery("SELECT i FROM Image AS i WHERE i.product.id = :productId AND i.product.user.id = :userId")
                .setParameter("productId", productId)
                .setParameter("userId", userId)
                .getResultList());
    }

    @Override
    public Image getDefaultImage(Long productId, Long userId) {
        return this.getCurrentSession()
                .createQuery("SELECT i FROM Image AS i WHERE i.product.id = :productId AND i.product.user.id = :userId AND i.imageType = :imageType", clazz)
                .setParameter("productId", productId)
                .setParameter("userId", userId)
                .setParameter("imageType", ImageType.DEFAULT_IMAGE)
                .getSingleResult();
    }
}
