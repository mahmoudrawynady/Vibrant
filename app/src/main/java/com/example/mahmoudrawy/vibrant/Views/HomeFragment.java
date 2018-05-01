package com.example.mahmoudrawy.vibrant.Views;

import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.mahmoudrawy.vibrant.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import instance.CareUnit;
import instance.Hospital;
import util.Constants;

public class HomeFragment extends Fragment {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<Hospital> hospitalList;
    private List<CareUnit> careUnitList;
    private ListView hospitalListView;
    private int generalAvailableBeds;
    private int specialAvailableBeds;
    private ProgressBar progressBar;
    private View view;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view==null) {
            view = inflater.inflate(R.layout.fragment_home, container, false);
        }
        hospitalListView = (ListView) view.findViewById(R.id.home_listview);
        progressBar= (ProgressBar) view.findViewById(R.id.home_progress);
        initializeLists();
        initializeFireBaseInstances();
        getHospitalsInfo();
        return view;
    }

    public void initializeLists() {
        this.careUnitList = new ArrayList<>();
        this.hospitalList = new ArrayList<>();
    }

    public void getMainHospitalData(DataSnapshot data, Hospital hospital) {
        String hospitalName = data.child(Constants.NAME_TAG).getValue(String.class);
        String hospitalAddress = data.child(Constants.GOVERNORATE_TAG).getValue(String.class);
        int hospitalType = data.child(Constants.TYPE_TAG).getValue(Integer.class);
        hospital.setName(hospitalName);
        hospital.setAddress(hospitalAddress);
        hospital.setType(hospitalType);
    }


    public void getGeneralCareUnits(DataSnapshot generalCareUnitSnapShot) {
        for (DataSnapshot general : generalCareUnitSnapShot.getChildren()) {
            int state = general.child(Constants.STATE_TAG).getValue(Integer.class);
            if (state == 0)
                generalAvailableBeds++;
        }
    }

    public void getSpecialCareUnits(DataSnapshot specialCareUnitSnapShot) {
        for (DataSnapshot special : specialCareUnitSnapShot.getChildren()) {
            CareUnit careUnit = new CareUnit();
            String careName = special.getKey();
            careUnit.setName(careName);
            DataSnapshot bed = special.child(Constants.BED_TAG);
            for (DataSnapshot snapshot : bed.getChildren()) {
                int state = snapshot.child(Constants.STATE_TAG).getValue(Integer.class);
                if (state == 0)
                    specialAvailableBeds++;
            }
            careUnitList.add(careUnit);
        }
    }

    public void initializeFireBaseInstances() {
        this.firebaseDatabase = FirebaseDatabase.getInstance();
        this.databaseReference = firebaseDatabase.getReference();
    }

    public void getHospitalsInfo() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot hospitalDataSnapshot = dataSnapshot.child(Constants.HOSPITAL_TAG);
                for (DataSnapshot data : hospitalDataSnapshot.getChildren()) {
                    generalAvailableBeds = 0;
                    specialAvailableBeds = 0;
                    Hospital hospital = new Hospital();
                    getMainHospitalData(data, hospital);
                    CareUnit generalCareUnit = new CareUnit();
                    generalCareUnit.setName(getString(R.string.general_care_unit));
                    DataSnapshot generalCareUnitSnapShot = data.child(Constants.GENERAL_CARE_TAG).child(Constants.BED_TAG);
                    DataSnapshot specialCareUnitSnapShot = data.child(Constants.SPECIAL_CARE_TAG).child(Constants.TYPE_TAG);
                    getGeneralCareUnits(generalCareUnitSnapShot);
                    generalCareUnit.setNumberOfAvailableBeds(generalAvailableBeds);
                    getSpecialCareUnits(specialCareUnitSnapShot);
                    careUnitList.add(generalCareUnit);
                    hospital.setNumberOfAvailableBeds(generalAvailableBeds + specialAvailableBeds);
                    hospital.setCareUnitList(careUnitList);
                    hospitalList.add(hospital);
                }
                ListWordAdapter listWordAdapter = new ListWordAdapter(getActivity().getApplicationContext(),
                        hospitalList, R.color.homeColor);
                hospitalListView.setAdapter(listWordAdapter);
               progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
