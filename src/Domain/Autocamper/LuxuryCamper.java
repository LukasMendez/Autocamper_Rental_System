package Domain.Autocamper;

/**
 * Created by Lukas
 * 08-04-2019.
 */
public class LuxuryCamper extends Autocamper {

    private int modelYear;
    private String kitchenFacilityType;
    private int numberOfBeds;
    private String heatingSystem;
    private String size;
    private String description;

    public String getType() {
        return type;
    }

    private String type = "Luxury";

    public String getPrice() {
        return price;
    }

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

    public LuxuryCamper(String VINNumber, int modelYear, String heatingSystem, String size, String description, int numberOfBeds, String type, String price)
    {
        super();
        this.VINNumber = VINNumber;
        this.modelYear = modelYear;
        this.heatingSystem = heatingSystem;
        this.size = size;
        this.description = description;
        this.numberOfBeds = numberOfBeds;
        this.price = price;

    }

    public String getVINNumber() {
        return VINNumber;
    }

    private String VINNumber;





}