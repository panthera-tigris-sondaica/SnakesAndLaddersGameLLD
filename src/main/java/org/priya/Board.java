package org.priya;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class Board {
    private int totalCells;
    private Map<String,Integer> playerPosition;
    private Map<Integer,Behaviour> cellBehaviour;
    private Dice d;
    Board(int totalCells){
        this.totalCells=totalCells;
        playerPosition=new HashMap<>();
        d=new Dice();
        initializeCellBehaviour();
    }

    private void initializeCellBehaviour() {
        cellBehaviour=new HashMap<>();
        Gson gson = new Gson();

        Snake[] snakes = gson.fromJson(new InputStreamReader(
                Snake.class.getClassLoader().getResourceAsStream("behavior/Snake.json")),Snake[].class);
        for(Snake s : snakes){
            cellBehaviour.put(s.getHead(), s);
        }

        Ladder[] ladders = gson.fromJson(new InputStreamReader(
                Snake.class.getClassLoader().getResourceAsStream("behavior/Ladder.json")),Ladder[].class);
        for(Ladder l : ladders){
            cellBehaviour.put(l.getBottom(), l);
        }
    }

    public boolean playWith(Player p){
        int dNum=d.roll();
        System.out.println("Dice Rolled "+dNum);
        int currentPosition = playerPosition.get(p.getName());
        int expectedPosition=currentPosition+dNum;
        if(expectedPosition>totalCells){
            System.out.println("Player cant move to "+expectedPosition);
            if(dNum==6){
                System.out.println("Rolling Dice Again");
                return playWith(p);
            }
            else return false;
        }else{
            Set<Integer> visited = new HashSet<>();
            visited.add(expectedPosition);
            boolean behaviourFlag=false;
            while(cellBehaviour.containsKey(expectedPosition)){
                behaviourFlag=true;
                Behaviour b=cellBehaviour.get(expectedPosition);
                System.out.println("Found "+b.getClass().getSimpleName()+" at "+expectedPosition);
                expectedPosition=b.getExpectedPosition();
                System.out.println("Moving Player to "+expectedPosition);
                if(visited.contains(expectedPosition)){
                    System.out.println("Loop found at "+expectedPosition);
                    break;
                }else{
                    visited.add(expectedPosition);
                }
            }

            if(!behaviourFlag) System.out.println("Moving Player to "+expectedPosition);
            playerPosition.put(p.getName(),expectedPosition);
            if (expectedPosition==totalCells){
                System.out.println("Player Won: "+p.getName());
                return true;
            }
            else{
                if(dNum==6){
                    System.out.println("Rolling Dice Again");
                    return playWith(p);
                }
                else return false;
            }

        }
    }

    public void addPlayer(Player player){
        playerPosition.put(player.getName(),0);
    }


}
