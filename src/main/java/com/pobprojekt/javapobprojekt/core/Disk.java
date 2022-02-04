package com.pobprojekt.javapobprojekt.core;

public class Disk extends Memory {
    private StorageType storageType;

    public Disk(String name, String manufacturer, double price, int releaseYear, double capacity, StorageType storageType) {
        super(name, manufacturer, price, releaseYear, capacity);
        this.classType = ClassType.Disk;

        this.storageType = storageType;
    }

    public StorageType getStorageType() {
        return storageType;
    }

    public void setStorageType(StorageType storageType) {
        this.storageType = storageType;
    }

    @Override
    public String getAdditionalInfo() {
        return super.getAdditionalInfo() +
                ", rodzaj dysku: " + getStorageType().toString();
    }

    @Override
    public float getRating() {
        float storageTypeMultiplier = storageType == StorageType.HDD ? 0.5f : 1;
        float capacityRating = (float)Math.max(Math.min(getCapacity() / 128, 6), 10);

        return capacityRating * storageTypeMultiplier;
    }
}
