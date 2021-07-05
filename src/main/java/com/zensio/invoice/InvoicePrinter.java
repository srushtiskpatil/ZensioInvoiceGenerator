package com.zensio.invoice;

import java.util.List;

import static com.zensio.invoice.Constants.*;
import static com.zensio.invoice.Constants.TOTAL;
import static com.zensio.invoice.Constants.TOTAL_GST;

public class InvoicePrinter {

    public String printInvoiceFor(List<BillableResource> billableResources) {
        Double total = 0.0;
        Double totalGst = 0.0;
        StringBuilder resourcesInvoiceBuilder = new StringBuilder();
        Integer freeConferenceHours = billableResources.stream().mapToInt(br -> br.calculateFreeConferenceHours().intValue()).sum();
        for (BillableResource billableResource : billableResources) {
            if (HOURS_OF_CONFERENCE_ROOM_USAGE.equals(billableResource.resourceName())){
                BillableResource discountedResource = billableResource.applyDiscount(freeConferenceHours);
                total += discountedResource.calculateBill();
                totalGst += discountedResource.calculateGst();
                resourcesInvoiceBuilder.append(discountedResource.printBill(billableResource.unitsUsed())).append("\n");
            }else {
                total += billableResource.calculateBill();
                totalGst += billableResource.calculateGst();
                resourcesInvoiceBuilder.append(billableResource.printBill(billableResource.unitsUsed())).append("\n");
            }
        }
        resourcesInvoiceBuilder.append(String.format("%s: %s", TOTAL, total.intValue())).append("\n");
        resourcesInvoiceBuilder.append(String.format("%s: %s", TOTAL_GST, totalGst.intValue()));
        return resourcesInvoiceBuilder.toString();
    }
}
