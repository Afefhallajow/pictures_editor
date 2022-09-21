import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

public class myapp extends Application {
    String path;
    int n;
private SeamCarver seamCarver1;
private Compress com;
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
Button btn1 =new Button("Compress");
Button b2=new Button("unCompress");
        GridPane root = new GridPane();
        root.add(btn,1,2);
        root.add(btn1,2,2);
        root.add(b2,3,2);
        Button button =new Button("return");

        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25,25,25,25));
        Scene scene = new Scene(root, 400, 250);
        b2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            com=new Compress();
                javafx.scene.control.Label file_path = new Label("file path");
                TextField filepath = new TextField();
                GridPane root = new GridPane();

                root.add(file_path, 2, 2);
                root.add(filepath, 3, 2);
                Button btn1 = new Button("Confirm");

                root.add(btn1, 3, 4);
                root.setAlignment(Pos.CENTER);
                root.setHgap(10);
                root.setVgap(10);
                root.setPadding(new Insets(25,25,25,25));
                Scene scen1 = new Scene(root, 400, 400);

                primaryStage.setTitle("unCompress");
                primaryStage.setScene(scen1);

                primaryStage.show();
                btn1.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {

                        String x1 =filepath.getText().toString();

                        javafx.scene.control.Label file_path = new Label("file path");
                        TextField filepath1 = new TextField();

                        GridPane root = new GridPane();

                        root.add(file_path, 2, 2);
                        root.add(filepath1, 3, 2);
                        Button btn1 = new Button("Confirm");
                        root.add(btn1, 3, 4);
                        root.add(button,4,4);
                        root.setAlignment(Pos.CENTER);
                        root.setHgap(10);
                        root.setVgap(10);
                        root.setPadding(new Insets(25,25,25,25));

                        Scene scen2 = new Scene(root, 400, 400);

                        primaryStage.setTitle("SaveUnCompress");
                        primaryStage.setScene(scen2);

                        primaryStage.show();
                        button.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                primaryStage.setTitle("Hello World!");
                                primaryStage.setScene(scene);
                                primaryStage.show();
                            }});
                        btn1.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                String path =filepath1.getText().toString();
                                String text = "";
                                System.out.println(x1);
                                try {
                                    text=com.unCompress1(x1);
                                    System.out.println(x1);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                try {
                                    com.Save(path,text);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }});
                    }});

            }});


btn1.setOnAction(new EventHandler<ActionEvent>() {

    @Override
    public void handle(ActionEvent event) {
        com =new Compress();
        javafx.scene.control.Label file_path = new Label("file path");
        TextField filepath = new TextField();
        GridPane root = new GridPane();

        root.add(file_path, 2, 2);
        root.add(filepath, 3, 2);
        Button btn1 = new Button("Confirm");

        root.add(btn1, 3, 4);
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25,25,25,25));
        Scene scene1 = new Scene(root, 400, 400);

        primaryStage.setTitle("Compress");
        primaryStage.setScene(scene1);

        primaryStage.show();
        btn1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                String x1 =filepath.getText().toString();

                javafx.scene.control.Label file_path = new Label("file path");
                TextField filepath1 = new TextField();

                GridPane root = new GridPane();

                root.add(file_path, 2, 2);
                root.add(filepath1, 3, 2);
                Button btn1 = new Button("Confirm");
                root.add(btn1, 3, 4);
                root.add(button,4,4);
                root.setAlignment(Pos.CENTER);
                root.setHgap(10);
                root.setVgap(10);
                root.setPadding(new Insets(25,25,25,25));

                Scene scene2 = new Scene(root, 400, 400);

                primaryStage.setTitle("SaveCompress");
                primaryStage.setScene(scene2);

                primaryStage.show();
                button.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setTitle("Hello World!");
                        primaryStage.setScene(scene);
                        primaryStage.show();
                    }});
                btn1.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                   String path =filepath1.getText().toString();
                        String text = "";
                  System.out.println(x1);
                        try {
                    text=com.Compress1(x1);
System.out.println(x1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        try {
                            com.Save(path,text);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        }});
            }});

    }});
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                javafx.scene.control.Label photo_path = new Label("photo path");
                TextField photopath = new TextField();
                javafx.scene.control.Label number = new Label("Reduction ratio %");
                TextField number1 = new TextField();
                GridPane root = new GridPane();

                root.add(photo_path, 1, 2);
                Scene scene3 = new Scene(root, 400, 400);
                root.add(photopath, 2, 2);
                root.add(number, 1, 3);
                root.add(number1, 2, 3);
                Button btun1 = new Button("Confirm");

                root.add(btun1, 3, 4);
                root.add(button, 4, 4);

                root.setAlignment(Pos.CENTER);
                root.setHgap(10);
                root.setVgap(10);
                root.setPadding(new Insets(25,25,25,25));
                primaryStage.setTitle("Seam Craver!");
                primaryStage.setScene(scene3);
                primaryStage.show();

                btun1.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
String                        X=photopath.getText().toString();
                            int v=Integer.parseInt( number1.getText().toString());
                         seamCarver1=new SeamCarver(X,v);
                        try {
                            seamCarver1.SeamCrave1();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        File outb=new File("b.png");
                        try {
                            ImageIO.write(seamCarver1.Energy_photo(),"png",outb);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Label new_path=new Label("save in ");
                        TextField path_new=new TextField();

                        Label newenergy_path=new Label("save energy in ");
                        TextField pathen_new=new TextField();

                        Button Energy=new Button("energy matrix");
                        Button Seam=new Button("Seam Carver");

                        GridPane choose =new GridPane();
                        choose.add(Energy,2,4);
                        choose.add(Seam,2,5);
                        choose.add(newenergy_path,1,2);
                        choose.add(pathen_new,1,3);

                        choose.add(new_path,2,1);
choose.add(path_new,2,2);
                        choose.setAlignment(Pos.CENTER);
                        choose.setHgap(10);
                        choose.setVgap(10);
                        choose.setPadding(new Insets(25,25,25,25));

                        Scene scene4 =new Scene(choose,250,300);
                        primaryStage.setTitle("Show Image!");
                        primaryStage.setScene(scene4);
                        primaryStage.show();
                        Seam.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {

 String path1=path_new.getText().toString();
                        BufferedImage pic2=seamCarver1.picture();
                    System.out.println(seamCarver1.leanth);

                                try {
                                    seamCarver1.Save(path1,seamCarver1.picture());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                InputStream stream = null;

                                try {
                                    stream = new FileInputStream(path1);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                                if( stream != null);
                                Image picure=new Image(stream);

                        ImageView imv=new ImageView();
                        imv.setImage(picure);
                        GridPane vb=new GridPane();
                        vb.add(imv,1,1);
                    vb.add(button,1,5);
                        imv.setFitHeight(400);
                        imv.setFitWidth(400);
                                vb.setAlignment(Pos.CENTER);
                                vb.setHgap(10);
                                vb.setVgap(10);
                               // vb.setPadding(new Insets(25,25,25,25));
                        Scene scene7 =new Scene(vb,600,700);
                        primaryStage.setTitle("Show Image after!");
                        primaryStage.setScene(scene7);
                        primaryStage.show();
                                button.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        primaryStage.setTitle("Show Image!");
                                        primaryStage.setScene(scene4);
                                        primaryStage.show();

                                    }
                                });


                    }
                });
    Energy.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

            String path1=pathen_new.getText().toString();
            BufferedImage pic2=seamCarver1.picture();
            System.out.println(seamCarver1.leanth);

            try {
                seamCarver1.Save(path1,seamCarver1.Energy_photo());
            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStream stream = null;

            try {
                stream = new FileInputStream(path1);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if( stream != null);
            Image picure=new Image(stream);
            ImageView imv =new ImageView();
imv.setImage(picure);
            GridPane vb=new GridPane();
            Label new_path=new Label("save in ");
            TextField path_new=new TextField();
            Button save=new Button("save ");
            vb.add(imv,1,1);
            vb.add(button,1,5);
            vb.add(save,1,4);
            vb.add(new_path,1,2);
            vb.add(path_new,1,3);
            imv.setFitHeight(400);
            imv.setFitWidth(400);
            vb.setAlignment(Pos.CENTER);
            vb.setHgap(10);
            vb.setVgap(10);
            vb.setPadding(new Insets(25,25,25,25));

            Scene scene41 =new Scene(vb,600,800);
            primaryStage.setTitle("Show Energy Image!");
            primaryStage.setScene(scene41);
            primaryStage.show();
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    primaryStage.setTitle("Show Image!");
                    primaryStage.setScene(scene4);
                    primaryStage.show();

                }
            });

        }
    });
                    }
                });
            }});


        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
 private static Scanner reader=new Scanner(System.in);
    public static void main(String[] args) {
        launch(args);
/**
 Compress compress=new Compress();
 String a = "";
 a=reader.next();
 String b=compress.compress(a);
        System.out.println(b);
     System.out.println(compress.un_compress(b));
        /**   try {
            compress.Compress1("C:\\Users\\HP\\Desktop\\na\\a.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(compress.un_compress(b));
    }**/}
}
