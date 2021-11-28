package Save.O.Save.O.User.Profile.controller;

import Save.O.Save.O.User.Profile.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/user_details")
public class ImageController {
    @Autowired
    ImageService imageService;

    @GetMapping(value = "/image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    Resource downloadImage(@PathVariable(name="imageId") Long imageId) {
        return imageService.getImage(imageId);
    }

    @PostMapping("/{user_id}/image")
    public Long uploadImage(@PathVariable(name="user_id") Long user_id, @RequestParam MultipartFile multipartImage) throws Exception {
        return imageService.storeImage(user_id, multipartImage);
    }
}
