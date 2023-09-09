/*
 * Jackson, Terrence
 * CMSC 330 Project 1
 * 09.09.2023
 * Summary:
 * Similar to HollowPolygon, but includes a step to fill in the polygon
 */

import java.awt.*;

/*
 * Class that defines all solid polygons
 */
class SolidPolygon extends Polygon_ {

    /*
     * Constructor that calls super constructor
     */
    public SolidPolygon(Color color, int vertexCount) {
        super(color, vertexCount);
    }

    /*
     * Draws and fills solid polygon
     */
    @Override
    public void drawPolygon(Graphics graphics, Polygon polygon) {
        graphics.drawPolygon(polygon);
        graphics.fillPolygon(polygon);
    }
}
