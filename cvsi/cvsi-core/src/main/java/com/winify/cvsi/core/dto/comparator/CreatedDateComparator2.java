package com.winify.cvsi.core.dto.comparator;

import com.winify.cvsi.core.dto.ConversationDto;
import com.winify.cvsi.core.dto.templates.response.ProductTemplateResponse;

import java.util.Comparator;

public class CreatedDateComparator2 implements Comparator<ConversationDto> {
    @Override
    public int compare(ConversationDto o1, ConversationDto o2) {
        return o1.getCreatedDate().compareTo(o2.getCreatedDate());
    }
}
