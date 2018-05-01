package instance;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PH-Data™ 01221240053 on 27/04/2018.
 */

public class Hospital implements Serializable{
    private String name;
    private String address;
    private int type;
    private int numberOfAvailableBeds;
    private List<CareUnit> careUnitList;

    public Hospital(){
        careUnitList=new ArrayList();

    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    

    public void setNumberOfAvailableBeds(int numberOfAvailableBeds) {
        this.numberOfAvailableBeds = numberOfAvailableBeds;
    }

    public int getNumberOfAvailableBeds() {
        return numberOfAvailableBeds;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public List<CareUnit> getCareUnitList() {
        return careUnitList;
    }

    public void setCareUnitList(List<CareUnit> careUnitList) {
        this.careUnitList = careUnitList;
    }


}
