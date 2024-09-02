/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package project;

/**
 * @author simji
 * This show the import function of GUI
 */
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * @author simji
 * This class provides the blueprint for the application
 */
public class Project extends Application{
        private TextField nameTF, icTF, numberTF;
	private TextArea infoTA;
	private RadioButton selectedSeatRB;
	private String selectedFlight;
	private final int numRows = 20;
	private final int numCols = 4;

    /**
     * @param args
     *  This launch the main method of the project
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @param primaryStage
     *  This show the creating main interface and element of creating interface
     */
    @Override
    public void start(Stage primaryStage) {
    	//Create the main interface
    	BorderPane borderPane = new BorderPane();
    	
    	GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        
        //Create interface elements
        Label systemL = new Label("Flight Booking System");
        systemL.setStyle("-fx-font-size: 20px;-fx-text-fill: blue;"); 
        
        ComboBox<String>flightCB = new ComboBox<>();
    	flightCB.getItems().addAll("F233", "G625", "F234", "M570", "L567", "F723", "F948");
    	flightCB.setPromptText("Please select a flight");
    	
    	TextArea flightTA = new TextArea();
    	flightTA.setEditable(false);
    	flightTA.setMaxWidth(320);
        flightTA.setMaxHeight(79);
        
        //Set the ComboBox selection event
        flightCB.setOnAction(event -> {
            selectedFlight = flightCB.getSelectionModel().getSelectedItem();
            if (selectedFlight != null) {
                String flightInfo = getFlightInfo(selectedFlight);
                flightTA.setText(flightInfo);
            }
        });
        /**
         * This provides the name, IC No and phone number for label
         */
        Label nameL = new Label("Name:");
    	nameTF = new TextField();
    	
    	Label icL = new Label("IC.No/Passport No:");
    	icTF = new TextField();
    	
    	Label numberL = new Label("Phone Number:");
    	numberTF = new TextField();
    	
    	Button bookB = new Button("Book");
    	bookB.setOnAction(event -> bookSeats());
    	
    	infoTA = new TextArea();
    	infoTA.setEditable(false);
    	infoTA.setMaxWidth(320);
    	infoTA.setMaxHeight(300);
    	
    	Label resultL = new Label("Booking Result");
    	resultL.setStyle("-fx-font-size: 15px;-fx-text-fill: green;"); 
    	
    	Label seatL = new Label("Seat:");
    	
    	for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                RadioButton seat = new RadioButton();
                seat.setText((col + 1) + "-" + (row + 1));
                gridPane.add(seat, col, row);
                
                //Set the Button event
                seat.setOnAction(event -> {
                    if (seat.isSelected()) {
                        selectedSeatRB = seat;
                        for (Node node : gridPane.getChildren()) {
                    		if (node instanceof RadioButton && node != seat) {
                        		((RadioButton) node).setSelected(false);
                    		}
                		}
                    } else {
                        selectedSeatRB = null;
                    }
                });
            }
        }       
        //Set the layout
        VBox leftBox = new VBox(10);
        leftBox.setPadding(new Insets(5));
        
        VBox rightBox = new VBox(10);
        rightBox.setPadding(new Insets(5));
        rightBox.setAlignment(Pos.CENTER);
        
     	leftBox.getChildren().addAll(systemL, flightCB, flightTA, nameL, nameTF, icL, icTF, numberL, numberTF, bookB, resultL, infoTA);
        rightBox.getChildren().addAll(seatL, gridPane);
        
        borderPane.setLeft(leftBox);
        borderPane.setCenter(rightBox);
        
        //Set the stage and show
        Scene scene = new Scene(borderPane, 560, 600);
        primaryStage.setTitle("PROG1103_2023B_Project_D220220C");
        primaryStage.setScene(scene);
        primaryStage.show();
    }   
    //Get the flight information by flight code
    private String getFlightInfo(String flightCode) {
        if ("F233".equals(flightCode)) {
            return "Flight:  F233\nDeparture:  Kuala Lumpur Airport\nDestination:  Jakarta Airport\nPrice:  RM 400.00";
        } else if ("G625".equals(flightCode)) {
            return "Flight:  G625\nDeparture:  Kuala Lumpur Airport\nDestination:  Bangkok Airport\nPrice:  RM 450.00";
        } else if ("F234".equals(flightCode)) {
        	return "Flight:  F234\nDeparture:  Kuala Lumpur Airport\nDestination:  Beijing Airport\nPrice:  RM 600.00";
        } else if ("M570".equals(flightCode)) {
        	return "Flight:  M570\nDeparture:  Kuala Lumpur Airport\nDestination:  Tokyo Airport\nPrice:  RM 700.00";
        } else if ("L567".equals(flightCode)) {
        	return "Flight:  L567\nDeparture:  Kuala Lumpur Airport\nDestination:  New York Airport\nPrice:  RM 900.00";
        } else if ("F723".equals(flightCode)) {
        	return "Flight:  F723\nDeparture:  Kuala Lumpur Airport\nDestination:  London Airport\nPrice:  RM 800.00";
        } else if ("F948".equals(flightCode)) {
        	return "Flight:  F948\nDeparture:  Kuala Lumpur Airport\nDestination:  Paris Airport\nPrice:  RM 850.00";
        } else {
            return "Flight information not available";
        }
	}
	private void bookSeats() {
		if (selectedFlight != null && selectedSeatRB != null && !nameTF.getText().isEmpty() && !icTF.getText().isEmpty() && !numberTF.getText().isEmpty()) {
			//Build the booking information
            String flightInfo = getFlightInfo(selectedFlight);
            String seatInfo = selectedSeatRB.getText();
            String passengerName = nameTF.getText();
            String icNumber = icTF.getText(); 
            String phoneNumber = numberTF.getText();
            
            String bookingInfo ="Booking Successful!" +
            		"\n" +
            		"\n-Flight Information-" +
                    "\n" + flightInfo +
                    "\n" +
                    "\n-Selected Seat-" +
                    "\nSeat:  " + seatInfo +
                    "\n" +
                    "\n-Passenger Information-" +
                    "\nName:  " + passengerName +
                    "\nIC.No/Passport.No:  " + icNumber +
                    "\nPhone Number:  " + phoneNumber;
            
            infoTA.setText(bookingInfo);
			infoTA.setStyle("-fx-text-fill: black;");
        } else {
        	//Show error message
            infoTA.setText("Please select a flight, a seat, and enter your information.");
           	infoTA.setStyle("-fx-text-fill: red;");
        }
    }
}

    
    

