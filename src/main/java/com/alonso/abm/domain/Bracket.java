package com.alonso.abm.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bracket {
    Long id;
    Set<Round> rounds;

    public Bracket(){
        this.rounds = new HashSet<Round>();
    }

}
