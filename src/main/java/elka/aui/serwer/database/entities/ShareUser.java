package elka.aui.serwer.database.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
public class ShareUser {
    @Id
    private Long id;

    @ManyToOne
    private Users users;

    @ManyToOne
    private Note note;

}
