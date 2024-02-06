package com.crazi.dice;

import java.util.ArrayList;
import java.util.List;

/*
    Playfield input format:
    comma separated for each row of a column
    forward slash separated for each column of the playfield
    Int values only
    0 is an empty slot
    example:
    - Empty playfield:
        "0,0,0/0,0,0/0,0,0"
    - Example playfield:
        "4,6,0/1,1,3/5,0,0"
 */

public class KnucklebonesPlayfield {

    List<Column> playfield;

    //TODO: Add in reading from csv string
    //TODO: Add in writing to csv string

    KnucklebonesPlayfield(String field){

    }

    KnucklebonesPlayfield(int rows, int columns){
        this.playfield = new ArrayList<>();
        for(int count = 0; count < columns; count++) {
            this.playfield.add(new Column(rows));
        }
    }

    public class Column{
        List<Integer> rows;

        Column(int rows){
            this.rows = new ArrayList<>();
            for(int count = 0; count < rows; count++){
                this.rows.add(0);
            }
        }

        //TODO: Add in reading from csv string

    }
}
