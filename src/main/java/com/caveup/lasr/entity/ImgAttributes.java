package com.caveup.lasr.entity;

import lombok.Data;

/**
 * @author xueliang.wu
 */
@Data
public class ImgAttributes extends Attributes {
    private Integer width;
    private Integer height;
    private String url;
    private String previewUrl;
    private String provider;
}
