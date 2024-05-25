package com.caveup.lasr.entity;

import lombok.Data;

/**
 * @author xueliang.wu
 */
@Data
public class ElementAttributes extends Attributes {
    private String alternativeText;
    private Integer width;
    private Integer height;

    private String hash;
    private String ext;
    private String mime;
    private Double size;
    private String url;
    private String previewUrl;
    private String provider;
}
