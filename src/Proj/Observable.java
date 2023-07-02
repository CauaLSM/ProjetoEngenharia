package Proj;

public interface Observable {
    void registerObserver(Observers o);
    void removeObserver(Observers o);
    void notifyObservers();
}
