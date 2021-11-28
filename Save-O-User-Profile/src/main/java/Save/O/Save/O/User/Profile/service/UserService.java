package Save.O.Save.O.User.Profile.service;


import Save.O.Save.O.User.Profile.dao.User;
import Save.O.Save.O.User.Profile.dto.UserDTO;
import Save.O.Save.O.User.Profile.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Arrays;
import java.util.HashSet;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    public UserDTO convertUserToDto(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

    public User convertUserToEntity(UserDTO userDTO) throws ParseException {
        User user = modelMapper.map(userDTO, User.class);
        return user;
    }

    public UserDTO createNewUser(Optional<Long> id){
        if(id.isPresent() && userRepository.findById(id.get()).isPresent()){
            return convertUserToDto(userRepository.findById(id.get()).get());
        }else{
            User user=new User();
            user=userRepository.save(user);
            return convertUserToDto(user);
        }
    }

    private UserDTO convertUserToDto(User user) {
        
    }
    

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertUserToDto)
                .collect(Collectors.toList());
    }


    public UserDTO getUserById(Long id) {
        if(userRepository.findById(id).isEmpty()){
            return null;
        }else{
            return convertUserToDto(userRepository.findById(id).get());
        }
    }

    private UserDTO convertUserToDto(User user) {
    }

    public void deleteUserById(Long id) {
        if(userRepository.findById(id).isPresent()){
            userRepository.delete(userRepository.findById(id).get());
        }
    }
}
