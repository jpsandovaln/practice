/*
 *  Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 */

package com.jalasoft.practice.model.extract.parameter;

import java.io.File;

/**
 * @author HP
 * @version 1.1
 */
public class ExtractMetadataParam extends Parameter {
    String type;
    String outDir;
    String exifToolBinaryDir;

    public ExtractMetadataParam(File inputFile, String type, String outDir, String exifToolBinaryDir) {
        super(inputFile);
        this.type = type;
        this.outDir = outDir;
        this.exifToolBinaryDir = exifToolBinaryDir;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOutDir() {
        return outDir;
    }

    public void setOutDir(String outDir) {
        this.outDir = outDir;
    }

    public String getExifToolBinaryDir() {
        return exifToolBinaryDir;
    }

    public void setExifToolBinaryDir(String exifToolBinaryDir) {
        this.exifToolBinaryDir = exifToolBinaryDir;
    }
}
