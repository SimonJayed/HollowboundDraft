    package entity;

    import main.GamePanel;


    public class NPC_OldMan extends Entity {

        public NPC_OldMan(GamePanel gp) {
            super(gp);

            name = "Gary";
            speed = 1;
            maxLife = 10;
            life = maxLife;

            getImage();
            setDialogue();

            this.solidArea.x = 8;
            this.solidArea.y = 16;
            this.solidArea.width = 32;
            this.solidArea.height = 32;
            this.solidAreaDefaultX = this.solidArea.x;
            this.solidAreaDefaultY = this.solidArea.y;
        }

        public void getImage() {
            up1 = setup("/npc/oldman_up_1");
            up2 = setup("/npc/oldman_up_2");
            down1 = setup("/npc/oldman_down_1");
            down2 = setup("/npc/oldman_down_2");
            left1 = setup("/npc/oldman_left_1");
            left2 = setup("/npc/oldman_left_2");
            right1 = setup("/npc/oldman_right_1");
            right2 = setup("/npc/oldman_right_2");
        }

        public void setAction() {
            actionLockCounter++;

            if (actionLockCounter >= 120) {
                int i = gp.randomize(100);

                if (i <= 25) {
                    direction = "up";
                } else if (i > 25 && i <= 50) {
                    direction = "down";
                } else if (i > 50 && i <= 75) {
                    direction = "left";
                } else if (i > 75 && i <= 100) {
                    direction = "right";
                }
                actionLockCounter = 0;
            }
        }

        public int collideEntity(int collideCounter){
            collideCounter++;
            System.out.println("Collide: " + collideCounter);

            if (collideCounter <= 200) {
                gp.ui.showMessage("Wtf, man");
            } else if (collideCounter <= 600) {
                if (collideCounter >= 350 && collideCounter <= 450){
                    gp.ui.showMessage("This is the last warning.");
                }
                else if(collideCounter >= 450){
                    gp.ui.showMessage("...");
                }
                else{
                    gp.ui.showMessage("Dude, stop.");
                }
            } else {
                collideCounter = 800;
                gp.ui.showMessage("I'M GONNA TOUCH YOU!!");
            }
            return collideCounter;
        }

        public void setDialogue(){
            dialogues[0] = "Hello, lad.";
            dialogues[1] = "You seem lost.";
            dialogues[2] = "I can fix that.";
            dialogues[3] = "No. Seriously, dude... \nI can.";
            dialogues[4] = "Why tf does no one believe what I \nsay?";
        }

        public void speak(){
            super.speak();
        }
    }
