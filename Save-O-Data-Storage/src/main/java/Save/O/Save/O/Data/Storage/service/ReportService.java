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
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserRepository userRepository;

    public List<ReportDTO> getUserBalance(Long userId, LocalDate startDate, LocalDate endDate) {
        if (userRepository.findById(userId).isPresent()) {
            LocalDate startDateChecked = ((startDate==null) ? LocalDate.parse("1900-01-01") : startDate);
            LocalDate endDateChecked = ((endDate==null) ? LocalDate.parse("2999-01-01") : endDate);
            String[] balance=categoryRepository.getBalance(userId, startDateChecked, endDateChecked);
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
            LocalDate startDateChecked = ((startDate==null) ? LocalDate.parse("1900-01-01") : startDate);
            LocalDate endDateChecked = ((endDate==null) ? LocalDate.parse("2999-01-01") : endDate);
            String[] balance = categoryRepository.getCategoryStatsByType(userId, startDateChecked, endDateChecked, type.toString());

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

    public List<ReportDTO> getUserTypes(Long userId, LocalDate startDate, LocalDate endDate) {
        if (userRepository.findById(userId).isPresent()) {
            LocalDate startDateChecked = ((startDate==null) ? LocalDate.parse("1900-01-01") : startDate);
            LocalDate endDateChecked = ((endDate==null) ? LocalDate.parse("2999-01-01") : endDate);
            String[] balance = categoryRepository.getTypeSum(userId, startDateChecked, endDateChecked);
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







