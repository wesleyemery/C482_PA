package Model;

public class InHouse extends Part{
    //InHouse Fields
    private int machineId;

    //InHouse Constructors
    public InHouse(int partID, String partName, int partInvLevel, double partPrice, int partMin, int partMax, int machineId){
        this.setPartID(partID);
        this.setPartName(partName);
        this.setPartPrice(partPrice);
        this.setPartInvLevel(partInvLevel);
        this.setPartMax(partMax);
        this.setPartMin(partMin);
        this.setMachineId(machineId);
    }

    //InHouse Getter and Setter
    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
