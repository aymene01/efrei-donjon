package game;
import java.util.ArrayList;
import java.util.List;
import people.Player;
import location.Room;
import people.Monster;
import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);
    private Player player;
    private List<Room> rooms;
    private int currentRoomIndex;

    private static final List<String> PLAYER_ATTACKS = List.of("Shoot", "Jump", "Punch");

    public Game() {
        initGame();
    }

    private List<Room> createRooms() {
        List<Room> rooms = new ArrayList<>();

        // Room 1
        List<Monster> room1Monsters = new ArrayList<>();
        room1Monsters.add(new Monster("Monster 1", "Water", 50, 20));
        room1Monsters.add(new Monster("Monster 2", "Fire", 40, 15));
        rooms.add(new Room("Room 1", room1Monsters));

        // Room 2
        List<Monster> room2Monsters = new ArrayList<>();
        room2Monsters.add(new Monster("Monster 3", "Plant", 60, 25));
        room2Monsters.add(new Monster("Monster 4", "Water", 50, 20));
        rooms.add(new Room("Room 2", room2Monsters));

        // Room 3
        List<Monster> room3Monsters = new ArrayList<>();
        room3Monsters.add(new Monster("Monster 5", "Fire", 40, 15));
        room3Monsters.add(new Monster("Monster 6", "Plant", 60, 25));
        rooms.add(new Room("Room 3", room3Monsters));

        return rooms;
    }

    public void initGame(){
        System.out.println("Before we start, let's get to know each other.");
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();
        player = new Player(playerName);
        rooms = createRooms();
        currentRoomIndex = 0;
    }

    public void start() {
        System.out.println("Welcome, " + player.getName() + "! Let the game begin!");

        while (!rooms.isEmpty() && player.isAlive()) {
            Room currentRoom = rooms.get(currentRoomIndex);

            System.out.println("\n--- Current Room: " + currentRoom.getName() + " ---");
            System.out.println("Monsters in this room:");
            for (Monster monster : currentRoom.getMonsters()) {
                System.out.println(monster.getName() + " (" + monster.getType() + ") - " + monster.getHealth() + " health");
            }

            int attackIndex = player.chooseAttack();
            String attack = PLAYER_ATTACKS.get(attackIndex);

            for (Monster monster : currentRoom.getMonsters()) {
                int damage = player.calculateAttackDamage(attack, monster.getType());
                monster.decreaseHealth(damage);
                System.out.println(player.getName() + " used " + attack + " against " + monster.getName() + " (" + monster.getType() + ")");
                System.out.println(monster.getName() + " took " + damage + " damage");

                if (!monster.isAlive()) {
                    System.out.println(monster.getName() + " was defeated!");
                }
            }

            currentRoom.getMonsters().removeIf(monster -> !monster.isAlive());

            if (currentRoom.isCleared()) {
                System.out.println("\n--- " + currentRoom.getName() + " cleared! ---");
                rooms.remove(currentRoom);
                if (rooms.isEmpty()) {
                    System.out.println("Congratulations, " + player.getName() + "! You cleared all the rooms and won the game!");
                } else {
                    currentRoomIndex = player.chooseRoom(rooms);
                }
            }
        }

        if (!player.isAlive()) {
            System.out.println("Game Over! Better luck next time, " + player.getName() + "!");
        }
        System.out.println("Thank you for playing. Goodbye!");
    }
}
