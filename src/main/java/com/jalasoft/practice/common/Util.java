/*
 *  Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 */

package com.jalasoft.practice.common;

import com.jalasoft.practice.common.exception.InvalidDataException;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author HP
 * @version 1.1
 */
public class Util {
    public static String getMimeType(File file) {
        try {
            return Files.probeContentType(file.toPath());
        } catch (IOException ex) {
            return "";
        }
    }

    public static String zipFile(String file) {
        try {
            String zipPath = file + ".zip";
            ZipFile zipFile = new ZipFile(new File(zipPath));
            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
            zipFile.addFile(new File(file), parameters);
            return zipPath;
        } catch (ZipException ex) {
            return null;
        }
    }
}
