package com.ics.icsoauth2server.api.blogs;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

import static com.ics.icsoauth2server.helper.ConstraintValidationMessage.*;

public @Data class BlogCreationRequest extends BlogBaseRequest{

    @NotNull(message = POST_TITLE_NOT_NULL)
    @NotBlank(message = POST_TITLE_NOT_BLANK)
    @NotEmpty(message = POST_TITLE_NOT_EMPTY)
    private String title;

    @NotNull(message = POST_CONTENT_NOT_NULL)
    @NotEmpty(message = POST_CONTENT_NOT_EMPTY)
    private String content;

    private Set<String> tags;
}
