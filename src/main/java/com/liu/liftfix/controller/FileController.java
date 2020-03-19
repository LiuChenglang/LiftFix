package com.liu.liftfix.controller;

import com.liu.liftfix.dto.FileDTO;
import com.liu.liftfix.exeption.CustomizeErrorCode;
import com.liu.liftfix.exeption.CustomizeException;
import com.liu.liftfix.provider.AliyunProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;

@Controller
public class FileController {

    @Autowired
    private AliyunProvider aliyunProvider;

    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO upload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("editormd-image-file");
        File file = MultipartFilt2File(multipartFile);
        URL fileName = aliyunProvider.upload(file, file.getName());
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setUrl(fileName.toString());
        return fileDTO;
    }

    private File MultipartFilt2File(MultipartFile multipartFile) {
        File file = null;
        if (multipartFile.equals("") || multipartFile.getSize() <= 0) {
            multipartFile = null;
        } else {
            InputStream ins = null;
            try {
                ins = multipartFile.getInputStream();
            } catch (Exception e) {
                e.printStackTrace();
                throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
            }
            file = new File(multipartFile.getOriginalFilename());
            try {
                OutputStream os = new FileOutputStream(file);
                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                os.close();
                ins.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
            }
        }
        return file;
    }
}