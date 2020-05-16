package Model;

public class Outsourced extends Part {
    //Outsourced Fields
    private String companyName;

    //Outsourced Methods
    public Outsourced(int partID, String partName, double partPrice, int partInvLevel, int partMin, int partMax, String companyName){
        this.setPartID(partID);
        this.setPartName(partName);
        this.setPartPrice(partPrice);
        this.setPartInvLevel(partInvLevel);
        this.setPartMax(partMax);
        this.setPartMin(partMin);
        this.setCompanyName(companyName);
    }

    //Outsourced Setters and Getters
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
