package ua.goIt.model;

import lombok.Data;

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

    @ManyToMany(mappedBy = "projects", fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Developer> developers =  new ArrayList<>();

    @ManyToMany(mappedBy = "projects", fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Company> companies  =  new ArrayList<>();

    @ManyToMany(mappedBy = "projects", fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Customer> customers  =  new ArrayList<>();


    @Override
    public Long getId() {
        return id;
    }

}
