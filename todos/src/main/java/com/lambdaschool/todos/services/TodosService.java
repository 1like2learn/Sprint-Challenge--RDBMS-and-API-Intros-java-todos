package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todos;

public interface TodosService
{

    //change a todos completed field to true based off it's id
    public void markComplete(long todoid);

    //verifies a todos fields and saves it into the repository
    public Todos save(Todos todos);
}
