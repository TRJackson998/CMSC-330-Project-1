/*
 * Jackson, Terrence
 * CMSC 330 Project 1
 * 09.09.2023
 * Summary:
 * Class that defines a Solid RegularPolygon object
 * uses calculus/geometry to calculate coordinates for any number of sides
 */

import java.awt.*;
import java.lang.Math;

class RegularPolygon extends SolidPolygon {
    /*
     * Constructor that initializes the vertices of the RegularPolygon given the
     * center, number of sides, and radius
     * Uses the understanding that 2*pi is the number of radians to make a full
     * revolution around a circle and that cosine and sine of an angle lead to the x
     * and y coordinates along the circle of radius r
     */
    public RegularPolygon(Color color, Point center, int numSides, int radius) {
        super(color, numSides);

        // init vars
        double angle = (2 * Math.PI) / numSides; // 2pi is full circle, divide by num sides to get the angle between
                                                 // each point
        int[] x_points = new int[numSides + 1]; // init how many x coordinates needed
        int[] y_points = new int[numSides + 1];// init how many y coordinates needed

        int index = 0; // keep track for filling in int[]
        // start at 0; don't go past a full 2pi rotation; get to the next angle
        for (double currentAngle = 0; currentAngle < (2 * Math.PI); currentAngle += angle) {
            // cosine of the angle, offset by radius and center -> x coord
            x_points[index] = (int) (radius * Math.cos(currentAngle) + center.x);
            // sine of the angle, offset by radius and center -> y coord
            y_points[index] = (int) (radius * Math.sin(currentAngle) + center.x);
            index += 1;
        }

        createPolygon(x_points, y_points);
    }
}
