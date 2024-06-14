package com.alonso.abm.domain.match;

import com.alonso.abm.domain.player.Player;

import java.util.List;

public interface MatchFactory {

    public List<Match> createMatchList(List<Player> players);

}
