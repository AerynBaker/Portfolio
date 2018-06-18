import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Battleship extends Applet implements MouseListener {

	private final static int battleTextLength = 20;
	private final static int defaultGridSize = 10;
	private final static Color defaultColor = Color.BLACK;
	private final static Color targetColor = Color.MAGENTA;
	private final static Color hitColor = Color.RED;
	private final static Color missColor = Color.GREEN;
	private final static int difficulty = 1;
	
	//An implementation of the algorithm discussed in the article 
	//http://www.datagenetics.com/blog/december32011/
	
	public ArrayList<String> battleText;
	public Position currentTarget;
	public TargetingAI targetBot;
	public Board targetBoard;
	private int mode; //[0: targeting, 1: game]
	public Game game;
	
	public void init() {
		addMouseListener(this);
		this.setSize(1200,600);
		battleText = new ArrayList<String>();
		battleText.add("Start.");
		currentTarget = new Position(0,0);
		targetBot = new TargetingAI(difficulty);
		mode = 1;
		game = new Game(difficulty);
	}
	
	public void paint(Graphics g) {
		if(mode==0) {
			drawGrid(g,0,targetBoard);
			paintInterface(g);
			paintTargeting(g);
			printBattleText(g,600);
		}
		else if(mode==1) {
			drawGrid(g,0,game.getPlayerBoard());
			drawGrid(g,750,game.getOpponentBoard());
			printBattleText(g,500);
		}
	}
	
	public void paintTargeting(Graphics g) {
		drawSquare(g,currentTarget,targetColor);
	}
	
	public void paintShips(Graphics g) {
		//put player ships onto their grid
	}
	
	public void drawGrid(Graphics g, int offset, Board b) {
		for(int i=0; i<11; i++) {
			g.drawLine(offset+20+(i*40), 40, offset+20+(i*40), 440);
		}
		for(int j=0; j<11; j++) {
			g.drawLine(offset+20, 40+(j*40), offset+420, 40+(j*40));
		}
		int n = 1;
		char c = 'A';
		for(int i=0; i<10; i++) {
			g.drawString(""+c, offset+10, 60+(i*40));
			g.drawString(""+n, offset+35+(i*40), 30);
			n++;
			c++;
		}
		
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				if(b.get(i,j).isHit())
					drawShot(g, new Position(i,j),hitColor);
				else if(b.get(i,j).isMiss())
					drawShot(g, new Position(i,j),missColor);
			}
		}
	}
	
	public String targetText() {
		String s = "";
		s+="( "+(char)(currentTarget.getY()+65);
		s+=" , "+(currentTarget.getX()+1)+" )";
		return s;
	}
	
	public void paintInterface(Graphics g) {
		g.drawRect(20, 500, 100, 20);
		g.drawString(""+targetText(), 25, 515);
		g.drawRect(120, 500, 60, 20);
		g.drawString("Hit", 125, 515);
		g.drawRect(180, 500, 60, 20);
		g.drawString("Miss", 185, 515);
		//ships to sink
		int i=0;
		for(Ship s: targetBoard.getShips()) {
			g.drawRect(460, 60+(i*25), 90, 24);
			g.drawString("Sink Ship ("+s.getSize()+")", 465, 75+(i*25));
			i++;
		}
	}
	
	public void printBattleText(Graphics g, int x) {
		int j=1;
		int i = (battleText.size()-battleTextLength);
		if(i<0) i=0;
		for(i=i; i<battleText.size(); i++) {
			g.drawString(battleText.get(i), x, (25*j));
			j++;
		}
	}
	
	public void drawSquare(Graphics g, Position p, Color c) {
		g.setColor(c);
		g.drawRect(21+(p.getX()*40), 41+(p.getY()*40), 38, 38);
		g.setColor(defaultColor);
	}
	
	public void drawShot(Graphics g, Position p, Color c) {
		drawSquare(g,p,c);
		g.setColor(c);
		g.drawLine(20+(p.getX()*40), 40+(p.getY()*40), 60+(p.getX()*40), 80+(p.getY()*40));
		g.drawLine(60+(p.getX()*40), 40+(p.getY()*40), 20+(p.getX()*40), 80+(p.getY()*40));
		g.setColor(defaultColor);
	}
	
	public void drawTarget(Graphics g, Position p) {
		drawSquare(g,p,targetColor);
		//draw circle
	}

	@Override
	public void mousePressed(MouseEvent ms) {
		if (mode==0) {
			if (ms.getX() >= 120 && ms.getY() >= 500 && ms.getX() < 180 && ms.getY() < 520) {
				targetBoard.hit(currentTarget);
			}
			if (ms.getX() >= 180 && ms.getY() >= 500 && ms.getX() < 220 && ms.getY() < 520) {
				targetBoard.miss(currentTarget);
			}
			if (ms.getX() >= 460 && ms.getX() < 550) {
				int sNum = ((ms.getY() - 35) / 25);
				targetBoard.removeShip(sNum - 1);
			} else
				currentTarget = targetBot.target(targetBoard);
		}
		else if (mode==1) {
			//if click on grid, call findSquare
			if (ms.getX() >= 770 && ms.getY() >= 40 && ms.getX() < 1170 && ms.getY() < 440) {
			currentTarget = findSquare(ms.getX(), ms.getY());
			}
			game.shoot(currentTarget);
		}
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	//** shortcut methods **//
	
	public Position findSquare(int x, int y) {
		//find what grid square was clicked from mouseEvent's x and y
		//return the position of the grid square
		return new Position(x,y);
	}
}
