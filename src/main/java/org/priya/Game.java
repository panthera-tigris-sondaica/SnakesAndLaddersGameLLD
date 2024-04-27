package org.priya;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
@Getter
@Setter
public class Game{
    boolean isGameCompleted;
    public  Game(Board board, List<String> listOfPlayers) {
        this.board = board;
        this.players = new LinkedList<>();
        for (String name : listOfPlayers) {
            Player player = new Player(name);
            board.addPlayer(player);
            players.offer(player);
        }
    }

    private Board board;
    private Queue<Player> players;

    public void play(){
        System.out.println("Lets Begin the Game");
        while(!isGameCompleted){
            rollDice();
        }
        System.out.println("Game Over");
    }

    private void rollDice() {
        Player p=players.poll();
        System.out.println("\nPlayer "+p.getName()+" is playing");
        isGameCompleted=board.playWith(p);
        players.offer(p);
    }


}
