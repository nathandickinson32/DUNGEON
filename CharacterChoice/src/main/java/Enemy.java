import java.util.Random;

public class Enemy {
    private String name;
    private int health;
    private int attackDamage;

    public Enemy(String name, int health, int attackDamage) {
        this.name = name;
        this.health = health;
        this.attackDamage = attackDamage;
    }
    public static Enemy generateRandomEnemy() {
        Random random = new Random();

        // Define array of enemy types
        String[] enemyTypes = {"Goblin", "Skeleton", "Orc", "Spider", "Zombie"};
        // Generate a random index to select an enemy type
        int randomIndex = random.nextInt(enemyTypes.length);
        String randomEnemyType = enemyTypes[randomIndex];

        int minHealth = 30;
        int maxHealth = 100;
        int randomHealth = random.nextInt(maxHealth - minHealth + 1) + minHealth;

        int minAttackDamage = 5;
        int maxAttackDamage = 20;
        int randomAttackDamage = random.nextInt(maxAttackDamage - minAttackDamage + 1) + minAttackDamage;

        return new Enemy(randomEnemyType, randomHealth, randomAttackDamage);
    }


    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public int attack() {
        Random random = new Random();
        // Calculate damage within a range (e.g., 80% to 120% of base damage)
        int minDamage = (int) (attackDamage * 0.8);
        int maxDamage = (int) (attackDamage * 1.2);
        return random.nextInt(maxDamage - minDamage + 1) + minDamage;
    }

    public String getName() {
        return name;
    }
}
