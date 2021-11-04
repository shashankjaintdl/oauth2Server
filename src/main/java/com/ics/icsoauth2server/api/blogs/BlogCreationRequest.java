package com.ics.icsoauth2server.api.blogs;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

import static com.ics.icsoauth2server.helper.ConstraintValidationMessage.*;

public @Data class BlogCreationRequest extends BlogBaseRequest{

    @NotNull(message = BLOG_TOPIC_NOT_NULL)
    @NotBlank(message = BLOG_TOPIC_NOT_BLANK)
    @NotEmpty(message = BLOG_TOPIC_NOT_EMPTY)
    private String title;

    @NotNull(message = BLOG_CONTENT_NOT_NULL)
    @NotEmpty(message = BLOG_CONTENT_NOT_EMPTY)
    private String content;

    private Set<String> tags;
}
