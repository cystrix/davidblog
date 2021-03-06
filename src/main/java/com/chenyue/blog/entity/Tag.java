package com.chenyue.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    private Integer tagId;
    private String tagName;
    private String tagDescription;


    public Tag(Integer tagId){
        this.tagId = tagId;
    }

    /*non-database field*/
    private Integer articleCount;
}
