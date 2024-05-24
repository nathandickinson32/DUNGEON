import java.util.Random;

public abstract class Player {
    protected String name;
    protected int health;
    protected int attackDamage;
    protected int numHealthPotions;

    public Player(String name, int health, int attackDamage, int numHealthPotions) {
        this.name = name;
        this.health = health;
        this.attackDamage = attackDamage;
        this.numHealthPotions = numHealthPotions;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int attack() {
        Random random = new Random();
        int minDamage = (int) (attackDamage * 0.8);
        int maxDamage = (int) (attackDamage * 1.2);
        return random.nextInt(maxDamage - minDamage + 1) + minDamage;
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public void useHealthPotion() {
        health += 30;
        numHealthPotions--;
    }

    public int getHealth() {
        return health;
    }

    public int getNumHealthPotions() {
        return numHealthPotions;
    }

    public String getStatus() {
        return "Name: " + name + ", Health: " + health + ", Health Potions: " + numHealthPotions;
    }

}
