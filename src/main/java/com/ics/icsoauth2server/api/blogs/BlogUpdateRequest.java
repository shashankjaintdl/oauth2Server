package com.ics.icsoauth2server.api.blogs;

import com.ics.icsoauth2server.helper.ConstraintValidationMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

import static com.ics.icsoauth2server.helper.ConstraintValidationMessage.*;

@AllArgsConstructor
@NoArgsConstructor
public  @Data  class BlogUpdateRequest extends BlogBaseRequest{

    @NotNull(message = POST_TITLE_NOT_NULL)
    @NotBlank(message = POST_TITLE_NOT_BLANK)
    @NotEmpty(message = POST_TITLE_NOT_EMPTY)
    private String title;

    @NotNull(message = POST_CONTENT_NOT_NULL)
    @NotEmpty(message = POST_CONTENT_NOT_EMPTY)
    private String content;

    private Set<String> tags;

}
