package com.alonso.abm.domain.player;

import java.security.InvalidParameterException;
import java.time.LocalDate;

public class PlayerBuilder {

    private String firstName;
    private String lastName;
    private String nickName;
    private String email;
    private LocalDate dateOfBirth;

    public PlayerBuilder firstName(String firstName){
        this.firstName = firstName;
        return this;
    }
    public PlayerBuilder email(String email){
        this.email = email;
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
            throw new InvalidParameterException("Invalid date of birth" + dateOfBirth );
        this.dateOfBirth = dateOfBirth;
        return this;
    }
    public Player build(){
        Player player = new Player();
        player.setFirstName(this.firstName);
        player.setLastName(this.lastName);
        player.setEmail(this.email);
        player.setNickName(this.nickName);
        player.setDateOfBirth(this.dateOfBirth);
        return player;
    }

}
