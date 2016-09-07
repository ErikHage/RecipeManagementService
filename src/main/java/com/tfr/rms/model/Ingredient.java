package com.tfr.rms.model;

/**
 * Created by Erik on 9/3/2016.
 */
public class Ingredient {

    public String name;
    public String measure;
    public double amount;
    public String notes;

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", measure='" + measure + '\'' +
                ", amount=" + amount +
                ", notes='" + notes + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ingredient that = (Ingredient) o;

        if (Double.compare(that.amount, amount) != 0) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (measure != null ? !measure.equals(that.measure) : that.measure != null) return false;
        return notes != null ? notes.equals(that.notes) : that.notes == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + (measure != null ? measure.hashCode() : 0);
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        return result;
    }
}
