package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todos;
import com.lambdaschool.todos.models.User;
import com.lambdaschool.todos.repository.TodosRepository;
import com.lambdaschool.todos.repository.UserRepository;
import com.lambdaschool.todos.views.UserNameCountTodos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements the Userservice Interface
 */
@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService
{
    /**
     * Connects this service to the User table.
     */
    @Autowired
    private UserRepository userrepos;

    @Autowired
    private TodosRepository todosrepo;

    /**
     * Connects this service to the auditing service in order to get current user name
     */
    @Autowired
    private UserAuditing userAuditing;

    public User findUserById(long id) throws EntityNotFoundException
    {
        return userrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User id " + id + " not found!"));
    }

    @Override
    public List<User> findAll()
    {
        List<User> list = new ArrayList<>();
        /*
         * findAll returns an iterator set.
         * iterate over the iterator set and add each element to an array list.
         */
        userrepos.findAll()
            .iterator()
            .forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        userrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User id " + id + " not found!"));
        userrepos.deleteById(id);
    }

    @Transactional
    @Override
    public User save(User user)
    {
        //create a new user to tack stuff onto in a controlled manner
        User newUser = new User();

        //tack stuff on
        newUser.setUsername(user.getUsername()
            .toLowerCase());
        newUser.setPassword(user.getPassword());
        newUser.setPrimaryemail(user.getPrimaryemail()
            .toLowerCase());

        //make sure there are no random todos in our new user
        newUser.getTodos().clear();
        //loop through todos in the passed in user
        for( Todos t: user.getTodos()){

            //create a new todos to tack stuff onto
            Todos newTodo = new Todos();

            //tack stuff on
            newTodo.setDescription(t.getDescription());
            newTodo.setUser(newUser);

            //add that new todos to our new user
            newUser.getTodos().add(newTodo);
        }

        //save the user and return it
        return userrepos.save(newUser);
    }

    @Override
    public List<UserNameCountTodos> getCountUserTodos()
    {
        return null;
    }
}
