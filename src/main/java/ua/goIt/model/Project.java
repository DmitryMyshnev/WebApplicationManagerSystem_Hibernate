package ua.goIt.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "projects")
public class Project implements Identity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "project_name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "cost")
    private Integer cost;
    @Column(name = "date")
    private String date;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
            name = "developer_project",
            inverseJoinColumns = { @JoinColumn(name = "developer_id") },
            joinColumns = { @JoinColumn(name = "project_id") }
    )
    private List<Developer> developers =  new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
            name = "company_project",
            inverseJoinColumns = { @JoinColumn(name = "company_id") },
            joinColumns = { @JoinColumn(name = "project_id") }
    )
    private List<Company> companies  =  new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "custom_project",
            inverseJoinColumns = { @JoinColumn(name = "custom_id") },
            joinColumns = { @JoinColumn(name = "project_id") }
    )
    private List<Customer> customers  =  new ArrayList<>();


    @Override
    public Long getId() {
        return id;
    }

}
