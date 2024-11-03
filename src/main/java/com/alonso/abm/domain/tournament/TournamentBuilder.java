package com.alonso.abm.domain.tournament;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;

public class TournamentBuilder {
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime finalDate;

    public TournamentBuilder Name(String name) {
        if(name == null || name.isEmpty())
            throw new InvalidParameterException("Invalid name!");
        this.name = name;
        return this;
    }

    public TournamentBuilder startDate(LocalDateTime startDate){
        if(startDate.isAfter(LocalDateTime.now()))
            throw new InvalidParameterException("Invalid start date!");
        this.startDate = startDate;
        return this;
    }

    public TournamentBuilder finalDate(LocalDateTime finalDate) {
        if(finalDate.isBefore(this.startDate) || finalDate.equals(this.startDate))
            throw new InvalidParameterException("Invalid final date!");
        this.finalDate = finalDate;
        return this;
    }

    public Tournament build(){
        Tournament tournament = new Tournament();
        tournament.setName(name);
        tournament.setStartDay(startDate);
        tournament.setFinalDay(finalDate);
        return tournament;
    }

}
