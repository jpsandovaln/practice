/*
 *  Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 */

package com.jalasoft.practice.controller.request;

import com.jalasoft.practice.controller.exception.RequestParamInvalidException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

/**
 * @author HP
 * @version 1.1
 */
public class RequestExtractParameter extends RequestParameter {
    private String lang;
    private final static List<String> LANGUAGES = Arrays.asList("eng","spa");

    public RequestExtractParameter(String lang, String md5, MultipartFile file) {
        super(md5, file);
        this.lang = lang;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public void validate() throws RequestParamInvalidException {
        if (this.md5 == null || this.md5.trim().isEmpty()) {
            throw new RequestParamInvalidException("md5 is null or empty");
        }
        if (!this.md5.matches("[a-fA-F0-9]{32}")) {
            throw new RequestParamInvalidException("md5 invalid");
        }
        if (this.file == null || this.file.isEmpty()) {
            throw new RequestParamInvalidException("file is null or empty");
        }
        if (this.file.getContentType() == null || !this.file.getContentType().startsWith("image")) {
            throw new RequestParamInvalidException("invalid file format.");
        }
        if (this.file.getOriginalFilename().contains("..")) {
            throw new RequestParamInvalidException("invalid file name.");
        }
        if (this.lang == null || this.lang.trim().isEmpty()) {
            throw new RequestParamInvalidException("lang is null or empty");
        }
        if (!LANGUAGES.contains(this.lang)) {
            throw new RequestParamInvalidException("lang not allowed.");
        }

    }
}
