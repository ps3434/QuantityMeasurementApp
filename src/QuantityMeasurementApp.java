package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public enum LengthUnit {
        FEET(1.0),
        INCHES(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(1.0 / 30.48);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
        }
    }

    public static class Length {
        private final double value;
        private final LengthUnit unit;

        public Length(double value, LengthUnit unit) {
            if (unit == null || !Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid input");
            }
            this.value = value;
            this.unit = unit;
        }

        public double toFeet() {
            return unit.toFeet(value);
        }

        public Length convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }
            double feet = toFeet();
            return new Length(targetUnit.fromFeet(feet), targetUnit);
        }

        public Length add(Length other) {
            return add(other, this.unit);
        }

        public Length add(Length other, LengthUnit targetUnit) {
            if (other == null || targetUnit == null) {
                throw new IllegalArgumentException("Invalid input");
            }

            double sumFeet = this.toFeet() + other.toFeet();
            double result = targetUnit.fromFeet(sumFeet);

            return new Length(result, targetUnit);
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    public static Length demonstrateLengthAddition(
            Length l1, Length l2, LengthUnit targetUnit) {
        return l1.add(l2, targetUnit);
    }

    public static void main(String[] args) {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        System.out.println("FEET  : " + l1.add(l2, LengthUnit.FEET));
        System.out.println("INCHES: " + l1.add(l2, LengthUnit.INCHES));
        System.out.println("YARDS : " + l1.add(l2, LengthUnit.YARDS));
        System.out.println("CM    : " + l1.add(l2, LengthUnit.CENTIMETERS));
    }
}