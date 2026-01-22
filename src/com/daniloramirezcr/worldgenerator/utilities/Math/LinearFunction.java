package com.daniloramirezcr.worldgenerator.utilities.Math;

public class LinearFunction {
    double slope;
    double yIntercept;
    boolean isVertical; // Flag for vertical lines

    public LinearFunction(Point p1, Point p2) {
        if (p1.x == p2.x) {
            // Handle vertical lines
            this.isVertical = true;
            this.slope = Double.NaN; // Not a number
            this.yIntercept = Double.NaN;
        } else {
            this.isVertical = false;
            // Calculate slope: m = (y2 - y1) / (x2 - x1)
            this.slope = (p2.y - p1.y) / (p2.x - p1.x);
            // Calculate y-intercept: b = y1 - m*x1
            this.yIntercept = p1.y - this.slope * p1.x;
        }
    }

    /**
     * Returns the y value for a given x value using the linear function.
     */
    public double getY(double x) {
        if (isVertical) {
            throw new UnsupportedOperationException("Cannot get Y for a vertical line equation.");
        }
        return this.slope * x + this.yIntercept;
    }

    @Override
    public String toString() {
        if (isVertical) {
            return "x = " + (int) yIntercept; // For vertical lines, it's x = constant (the x-coordinate)
        }
        return String.format("y = %.2fx + %.2f", this.slope, this.yIntercept);
    }
}