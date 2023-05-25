package people;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import location.Room;

public class Player {
    private String name;
    private int health;

    private Map<String, Map<String, Double>> attackEfficiency;
    private static final List<String> PLAYER_ATTACKS = List.of("Shoot", "Jump", "Punch");

    public Player(String name) {
        this.name = name;
        this.health = 100;
        initializeAttackEfficiency();
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void decreaseHealth(int amount) {
        health -= amount;
        if (health < 0) {
            health = 0;
        }
    }

    public void increaseHealth(int amount) {
        health += amount;
        if (health > 100) {
            health = 100;
        }
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int calculateAttackDamage(String attack, String monsterType) {
        int baseDamage = (int) (Math.random() * 20) + 10;
        double efficiencyMultiplier = getEfficiencyMultiplier(attack, monsterType);
        return (int) (baseDamage * efficiencyMultiplier);
    }

    private void initializeAttackEfficiency() {
        attackEfficiency = new HashMap<>();
        attackEfficiency.put("Shoot", createEfficiencyMap(2, 0.5, 1));
        attackEfficiency.put("Jump", createEfficiencyMap(1, 2, 0.5));
        attackEfficiency.put("Punch", createEfficiencyMap(0.5, 1, 2));
    }

    private Map<String, Double> createEfficiencyMap(double water, double fire, double plant) {
        Map<String, Double> efficiencyMap = new HashMap<>();
        efficiencyMap.put("Water", water);
        efficiencyMap.put("Fire", fire);
        efficiencyMap.put("Plant", plant);
        return efficiencyMap;
    }

    private double getEfficiencyMultiplier(String attack, String monsterType) {
        Map<String, Double> efficiencyMap = attackEfficiency.getOrDefault(attack, new HashMap<>());
        return efficiencyMap.getOrDefault(monsterType, 1.0);
    }

    public int chooseAttack() {
        System.out.println("\nChoose your attack:");
        for (int i = 0; i < PLAYER_ATTACKS.size(); i++) {
            System.out.println((i + 1) + ". " + PLAYER_ATTACKS.get(i));
        }
        return getUserInput("Enter the attack number: ", 1, PLAYER_ATTACKS.size()) - 1;
    }

    public int chooseRoom(List<Room> rooms) {
        System.out.println("\nChoose the room you want to enter:");
        for (int i = 0; i < rooms.size(); i++) {
            System.out.println((i + 1) + ". " + rooms.get(i).getName());
        }
        return getUserInput("Enter the room number: ", 1, rooms.size()) - 1;
    }

    private int getUserInput(String message, int min, int max) {
        Scanner scanner = new Scanner(System.in);
        int input = min - 1;
        while (input < min || input > max) {
            System.out.print(message);
            try {
                input = Integer.parseInt(scanner.nextLine());
                if (input < min || input > max) {
                    System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return input;
    }
}