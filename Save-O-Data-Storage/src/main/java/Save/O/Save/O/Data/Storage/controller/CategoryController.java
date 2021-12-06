package Save.O.Save.O.Data.Storage.controller;

import Save.O.Save.O.Data.Storage.dto.CategoryDTO;
import Save.O.Save.O.Data.Storage.dto.UserDTO;
import Save.O.Save.O.Data.Storage.enums.Type;
import Save.O.Save.O.Data.Storage.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/data_storage/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/{user_id}/{type}")
    public Set<CategoryDTO> getUserCategories(@PathVariable(name="user_id") Long user_id, @PathVariable(name="type") Type type){
        System.out.println(type);
        System.out.println(type.compareTo(Type.DEPOSIT));
        return categoryService.getUserCategories(user_id, type);
    }

    @GetMapping("/{categoryId}")
    public CategoryDTO getCategoryDTOById(@PathVariable(name="categoryId")Long categoryId){
        return categoryService.getCategoryDTOById(categoryId);
    }

    @PostMapping
    public void createCategory(@RequestBody CategoryDTO categoryDTO) throws ParseException {
        categoryService.createCategory(categoryDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable(name="id")Long id){
        categoryService.deleteCategoryById(id);
    }


}
