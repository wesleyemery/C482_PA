package Model;

public abstract class Part {
    //Part Fields
    private int partID;
    private String partName;
    private double partPrice =0.0;
    private int partInvLevel;
    private int partMin;
    private int partMax;

    public Part(int partID, String partName, double partPrice, int partInvLevel, int partMin, int partMax)
        {
        this.partID = partID;
        this.partInvLevel = partInvLevel;
        this.partMin = partMin;
        this.partMax = partMax;
        this.partName = partName;
        this.partPrice = partPrice;
        }


    //Part Getters and Setters

    public int getPartID() {
        return partID;
    }

    public void setPartID(int partID) {
        this.partID = partID;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public double getPartPrice() {
        return partPrice;
    }

    public void setPartPrice(double partPrice) {
        this.partPrice = partPrice;
    }

    public int getPartInvLevel() {
        return partInvLevel;
    }

    public void setPartInvLevel(int partInvLevel) {
        this.partInvLevel = partInvLevel;
    }

    public int getPartMin() {
        return partMin;
    }

    public void setPartMin(int partMin) {
        this.partMin = partMin;
    }

    public int getPartMax() {
        return partMax;
    }

    public void setPartMax(int partMax) {
        this.partMax = partMax;
    }


}
