package ua.goIt.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "developers")
public class Developer implements Identity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private Integer age;
    @Column(name = "sex")
    private String sex;
    @Column(name = "salary")
    private Integer salary;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
            name = "developer_skills",
            joinColumns = { @JoinColumn(name = "developer_id")},
            inverseJoinColumns = { @JoinColumn(name = "skills_id")}
    )

    private List<Skill> skills = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
            name = "developer_project",
            joinColumns = { @JoinColumn(name = "developer_id") },
            inverseJoinColumns = { @JoinColumn(name = "project_id") }
    )
    private List<Project> projects =  new ArrayList<>();

    @Override
    public Long getId() {
        return id;
    }

}
