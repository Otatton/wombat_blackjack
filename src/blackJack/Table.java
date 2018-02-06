package blackJack;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;

import java.awt.Color;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.SwingConstants;

public class Table extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static int width = 60;
    private static int height = 90;
    private int tableHeight = 650;
    private int tableWidth = 800;

    private Border cardBorder = new LineBorder(Color.YELLOW);
    private ImageIcon cardBack = new ImageIcon(resizeImage("/images/back.png"));

    private JLabel lblDealerCard1 = new JLabel("");
    private JLabel lblDealerCard2 = new JLabel("");
    private JLabel lblDealerCard3 = new JLabel("");
    private JLabel lblDealerCard4 = new JLabel("");
    private JLabel lblDealerCard5 = new JLabel("");
    private JLabel lblDealerCard6 = new JLabel("");
    private JLabel lblDiscard = new JLabel("");

    private JLabel lblPlayerCard1 = new JLabel("");
    private JLabel lblPlayerCard2 = new JLabel("");
    private JLabel lblPlayerCard3 = new JLabel("");
    private JLabel lblPlayerCard4 = new JLabel("");
    private JLabel lblPlayerCard5 = new JLabel("");
    private JLabel lblPlayerCard6 = new JLabel("");
    private JLabel lblResult = new JLabel("");
    private JLabel lblPlayerScore = new JLabel("");
    private JLabel lblDealerScore = new JLabel("");
    private JLabel lblSplit16 = new JLabel("");
    private JLabel lblSplit15 = new JLabel("");
    private JLabel lblSplit14 = new JLabel("");
    private JLabel lblSplit13 = new JLabel("");
    private JLabel lblSplit12 = new JLabel("");
    private JLabel lblSplit11 = new JLabel("");
    private JLabel lblSplit26 = new JLabel("");
    private JLabel lblSplit25 = new JLabel("");
    private JLabel lblSplit24 = new JLabel("");
    private JLabel lblSplit23 = new JLabel("");
    private JLabel lblSplit22 = new JLabel("");
    private JLabel lblSplit21 = new JLabel("");
    private JLabel lblSplit1 = new JLabel("");
    private JLabel lblSplit2 = new JLabel("");
    private JLabel lblSplitScore1 = new JLabel("");
    private JLabel lblSplitScore2 = new JLabel("");

    private JButton btnHit = new JButton("Hit");
    private JButton btnStand = new JButton("Stand");
    private JButton btnDoubleDown = new JButton("Double Down");
    private JButton btnPlaceBet = new JButton("Place Bet");
    private JButton btnLeaveGame = new JButton("Leave");

    static List<Deck> playingDeck = new ArrayList<Deck>();
    private Deck dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5, dealerCard6;
    private Deck playerCard1, playerCard2, playerCard3, playerCard4, playerCard5, playerCard6;
    private Deck splitCard11, splitCard12, splitCard13, splitCard14, splitCard15, splitCard16;
    private Deck splitCard21, splitCard22, splitCard23, splitCard24, splitCard25, splitCard26;

    private Player player;
    private JLabel lblCurrent;
    private final JLabel lblBet = new JLabel("Bet");
    private final JLabel lblCurrentBet = new JLabel("");

    private boolean blackJack;
    private boolean playerBust;
    private boolean playerStand;
    private boolean insurance;
    private boolean split;
    private boolean doubledDown;

    private Integer cardNumber;
    private Integer playerScore;
    private Integer dealerScore;
    private Integer dealerCheckScore;
    private Integer dealerCardNumber;
    private Integer playerCardNumber;
    private Integer splitHands;
    private Integer splitScore1;
    private Integer splitScore2;

    final String push = "Push";
    final String lose = "Lose";
    final String win = "Win";
    final String bust = "Bust";

    private Save mySave = new Save();


    public static void main(String[] args) {
        for (Deck d : Deck.values())
        {
            playingDeck.add(d);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Table frame = new Table();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Table() {
        setTitle("Blackjack");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 994, 650);
        contentPane = new JPanel();
        contentPane.setOpaque(false);
        contentPane.setBorder(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        cardLabels();
        createTable();
        shuffleDeck();
        resetPlay();
        btnHit.setEnabled(false);
        btnStand.setEnabled(false);
        btnDoubleDown.setEnabled(false);
        btnPlaceBet.setEnabled(true);
    }

    private void createTable() {
        ImageIcon tableIcon = new ImageIcon(Table.class.getResource("/images/Table.png"));
        Image tableImage1 = tableIcon.getImage();
        Image tableImage = tableImage1.getScaledInstance(tableWidth, tableHeight, Image.SCALE_SMOOTH);

        tableLabels();
        tableButtons();
        startGame();
        playerButtons();

        JLabel lblTableImage = new JLabel("");
        lblTableImage.setEnabled(true);
        lblTableImage.setIcon(new ImageIcon(tableImage));
        lblTableImage.setBounds(0, 0, tableWidth, tableHeight);
        contentPane.add(lblTableImage);
    }

    private void playerButtons() {
        btnLeaveGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        btnLeaveGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                saveGame();
                System.exit(0);
            }
        });


        btnLeaveGame.setBounds(854, 35, 89, 23);
        contentPane.add(btnLeaveGame);
        JLabel lblPlayer = new JLabel("Player");
        lblPlayer.setHorizontalAlignment(SwingConstants.LEFT);
        lblPlayer.setBounds(827, 522, 46, 14);
        contentPane.add(lblPlayer);

        JLabel lblCash = new JLabel("Cash");
        lblCash.setHorizontalAlignment(SwingConstants.LEFT);
        lblCash.setBounds(827, 547, 46, 14);
        contentPane.add(lblCash);

        JLabel lblName = new JLabel(player.getName());
        lblName.setForeground(Color.BLACK);
        lblName.setHorizontalAlignment(SwingConstants.RIGHT);
        lblName.setBounds(873, 522, 89, 14);
        contentPane.add(lblName);

        lblCurrent = new JLabel("$" + String.valueOf(player.getCash()));
        lblCurrent.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCurrent.setBounds(873, 547, 89, 14);
        contentPane.add(lblCurrent);

        lblBet.setBounds(827, 572, 46, 14);
        contentPane.add(lblBet);

        lblCurrentBet.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCurrentBet.setBounds(873, 572, 89, 14);
        contentPane.add(lblCurrentBet);
    }

    private void newBet() {
        Object[] bet = {10, 20, 50};
        Object defaultBet = bet[0];
        int myBet = JOptionPane.showOptionDialog(this, "How much would you like to bet?", "Dealer",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, bet, defaultBet);
        player.placeBet((int) bet[myBet]);
        lblCurrentBet.setText("$" + String.valueOf(player.getBet()));
    }

    private void newPlayer() {
        boolean stop = false;
        String name = null;
        while(stop == false){
            name = JOptionPane.showInputDialog("Enter your name:");

            if(name == null || name.length() <= 0){
                JOptionPane.showMessageDialog(null, "I am sorry, I did not catch your name...");
            }
            else{
                stop = true;
            }
        }
        player = new Player(name, 500);
        JOptionPane.showMessageDialog(null, "Thank you for joining the game " + player.getName()
                + ", please sit down while the deck is shuffled");
    }

    private void startGame()
    {
        int readFile = JOptionPane.showConfirmDialog
                (null, "Resume last game?", "Start", JOptionPane.YES_NO_OPTION);

        if(readFile == 0) {
            try {
                ArrayList<String> list = mySave.readFile();
                player = new Player(list.get(0), Integer.parseInt(list.get(1)));
                if(player.getCash() <= 0)
                {
                    JOptionPane.showMessageDialog(null, "I dont see any money on your account, what was your name again?");
                    newPlayer();
                }
            } catch (NumberFormatException e) {
                newPlayer();
            }
        }
        else {
            newPlayer();
        }
    }

    private void saveGame()
    {
        int x = JOptionPane.showConfirmDialog(null, "Save your game?", "Save", JOptionPane.YES_NO_OPTION);

        if (x == 0) {
            mySave.saveFile(player.getName(), player.getCash());
            JOptionPane.showMessageDialog(null, "Your progress is saved");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Your was not saved");
        }
    }

    private void tableButtons() {
        //Hit
        btnHit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        btnHit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                if (playerCardNumber > 2){
                    btnDoubleDown.setEnabled(false);
                }
                if (playerBust == false){
                    playerHit();
                }
            }
        });
        btnHit.setBounds(827, 377, 135, 23);
        contentPane.add(btnHit);

        //Stand
        btnStand.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnStand.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (playerStand == false){
                    if (split == false) {
                        dealerHand();
                        playerStand = true;
                    } else {
                        switch(splitHands){
                            case 1:
                                lblSplit1.setIcon(null);
                                lblSplit2.setIcon(new ImageIcon(resizeImage("/images/arrow.png")));
                                splitHands = 2;
                                playerCardNumber = 2;
                                break;
                            case 2:
                                lblSplit2.setIcon(null);
                                dealerHand();
                                playerStand = true;
                                break;
                        }
                    }
                }
            }});
        btnStand.setBounds(827, 416, 135, 23);
        contentPane.add(btnStand);

        //Double Down
        btnDoubleDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (btnDoubleDown.isEnabled() == true){
                    doubledDown = true;
                    doubleDown();
                }
            }
        });
        btnDoubleDown.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnDoubleDown.setBounds(827, 455, 135, 23);
        contentPane.add(btnDoubleDown);


        //Bet
        btnPlaceBet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        btnPlaceBet.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                if (btnPlaceBet.isEnabled() == true) {
                    newBet();
                    updateCash();
                    dealCards();
                }
            }
        });
        btnPlaceBet.setBounds(827, 488, 135, 23);
        contentPane.add(btnPlaceBet);
    }

    private void tableLabels() {
        lblDealerScore.setOpaque(true);
        lblDealerScore.setHorizontalAlignment(SwingConstants.CENTER);
        lblDealerScore.setForeground(Color.GREEN);
        lblDealerScore.setFont(new Font("Segoe UI Black", Font.PLAIN, 40));
        lblDealerScore.setBackground(Color.BLACK);
        lblDealerScore.setBounds(827, 86, 135, 47);
        contentPane.add(lblDealerScore);

        lblResult.setHorizontalAlignment(SwingConstants.CENTER);
        lblResult.setForeground(Color.BLACK);
        lblResult.setFont(new Font("Bodoni MT Black", Font.PLAIN, 40));
        lblResult.setBounds(812, 205, 166, 53);
        contentPane.add(lblResult);

        lblPlayerScore.setOpaque(true);
        lblPlayerScore.setForeground(Color.GREEN);
        lblPlayerScore.setBackground(Color.BLACK);
        lblPlayerScore.setFont(new Font("Segoe UI Black", Font.PLAIN, 40));
        lblPlayerScore.setBounds(827, 319, 135, 47);
        lblPlayerScore.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblPlayerScore);

    }

    private void shuffleDeck(){
        Collections.shuffle(playingDeck);
        cardNumber = 0;
        lblDiscard.setIcon(null);
    }

    private void resetPlay(){
        blackJack = false;
        playerScore = 0;
        playerBust = false;
        playerStand = false;
        insurance = false;
        doubledDown = false;
        split = false;
        dealerScore = 0;
        dealerCardNumber = 0;
        playerCardNumber = 0;
        btnHit.setEnabled(true);
        btnStand.setEnabled(true);
        btnDoubleDown.setEnabled(true);
        btnPlaceBet.setEnabled(false);
        lblResult.setText("");
        splitScore1 = 0;
        splitScore2 = 0;

        dealerCard1 = null;
        lblDealerCard1.setIcon(null);
        dealerCard2 = null;
        lblDealerCard2.setIcon(null);
        dealerCard3 = null;
        lblDealerCard3.setIcon(null);
        dealerCard4 = null;
        lblDealerCard4.setIcon(null);
        dealerCard5 = null;
        lblDealerCard5.setIcon(null);
        dealerCard6 = null;
        lblDealerCard6.setIcon(null);

        playerCard1 = null;
        lblPlayerCard1.setIcon(null);
        playerCard2 = null;
        lblPlayerCard2.setIcon(null);
        playerCard3 = null;
        lblPlayerCard3.setIcon(null);
        playerCard4 = null;
        lblPlayerCard4.setIcon(null);
        playerCard5 = null;
        lblPlayerCard5.setIcon(null);
        playerCard6 = null;
        lblPlayerCard6.setIcon(null);

        lblSplit1.setIcon(null);
        lblSplit1.setText(null);
        splitCard11 = null;
        lblSplit11.setIcon(null);
        splitCard11 = null;
        lblSplit12.setIcon(null);
        splitCard13 = null;
        lblSplit13.setIcon(null);
        splitCard14 = null;
        lblSplit14.setIcon(null);
        splitCard15 = null;
        lblSplit15.setIcon(null);
        splitCard16 = null;
        lblSplit16.setIcon(null);
        lblSplitScore1.setText(null);

        lblSplit2.setIcon(null);
        lblSplit2.setText(null);
        splitCard21 = null;
        lblSplit21.setIcon(null);
        splitCard22 = null;
        lblSplit22.setIcon(null);
        splitCard23 = null;
        lblSplit23.setIcon(null);
        splitCard24 = null;
        lblSplit24.setIcon(null);
        splitCard25 = null;
        lblSplit25.setIcon(null);
        splitCard26 = null;
        lblSplit26.setIcon(null);
        lblSplitScore2.setText(null);
    }

    private void dealCards(){
        dealerCheckScore = 0;
        if (cardNumber >= 4){
            lblDiscard.setIcon(new ImageIcon(resizeImage("/images/back.png")));
        }

        resetPlay();
        dealerHit();
        dealerHit();
        playerHit();
        playerHit();

        dealerScore = dealerCard1.getCardValue();
        lblDealerScore.setText(dealerScore.toString());
        if (dealerScore == 11){
            insurance = Insurance.choice();
            dealerCheckScore = dealerScore + dealerCard2.getCardValue();
            if (dealerCheckScore == 21){
                lblDealerCard2.setIcon(new ImageIcon(resizeImage(dealerCard2.getCardImage())));
            }
        } else if(dealerScore == 10){
            dealerCheckScore = dealerScore + dealerCard2.getCardValue();
            if (dealerCheckScore == 21){
                lblDealerCard2.setIcon(new ImageIcon(resizeImage(dealerCard2.getCardImage())));
            }
        }

        if ((playerScore == 21) || (dealerCheckScore == 21)) {
            lblDealerCard2.setIcon(new ImageIcon(resizeImage(dealerCard2.getCardImage())));
            dealerScore = dealerCard1.getCardValue() + dealerCard2.getCardValue();
            btnHit.setEnabled(false);
            btnStand.setEnabled(false);
            btnDoubleDown.setEnabled(false);
            btnPlaceBet.setEnabled(true);
            playerStand = true;
            compareScores();
        } else {
            if(playerCard1.getCardType() == playerCard2.getCardType()) {
                split = Split.choice();
            }
            if (split == true) splitHand();
        }
    }

    private void splitHand() {
        playerScore = 0;
        splitHands = 1;
        lblPlayerScore.setText("");
        splitCard11 = playerCard1;
        lblSplit11.setIcon(lblPlayerCard1.getIcon());
        playerCard1 = null;
        lblPlayerCard1.setIcon(null);
        cardNumber += 1;
        splitCard12 = playingDeck.get(cardNumber);
        lblSplit12.setIcon(new ImageIcon(resizeImage(splitCard12.getCardImage())));

        splitCard21 = playerCard2;
        lblSplit21.setIcon(lblPlayerCard2.getIcon());
        playerCard2 = null;
        lblPlayerCard2.setIcon(null);
        cardNumber += 1;
        splitCard22 = playingDeck.get(cardNumber);
        lblSplit22.setIcon(new ImageIcon(resizeImage(splitCard22.getCardImage())));

        splitScore1 = (splitCard11.getCardValue() + splitCard12.getCardValue());
        splitScore2 = (splitCard21.getCardValue() + splitCard22.getCardValue());
        lblSplitScore2.setText(splitScore2.toString());

        //Only one card is dealt when splitting Aces
        if (splitCard11.getCardValue() == 11) {
            playerStand = true;
            playerSplitHand1();
            playerSplitHand2();
            lblSplit1.setIcon(null);
            lblSplit2.setIcon(null);
            dealerHand();
        } else {
            playerSplitHand1();
            if (splitScore1 >= 21){
                playerSplitHand2();
            }
        }
    }

    private void cardLabels() {
        //lblDiscard.setBorder(cardBorder);
        lblDiscard.setBounds(32, 64, width, height);
        contentPane.add(lblDiscard);

        //lblDealerCard6.setBorder(cardBorder);
        lblDealerCard6.setBounds(533, 64, width, height);
        contentPane.add(lblDealerCard6);

        //lblDealerCard5.setBorder(cardBorder);
        lblDealerCard5.setBounds(500, 64, width, height);
        contentPane.add(lblDealerCard5);

        //lblDealerCard4.setBorder(cardBorder);
        lblDealerCard4.setBounds(466, 64, width, height);
        contentPane.add(lblDealerCard4);

        //lblDealerCard3.setBorder(cardBorder);
        lblDealerCard3.setBounds(433, 64, width, height);
        contentPane.add(lblDealerCard3);

        //lblDealerCard2.setBorder(cardBorder);
        lblDealerCard2.setBounds(400, 64, width, height);
        contentPane.add(lblDealerCard2);

        //lblDealerCard1.setBorder(cardBorder);
        lblDealerCard1.setBounds(366, 64, width, height);
        contentPane.add(lblDealerCard1);

        //lblPlayerCard6.setBorder(cardBorder);
        lblPlayerCard6.setBounds(533, 400, width, height);
        contentPane.add(lblPlayerCard6);

        //lblPlayerCard5.setBorder(cardBorder);
        lblPlayerCard5.setBounds(500, 400, width, height);
        contentPane.add(lblPlayerCard5);

        //lblPlayerCard4.setBorder(cardBorder);
        lblPlayerCard4.setBounds(466, 400, width, height);
        contentPane.add(lblPlayerCard4);

        //lblPlayerCard3.setBorder(cardBorder);
        lblPlayerCard3.setBounds(433, 400, width, height);
        contentPane.add(lblPlayerCard3);

        //lblPlayerCard2.setBorder(cardBorder);
        lblPlayerCard2.setBounds(400, 400, width, height);
        contentPane.add(lblPlayerCard2);

        //lblPlayerCard1.setBorder(cardBorder);
        lblPlayerCard1.setBounds(366, 400, width, height);
        contentPane.add(lblPlayerCard1);

        //lblSplit16.setBorder(cardBorder);
        lblSplit16.setBounds(258, 406, width, height);
        contentPane.add(lblSplit16);

        //lblSplit15.setBorder(cardBorder);
        lblSplit15.setBounds(238, 386, width, height);
        contentPane.add(lblSplit15);

        //lblSplit14.setBorder(cardBorder);
        lblSplit14.setBounds(218, 366, width, height);
        contentPane.add(lblSplit14);

        //lblSplit13.setBorder(cardBorder);
        lblSplit13.setBounds(198, 346, width, height);
        contentPane.add(lblSplit13);

        //lblSplit12.setBorder(cardBorder);
        lblSplit12.setBounds(178, 326, width, height);
        contentPane.add(lblSplit12);

        //lblSplit11.setBorder(cardBorder);
        lblSplit11.setBounds(158, 306, width, height);
        contentPane.add(lblSplit11);

        //lblSplit26.setBorder(cardBorder);
        lblSplit26.setBounds(658, 4067, width, height);
        contentPane.add(lblSplit26);

        //lblSplit25.setBorder(cardBorder);
        lblSplit25.setBounds(638, 386, width, height);
        contentPane.add(lblSplit25);

        //lblSplit24.setBorder(cardBorder);
        lblSplit24.setBounds(618, 366, width, height);
        contentPane.add(lblSplit24);

        //lblSplit23.setBorder(cardBorder);
        lblSplit23.setBounds(598, 346, width, height);
        contentPane.add(lblSplit23);

        //lblSplit22.setBorder(cardBorder);
        lblSplit22.setBounds(578, 326, width, height);
        contentPane.add(lblSplit22);

        //lblSplit21.setBorder(cardBorder);
        lblSplit21.setBounds(558, 306, width, height);
        contentPane.add(lblSplit21);

        //lblSplit1.setBorder(cardBorder);
        lblSplit1.setBounds(178, 205, height, height);
        lblSplit1.setForeground(Color.YELLOW);
        lblSplit1.setFont(new Font("Segoe UI Black", Font.PLAIN, 38));
        contentPane.add(lblSplit1);

        //lblSplit2.setBorder(cardBorder);
        lblSplit2.setBounds(578, 205, height, height);
        lblSplit2.setForeground(Color.YELLOW);
        lblSplit2.setFont(new Font("Segoe UI Black", Font.PLAIN, 38));
        contentPane.add(lblSplit2);

        //lblSplitScore1.setBorder(cardBorder);
        lblSplitScore1.setBounds(178, 450, height, height);
        lblSplitScore1.setForeground(Color.YELLOW);
        lblSplitScore1.setFont(new Font("Segoe UI Black", Font.PLAIN, 40));
        contentPane.add(lblSplitScore1);

        //lblSplitScore2.setBorder(cardBorder);
        lblSplitScore2.setBounds(558, 450, height, height);
        lblSplitScore2.setForeground(Color.YELLOW);
        lblSplitScore2.setFont(new Font("Segoe UI Black", Font.PLAIN, 40));
        contentPane.add(lblSplitScore2);
    }

    private void dealerHit(){
        cardNumber += 1;
        dealerCardNumber += 1;
        switch (dealerCardNumber) {
            case 1:
                dealerCard1 = playingDeck.get(cardNumber);
                lblDealerCard1.setIcon(new ImageIcon(resizeImage(dealerCard1.getCardImage())));
                break;
            case 2:
                dealerCard2 = playingDeck.get(cardNumber);
                lblDealerCard2.setIcon(cardBack);
                break;
            case 3:
                dealerCard3 = playingDeck.get(cardNumber);
                lblDealerCard3.setIcon(new ImageIcon(resizeImage(dealerCard3.getCardImage())));
                break;
            case 4:
                dealerCard4 = playingDeck.get(cardNumber);
                lblDealerCard4.setIcon(new ImageIcon(resizeImage(dealerCard4.getCardImage())));
                break;
            case 5:
                dealerCard5 = playingDeck.get(cardNumber);
                lblDealerCard5.setIcon(new ImageIcon(resizeImage(dealerCard5.getCardImage())));
                break;
            case 6:
                dealerCard6 = playingDeck.get(cardNumber);
                lblDealerCard6.setIcon(new ImageIcon(resizeImage(dealerCard6.getCardImage())));
                break;
        }
    }

    private void dealerHand(){
        btnHit.setEnabled(false);
        btnDoubleDown.setEnabled(false);
        btnStand.setEnabled(false);
        lblDealerCard2.setIcon(new ImageIcon(resizeImage(dealerCard2.getCardImage())));
        dealerScore = dealerCard1.getCardValue() + dealerCard2.getCardValue();
        lblDealerScore.setText(dealerScore.toString());
        try{
            Thread.sleep(200);
        } catch (InterruptedException ie) {
            JOptionPane.showMessageDialog(null,"Pause Exception");
        }
        boolean dealerStand = false;
        while(dealerStand == false) {
            int numberOfAces = 0;
            dealerScore = 0;
            for (int d = 1; d <= dealerCardNumber ; d++){
                switch (d){
                    case 1:
                        dealerScore += dealerCard1.getCardValue();
                        if (dealerCard1.getCardValue() == 11) numberOfAces += 1;
                        break;
                    case 2:
                        dealerScore += dealerCard2.getCardValue();
                        if (dealerCard2.getCardValue() == 11) numberOfAces += 1;
                        break;
                    case 3:
                        dealerScore += dealerCard3.getCardValue();
                        if (dealerCard3.getCardValue() == 11) numberOfAces += 1;
                        break;
                    case 4:
                        dealerScore += dealerCard4.getCardValue();
                        if (dealerCard4.getCardValue() == 11) numberOfAces += 1;
                        break;
                    case 5:
                        dealerScore += dealerCard5.getCardValue();
                        if (dealerCard5.getCardValue() == 11) numberOfAces += 1;
                        break;
                    case 6:
                        dealerScore += dealerCard6.getCardValue();
                        if (dealerCard6.getCardValue() == 11) numberOfAces += 1;
                        break;
                }
            }

            try{
                Thread.sleep(200);
            } catch (InterruptedException ie) {
                JOptionPane.showMessageDialog(null,"Pause Exception");
            }

            if ((dealerScore > 21) && (numberOfAces > 0)){
                for (int a = 0; a < numberOfAces; a++) {
                    dealerScore -= 10;
                    if (dealerScore < 21) break;
                }
            }

            if (dealerScore < 17) {
                dealerHit();
            } else if (dealerScore == 17 && numberOfAces >= 1){
                dealerHit();
            } else {
                dealerStand = true;
            }
        }
        compareScores();
        btnPlaceBet.setEnabled(true);
    }

    private void playerHit(){
        playerCardNumber += 1;
        cardNumber += 1;
        if (split == false) {
            switch (playerCardNumber){
                case 1:
                    playerCard1 = playingDeck.get(cardNumber);
                    lblPlayerCard1.setIcon(new ImageIcon(resizeImage(playerCard1.getCardImage())));
                    break;
                case 2:
                    playerCard2 = playingDeck.get(cardNumber);
                    lblPlayerCard2.setIcon(new ImageIcon(resizeImage(playerCard2.getCardImage())));
                    break;
                case 3:
                    playerCard3 = playingDeck.get(cardNumber);
                    lblPlayerCard3.setIcon(new ImageIcon(resizeImage(playerCard3.getCardImage())));
                    break;
                case 4:
                    playerCard4 = playingDeck.get(cardNumber);
                    lblPlayerCard4.setIcon(new ImageIcon(resizeImage(playerCard4.getCardImage())));
                    break;
                case 5:
                    playerCard5 = playingDeck.get(cardNumber);
                    lblPlayerCard5.setIcon(new ImageIcon(resizeImage(playerCard5.getCardImage())));
                    break;
                case 6:
                    playerCard6 = playingDeck.get(cardNumber);
                    lblPlayerCard6.setIcon(new ImageIcon(resizeImage(playerCard6.getCardImage())));
                    break;
            }
            playerHand();
        } else {
            switch (splitHands){
                case 1:
                    switch (playerCardNumber){
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            splitCard13 = playingDeck.get(cardNumber);
                            lblSplit13.setIcon(new ImageIcon(resizeImage(splitCard13.getCardImage())));
                            break;
                        case 4:
                            splitCard14 = playingDeck.get(cardNumber);
                            lblSplit14.setIcon(new ImageIcon(resizeImage(splitCard14.getCardImage())));
                            break;
                        case 5:
                            splitCard15 = playingDeck.get(cardNumber);
                            lblSplit15.setIcon(new ImageIcon(resizeImage(splitCard15.getCardImage())));
                            break;
                        case 6:
                            splitCard16 = playingDeck.get(cardNumber);
                            lblSplit16.setIcon(new ImageIcon(resizeImage(splitCard16.getCardImage())));
                            break;
                    }
                    playerSplitHand1();
                    break;
                case 2:
                    switch (playerCardNumber){
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            splitCard23 = playingDeck.get(cardNumber);
                            lblSplit23.setIcon(new ImageIcon(resizeImage(splitCard23.getCardImage())));
                            break;
                        case 4:
                            splitCard24 = playingDeck.get(cardNumber);
                            lblSplit24.setIcon(new ImageIcon(resizeImage(splitCard24.getCardImage())));
                            break;
                        case 5:
                            splitCard25 = playingDeck.get(cardNumber);
                            lblSplit25.setIcon(new ImageIcon(resizeImage(splitCard25.getCardImage())));
                            break;
                        case 6:
                            splitCard26 = playingDeck.get(cardNumber);
                            lblSplit26.setIcon(new ImageIcon(resizeImage(splitCard26.getCardImage())));
                            break;
                    }
                    playerSplitHand2();
                    break;
            }
        }
    }

    private void playerSplitHand1() {
        lblSplit1.setIcon(new ImageIcon(resizeImage("/images/arrow.png")));
        int numberOfAces = 0;
        splitScore1 = 0;
        for (int c = 1; c <= playerCardNumber ; c++){
            switch (c){
                case 1:
                    splitScore1 += splitCard11.getCardValue();
                    if (splitCard11.getCardValue() == 11) numberOfAces += 1;
                    break;
                case 2:
                    splitScore1 += splitCard12.getCardValue();
                    if (splitCard12.getCardValue() == 11) numberOfAces += 1;
                    break;
                case 3:
                    splitScore1 += splitCard13.getCardValue();
                    if (splitCard13.getCardValue() == 11) numberOfAces += 1;
                    break;
                case 4:
                    splitScore1 += splitCard14.getCardValue();
                    if (splitCard14.getCardValue() == 11) numberOfAces += 1;
                    break;
                case 5:
                    splitScore1 += splitCard15.getCardValue();
                    if (splitCard15.getCardValue() == 11) numberOfAces += 1;
                    break;
                case 6:
                    splitScore1 += splitCard16.getCardValue();
                    if (splitCard16.getCardValue() == 11) numberOfAces += 1;
                    break;
            }
        }
        if ((splitScore1 > 21) && (numberOfAces > 0)){
            for (int a = 0; a < numberOfAces; a++) {
                splitScore1 -= 10;
                if (splitScore1 < 21) break;
            }
        }
        lblSplitScore1.setText(splitScore1.toString());
        if (splitScore1 >= 21){
            if (splitScore1 > 21) {
                lblSplit1.setText(bust);
            }
            if (doubledDown == false) {
                splitHands = 2;
            }
            playerCardNumber = 2;
            lblSplit1.setIcon(null);
            lblSplit2.setIcon(new ImageIcon(resizeImage("/images/arrow.png")));
        }
    }

    private void playerSplitHand2() {
        int numberOfAces = 0;
        splitScore2 = 0;
        for (int c = 1; c <= playerCardNumber ; c++){
            switch (c){
                case 1:
                    splitScore2 += splitCard21.getCardValue();
                    if (splitCard21.getCardValue() == 11) numberOfAces += 1;
                    break;
                case 2:
                    splitScore2 += splitCard22.getCardValue();
                    if (splitCard22.getCardValue() == 11) numberOfAces += 1;
                    break;
                case 3:
                    splitScore2 += splitCard23.getCardValue();
                    if (splitCard23.getCardValue() == 11) numberOfAces += 1;
                    break;
                case 4:
                    splitScore2 += splitCard24.getCardValue();
                    if (splitCard24.getCardValue() == 11) numberOfAces += 1;
                    break;
                case 5:
                    splitScore2 += splitCard25.getCardValue();
                    if (splitCard25.getCardValue() == 11) numberOfAces += 1;
                    break;
                case 6:
                    splitScore2 += splitCard26.getCardValue();
                    if (splitCard26.getCardValue() == 11) numberOfAces += 1;
                    break;
            }
        }
        if ((splitScore2 > 21) && (numberOfAces > 0)){
            for (int a = 0; a < numberOfAces; a++) {
                splitScore2 -= 10;
                if (splitScore2 < 21) break;
            }
        }
        lblSplitScore2.setText(splitScore2.toString());

        if (splitScore2 >= 21){
            lblSplit2.setIcon(null);
            if (splitScore2 > 21){
                lblSplit2.setText(bust);
                dealerHand();
            }
            playerStand = true;
        }
    }

    private void playerHand(){
        int numberOfAces = 0;
        playerScore = 0;
        for (int c = 1; c <= playerCardNumber ; c++){
            switch (c){
                case 1:
                    playerScore += playerCard1.getCardValue();
                    if (playerCard1.getCardValue() == 11) numberOfAces += 1;
                    break;
                case 2:
                    playerScore += playerCard2.getCardValue();
                    if (playerCard2.getCardValue() == 11) numberOfAces += 1;
                    break;
                case 3:
                    playerScore += playerCard3.getCardValue();
                    if (playerCard3.getCardValue() == 11) numberOfAces += 1;
                    break;
                case 4:
                    playerScore += playerCard4.getCardValue();
                    if (playerCard4.getCardValue() == 11) numberOfAces += 1;
                    break;
                case 5:
                    playerScore += playerCard5.getCardValue();
                    if (playerCard5.getCardValue() == 11) numberOfAces += 1;
                    break;
                case 6:
                    playerScore += playerCard6.getCardValue();
                    if (playerCard6.getCardValue() == 11) numberOfAces += 1;
                    break;
            }
        }

        if ((playerScore > 21) && (numberOfAces > 0)){
            for (int a = 0; a < numberOfAces; a++) {
                playerScore -= 10;
                if (playerScore < 21) break;
            }
        }

        if (playerScore > 21){
            playerBust = true;
            lblResult.setText(bust);
            btnHit.setEnabled(false);
            btnStand.setEnabled(false);
            playerStand = true;
            btnPlaceBet.setEnabled(true);
            lblDealerCard2.setIcon(new ImageIcon(resizeImage(dealerCard2.getCardImage())));
            dealerScore = dealerCard1.getCardValue() + dealerCard2.getCardValue();
            lblDealerScore.setText(dealerScore.toString());
        } else if (playerScore == 21 && playerCardNumber == 2){
            blackJack = true;
        }
        lblPlayerScore.setText(playerScore.toString());
    }

    private void compareScores(){
        lblDealerScore.setText(dealerScore.toString());
        if (split == false){
            if (dealerScore > 21){
                lblResult.setText(win);
                payOut();
            } else if (dealerScore == playerScore) {
                lblResult.setText(push);
                payOut();
            } else if(dealerScore > playerScore){
                lblResult.setText(lose);
            } else {
                lblResult.setText(win);
                payOut();
            }
            lblPlayerScore.setText(playerScore.toString());
        } else {
            if (splitScore1 > 21){
                lblSplit1.setText(bust);
            } else if (dealerScore > 21){
                lblSplit1.setText(win);
            } else if (dealerScore == splitScore1) {
                lblSplit1.setText(push);
            } else if(dealerScore > splitScore1){
                lblSplit1.setText(lose);
            } else {
                lblSplit1.setText(win);
            }

            if (splitScore2 > 21){
                lblSplit2.setText(bust);
            } else if (dealerScore > 21){
                lblSplit2.setText(win);
            } else if (dealerScore == splitScore2) {
                lblSplit2.setText(push);
            } else if(dealerScore > splitScore2){
                lblSplit2.setText(lose);
            } else {
                lblSplit2.setText(win);
            }
        }
        if (cardNumber > 42) {
            shuffleDeck();
        }
    }

    private void doubleDown() {
        int doubleBet = (player.getBet() * 2);
        player.placeBet(doubleBet);
        playerHit();
        if (split == false){
            if (playerScore <= 21) {
                dealerHand();
            }
        } else {
            if (splitHands == 1) {
                lblSplit1.setIcon(null);
                lblSplit2.setIcon(new ImageIcon(resizeImage("/images/arrow.png")));
                splitHands = 2;
                playerCardNumber = 2;
            } else {
                if (splitScore2 < 21) {
                    lblSplit2.setIcon(null);
                    dealerHand();
                }
            }
        }
    }

    private void updateCash()
    {
        lblCurrent.setText("$" + String.valueOf(player.getCash()));
    }

    public void payOut()
    {
        if(playerScore == 21){
            player.addCash(player.blackJack(blackJack));
        }else if (dealerScore > 21){
            player.addCash(player.blackJack(false));
        } else if (dealerScore == playerScore) {
            player.addCash(player.getBet());
        } else {
            player.addCash(player.blackJack(false));
        }
        updateCash();
    }

    public static Image resizeImage(String imagePath) {
        ImageIcon image1 = new ImageIcon(Table.class.getResource(imagePath));
        Image image2 = image1.getImage();
        Image image3 = image2.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return image3;
    }

    public Image resizeImage(ImageIcon imageName){
        Image image2 = imageName.getImage();
        Image image3 = image2.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return image3;
    }
}

