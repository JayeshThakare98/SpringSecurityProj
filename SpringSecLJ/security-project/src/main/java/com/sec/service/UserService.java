package com.sec.service;

import com.sec.models.UserDetails;
import com.sec.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
//    List<UserDetails> userList = new ArrayList<>();

//    public UserService() {
//        userList.add(new UserDetails("abc","1111","abc@gmail.com"));
//        userList.add(new UserDetails("def","2222","def@gmail.com"));
//        userList.add(new UserDetails("ghi","3333","ghis@gmail.com"));
//        userList.add(new UserDetails("jkl","4444","jkl@gmail.com"));
//        userList.add(new UserDetails("mno","5555","mno@gmail.com"));
//    }
    //create User
    public UserDetails creteUserDetails(UserDetails userDetails){
        UserDetails saved = userRepo.save(userDetails);
        return  saved ;
    }
    //get all user
    public List<UserDetails> getAllUsers(){
        return  userRepo.findAll() ;
    }
    //get all user
    public Optional<UserDetails> getUserById(Integer userId){
        Optional<UserDetails> byId = userRepo.findById(userId);
        return byId ;
    }
//updqate by ud
    public UserDetails upDateUserByid(Integer userId ,  UserDetails userDetails){
//        Optional<UserDetails> byId = Optional.ofNullable(userRepo.findById(userId).orElseThrow(() -> new RuntimeException("Id not found for this")));
//        Optional<UserDetails> save = userRepo.save(byId);
        UserDetails byId = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("user id " + userId + "Is not present in data base "));
        UserDetails updatedUser = userRepo.save(byId);
        return updatedUser ;
    }


    //get all user
//    public List<UserDetails> getAllUsers(){
//        return  userList ;
//    }
    // get single user
//    public  UserDetails getSingleuser( String username ){
//        UserDetails singleUser = userList.get(0);
//        UserDetails singleUser = userList.stream().filter((user) -> user.getUsername().equals(username)).findAny().orElse(null);

//        return singleUser;
//    }
//    //Adding user
//    public UserDetails AddingUser (UserDetails userDetails){
////        UserDetails dbUser = new UserDetails();
////        dbUser.setUsername(userDetails.getUsername());
//        userList.add(userDetails);
//        return userDetails;
//
//    }

}
