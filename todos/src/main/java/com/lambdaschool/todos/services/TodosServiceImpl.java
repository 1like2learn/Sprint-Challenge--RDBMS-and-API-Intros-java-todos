package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todos;
import com.lambdaschool.todos.repository.TodosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

//class will alter data so it is annotated as transactional
//let's spring framework know this is a service
@Transactional
@Service(value = "todosService")
public class TodosServiceImpl implements TodosService{

    //lets us use the repo in this class
    @Autowired
    private TodosRepository todosrepo;

    //change a todos completed field to true based off it's id
    @Transactional
    @Override
    public void markComplete(long todoid) {

        //finds a todos and verifies that it exists
        Todos todo = todosrepo.findById(todoid)
            .orElseThrow(() -> new EntityNotFoundException("Todo " + todoid + " Not Found."));
        //sets that todos completed field to true
        todo.setCompleted(true);
    }

    //verifies a todos fields and saves it into the repository
    @Transactional
    @Override
    public Todos save(Todos todos) {

        //uses the repo's save method to save a todos
        return todosrepo.save(todos);
    }
}
