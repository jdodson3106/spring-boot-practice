package com.justindodson.console;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Getter
@Component
public class NumberGeneratorImpl implements NumberGenerator {

    // == fields ==
    @Getter(AccessLevel.NONE)
    private final Random random = new Random();

    private final int maxNumber;
    private final int minNumber;

    // == Constructors ==
    @Autowired
    public NumberGeneratorImpl(@MaxNumber int maxNumber,@MinNumber int minNumber) {
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
    }

    // == public methods ==
    @Override
    public int next() {
        // the below formula generates a min-max range to generate a number in
        // the (max - min) + 1 sets the exclusive max value to set the list to
        // (10 - 5) + 1 = 6 means the list will be 0-5 since the max is exclusive
        // then you add the min value to the entire range so that [0,..., 5] + 6
        // gives you [5...10]
        return random.nextInt((maxNumber - minNumber) + 1) + minNumber;
    }
}