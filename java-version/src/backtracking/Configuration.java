package backtracking;

/**
 * Credit to the RIT CS Department
 *
 * @author sps (Sean Strout @ RIT CS)
 */

import java.util.Collection;

public interface Configuration {

    public Collection<Configuration> getSuccessors();

    public boolean isValid();

    public boolean isGoal();
}
