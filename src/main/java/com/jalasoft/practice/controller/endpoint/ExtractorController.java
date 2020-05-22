package com.jalasoft.practice.controller.endpoint;

import com.jalasoft.practice.controller.component.Properties;
import com.jalasoft.practice.controller.exception.FileException;
import com.jalasoft.practice.controller.request.RequestExtractParameter;
import com.jalasoft.practice.controller.response.ErrorResponse;
import com.jalasoft.practice.controller.response.OKResponse;
import com.jalasoft.practice.controller.service.FileService;
import com.jalasoft.practice.model.extract.ExtractorTextFromImage;

import com.jalasoft.practice.model.extract.IExtractor;
import com.jalasoft.practice.model.extract.exception.ExtractException;
import com.jalasoft.practice.model.extract.exception.ParameterInvalidException;
import com.jalasoft.practice.model.extract.parameter.ExtractTextParam;
import com.jalasoft.practice.model.extract.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ExtractorController {

    @Autowired
    private Properties properties;

    @Autowired
    private FileService fileService;

    @Autowired
    private FileService fileService2;

    @Autowired
    private FileService fileService3;

    @PostMapping("/extractor")
    public ResponseEntity extract(RequestExtractParameter parameter) {
        try {
            List<Thread> threadList = new ArrayList<>();

            /* for (int idx = 0; idx < parameter.getFile().length; idx ++) {
                FileService fileServicex = new FileService();
                fileServicex.setFields(parameter.getFile()[0], parameter.getMd5());
                threadList.add(new Thread(fileServicex));
            } */


            fileService.setFields(parameter.getFile()[0], parameter.getMd5());
            fileService2.setFields(parameter.getFile()[1], parameter.getMd5());
            fileService3.setFields(parameter.getFile()[2], parameter.getMd5());

            Thread t1 = new Thread(fileService);
            Thread t2 = new Thread(fileService2);
            Thread t3 = new Thread(fileService2);

            t1.start();
            t2.start();
            t3.start();

            t1.join();
            t2.join();
            t3.join();

            File image = fileService.getSavedFile();
            File image2 = fileService2.getSavedFile();
            File image3 = fileService3.getSavedFile();

            System.out.println(image.getName());
            System.out.println(image2.getName());
            System.out.println(image3.getName());

            // File image = fileService.store(parameter.getFile()[0], parameter.getMd5());
            String tessData = properties.getTessdataFolder();

            IExtractor<ExtractTextParam> ext = new ExtractorTextFromImage();
            Result result = ext.extract(new ExtractTextParam(image, parameter.getLang(), tessData));

            return ResponseEntity.ok().body(
                    new OKResponse<Integer>(result.getText(), HttpServletResponse.SC_OK)
            );
        //} catch (FileException ex) {
        //    return ResponseEntity.badRequest().body(
        //            new ErrorResponse<Integer>(ex.getMessage(), HttpServletResponse.SC_BAD_REQUEST)
        //    );
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
