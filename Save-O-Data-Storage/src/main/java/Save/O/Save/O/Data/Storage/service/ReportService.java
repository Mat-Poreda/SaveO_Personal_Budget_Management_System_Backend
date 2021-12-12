package Save.O.Save.O.Data.Storage.service;

import Save.O.Save.O.Data.Storage.dto.ReportDTO;
import Save.O.Save.O.Data.Storage.repository.CategoryRepository;
import Save.O.Save.O.Data.Storage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class ReportService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;


    public ReportDTO getUserBalance(Long userId, LocalDate startDate, LocalDate endDate) {
        if (userRepository.findById(userId).isPresent()) {
            String balance=categoryRepository.getBalance(userId, startDate, endDate);
            ReportDTO report=new ReportDTO();
            if(balance!=null && balance!=""){
                String[] list=balance.split(",");
                report.setName(list[0]);
                report.setValue(Double.parseDouble(list[1]));
            }
            return report;
        } else return null;
    }
}






