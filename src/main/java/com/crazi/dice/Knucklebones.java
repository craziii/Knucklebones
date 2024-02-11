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

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Knucklebones {

    Logger logger = Logger.getLogger(this.getClass().getName());
    Random rng;
    KnucklebonesPlayer player1;
    KnucklebonesPlayer player2;
    KnucklebonesPlayer currentPlayer;

    public Knucklebones() {
    }

    public void gameHandler(){
        //TODO: Work out resetting a game after completion
        //TODO: Be able to playback games from a file
        rng = new Random();
        setupGame();
        if(!checkForGameEnd()){
            gameLoop();
        }
    }

    public void setupGame(){
        player1 = new KnucklebonesPlayer(1);
        player2 = new KnucklebonesPlayer(2);
        if(rng.nextBoolean()){
            currentPlayer = player1;
            player1.setCurrentPlayer(true);
        }
        else{
            currentPlayer = player2;
            player2.setCurrentPlayer(true);
        }
    }

    public void gameLoop(){
        //TODO: work out full game loop
        takeTurn(currentPlayer);
        if(checkForGameEnd()){
            endGame();
        }
        selectPlayer();
    }

    private void selectPlayer(){
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public void takeTurn(KnucklebonesPlayer currentPlayer){
        //TODO: implement taking turns
    }

    private boolean checkForGameEnd(){
        return player1.isPlayfieldFull() || player2.isPlayfieldFull();
    }

    private void endGame(){
        //TODO: implement endgame logic
        //TODO: Log game information to file
            //TODO: Work out file naming convention for recorded games
        logger.log(Level.INFO,"");
    }

    private KnucklebonesPlayer findWinner(){
        return new GameInfo(player1,player2).returnWinner() == 1 ? player1 : player2;
    }

    public static class GameInfo{
        TurnInfo player1Info;
        TurnInfo player2Info;

        public GameInfo(KnucklebonesPlayer player1, KnucklebonesPlayer player2){
            this.player1Info = player1.getTurnInfoObject();
            this.player2Info = player2.getTurnInfoObject();
        }

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

        private int returnWinner(){
            return player1Info.isThisHigherScore(player2Info) ? player1Info.playerNum : player2Info.playerNum;
        }
    }

    public static class TurnInfo{
        final int playerNum;
        final KnucklebonesPlayfield playfield;
        final String playfieldString;
        final int currentRoll;
        final int currentScore;

        public TurnInfo(int playerNum, KnucklebonesPlayer player, int currentRoll, int currentScore) {
            this.playerNum = playerNum;
            this.playfield = player.getField();
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

        public boolean isThisHigherScore(TurnInfo other){
            return this.currentScore > other.currentScore;
        }
    }
}
