package com.sec.service;

import com.sec.models.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    List<UserDetails> userList = new ArrayList<>();

    public UserService() {
        userList.add(new UserDetails("abc","1111","abc@gmail.com"));
        userList.add(new UserDetails("def","2222","def@gmail.com"));
        userList.add(new UserDetails("ghi","3333","ghis@gmail.com"));
        userList.add(new UserDetails("jkl","4444","jkl@gmail.com"));
        userList.add(new UserDetails("mno","5555","mno@gmail.com"));
    }

    //get all user
    public List<UserDetails> getAllUsers(){
        return  userList ;
    }
    // get single user
    public  UserDetails getSingleuser( String username ){
//        UserDetails singleUser = userList.get(0);
        UserDetails singleUser = userList.stream().filter((user) -> user.getUsername().equals(username)).findAny().orElse(null);

        return singleUser;
    }
    //Adding user
    public UserDetails AddingUser (UserDetails userDetails){
//        UserDetails dbUser = new UserDetails();
//        dbUser.setUsername(userDetails.getUsername());
        userList.add(userDetails);
        return userDetails;

    }

}
