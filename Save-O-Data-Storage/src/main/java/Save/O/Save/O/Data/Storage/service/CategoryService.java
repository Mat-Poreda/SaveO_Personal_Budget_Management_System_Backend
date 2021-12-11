package Save.O.Save.O.Data.Storage.service;

import Save.O.Save.O.Data.Storage.dao.Category;
import Save.O.Save.O.Data.Storage.dao.Transaction;
import Save.O.Save.O.Data.Storage.dao.User;
import Save.O.Save.O.Data.Storage.dto.CategoryDTO;
import Save.O.Save.O.Data.Storage.dto.ReportRequestDTO;
import Save.O.Save.O.Data.Storage.dto.UserDTO;
import Save.O.Save.O.Data.Storage.enums.Type;
import Save.O.Save.O.Data.Storage.repository.CategoryRepository;
import Save.O.Save.O.Data.Storage.repository.UserRepository;
import org.apache.tomcat.jni.Local;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;
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
//            if(!categoryRepository.findByUserAndNameAndType(user, categoryDTO.getName(), categoryDTO.getType()).isEmpty()) {
//                throw new ResponseStatusException(HttpStatus.CONFLICT, "Category already exists");
//            }else {
                Category category=new Category();
                category.setName(categoryDTO.getName());
                category.setType(categoryDTO.getType());
                category.setTransactions(new HashSet<>());
                Set<Category> categories=user.getCategories();
                categories.add(category);
                user.setCategories(categories);
                userRepository.save(user);
            }
        }
//    }


    public Set<CategoryDTO> getUserCategories(Long user_id, Type type, LocalDate startDate, LocalDate endDate) {
        if(userRepository.findById(user_id).isPresent()){
            User user=userRepository.findById(user_id).get();
            Set<Category> categories = user.getCategories();
            categories=categories.stream()
                    .filter(f -> type.compareTo(f.getType()) == 0)
                    .collect(Collectors.toSet());
            return categories.stream()
                    .map(this::convertCategoryToDto)
                    .collect(Collectors.toSet());
        }else return null;
    }

    public Category getCategory(Long categoryId, Long userId){

        if(categoryRepository.findById(categoryId).isPresent() ){
            return categoryRepository.getById(categoryId);
        }else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
    }

    public ReportRequestDTO getCategoryStats(Long categoryId, LocalDate startDate, LocalDate endDate){
        ReportRequestDTO report=new ReportRequestDTO();
        report.setId(categoryId);
        report.setStartDate(startDate);
        report.setEndDate(endDate);
        if(categoryRepository.findById(categoryId).isPresent()){
            String uncutList=categoryRepository.getUserCategoriesStats(categoryId, startDate, endDate);
            if(uncutList!=null && uncutList!=""){
            String[] list=uncutList.split(",");

                report.setSum(Double.parseDouble(list[1]));
                report.setCount(Double.parseDouble(list[2]));
                report.setAvg(Double.parseDouble(list[3]));
            }
        }
        return report;
    }

    public CategoryDTO getCategoryDTOById(Long categoryId) {
        if(categoryRepository.findById(categoryId).isPresent()){
            return convertCategoryToDto(categoryRepository.getById(categoryId));
        }else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
    }

    public void deleteCategoryById(Long id) {
        if(categoryRepository.findById(id).isPresent()){
            categoryRepository.delete(categoryRepository.getById(id));
        }
    }
}
