package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.CellType;
import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.exceptions.CellException;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;
import com.progmatic.labyrinthproject.interfaces.Player;

import java.util.ArrayList;

public class ConsciousPlayer implements Player {

    @Override
    public Direction nextMove(Labyrinth l) {
        return null;
    }
    private int[][] stepsNeeded (Labyrinth l) throws CellException {
        int[][] stepsNeeded = new int[l.getWidth()][l.getHeight()];
        for (int i = 0; i <l.getHeight() ; i++) {
        for (int j = 0; j <l.getWidth() ; j++) {
        if(l.getCellType(new Coordinate(j,i))==CellType.WALL){
            stepsNeeded[j][i]=-1;
        } else if(l.getCellType(new Coordinate(j,i))==CellType.START){
            stepsNeeded[j][i]=0;
        } else {
            if(stepsNeeded[j-1][i]==0||stepsNeeded[j][i-1]==0){
                stepsNeeded[j][i]=1;
            }
        }
}
        }
        return stepsNeeded;
    }
}

