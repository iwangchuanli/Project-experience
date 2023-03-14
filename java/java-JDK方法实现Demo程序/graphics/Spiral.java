/*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
package com.davidflanagan.examples.graphics;
import java.awt.*;
import java.awt.geom.*;

/** This Shape implementation represents a spiral curve */
public class Spiral implements Shape {
    double centerX, centerY;            // The center of the spiral
    double startRadius, startAngle;     // The spiral starting point
    double endRadius, endAngle;         // The spiral ending point
    double outerRadius;                 // the bigger of the two radii
    int angleDirection;                 // 1 if angle increases, -1 otherwise

    /**
     * The constructor.  It takes arguments for the center of the shape, the
     * start point, and the end point.  The start and end points are specified
     * in terms of angle and radius.  The spiral curve is formed by varying
     * the angle and radius smoothly between the two end points.
     **/
    public Spiral(double centerX, double centerY,
		  double startRadius, double startAngle,
		  double endRadius, double endAngle)
    {
	// Save the parameters that describe the spiral
	this.centerX = centerX; 	this.centerY = centerY;
	this.startRadius = startRadius;	this.startAngle = startAngle;
	this.endRadius = endRadius;	this.endAngle = endAngle;

	// figure out the maximum radius, and the spiral direction
	this.outerRadius = Math.max(startRadius, endRadius);
	if (startAngle < endAngle) angleDirection = 1;
	else angleDirection = -1;
	
	if ((startRadius < 0) || (endRadius < 0))
	    throw new IllegalArgumentException("Spiral radii must be >= 0");
    }

    /** 
     * The bounding box of a Spiral is the same as the bounding box of a
     * circle with the same center and the maximum radius
     **/
    public Rectangle getBounds() {
	return new Rectangle((int)(centerX-outerRadius),
			     (int)(centerY-outerRadius),
			     (int)(outerRadius*2), (int)(outerRadius*2));
    }

    /** Same as getBounds(), but with floating-point coordinates */
    public Rectangle2D getBounds2D() {
	return new Rectangle2D.Double(centerX-outerRadius, centerY-outerRadius,
				      outerRadius*2, outerRadius*2);
    }

    /** 
     * A spiral is an open curve, not a not a closed area; it does not have an
     * inside and an outsize, so the contains() methods always return false.
     **/
    public boolean contains(double x, double y) { return false; }
    public boolean contains(Point2D p) { return false; }
    public boolean contains(Rectangle2D r) { return false; }
    public boolean contains(double x, double y, double w, double h) {
	return false;
    }

    /**
     * This method is allowed to approximate if it would be too computationally
     * intensive to determine an exact answer.  Therefore, we check whether
     * the rectangle intersects a circle of the outer radius.  This is a good
     * guess for a tight spiral, but less good for a "loose" spiral. 
     **/
    public boolean intersects(double x, double y, double w, double h) {
	Shape approx = new Ellipse2D.Double(centerX-outerRadius,
					    centerY-outerRadius,
					    outerRadius*2, outerRadius*2);
	return approx.intersects(x, y, w, h);
    }

    /** This version of intersects() just calls the one above */
    public boolean intersects(Rectangle2D r) {
	return intersects(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }

    /**
     * This method is the heart of all Shape implementations.  It returns a
     * PathIterator that describes the shape in terms of the line and curve
     * segments that comprise it.  Our iterator implementation approximates
     * the shape of the spiral using line segments only.  We pass in a
     * "flatness" argument that tells it how good the approximation must be.
     * (smaller numbers mean a better approximation).
     */
    public PathIterator getPathIterator(AffineTransform at) {
	return new SpiralIterator(at, outerRadius/500.0);
    }

    /**
     * Return a PathIterator that describes the shape in terms of line
     * segments only, with an approximation quality specified by flatness.
     **/
    public PathIterator getPathIterator(AffineTransform at, double flatness) {
	return new SpiralIterator(at, flatness);
    }

    /**
     * This inner class is the PathIterator for our Spiral shape.  For
     * simplicity, it does not describe the spiral path in terms of Bezier
     * curve segments, but simply approximates it with line segments.  The
     * flatness property specifies how far the approximation is allowed to
     * deviate from the true curve.
     **/
    class SpiralIterator implements PathIterator {
	AffineTransform transform;    // How to transform generated coordinates
	double flatness;              // How close an approximation
	double angle = startAngle;    // Current angle
	double radius = startRadius;  // Current radius
	boolean done = false;         // Are we done yet?

	/** A simple constructor.  Just store the parameters into fields */
	public SpiralIterator(AffineTransform transform, double flatness) {
	    this.transform = transform;
	    this.flatness = flatness;
	}

	/** 
	 * All PathIterators have a "winding rule" that helps to specify what
	 * is the inside of a area and what is the outside.  If you fill a
	 * spiral (which you're not supposed to do) the winding rule returned
	 * here yields better results than the alternative, WIND_EVEN_ODD
	 **/
	public int getWindingRule() { return WIND_NON_ZERO; }

	/** Returns true if the entire path has been iterated */
	public boolean isDone() { return done; }

	/**
	 * Store the coordinates of the current segment of the path into the
	 * specified array, and return the type of the segment.  Use
	 * trigonometry to compute the coordinates based on the current angle
	 * and radius.  If this was the first point, return a MOVETO segment,
	 * otherwise return a LINETO segment. Also, check to see if we're done.
	 **/
	public int currentSegment(float[] coords) {
	    // given the radius and the angle, compute the point coords
	    coords[0] = (float)(centerX + radius*Math.cos(angle));
	    coords[1] = (float)(centerY - radius*Math.sin(angle));

	    // If a transform was specified, use it on the coordinates
	    if (transform != null) transform.transform(coords, 0, coords, 0,1);

	    // If we've reached the end of the spiral remember that fact
	    if (angle == endAngle) done = true;

	    // If this is the first point in the spiral then move to it
	    if (angle == startAngle) return SEG_MOVETO;

	    // Otherwise draw a line from the previous point to this one
	    return SEG_LINETO;
	}

	/** This method is the same as above, except using double values */
	public int currentSegment(double[] coords) {
	    coords[0] = centerX + radius*Math.cos(angle);
	    coords[1] = centerY - radius*Math.sin(angle);
	    if (transform != null) transform.transform(coords, 0, coords, 0,1);
	    if (angle == endAngle) done = true;
	    if (angle == startAngle) return SEG_MOVETO;
	    else return SEG_LINETO;
	}

	/** 
	 * Move on to the next segment of the path.  Compute the angle and
	 * radius values for the next point in the spiral.
	 **/
	public void next() {
	    if (done) return;

	    // First, figure out how much to increment the angle.  This
	    // depends on the required flatness, and also upon the current
	    // radius.  When drawing a circle (which we'll use as our
	    // approximation) of radius r, we can maintain a flatness f by
	    // using angular increments given by this formula:
	    //      a = acos(2*(f/r)*(f/r) - 4*(f/r) + 1)
	    // Use this formula to figure out how much we can increment the
	    // angle for the next segment.  Note that the formula does not
	    // work well for very small radii, so we special case those.
	    double x = flatness/radius;
	    if (Double.isNaN(x) || (x > .1))
		angle += Math.PI/4*angleDirection; 
	    else {
		double y = 2*x*x - 4*x + 1;
		angle += Math.acos(y)*angleDirection;
	    }
		
	    // Check whether we've gone past the end of the spiral
	    if ((angle-endAngle)*angleDirection > 0) angle = endAngle;

	    // Now that we know the new angle, we can use interpolation to
	    // figure out what the corresponding radius is.
	    double fractionComplete = (angle-startAngle)/(endAngle-startAngle);
	    radius = startRadius + (endRadius-startRadius)*fractionComplete;
	}
    }
}
