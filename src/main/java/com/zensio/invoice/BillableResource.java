package com.zensio.invoice;

public class BillableResource {

    private final String resourceName;
    private final Integer unitsUsed;
    private final Integer pricePerUnit;

    private final Integer freeConferenceHoursPerUnit;
    private final Double gstRate;


    public BillableResource(String resourceName, Integer unitsUsed, Integer pricePerUnit, Integer freeConferenceHoursPerUnit, Double gstRate) {
        this.unitsUsed = unitsUsed;
        this.pricePerUnit = pricePerUnit;
        this.resourceName = resourceName;
        this.freeConferenceHoursPerUnit = freeConferenceHoursPerUnit;
        this.gstRate = gstRate;
    }

    public Integer unitsUsed() {
        return this.unitsUsed;
    }

    public Integer calculateFreeConferenceHours() {
        return this.unitsUsed * this.freeConferenceHoursPerUnit;
    }

    public String resourceName() {
        return this.resourceName;
    }

    public Double calculateBill() {
        return this.unitsUsed * this.pricePerUnit * (1 + this.gstRate);
    }

    public Double calculateGst() {
        return this.unitsUsed * this.pricePerUnit * this.gstRate;
    }

    public BillableResource applyDiscount(Integer complimentaryUnits) {
        return new BillableResource(this.resourceName, Math.max(0, this.unitsUsed - complimentaryUnits), this.pricePerUnit, this.freeConferenceHoursPerUnit, this.gstRate);
    }

    public String printBill(Integer consumedUnits) {
        return String.format("%s %s: %s", consumedUnits, this.resourceName, calculateBill().intValue());
    }
}
