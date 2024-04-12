import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random randNum = new Random();


        // Game Variables
        String[] level1Enemies = {"Spider", "Mosquito", "Bee"};
        int level1EnemyHealth;
        int maxEnemyHealth = 5;
        int enemyAttackDamage = 5;
        String[] level2Enemies = {"Skeleton", "Zombie", "Assassin"};
        int level2EnemyHealth;
        int maxLevel2EnemyHealth = 75;
        int level2EnemyAttackDamage = 40;
        int weaponDrop = 0;

        // Player variables
        int health = 75;
        int attackDamage = 50;
        int numHealthPotions = 3;
        int healthPotionHealAmount = 30;
        int healthPotionDropChance = 40;
        int attackAgain = 30; // Percentage
        int score = 0;


        //weapon variables

        String sword = "sword";
        String karate = "karate";

        int swordBonus = 50; // added to attack damage
        int swordNeg = 10; // subtracted from heal amount
        int karateBonus = 40; // added to attackAgain
        int karateNeg = 25; // subtracted from attack damage

        // armour variables
        String dress = "dress";
        String knight = "knight";
        String turtle = "turtle";
        boolean armourChoiceLoop = false;

        boolean running = true;

        System.out.println("Welcome to the Dungeon!");
        System.out.println("What is your name traveller?");
        String heroName = scanner.nextLine();
        if (heroName.equals("Cynthia")) {
            heroName = "Princess Cynthia";
            attackAgain += 20;
            System.out.println("Welcome Princess Cynthia!");
        } else if (heroName.equals("Theodore")) {
            heroName = "Prince Theodore";
            attackDamage += 10;
            System.out.println("Welcome Prince Theodore!");
        } else if (heroName.equals("Harvey")) {
            heroName = "Prince Harvey";
            attackDamage += 10;
            System.out.println("Welcome Prince Harvey!");
        } else {
            System.out.println("Welcome " + heroName + "!");
        }


        System.out.println("Choose your armour!");

        String armourChoice;

        do {
            System.out.println("dress, knight, turtle");
            armourChoice = scanner.nextLine();
            if (armourChoice.equals("dress")) {
                health += 35;
                attackDamage += 20;
                armourChoiceLoop = true;
            } else if (armourChoice.equals("knight")) {
                health += 15;
                attackDamage += 20;
                armourChoiceLoop = true;

            } else if (armourChoice.equals("turtle")) {
                health += 25;
                attackAgain += 15;
                armourChoiceLoop = true;

            } else {
                System.out.println("Try Again!");
            }
        } while (!armourChoiceLoop);


        GAME:
        while (running) {

            System.out.println("-------------------------------------------");
            if (weaponDrop < 3 && score < 150) {
                level1EnemyHealth = randNum.nextInt(maxEnemyHealth) + 1;
                String enemy = level1Enemies[randNum.nextInt(level1Enemies.length)];
                System.out.println("\t# " + enemy + " appeared!" + " #\n");

                while (level1EnemyHealth > 0) {
                    System.out.println("\tYou're score: " +score);
                    System.out.println("\tYour HP: " + health);
                    System.out.println("\t" + enemy + "'s HP " + level1EnemyHealth);
                    System.out.println("\n\tWhat would you like to do?");
                    System.out.println("\t1. Attack");
                    System.out.println("\t2. Drink Health Potion");
                    System.out.println("\t3. Run!");

                    String input = scanner.nextLine();
                    if (input.equals("1")) {
                        int damageDealt = randNum.nextInt(attackDamage);
                        if (randNum.nextInt(100) < attackAgain) {
                            int damageDealt1 = randNum.nextInt(attackDamage);
                            damageDealt += damageDealt1;
                            System.out.println("Combo! " + damageDealt1 + " Extra Damage!");
                        }


                        int damageTaken = randNum.nextInt(enemyAttackDamage);

                        level1EnemyHealth -= damageDealt;
                        health -= damageTaken;

                        System.out.println("\t> You Strike the " + enemy + " for " + damageDealt + " damage.");
                        System.out.println("\t> You recieve " + damageTaken + " in retaliation!");

                        if (health < 1) {
                            System.out.println("You are too weak to go on!");
                            break;
                        }
                    } else if (input.equals("2")) {

                        if (numHealthPotions > 0) {
                            health += healthPotionHealAmount;
                            numHealthPotions--;
                            System.out.println("\t> You drink a health potion, healing yourself for " + healthPotionHealAmount + "."
                                    + "\n\t> You now have " + health + " HP."
                                    + "\n\t> You have " + numHealthPotions + " health potions left.\n");

                        } else {
                            System.out.println("\t> You have no health potions left! Defeat an enemy for a chance to receive one!");
                        }

                    } else if (input.equals("3")) {
                        System.out.println("\t>You run away from the " + enemy + "!");
                        continue GAME;

                    } else {
                        System.out.println("Invalid Command!");

                    }
                }
                if (health < 1) {
                    System.out.println("You limp out of the dungeon, weak from battle.");
                    break;
                }

                weaponDrop++;
                score += 50;


                System.out.println("---------------------------------------");
                System.out.println(" # " + enemy + " was defeated! # ");
                System.out.println(" # You have " + health + " HP left .  # ");
                if (randNum.nextInt(100) < healthPotionDropChance) {
                    numHealthPotions++;
                    System.out.println(" # The " + enemy + " dropped a health potion! # ");
                    System.out.println(" # You now have " + numHealthPotions + " health potion(s). #");
                }


                System.out.println("-------------------------------------------");
                System.out.println("What would you like to do now?");
                System.out.println("1. Continue fighting");
                System.out.println("2. Exit dungeon");

                String input = scanner.nextLine();

                while (!input.equals("1") && !input.equals("2")) {
                    System.out.println("Invalid command!");
                    input = scanner.nextLine();
                }
                if (input.equals("1")) {
                    System.out.println("You continue on your adventure!");
                } else if (input.equals("2")) {
                    System.out.println("You exit the dungeon, successful from your adventures!");
                    break;
                }
            }

            ////////////////////////////////////////////////////////////////////////


            //////////////////////////////////////////////////////////////////////////////

            else if (weaponDrop >= 3 && score <= 750) {
                boolean weaponDropReady = false;
                if (weaponDrop == 5) {
                    System.out.println("A fellow traveller offers you a sword or teach you karate.");
                    System.out.println("sword or karate");


                    do {
                        String weaponChoice = scanner.nextLine();
                        if (weaponChoice.equals("karate")) {
                            attackAgain += karateBonus;
                            attackDamage -= karateNeg;
                            weaponDropReady = true;
                        } else if (weaponChoice.equals("sword")) {
                            attackDamage += swordBonus;
                            healthPotionHealAmount -= swordNeg;
                            weaponDropReady = true;
                        } else {
                            System.out.println("Invalid Choice!");
                        }
                    } while (!weaponDropReady);
                }

                level2EnemyHealth = randNum.nextInt(maxLevel2EnemyHealth);
                String enemy = level2Enemies[randNum.nextInt(level2Enemies.length)];
                System.out.println("\t# " + enemy + " appeared!" + " #\n");

                while (level2EnemyHealth > 0) {
                    System.out.println("\tYou're score: " + score);

                    System.out.println("\tYour HP: " + health);
                    System.out.println("\t" + enemy + "'s HP " + level2EnemyHealth);
                    System.out.println("\n\tWhat would you like to do?");
                    System.out.println("\t1. Attack");
                    System.out.println("\t2. Drink Health Potion");
                    System.out.println("\t3. Run!");

                    String input = scanner.nextLine();
                    if (input.equals("1")) {
                        int damageDealt = randNum.nextInt(attackDamage);
                        if (randNum.nextInt(100) < attackAgain) {
                            int damageDealt1 = randNum.nextInt(attackDamage);
                            damageDealt += damageDealt1;
                            System.out.println("Combo! " + damageDealt1 + " Extra Damage!");
                        }


                        int damageTaken = randNum.nextInt(level2EnemyAttackDamage);

                        level2EnemyHealth -= damageDealt;
                        health -= damageTaken;

                        System.out.println("\t> You Strike the " + enemy + " for " + damageDealt + " damage.");
                        System.out.println("\t> You recieve " + damageTaken + " in retaliation!");

                        if (health < 1) {
                            System.out.println("You are too weak to go on!");
                            break;
                        }
                    } else if (input.equals("2")) {

                        if (numHealthPotions > 0) {
                            health += healthPotionHealAmount;
                            numHealthPotions--;
                            System.out.println("\t> You drink a health potion, healing yourself for " + healthPotionHealAmount + "."
                                    + "\n\t> You now have " + health + " HP."
                                    + "\n\t> You have " + numHealthPotions + " health potions left.\n");

                        } else {
                            System.out.println("\t> You have no health potions left! Defeat an enemy for a chance to receive one!");
                        }

                    } else if (input.equals("3")) {
                        System.out.println("\t>You run away from the " + enemy + "!");
                        continue GAME;

                    } else {
                        System.out.println("Invalid Command!");

                    }
                }
                if (health < 1) {
                    System.out.println("You limp out of the dungeon, weak from battle.");
                    break;
                }

                System.out.println("---------------------------------------");
                System.out.println(" # " + enemy + " was defeated! # ");
                System.out.println(" # You have " + health + " HP left .  # ");
                if (randNum.nextInt(100) < healthPotionDropChance) {
                    numHealthPotions++;
                    System.out.println(" # The " + enemy + " dropped a health potion! # ");
                    System.out.println(" # You now have " + numHealthPotions + " health potion(s). #");
                }


                weaponDrop++;
                score += 200;
                System.out.println("Score: " + score);

                if (weaponDrop == 6 && score > 650) {
                    System.out.println("Please take this helmet. You're gonna need it!");
                    System.out.println("You take the helmet adding 100 HP");
                    health+=100;
                    System.out.println("If you answer the trolls riddle correctly you receive a secret weapon!");

                    if (heroName.equals("Princess Cynthia")) {
                        System.out.println("Who is Mario's girlfriend?");
                        System.out.println("A. Princess Peach\nB. Princess Diana\nC. Princess Leia");
                        String answer = scanner.nextLine();
                        if (answer.toUpperCase().equals("A")) {

                            System.out.println("Correct!\nHere is your reward!\nPrincess Cynthia is gifted a Unicorn to ride into battle!\nCombo chance increased!");
                            attackAgain += 20;

                        } else {
                            System.out.println("That is wrong!");
                        }
                    }
                    else if (heroName.equals("Prince Theodore")) {
                        System.out.println("What is the name of the green dinosaur that helps Mario on his adventures?");
                        System.out.println("A. Goomba\nB. Little Foot\nC. Yoshi");
                        String answer = scanner.nextLine();
                        if (answer.toUpperCase().equals("C")) {
                            {
                                System.out.println("Correct!\nHere is your reward!\nPrince Theodore is gifted a Dragon to ride into battle!\nCombo chance increased!");
                                attackAgain += 20;
                            }
                        } else {
                            System.out.println("That is wrong!");
                        }
                    }
                    else  if (heroName.equals("Prince Harvey")) {

                        System.out.println("What instrument does Bowser play to impress Princess Peach??");
                        System.out.println("A. Trumpet\nB. Piano\nC. Tuba");

                        String answer = scanner.nextLine();
                        if (answer.toUpperCase().equals("B")) {
                            System.out.println("Correct!\nHere is your reward!\nPrince Theodore is gifted a Dragon to ride into battle!\nCombo chance increased!");
                            attackAgain += 20;
                        } else {
                            System.out.println("That is wrong!");
                        }
                    }
                    else{
                        System.out.println("When is Nathan's birthday?");
                        System.out.println("A. January\nB. June\nC. July");
                        String answer = scanner.nextLine();
                        if(answer.toUpperCase().equals("C")){
                            System.out.println("Take this dragon with you!!!");
                            System.out.println("Combo chance increased!!!");
                        }else{
                            System.out.println("YOU ARE DOOMED!!!\nYou have lost your armour!");
                        }
                    }
                }


                System.out.println("-------------------------------------------");
                System.out.println("What would you like to do now?");
                System.out.println("1. Continue fighting");
                System.out.println("2. Exit dungeon");

                String input = scanner.nextLine();

                while (!input.equals("1") && !input.equals("2")) {
                    System.out.println("Invalid command!");
                    input = scanner.nextLine();
                }
                if (input.equals("1")) {
                    System.out.println("You continue on your adventure!");
                } else if (input.equals("2")) {
                    System.out.println("You exit the dungeon, successful from your adventures!");
                    break;
                }
            }

            ///////////////////////////////////////////////////////////////////////////


            ///////////////////////////////////////////////////////////////////////////


            else if (score > 750) {
                // WELCOME TO BOSS
                if(score==950){
                    attackDamage+=75;
                }
                String []level3Enemies = {"Giant Evil Gorilla", "Giant Evil Hippo","Giant Evil Alligator"};
                int level3EnemyHealth = 700;
                int level3EnemyAttackDamage = level2EnemyAttackDamage+50;



                String enemy = level3Enemies[randNum.nextInt(level3Enemies.length)];
                System.out.println("\t# The Big Bad Boss " + enemy + " has appeared!!!!" + " #\n");

                while (level3EnemyHealth > 0) {


                    System.out.println("\tYour HP: " + health);
                    System.out.println("\t" + enemy + "'s HP " + level3EnemyHealth);
                    System.out.println("\n\tWhat would you like to do?");
                    System.out.println("\t1. Attack");
                    System.out.println("\t2. Drink Health Potion");


                    String input = scanner.nextLine();
                    if (input.equals("1")) {
                        int damageDealt = randNum.nextInt(attackDamage);
                        if (randNum.nextInt(100) < attackAgain) {
                            int damageDealt1 = randNum.nextInt(attackDamage);
                            damageDealt += damageDealt1;
                            System.out.println("Combo! " + damageDealt1 + " Extra Damage!");
                        }


                        int damageTaken = randNum.nextInt(level3EnemyAttackDamage);

                        level3EnemyHealth -= damageDealt;
                        health -= damageTaken;

                        System.out.println("\t> You Strike the " + enemy + " for " + damageDealt + " damage.");
                        System.out.println("\t> You recieve " + damageTaken + " in retaliation!");

                        if (health < 1) {
                            System.out.println("You are too weak to go on!");
                            break;
                        }
                    } else if (input.equals("2")) {

                        if (numHealthPotions > 0) {
                            health += healthPotionHealAmount;
                            numHealthPotions--;
                            System.out.println("\t> You drink a health potion, healing yourself for " + healthPotionHealAmount + "."
                                    + "\n\t> You now have " + health + " HP."
                                    + "\n\t> You have " + numHealthPotions + " health potions left.\n");

                        } else {
                            System.out.println("\t> You have no health potions left! Defeat an enemy for a chance to receive one!");
                        }

                    }else {
                        System.out.println("Invalid Command!");

                    }
                }
                if (health < 1) {
                    System.out.println("You limp out of the dungeon, weak from battle.");
                    break;
                }

                System.out.println("---------------------------------------");
                System.out.println(" # " + enemy + " was defeated! # ");
                System.out.println(" # You have " + health + " HP left .  # ");
                if (randNum.nextInt(100) < healthPotionDropChance) {
                    numHealthPotions++;
                    System.out.println(" # The " + enemy + " dropped a health potion! # ");
                    System.out.println(" # You now have " + numHealthPotions + " health potion(s). #");
                }



                score += 9050;
                System.out.println("Score: " + score + "!!!!!!!!!!!!!!!!!!!!");



                System.out.println("-------------------------------------------");
                System.out.println("You win!");
                break;

            }


        }
        System.out.println("#######################");
        System.out.println("# THANKS FOR PLAYING " + heroName + "! #");
        System.out.println("#######################");
    }


}



