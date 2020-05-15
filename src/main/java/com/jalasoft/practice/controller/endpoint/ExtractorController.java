package com.jalasoft.practice.controller.endpoint;

import com.jalasoft.practice.controller.component.Properties;
import com.jalasoft.practice.controller.exception.FileException;
import com.jalasoft.practice.controller.response.ErrorResponse;
import com.jalasoft.practice.controller.response.OKResponse;
import com.jalasoft.practice.controller.response.Response;
import com.jalasoft.practice.controller.service.FileService;
import com.jalasoft.practice.model.extract.ExtractorTextFromImage;

import com.jalasoft.practice.model.extract.exception.ExtractException;
import com.jalasoft.practice.model.extract.exception.ParameterInvalidException;
import com.jalasoft.practice.model.extract.parameter.ExtractTextParam;
import com.jalasoft.practice.model.extract.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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
            ExtractorTextFromImage ext = new ExtractorTextFromImage();
            ExtractTextParam param = new ExtractTextParam(image, lang, tessData);
            param.validate();
            Result result = ext.extract(param);
            return ResponseEntity.ok().body(
                    new OKResponse(result.getText(), Integer.toString(HttpServletResponse.SC_OK))
            );
        } catch (FileException ex) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse(ex.getMessage(), Integer.toString(HttpServletResponse.SC_BAD_REQUEST))
            );
        } catch (ParameterInvalidException ex) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse(ex.getMessage(), Integer.toString(HttpServletResponse.SC_BAD_REQUEST))
            );
        } catch (ExtractException ex) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse(ex.getMessage(), Integer.toString(HttpServletResponse.SC_BAD_REQUEST))
            );
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse(ex.getMessage(), Integer.toString(HttpServletResponse.SC_BAD_REQUEST))
            );
        }
    }
}
