package com.caveup.lasr.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @author xueliang.wu
 */
@Data
public class Attributes {

    private String name;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date publishedAt;
}
