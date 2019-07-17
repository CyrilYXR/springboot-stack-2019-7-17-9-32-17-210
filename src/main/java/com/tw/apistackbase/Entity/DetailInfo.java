package com.tw.apistackbase.Entity;

import javax.persistence.*;

@Entity
public class DetailInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 255, nullable = false)
    private String subjectiveElement;

    @Column(length = 255, nullable = false)
    private String objectiveElement;

    public DetailInfo() {
    }

    public DetailInfo(String subjectiveElement, String objectiveElement) {
        this.subjectiveElement = subjectiveElement;
        this.objectiveElement = objectiveElement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectiveElement() {
        return subjectiveElement;
    }

    public void setSubjectiveElement(String subjectiveElement) {
        this.subjectiveElement = subjectiveElement;
    }

    public String getObjectiveElement() {
        return objectiveElement;
    }

    public void setObjectiveElement(String objectiveElement) {
        this.objectiveElement = objectiveElement;
    }
}
