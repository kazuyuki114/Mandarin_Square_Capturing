package application.gui.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PlayController implements Initializable {
	private Media media;
//	private MediaPlayer mediaPlayer;
	private Stage stage;
	private Scene menuScene;
	private static final int NUM_PIECES = 5;
	private Image smallPieceImage;
	private Image bigPieceImage;
	Random random = new Random();
	private boolean isCellSelected = false;
    @FXML
    private AnchorPane root;
    
    @FXML
    private Slider musicSlider;

    @FXML
    private Slider soundEffectSlider;
    
    @FXML
    private Button backButton;

    @FXML
    private Rectangle P1Cell2;

    @FXML
    private Rectangle P1Cell1;

    @FXML
    private Rectangle P1Cell3;

    @FXML
    private Rectangle P1Cell5;

    @FXML
    private Rectangle P1Cell4;

    @FXML
    private Rectangle P2Cell8;

    @FXML
    private Rectangle P2Cell7;

    @FXML
    private Rectangle P2Cell9;

    @FXML
    private Rectangle P2Cell10;

    @FXML
    private Rectangle P2Cell11;
    
    @FXML
    private Button Cell1ClockwiseButton;

    @FXML
    private Button Cell1CounterClockwiseButton;

    @FXML
    private Button Cell2CounterClockwiseButton;

    @FXML
    private Button Cell2ClockwiseButton;

    @FXML
    private Button Cell3CounterClockwiseButton;

    @FXML
    private Button Cell3ClockwiseButton;

    @FXML
    private Button Cell4CounterClockwiseButton;

    @FXML
    private Button Cell4ClockwiseButton;

    @FXML
    private Button Cell5ClockwiseButton;

    @FXML
    private Button Cell5CounterClockwiseButton;

    @FXML
    private Button Cell11ClockwiseButton;

    @FXML
    private Button Cell10ClockwiseButton;

    @FXML
    private Button Cell9ClockwiseButton;

    @FXML
    private Button Cell8ClockwiseButton;

    @FXML
    private Button Cell7ClockwiseButton;

    @FXML
    private Button Cell11CounterClockwiseButton;

    @FXML
    private Button Cell10CounterClockwiseButton;

    @FXML
    private Button Cell9CounterClockwiseButton;

    @FXML
    private Button Cell8CounterClockwiseButton;

    @FXML
    private Button Cell7CounterClockwiseButton;

    @FXML
    private Button[] clockwiseButtons;

    @FXML
    private Button[] counterclockwiseButtons;
    
    @FXML
    private Rectangle[] cells;
    

    @FXML
    private Arc P1HalfCircle;

    @FXML
    private Arc P2HalfCircle;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        cells = new Rectangle[]{P1Cell1, P1Cell2, P1Cell3, P1Cell4, P1Cell5, P2Cell7, P2Cell8, P2Cell9, P2Cell10, P2Cell11};
		
        clockwiseButtons = new Button[]{Cell1ClockwiseButton, Cell2ClockwiseButton, Cell3ClockwiseButton, Cell4ClockwiseButton, Cell5ClockwiseButton,
                Cell7ClockwiseButton, Cell8ClockwiseButton, Cell9ClockwiseButton, Cell10ClockwiseButton, Cell11ClockwiseButton};

counterclockwiseButtons = new Button[]{Cell1CounterClockwiseButton, Cell2CounterClockwiseButton, Cell3CounterClockwiseButton, Cell4CounterClockwiseButton, Cell5CounterClockwiseButton,
                Cell7CounterClockwiseButton, Cell8CounterClockwiseButton, Cell9CounterClockwiseButton, Cell10CounterClockwiseButton, Cell11CounterClockwiseButton};
        
        // TODO Auto-generated method stub
		if(!MediaManager.isPlaying()) {
			File file = new File("src/application/gui/music/Battle_P2_Reverse_1999_Soundtrac_ Extended.mp3");
			System.out.println(file.exists());
			media = new Media(file.toURI().toString());
	        MediaManager.setMediaPlayer(media);
	        MediaManager.setPlaying(true);
		}
        MediaManager.getMediaPlayer().play();
        MediaManager.getMediaPlayer().setOnEndOfMedia(() -> MediaManager.getMediaPlayer().seek(MediaManager.getMediaPlayer().getStartTime()));
        // Load small piece image
        String smallPiecePath = "/application/gui/image/small1.png";
        URL imageUrl = getClass().getResource(smallPiecePath);
        if (imageUrl == null) {
            System.out.println("Error: Image resource not found at " + smallPiecePath);
        } else {
            smallPieceImage = new Image(imageUrl.toExternalForm());
            System.out.println("Loaded image from: " + imageUrl);
        }
        
        if (smallPieceImage.isError()) {
            System.out.println("Error loading image: " + smallPiecePath);
        }
        smallPieceImage = new Image(getClass().getResourceAsStream(smallPiecePath));
        
        for(Rectangle rect : cells) {
        	addPiecesToCell(rect, NUM_PIECES);
        }

        // Load big piece image
        String bigPiecePath = "/application/gui/image/big.png";
        URL bigPieceUrl = getClass().getResource(bigPiecePath);
        if (bigPieceUrl == null) {
            System.out.println("Error: Image resource not found at " + bigPiecePath);
        } else {
            bigPieceImage = new Image(bigPieceUrl.toExternalForm());
            System.out.println("Loaded image from: " + bigPieceUrl);
        }
        
        if (smallPieceImage.isError()) {
            System.out.println("Error loading image: " + bigPiecePath);
        }
        bigPieceImage = new Image(getClass().getResourceAsStream(bigPiecePath));
        addBigPieceToHalfCircle(P1HalfCircle);
        addBigPieceToHalfCircle(P2HalfCircle);
	}
	public void backToMenu(ActionEvent event) throws IOException {
	    try {
	        MediaManager.getMediaPlayer().pause();
	        MediaManager.setPlaying(false);
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/gui/resource/MenuScene.fxml"));
	        Parent root = loader.load();
	        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        menuScene = new Scene(root);
	        stage.setScene(menuScene);
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        System.err.println("Error loading FXML file: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	private void addPiecesToCell(Rectangle rectangle, int numPieces) {
	    // List to keep track of placed pieces' positions
	    List<ImageView> placedPieces = new ArrayList<>();

	    for (int i = 0; i < numPieces; i++) {
	        ImageView pieceView = new ImageView(smallPieceImage);
	        pieceView.setFitHeight(20);  // Adjust size as needed
	        pieceView.setFitWidth(20);   // Adjust size as needed

	        boolean overlapping;
	        double xPos = 0, yPos = 0;

	        do {
	            overlapping = false;

	            // Calculate random position within the rectangle
	            xPos = rectangle.getLayoutX() + random.nextDouble() * (rectangle.getWidth() - pieceView.getFitWidth());
	            yPos = rectangle.getLayoutY() + random.nextDouble() * (rectangle.getHeight() - pieceView.getFitHeight());

	            // Check for overlap with existing pieces
	            for (ImageView placedPiece : placedPieces) {
	                if (isOverlapping(xPos, yPos, pieceView, placedPiece)) {
	                    overlapping = true;
	                    break;
	                }
	            }
	        } while (overlapping);

	        pieceView.setLayoutX(xPos);
	        pieceView.setLayoutY(yPos);

	        placedPieces.add(pieceView);
	        root.getChildren().add(pieceView);
	    }
	}
	
	private void addBigPieceToHalfCircle(Arc arc) {
	    ImageView pieceView = new ImageView(bigPieceImage); // Assuming bigPieceImage is initialized
	    pieceView.setFitHeight(30);  // Adjust size as needed
	    pieceView.setFitWidth(30);   // Adjust size as needed

//	    // Calculate the center of the arc
//	    double centerX = arc.getCenterX() + arc.getRadiusX();
//	    double centerY = arc.getCenterY();
//
//	    // Calculate the angle of the middle of the arc
//	    double angle = arc.getStartAngle() + (arc.getLength() / 2);
//
//	    // Calculate the x and y coordinates of the middle of the arc
//	    double xPos = centerX + arc.getRadiusX() * Math.cos(Math.toRadians(angle));
//	    double yPos = centerY + arc.getRadiusX() * Math.sin(Math.toRadians(angle));
	    double xPos = 0, yPos = 0;
	    if (arc == P1HalfCircle) {
	    	xPos = 360;
	    	yPos = 370;
	    } else {
	    	xPos = 900;
	    	yPos = 370;
	    }
	    // Add the ImageView to the middle of the arc
	    pieceView.setLayoutX(xPos);
	    pieceView.setLayoutY(yPos);
	    System.out.println(xPos + " " + yPos);
	    // Add the ImageView to the parent container
	    root.getChildren().add(pieceView);
	}



	
	
	private boolean isOverlapping(double xPos, double yPos, ImageView newPiece, ImageView existingPiece) {
	    double newPieceRight = xPos + newPiece.getFitWidth();
	    double newPieceBottom = yPos + newPiece.getFitHeight();

	    double existingPieceRight = existingPiece.getLayoutX() + existingPiece.getFitWidth();
	    double existingPieceBottom = existingPiece.getLayoutY() + existingPiece.getFitHeight();

	    return xPos < existingPieceRight &&
	           newPieceRight > existingPiece.getLayoutX() &&
	           yPos < existingPieceBottom &&
	           newPieceBottom > existingPiece.getLayoutY();
	}
	

    @FXML
    void selectCell1(MouseEvent event) {
    	if (isCellSelected == false) {
    		isCellSelected = true;
    		unselectCells();
        	Cell1ClockwiseButton.setVisible(true);
        	Cell1CounterClockwiseButton.setVisible(true);
    	} else {
    		unselectCells();
    		isCellSelected = false;
    	}
    }

    @FXML
    void selectCell10(MouseEvent event) {
    	if (isCellSelected == false) {
    		isCellSelected = true;
    		unselectCells();
        	Cell10ClockwiseButton.setVisible(true);
        	Cell10CounterClockwiseButton.setVisible(true);
    	} else {
    		unselectCells();
    		isCellSelected = false;
    	}
    }

    @FXML
    void selectCell11(MouseEvent event) {
    	if (isCellSelected == false) {
    		isCellSelected = true;
    		unselectCells();
        	Cell11ClockwiseButton.setVisible(true);
        	Cell11CounterClockwiseButton.setVisible(true);
    	} else {
    		unselectCells();
    		isCellSelected = false;
    	}
    }

    @FXML
    void selectCell2(MouseEvent event) {
    	if (isCellSelected == false) {
    		isCellSelected = true;
    		unselectCells();
        	Cell2ClockwiseButton.setVisible(true);
        	Cell2CounterClockwiseButton.setVisible(true);
    	} else {
    		unselectCells();
    		isCellSelected = false;
    	}
    }

    @FXML
    void selectCell3(MouseEvent event) {
    	if (isCellSelected == false) {
    		isCellSelected = true;
    		unselectCells();
        	Cell3ClockwiseButton.setVisible(true);
        	Cell3CounterClockwiseButton.setVisible(true);
    	} else {
    		unselectCells();
    		isCellSelected = false;
    	}
    }

    @FXML
    void selectCell4(MouseEvent event) {
    	if (isCellSelected == false) {
    		isCellSelected = true;
    		unselectCells();
        	Cell4ClockwiseButton.setVisible(true);
        	Cell4CounterClockwiseButton.setVisible(true);
    	} else {
    		unselectCells();
    		isCellSelected = false;
    	}
    }

    @FXML
    void selectCell5(MouseEvent event) {
    	if (isCellSelected == false) {
    		isCellSelected = true;
    		unselectCells();
        	Cell5ClockwiseButton.setVisible(true);
        	Cell5CounterClockwiseButton.setVisible(true);
    	} else {
    		unselectCells();
    		isCellSelected = false;
    	}
    }

    @FXML
    void selectCell7(MouseEvent event) {
    	if (isCellSelected == false) {
    		isCellSelected = true;
    		unselectCells();
        	Cell7ClockwiseButton.setVisible(true);
        	Cell7CounterClockwiseButton.setVisible(true);
    	} else {
    		unselectCells();
    		isCellSelected = false;
    	}
    }

    @FXML
    void selectCell8(MouseEvent event) {
    	if (isCellSelected == false) {
    		isCellSelected = true;
    		unselectCells();
        	Cell8ClockwiseButton.setVisible(true);
        	Cell8CounterClockwiseButton.setVisible(true);
    	} else {
    		unselectCells();
    		isCellSelected = false;
    	}
    }

    @FXML
    void selectCell9(MouseEvent event) {
    	if (isCellSelected == false) {
    		isCellSelected = true;
    		unselectCells();
        	Cell9ClockwiseButton.setVisible(true);
        	Cell9CounterClockwiseButton.setVisible(true);
    	} else {
    		unselectCells();
    		isCellSelected = false;
    	}
    }
    void unselectCells() {
           // Perform actions to unselect the cell
        for(Button button : clockwiseButtons) {
        	button.setVisible(false);
        }
        for(Button button : counterclockwiseButtons) {
        	button.setVisible(false);
        }
    }
}
