package com.crazi.dice;

public class KnucklebonesPlayer {

    private boolean isCurrentPlayer;
    private Die die;
    private KnucklebonesPlayfield field;

    public KnucklebonesPlayer(){
        this.die = CommonDie.D6();
        this.field = new KnucklebonesPlayfield("0,0,0/0,0,0/0,0,0");
        this.isCurrentPlayer = false;
    }

    public KnucklebonesPlayer(Die die, KnucklebonesPlayfield field, boolean isCurrentPlayer){
        setDie(die);
        setField(field);
        setCurrentPlayer(isCurrentPlayer);
    }

    public Knucklebones.TurnInfo getTurnInfoObject(){
        return new Knucklebones.TurnInfo(getField(), getDie().getCurrentValue(), getField().getScore());
    }

    public boolean isCurrentPlayer() {
        return isCurrentPlayer;
    }

    public void setCurrentPlayer(boolean currentPlayer) {
        isCurrentPlayer = currentPlayer;
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

    public void setField(KnucklebonesPlayfield field) {
        this.field = field;
    }
}
