package Save.O.Save.O.Data.Storage.controller;

import Save.O.Save.O.Data.Storage.dto.ReportDTO;
import Save.O.Save.O.Data.Storage.enums.Type;
import Save.O.Save.O.Data.Storage.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/data_storage/report")
public class ReportController {
    @Autowired
    ReportService reportService;

    @GetMapping("/{userId}/balance")
    public List<ReportDTO> getbalance(
            @PathVariable(name="userId")Long userId,
            @RequestParam(name="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(name="endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){
        return reportService.getUserBalance(userId, startDate, endDate);
    }

    @GetMapping("/{userId}/category_stats_by_type")
    public List<ReportDTO> getCategoryStatsByType(
            @PathVariable(name="userId")Long userId,
            @RequestParam(name="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(name="endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(name="type") Type type){
        return reportService.getCategoryStatsByType(userId, startDate, endDate, type);
    }

}
