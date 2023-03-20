import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BABY extends Application implements EventHandler<ActionEvent>{

	Button b,b1,b2;
	ComboBox cb;
	TextField tf;
	Label l;
	TextArea ta,ta22 ;
	RadioButton rb1,rb2;
	Stage Window;
	Scene s,s1,s2;
	static ArrayList<Name> n = new ArrayList<>();


	public static void main(String[] args) {
		Application.launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Window = primaryStage;
		GridPane pane = new GridPane();

		pane.add(new Label("Year:"), 0, 0);
		cb = new ComboBox();
		cb.getItems().addAll("2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017");
		pane.add(cb, 1, 0);


		pane.add(new Label("Gender:"), 0, 1);
		rb1 = new RadioButton("Male");
		rb2 = new RadioButton("Female");
		ToggleGroup t = new ToggleGroup();
		rb1.setToggleGroup(t);
		rb2.setToggleGroup(t);
		HBox hb = new HBox();
		hb.getChildren().addAll(rb1,rb2);
		hb.setSpacing(5);
		pane.add(hb, 1, 1);

		pane.add(new Label("Name:"), 0, 2);
		tf = new TextField();
		pane.add(tf, 1, 2);

		b = new Button("Get Rank");
		b.setOnAction(this);
		l = new Label();
		pane.add(b, 0, 3);
		pane.add(l, 1, 3);

		b1 = new Button("Get Top 10 Names");
		pane.add(b1, 0, 4);
		b1.setOnAction(this);

		b2 = new Button("List Unisex Names");
		pane.add(b2, 0, 5);
		b2.setOnAction(this);


		pane.setAlignment(Pos.CENTER);
		pane.setHgap(5);
		pane.setVgap(5);

		ta = new TextArea();
		ta.setEditable(false);
		Label l4 = new Label("Top 10 names in this gender :");
		Button Back = new Button("Back");
		BorderPane bp = new BorderPane();
		bp.setTop(l4);
		bp.setCenter(ta);
		bp.setBottom(Back);

		Back.setOnAction(e->Window.setScene(s));


		ta22 = new TextArea();
		ta22.setEditable(false);
		Label l3 = new Label("Baby names for both genders:");
		Button Back2 = new Button("Back");
		BorderPane bp2 = new BorderPane();
		bp2.setTop(l3);
		bp2.setCenter(ta22);
		bp2.setBottom(Back2);

		Back2.setOnAction(e->Window.setScene(s));


		s = new Scene(pane,400,400);
		s1 = new Scene(bp,400,250);
		s2=new Scene(bp2,400,400);
		Window.setTitle("Baby name popularity ranking");
		Window.setScene(s);
		Window.show();

	}


	@Override
	public void handle(ActionEvent event){
		//button1
		if(event.getSource()==b) {

			int year = Integer.parseInt(cb.getValue().toString());
			String name = tf.getText().toString();
			String gender;

			if(rb1.isSelected()) {
				gender = "M";
			}
			else
				gender="F";

			File f = new File("USA_yob"+year+".txt");
			if(f.exists()) {
				Scanner in;
				try {
					in = new Scanner(f);
					while(in.hasNext()) {
						String m = in.nextLine();
						String[] t = m.split(",");
						n.add(new Name(t[0], t[1],Integer.parseInt(t[2])));}

					if(rank(name,gender)==-1) {
						l.setText(name+" is not found!!");



					}
					else
						l.setText(name + "  is Ranked at " + rank(name,gender));


				}

				catch (FileNotFoundException e){

					e.printStackTrace();
				}
			}
		}


		//button2

		else if (event.getSource()==b1) {
			Window.setScene(s1);

			int year = Integer.parseInt(cb.getValue().toString());
			String gender;

			if(rb1.isSelected()) {
				gender = "M";
			}
			else {
				gender="F";
			}

			File f = new File("USA_yob"+year+".txt");
			if(f.exists()) {
				Scanner in;
				try {
					in = new Scanner(f);
					while(in.hasNext()) {
						String m = in.nextLine();
						String[] t = m.split(",");
						n.add(new Name(t[0], t[1],Integer.parseInt(t[2])));}


					Collections.sort(n);

					int count =0 ;
					for (int i = 0; i < n.size(); i++) {
						if(n.get(i).getGender().equals(gender)) {
							ta.setText(ta.getText()+n.get(i).toString()+"\n");
							count ++;
							if(count==10) {
								break;
							}
						}
					}
				}

				catch (FileNotFoundException e){

					e.printStackTrace();
				}
			}
		}


		//Button 3

		else  {
			Window.setScene(s2);
			System.out.println("when click button 3 please wait for it to appear it takes time (file very large)\n if it takes very long time please close it and try again");

			int year = Integer.parseInt(cb.getValue().toString());

			File f = new File("USA_yob"+year+".txt");
			if(f.exists()) {
				Scanner in;
				try {
					in = new Scanner(f);
					while(in.hasNext()) {
						String m = in.nextLine();
						String[] t = m.split(",");
						n.add(new Name(t[0], t[1],Integer.parseInt(t[2])));
					}


					Collections.sort(n);



					for (int i = 0; i < n.size(); i++) {
						for (int j = i+1; j < n.size(); j++) {
							if(n.get(i).getName().equals(n.get(j).getName())) { 
								if(n.get(i).getGender() != n.get(j).getGender()) {
									ta22.setText(ta22.getText()+(n.get(i).toString()+ "<==>"+n.get(j).toString())+"\n");
								}
							}
						}
					}
				}
				
				
				
				
				
				
				

				catch (FileNotFoundException e){

					e.printStackTrace();
				}
			}
		}
	}

	

	public static int rank(String name,String gender) {
		for (int i = 0; i <n.size(); i++) {
			if(n.get(i).getName().equals(name) && n.get(i).getGender().equals(gender)) 
				return i+1;

		}
		return -1;
	}
}




