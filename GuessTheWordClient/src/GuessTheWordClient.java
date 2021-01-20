import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.toString;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.String;
import java.util.Arrays;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Demir
 */
public class GuessTheWordClient extends javax.swing.JFrame {

     private JFrame frame = new JFrame("Guess The Word");
     private Socket socket;
     private BufferedReader in;
     private PrintWriter out;
     private String temp;
     private static int PORT = 8901;
     private JLabel messageLabel = new JLabel("");
    /**
     * Creates new form GuessTheWordClient
     */
    public GuessTheWordClient(String serverAddress) throws Exception {
        
        //initComponents();
        // Setup networking
        socket = new Socket(serverAddress, PORT);
        in = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        
        //<editor-fold defaultstate="collapsed" desc=" Kod za GUI, jer form ne moze za Play() ">
         jPanel1 = new javax.swing.JPanel();
        pokusaj = new javax.swing.JTextField();
        points1 = new javax.swing.JLabel();
        points2 = new javax.swing.JLabel();
        stone3 = new javax.swing.JLabel();
        word1 = new javax.swing.JLabel();
        stone4 = new javax.swing.JLabel();
        stone2 = new javax.swing.JLabel();
        stone = new javax.swing.JLabel();
        word2 = new javax.swing.JLabel();
        word3 = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        
        frame.setTitle("GuessTheWord");
        frame.setAutoRequestFocus(false);
        frame.setLocationByPlatform(true);
        setMaximumSize(new java.awt.Dimension(800, 460));
        setMinimumSize(new java.awt.Dimension(800, 460));
        setPreferredSize(new java.awt.Dimension(800, 460));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 460));

        messageLabel.setOpaque(true);
        messageLabel.setBackground(Color.BLACK);
        messageLabel.setForeground(Color.WHITE);
        frame.getContentPane().add(messageLabel, "South");
        
        jPanel1.setMaximumSize(new java.awt.Dimension(800, 430));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 430));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pokusaj.setBackground(new java.awt.Color(51, 51, 51));
        pokusaj.setFont(new java.awt.Font("Arial Nova", 0, 18)); // NOI18N
        pokusaj.setForeground(new java.awt.Color(255, 255, 255));
        pokusaj.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pokusaj.setText("****");
        pokusaj.setToolTipText("press enter to send");
        pokusaj.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        pokusaj.setCaretColor(new java.awt.Color(255, 255, 255));
        pokusaj.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pokusaj.setSelectionColor(new java.awt.Color(0, 153, 0));
        pokusaj.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pokusajMouseClicked(evt);
            }
        });
        pokusaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pokusajActionPerformed(evt);
            }
        });
        pokusaj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pokusajKeyPressed(evt);
            }
        });
        

        jPanel1.add(pokusaj, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 350, 140, 50));

        points1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        points1.setForeground(new java.awt.Color(255, 255, 255));
        points1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        points1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/1player.png"))); // NOI18N
        points1.setText("2");
        points1.setToolTipText("");
        points1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(points1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 110, 100));

        points2.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        points2.setForeground(new java.awt.Color(255, 255, 255));
        points2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        points2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/2player.png"))); // NOI18N
        points2.setText("6");
        points2.setToolTipText("");
        points2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(points2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 0, 100, 100));

        stone3.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        stone3.setForeground(new java.awt.Color(255, 255, 255));
        stone3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stone3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drock.png"))); // NOI18N
        stone3.setText("R");
        stone3.setToolTipText("");
        stone3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(stone3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 320, 100, 90));

        word1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        word1.setForeground(new java.awt.Color(153, 0, 51));
        word1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        word1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tag2.png"))); // NOI18N
        word1.setText("****");
        word1.setToolTipText("");
        word1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(word1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, 200, 110));

        stone4.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        stone4.setForeground(new java.awt.Color(255, 255, 255));
        stone4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stone4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drock.png"))); // NOI18N
        stone4.setText("T");
        stone4.setToolTipText("");
        stone4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(stone4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 300, 100, 90));

        stone2.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        stone2.setForeground(new java.awt.Color(255, 255, 255));
        stone2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stone2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srock.png"))); // NOI18N
        stone2.setText("V");
        stone2.setToolTipText("");
        stone2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(stone2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, 100, 90));

        stone.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        stone.setForeground(new java.awt.Color(255, 255, 255));
        stone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srock.png"))); // NOI18N
        stone.setText("Q");
        stone.setToolTipText("");
        stone.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(stone, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, 100, 90));

        word2.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        word2.setForeground(new java.awt.Color(153, 0, 51));
        word2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tag2.png"))); // NOI18N
        word2.setText("*****");
        word2.setToolTipText("");
        word2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(word2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, 200, 110));

        word3.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        word3.setForeground(new java.awt.Color(153, 0, 51));
        word3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tag2.png"))); // NOI18N
        word3.setText("VRT");
        word3.setToolTipText("");
        word3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(word3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 200, 110));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/back.jpg"))); // NOI18N
        jPanel1.add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 430));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        
        frame.getContentPane().add(jPanel1,"Center");
        
        //</editor-fold>
        
        stone.setText("");
        stone.setToolTipText(null);
        stone2.setText("");
        stone2.setToolTipText(null);
        stone3.setText("");
        stone3.setToolTipText(null);
        stone4.setText("");
        stone4.setToolTipText(null);
        
        word1.setText("");
        word1.setToolTipText(null);
        word2.setText("");
        word2.setToolTipText(null);
        word3.setText("");
        word3.setToolTipText(null);
        points1.setText("0");
        points1.setToolTipText(null);
        points2.setText("0");
        points2.setToolTipText(null);
        
       
            
        
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pokusaj = new javax.swing.JTextField();
        points1 = new javax.swing.JLabel();
        points2 = new javax.swing.JLabel();
        stone3 = new javax.swing.JLabel();
        word1 = new javax.swing.JLabel();
        stone4 = new javax.swing.JLabel();
        stone2 = new javax.swing.JLabel();
        stone = new javax.swing.JLabel();
        word2 = new javax.swing.JLabel();
        word3 = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GuessTheWord");
        setAutoRequestFocus(false);
        setLocationByPlatform(true);
        setMaximumSize(new java.awt.Dimension(800, 460));
        setMinimumSize(new java.awt.Dimension(800, 460));
        setPreferredSize(new java.awt.Dimension(800, 460));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 460));

        jPanel1.setMaximumSize(new java.awt.Dimension(800, 430));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 430));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pokusaj.setBackground(new java.awt.Color(51, 51, 51));
        pokusaj.setFont(new java.awt.Font("Arial Nova", 0, 18)); // NOI18N
        pokusaj.setForeground(new java.awt.Color(255, 255, 255));
        pokusaj.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pokusaj.setText("****");
        pokusaj.setToolTipText("press enter to send");
        pokusaj.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        pokusaj.setCaretColor(new java.awt.Color(255, 255, 255));
        pokusaj.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pokusaj.setSelectionColor(new java.awt.Color(0, 153, 0));
        pokusaj.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pokusajMouseClicked(evt);
            }
        });
        pokusaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pokusajActionPerformed(evt);
            }
        });
        pokusaj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pokusajKeyPressed(evt);
            }
        });
        jPanel1.add(pokusaj, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 350, 140, 50));

        points1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        points1.setForeground(new java.awt.Color(255, 255, 255));
        points1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        points1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/1player.png"))); // NOI18N
        points1.setText("2");
        points1.setToolTipText("");
        points1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(points1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 110, 100));

        points2.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        points2.setForeground(new java.awt.Color(255, 255, 255));
        points2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        points2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/2player.png"))); // NOI18N
        points2.setText("6");
        points2.setToolTipText("");
        points2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(points2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 0, 100, 100));

        stone3.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        stone3.setForeground(new java.awt.Color(255, 255, 255));
        stone3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stone3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drock.png"))); // NOI18N
        stone3.setText("R");
        stone3.setToolTipText("");
        stone3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(stone3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 320, 100, 90));

        word1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        word1.setForeground(new java.awt.Color(153, 0, 51));
        word1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        word1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tag2.png"))); // NOI18N
        word1.setText("****");
        word1.setToolTipText("");
        word1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(word1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, 200, 110));

        stone4.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        stone4.setForeground(new java.awt.Color(255, 255, 255));
        stone4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stone4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drock.png"))); // NOI18N
        stone4.setText("T");
        stone4.setToolTipText("");
        stone4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(stone4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 300, 100, 90));

        stone2.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        stone2.setForeground(new java.awt.Color(255, 255, 255));
        stone2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stone2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srock.png"))); // NOI18N
        stone2.setText("V");
        stone2.setToolTipText("");
        stone2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(stone2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, 100, 90));

        stone.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        stone.setForeground(new java.awt.Color(255, 255, 255));
        stone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srock.png"))); // NOI18N
        stone.setText("Q");
        stone.setToolTipText("");
        stone.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(stone, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, 100, 90));

        word2.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        word2.setForeground(new java.awt.Color(153, 0, 51));
        word2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tag2.png"))); // NOI18N
        word2.setText("*****");
        word2.setToolTipText("");
        word2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(word2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, 200, 110));

        word3.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        word3.setForeground(new java.awt.Color(153, 0, 51));
        word3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tag2.png"))); // NOI18N
        word3.setText("VRT");
        word3.setToolTipText("");
        word3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(word3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 200, 110));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/back.jpg"))); // NOI18N
        jPanel1.add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 430));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pokusajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pokusajActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pokusajActionPerformed

    private void pokusajMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pokusajMouseClicked
        pokusaj.setText("");
    }//GEN-LAST:event_pokusajMouseClicked


    private void pokusajKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pokusajKeyPressed
        int key = evt.getKeyCode();
           if (key == KeyEvent.VK_ENTER) {
              //Toolkit.getDefaultToolkit().beep(); 
              this.temp = pokusaj.getText();
              out.println("GUESS" + pokusaj.getText().toString());
              pokusaj.setText("");
              
           }
    }//GEN-LAST:event_pokusajKeyPressed
    
    
    
    public String build (int i) {
    String toAdd = "*";
    StringBuilder s = new StringBuilder();
    for(int count = 0; count < i; count++) {
     s.append(toAdd);
  } 
    String output = s.toString();
    return output;}
    
    
    
    public void play() throws Exception {
        String response;
        try {
            
            response = in.readLine();
           //WELCOME1_QWRS_574 --1 igrac QWRS slova za kamenja 5,7,4 duzine reci
            if (response.startsWith("WELCOME")) {
                char mark = response.charAt(7);
                char slovo1 = response.charAt(9);
                char slovo2 = response.charAt(10);
                char slovo3= response.charAt(11);
                char slovo4 = response.charAt(12);
                
                char broj1 = response.charAt(14);
                int i1 = Integer.parseInt(String.valueOf(broj1));
                String br1 = build(i1);
                
                char broj2 = response.charAt(15);
                int i2 = Integer.parseInt(String.valueOf(broj2));
                String br2 = build(i2);
                
                char broj3 = response.charAt(16);
                int i3 = Integer.parseInt(String.valueOf(broj3));
                String br3 = build(i3);
                //icon = new ImageIcon(mark == 'X' ? "x.png" : "o.png");
                //ol,yt opponentIcon  = new ImageIcon(mark == 'X' ? "o.png" : "x.png");
                stone.setText(Character.toString(slovo1));
                stone2.setText(Character.toString(slovo2));
                stone3.setText(Character.toString(slovo3));
                stone4.setText(Character.toString(slovo4));
                
                word1.setText(br1);
                word2.setText(br2);
                word3.setText(br3);
                
                frame.setTitle("Guess The Word - Player " + mark);}
                while (true) {
                response = in.readLine();
                // GOOD_GUESS - pogodio rec,dodaj bodove GOOD_GUESS1 8
                if (response.startsWith("GOOD_GUESS")) {
                    
                    char poin = response.charAt(11);
                    int point = poin - '0'; //jedino konvertuje ovako
                    System.out.println(word1.getText().length());
                    System.out.println(this.temp.toString());
                    System.out.println(point);
                    if(word1.getText().length()==point){
                        word1.setText(this.temp.toString());}
                    if(word2.getText().length()==point){
                        word2.setText(this.temp.toString());}
                    if(word3.getText().length()==point){
                        word3.setText(this.temp.toString());}
                    
                    char mark = response.charAt(10);
                    messageLabel.setText("Good guess, please wait your turn." );
                    
                    if(mark == '1')
                    {
                        int points;
                        points = Integer.valueOf(points1.getText());
                        System.out.println(points);
                        points = points +point;
                        points1.setText(Integer.toString(points));
                        
                    }
                    if(mark == '2')
                    {
                        int points;
                        points = Integer.valueOf(points2.getText());
                        points = points +point;
                        points2.setText(Integer.toString(points));
                        
                    }
                    
                } else if (response.startsWith("OPPONENT_GUESSED")) {
                    //ovde ce ic update bodova tudjih i reci
                    char mark = response.charAt(17);
                    
                    char pogodio = response.charAt(18);
                    String loc = response.substring(19);
                   // System.out.println(mark);
                    
                   // System.out.println(pogodio);
                   // System.out.println(loc);
                    if(pogodio == '1'){
                        
                        int point = loc.length();
                       // System.out.println(point);
                    if(word1.getText().length()==point){
                        word1.setText(loc);}
                    if(word2.getText().length()==point){
                        word2.setText(loc);}
                    if(word3.getText().length()==point){
                        word3.setText(loc);}
                     if(mark == '1')
                    {
                        int points;
                        points = Integer.valueOf(points1.getText());
                        System.out.println(points);
                        points = points +point;
                        points1.setText(Integer.toString(points));
                        
                    }
                    if(mark == '2')
                    {
                        int points;
                        points = Integer.valueOf(points2.getText());
                        points = points +point;
                        points2.setText(Integer.toString(points));
                        
                    }
                    }
                    
                    messageLabel.setText("Opponent guessed, your turn: " + loc );
                } else if (response.startsWith("VICTORY")) {
                    messageLabel.setText("You win");
                    break;
                } else if (response.startsWith("DEFEAT")) {
                    messageLabel.setText("You lose");
                    break;
                } else if (response.startsWith("TIE")) {
                    messageLabel.setText("You tied");
                    break;
                } else if (response.startsWith("MESSAGE")) {
                    messageLabel.setText(response.substring(8));
                }
            }
            out.println("QUIT");
        }
        finally {
            socket.close();
        }
    }
    
      private boolean wantsToPlayAgain() {
        int response = JOptionPane.showConfirmDialog(frame,
            "Want to play again?",
            "Guess Some More WORDZzz",
            JOptionPane.YES_NO_OPTION);
        frame.dispose();
        return response == JOptionPane.YES_OPTION;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GuessTheWordClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GuessTheWordClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GuessTheWordClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GuessTheWordClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //Display form and plaay
         
                    while(true){
                    String serverAddress = (args.length == 0) ? "localhost" : args[1];
                    GuessTheWordClient client = new GuessTheWordClient(serverAddress);
                    client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    client.frame.setSize(800, 460);
                    client.frame.setVisible(true);
                    client.frame.setResizable(false);
                    client.play(); 
                    if (!client.wantsToPlayAgain()) {
                     break;
                    }   
                    }
                    
          
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel points1;
    private javax.swing.JLabel points2;
    public javax.swing.JTextField pokusaj;
    private javax.swing.JLabel stone;
    private javax.swing.JLabel stone2;
    private javax.swing.JLabel stone3;
    private javax.swing.JLabel stone4;
    private javax.swing.JLabel word1;
    private javax.swing.JLabel word2;
    private javax.swing.JLabel word3;
    // End of variables declaration//GEN-END:variables
}


/** Pauza, za sutra:
 * Dakle , ispravka kod promene kruga, regulacija , bice valid move i good move.
 * , otvaranja kutija, bodovanja i pobede.
 * 
 * Update:
 * ispravljen redosled, pobeda..
 * jos otvaranje kutija, prosledjivanje bodova, maybe another set reci?
 * 
 * Update 2:
 * otvara kutije, boduje validno..sad prebaci drugom da vidi i skloni system outs za testiranje.
 * 
 * DONE
 */