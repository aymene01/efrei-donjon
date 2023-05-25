package location;
import people.Monster;

import java.util.List;

public class Room {
    private String name;
    private List<Monster> monsters;

    public Room(String name, List<Monster> monsters) {
        this.name = name;
        this.monsters = monsters;
    }

    public boolean isCleared() {
        for (Monster monster : monsters) {
            if (monster.isAlive()) {
                return false;
            }
        }
        return true;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public String getName() {
        return name;
    }
}