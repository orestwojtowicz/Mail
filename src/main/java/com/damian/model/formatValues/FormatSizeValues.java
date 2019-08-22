package com.damian.model.formatValues;



public class FormatSizeValues implements Comparable<FormatSizeValues> {

    private int size;


    public FormatSizeValues(int size) {
        this.size = size;
    }


    @Override
    public String toString() {

        String returnValue;

        if (size <= 0) {
            returnValue = "0";
        } else if (size < 1024) {
            returnValue = size + " B";
        } else if (size < 1048576) {
            returnValue = size / 1024 + " kB";
        } else {
            returnValue = size / 1048576 + " mB";
        }

        return returnValue;
    }

    @Override
    public int compareTo(final FormatSizeValues formatSizeValues) {
        return Integer.compare(this.size, formatSizeValues.size);
    }
}

