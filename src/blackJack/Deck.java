package blackJack;

import javax.swing.ImageIcon;

public enum Deck {
    HA("Hearts","Ace","/images/AH.png",11),
    H2("Hearts","Two","/images/2H.png",2),
    H3("Hearts","Three","/images/3H.png",3),
    H4("Hearts","Four","/images/4H.png",4),
    H5("Hearts","Five","/images/5H.png",5),
    H6("Hearts","Six","/images/6H.png",6),
    H7("Hearts","Seven","/images/7H.png",7),
    H8("Hearts","Eight","/images/8H.png",8),
    H9("Hearts","Nine","/images/9H.png",9),
    H10("Hearts","Ten","/images/10H.png",10),
    HJ("Hearts","Jack","/images/JH.png",10),
    HQ("Hearts","Queen","/images/QH.png",10),
    HK("Hearts","King","/images/KH.png",10),

    SA("Spades","Ace","/images/AS.png",11),
    S2("Spades","Two","/images/2S.png",2),
    S3("Spades","Three","/images/3S.png",3),
    S4("Spades","Four","/images/4S.png",4),
    S5("Spades","Five","/images/5S.png",5),
    S6("Spades","Six","/images/6S.png",6),
    S7("Spades","Seven","/images/7S.png",7),
    S8("Spades","Eight","/images/8S.png",8),
    S9("Spades","Nine","/images/9S.png",9),
    S10("Spades","Ten","/images/10S.png",10),
    SJ("Spades","Jack","/images/JS.png",10),
    SQ("Spades","Queen","/images/QS.png",10),
    SK("Spades","King","/images/KS.png",10),

    DA("Diamonds","Ace","/images/AD.png",11),
    D2("Diamonds","Two","/images/2D.png",2),
    D3("Diamonds","Three","/images/3D.png",3),
    D4("Diamonds","Four","/images/4D.png",4),
    D5("Diamonds","Five","/images/5D.png",5),
    D6("Diamonds","Six","/images/6D.png",6),
    D7("Diamonds","Seven","/images/7D.png",7),
    D8("Diamonds","Eight","/images/8D.png",8),
    D9("Diamonds","Nine","/images/9D.png",9),
    D10("Diamonds","Ten","/images/10D.png",10),
    DJ("Diamonds","Jack","/images/JD.png",10),
    DQ("Diamonds","Queen","/images/QD.png",10),
    DK("Diamonds","King","/images/KD.png",10),

    CA("Clubs","Ace","/images/AC.png",11),
    C2("Clubs","Two","/images/2C.png",2),
    C3("Clubs","Three","/images/3C.png",3),
    C4("Clubs","Four","/images/4C.png",4),
    C5("Clubs","Five","/images/5C.png",5),
    C6("Clubs","Six","/images/6C.png",6),
    C7("Clubs","Seven","/images/7C.png",7),
    C8("Clubs","Eight","/images/8C.png",8),
    C9("Clubs","Nine","/images/9C.png",9),
    C10("Clubs","Ten","/images/10C.png",10),
    CJ("Clubs","Jack","/images/JC.png",10),
    CQ("Clubs","Queen","/images/QC.png",10),
    CK("Clubs","King","/images/KC.png",10);

    private final String cardSuit;
    private final String cardType;
    private final String cardImagePath;
    private final ImageIcon cardImage;
    private final Integer cardValue;


    private Deck(String suit, String type, String imagepath, Integer value)
    {
        cardSuit = suit;
        cardType = type;
        cardImagePath = imagepath;
        cardImage = new ImageIcon(this.getClass().getResource(imagepath));
        cardValue = value;
    }

    public String getCardSuit()
    {
        return cardSuit;
    }

    public String getCardType()
    {
        return cardType;
    }

    public String getImagePath()
    {
        return cardImagePath;
    }

    public ImageIcon getCardImage()
    {
        return cardImage;
    }

    public Integer getCardValue()
    {
        return cardValue;
    }

}

