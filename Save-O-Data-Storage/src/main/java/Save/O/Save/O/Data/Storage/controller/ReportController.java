package Save.O.Save.O.Data.Storage.controller;

import Save.O.Save.O.Data.Storage.dto.ReportDTO;
import Save.O.Save.O.Data.Storage.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/data_storage/report")
public class ReportController {
    @Autowired
    ReportService reportService;

    @GetMapping("/{userId}/balance")
    public ReportDTO getCategoryStats(@PathVariable(name="userId")Long userId, @RequestParam(name="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam(name="endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){
        return reportService.getUserBalance(userId, startDate, endDate);
    }


}
