package instance;

import java.io.Serializable;

/**
 * Created by PH-Data™ 01221240053 on 27/04/2018.
 */

public class CareUnit implements Serializable{
    private String name;
    private int numberOfAvailableBeds;
    private int type;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setNumberOfAvailableBeds(int numberOfAvailableBeds) {
        this.numberOfAvailableBeds = numberOfAvailableBeds;
    }

    public int getNumberOfAvailableBeds() {
        return numberOfAvailableBeds;
    }
}
