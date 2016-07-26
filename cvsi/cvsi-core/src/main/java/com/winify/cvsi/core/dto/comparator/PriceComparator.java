package com.winify.cvsi.core.dto.comparator;

import com.winify.cvsi.core.dto.templates.response.ProductTemplateResponse;

import java.util.Comparator;

public class PriceComparator implements Comparator<ProductTemplateResponse> {
    @Override
    public int compare(ProductTemplateResponse o1, ProductTemplateResponse o2) {
        if (o1.getPrice() == null)
            return -1;
        if (o2.getPrice() == null)
            return 1;
        if (o1.getPrice() == null && o1.getPrice() == null)
            return 0;
        return o1.getPrice().compareTo(o2.getPrice());
    }
}
