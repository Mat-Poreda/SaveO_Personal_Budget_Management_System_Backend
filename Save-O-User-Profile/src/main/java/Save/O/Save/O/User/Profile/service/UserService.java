package Save.O.Save.O.User.Profile.service;


import Save.O.Save.O.User.Profile.dao.Image;
import Save.O.Save.O.User.Profile.dao.UserDetails;
import Save.O.Save.O.User.Profile.dto.UserDTO;
import Save.O.Save.O.User.Profile.dto.UserStatsDTO;
import Save.O.Save.O.User.Profile.repository.ImageRepository;
import Save.O.Save.O.User.Profile.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    private ModelMapper modelMapper;
//    @Autowired
//    private ImageService imageService;
//
//
//    public UserDTO convertUserToDto(User user) {
//        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
//        return userDTO;
//    }
//
//    public User convertUserToEntity(UserDTO userDTO) throws ParseException {
//        User user = modelMapper.map(userDTO, User.class);
//        return user;
//    }
//
//    public UserDTO createNewUser(Optional<Long> id){
//        if(id.isPresent() && userRepository.findById(id.get()).isPresent()){
//            return convertUserToDto(userRepository.findById(id.get()).get());
//        }else{
//            User user=new User();
//            user=userRepository.save(user);
//            return convertUserToDto(user);
//        }
//    }
//
//
//
//    public List<UserDTO> getAllUsers() {
//        List<User> users = userRepository.findAll();
//        return users.stream()
//                .map(this::convertUserToDto)
//                .collect(Collectors.toList());
//    }
//
//
//    public UserDTO getUserById(Long id) {
//        if(userRepository.findById(id).isEmpty()){
//            return null;
//        }else{
//            return convertUserToDto(userRepository.findById(id).get());
//        }
//    }
//
//
//    public void deleteUserById(Long id) {
//        if(userRepository.findById(id).isPresent()){
//            userRepository.delete(userRepository.findById(id).get());
//        }
//    }
//
//    public UserDTO updateUser(Long id, UserDTO userDTO)throws Exception{
//
//        if(userRepository.findById(id).isEmpty()){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with given Id does not exist");
//        }else{
//            User user=userRepository.findById(id).get();
//            if(!userDTO.getBio().isEmpty()){
//                user.setBio(userDTO.getBio());
//            }
//            if(!userDTO.getUsername().isEmpty()){
//                user.setUsername(userDTO.getUsername());
//            }
//
//         return convertUserToDto(userRepository.save(user));
//        }
//    }

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ModelMapper modelMapper;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        List<UserDetails> userDetails = userRepository.findAll();
        return userDetails.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public UserDTO getById(Long id) {
        Optional<UserDetails> user = userRepository.findById(id);
        if (user.isPresent()) {
            return convertToDto(user.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no User with id " + id);
        }
    }

    public UserDTO getByEmail(String email) throws ParseException {
        Optional<UserDetails> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return convertToDto(userRepository.findByEmail(email).get());
        } else {
//            return createUser(new UserDTO());
            return null;
        }
    }


    public void deleteUser(String email) {
        userRepository.deleteByEmail(email);
    }

    public UserDTO createUser(UserDTO userDTO) throws ParseException {

        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            return convertToDto(userRepository.findByEmail(userDTO.getEmail()).get());
        } else {
            UserDetails createdUserDetails = convertToEntity(userDTO);
            createdUserDetails = userRepository.save(createdUserDetails);

            return convertToDto(userRepository.findByEmail(createdUserDetails.getEmail()).get());
        }
    }
    public void updateUser(String email, UserDTO updateRequest) {
        var user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            if (updateRequest.getBio() != null) {
                user.get().setBio(updateRequest.getBio());
            }
            if (updateRequest.getNickname() != null) {
                user.get().setUsername(updateRequest.getNickname());
            }

            userRepository.save(user.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no User with email " + email);
        }
    }

    public UserDetails dtoToDao(UserDTO userDTO) throws ParseException {
        return convertToEntity(userDTO);
    }

    public UserDTO convertToDto(UserDetails userDetails) {
        UserDTO userDTO = modelMapper.map(userDetails, UserDTO.class);
        return userDTO;
    }

    public UserDetails convertToEntity(UserDTO userDTO) throws ParseException {
        UserDetails userDetails = modelMapper.map(userDTO, UserDetails.class);
        return userDetails;
    }


    public Long storeImage(String email, MultipartFile multipartImage) throws IOException {
        Image dbImage = new Image();
        dbImage.setName(multipartImage.getName());
        dbImage.setContent(multipartImage.getBytes());

        UserDetails userDetails = userRepository.findByEmail(email).get();
        if (userDetails.getImage() != null) {
            dbImage.setId(userDetails.getImage().getId());
        }
        userDetails.setImage(imageRepository.save(dbImage));
        return userRepository.save(userDetails).getImage().getId();
    }

    public Resource getImage(Long imageId) {

        byte[] image = imageRepository.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getContent();
        return new ByteArrayResource(image);
    }

    public UserStatsDTO getUserStats(String email) {
        if (userRepository.findByEmail(email).isEmpty()) {
            return new UserStatsDTO();
        } else {

            return null;
        }
    }

}








