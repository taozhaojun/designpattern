import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;

public class ObserverPattern {

    public static void main(String[] args) {
        Chris c = new Chris();
        c.addListener(new Neighbor());
        new Thread(c).start();

    }

}

interface Observable2 {
    void addListener(PropertyChangeListener listener);

    void removeListener(PropertyChangeListener listener);
}

class Chris implements Observable2, Runnable {
    PropertyChangeSupport manager = new PropertyChangeSupport(this);
    ChrisState state = ChrisState.HOME;

    @Override
    public void addListener(PropertyChangeListener listener) {
        manager.addPropertyChangeListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeListener listener) {
        manager.removePropertyChangeListener(listener);
    }

    @Override
    public void run() {
        Random rnd = new Random();

        while (true) {
            double chance = rnd.nextDouble();
            ChrisState newState;
            if (chance < 0.7) {
                newState = ChrisState.HOME;
            } else {
                newState = ChrisState.AWAY;
            }

            System.out.println("Chirs current status: " + newState);

            PropertyChangeEvent event = new PropertyChangeEvent(this, "Chris state", state, newState);
            manager.firePropertyChange(event);

            state = newState;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

}

class Neighbor implements PropertyChangeListener {

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ChrisState state = (ChrisState) evt.getNewValue();
        if (state == ChrisState.HOME) {
            System.out.println("Neighbor State: Quiet");
        } else {
            System.out.println("Neighbor State: Throwing garbage to his back yard. Playing music lound");
        }

    }

}
enum ChrisState{
    HOME, AWAY
}