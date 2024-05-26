package com.caveup.lasr.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.util.Base64Utils;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * @author xw80329
 */
@Slf4j
public class Base64Helper {

    public static final String DATA_IMAGE_PNG_BASE_64 = "data:image/png;base64,";

    /**
     * dump base64 to file
     *
     * @param base64Str
     * @return
     */
    public static Optional<File> base64ToFile(String base64Str) {
        try {
            File tmp = File.createTempFile("data_", ".jpg");
            byte[] content = Base64Utils.decodeFromString(base64Str);
            FileUtils.writeByteArrayToFile(tmp, content);
            log.info("output =>{}", tmp.getAbsolutePath());
            return Optional.of(tmp);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return Optional.empty();
    }

    public static Optional<File> base64ToFile(String base64Str, String outputName) {
        try {
            File tmp = new File(outputName);
            if (base64Str.startsWith(DATA_IMAGE_PNG_BASE_64)) {
                base64Str = base64Str.substring(DATA_IMAGE_PNG_BASE_64.length());
            }
            byte[] content = Base64Utils.decodeFromString(base64Str);
            FileUtils.writeByteArrayToFile(tmp, content);
            log.info("output =>{}", tmp.getAbsolutePath());
            return Optional.of(tmp);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return Optional.empty();
    }
}
