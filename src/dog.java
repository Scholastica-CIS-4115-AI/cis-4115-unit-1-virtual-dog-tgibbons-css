
/**
 * The dog class implements a simple Finite State Machine for a virtual dog.
 *    The dog has five possible States,  {HAPPY, SAD, HUNGRY, ANGRY, SLEEPY}
 *    The GUI provides four possible inputs or actions, {PET, WALK, FEED, IGNORE}
 *
 * @author      Original author: Tom Gibbons. Updated by: STUDENT NAME HERE
 * @version Spring 2017 version
 */
public class dog {
    
// TOM made changes for the video
    
    // ======================================================
    // Constants for the dog' state and actions
    // =====================================================
    String[] state_names = {"Happy", "Sad", "Hungry", "Angry", "Sleepy"};          //array of strings for the state names.

    public enum States {
        HAPPY, SAD, HUNGRY, ANGRY, SLEEPY
    };                                                                              //enum and variable for dog state 
    public States state = States.HAPPY;

    String[] action_names = { //array of strings for actions
        "Pet dog",
        "Walk the dog",
        "Feed the dog",
        "Ignore the dog"};

    public enum Actions {
        PET, WALK, FEED, IGNORE
    };                                                                              //enum of actions
    public Actions currentAction;                                                   //the current action the user selected

    private String dog_status = "";                                                 // stores what the dog is saying

    /**
     * *
     * getState() returns the state of the dog
     *
     * @return state as an enum States.
     */
    private States getState() {
        return state;
    }

    /**
     * *
     * getStateAsInt() returns the state of the dog
     *
     * @return state as an integer corresponding to the index in the States enum.
     */
    public int getStateAsInt() {
        return state.ordinal();
    }

    /**
     * *
     * getStateLabel() returns the state of the dog as a string
     *
     * @return state as a string from the state_names array.
     */
    public String getStateLabel() {
        return state_names[state.ordinal()];
    }

    /**
     * *
     * setState() sets the state of the dog to the given States enum value. It also updates the dog's status based on the new state.
     */
    private void setState(States new_state) {
        // used to set the dog's state
        state = new_state;
        switch (state) {
            case HAPPY:
                setDogStatus("The dog is now wagging its tail");                // wag your tail when happy
                break;
            case SAD:
                setDogStatus("The dog wimpers, 'Mmmm mmmm'");                   // whimper when sad
                break;
            case HUNGRY:
                setDogStatus("The dog says, 'Ruff ruff'");                      // bark when hungry
                break;
            case ANGRY:
                setDogStatus("The dog says, 'Grrrrr'");                         // Growl when angry
                break;
            case SLEEPY:
                setDogStatus("The dog lies down to sleep");	  		// lie down when sleepy
                break;
        }
    }

    /**
     * *
     * getDogStatus() returns the state and the status of the dog. This is a string saying what the dog is doing.
     *
     * @return an HTML formated string describing the dog's state and what the dog is doing--its status.
     */
    public String getDogStatus() {
        return "<html>" + getStateLabel() + " dog <br>" + dog_status;
    }

    /**
     * *
     * setDogStatus() sets the status of the dog. This is a string saying what the dog is doing.
     */
    public void setDogStatus(String sound) {
        dog_status = sound;
    }

    /**
     * *
     * getActions() returns a list, actually an array, of all the possible dog actions.
     *
     * @return an array of all actions as array of strings
     */
    public String[] getActions() {
        return action_names;
    }

    /**
     * **
     * do_action() is called from GUI when user choose an action. Depending of the dog's state, the corresponding update method is called
     *
     * @param act_num is an integer that corresponds to an Actions enum that the user selected
     */
    public void do_action(int act_num) {
        // depending on choice from combo box, call one of the action methods
        currentAction = Actions.values()[act_num];			// convert the integer action into an enum action
        switch (state) {
            case HAPPY:
                updateHappy(currentAction);
                break;
            case SAD:
                updateSad(currentAction);
                break;
            case HUNGRY:
                updateHungry(currentAction);
                break;
            case ANGRY:
                updateAngry(currentAction);
                break;
            case SLEEPY:
                updateSleepy(currentAction);
                break;
        }

    }

    // ======================================================================================
    // These are the dog action routines.  This is what the dog can do is response to input
    //        modify these routines to implement your dog's finite state machine
    // ======================================================================================
    /**
     * ** HAPPY ***** updateHappy() is called when the dog is happy and a new input or action arrives. The dog always wags its tail when it is happy The dog
     * may change state depending on the input or action
     *
     * @param currentAction is a Actions enum which corresponds to the action the user selected from the user interface
     */
    private void updateHappy(Actions currentAction) {
        switch (currentAction) {
            case PET:								//dog is being petted
            case FEED:								//or dog is being fed
                setState(States.HAPPY); 			//  the dog stays happy
                break;
            case WALK:								//dog is being walked
                setState(States.HUNGRY); 			//  this makes the dog hungry
                break;
            case IGNORE:							//dog is being ignored
                setState(States.SAD); 				//   this makes the dog sad
                break;
        }
    }

    /**
     * ** SAD ***** updateSad() is called when the dog is sad and a new input or action arrives. The dog always wimpers when it is sad The dog may change state
     * depending on the input or action
     *
     * @param currentAction is a Actions enum which corresponds to the action the user selected from the user interface
     */
    private void updateSad(Actions currentAction) {
        switch (currentAction) {
            case PET:
            case FEED:
                setState(States.HAPPY);
                break;
            case WALK:
                setState(States.HUNGRY);
                break;
            case IGNORE:
                setState(States.SAD);
                break;
        }
    }

    /**
     * ** HUNGRY ***** updateHungry() is called when the dog is hungry and a new input or action arrives. The dog always barks when hungry The dog may change
     * state depending on the input or action
     *
     * @param currentAction is a Actions enum which corresponds to the action the user selected from the user interface
     */
    private void updateHungry(Actions currentAction) {
        switch (currentAction) {
            case PET:
            case WALK:
                setState(States.HUNGRY);
                break;
            case FEED:
                setState(States.HAPPY);
                break;
            case IGNORE:
                setState(States.SAD);
                break;
        }
    }

    /**
     * ** ANGRY ***** updateAngry() is called when the dog is hungry and a new input or action arrives. The dog always growls when angry The dog may change
     * state depending on the input or action
     *
     * @param currentAction is a Actions enum which corresponds to the action the user selected from the user interface
     */
    private void updateAngry(Actions currentAction) {
        switch (currentAction) {
            case PET:
            case WALK:
            case FEED:
            case IGNORE:
                setState(States.ANGRY); 			// Currently the dog always stays angry, not matter what you do
                break;
        }
    }

    /**
     * ** SLEEPY ***** updateSleepy() is called when the dog is hungry and a new input or action arrives. The dog always barks when hungry The dog may change
     * state depending on the input or action
     *
     * @param currentAction is a Actions enum which corresponds to the action the user selected from the user interface
     */
    private void updateSleepy(Actions currentAction) {
        switch (currentAction) {
            case PET:
            case WALK:
            case FEED:
            case IGNORE:
                setState(States.SLEEPY); 			// Currently the dog always stays sleepy, not matter what you do
                break;
        }
    }

}
