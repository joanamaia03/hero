import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private int width;
    private int height;
    private Hero hero;
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;

    public Arena(int width, int height){
        this.width=width;
        this.height=height;
        hero= new Hero(10,10);
        this.walls = createWalls();
        this.coins=createCoins();
        this.monsters=createMonsters();
    }
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#ffb6c1"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new
                TerminalSize(width, height), ' ');
        hero.draw(graphics);
        for(Wall wall : walls)
            wall.draw(graphics);
        for(Coin coin : coins)
            coin.draw(graphics);
        for(Monster monster : monsters)
            monster.draw(graphics);
    }
    public void moveHero(Position position) {
        if (canHeroMove(position))
           hero.setPosition(position);
        retrieveCoins();
    }
    private boolean canHeroMove(Position pos) {
        return (pos.getX() >= 1 && pos.getX() < width-1) && (pos.getY() >= 1 && pos.getY() < height-1) && !walls.contains(new Wall(pos.getX(), pos.getY()));
    }
    public void processKey(KeyStroke key) {
        System.out.println(key);
        if (key.getKeyType() == KeyType.ArrowUp)
            moveHero(hero.moveUp());
        if (key.getKeyType() == KeyType.ArrowDown)
            moveHero(hero.moveDown());
        if (key.getKeyType() == KeyType.ArrowRight)
            moveHero(hero.moveRight());
        if (key.getKeyType() == KeyType.ArrowLeft)
            moveHero(hero.moveLeft());
    }
    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }
    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for(int i=0; i<5; i++){
            Coin newcoin = new Coin(random.nextInt(width-2) + 1,
                    random.nextInt(height-2)+1);
            if(!coins.contains(newcoin) && !newcoin.getPosition().equals(hero.getPosition()))
                coins.add(newcoin);
        }
        return coins;
    }
    private void retrieveCoins(){
        for(Coin coin : coins){
            if(hero.getPosition().equals(coin.getPosition())) {
                coins.remove(coin);
                break;
            }
        }
    }
    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for(int i=0; i<5; i++){
            Monster newmonster = new Monster(random.nextInt(width-2) + 1, random.nextInt(height-2)+1);
            if(!monsters.contains(newmonster) && !newmonster.getPosition().equals(hero.getPosition()))
                monsters.add(newmonster);
        }
        return monsters;
    }
    public void moveMonsters(){
        for(Monster monster : monsters){
            monster.setPosition(monster.move(this));
        }
    }

    public boolean verifyMonsterCollisions(){
        for(Monster monster : monsters){
            if(monster.getPosition().equals(hero.getPosition())){
                System.out.println("Death.");
                return true;
            }
        }
        return false;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

