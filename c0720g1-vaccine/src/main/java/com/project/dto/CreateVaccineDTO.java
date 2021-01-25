package com.project.dto;

public class CreateVaccineDTO {
    private String nameVaccine;
    private String typeVaccine;
    private String dateRecieve;
    private String licenseCode;
    private String orrigin;
    private String provider;
    private int unitPrice;
    private double dosage;
    private long quantity;
    private String expired;
    private String maintenance;
    private String age;

    public CreateVaccineDTO() {
    }

    public String getNameVaccine() {
        return nameVaccine;
    }

    public void setNameVaccine(String nameVaccine) {
        this.nameVaccine = nameVaccine;
    }

    public String getTypeVaccine() {
        return typeVaccine;
    }

    public void setTypeVaccine(String typeVaccine) {
        this.typeVaccine = typeVaccine;
    }

    public String getDateRecieve() {
        return dateRecieve;
    }

    public void setDateRecieve(String dateRecieve) {
        this.dateRecieve = dateRecieve;
    }

    public String getLicenseCode() {
        return licenseCode;
    }

    public void setLicenseCode(String licenseCode) {
        this.licenseCode = licenseCode;
    }

    public String getOrrigin() {
        return orrigin;
    }

    public void setOrrigin(String orrigin) {
        this.orrigin = orrigin;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getDosage() {
        return dosage;
    }

    public void setDosage(double dosage) {
        this.dosage = dosage;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }

    public String getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(String maintenance) {
        this.maintenance = maintenance;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

