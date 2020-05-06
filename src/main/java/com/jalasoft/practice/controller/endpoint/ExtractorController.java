package com.jalasoft.practice.controller.endpoint;

import com.jalasoft.practice.model.Extractor;

import com.jalasoft.practice.model.parameter.ExtractTextParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/api/v1")
public class ExtractorController {

    @PostMapping("/extractor")
    public String extract(@RequestParam(value="lang") String lang,
                          @RequestParam(value="file") MultipartFile file) {
        if (lang.isEmpty()) {
            return "error lang1";
        }
        if(!"eng".equals(lang)) {
            return "error lang2";
        }
        if (file.isEmpty()) {
            return "error file";
        }
        String fileInput;
        try {
            String folder = "imageFolder/";
            Files.createDirectories(Paths.get(folder));
            fileInput = folder + file.getOriginalFilename();
            Path path = Paths.get(fileInput);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            return ex.getMessage();
        }

        String tessData = "thirdParty/Tess4J/tessdata";
        Extractor ext = new Extractor();
        ExtractTextParam param = new ExtractTextParam();
        param.setImageFile(fileInput);
        param.setLang(lang);
        param.setTessData(tessData);
        String result = ext.extract(param);
        return result;
    }
}
