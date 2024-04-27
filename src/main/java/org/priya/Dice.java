package org.priya;

import java.security.SecureRandom;
import java.util.Random;

public class Dice {
    private final Random random;
    public Dice(){
        random=new SecureRandom();
    }

    public int roll(){
     return random.nextInt(1,7);
    }
}
