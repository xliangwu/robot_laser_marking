package com.caveup.lasr.controller;

import com.caveup.lasr.config.AppConfig;
import com.caveup.lasr.connection.PlcTemplate;
import com.caveup.lasr.connection.TcpSocketTemplate;
import com.caveup.lasr.entity.MarkingRequest;
import com.caveup.lasr.result.ApiStatusCode;
import com.caveup.lasr.result.helper.ApiResultHelper;
import com.caveup.lasr.result.model.ApiResultModel;
import com.caveup.lasr.util.Base64Helper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xw80329
 */
@RestController
@Slf4j
@RequestMapping("/laser/v1")
@Validated
@CrossOrigin
public class LasrController {

    @Autowired
    private PlcTemplate plcTemplate;

    @Autowired
    private TcpSocketTemplate tcpSocketTemplate;

    @Autowired
    private AppConfig appConfig;

    @PostMapping("/marking")
    public ApiResultModel<Object> marking(@RequestParam(value = "requestId", required = false) String requestId,
                                          @RequestBody @Validated MarkingRequest markingParams,
                                          HttpServletRequest request) {
        log.info("request:{},requestId:{}", request.getRequestURI(), requestId);
        try {
            String imageData = markingParams.getImageData();
            SimpleDateFormat dfm = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat pidFmt = new SimpleDateFormat("yyyyMMddHHmmss");

            Date now = new Date();
            String label = dfm.format(now);
            String fileName = pidFmt.format(now) + ".png";
            String parentDir = appConfig.getMarkingRootDir() + File.separator + label;
            FileUtils.createParentDirectories(new File(parentDir));
            log.info("create parent folder:{}", parentDir);
            String outputName = parentDir + File.separator + fileName;
            Base64Helper.base64ToFile(imageData, outputName);
            log.info("output name:{}", outputName);

            plcTemplate.writeInt(appConfig.getDbNum(), appConfig.getFloorOffset(), 1);
            return ApiResultHelper.error(ApiStatusCode.SUCCESS);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return ApiResultHelper.error(ApiStatusCode.UNKNOWN_ERROR);
        }
    }
}
