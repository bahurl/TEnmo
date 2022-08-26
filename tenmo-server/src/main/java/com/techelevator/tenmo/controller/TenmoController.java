package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TenmoController {

    private AccountDao accountDao;
    private UserDao userDao;

    public TenmoController(AccountDao accountDao, UserDao userDao) {
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

    //url: http://localhost:8080/account?userid=1001
    @GetMapping(value = "/account")
    public Account getAccountByUserId(@RequestParam long userid) {

        return accountDao.findAccountByUserId(userid);
    }


    @GetMapping(value = "/users")
    public List<User> findAllUsers() {
        return userDao.findAll();
    }

    @GetMapping(value = "/user/{id}")
    public User findUserByID(@PathVariable long id) {

    }


}
