package me.kevint.flipIt.math;

import java.awt.Rectangle;

public class MathUtil {
	
	public static boolean intersection(Rectangle r1, Rectangle r2,
	        Rectangle out) {
	    int xmin = Math.max(r1.x, r2.x);
	    int xmax1 = r1.x + r1.width;
	    int xmax2 = r2.x + r2.width;
	    int xmax = Math.min(xmax1, xmax2);
	    if (xmax > xmin) {
	        int ymin = Math.max(r1.y, r2.y);
	        int ymax1 = r1.y + r1.height;
	        int ymax2 = r2.y + r2.height;
	        int ymax = Math.min(ymax1, ymax2);
	        if (ymax > ymin) {
	        	if(out != null) {
		            out.x = xmin;
		            out.y = ymin;
		            out.width = xmax - xmin;
		            out.height = ymax - ymin;
	        	}
	            return true;
	        }
	    }
	    return false;
	}

}
