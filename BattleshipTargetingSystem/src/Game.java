
public class Game {

	private Board playerBoard;
	private Board opponentBoard;
	private TargetingAI opponent;
	
	public Game(int difficulty) {
		this.playerBoard = new Board();
		this.opponentBoard = new Board();
		this.opponent = new TargetingAI(difficulty);
	}
	
	public void shoot(Position p) {
		opponentBoard.shoot(p);
		returnFire();
	}
	
	private void returnFire() {
		opponent.calculate(playerBoard.shoot(opponent.target(playerBoard)), playerBoard);
	}

	public Board getPlayerBoard() {
		return playerBoard;
	}

	public TargetingAI getOpponent() {
		return opponent;
	}
	
	public Board getOpponentBoard() {
		return this.opponentBoard;
	}
}
