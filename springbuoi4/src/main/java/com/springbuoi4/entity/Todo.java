package com.springbuoi4.entity;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Min(value = 1)
    private Long id;

    @Size(min = 5 , message = "title must greater than 4 characters!")
    @NotBlank(message = "title must not blank!")
    @Column(name = "title")
    private String title;

    public Todo(Long id , String title) {
        this.id = id;
        this.title = title;
    }

    public Todo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
