package com.yon.model;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="YON_Actions")
public class Action implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="id_yon", nullable = false, referencedColumnName = "id")
    private Yon yonID;

    @Column(name="attribute", nullable = false)
    private String attribute = "0";

    @Column(name="date")
    private Date date;

    @Column(name="state")
    @Enumerated(EnumType.STRING)
    private State status;

    public Yon getYonID() {
        return yonID;
    }

    public void setYonID(Yon yonID) {
        this.yonID = yonID;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public State getStatus() {
        return status;
    }

    public void setStatus(State status) {
        this.status = status;
    }
    public int getId() {
        return id;
    }
}

