package fr.rtgrenoble.velascof.simtelui.model;

public class ModulationListItem {

    private String name;
    private double value;
    private boolean valid;

    public ModulationListItem(String prefix, int ordre, int i, double value) {
        this.name = String.format(prefix + "%" + String.valueOf((int) (Math.log(ordre) / Math.log(2))) + "s", Integer.toBinaryString(i)).replace(' ', '0') + " :";
        this.value = value;
        this.valid = false;
    }

    public ModulationListItem(String prefix, int ordre, int i) {
        this(prefix, ordre, i, 0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
