package Save.O.Save.O.Data.Storage.service;

import Save.O.Save.O.Data.Storage.dao.Category;
import Save.O.Save.O.Data.Storage.dao.Transaction;
import Save.O.Save.O.Data.Storage.dao.User;
import Save.O.Save.O.Data.Storage.dto.CategoryDTO;
import Save.O.Save.O.Data.Storage.dto.UserDTO;
import Save.O.Save.O.Data.Storage.enums.Type;
import Save.O.Save.O.Data.Storage.repository.CategoryRepository;
import Save.O.Save.O.Data.Storage.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;


    public CategoryDTO convertCategoryToDto(Category category) {
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
        return categoryDTO;
    }

    public Category convertCategoryToEntity(CategoryDTO categoryDTO) throws ParseException {
        Category category = modelMapper.map(categoryDTO, Category.class);
        return category;
    }

    public void createCategory(CategoryDTO categoryDTO) throws ParseException {
        if(categoryDTO.getUserId()==null || userRepository.findById(categoryDTO.getUserId()).isEmpty()){

            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No user with given id");
        }else{
            User user=userRepository.findById(categoryDTO.getUserId()).get();
            if(!categoryRepository.findByUserAndNameAndType(user, categoryDTO.getName(), categoryDTO.getType()).isEmpty()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Category already exists");
            }else {
                Category category=new Category();
                category.setName(categoryDTO.getName());
                category.setType(categoryDTO.getType());
                category.setUser(user);
                category.setTransactions(new HashSet<>());
                Set<Category> categories=user.getCategories();
                categories.add(category);
                user.setCategories(categories);
                userRepository.save(user);
            }
        }
    }


    public Set<CategoryDTO> getUserCategories(Long user_id) {
        if(userRepository.findById(user_id).isPresent()){
            User user=userRepository.findById(user_id).get();
            Set<Category> categories = user.getCategories();
            return categories.stream()
                    .map(this::convertCategoryToDto)
                    .collect(Collectors.toSet());
        }else return null;
    }
}
