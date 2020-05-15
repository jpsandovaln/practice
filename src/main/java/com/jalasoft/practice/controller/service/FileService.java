/*
 *  Copyright (c) 2020 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 */

package com.jalasoft.practice.controller.service;

import com.jalasoft.practice.controller.component.Properties;
import com.jalasoft.practice.controller.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author HP
 * @version 1.1
 */
@Service
public class FileService {

    @Autowired
    private Properties properties;

    public File store(MultipartFile file, String md5) throws Exception {
        if (md5.trim().isEmpty()) {
            throw new Exception("md5 error");
        }

        String fileInput;
        File image;
        String folder = properties.getInputFolder();
        Files.createDirectories(Paths.get(folder));
        fileInput = folder + file.getOriginalFilename();
        Path path = Paths.get(fileInput);
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        return new File(fileInput);
    }
}
