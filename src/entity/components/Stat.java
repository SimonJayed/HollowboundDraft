//package entity.components;
//
//public class Stat {
//    private int level;
//    private double exp;
//    private double nextLevelExp;
//
//    private double initialHP;
//    private double initialEnergy;
//
//    private int vit, pow, mag, agi, luck; // Base stats
//
//    public Stat(int level, double initialHP, double initialEnergy, int vit, int pow, int mag, int agi, int luck) {
//        this.level = level;
//        this.exp = level * 10;
//        this.nextLevelExp = exp * 2 + level;
//
//        this.initialHP = initialHP;
//        this.initialEnergy = initialEnergy;
//
//        this.vit = vit;
//        this.pow = pow;
//        this.mag = mag;
//        this.agi = agi;
//        this.luck = luck;
//    }
//
//    // Getters to Calculate Stats Dynamically
//    public double getMaxHP() {
//        return initialHP + (15 * level) + (vit * 2);
//    }
//
//    public double getMaxEnergy() {
//        return initialEnergy + (15 * level) + (mag * 2);
//    }
//
//    public double getManaRegen() {
//        return getMaxEnergy() * 0.1;
//    }
//
//    public double getAttack() {
//        return pow * 2;
//    }
//
//    public double getDefense() {
//        return vit * 2;
//    }
//
//    public double getCrit() {
//        return luck * 0.5;
//    }
//
//    public void levelUp() {
//        level++;
//        exp = 0;
//        nextLevelExp *= 1.5;
//    }
//}
//}
