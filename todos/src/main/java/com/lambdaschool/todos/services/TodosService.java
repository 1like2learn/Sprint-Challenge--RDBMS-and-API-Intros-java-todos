package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todos;

public interface TodosService
{
    public void markComplete(long todoid);
    public Todos save(Todos todos);
}
