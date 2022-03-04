package org.example;

public class Jersey {
    private String size;
    private String brand;
    private String colour;

    public Jersey( String size, String brand, String colour) {
        this.size = size;
        this.brand = brand;
        this.colour = colour;
    }


    public String getSize() {
        return size;
    }

    public String getBrand() {
        return brand;
    }

    public String getColour() {
        return colour;
    }


    public void setSize(String size) {
        this.size = size;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "Jersey{" +
                ", size=" + size +
                ", brand='" + brand + '\'' +
                ", colour='" + colour + '\'' +
                '}';
    }
}
