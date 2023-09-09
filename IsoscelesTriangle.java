/*
 * Jackson, Terrence
 * CMSC 330 Project 1
 * 09.09.2023
 * Summary:
 * Class that defines a solid IsoscelesTriangle object
 */

import java.awt.*;

class IsoscelesTriangle extends SolidPolygon {

    /*
     * Constructor that initializes the vertices of the IsoscelesTriangle
     */
    public IsoscelesTriangle(Color color, Point topVertex, int height, int width) {
        super(color, 3);
        int[] x_points = { topVertex.x, topVertex.x - (width / 2), topVertex.x + (width / 2) };
        int[] y_points = { topVertex.y, topVertex.y + height, topVertex.y + height };
        createPolygon(x_points, y_points);
    }
}
