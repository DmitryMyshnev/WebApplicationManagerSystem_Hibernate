package ua.goIt.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "skills")

public class Skill implements Identity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "language")
    private String language;
    @Column(name = "level")
    private String level;
    @ManyToMany(mappedBy = "skills", fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Developer> developers = new ArrayList<>();

    @Override
    public Long getId() {
        return id;
    }

}
