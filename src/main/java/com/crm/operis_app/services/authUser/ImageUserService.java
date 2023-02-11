package com.crm.operis_app.services.authUser;

import com.crm.operis_app.model.authUser.ImageUser;
import com.crm.operis_app.model.authUser.User;
import com.crm.operis_app.repository.authUser.ImageUserRepository;
import com.crm.operis_app.repository.authUser.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
@Service
@Transactional

public class ImageUserService {

    @Autowired
    private ImageUserRepository imageUserRepository;

    @Autowired
    private UserRepository userRepository;

    public ImageUser store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        ImageUser ImageActionCorrective = new ImageUser(fileName, file.getContentType(), file.getBytes());
        return imageUserRepository.save(ImageActionCorrective);
    }


    public  void addImageToUser(Long UserId, Long imageId) {

        Optional<ImageUser> imageById = imageUserRepository.findById(imageId);
        if (!imageById.isPresent()) {
            throw new ResourceNotFoundException("image with id " + imageId + " does not exist");
        }
        ImageUser image = imageById.get();

        Optional<User> userById = userRepository.findById(UserId);
        if (!userById.isPresent()) {
            throw new ResourceNotFoundException("action with id " + UserId + " does not exist");
        }

        if(imageById.isPresent()){

            User user = userById.get();
            user.addImageUser(image);
            userRepository.save(user);}
    }

    public  ImageUser getImageUserById(Long imageId) {
        if (!imageUserRepository.existsById(imageId)) {
            throw new ResourceNotFoundException("image with id " + imageId + " not found");
        }
        return imageUserRepository.findById(imageId).get();
    }


    public ResponseEntity<Object> deleteImageById(Long imageId) {
        if (!imageUserRepository.existsById(imageId)) {
            throw new ResourceNotFoundException("Image with id " + imageId + " not found");
        }
        imageUserRepository.deleteById(imageId);
        return ResponseEntity.ok().build();
    }
}
