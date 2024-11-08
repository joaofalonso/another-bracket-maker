package com.alonso.abm.domain.player;

import java.security.InvalidParameterException;
import java.time.LocalDate;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class PlayerBuilder {

    private String firstName;
    private String lastName;
    private String nickName;
    private String email;
    private LocalDate dateOfBirth;

    private void validateEmail(){
        if(this.email.trim().isEmpty())
            throw new RuntimeException("Player email can't be empty!");
        Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher matcher = pattern.matcher(this.email);
        if(!matcher.find())
            throw new RuntimeException("Invalid email format!");
    }

    public PlayerBuilder firstName(String firstName){
        this.firstName = firstName;
        return this;
    }
    public PlayerBuilder email(String email){
        this.email = email;
        this.validateEmail();
        return this;
    }
    public PlayerBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    public PlayerBuilder nickName(String nickName){
        this.nickName = nickName;
        return this;
    }
    public PlayerBuilder dateOfBirth(LocalDate dateOfBirth){
        if(dateOfBirth.isAfter(LocalDate.now()))
            throw new InvalidParameterException("Invalid date of birth");
        this.dateOfBirth = dateOfBirth;
        return this;
    }
    public Player build(){
        Player player = new Player();
        player.setFirstName(this.firstName);
        player.setLastName(this.lastName);
        this.validateEmail();
        player.setEmail(this.email);
        player.setNickName(this.nickName);
        player.setDateOfBirth(this.dateOfBirth);
        return player;
    }

}
