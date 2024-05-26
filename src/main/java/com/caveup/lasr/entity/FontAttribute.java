package com.caveup.lasr.entity;

import lombok.Data;

import java.util.Map;

/**
 * @author xueliang.wu
 */
@Data
public class FontAttribute extends Attributes {

    private Map<String, Object> img;
    private Map<String, Object> file;

}
