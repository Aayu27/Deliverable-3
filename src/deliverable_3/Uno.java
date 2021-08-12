/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deliverable_3;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author aayu9
 */
public class Uno {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        String[] color = {"red", "blue", "green", "yellow"};
        ArrayList<Card> temp;
        int playernumber = 0;
        boolean win = true;
        boolean isbreak = true;
        ArrayList<Card> cardArrayList = new ArrayList<>();
        ArrayList<Boolean> whowin = new ArrayList<>();

        for (int i = 0; i < color.length; i++) {
            for (int j = 0; j <= 10; j++) {
                cardArrayList.add(new Card(color[i], j));
            }
        }

        GroupOfCards group = new GroupOfCards(40);
        group.setCards(cardArrayList);
        group.shuffle();

        System.out.println("Enter number of players:");

        int x = scanner.nextInt();
        String s;
        int n;
        int starting = 0;
        int ending = 6;
        ArrayList<Card> dropcards;

        Card deckcard = group.getCards().get(0);
        group.getCards().remove(0);
        //array player
        ArrayList<Player> player = new ArrayList<>();
        for (int j = 0; j < x; j++) {
            dropcards = new ArrayList<>();
            whowin.add(false);
            System.out.println("\nEnter " + Integer.parseInt(String.valueOf(j + 1)) + " player name:");
            s = scanner.next();
            for (int o = starting; o <= ending; o++) {
                dropcards.add(group.getCards().get(o));
                group.getCards().remove(o);
            }

            starting += 7;
            ending += 7;
            player.add(new Player(s, dropcards));


        }
        Game game = new Game("UNO");

        game.setPlayers(player);

        int p = 0;

        String playerwins = null;
        for (int i = 0; i < player.size(); i++) {
            System.out.println("\n"+game.getPlayers().get(i).getName() + " got :");
            for (int j = 0; j < player.get(i).getCards().size(); j++) {

                System.out.println(game.getPlayers().get(i).getCards().get(j).number + "\t" + player.get(i).getCards().get(j).color);
            }
        }

        System.out.println("\nWant to start the game? the press 'yes' or 'no'");
        String startgame = scanner.next();
        
        System.out.println("\n");
        boolean choosecolor; 
        if (startgame.toLowerCase().equals("yes")) {
            int turn = 0;
            while (win) {
                turn = 0;
                isbreak=true;
                choosecolor = false;
                while (isbreak) {
                    for (int j = 0; j < player.get(playernumber).getCards().size(); j++) {

                        System.out.println(j + ". " + game.getPlayers().get(playernumber).getCards().get(j).number + "\t" + player.get(playernumber).getCards().get(j).color);
                    }

                    System.out.println(player.get(playernumber).getCards().size() + ". " + "Skip");
                    System.out.println("\n");
                    System.out.println("Deck Card - " + deckcard.number + "\t" + deckcard.color);                    
                    System.out.println("\n"+game.getPlayers().get(playernumber).getName()+" please choose your card from above. You have "+player.get(playernumber).getPoints()+" points");
                    int input = scanner.nextInt();
                    System.out.println("\n");
                    if (input == player.get(playernumber).getCards().size() && turn > 0) {
                        isbreak = false;
                    } else if (turn == 0 && input == player.get(playernumber).getCards().size()) {
                        game.getPlayers().get(playernumber).getCards().add(group.getCards().get(0));
                        group.getCards().remove(0);
                        player.get(playernumber).setPoints(player.get(playernumber).getPoints()-1);
                    }
                    else if(game.getPlayers().get(playernumber).getCards().get(input).number == deckcard.number){
                        deckcard = new Card(game.getPlayers().get(playernumber).getCards().get(input).color, game.getPlayers().get(playernumber).getCards().get(input).number);
                        game.getPlayers().get(playernumber).getCards().remove(input);
                        choosecolor=true;
                        player.get(playernumber).setPoints(player.get(playernumber).getPoints()+5);
                    }
                    else if (game.getPlayers().get(playernumber).getCards().get(input).color == deckcard.color&& choosecolor==false) {
                        deckcard = new Card(game.getPlayers().get(playernumber).getCards().get(input).color, game.getPlayers().get(playernumber).getCards().get(input).number);
                        game.getPlayers().get(playernumber).getCards().remove(input);
                        isbreak = false;
                        player.get(playernumber).setPoints(player.get(playernumber).getPoints()+5);

                    } else {
                        System.out.println("Invalid move play again\n");
                    }
                    if (game.getPlayers().get(playernumber).getCards().size()==0){
                        isbreak=false;
                    }
                    turn++;
                }
                if (game.getPlayers().get(playernumber).getCards().size() == 0) {
                    playerwins = game.getPlayers().get(playernumber).getName();
                    win=false;
                }
                if (playernumber == game.getPlayers().size() - 1) {
                    playernumber = 0;
                } else {
                    playernumber++;
                }
            }


            System.out.println("Congratulations!!!!!! The WINNER is " + playerwins );
        } else {
            return;
        }
    }
    
}
