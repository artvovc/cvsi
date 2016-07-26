package com.winify.cvsi.core.dto.comparator;

import com.winify.cvsi.core.dto.templates.response.ProductTemplateResponse;

import java.util.Comparator;

public class CreatedDateComparator implements Comparator<ProductTemplateResponse> {
    @Override
    public int compare(ProductTemplateResponse o1, ProductTemplateResponse o2) {
        return o1.getCreatedDate().compareTo(o2.getCreatedDate());
    }
}
