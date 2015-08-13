package maze;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Barrel extends Thread{
	
	private Image barrel;
	private Map map;
	private int x, y, tileX, tileY;
	private String barrelFile = "src/resources/barrel.png";
	private boolean stopRequested = false; 
	private boolean sharkTime = false;
	
	public Barrel(Map m) {
		ImageIcon img = new ImageIcon(barrelFile);
		barrel = img.getImage();
		map = m;
	}
	
	public void run() {
		while(!stopRequested){
			try {
				sleep(1500);
				//System.out.println("move fisherman");
				move();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Barrel stopping");
	}
	public void requestStop()
	{
		stopRequested = true;
	}
	public Image getBarrel() {
		return barrel;
	}
	
	public int getBarrelX() {
		return x;
	}
	
	public int getBarrelY() {
		return y;
	}
	
	public int getBarrelTileX() {
		return tileX;
	}
	
	public int getBarrelTileY() {
		return tileY;
	}
	
	public void setStartLocation(int dx, int dy) {
		x = dx * 32;
		y = dy * 32;
		
		tileX = dx;
		tileY = dy;
	}
	
	//public void move(int dx, int dy, int tx, int ty){
	public void move(){
		int tx = 0;
		int ty = 0;
		boolean barrelMoved = false;
		while (!barrelMoved){
			int direction = new Random().nextInt(8);
			switch(direction){
				case 0 : {
					if(!(getBarrelTileY() - 1 < 0) && map.getMap(getBarrelTileX(), getBarrelTileY() - 1) == 'g') {
						barrelMoved = true;
						//fm.move(0, -32, 0, -1);
						tx = 0;
						ty = -1;
						break;
					}
					break;
				}

				case 2 : {
						if(!(getBarrelTileX() + 1 > map.getMapSize() - 1) && map.getMap(getBarrelTileX() + 1, getBarrelTileY()) == 'g') {
							//fm.move(32, 0, 1, 0);
							tx = 1;
							ty = 0;
							barrelMoved = true;
							break;
						}
					break;
				}

				case 4 : {
						if(!(getBarrelTileY() + 1 > map.getMapSize() - 1) && map.getMap(getBarrelTileX(), getBarrelTileY() + 1) == 'g') {
							//fm.move(0, 32, 0, 1);
							tx = 0;
							ty = 1;
							barrelMoved = true;
							break;
					}
					break;
				}

				case 6 : {
						if(!(getBarrelTileX() - 1 < 0) && map.getMap(getBarrelTileX() - 1, getBarrelTileY()) == 'g') {
							//fm.move(-32, 0, -1, 0);
							tx = -1;
							ty = 0;
							barrelMoved = true;
							break;
					}
					break;
				}

			}
		}
		x += tx * 32;
		y += ty * 32;
		
		tileX += tx;
		tileY += ty;
	}
	
	public void randomStart() {
		Random rand = new Random();
		int randX;
		int randY;
		do{
			do{
			randX = rand.nextInt(13);
			}while(Math.abs(map.getStartX()-randX) < 3);
			
			do{
			randY = rand.nextInt(13);
			}while(Math.abs(map.getStartY()-randY) < 3);
			
		//test that the location generated falls on a Ground "g" space	
		}while(map.getMap(randX, randY) != 'g');
			setStartLocation(randX, randY);
	}
	
	public void isNear(Player p) {
		//moveFisherman();
		//if(isFishCaught()){
			//fishermanCaughtFish(p);
		//}
	}
	
	public Image getImage() {
		return barrel;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getTileX() {
		return tileX;
	}
	
	public int getTileY() {
		return tileY;
	}
	
	public void isPlayerNear(Player player){
		if(player.getTileX() == tileX && player.getTileY() == tileY){
			sharkTime = true;
		}
	}
	
	public boolean getSharkTime(){
		return sharkTime;
	}
	
	public void resetsharkTime(){
		sharkTime = false;
	}

	public boolean isStopRequested() {
		return stopRequested;
	}
	

}
