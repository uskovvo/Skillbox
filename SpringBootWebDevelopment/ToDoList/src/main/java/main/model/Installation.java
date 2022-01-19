package main.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Installation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String city;
    @Getter
    @Setter
    private String street;
    @Getter
    @Setter
    private int house;
    @Getter
    @Setter
    private int salary;
}
