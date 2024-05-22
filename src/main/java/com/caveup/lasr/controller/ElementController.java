package com.caveup.lasr.controller;

import com.alibaba.fastjson.JSONArray;
import com.caveup.lasr.config.AppConfig;
import com.caveup.lasr.constatns.Constants;
import com.caveup.lasr.entity.*;
import com.caveup.lasr.result.ApiStatusCode;
import com.caveup.lasr.result.helper.ApiResultHelper;
import com.caveup.lasr.result.model.ApiResultModel;
import com.caveup.lasr.util.QueryUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author xw80329
 */
@RestController
@Slf4j
@RequestMapping("/api/v1")
@Validated
@CrossOrigin
public class ElementController {

    private static final Map<String, String> MATERIAL_MAP = new ConcurrentHashMap<>();

    @Autowired
    private AppConfig appConfig;

    @GetMapping("/sizes")
    @ResponseBody
    public ApiResultModel<Object> sizes() {
        try {
            File designerFile = new File(appConfig.getDesignerConfig());
            String designerConfigContent = FileUtils.readFileToString(designerFile, "utf8");
            JSONArray data = JSONArray.parseArray(designerConfigContent);
            ApiResultModel<Object> apiResultModel = ApiResultHelper.success(data);
            Pagination pagination = new Pagination(Constants.DEFAULT_PAGE_SIZE, data.size());
            Map<String, Object> meta = new HashMap<>();
            meta.put("pagination", pagination);
            apiResultModel.setMeta(meta);
            return apiResultModel;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return ApiResultHelper.error(ApiStatusCode.UNKNOWN_ERROR);
        }
    }

    @GetMapping("/material-types")
    public ApiResultModel<Object> materialTypes() {
        try {
            File materialFile = new File(appConfig.getMaterialRootDir());
            File[] subTypes = materialFile.listFiles();
            Assert.notNull(subTypes, String.format("%s should be non-empty", appConfig.getMaterialRootDir()));
            List<MaterialType> materialTypeList = new ArrayList<>();
            int i = 1;
            for (File file : subTypes) {
                if (file.isFile()) {
                    continue;
                }

                MaterialType materialType = new MaterialType();
                materialType.setId(i + 1);
                Attributes attributes = new Attributes();
                attributes.setName(file.getName());
                attributes.setCreatedAt(new Date());
                attributes.setPublishedAt(new Date());
                attributes.setUpdatedAt(new Date());
                materialType.setAttributes(attributes);
                materialTypeList.add(materialType);
                MATERIAL_MAP.put(String.valueOf(materialType.getId()), attributes.getName());
                i++;
            }
            ApiResultModel<Object> apiResultModel = ApiResultHelper.success(materialTypeList);
            Pagination pagination = new Pagination(Constants.DEFAULT_PAGE_SIZE, materialTypeList.size());
            Map<String, Object> meta = new HashMap<>();
            meta.put("pagination", pagination);
            apiResultModel.setMeta(meta);
            return apiResultModel;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return ApiResultHelper.error(ApiStatusCode.UNKNOWN_ERROR);
        }
    }

    @GetMapping(path = "/uploads", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<?> download(@RequestParam("name") String name) {
        File materialFile = new File(appConfig.getMaterialRootDir());
        Collection<File> allImages = FileUtils.listFiles(materialFile, new String[]{"jpg", "png"}, true);

        try {
            Optional<File> matchedFile = allImages.stream().filter(e -> e.getName().equals(name)).findFirst();
            if (matchedFile.isPresent()) {
                byte[] imageBytes = FileUtils.readFileToByteArray(matchedFile.get());
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_PNG);
                headers.setContentDispositionFormData("attachment", name);
                ByteArrayResource content = new ByteArrayResource(imageBytes);
                return ResponseEntity.ok().headers(headers).body(content);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/materials")
    public ApiResultModel<Object> materials(HttpServletRequest request) {
        log.info("request:{},query:{}", request.getRequestURI(), request.getQueryString());
        try {
            Map<String, String> params = QueryUtil.parse(request.getQueryString());

            AtomicReference<String> selectedType = new AtomicReference<>();
            AtomicReference<String> keyword = new AtomicReference<>();

            selectedType.set("all");
            params.forEach((k, v) -> {
                if (k.contains("material_type")) {
                    selectedType.set(v);
                }

                if (k.contains("name")) {
                    keyword.set(v);
                }
            });

            log.info("material_type:{}", selectedType.get());
            String categoryName = MATERIAL_MAP.getOrDefault(selectedType.get(), null);
            String keywordStr = keyword.get();
            File materialFile = new File(appConfig.getMaterialRootDir());
            Collection<File> allImages = FileUtils.listFiles(materialFile, new String[]{"jpg", "png"}, true)
                    .stream()
                    .sorted((a, b) -> String.CASE_INSENSITIVE_ORDER.compare(a.getName(), b.getName())).collect(Collectors.toList());

            Assert.notNull(allImages, String.format("%s should be non-empty", appConfig.getMaterialRootDir()));
            List<Material> materials = new ArrayList<>();
            int i = 1;
            for (File file : allImages) {
                if (file.isDirectory()) {
                    continue;
                }

                if (null != categoryName && !file.getParent().endsWith(categoryName)) {
                    continue;
                }

                if (StringUtils.isNotBlank(keywordStr) && !file.getName().contains(keywordStr)) {
                    continue;
                }

                Material material = new Material();
                material.setId(i + 1);

                //detail
                MaterialDetail materialDetail = new MaterialDetail();
                ImgAttributes imgAttributes = new ImgAttributes();
                imgAttributes.setUrl("/api/v1/uploads?name=" + file.getName());
                imgAttributes.setName(file.getName());
                imgAttributes.setHeight(500);
                imgAttributes.setWidth(374);
                imgAttributes.setUpdatedAt(new Date());
                imgAttributes.setUpdatedAt(new Date());
                imgAttributes.setPublishedAt(new Date());
                materialDetail.setAttributes(imgAttributes);
                materialDetail.setId(material.getId() * 100);

                Map<String, Object> img = new HashMap<>();
                img.put("data", materialDetail);

                ImgMaterials attributes = new ImgMaterials();
                attributes.setName(file.getName());
                attributes.setCreatedAt(new Date());
                attributes.setPublishedAt(new Date());
                attributes.setUpdatedAt(new Date());
                attributes.setImg(img);

                material.setAttributes(attributes);
                materials.add(material);
                i++;
            }
            ApiResultModel<Object> apiResultModel = ApiResultHelper.success(materials);
            Pagination pagination = new Pagination(Constants.DEFAULT_PAGE_SIZE, materials.size());
            Map<String, Object> meta = new HashMap<>();
            meta.put("pagination", pagination);
            apiResultModel.setMeta(meta);
            return apiResultModel;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return ApiResultHelper.error(ApiStatusCode.UNKNOWN_ERROR);
        }
    }
}
