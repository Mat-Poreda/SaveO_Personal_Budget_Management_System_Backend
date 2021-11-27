package Save.O.Save.O.Data.Storage.controller;

import Save.O.Save.O.Data.Storage.dto.CategoryDTO;
import Save.O.Save.O.Data.Storage.dto.UserDTO;
import Save.O.Save.O.Data.Storage.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/users/")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/{user_id}/categories")
    public Set<CategoryDTO> getUserCategories(@PathVariable(name="user_id") Long user_id){
        return categoryService.getUserCategories(user_id);
    }
//
//    @GetMapping("/{id}")
//    public UserDTO getAllUsers(@PathVariable(name="id")Long id){
//        return categoryService.getUserById(id);
//    }
//
//    @PostMapping
//    public UserDTO createNewUser(Optional<Long> id){
//        return categoryService.createNewUser(id);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteUserById(@PathVariable(name="id")Long id){
//        categoryService.deleteUserById(id);
//    }


}
