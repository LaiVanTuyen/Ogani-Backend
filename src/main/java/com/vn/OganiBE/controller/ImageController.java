package com.vn.OganiBE.controller;

import com.vn.OganiBE.entity.Image;
import com.vn.OganiBE.exception.BadRequestException;
import com.vn.OganiBE.exception.InternalServerException;
import com.vn.OganiBE.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/image")
@CrossOrigin(origins = "*",maxAge = 3600)
public class ImageController {
    private static String UPLOAD_DIR  = System.getProperty("user.dir") + "/src/main/resources/static/photos/";

    @Autowired
    private ImageService imageService;


    @GetMapping("/")
    public ResponseEntity<?> getList(){
        List<Image> listImage = imageService.getListImage();

        return  ResponseEntity.ok(listImage);
    }

    @GetMapping("/user/{id}")
    @Operation(summary="Lấy ra danh sách hình ảnh của user bằng user_id")
    public ResponseEntity<?> getListByUser(@PathVariable long userId){
        List<Image> listImage = imageService.getListByUser(userId);

        return ResponseEntity.ok(listImage);
    }

    @PostMapping("/upload-file")
    @Operation(summary="Upload file lên database")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file){
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);;
        if (originalFilename != null && originalFilename.length() > 0) {
            if (!extension.equals("png") && !extension.equals("jpg") && !extension.equals("gif") && !extension.equals("svg") && !extension.equals("jpeg")) {
                throw new BadRequestException("Không hỗ trợ định dạng file này");
            }
            try {
                Image img = new Image();
                img.setName(file.getName());
                img.setSize(file.getSize());
                img.setType(extension);
                img.setData(file.getBytes());
                String uid = UUID.randomUUID().toString();
                String link = UPLOAD_DIR + uid + "." + extension;
                // Create file
                File serverFile = new File(link);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(file.getBytes());
                stream.close();

                imageService.save(img);
                return ResponseEntity.ok(img);
            } catch (Exception e) {
                throw new InternalServerException("Lỗi khi upload file");
            }
        }

        throw new BadRequestException("File không hợp lệ");

    }
}
