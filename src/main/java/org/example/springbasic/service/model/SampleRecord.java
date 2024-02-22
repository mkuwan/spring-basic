package org.example.springbasic.service.model;

import org.junit.jupiter.api.Test;

public record SampleRecord(String id, String name) {
}


class SampleRecordCheck {

    @Test
    public void check() {
        SampleRecord sampleRecord = new SampleRecord("1", "name");
        SampleRecord sampleRecord2 = new SampleRecord("1", "name");
        System.out.println(sampleRecord.id());
        System.out.println(sampleRecord.name());

        System.out.println(sampleRecord.toString());
        System.out.println(sampleRecord.hashCode());

        System.out.println(sampleRecord.equals(sampleRecord2));
        System.out.println(sampleRecord == sampleRecord2);
    }
}