package Save.O.Save.O.Data.Storage.controller;

import Save.O.Save.O.Data.Storage.dto.UserDTO;
import Save.O.Save.O.Data.Storage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<UserDTO> getAllUsers(){
    return userService.getAllUsers();
}

    @GetMapping("/{id}")
    public UserDTO getUserDTO(@PathVariable(name="id")Long id){
        return userService.getUserDTOById(id);
    }

    @PostMapping
    public UserDTO createNewUser(Optional<Long> id){
        return userService.createNewUser(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable(name="id")Long id){
        userService.deleteUserById(id);
    }


}
