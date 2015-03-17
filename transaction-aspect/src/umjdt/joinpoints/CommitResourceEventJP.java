/**
 * 
 */
package umjdt.joinpoints;

import org.aspectj.lang.JoinPoint;

/**
 * @author AnasAlsubh
 * 
 */
public class CommitResourceEventJP {

	private JoinPoint commitResourceJP;

	public JoinPoint getCommitResourceJP() {
		return commitResourceJP;
	}

	public void setCommitResourceJP(JoinPoint commitResourceJP) {
		this.commitResourceJP = commitResourceJP;
	}
}
