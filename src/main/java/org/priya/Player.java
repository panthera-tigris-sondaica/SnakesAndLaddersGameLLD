package org.priya;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Player {
    public Player(String name){
        this.name=name;
    }
    private String name;
}
