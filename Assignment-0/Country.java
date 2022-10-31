import java.io.File;
import java.util.Random;
import java.io.IOException;
import java.io.FileWriter;


// Time Class
 class Time {
    private int time;

    // Constructor
    public Time(int time)
    {
        this.time = time;
    }

    // Function to get time
    public int get_time()
    {
        return time;
    }

//    Function to set time
    public void set_time(int time)
    {
        this.time = time;
    }
}


//  Sensor Class
class Sensor {
    private double probability;

    // Function to get probability
    public double getProbability() {
        return probability;
    }

    // Function to set probability
    public void setProbability(double probability) {
        this.probability = probability;
    }

    // sensor where infiltrator is standing
    private int sensor_pos;

    // Sensor in front of the infiltrator
    private int sensor_front;

    // Sensor in front left of infiltrator
    private int sensor_front_left;

    // Sensor in front right of infiltrator
    private int sensor_front_right;

    // Function to take decision
    public void takeDecision(){
        double result_pos =  Math.random();
        double result_front =  Math.random();
        double result_front_left =  Math.random();
        double result_front_right =  Math.random();

        if (result_pos<probability){
            setSensor_pos(1);
        }
        else {
            setSensor_pos(0);
        }

        if (result_front<probability){
            setSensor_front(1);
        }
        else {
            setSensor_front(0);
        }

        if (result_front_left<probability){
            setSensor_front_left(1);
        }
        else {
            setSensor_front_left(0);
        }

        if (result_front_right<probability){
            setSensor_front_right(1);
        }
        else {
            setSensor_front_right(0);
        }
    }

    public int getSensor_pos()
    {
        return sensor_pos;
    }

    public void setSensor_pos(int sensor_pos)
    {
        this.sensor_pos = sensor_pos;
    }

    public int getSensor_front()
    {
        return sensor_front;
    }

    public void setSensor_front(int sensor_front)
    {
        this.sensor_front = sensor_front;
    }

    public int getSensor_front_left()
    {
        return sensor_front_left;
    }

    public void setSensor_front_left(int sensor_front_left)
    {
        this.sensor_front_left = sensor_front_left;
    }

    public int getSensor_front_right()
    {
        return sensor_front_right;
    }

    public void setSensor_front_right(int sensor_front_right)
    {
        this.sensor_front_right = sensor_front_right;
    }
}


// Border Class


class Border {
    private int width;
    private int len;


    // Function to get the width of Border
    public int get_width()
    {
        return width;
    }

    // Function to set the width of Border
    public void set_width(int width)
    {
        this.width = width;
    }

    // Function to set the length of Border (Hardcode to 1000)
    public void set_len()
    {
        this.len = 1000; // Hardcoded
    }
}


// Infiltrator Class
class Infiltrator {

     //  To measure the step taken by infiltrator
    private int vert_step;

//    Function to get the vertical step
    public int get_vert_step() {
        return vert_step;
    }

    //    Function to set the vertical step
    public void set_vert_step(int vert_step) {
        this.vert_step = vert_step;
    }

}


public class Country {
    public static void main(String[] args) throws IOException {

        File outputFile = new File("output.txt");

        if (outputFile.createNewFile()){
            System.out.print("A new file created : " + outputFile.getName() + "\n");
        }
        else{
            System.out.print("File Exist Already. \n");
        }

        FileWriter writer = new FileWriter("output.txt");

        double probability = 0;
        while(probability <= 0.95){

            // Initializing the width of border with 5
            int wid = 5;

            while(wid<=100){

                // Creating border object
                Border border = new Border();
                border.set_width(wid);
                border.set_len();

                // Creating Sensor object
                Sensor sensor = new Sensor();
                sensor.setProbability(probability);

                // Creating Sensor Object
                Infiltrator infiltrator = new Infiltrator();
                infiltrator.set_vert_step(1);

                // starting time is 0
                Time time = new Time(0);

                // Loop will run while infiltrator don't enter the defending country
                while ((infiltrator.get_vert_step() != border.get_width()+1)){
                    if (time.get_time()%10==0) {
                        sensor.takeDecision();
                        if (sensor.getSensor_pos() == 0) {
                            if(infiltrator.get_vert_step() == border.get_width()){
                                infiltrator.set_vert_step(infiltrator.get_vert_step() + 1);
                                time.set_time(time.get_time()+10);
                                break;
                            }
                            if (sensor.getSensor_front() == 0 || sensor.getSensor_front_left() == 0 || sensor.getSensor_front_right() == 0) {
                                infiltrator.set_vert_step(infiltrator.get_vert_step() + 1);
                            }
                        }
                    }
                    time.set_time(time.get_time()+1);
                }
                System.out.print(time.get_time() + "\n");
                writer.write(String.format("%d %f %d \n",border.get_width(),sensor.getProbability(),time.get_time()));
                wid += 5;
            }

            probability += 0.05;
        }

        // File closed
        writer.close();
    }
}
