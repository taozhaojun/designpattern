import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class ObserverPattern2 {

    public static void main(String[] args) {
        TempServer server = new TempServer();

        for (int i = 1; i <= 5; i++) {
            server.addListener(new User("user" + i));
        }

        new Thread(server).start();

    }
}

interface Observable {
    void addListener(PropertyChangeListener listener);

    void removeListener(PropertyChangeListener listener);
}

class TempServer implements Runnable, Observable {
    PropertyChangeSupport manager = new PropertyChangeSupport(this);
    int oldTemp = 0;

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
            int newTemp = rnd.nextInt(41);
            PropertyChangeEvent event = new PropertyChangeEvent(this, "temp data", oldTemp, newTemp);
            manager.firePropertyChange(event);
            oldTemp = newTemp;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}

interface Observer extends PropertyChangeListener {

    void propertyChange(PropertyChangeEvent evt);
}

class User implements Observer {

    String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        System.out.println(name + " got a data which is " + evt.getNewValue());

    }

}
