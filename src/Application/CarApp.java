package Application;
import Controllers.*;
import Interfaces.*;
import Model.*;
import View.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;


public class CarApp {



    public static void main(String[] args){

        CarController cc = new CarController();
        CarController.initCC(cc);

    }

}

