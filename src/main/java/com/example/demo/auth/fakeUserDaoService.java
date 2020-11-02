package com.example.demo.auth;

import com.example.demo.security.UserRole;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

@Repository("fake")
public class fakeUserDaoService implements UserDao {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> selectUserByUsername(String username) {
        return getUsers().stream().filter(user->user.getUsername()
                .equals(username)).findFirst();
    }

    private List<User> getUsers(){
        List<User> users = Lists.newArrayList(
                new User(UserRole.CEO.getGrantedAuthorities(),passwordEncoder.encode("password"),"wael"
                , true,true,true,true),
                new User(UserRole.ADMINTRAINEE.getGrantedAuthorities(),passwordEncoder.encode("password1"),"anna"
                        , true,true,true,true),
                new User(UserRole.DEV.getGrantedAuthorities(),passwordEncoder.encode("password2"),"tom"
                        , true,true,true,true)
        );
        return users;

    }
}
