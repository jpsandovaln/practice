package com.jalasoft.practice.controller.endpoint;

import com.jalasoft.practice.controller.response.Response;
import com.jalasoft.practice.model.Extractor;

import com.jalasoft.practice.model.parameter.ExtractTextParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/api/v1")
public class ExtractorController {

    @PostMapping("/extractor")
    public ResponseEntity extract(@RequestParam(value="lang") String lang,
                                  @RequestParam(value="md5") String md5,
                                  @RequestParam(value="file") MultipartFile file) {
        if (md5.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(
                new Response("", "error md5", "400")
            );
        }
        /* if(md5.length() != 32) {
            return "error md5";
        } */
        String fileInput;
        File image;
        try {
            String folder = "imageFolder/";
            Files.createDirectories(Paths.get(folder));
            fileInput = folder + file.getOriginalFilename();
            image = new File(fileInput);
            Path path = Paths.get(fileInput);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            return ResponseEntity.badRequest().body(
                new Response("", ex.getMessage(), "400")
            );
        }

        String tessData = "thirdParty/Tess4J/tessdata";
        Extractor ext = new Extractor();
        try {
            ExtractTextParam param = new ExtractTextParam(image, lang, tessData);
            param.validate();
            String result = ext.extract(param);
            return ResponseEntity.ok().body(
                new Response(result, "", "200")
            );
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(
                new Response("", ex.getMessage(), "400")
            );
        }
    }
}
