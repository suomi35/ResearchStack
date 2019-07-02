package org.researchstack.backbone.task;

import java.io.Serializable;

public class FitnessCheckActiveTask extends ActiveTask implements Serializable {

    protected int walkDuration;
    protected int restDuration;
    protected String intendedUse;

    /**
     * Returns an initialized fitness check active task using the specified identifier and array of steps.
     *
     * @param identifier    The unique identifier for the task.
     * @param walkDuration  The number of seconds for that the user should walk.
     * @param restDuration  The number of seconds for that the user should walk.
     */
    public FitnessCheckActiveTask(String identifier, int walkDuration, int restDuration, String intendedUse) {
        super(identifier);
        this.walkDuration = walkDuration;
        this.restDuration = restDuration;
        this.intendedUse = intendedUse;
    }

    public int getWalkDuration() {
        return walkDuration;
    }

    public int getRestDuration() {
        return restDuration;
    }

    public String getIntendedUse() {
        return intendedUse;
    }
}