package Save.O.Save.O.User.Profile.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {

    private Long id;
    @Lob
    private byte[] content;
    private String name;

}