package elka.aui.serwer.database.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="content")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 10000, nullable = false)
    private String content;

    @Column(name = "length")
    private int length;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Users users;

    @OneToMany(mappedBy = "note")
    private List<ShareUser> shareuser;
}
