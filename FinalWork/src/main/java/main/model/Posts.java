package main.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private boolean visibility;
    @Getter
    @Setter
    private ModerationStatus moderationStatus = ModerationStatus.NEW;
    @Getter
    @Setter
    private String title;

}
