package com.crazi.dice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        this.playfield = new ArrayList<>();
        List<String> columnStrings = List.of(field.split("/"));
        for(String columnString : columnStrings){
            this.playfield.add(new Column(columnString, columnStrings.indexOf(columnString) + 1));
        }
    }

    public KnucklebonesPlayfield(int rows, int columns){
        this.playfield = new ArrayList<>();
        for(int count = 0; count < columns; count++) {
            this.playfield.add(new Column(rows, count));
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

    public void sortPlayfield(){
        for(Column column : playfield){
            column.sortColumn();
        }
    }

    public void removeFromColumn(int value, int column){
        playfield.stream().forEach((x) -> {
            if(x.columnNum == column){
                x.removeFromColumn(value);
            }
        });
    }

    public int getScore(){
        int total = 0;
        for(Column column : playfield){
            total += column.getScore();
        }
        return total;
    }

    public boolean isFull(){
        for(Column column : playfield){
            if(!column.isFull()){
                return false;
            }
        }
        return true;
    }

    public static class Column{

        int columnNum;
        List<Integer> rows;

        public Column(int rows, int column){
            this.rows = new ArrayList<>();
            for(int count = 0; count < rows; count++){
                this.rows.add(0);
            }
            this.columnNum = column;
        }

        public Column(String column, int columnNum){
            this.rows = new ArrayList<>();
            for(String row : List.of(column.split(","))){
                this.rows.add(Integer.valueOf(row));
            }
            this.columnNum = columnNum;
        }

        public void removeFromColumn(int value){
            rows = rows.stream().map((x) -> {
                if(x == value) {
                    return 0;
                }
                else{
                    return x;
                }
            }).collect(Collectors.toList());
            sortColumn();
        }

        public void sortColumn(){
            List<Integer> newColumn = new ArrayList<>();
            Map<Boolean, List<Integer>> outcome = rows.stream().collect(Collectors.partitioningBy((x) -> x != 0));
            newColumn.addAll(outcome.get(true));
            newColumn.addAll(outcome.get(false));
            rows = newColumn;
        }

        public int getScore() {
            int total = 0;
            for(int row : rows){
                total += (int) (rows.stream().filter((x) -> x == row).count() * row);
            }
            return total;
        }

        public boolean isFull(){
            return rows.stream().noneMatch((x) -> x == 0);
        }
    }
}
