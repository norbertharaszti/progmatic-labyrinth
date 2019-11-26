package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.CellType;
import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.exceptions.CellException;
import com.progmatic.labyrinthproject.exceptions.InvalidMoveException;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author pappgergely
 */
public class LabyrinthImpl implements Labyrinth {
    protected CellType[][] labyrinth;
    protected Coordinate playerPosition;

    public LabyrinthImpl() {

    }
    @Override
    public int getWidth() {
        if (labyrinth == null) {
            return -1;
        }
        return labyrinth.length;
    }

    @Override
    public int getHeight() {
        if (labyrinth == null) {
            return -1;
        }
        return labyrinth[0].length;
    }

    @Override
    public void loadLabyrinthFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            int width = Integer.parseInt(sc.nextLine());
            int height = Integer.parseInt(sc.nextLine());
            setSize(width, height);
            for (int hh = 0; hh < height; hh++) {
                String line = sc.nextLine();
                for (int ww = 0; ww < width; ww++) {
                    switch (line.charAt(ww)) {
                        case 'W':
                            labyrinth[ww][hh] = CellType.WALL;
                            break;
                        case 'E':
                            labyrinth[ww][hh] = CellType.END;
                            break;
                        case 'S':
                            labyrinth[ww][hh] = CellType.START;
                            playerPosition = new Coordinate(ww, hh);
                            break;
                        case ' ':
                            labyrinth[ww][hh] = CellType.EMPTY;
                    }
                }
            }
        } catch (FileNotFoundException | NumberFormatException ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public CellType getCellType(Coordinate c) throws CellException {
        if (c.getCol() >= 0 && c.getCol() < labyrinth[0].length && c.getRow() >= 0 && c.getRow() < labyrinth.length) {
            return labyrinth[c.getCol()][c.getRow()];
        } else {
            throw new CellException(c.getCol(), c.getRow(), "This coordinate is invalid!");
        }
    }

    public void setSize(int width, int height) {
        labyrinth = new CellType[width][height];
    }

    @Override
    public void setCellType(Coordinate c, CellType type) throws CellException {
        if (c.getCol() >= 0 && c.getCol() < labyrinth[0].length && c.getRow() >= 0 && c.getRow() < labyrinth.length) {
            labyrinth[c.getCol()][c.getRow()] = type;
            if (type == CellType.START) {
                playerPosition = new Coordinate(c.getCol(), c.getRow());
            }
        } else {
            throw new CellException(c.getRow(), c.getCol(), "This coordinate is invalid!");
        }
    }

    @Override
    public Coordinate getPlayerPosition() {


        return playerPosition;
    }

    @Override
    public boolean hasPlayerFinished() {
        return labyrinth[playerPosition.getCol()][playerPosition.getRow()] == CellType.END;
    }

    @Override
    public List<Direction> possibleMoves() {
        ArrayList<Direction> possibleDirections = new ArrayList<>();
        if (hasPlayerFinished()) {
            return null;
        } else {
            if (playerPosition.getRow() - 1 >= 0 &&
                    labyrinth[playerPosition.getCol()][playerPosition.getRow() - 1] != CellType.WALL) {
                possibleDirections.add(Direction.NORTH);
            }
            if (playerPosition.getRow() + 1 < labyrinth[0].length &&
                    labyrinth[playerPosition.getCol()][playerPosition.getRow() + 1] != CellType.WALL) {
                possibleDirections.add(Direction.SOUTH);
            }
            if (playerPosition.getCol() - 1 >= 0 &&
                    labyrinth[playerPosition.getCol() - 1][playerPosition.getRow()] != CellType.WALL) {
                possibleDirections.add(Direction.WEST);
            }
            if (playerPosition.getCol() + 1 < labyrinth.length &&
                    labyrinth[playerPosition.getCol() + 1][playerPosition.getRow()] != CellType.WALL) {
                possibleDirections.add(Direction.EAST);
            }
        }
        return possibleDirections;
    }

    @Override
    public void movePlayer(Direction direction) throws InvalidMoveException {
        if (direction == Direction.EAST) {
            if (playerPosition.getCol() + 1 < labyrinth.length && labyrinth[playerPosition.getCol() + 1][playerPosition.getRow()] != CellType.WALL) {
                playerPosition = new Coordinate(playerPosition.getCol() + 1, playerPosition.getRow());
            } else {
                throw new InvalidMoveException();
            }
        } else if (direction == Direction.WEST) {
            if (playerPosition.getCol() - 1 >= 0 && labyrinth[playerPosition.getCol() - 1][playerPosition.getRow()] != CellType.WALL) {
                playerPosition = new Coordinate(playerPosition.getCol() - 1, playerPosition.getRow());
            } else {
                throw new InvalidMoveException();
            }
        } else if (direction == Direction.NORTH) {
            if (playerPosition.getRow() - 1 >= 0 && labyrinth[playerPosition.getCol()][playerPosition.getRow() - 1] != CellType.WALL) {
                playerPosition = new Coordinate(playerPosition.getCol(), playerPosition.getRow() - 1);
            } else {
                throw new InvalidMoveException();
            }
        } else {
            if (playerPosition.getRow() + 1 < labyrinth[0].length && labyrinth[playerPosition.getCol()][playerPosition.getRow() + 1] != CellType.WALL) {
                playerPosition = new Coordinate(playerPosition.getCol(), playerPosition.getRow() + 1);
            } else {
                throw new InvalidMoveException();
            }
        }
    }

}
