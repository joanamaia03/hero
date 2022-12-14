import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Hero extends Element {
    private Position position ;
    private Screen screen;
    public Hero(int x, int y) {
        super(x,y);
    }
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#993399"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "X");
    }

    public Position moveUp(){
        return new Position(getPosition().getX(), getPosition().getY()-1);
    }
    public Position moveRight(){
        return new Position(getPosition().getX() +1, getPosition().getY());
    }
    public Position moveDown(){
        return new Position(getPosition().getX(), getPosition().getY()+1);
    }
    public Position moveLeft(){
        return new Position(getPosition().getX() -1, getPosition().getY());
    }
}
