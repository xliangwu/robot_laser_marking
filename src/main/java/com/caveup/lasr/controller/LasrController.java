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
        log.info("request:{},requestId:{},params:{}", request.getRequestURI(), request, markingParams);
        try {
            String imageData = markingParams.getImageData();
            SimpleDateFormat dfm = new SimpleDateFormat("yyyyMMddHHmmss");
            String label = dfm.format(new Date());
            String fileName = label + ".png";
            String outputName = appConfig.getMarkingRootDir() + File.separator + fileName;
            Base64Helper.base64ToFile(imageData, outputName);
            log.info("output name:{}", outputName);
            boolean ret = tcpSocketTemplate.updateMarkingContent(fileName);
            log.info("updateMarkingContent,output:{},status:{}", outputName, ret);
            return ApiResultHelper.error(ret ? ApiStatusCode.SUCCESS : ApiStatusCode.MARKING_ERROR);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return ApiResultHelper.error(ApiStatusCode.UNKNOWN_ERROR);
        }
    }
}
