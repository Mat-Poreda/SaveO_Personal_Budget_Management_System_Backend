package Save.O.Save.O.User.Profile.service;

import Save.O.Save.O.User.Profile.dao.Image;
import Save.O.Save.O.User.Profile.dao.User;
import Save.O.Save.O.User.Profile.repository.ImageRepository;
import Save.O.Save.O.User.Profile.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    UserRepository userRepository;


    public Long storeImage(Long userId, MultipartFile multipartImage) throws IOException {
        Image dbImage = new Image();
        dbImage.setName(multipartImage.getName());
        dbImage.setContent(multipartImage.getBytes());

        User user = userRepository.findById(userId).get();
        if (user.getImage() != null) {
            dbImage.setId(user.getImage().getId());
        }
        user.setImage(imageRepository.save(dbImage));
        return userRepository.save(user).getImage().getId();
    }

    public Resource getImage(Long imageId) {

        byte[] image = imageRepository.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getContent();
        return new ByteArrayResource(image);
    }


}
