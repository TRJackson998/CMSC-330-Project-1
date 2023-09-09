/*
 * Jackson, Terrence
 * CMSC 330 Project 1
 * 09.09.2023
 * Summary:
 * Class that defines a Text object
 */

import java.awt.*;

class Text_ extends Image {

    private Point vertex;
    private String text;
    private Color color;

    /*
     * Constructor that initializes the vertices, text, and color of the text
     */
    public Text_(Color color, Point vertex, String text) {
        super(color);
        this.color = color;
        this.vertex = vertex;
        this.text = text;
    }

    /*
     * Sets correct color and draws text object
     */
    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(color);
        graphics.drawString(text, vertex.x, vertex.y);
    }
}
