package com.tw.apistackbase.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Procuratorate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Prosecutor> prosecutors;

    public Procuratorate() {
    }

    public Procuratorate(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Prosecutor> getProsecutors() {
        return prosecutors;
    }

    public void setProsecutors(List<Prosecutor> prosecutors) {
        this.prosecutors = prosecutors;
    }
}
