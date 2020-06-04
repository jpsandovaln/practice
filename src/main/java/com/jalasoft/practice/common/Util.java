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
}
