package com.ics.icsoauth2server.api.category;

import com.ics.icsoauth2server.helper.ConstantExceptionMessage;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.ics.icsoauth2server.helper.ConstantExceptionMessage.*;

@NoArgsConstructor
public @Data class CategoryRequest {

    private String UUID;

    private String categoryName;

    private String description;

    public CategoryRequest(String UUID, String categoryName,String description){
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
