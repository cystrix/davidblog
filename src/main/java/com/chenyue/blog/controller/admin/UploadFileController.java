package com.chenyue.blog.controller.admin;


import com.chenyue.blog.vo.Response;
import com.chenyue.blog.vo.UploadFileVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Calendar;

/**
 * @author chenyue7@foxmail.com
 */
@Controller
@RequestMapping("/admin/upload")
@Slf4j
public class UploadFileController {

    public final String rootPath = "C:\\Users\\chenyue\\OneDrive\\桌面\\DavidBlog\\uploads";

    public final String allowSuffix=  ".bmp.jpg.jpeg.png.pdf.doc.zip.rar.gz";

    @RequestMapping(value = "/img", method = RequestMethod.POST)
    public Response uploadFile(@RequestParam("file") MultipartFile file){
        String filename = file.getOriginalFilename();

        String name = filename.substring(0, filename.indexOf("."));
        String suffix = filename.substring(filename.lastIndexOf("."));

        if(!allowSuffix.contains(suffix)) {
            return Response.failed("不允许上传该后缀文件");
        }


        Calendar date = Calendar.getInstance();
        File dateDirs = new File(date.get(Calendar.YEAR) + File.separator + (date.get(Calendar.MONTH) + 1));

        File descFile = new File(rootPath + File.separator + dateDirs + File.separator + filename);
        int i = 1;
        String newFilename = filename;
        while(descFile.exists()) {
            newFilename = name + "(" + i + ")" + suffix;
            String parentPath = descFile.getParent();
            descFile = new File(parentPath + File.separator + newFilename);
            i++;
        }

        if (!descFile.getParentFile().exists()){
            descFile.getParentFile().mkdirs();
        }

        try {
            file.transferTo(descFile);
        } catch (Exception e){
            e.printStackTrace();
            log.error("上传失败, cause:{}", e);
        }

        String fileUrl = "/uploads" + dateDirs + "/" + newFilename;

        UploadFileVo uploadFileVo = new UploadFileVo();
        uploadFileVo.setTitle(filename);
        uploadFileVo.setSrc(fileUrl);
        return Response.success(uploadFileVo);
    }

}
