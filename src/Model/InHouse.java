package Model;

public class InHouse extends Part {
    //InHouse Fields
    private int machineId;

    //InHouse Constructors
    public InHouse(int partID, String partName,  double partPrice, int partInvLevel, int partMin, int partMax, int machineId){
        super(partID, partName, partPrice, partInvLevel, partMin, partMax);
        this.machineId = machineId;
    }

    //InHouse Getter and Setter
    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
