package com.crazi.dice;

import org.junit.jupiter.api.Test;

import java.util.List;
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

    @Test public void columnRemoveAndSortTest(){
        String startString = "0,6,6/0,5,1/2,4,3";
        String sortedString = "6,6,0/5,1,0/2,4,3";
        String removedString1 = "6,6,0/5,1,0/2,3,0";
        String removedString2 = "0,0,0/5,1,0/2,3,0";
        KnucklebonesPlayfield playfield = new KnucklebonesPlayfield(startString);
        playfield.sortPlayfield();
        assertEquals(0, playfield.toString().compareTo(sortedString));
        playfield.removeFromColumn(4,3);
        assertEquals(0, playfield.toString().compareTo(removedString1));
        playfield.removeFromColumn(6,1);
        assertEquals(0, playfield.toString().compareTo(removedString2));
    }

    @Test public void columnScoreCalculationTest(){
        for(int val1 = 0; val1 < 7; val1++){
            for(int val2 = 0; val2 < 7; val2++){
                for(int val3 = 0; val3 < 7; val3++){
                    KnucklebonesPlayfield.Column col = new KnucklebonesPlayfield.Column(buildColumn(val1, val2, val3),1);
                    int total =  col.getScore();
                    int compareTotal = valuesToScore(List.of(val1,val2,val3));
                    assertEquals(compareTotal, total);
                }
            }
        }
    }

    private String buildColumn(int a, int b, int c){
        return a+","+b+","+c;
    }

    private int valuesToScore(List<Integer> input){
        int total = 0;
        for(int val : input){
            for(int val2 : input){
                total += val == val2 ? val : 0;
            }
        }
        return total;
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
