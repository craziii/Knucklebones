package com.crazi.dice;

/*
    Turn Info standard format
    playfield-roll-score
    First turn example:
        0,0,0/0,0,0/0,0,0-X-0
        Where X is the value of the first turn roll

    Game Info standard format
    playfield-roll-score-other_playfield-other_score
    First turn example:
        0,0,0/0,0,0/0,0,0-X-0-0,0,0/0,0,0/0,0,0-0
 */

public class Knucklebones {


    public class GameInfo{
        TurnInfo player1Info;
        TurnInfo player2Info;

        public String getGameInfo(int player){
            StringBuilder gameInfoString = new StringBuilder();
            switch(player){
                case 1:
                    gameInfoString.append(player1Info.getTurnInfo(false,true))
                            .append("-")
                            .append(player2Info.getTurnInfo(false,false));
                    break;
                case 2:
                    gameInfoString.append(player2Info.getTurnInfo(false,true))
                            .append("-")
                            .append(player1Info.getTurnInfo(false,false));
                    break;
                default:
                    gameInfoString.append(player1Info.getTurnInfo(false,true))
                            .append("-")
                            .append(player2Info.getTurnInfo(false,true));
            }
            return gameInfoString.toString();
        }
    }

    public static class TurnInfo{
        final KnucklebonesPlayfield playfield;
        final String playfieldString;
        final int currentRoll;
        final int currentScore;
        public TurnInfo(KnucklebonesPlayfield playfield, int currentRoll, int currentScore) {
            this.playfield = playfield;
            this.playfieldString = playfield.toString();
            this.currentRoll = currentRoll;
            this.currentScore = currentScore;
        }

        public String getTurnInfo(boolean verbose, boolean roll){
            StringBuilder output = new StringBuilder();
            if(verbose){
                output.append("Playfield:")
                        .append(playfield.toString())
                        .append("-");
                if(roll){
                    output.append("Value Rolled:")
                            .append(currentRoll)
                            .append("-");
                }
                output.append("Current Score:")
                        .append(playfield.getScore());
            }
            else {
                output.append(playfield.toString())
                        .append("-");
                if(roll){
                    output.append(currentRoll)
                            .append("-");
                }
                output.append(playfield.getScore());
            }
            return output.toString();
        }
    }
}
