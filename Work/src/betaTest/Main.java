package betaTest;

import java.awt.Dialog;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
	static InetAddress ip;
	static String hostname;
	static String total;
	static String available;
	static String fileSysRoot;
	static String totalSpace;
	static String freeSpace;
	static String usageSpace;
	static String sysInfo;
	static String serverInfo;
	static String Date;
	static String time;
	static String Status;
	static String username;
	SendMail sm = new SendMail();


	@Override
	public void start(final Stage primaryStage) {
		
		String timeStamp = new SimpleDateFormat("MM.dd_HH.mm.ss")
				.format(Calendar.getInstance().getTime());
		if (System.getProperty("java.version").startsWith("1.8")) {

			try {
				primaryStage.setTitle("Technical support");
				GridPane grid = new GridPane();
				grid.setAlignment(Pos.CENTER);
				grid.setHgap(10);
				grid.setVgap(10);
				grid.setPadding(new Insets(25, 25, 25, 25));

				Scene scene = new Scene(grid, 300, 150);
				Text scenetitle = new Text("Describe your problem");
				scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
				grid.add(scenetitle, 0, 0, 2, 1);

				final TextField userTextField = new TextField();
				final TextArea description = new TextArea();
				grid.add(userTextField, 0, 1);
				grid.add(description, 0, 1);
				userTextField.setPrefWidth(800);
				userTextField.setPrefHeight(200);
				userTextField.setMaxWidth(400.0);
				userTextField.setMaxHeight(100.0);

				final Button btn = new Button("Send problem");
				HBox hbBtn = new HBox(10);
				hbBtn.setAlignment(Pos.BOTTOM_CENTER);
				hbBtn.getChildren().add(btn);
				grid.add(hbBtn, 0, 2);

				primaryStage.setScene(scene);
				primaryStage.show();
				
				
				btn.setOnAction(new EventHandler<ActionEvent>() {
									
					public void handle(ActionEvent event) {

						try {
							String fileName = System.getProperty("user.name");
							File statText = new File("u:\\HelpDesk\\sort\\"
									+ fileName + timeStamp + ".txt");
							FileOutputStream is = new FileOutputStream(statText);
							OutputStreamWriter osw = new OutputStreamWriter(is);
							BufferedWriter w = new BufferedWriter(osw);
							String msg = description.getText();
							msg = msg.replaceAll("(?!\\r)\\n", "\r\n");
							
							
							
							try {
								w.write(msg);
								w.newLine();
								w.newLine();
								w.write("User name: "
										+ System.getProperty("user.name")
										+ "\n\r" + "\n\r" + "Name PC "
										+ UserName.username + "\n\r" + "\n\r");

								w.write("" + sysInfo + "\n\r" + "\n\r");

								w.write("" + total + "\n\r" + "\n\r");

								w.write("" + available + "\n\r" + "\n\r"
										+ "\n\r");
								w.close();
							} catch (IOException e) {
								e.printStackTrace();								
							}
							
							excelWriter.writeToExcel(Date, time, username, msg, Status);
							sm.sendMail(msg, statText, timeStamp);

							primaryStage.close();

						} catch (IOException e) {
							e.printStackTrace();
							
							try {
								
								sm.sendMailWoutfile("Cannot write file", timeStamp);
								primaryStage.close();
								errorDialog();
								
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							
						}
					}
				});

			} catch (Exception e) {

				e.printStackTrace();
			}
		} else {
			try {
				Process p = Runtime.getRuntime().exec(
						"u:\\HelpDesk\\soft\\jre8.exe");
				try {
					p.waitFor();
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	public void getInfo() throws Exception {
		// Get Memory
		memory mem = new memory();
		mem.getMemory();
		total = mem.getTotal();
		available = mem.getAvailable();
	

		// Get systemInfo
		systemInfo sy = new systemInfo();
		sysInfo = sy.OsInfo();
		// Get Prosess

		// Get Ip & Host
		ip = InetAddress.getLocalHost();
		hostname = ip.getHostName();
		UserName.getUsername();

		Date = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance()
				.getTime());
		time = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance()
				.getTime());
		Status = "Open";
		username = System.getProperty("user.name");
		
	}
public void errorDialog() {
	
	final Stage newprimaryStage = new Stage();
	newprimaryStage.setTitle("Error");
    GridPane grid2 = new GridPane();
    grid2.setAlignment(Pos.CENTER);
    grid2.setHgap(10);
    grid2.setVgap(10);
    grid2.setPadding(new Insets(25, 25, 25, 25));

    Scene scene2 = new Scene(grid2, 300, 150);
    Text scenetitle2 = new Text();
    scenetitle2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
    grid2.add(scenetitle2, 0, 0, 2, 1);
   
    final Button btn2 = new Button("OK");
    HBox hbBtn2 = new HBox(10);
    hbBtn2.setAlignment(Pos.BOTTOM_CENTER);
    hbBtn2.getChildren().add(btn2);
    grid2.add(hbBtn2, 1, 2);
    btn2.setDisable(true);
	newprimaryStage.setScene(scene2);
    newprimaryStage.show();
    scenetitle2.setText("Don't worry");
    btn2.setDisable(false);
    btn2.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
        	
        	
        	newprimaryStage.close();
        	
        	
        }
    });  
}
	public static void main(String[] args) {
		launch(args);
		

	}

}
