package com.winify.cvsi.core.dto.templates.request;


import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

public class ImageUploadClientRequest implements Serializable{
    private List<MultipartFile> files;

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
}
