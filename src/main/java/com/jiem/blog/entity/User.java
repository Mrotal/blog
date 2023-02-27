package com.jiem.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName user
 */
@Data
public class User implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private String introduction;

    /**
     * 
     */
    private String avatar;

}