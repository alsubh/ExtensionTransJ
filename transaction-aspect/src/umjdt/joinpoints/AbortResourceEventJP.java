/**
 * 
 */
package umjdt.joinpoints;

import org.aspectj.lang.JoinPoint;

/**
 * @author AnasAlsubh
 * 
 */
public class AbortResourceEventJP {
	private JoinPoint abortResourceJP;

	public JoinPoint getAbortResourceJP() {
		return abortResourceJP;
	}

	public void setAbortResourceJP(JoinPoint abortResourceJP) {
		this.abortResourceJP = abortResourceJP;
	}
}
