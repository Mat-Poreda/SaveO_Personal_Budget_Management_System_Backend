package Save.O.Save.O.Data.Storage.service;

import Save.O.Save.O.Data.Storage.dao.Category;
import Save.O.Save.O.Data.Storage.dao.User;
import Save.O.Save.O.Data.Storage.dto.UserDTO;
import Save.O.Save.O.Data.Storage.enums.Type;
import Save.O.Save.O.Data.Storage.repository.CategoryRepository;
import Save.O.Save.O.Data.Storage.repository.TransactionRepository;
import Save.O.Save.O.Data.Storage.repository.UserRepository;
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
    CategoryRepository categoryRepository;
    @Autowired
    TransactionRepository transactionRepository;
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
            user.setCategories(new HashSet<>());
            user.setCategories(new HashSet<>(Arrays.asList(
                    new Category(Type.INCOME, "Salary"),
                    new Category(Type.INCOME, "Gift"),
                    new Category(Type.INCOME, "Other"),
                    new Category(Type.EXPENSE, "Food"),
                    new Category(Type.EXPENSE, "Bills"),
                    new Category(Type.EXPENSE, "Transport"),
                    new Category(Type.EXPENSE, "Pets"),
                    new Category(Type.EXPENSE, "Culture"),
                    new Category(Type.EXPENSE, "Education"),
                    new Category(Type.EXPENSE, "Training"),
                    new Category(Type.EXPENSE, "Pleasures"),
                    new Category(Type.DEPOSIT, "Holiday Fund"),
                    new Category(Type.DEPOSIT, "Emergency Savings")
                    )
            ));
            user=userRepository.save(user);
            return convertUserToDto(user);
        }
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertUserToDto)
                .collect(Collectors.toList());
    }


    public UserDTO getUserDTOById(Long id) {
        if(userRepository.findById(id).isEmpty()){
            return null;
        }else{
            return convertUserToDto(userRepository.findById(id).get());
        }
    }

    public User getUserById(Long id) {
        if(userRepository.findById(id).isEmpty()){
            return null;
        }else{
            return userRepository.findById(id).get();
        }
    }

    public void deleteUserById(Long id) {
        if(userRepository.findById(id).isPresent()){
            userRepository.delete(userRepository.findById(id).get());
        }
    }
}
