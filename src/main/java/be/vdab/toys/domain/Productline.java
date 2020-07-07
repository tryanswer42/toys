package be.vdab.toys.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name ="productlines")
public class Productline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String name;

    @Lob
    private String description;

    @Version
    private long version;

    protected Productline(){}

    public Productline(@NotBlank String name, String description) {
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
