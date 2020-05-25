package com.jalasoft.practice.controller.endpoint;

import com.jalasoft.practice.controller.component.Properties;
import com.jalasoft.practice.controller.exception.FileException;
import com.jalasoft.practice.controller.request.RequestExtractMetadataParameter;
import com.jalasoft.practice.controller.request.RequestExtractParameter;
import com.jalasoft.practice.controller.response.ErrorResponse;
import com.jalasoft.practice.controller.response.OKResponse;
import com.jalasoft.practice.controller.service.FileService;
import com.jalasoft.practice.model.extract.ExtractMetadataFromFile;
import com.jalasoft.practice.model.extract.ExtractorTextFromImage;

import com.jalasoft.practice.model.extract.IExtractor;
import com.jalasoft.practice.model.extract.exception.ExtractException;
import com.jalasoft.practice.model.extract.exception.ParameterInvalidException;
import com.jalasoft.practice.model.extract.parameter.ExtractMetadataParam;
import com.jalasoft.practice.model.extract.parameter.ExtractTextParam;
import com.jalasoft.practice.model.extract.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity extract(RequestExtractParameter parameter) {
        try {
            File image = fileService.store(parameter.getFile(), parameter.getMd5());
            String tessData = properties.getTessdataFolder();

            IExtractor<ExtractTextParam> ext = new ExtractorTextFromImage();
            Result result = ext.extract(new ExtractTextParam(image, parameter.getLang(), tessData));

            return ResponseEntity.ok().body(
                    new OKResponse<Integer>(result.getText(), HttpServletResponse.SC_OK)
            );
        } catch (FileException ex) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<Integer>(ex.getMessage(), HttpServletResponse.SC_BAD_REQUEST)
            );
        } catch (ParameterInvalidException ex) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<Integer>(ex.getMessage(), HttpServletResponse.SC_BAD_REQUEST)
            );
        } catch (ExtractException ex) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<Integer>(ex.getMessage(), HttpServletResponse.SC_BAD_REQUEST)
            );
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<Integer>(ex.getMessage(),HttpServletResponse.SC_BAD_REQUEST)
            );
        }
    }

    @PostMapping("/extractor/metadata")
    public ResponseEntity extractMetadata(RequestExtractMetadataParameter parameter) {
        try {
            File image = fileService.store(parameter.getFile(), parameter.getMd5());
            IExtractor<ExtractMetadataParam> ext = new ExtractMetadataFromFile();
            Result result = ext.extract(
                    new ExtractMetadataParam(
                            image,
                            parameter.getOutputType(),
                            properties.getPublicFolder(),
                            properties.getExiftoolBinary()));

            String fileDownloadUri = fileService.getDownloadLink(new File(result.getText()));

            return ResponseEntity.ok().body(
                    new OKResponse<Integer>(fileDownloadUri, HttpServletResponse.SC_OK)
            );
        } catch (FileException ex) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<Integer>(ex.getMessage(), HttpServletResponse.SC_BAD_REQUEST)
            );
        } catch (ParameterInvalidException ex) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<Integer>(ex.getMessage(), HttpServletResponse.SC_BAD_REQUEST)
            );
        } catch (ExtractException ex) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<Integer>(ex.getMessage(), HttpServletResponse.SC_BAD_REQUEST)
            );
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<Integer>(ex.getMessage(),HttpServletResponse.SC_BAD_REQUEST)
            );
        }
    }
}
