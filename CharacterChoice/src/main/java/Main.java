import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to My Dungeon");
        System.out.println("Choose your character class:");
        System.out.println("1. Warrior\n2. Princess\n3. Wizard");
        int classChoice = scanner.nextInt();

        Player player;
        switch (classChoice) {
            case 1:
                player = new Warrior();
                break;
            case 2:
                player = new Princess();
                break;
            case 3:
                player = new Wizard();
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Warrior.");
                player = new Warrior();
        }


        // Start game loop
        while (player.isAlive()) {
            // Explore dungeon
            exploreDungeon(scanner, player);
        }

        // Game over
        System.out.println("Game over! You have been defeated.");
    }

    // Method to simulate dungeon exploration
    public static void exploreDungeon(Scanner scanner, Player player) {
        System.out.println("You are in a dark dungeon. What would you like to do?");
        System.out.println("1. Move to the next room");
        System.out.println("2. Check player status");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                // Generate random encounter
                Enemy enemy = Enemy.generateRandomEnemy();
                System.out.println("You encounter a " + enemy.getName() + "! Prepare for battle.");
                // Start combat encounter
                combatEncounter(scanner, player, enemy);
                break;
            case 2:
                // Display player status
                System.out.println(player.getStatus());
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }


    public static void combatEncounter(Scanner scanner, Player player, Enemy enemy) {
        while (player.isAlive() && enemy.isAlive()) {
            System.out.println("Your turn. Choose an action:");
            System.out.println("1. Attack");
            System.out.println("2. Use potion");
            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    // Player attacks enemy
                    int playerDamage = player.attack();
                    enemy.takeDamage(playerDamage);
                    System.out.println("You attack the " + enemy.getName() + " for " + playerDamage + " damage!");
                    break;
                case 2:
                    // Player uses a health potion
                    if (player.getNumHealthPotions() > 0) {
                        player.useHealthPotion();
                        System.out.println("You use a health potion. Current health: " + player.getHealth());
                    } else {
                        System.out.println("You have no health potions left!");
                    }
                    break;
                default:
                    System.out.println("Invalid action.");
            }

            // Check if enemy is defeated
            if (!enemy.isAlive()) {
                System.out.println("You have defeated the " + enemy.getName() + "!");
                // Grant experience points or loot to player
                // Implement this based on your game design
                break;
            }

            // Enemy's turn
            int enemyDamage = enemy.attack();
            player.takeDamage(enemyDamage);
            System.out.println("The " + enemy.getName() + " attacks you for " + enemyDamage + " damage!");

            // Check if player is defeated
            if (!player.isAlive()) {
                break;
            }
        }
    }
}

