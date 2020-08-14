package com.lambdaschool.todos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

//entity that manages actions with the todos table
@Entity
@Table(name = "todos")
public class Todos extends Auditable{

    /** Fields*/
    //dims and generate the primary id that we will use to identify objects
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long todoid;

    //dims a relationship between user and todos
    //many todos to one user
    //tables are joined on the userid that the two tables share
    //ignore todos so we don't get an infinite loop
    @ManyToOne()
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnoreProperties("todos")
    private User user;

    private String description;
    private Boolean completed = false;


    /** Constructors */
    public Todos() {
    }

    public Todos(
        User user,
        String description) {
        this.user = user;
        this.description = description;
    }

    /** Getters and Setters*/
    public long getTodoid() {
        return todoid;
    }

    public void setTodoid(long todoid) {
        this.todoid = todoid;
    }

    public com.lambdaschool.todos.models.User getUser() {
        return user;
    }

    public void setUser(com.lambdaschool.todos.models.User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
