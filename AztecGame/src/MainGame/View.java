package MainGame;
import java.awt.Point;
import java.awt.Polygon;

public class View {

	private Point loc;
	int width;
	int height;

	private int xMax, yMax, xMin, yMin;

	public View(int width, int height) {
		this(new Point(0, 0), width, height);

		xMax = 1250 - width;
		xMin = -4000 + width;
		yMax = -1250 + height;
		yMin = 2000 - height;
	}

	public View(Point loc, int width, int height) {
		this.loc = loc;
		this.width = width;
		this.height = height;

		xMax = 1250 - width;
		xMin = -4000 + width;
		yMax = -1250 + height;
		yMin = 2000 - height;
	}

	public Point getLocation() {
		return loc;
	}

	public boolean pointIsInView(Point pnt) {
		return pointIsInView(pnt, 10);
	}
	public boolean pointIsInView(Point pnt, int buffer) {
		if((pnt.x < (getLocation().x - buffer)) || (pnt.y < (getLocation().y - buffer))) {
			return false;
		}
		if((pnt.x > (getLocation().x + width + buffer)) || (pnt.y > (getLocation().y + width + buffer))) {
			return false;
		}
		return true;
	}


	public boolean polygonIsInView(Polygon poly) {
		return polygonIsInView(poly, 10);
	}
	public boolean polygonIsInView(Polygon poly, int buffer) {
		for(int x=0; x < poly.xpoints.length; x++) {
			for(int y=0; y<poly.ypoints.length; y++) {
				if(pointIsInView(new Point(poly.xpoints[x], poly.ypoints[y]), buffer)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Description: Gets xMax for whoever calls this.
	 *
	 * @return the xMax
	 */
	public int getxMax() {
		return xMax;
	}

	/**
	 * Description: Gets yMax for whoever calls this.
	 *
	 * @return the yMax
	 */
	public int getyMax() {
		return yMax;
	}

	/**
	 * Description: Gets xMin for whoever calls this.
	 *
	 * @return the xMin
	 */
	public int getxMin() {
		return xMin;
	}

	/**
	 * Description: Gets yMin for whoever calls this.
	 *
	 * @return the yMin
	 */
	public int getyMin() {
		return yMin;
	}
}
