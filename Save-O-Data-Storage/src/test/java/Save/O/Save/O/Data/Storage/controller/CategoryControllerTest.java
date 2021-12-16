package Save.O.Save.O.Data.Storage.controller;

import Save.O.Save.O.Data.Storage.dao.Category;
import Save.O.Save.O.Data.Storage.dao.Transaction;
import Save.O.Save.O.Data.Storage.dao.User;
import Save.O.Save.O.Data.Storage.enums.Type;
import Save.O.Save.O.Data.Storage.repository.CategoryRepository;
import Save.O.Save.O.Data.Storage.repository.TransactionRepository;
import Save.O.Save.O.Data.Storage.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CategoryControllerTest {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private MockMvc mockMvc;

    User user1;
    User user2;
    Category category1;
    Category category2;
    Category category3;
    Transaction transaction1;
    Transaction transaction2;
    Transaction transaction3;
    LocalDate date;

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        user1=new User(new HashSet<>(), 1L);
        user2=new User(new HashSet<>(), 2L);
        category1=new Category(Type.INCOME, "Salary");
        category1.setTransactions(new HashSet<>());
        category2=new Category(Type.EXPENSE, "Rent");
        category2.setTransactions(new HashSet<>());
        category3=new Category(Type.DEPOSIT, "Holiday fund");
        category3.setTransactions(new HashSet<>());
        LocalDate date = LocalDate.of(2021,1,1);
        LocalDate date2 = LocalDate.of(2021,1,15);
        LocalDate date3 = LocalDate.of(2021,1,3);
        transaction1=new Transaction("salary",new BigDecimal("5000"),date);
        transaction2=new Transaction("rent payment",new BigDecimal("2000"),date2);
        transaction3=new Transaction("savings for holidays",new BigDecimal("1000"),date3);
        category1.getTransactions().add(transaction1);
        category2.getTransactions().add(transaction2);
        category3.getTransactions().add(transaction3);
        user1.getCategories().addAll(List.of(category1,category2,category3));
        userRepository.save(user1);

    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        categoryRepository.deleteAll();
        transactionRepository.deleteAll();
    }

    @Test
    void getUserCategories() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/api/data_storage/category/1/INCOME")).andExpect(status().isOk()).andReturn();
        Assertions.assertTrue(result.getResponse().getContentAsString().contains("Salary"));
    }

    @Test
    void getCategoryDTOById() {
    }

    @Test
    void getCategoryStats() {
    }

    @Test
    void createCategory() {
    }

    @Test
    void deleteCategoryById() {
    }
}