public abstract class Entity {
    public String name;
    public boolean state;
    public char gender;

    public Entity(String name, char gender){
        this.name = name;
        this.gender = gender;
    }

    public abstract void talk();

    public static class Human extends Entity{
        public Human(String name, char gender){
            super(name, gender);
        }

        public void talk(){
            System.out.println("CHAEWON NUMBER ONE!!");
        }
    }

    public static class Compy extends Entity{
        public Compy(String name, char gender){
            super(name, gender);
        }

        public void talk(){
            System.out.println("compy mwew mwew krek krek!!");
        }
    }
    public static class Coelacanth extends Entity{
        public Coelacanth(String name, char gender){
            super(name, gender);
        }

        public void talk(){
            System.out.println("silikent blop blop blop chaewonxsimon!!");
        }
    }
    public static class Pterosaur extends Entity{
        public Pterosaur(String name, char gender){
            super(name, gender);
        }

        public void talk(){
            System.out.println("Ptero qwak qwak cak!! cok?");
        }
    }
}
