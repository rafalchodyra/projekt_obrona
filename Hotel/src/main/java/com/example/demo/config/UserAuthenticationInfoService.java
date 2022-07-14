package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.dao.UserRepository;
import com.example.demo.entities.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserAuthenticationInfoService {

    private final Map<String, User> infos = new HashMap<String, User>();

    public void addUserInfo(User userInfo){
        infos.put(userInfo.getName(), userInfo);
    }

    public User getUserInfo(String username) {
        return infos.get(username);
    }

}