package com.example.mahmoudrawy.vibrant.Views;

/**
 * Created by Tarek on 4/23/2018.
 */
public class ListWord {
    private String vHospitalName;
    private String vHospitalLocation;
    private String vHospitalAvailablelBeds;
    private int vHospitalImage;

    public ListWord(String HospitalName, String HospitalLocation, String HospitalAvailablelBeds, int HospitalImage) {
        vHospitalName = HospitalName;
        vHospitalLocation = HospitalLocation;
        vHospitalAvailablelBeds = HospitalAvailablelBeds;
        vHospitalImage = HospitalImage;
    }

    public String getvHospitalName() {
        return vHospitalName;
    }

    public String getvHospitalLocation() {
        return vHospitalLocation;
    }

    public String getvHospitalAvailablelBeds() {
        return vHospitalAvailablelBeds;
    }

    public int getvHospitalImage(){ return vHospitalImage; }
}
