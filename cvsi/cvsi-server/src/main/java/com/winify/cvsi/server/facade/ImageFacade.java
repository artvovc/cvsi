package com.winify.cvsi.server.facade;

import com.winify.cvsi.core.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Artemie on 28.06.2016.
 */
@Service
public class ImageFacade {
    @Autowired
    private ImageService imageService;
}
