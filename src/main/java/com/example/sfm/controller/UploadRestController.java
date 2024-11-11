package com.example.sfm.controller;

import com.example.sfm.exception.DangerException;
import com.example.sfm.util.ResultUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author lihai
 * Create Date: 2020-10-06
 */
@RestController
@RequestMapping("/api/v1/upload")
public class UploadRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadRestController.class);

    @PostMapping("/image")
    public ResultUtil upload(@RequestParam("file") MultipartFile multipartFile) {

        if (multipartFile != null && multipartFile.getOriginalFilename() != null) {
            File file = new File(multipartFile.getOriginalFilename());
            try {
                FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
                throw new DangerException("文件上传失败");
            }
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] buf = new byte[(int) file.length()];
                fis.read(buf);
                return ResultUtil.success("data:image/jpeg;base64," + new String(Base64.encodeBase64(buf), StandardCharsets.ISO_8859_1));
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
                throw new DangerException("文件上传失败");
            } finally {
                if (file.exists()) {
                    file.delete();
                }
            }
        }

        return ResultUtil.fail("文件上传失败");
    }

    public String imgToBase64(File imgFile) throws IOException {
        BufferedImage read = ImageIO.read(imgFile);
        return imgBufferedToBase64String(read);
    }

    public String imgBufferedToBase64String(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return Base64Utils.encodeToString(bytes);
    }

}
