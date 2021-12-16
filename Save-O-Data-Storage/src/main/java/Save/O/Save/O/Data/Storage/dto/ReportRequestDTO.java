package Save.O.Save.O.Data.Storage.dto;

import Save.O.Save.O.Data.Storage.dao.Category;
import Save.O.Save.O.Data.Storage.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportRequestDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private Long id;
    private double  sum;
    private double count;
    private double avg;

}
