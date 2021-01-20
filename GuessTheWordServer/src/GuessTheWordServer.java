import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


/**
 * Protokol za komunikaciju:
 */

/**
 **  Client -> Server           Server -> Client
 *  ----------------           ----------------
 *  GUESS <text>               WELCOME <char>  (char in {1, 2})
 *  QUIT                       GOOD_GUESS
 *                             OTHER_PLAYER_GUESSED <text>
 *                             VICTORY
 *                             DEFEAT
 *                             TIE
 *                             MESSAGE <text>
 * @author Demir
 */
public class GuessTheWordServer {
    
    public static void main(String[] args) throws Exception {
        ServerSocket listener = new ServerSocket(8901);
        System.out.println("Guess The Word is Running");
        try {
            while (true) {
                Game game = new Game();
                Game.Player player1 = game.new Player(listener.accept(), '1');
                Game.Player player2 = game.new Player(listener.accept(), '2');
                player1.setOpponent(player2);
                player2.setOpponent(player1);
                game.currentPlayer = player1;
                player1.start();
                player2.start();
            }
        } finally {
            listener.close();
        }
    }
    
}

/**
 * GAME for two players! 4 Slova , tri reci .Reci daju bodove kad se pogode. Moguce kombinacije:
 * {oksa - slova , reč 1 - kosa , reč 2 - sok, reč 3 - sokak}
 */
class Game {
    protected String[] set1 = new String[]{"oksa","kosa","sok","sokak"};
    protected String[] set2 = new String[]{};
    protected String[] set3 = new String[]{};
    protected String[] set4 = new String[]{};
    private int points1=0;
    private int points2=0;
    Random rand = new Random();
    int izbor = rand.nextInt(4)+1; //starts at 0, so add 1 za random redosled
    protected String[] set = set1; //za test
    protected String[] reci = new String[3];
    /**
     * izmenice set1 u sledeci. kad zavrse igru
     */
    Player currentPlayer;
    
    public void setpoints1(int i){
        this.points1 += i;
    }
    
    public void setpoints2(int i){
        this.points2 += i;
    }
    
    public boolean hasWinner(String set) {
        boolean some = false;
        switch(set){
            case "set1":{ if(this.points1 >= 7 || this.points2 >= 7) some= true;}
            break;
            case "set2":{ if(points1 > points2 || points2 > points1) some= true;}
            break;
            case "set3":{ if(points1 > points2 || points2 > points1) some= true;}
            break;
            case "set4":{ if(points1 >= 7 || points2 >= 7) some= true;}
            break;
        }
        return some;
                }
    
    public boolean allWordsGuessed() {
        for (int i = 0; i < 3; i++) {
            if (reci[i] == null) {
                return false;
            }
        }
        return true;
    }
    
    public void addToReci(String pokusaj) {
        List<String> arrlist 
            = new ArrayList<String>( 
                Arrays.asList(reci)); 
        arrlist.add(pokusaj); 
        // Convert the Arraylist to array 
        reci = arrlist.toArray(reci);
    }
    
     public boolean goodGuess(String pokusaj, Player player) {
        if (Arrays.asList(set).contains(pokusaj) == true &&
                pokusaj.matches(set[0].toString())==false  )
            {
            addToReci(pokusaj);
            //System.out.println(reci[0].toString() + reci[1].toString() + reci[2].toString() );
            
            return true;
        }
       
        return false;
    }
     
      public synchronized boolean legalMove(String pokusaj, Player player) {
        if (player == currentPlayer 
                && Arrays.asList(reci).contains(pokusaj) == false)
                 {
           // System.out.println(set[0].toString());
          //  System.out.println(reci.toString());
            
            return true;
        }
        return false;
    }
    
    //igrac
    
    class Player extends Thread {
        char mark;
        Player opponent;
        Socket socket;
        BufferedReader input;
        
        PrintWriter output;
        String setime;
        
        /**
         * Constructs a handler thread for a given socket and mark
         * initializes the stream fields, displays the first two
         * welcoming messages.
         */
        public Player(Socket socket, char mark) {
            this.socket = socket;
            this.mark = mark;
            this.setime = "set1";
            try {
                input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(socket.getOutputStream(), true);
                output.println("WELCOME" + mark + "_" + set1[0].toString() + "_" +set1[1].length()+set1[2].length()+set1[3].length() );
               // output.println("MESSAGE " + "WELCOME" + mark + "_" + set1[0].toString() + "_" +set1[1].length()+set1[2].length()+set1[3].length() );
                output.println("MESSAGE Waiting for opponent to connect");
            } catch (IOException e) {
                System.out.println("Player died: " + e);
            }
        }
        

        /**
         * Accepts notification of who the opponent is.
         */
        public void setOpponent(Player opponent) {
            this.opponent = opponent;
        }

        /**
         * Handles the otherPlayerGuessed message.
         */
        public void otherPlayerGuessed(String rec,int i,char mark) {
            output.println("OPPONENT_GUESSED " +mark +i + rec );
            
            
            output.println(hasWinner("set1") ? "DEFEAT"
                                         : allWordsGuessed() ? "TIE"
                                         : "");
        }

        /**
         * The run method of this thread.
         */
        public void run() {
            try {
                // The thread is only started after everyone connects.
                output.println("MESSAGE All players connected");

                // Tell the first player that it is her turn.
                if (mark == '1') {
                    output.println("MESSAGE Your move");
                }

                // Repeatedly get commands from the client and process them.
                while (true) {
                    String command = input.readLine();
                    if (command.startsWith("GUESS")) {
                        String word = command.substring(5);
                        if (legalMove(word, this)) {
                            if(goodGuess(word,this)) {
                                System.out.println("Info: " + mark );
                            if(mark=='1') setpoints1(word.length());
                            if(mark=='2') setpoints2(word.length());
                            output.println("GOOD_GUESS"+mark+ word.length() );
                           
                            
                            output.println(hasWinner("set1") ? "VICTORY"
                                         : allWordsGuessed() ? "TIE"
                                         : "");
                            currentPlayer = currentPlayer.opponent;
                            currentPlayer.otherPlayerGuessed(word,1,mark);
                            }
                             else {
                             output.println("MESSAGE Not a good guess.");
                            currentPlayer = currentPlayer.opponent;
                            currentPlayer.otherPlayerGuessed(word,0,mark);}
                            
                        } else {
                            output.println("MESSAGE Not your turn or another mistake. Try again.");
                            //maybeh
                           
                        }
                    } else if (command.startsWith("QUIT")) {
                        return;
                    }
                }
            } catch (IOException e) {
                System.out.println("Player died: " + e);
            } finally {
                try {socket.close();} catch (IOException e) {}
            }
        }
    }
}
