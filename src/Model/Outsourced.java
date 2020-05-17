package Model;

public class Outsourced extends Part
{
    //Outsourced Fields
    private String companyName;

    public Outsourced(int partID, String partName, double partPrice, int partInvLevel, int partMin, int partMax, String companyName) {
        super(partID, partName, partPrice, partInvLevel, partMin, partMax);
        this.companyName = companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getCompanyName()
    {
        return companyName;
    }
}
