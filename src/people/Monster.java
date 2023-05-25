package people;

public class Monster {
    private String name;
    private String type;
    private int health;
    private int damage;

    public Monster(String name, String type, int health, int damage) {
        this.name = name;
        this.type = type;
        this.health = health;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public void decreaseHealth(int amount) {
        health -= amount;
        if (health < 0) {
            health = 0;
        }
    }

    public boolean isAlive() {
        return health > 0;
    }
}