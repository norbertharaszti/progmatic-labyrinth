package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;
import com.progmatic.labyrinthproject.interfaces.Player;

public class RandomPlayer implements Player {

    @Override
    public Direction nextMove(Labyrinth l) {

        int multiplier = l.possibleMoves().size();
        int i = (int) (Math.random() * multiplier);
        return l.possibleMoves().get(i);

    }
}
