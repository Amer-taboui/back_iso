package com.crm.operis_app.controller.authUser;

import com.crm.operis_app.model.authUser.ImageUser;
import com.crm.operis_app.repository.authUser.ImageUserRepository;
import com.crm.operis_app.services.authUser.ImageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/api")
public class ImageUserController {
    @Autowired
    ImageUserService imageUserService ;

    @Autowired
    ImageUserRepository imageUserRepository;


    @PostMapping("/imageUser/upload")
    public long uplaodImage(@RequestParam("myFile") MultipartFile file) throws IOException {
        ImageUser img = new ImageUser( file.getOriginalFilename(),file.getContentType(),file.getBytes());
        final ImageUser savedImage = imageUserRepository.save(img);
        return savedImage.getId();
    }

    @PutMapping("/imageUser/{id}/update")
    public ImageUser updateImage(@PathVariable("id") Long id,@RequestParam("myFile") MultipartFile file) throws IOException {
        Optional<ImageUser> imageUser = imageUserRepository.findById(id);

        ImageUser imageU = imageUser.get();
        imageU.setName(file.getOriginalFilename());
        imageU.setType(file.getContentType());
        imageU.setPic(file.getBytes());
        final ImageUser savedImage = imageUserRepository.save(imageU);

        return savedImage;
    }


    /** ADD /REMOVE image to User */

    @PostMapping(value = "/User/{userId}/addImageUser/{imageId}")
    public void addImageToUser(@PathVariable(value = "userId") Long userId, @PathVariable(value = "imageId") Long imageId  ) {
        imageUserService.addImageToUser(userId,imageId);
    }


    @GetMapping("/imageUser/{id}")
    public ResponseEntity<byte[]> getImageUserById(@PathVariable Long id) {
        ImageUser fileDB = imageUserService.getImageUserById(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getPic());
    }
}
