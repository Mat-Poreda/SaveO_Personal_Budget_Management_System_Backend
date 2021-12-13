package Save.O.Save.O.Data.Storage.service;

import Save.O.Save.O.Data.Storage.dto.ReportDTO;
import Save.O.Save.O.Data.Storage.enums.Type;
import Save.O.Save.O.Data.Storage.repository.CategoryRepository;
import Save.O.Save.O.Data.Storage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserRepository userRepository;

    public List<ReportDTO> getUserBalance(Long userId, LocalDate startDate, LocalDate endDate) {
        if (userRepository.findById(userId).isPresent()) {
            String[] balance=categoryRepository.getBalance(userId, startDate, endDate);
            List<ReportDTO> report=new ArrayList<>();
            if(balance!=null && balance.length>0){
                for(int i=0;i<balance.length;i++){
                    ReportDTO temp=new ReportDTO();
                    String[] list=balance[i].split(",");
                    temp.setName(list[0]);
                    temp.setValue(Double.parseDouble(list[1]));
                    report.add(temp);
                }
            }
            return report;
        } else return null;
    }

    public List<ReportDTO> getCategoryStatsByType(Long userId, LocalDate startDate, LocalDate endDate, Type type) {
        if (userRepository.findById(userId).isPresent()) {
            String[] balance=categoryRepository.getCategoryStatsByType(userId, startDate, endDate, type.toString());
            List<ReportDTO> report=new ArrayList<>();
            if(balance!=null && balance.length>0){
                for(int i=0;i<balance.length;i++){
                    ReportDTO temp=new ReportDTO();
                    String[] list=balance[i].split(",");
                    temp.setName(list[0]);
                    temp.setValue(Double.parseDouble(list[1]));
                    report.add(temp);
                }
            }
            return report;
        } else return null;
    }
}






