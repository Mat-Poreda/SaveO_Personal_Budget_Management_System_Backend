package Save.O.Save.O.User.Profile.service;

import Save.O.Save.O.Data.Storage.dao.Image;
import Save.O.Save.O.Data.Storage.dao.Transaction;
import Save.O.Save.O.Data.Storage.repository.ImageRepository;
import Save.O.Save.O.Data.Storage.repository.TransactionRepository;
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
    TransactionRepository transactionRepository;

    public Long storeImage(Long transactionId, MultipartFile multipartImage) throws IOException {
        Image dbImage = new Image();
        dbImage.setName(multipartImage.getName());
        dbImage.setContent(multipartImage.getBytes());

        Transaction transaction = transactionRepository.findById(transactionId).get();
        if (transaction.getImage() != null) {
            dbImage.setId(transaction.getImage().getId());
        }
        transaction.setImage(imageRepository.save(dbImage));
        return transactionRepository.save(transaction).getImage().getId();
    }

    public Resource getImage(Long imageId) {

        byte[] image = imageRepository.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getContent();
        return new ByteArrayResource(image);
    }


}
