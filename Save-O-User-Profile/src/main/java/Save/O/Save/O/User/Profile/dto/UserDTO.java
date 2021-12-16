package Save.O.Save.O.User.Profile.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String nickname;
    private String email;
    private String bio;
    private String imageId;

    public UserDTO(String email) {
        this.email = email;
        this.bio = "";
        this.username = "";
    }
}
