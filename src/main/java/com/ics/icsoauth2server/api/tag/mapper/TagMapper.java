package com.ics.icsoauth2server.api.tag.mapper;

import com.ics.icsoauth2server.api.tag.TagRequest;
import com.ics.icsoauth2server.api.tag.TagResponse;
import com.ics.icsoauth2server.domain.Tags;

import java.util.List;

public class TagMapper {

    public Tags createMap(TagRequest request){
        Tags entity = new Tags();
        entity.setUUID(request.getUUID());
        entity.setTagName(request.getCategoryName());
        entity.setDescription(request.getDescription());
        return entity;
    }

    public List<TagResponse> tagResponseMapper(List<Tags> tags){
        return null;
    }
}
