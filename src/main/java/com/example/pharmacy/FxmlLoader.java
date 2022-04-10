package com.example.pharmacy;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import java.net.URL;

public class FxmlLoader {

    private Pane view;

    public Pane getView(String fileName) {

        try {

            URL fileURl = Main.class.getResource(fileName);
            if(fileURl == null)
            {
                throw new java.io.FileNotFoundException("can`t be found");
            }

            view = new FXMLLoader().load(fileURl);

        }catch (Exception e)
        {
            System.out.println(e.toString());
        }

        return view;
    }
}
