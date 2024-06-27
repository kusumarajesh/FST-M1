package Activities;




public class Activity1 {

    public static void main(String[] args)
    {
        int make=2014;
        String color="Black";
        String transmission="Manual";
        Car obj=new Car();
        obj.displayCharacteristics();
        //System.out.println(smthng);
        obj.accelerate();
        obj.brake();


    }

    public static class Car{
        public void displayCharacteristics(){
            System.out.println("fu");
            //return "udiew";
        }
        public void accelerate(){
            System.out.println("uhijl");
        }
        public void brake(){
            System.out.println("gfhgg");
        }

    }
}

