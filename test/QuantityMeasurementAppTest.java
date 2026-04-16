package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.Quantity;
import com.apps.quantitymeasurement.QuantityMeasurementApp.LengthUnit;

public class QuantityMeasurementAppTest {

    // ===== YARD TESTS =====

    @Test
    public void testEquality_YardToYard_SameValue() {
        Quantity q1 = new Quantity(1.0, LengthUnit.YARD);
        Quantity q2 = new Quantity(1.0, LengthUnit.YARD);
        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_YardToYard_DifferentValue() {
        Quantity q1 = new Quantity(1.0, LengthUnit.YARD);
        Quantity q2 = new Quantity(2.0, LengthUnit.YARD);
        assertFalse(q1.equals(q2));
    }

    @Test
    public void testEquality_YardToFeet_EquivalentValue() {
        Quantity q1 = new Quantity(1.0, LengthUnit.YARD);
        Quantity q2 = new Quantity(3.0, LengthUnit.FEET);
        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_FeetToYard_EquivalentValue() {
        Quantity q1 = new Quantity(3.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(1.0, LengthUnit.YARD);
        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_YardToInches_EquivalentValue() {
        Quantity q1 = new Quantity(1.0, LengthUnit.YARD);
        Quantity q2 = new Quantity(36.0, LengthUnit.INCH);
        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_YardToFeet_NonEquivalentValue() {
        Quantity q1 = new Quantity(1.0, LengthUnit.YARD);
        Quantity q2 = new Quantity(2.0, LengthUnit.FEET);
        assertFalse(q1.equals(q2));
    }

    // ===== CM TESTS =====

    @Test
    public void testEquality_CentimeterToCentimeter_SameValue() {
        Quantity q1 = new Quantity(2.0, LengthUnit.CENTIMETER);
        Quantity q2 = new Quantity(2.0, LengthUnit.CENTIMETER);
        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_CentimeterToInches_EquivalentValue() {
        Quantity q1 = new Quantity(1.0, LengthUnit.CENTIMETER);
        Quantity q2 = new Quantity(0.393701, LengthUnit.INCH);
        assertFalse(q1.equals(q2));
    }

    @Test
    public void testEquality_CentimeterToFeet_NonEquivalentValue() {
        Quantity q1 = new Quantity(1.0, LengthUnit.CENTIMETER);
        Quantity q2 = new Quantity(1.0, LengthUnit.FEET);
        assertFalse(q1.equals(q2));
    }

    // ===== TRANSITIVE PROPERTY =====

    @Test
    public void testEquality_MultiUnit_TransitiveProperty() {
        Quantity yard = new Quantity(1.0, LengthUnit.YARD);
        Quantity feet = new Quantity(3.0, LengthUnit.FEET);
        Quantity inch = new Quantity(36.0, LengthUnit.INCH);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inch));
        assertTrue(yard.equals(inch));
    }

    // ===== COMMON EDGE CASES =====

    @Test
    public void testEquality_NullComparison() {
        Quantity q = new Quantity(1.0, LengthUnit.FEET);
        assertFalse(q.equals(null));
    }

    @Test
    public void testEquality_SameReference() {
        Quantity q = new Quantity(1.0, LengthUnit.YARD);
        assertTrue(q.equals(q));
    }

    @Test
    public void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity(1.0, null);
        });
    }
}