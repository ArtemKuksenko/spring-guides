package FuelControl;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;

@JsonAutoDetect
public class Car {
    public String model;
    public int year;
    public float motorVolume;
    public int mileageStart;
    public ArrayList<Integer> money;
    public ArrayList<Integer> liter;
    public ArrayList<Integer> mileage;
    Car() { }
}
