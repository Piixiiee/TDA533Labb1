package Interfaces;

public interface Loadable<T> { // Applies to things that can carry load
    void load(T item); // Add item to storage object
    void unload() throws Exception; // Remove item from storage object
    void unload(T item) throws Exception;
}