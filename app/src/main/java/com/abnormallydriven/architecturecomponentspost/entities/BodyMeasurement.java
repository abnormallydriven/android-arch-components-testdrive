package com.abnormallydriven.architecturecomponentspost.entities;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(indices = {@Index(value = "measurementDate", unique = true), @Index("userId")},
foreignKeys = @ForeignKey(onUpdate = CASCADE, onDelete = CASCADE, entity = User.class, parentColumns = "id", childColumns = "userId")
)
public class BodyMeasurement {

    @PrimaryKey(autoGenerate = true)
    private int id;


    private float lowerLeftBicep;

    private float lowerRightBicep;

    private float leftBicep;

    private float rightBicep;

    private float leftCalf;

    private float rightCalf;

    private float chest;

    private float leftForearm;

    private float rightForearm;

    private float highHip;

    private float hip;

    private float lowerLeftThigh;

    private float lowerRightThigh;

    private float midLeftThigh;

    private float midRightThigh;

    private float upperLeftThigh;

    private float upperRightThigh;

    private float abdominalWaist;

    private float lowerWaist;

    private float narrowestWaist;

    private Date measurementDate;

    private int userId;

}
