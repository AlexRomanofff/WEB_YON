package com.yon.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name= "login", nullable = false)
    private String login;

    @Column(name="pass")
    private String password;

    @Column
    private String city;

    @Column(name= "country_code", length = 3)
    private String countryCode;

    @ManyToOne
    @JoinColumn(name="corproration_id", referencedColumnName = "id")
    private Corporation corporation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Corporation getCorporation() {
        return corporation;
    }

    public void setCorporation(Corporation corporationId) {
        this.corporation = corporationId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
