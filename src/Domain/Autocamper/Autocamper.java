package Domain.Autocamper;

/**
 * Created by Lukas
 * 08-04-2019.
 */
public abstract class Autocamper {


    public String getVINNumber() {
        return VINNumber;
    }

    private int modelYear;
    private String kitchenFacilityType;
    private int numberOfBeds;
    private String heatingSystem;
    private String size;
    private String type;
    private String price;

    public int getModelYear() {
        return modelYear;
    }

    public String getKitchenFacilityType() {
        return kitchenFacilityType;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public String getHeatingSystem() {
        return heatingSystem;
    }

    public String getSize() {
        return size;
    }

    public String getDescription() {
        return description;
    }

    private String description;
    public String VINNumber;



}
