/*
 * Jackson, Terrence
 * CMSC 330 Project 1
 * 09.09.2023
 * Summary:
 * Class that defines a solid Parallelogram object
 */

import java.awt.*;

class Parallelogram extends SolidPolygon {

    /*
     * Constructor that initializes the vertices of the Parallelogram
     */
    public Parallelogram(Color color, Point upperLeft, Point lowerRight, int offset) {
        super(color, 4);
        int[] x_points = { upperLeft.x, lowerRight.x + offset, lowerRight.x, upperLeft.x - offset, };
        int[] y_points = { upperLeft.y, upperLeft.y, lowerRight.y, lowerRight.y, };
        createPolygon(x_points, y_points);
    }
}
