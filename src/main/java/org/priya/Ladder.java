package org.priya;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ladder implements Behaviour{
    private int top;
    private int bottom;

    @Override
    public int getExpectedPosition() {
        return top;
    }
}
