package com.ics.icsoauth2server.api.tag;

import lombok.Data;
import lombok.NoArgsConstructor;

import static com.ics.icsoauth2server.helper.ConstantExceptionMessage.*;

@NoArgsConstructor
public @Data class TagRequest {

    private String UUID;

    private String categoryName;

    private String description;

    public TagRequest(String UUID, String categoryName, String description){
        if(categoryName.isEmpty()){
            throw new IllegalArgumentException(CATEGORY_NAME_EMPTY);
        }
        if (UUID.isEmpty()){
            throw new IllegalArgumentException(CATEGORY_UUID_EMPTY);
        }
        this.UUID = UUID;
        this.categoryName = categoryName;
        this.description  = description;
    }

}
