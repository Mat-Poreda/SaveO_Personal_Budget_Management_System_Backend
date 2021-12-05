package Save.O.Save.O.Data.Storage.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @OneToMany( cascade = CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval = true)
    @JsonIgnore
    @JoinColumn(name="user_id")
    private Set<Category> categories;

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



}
