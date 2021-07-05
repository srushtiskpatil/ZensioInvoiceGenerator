package com.zensio.invoice;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.zensio.invoice.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AcceptanceTest {

    @Test
    void invoiceForComplimentaryConferenceRoom() {
        List<BillableResource> usage = List.of(
                new BillableResource(OPEN_SEATS, 2, 5000, 5, 0.18),
                new BillableResource(CABIN_SEATS, 3, 10000, 10, 0.18),
                new BillableResource(HOURS_OF_CONFERENCE_ROOM_USAGE, 35, 200, 0, 0.18),
                new BillableResource(MEALS, 5, 100, 0, 0.12)
        );

        String expectedInvoice = String.join("\n",
                "2 " + OPEN_SEATS + ": 11800",
                "3 " + CABIN_SEATS + ": 35400",
                "35 " + HOURS_OF_CONFERENCE_ROOM_USAGE + ": 0",
                "5 " + MEALS + ": 560",
                TOTAL + ": 47760",
                TOTAL_GST + ": 7260"
        );

        assertEquals(expectedInvoice, new InvoicePrinter().printInvoiceFor(usage));
    }

    @Test
    void invoiceForCabinSeatsPackage() {
        List<BillableResource> usage = List.of(
                new BillableResource(CABIN_SEATS, 1, 10000, 10, 0.18),
                new BillableResource(HOURS_OF_CONFERENCE_ROOM_USAGE, 50, 200, 0, 0.18),
                new BillableResource(MEALS, 10, 100, 0, 0.12)
        );

        String expectedInvoice = String.join("\n",
                "1 " + CABIN_SEATS + ": 11800",
                "50 " + HOURS_OF_CONFERENCE_ROOM_USAGE + ": 9440",
                "10 " + MEALS + ": 1120",
                TOTAL + ": 22360",
                TOTAL_GST + ": 3360"
        );

        assertEquals(expectedInvoice, new InvoicePrinter().printInvoiceFor(usage));
    }

    @Test
    void invoiceForOpenSeatsPackage() {
        List<BillableResource> usage = List.of(
                new BillableResource(OPEN_SEATS, 2, 5000, 5, 0.18),
                new BillableResource(MEALS, 30, 100, 0, 0.12)
        );

        String expectedInvoice = String.join("\n",
                "2 " + OPEN_SEATS + ": 11800",
                "30 " + MEALS + ": 3360",
                TOTAL + ": 15160",
                TOTAL_GST + ": 2160"
        );

        assertEquals(expectedInvoice, new InvoicePrinter().printInvoiceFor(usage));
    }

}
