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
import com.jalasoft.practice.controller.constant.ErrorConstant;
import com.jalasoft.practice.controller.exception.FileException;
import com.jalasoft.practice.controller.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author HP
 * @version 1.1
 */
@Service
public class FileService {

    @Autowired
    private Properties properties;

    public File store(MultipartFile file, String md5) throws FileException {
        try {
            Path path = this.getFilePath(file.getOriginalFilename());
            String currentMd5 = this.getMd5(path);
            this.validateMd5(currentMd5, md5);

            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return new File(path.toString());
        } catch (IOException ex) {
            throw new FileException(ErrorConstant.FILE_ERROR, ex);
        }
    }

    private void validateMd5(String currentMd5, String md5) throws FileException {
        if(!currentMd5.equals(md5)) {
            throw  new FileException("Invalid file received");
        }
    }

    private String getMd5(Path path) throws FileException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(Files.readAllBytes(path));
            byte[] hash = messageDigest.digest();
            return DatatypeConverter.printHexBinary(hash).toUpperCase();
        } catch (NoSuchAlgorithmException | IOException ex) {
            throw new FileException(ErrorConstant.FILE_ERROR, ex);
        }
    }

    private Path getFilePath(String fileName) throws FileException {
        try {
            String folder = properties.getInputFolder();
            Files.createDirectories(Paths.get(folder));
            return Paths.get(folder + fileName);
        } catch (IOException ex) {
            throw new FileException(ErrorConstant.FILE_ERROR, ex);
        }
    }

    public String getDownloadLink(File file) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/download/")
                .path(file.getName())
                .toUriString();
    }
}
