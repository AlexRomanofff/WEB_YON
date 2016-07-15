package com.yon.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "corporation")
public class Corporation implements Serializable {

    private static final long serialVersionUID = 2161077342148082851L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
