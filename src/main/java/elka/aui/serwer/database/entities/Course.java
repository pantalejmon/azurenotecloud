package elka.aui.serwer.database.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
public class Course {
    @Id
    private Long id;

    @Column(length = 20, nullable = false, updatable = false)
    private String name;

    @Column(length = 6, nullable = false, updatable = false)
    private String code;
}
