package elka.aui.serwer.database.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int id;
    @Column(name = "role", unique = true, updatable = false, nullable = false)
    private String role;

    public Role(String role) {
        this.role = role;
    }
}
