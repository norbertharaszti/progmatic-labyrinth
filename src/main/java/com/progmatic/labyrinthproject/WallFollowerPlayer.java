package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;
import com.progmatic.labyrinthproject.interfaces.Player;

import java.util.ArrayList;

public class WallFollowerPlayer implements Player {
    protected Direction lastStep;

    @Override
    public Direction nextMove(Labyrinth l) {
        ArrayList<Direction> possibleLeftHandMoves = new ArrayList<>();
        if (lastStep == null) {
            lastStep = Direction.EAST;
        }
        if (lastStep == Direction.EAST) {
            if (l.possibleMoves().contains(Direction.NORTH)) {
                lastStep = Direction.NORTH;
                return Direction.NORTH;
            } else if (l.possibleMoves().contains(Direction.EAST)) {
                return Direction.EAST;
            } else if (l.possibleMoves().contains(Direction.SOUTH)) {
                lastStep = Direction.SOUTH;
                return Direction.SOUTH;
            } else {
                lastStep = Direction.WEST;
                return Direction.WEST;
            }
        } else if (lastStep == Direction.SOUTH) {
            if (l.possibleMoves().contains(Direction.EAST)) {
                lastStep = Direction.EAST;
                return Direction.EAST;
            } else if (l.possibleMoves().contains(Direction.SOUTH)) {
                return Direction.SOUTH;
            } else if (l.possibleMoves().contains(Direction.WEST)) {
                lastStep = Direction.WEST;
                return Direction.WEST;
            } else {
                lastStep = Direction.NORTH;
                return Direction.NORTH;
            }
        } else if (lastStep == Direction.WEST) {
            if (l.possibleMoves().contains(Direction.SOUTH)) {
                lastStep = Direction.SOUTH;
                return Direction.SOUTH;
            } else if (l.possibleMoves().contains(Direction.WEST)) {
                return Direction.WEST;
            } else if (l.possibleMoves().contains(Direction.NORTH)) {
                lastStep = Direction.NORTH;
                return Direction.NORTH;
            } else {
                lastStep = Direction.EAST;
                return Direction.EAST;
            }
        } else {
            if (l.possibleMoves().contains(Direction.WEST)) {
                lastStep = Direction.WEST;
                return Direction.WEST;
            } else if (l.possibleMoves().contains(Direction.NORTH)) {
                return Direction.NORTH;
            } else if (l.possibleMoves().contains(Direction.EAST)) {
                lastStep = Direction.EAST;
                return Direction.EAST;
            } else {
                lastStep = Direction.SOUTH;
                return Direction.SOUTH;
            }
        }
    }
}
