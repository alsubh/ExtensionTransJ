/**
 * 
 */
package umjdt.joinpoints;

import org.aspectj.lang.JoinPoint;

/**
 * @author AnasAlsubh
 * 
 */
public class StartHoldingResourceEventJP {
	private JoinPoint startResourceJP;

	public JoinPoint getStartResourceJP() {
		return startResourceJP;
	}

	public void setStartResourceJP(JoinPoint startResourceJP) {
		this.startResourceJP = startResourceJP;
	}
}
