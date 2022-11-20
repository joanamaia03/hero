import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall extends Element {

    private Position position;
    public Wall(int x, int y) {
        super(x,y);
    }

    public void draw(TextGraphics graphics)  {
        graphics.setForegroundColor(TextColor.Factory.fromString("#993399"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "#");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        return this.getPosition().equals(((Wall) o).getPosition());
    }
}

