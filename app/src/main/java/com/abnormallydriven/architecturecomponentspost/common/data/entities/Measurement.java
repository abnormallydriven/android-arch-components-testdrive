package com.abnormallydriven.architecturecomponentspost.common.data.entities;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "measurements", indices = {@Index(value = "measurementDate", unique = true), @Index("userId")},
foreignKeys = @ForeignKey(onUpdate = CASCADE, onDelete = CASCADE, entity = User.class, parentColumns = "id", childColumns = "userId")
)
public class Measurement {

    @PrimaryKey(autoGenerate = true)
    private long id;

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

    private long userId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getLowerLeftBicep() {
        return lowerLeftBicep;
    }

    public void setLowerLeftBicep(float lowerLeftBicep) {
        this.lowerLeftBicep = lowerLeftBicep;
    }

    public float getLowerRightBicep() {
        return lowerRightBicep;
    }

    public void setLowerRightBicep(float lowerRightBicep) {
        this.lowerRightBicep = lowerRightBicep;
    }

    public float getLeftBicep() {
        return leftBicep;
    }

    public void setLeftBicep(float leftBicep) {
        this.leftBicep = leftBicep;
    }

    public float getRightBicep() {
        return rightBicep;
    }

    public void setRightBicep(float rightBicep) {
        this.rightBicep = rightBicep;
    }

    public float getLeftCalf() {
        return leftCalf;
    }

    public void setLeftCalf(float leftCalf) {
        this.leftCalf = leftCalf;
    }

    public float getRightCalf() {
        return rightCalf;
    }

    public void setRightCalf(float rightCalf) {
        this.rightCalf = rightCalf;
    }

    public float getChest() {
        return chest;
    }

    public void setChest(float chest) {
        this.chest = chest;
    }

    public float getLeftForearm() {
        return leftForearm;
    }

    public void setLeftForearm(float leftForearm) {
        this.leftForearm = leftForearm;
    }

    public float getRightForearm() {
        return rightForearm;
    }

    public void setRightForearm(float rightForearm) {
        this.rightForearm = rightForearm;
    }

    public float getHighHip() {
        return highHip;
    }

    public void setHighHip(float highHip) {
        this.highHip = highHip;
    }

    public float getHip() {
        return hip;
    }

    public void setHip(float hip) {
        this.hip = hip;
    }

    public float getLowerLeftThigh() {
        return lowerLeftThigh;
    }

    public void setLowerLeftThigh(float lowerLeftThigh) {
        this.lowerLeftThigh = lowerLeftThigh;
    }

    public float getLowerRightThigh() {
        return lowerRightThigh;
    }

    public void setLowerRightThigh(float lowerRightThigh) {
        this.lowerRightThigh = lowerRightThigh;
    }

    public float getMidLeftThigh() {
        return midLeftThigh;
    }

    public void setMidLeftThigh(float midLeftThigh) {
        this.midLeftThigh = midLeftThigh;
    }

    public float getMidRightThigh() {
        return midRightThigh;
    }

    public void setMidRightThigh(float midRightThigh) {
        this.midRightThigh = midRightThigh;
    }

    public float getUpperLeftThigh() {
        return upperLeftThigh;
    }

    public void setUpperLeftThigh(float upperLeftThigh) {
        this.upperLeftThigh = upperLeftThigh;
    }

    public float getUpperRightThigh() {
        return upperRightThigh;
    }

    public void setUpperRightThigh(float upperRightThigh) {
        this.upperRightThigh = upperRightThigh;
    }

    public float getAbdominalWaist() {
        return abdominalWaist;
    }

    public void setAbdominalWaist(float abdominalWaist) {
        this.abdominalWaist = abdominalWaist;
    }

    public float getLowerWaist() {
        return lowerWaist;
    }

    public void setLowerWaist(float lowerWaist) {
        this.lowerWaist = lowerWaist;
    }

    public float getNarrowestWaist() {
        return narrowestWaist;
    }

    public void setNarrowestWaist(float narrowestWaist) {
        this.narrowestWaist = narrowestWaist;
    }

    public Date getMeasurementDate() {
        return measurementDate;
    }

    public void setMeasurementDate(Date measurementDate) {
        this.measurementDate = measurementDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
