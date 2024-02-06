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

    public KnucklebonesPlayfield(String field){
        //TODO: Add in reading from csv string
        this.playfield = new ArrayList<>();
        List<String> columnStrings = List.of(field.split("/"));
        for(String columnString : columnStrings){
            this.playfield.add(new Column(columnString));
        }
    }

    public KnucklebonesPlayfield(int rows, int columns){
        this.playfield = new ArrayList<>();
        for(int count = 0; count < columns; count++) {
            this.playfield.add(new Column(rows));
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for(Column column : playfield){
            for(int row : column.rows){
                output.append(row);
                output.append(",");
            }
            output.deleteCharAt(output.length()-1);
            output.append("/");
        }
        output.deleteCharAt(output.length()-1);
        return output.toString();
    }

    public class Column{
        List<Integer> rows;

        public Column(int rows){
            this.rows = new ArrayList<>();
            for(int count = 0; count < rows; count++){
                this.rows.add(0);
            }
        }

        public Column(String column){
            this.rows = new ArrayList<>();
            for(String row : List.of(column.split(","))){
                this.rows.add(Integer.valueOf(row));
            }
        }

        //TODO: Add in reading from csv string

    }
}
