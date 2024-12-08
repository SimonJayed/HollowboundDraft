package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public String name;
    public String gender;
    public String race;

    public int worldX, worldY, speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;
    public boolean collisionOn;

    public Entity(){}

    public Entity(String name){
        this.name = name;
    }

    public String toString() {
        return null;
    }

    public static class Human extends Entity{
        public Human(String name, String gender, String race){
            super(name);
            this.gender = gender;
            this.race = race;
        }

        public String toString(){
            return "CHAEWON NUMBER ONE!!";
        }
    }

    public static class Compy extends Entity{
        public Compy(String name, String gender, String race){
            super(name);
            this.gender = gender;
            this.race = race;
        }

        public String toString(){
            return "Compy mwew mwew krek krek!!";
        }
    }
    public static class Coelacanth extends Entity{
        public Coelacanth(String name, String gender, String race){
            super(name);
            this.gender = gender;
            this.race = race;
        }

        public String toString(){
            return "Silikent blop blop chaewonxsimon!!";
        }
    }
    public static class Pterosaur extends Entity{
        public Pterosaur(String name, String gender, String race){
            super(name);
            this.gender = gender;
            this.race = race;
        }

        public String toString(){
            return "Ptero qwak qwak cak!! cok?";
        }
    }
}
