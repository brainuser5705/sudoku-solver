package backtracking;

/**
 * Credit to the RIT CS Department
 *
 * @author sps (Sean Strout @ RIT CS)
 * @author jeh (James Heliotis @ RIT CS)
 */

import java.util.Optional;

public class Backtracker {

    private boolean debug;

    public Backtracker(boolean debug){
        this.debug = debug;
        if (this.debug){
            System.out.println("Debugging");
        }
    }

    private void debugPrint(String msg, Configuration config){
        if (this.debug){
            System.out.println(msg + ": \n" + config);
        }
    }

    public Optional<Configuration> solve(Configuration config){
        debugPrint("Current config", config);
        if (config.isGoal()){
            return Optional.of(config);
        }else{
            for (Configuration child : config.getSuccessors()){
                if (child.isValid()){
                    debugPrint("\t Valid successor", child);
                    Optional<Configuration> sol = solve(child);
                    if (sol.isPresent()){
                        return sol;
                    }
                }else{
                    debugPrint("\tInvalid successor", child);
                }
            }
        }
        return Optional.empty();
    }
}
