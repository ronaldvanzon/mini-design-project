/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;

/**
 *
 * @author rzon
 */
public interface Subject {
    
    public ArrayList<Observer> observers = new ArrayList<>();
    
    public void addObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();
}
