package com.jiem.blog.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

/**
 * 文章
 * @TableName article
 */
@Data
@ToString
public class Article implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createtime;

    /**
     * 标题
     */
    private String title;

    /**
     * 
     */
    private String author;

    /**
     *
     */
    private String content;

    /**
     *
     */
    private String description;

    private static final long serialVersionUID = 1L;

}