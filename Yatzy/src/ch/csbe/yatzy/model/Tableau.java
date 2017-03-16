package ch.csbe.yatzy.model;

public class Tableau {

	private Player player;

	private int one;
	private int two;
	private int three;
	private int four;
	private int five;
	private int six;
	private int thriple;
	private int quatruple;
	private int fullhouse;
	private int smallstreet;
	private int bigstreet;
	private int yatzy;
	private int chance;

	public Tableau(Player player) {
		super();
		this.player = player;
	}

	public int getOne() {
		return one;
	}

	public void setOne(int one) {
		this.one = one;
	}

	public int getTwo() {
		return two;
	}

	public void setTwo(int two) {
		this.two = two;
	}

	public int getThree() {
		return three;
	}

	public void setThree(int three) {
		this.three = three;
	}

	public int getFour() {
		return four;
	}

	public void setFour(int four) {
		this.four = four;
	}

	public int getFive() {
		return five;
	}

	public void setFive(int five) {
		this.five = five;
	}

	public int getSix() {
		return six;
	}

	public void setSix(int six) {
		this.six = six;
	}

	public int getBonus() {
		return (calculateUp()>=63)?25:0;
	}

	public int getThriple() {
		return thriple;
	}

	public void setThriple(int thriple) {
		this.thriple = thriple;
	}

	public int getQuatruple() {
		return quatruple;
	}

	public void setQuatruple(int quatruple) {
		this.quatruple = quatruple;
	}

	public int getFullhouse() {
		return fullhouse;
	}

	public void setFullhouse(int fullhouse) {
		this.fullhouse = fullhouse;
	}

	public int getSmallstreet() {
		return smallstreet;
	}

	public void setSmallstreet(int smallstreet) {
		this.smallstreet = smallstreet;
	}

	public int getBigstreet() {
		return bigstreet;
	}

	public void setBigstreet(int bigstreet) {
		this.bigstreet = bigstreet;
	}

	public int getYatzy() {
		return yatzy;
	}

	public void setYatzy(int yatzy) {
		this.yatzy = yatzy;
	}

	public int getChance() {
		return chance;
	}

	public void setChance(int chance) {
		this.chance = chance;
	}

	public Player getPlayer() {
		return player;
	}
	
	public int calculateUp(){
		return getOne()+getTwo()+getThree()+getFour()+getFive()+getSix();
	}

	public int calculateUpSum(){
		return calculateUp()+getBonus();
	}

	public int calculateDown(){
		return getChance()+getYatzy()+getBigstreet()+getSmallstreet()+getFullhouse()+getThriple()+getQuatruple();
	}

	public int calculateTotal(){
		return calculateUpSum()+calculateDown();
	}
	
	

}
