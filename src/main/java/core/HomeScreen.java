package core;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class HomeScreen extends Application{
	//Elements
	private Label label;
	private Button solnBtn;
	private TextField leftOperandTxtBox;
	private TextField rightOperandTxtBox;
	private TextField answerTxtBox;
	private ComboBox<String> operatorDropdown;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initUI(primaryStage);
	}

	public void initUI(Stage primaryStage) {
		//Create new canvas
		Pane canvas = new Pane();
		//canvas.setStyle("-fx-background-color: black");
		
		//Label
		label = new Label("Simple calculator using JavaFX");
		label.setFont(Font.font("Serif", FontWeight.NORMAL, 20));
		label.relocate(20, 20);
		
		//Left operand
		leftOperandTxtBox = new TextField();
		leftOperandTxtBox.setMaxWidth(50);
		leftOperandTxtBox.relocate(20, 60);
		
		//Right operand
		rightOperandTxtBox = new TextField();
		rightOperandTxtBox.setMaxWidth(50);
		rightOperandTxtBox.relocate(150, 60);
		
		//Operator dropdown
		operatorDropdown = new ComboBox<String>();
		operatorDropdown.getItems().addAll("+", "-", "x", "/", "%");
		operatorDropdown.setValue("+");
		operatorDropdown.relocate(80, 60);
		
		//'=' button
		solnBtn = new Button("=");
		solnBtn.relocate(210, 60);
		
		//Answer textbox
		answerTxtBox = new TextField();
		answerTxtBox.setEditable(false);
		answerTxtBox.setMaxWidth(50);
		answerTxtBox.relocate(250, 60);
		
		SetSolnBtnHandler();
		
		//Add elements to canvas
		canvas.getChildren().addAll(label, leftOperandTxtBox, rightOperandTxtBox, operatorDropdown, solnBtn, answerTxtBox);
		
		//Create and set new scene w/ canvas
		Scene scene = new Scene(canvas, 320, 200);
		
		primaryStage.setTitle("Simple Calculator App");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void SetSolnBtnHandler() {
		solnBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Double leftOperand = Double.valueOf(leftOperandTxtBox.getText());
				Double rightOperand = Double.valueOf(rightOperandTxtBox.getText());
				String operator = operatorDropdown.getValue();
				
				ArithmeticSolver solver = new ArithmeticSolver();
				
				String answer = String.valueOf(solver.solve(operator, leftOperand, rightOperand));
								
				answerTxtBox.setText(answer);
			}
		});
	}
}
