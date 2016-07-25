package com.winify.cvsi.core.dto.comparator;

import com.winify.cvsi.core.dto.templates.ProductTemplate;

import java.util.Comparator;

public class CreatedDateComparator implements Comparator<ProductTemplate> {
    @Override
    public int compare(ProductTemplate o1, ProductTemplate o2) {
        return o1.getCreatedDate().compareTo(o2.getCreatedDate());
    }
}
