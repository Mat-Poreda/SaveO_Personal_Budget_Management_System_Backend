package Save.O.Save.O.User.Profile.controller;

import Save.O.Save.O.User.Profile.dto.UserDTO;
import Save.O.Save.O.User.Profile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user_details")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getAllUsers(@PathVariable(name="id")Long id){
        return userService.getUserById(id);
    }

    @PostMapping
    public UserDTO createNewUser(Optional<Long> id){
        return userService.createNewUser(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable(name="id")Long id){
        userService.deleteUserById(id);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable(name="id") Long id, @RequestBody UserDTO userDTO) throws Exception{
        return userService.updateUser(id, userDTO);
    }


}
