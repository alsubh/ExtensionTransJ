/**
 * 
 */
package umjdt.joinpoints;

import org.aspectj.lang.JoinPoint;

import umjdt.joinpoints.lock.ResourceLockedJP;

/**
 * @author AnasAlsubh
 * 
 */
public class EndHoldingResourceEventJP extends ResourceLockedJP {
	private JoinPoint endResourceJP;

	public JoinPoint getEndResourceJP() {
		return endResourceJP;
	}

	public void setEndResourceJP(JoinPoint endResourceJP) {
		this.endResourceJP = endResourceJP;
	}
}
