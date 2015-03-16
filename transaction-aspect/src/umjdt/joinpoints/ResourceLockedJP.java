/**
 * 
 */
package umjdt.joinpoints;

import org.aspectj.lang.JoinPoint;

/**
 * @author AnasAlsubh
 * 
 */
public class ResourceLockedJP extends TransJP {
	private JoinPoint resourcelockedJP;

	public JoinPoint getResourcelockedJP() {
		return resourcelockedJP;
	}

	public void setResourcelockedJP(JoinPoint resourcelockedJP) {
		this.resourcelockedJP = resourcelockedJP;
	}
}
