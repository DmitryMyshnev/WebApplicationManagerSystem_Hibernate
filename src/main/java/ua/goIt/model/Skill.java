package ua.goIt.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
            name = "developer_skills",
            inverseJoinColumns = { @JoinColumn(name = "developer_id")},
            joinColumns = { @JoinColumn(name = "skills_id")}
    )
    private List<Developer> developers = new ArrayList<>();

    @Override
    public Long getId() {
        return id;
    }

}
