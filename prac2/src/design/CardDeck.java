package design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
	List<Card> cards;
	public CardDeck(){
		cards = new ArrayList<Card>();
		for(Card.House h:Card.House.values()){
			for(Card.Value val:Card.Value.values()){
				cards.add(new Card(val,h));
			}
		}
	}
	public void display(){
		for(Card c:cards)
			System.out.println(c);
	}
	public void sort(){
		Collections.sort(cards);
	}
	public static void main(String[] args){
		CardDeck c = new CardDeck();
		c.sort();
		c.display();
		/*Card c1 = new Card(Card.Value.TEN,Card.House.CLOVER);
		Card c2 = new Card(Card.Value.THREE,Card.House.DIAMONDS);
		System.out.println(c1.compareTo(c2));*/
	}
}

class Card implements Comparable<Card>{
	public Value val;
	public House house;
	public Card(Value val, House h){
		this.val = val;
		this.house = h;
	}
	public static enum Value{
		ACE(14),KING(13),QUEEN(12),JACK(11),TEN(10),NINE(9),EIGHT(8),SEVEN(7),SIX(6),FIVE(5),FOUR(4),THREE(3),TWO(2);
		int faceVal;
		Value(int val){
			this.faceVal = val;
		}
	};
	public static enum House{
		SPADE(4),HEARTS(3),CLOVER(1),DIAMONDS(2);
		int value;
		House(int val){
			this.value = val;
		}
	};
	@Override
	public boolean equals(Object o){
		if(o instanceof Card){
			return (this.val.equals(((Card)o).val) && this.house.equals(((Card)o).house));
		}
		return false;
	}
	@Override
	public String toString(){
		return this.val.faceVal + " "+this.house.toString();
	}
	@Override
	public int compareTo(Card c){
		int valDiff = this.val.faceVal - c.val.faceVal;
		return valDiff == 0 ? this.house.value - c.house.value : valDiff;
	}
}