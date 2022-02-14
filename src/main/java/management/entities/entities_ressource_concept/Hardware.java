package management.entities.entities_ressource_concept;

import javax.persistence.*;

@Entity
@Table(name = "hardware")
public class Hardware {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String type;

    private String name;

    public Hardware() {
    }

    public Hardware(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
