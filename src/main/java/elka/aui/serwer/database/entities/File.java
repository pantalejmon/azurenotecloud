package elka.aui.serwer.database.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
public class File {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Column(length = 40, updatable = false, nullable = false)
    private String fileName;
    @Column(length = 1000, updatable = false, nullable = false)
    private String url;

    private String fileType;

    @Lob
    private byte[] data;

    //tu jeszcze note_id

}
