package com.alonso.abm.domain.match;

import com.alonso.abm.domain.player.Player;

import java.util.regex.MatchResult;

public interface Match {

    public void addPlayer();
    public MatchStands getResult();


}
