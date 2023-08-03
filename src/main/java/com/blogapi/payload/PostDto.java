package com.blogapi.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PostDto {

    private long post_id;

    @NotEmpty
    @Size(min = 2,message ="Title should be at least 2 character")
    private String title;


    @NotEmpty(message = "Should not be empty")
    @Size(min = 4)
    private String descriptions;

    @NotEmpty(message = "content is not empty")

    private String content;

}
