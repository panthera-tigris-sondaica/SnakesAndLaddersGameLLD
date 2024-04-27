package org.priya;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Board b =new Board(100);
        List<String> listOfPlayers=List.of("Priya","Ritvik");
        Game game=new Game(b,listOfPlayers);
        game.play();
    }
}