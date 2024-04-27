package org.priya;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Snake implements Behaviour{

    private int head;
    private int tail;

    @Override
    public int getExpectedPosition() {
        return tail;
    }
}
