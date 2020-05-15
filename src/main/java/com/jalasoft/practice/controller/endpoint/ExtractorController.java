package com.jalasoft.practice.controller.endpoint;

import com.jalasoft.practice.controller.component.Properties;
import com.jalasoft.practice.controller.response.Response;
import com.jalasoft.practice.controller.service.FileService;
import com.jalasoft.practice.model.Extractor;

import com.jalasoft.practice.model.parameter.ExtractTextParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/api/v1")
public class ExtractorController {

    @Autowired
    private Properties properties;

    @Autowired
    private FileService fileService;

    @PostMapping("/extractor")
    public ResponseEntity extract(@RequestParam(value="lang") String lang,
                                  @RequestParam(value="md5") String md5,
                                  @RequestParam(value="file") MultipartFile file) {
        try {
            File image = fileService.store(file, md5);
            String tessData = properties.getTessdataFolder();
            Extractor ext = new Extractor();
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
