package com.crazi.dice;

public class KnucklebonesPlayer {

    private Die die;

    private KnucklebonesPlayfield field;

    public KnucklebonesPlayer(){
        this.die = CommonDie.D6();
        this.field = new KnucklebonesPlayfield("0,0,0/0,0,0/0,0,0");
    }

    public Die getDie() {
        return die;
    }

    public void setDie(Die die) {
        this.die = die;
    }

    public KnucklebonesPlayfield getField() {
        return field;
    }

    public String getFieldString(){
        return field.toString();
    }

    public void setField(KnucklebonesPlayfield field) {
        this.field = field;
    }
}
