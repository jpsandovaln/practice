/*
 *  Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 */

package com.jalasoft.practice.controller.request;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author HP
 * @version 1.1
 */
public class RequestExtractMetadataParameter {
    private String outputType;
    private String md5;
    private MultipartFile file;

    public RequestExtractMetadataParameter(String outputType, String md5, MultipartFile file) {
        this.outputType = outputType;
        this.md5 = md5;
        this.file = file;
    }

    public String getOutputType() {
        return outputType;
    }

    public void setOutputType(String outputType) {
        this.outputType = outputType;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
