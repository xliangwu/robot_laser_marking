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
import org.springframework.http.*;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
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

    @GetMapping("/fonts")
    @ResponseBody
    public ApiResultModel<Object> fonts() {
        try {
            File fontFile = new File(appConfig.getFontRootDir() + File.separator + Constants.FONT_CONFIG_NAME);
            if (!fontFile.exists()) {
                log.error("font config[{}] should be existed", fontFile.getAbsolutePath());
                return ApiResultHelper.error(ApiStatusCode.CONFIG_ERROR);
            }
            List<String> allLines = FileUtils.readLines(fontFile, "utf8").stream().filter(StringUtils::isNotBlank).collect(Collectors.toList());

            AtomicInteger index = new AtomicInteger();
            Date now = new Date();
            List<FontElement> allFonts = new ArrayList<>();
            allLines.stream().skip(1).forEach(e -> {
                String[] fields = e.split(",");
                FontElement fontElement = new FontElement();
                fontElement.setId(index.incrementAndGet());
                fontElement.setAttributes(new FontAttribute());
                fontElement.getAttributes().setName(fields[0]);
                fontElement.getAttributes().setCreatedAt(now);
                fontElement.getAttributes().setPublishedAt(now);
                fontElement.getAttributes().setUpdatedAt(now);

                //img and file
                ElementEntity fileData = new ElementEntity();
                fileData.setId(fontElement.getId() + 1000);
                fileData.setAttributes(new ElementAttributes());
                fileData.getAttributes().setName(fields[0]);
                fileData.getAttributes().setCreatedAt(now);
                fileData.getAttributes().setPublishedAt(now);
                fileData.getAttributes().setUpdatedAt(now);
                fileData.getAttributes().setUrl("/api/v1/getFonts/%s".formatted(fields[1]));
                fileData.getAttributes().setExt(".ttf");
                fileData.getAttributes().setSize(100.28);
                Map<String, Object> fileItem = new HashMap<>();
                fileItem.put("data", fileData);
                fontElement.getAttributes().setFile(fileItem);


                //img and file
                ElementEntity imgData = new ElementEntity();
                imgData.setId(fontElement.getId() + 2000);
                imgData.setAttributes(new ElementAttributes());
                imgData.getAttributes().setName(fields[0]);
                imgData.getAttributes().setCreatedAt(now);
                imgData.getAttributes().setPublishedAt(now);
                imgData.getAttributes().setUpdatedAt(now);
                imgData.getAttributes().setUrl("/api/v1/getFontImg/%s".formatted(fields[2]));
                imgData.getAttributes().setExt(".svg");
                imgData.getAttributes().setSize(100.28);
                Map<String, Object> imgItem = new HashMap<>();
                imgItem.put("data", imgData);
                fontElement.getAttributes().setImg(imgItem);
                allFonts.add(fontElement);
            });
            ApiResultModel<Object> apiResultModel = ApiResultHelper.success(allFonts);
            Pagination pagination = new Pagination(Constants.DEFAULT_PAGE_SIZE, allFonts.size());
            Map<String, Object> meta = new HashMap<>();
            meta.put("pagination", pagination);
            apiResultModel.setMeta(meta);
            return apiResultModel;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return ApiResultHelper.error(ApiStatusCode.UNKNOWN_ERROR);
        }
    }

    @GetMapping(path = "/getFonts/{name}")
    public ResponseEntity<?> getFonts(@PathVariable("name") String name) {
        try {
            File fontImgFile = new File(appConfig.getFontRootDir() + File.separator + "data" + File.separator + name);
            if (!fontImgFile.exists()) {
                throw new UnsupportedOperationException("font image missed" + name);
            }
            byte[] imageBytes = FileUtils.readFileToByteArray(fontImgFile);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("font", "ttf"));
            headers.setCacheControl(CacheControl.maxAge(1, TimeUnit.HOURS));
            headers.setContentDispositionFormData("attachment", URLEncoder.encode(name, StandardCharsets.UTF_8));
            ByteArrayResource content = new ByteArrayResource(imageBytes);
            return ResponseEntity.ok().headers(headers).body(content);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping(path = "/getFontImg/{name}")
    public ResponseEntity<?> getFontsImg(@PathVariable("name") String name) {
        try {
            File fontImgFile = new File(appConfig.getFontRootDir() + File.separator + "data" + File.separator + name);
            if (!fontImgFile.exists()) {
                throw new UnsupportedOperationException("font image missed");
            }
            byte[] imageBytes = FileUtils.readFileToByteArray(fontImgFile);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("image", "svg+xml"));
            headers.setCacheControl(CacheControl.maxAge(3600, TimeUnit.SECONDS));
            headers.setContentDispositionFormData("attachment", name);
            ByteArrayResource content = new ByteArrayResource(imageBytes);
            return ResponseEntity.ok().headers(headers).body(content);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


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
                ElementEntity elementEntity = new ElementEntity();
                ElementAttributes elementAttributes = new ElementAttributes();
                elementAttributes.setUrl("/api/v1/uploads?name=" + file.getName());
                elementAttributes.setName(file.getName());
                elementAttributes.setHeight(500);
                elementAttributes.setWidth(374);
                elementAttributes.setUpdatedAt(new Date());
                elementAttributes.setUpdatedAt(new Date());
                elementAttributes.setPublishedAt(new Date());
                elementEntity.setAttributes(elementAttributes);
                elementEntity.setId(material.getId() * 100);

                Map<String, Object> img = new HashMap<>();
                img.put("data", elementEntity);

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
