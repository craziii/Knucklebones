package com.crazi.dice;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KnucklebonesTest {
    @Test public void defaultPlayfieldStringTest(){
        String startString = "0,0,0/0,0,0/0,0,0";
        KnucklebonesPlayfield playfield = new KnucklebonesPlayfield(startString);
        assertTrue(checkPlayfieldAllZero(playfield));
        assertEquals(0, playfield.toString().compareTo(startString));
    }

    @Test public void defaultPlayfieldTest(){
        String startString = "0,0,0/0,0,0/0,0,0";
        KnucklebonesPlayfield playfield = new KnucklebonesPlayfield(3,3);
        assertTrue(checkPlayfieldAllZero(playfield));
        assertEquals(0, playfield.toString().compareTo(startString));
    }

    private boolean checkPlayfieldAllZero(KnucklebonesPlayfield playfield){
        for(KnucklebonesPlayfield.Column column : playfield.playfield){
            for(int row : column.rows){
                if(row != 0){
                    return false;
                }
            }
        }
        return true;
    }
}
