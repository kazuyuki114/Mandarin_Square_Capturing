package application.gui.controller;

import javafx.scene.paint.Color;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import application.board.Board;
import application.board.Cell;
import application.board.HalfCircle;
import application.player.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;


public class PlayController implements Initializable {
    private Media media;
    private Stage stage;
    private Scene menuScene;
    private static final int NUM_PIECES = 5;
    private Image smallPieceImage;
    private Image bigPieceImage;
    Random random = new Random();
    private boolean isCellSelected = false;
    private Player player1;
	private Player player2;
    private Board board;

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
    private Rectangle P1HalfCircle;

    @FXML
    private Rectangle P2HalfCircle;
    
    @FXML
    private Rectangle P1Score;

    @FXML
    private Rectangle P2Score;

    @FXML
    private Label P1ScoreLabel;

    @FXML
    private Label P2ScoreLabel;
    
    @FXML 
    private Label Player1;
    
    @FXML
    private Label Player2;
    

    private List<List<ImageView>> cellImageViews;
    private List<ImageView> P1ScoreImageView;
    private List<ImageView> P2ScoreImageView;
    private Rectangle[] cells;
    private ImageView bigPiece1;
    private ImageView bigPiece2;
    private boolean actionDone;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        cells = new Rectangle[]{P1HalfCircle, P1Cell1, P1Cell2, P1Cell3, P1Cell4, P1Cell5, P2HalfCircle, P2Cell7, P2Cell8, P2Cell9, P2Cell10, P2Cell11};
        clockwiseButtons = new Button[]{Cell1ClockwiseButton, Cell2ClockwiseButton, Cell3ClockwiseButton, Cell4ClockwiseButton, Cell5ClockwiseButton,
                Cell7ClockwiseButton, Cell8ClockwiseButton, Cell9ClockwiseButton, Cell10ClockwiseButton, Cell11ClockwiseButton};

        counterclockwiseButtons = new Button[]{Cell1CounterClockwiseButton, Cell2CounterClockwiseButton, Cell3CounterClockwiseButton, Cell4CounterClockwiseButton, Cell5CounterClockwiseButton,
                Cell7CounterClockwiseButton, Cell8CounterClockwiseButton, Cell9CounterClockwiseButton, Cell10CounterClockwiseButton, Cell11CounterClockwiseButton};
        if (!MediaManager.isPlaying()) {
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

        // Initialize cellImageViews
        cellImageViews = new ArrayList<>(cells.length);
        for (int i = 0; i < cells.length; i++) {
			cellImageViews.add(new ArrayList<>());
		}
        P1ScoreImageView = new ArrayList<>();
        P2ScoreImageView = new ArrayList<>();
        // Add pieces to cells
        for (int i = 1; i <= 5; i++) {
            addPiecesToCell(cells[i], NUM_PIECES, cellImageViews.get(i));
        }
        for (int i = 7; i <= 11; i++) {
            addPiecesToCell(cells[i], NUM_PIECES, cellImageViews.get(i));
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
        
        if (bigPieceImage.isError()) {
            System.out.println("Error loading image: " + bigPiecePath);
        }
        bigPieceImage = new Image(getClass().getResourceAsStream(bigPiecePath));

        addBigPieceToHalfCircle();
        
		board = new Board();
		player1 = new Player(1, board);
		player2 = new Player(2, board);
    	P1ScoreLabel.setText(""+player1.calculateScore());
    	P2ScoreLabel.setText(""+player1.calculateScore());
		player1.setInTurn(true);
		Player1.setTextFill(Color.web("#d92121"));
		ArrayList<Cell> Player1CellOnSide = new ArrayList<Cell>();
		ArrayList<Cell> Player2CellOnSide = new ArrayList<Cell>();
        for (int i = 1; i <= 5; i++) { 
            Player1CellOnSide.add(board.getCellList().get(i));
        }
        for (int i = 7; i <= 11; i++) {
            Player2CellOnSide.add(board.getCellList().get(i));
        }
        player1.setCellsOnPlayerSide(Player1CellOnSide);
        player2.setCellsOnPlayerSide(Player2CellOnSide);
    }
    void updateScore() {
    	P1ScoreLabel.setText(""+player1.calculateScore());
    	P2ScoreLabel.setText(""+player2.calculateScore());
    }
    void changeTurn() {
    	updateScore();
    	if (isEndGame(board, player1, player2)) {
    		// GameOver sounds
    	    File file = new File("src/application/gui/music/GameOverSound.mp3");
    	    System.out.println(file.exists());
    	    media = new Media(file.toURI().toString());
    	    MediaManager.setSoundPlayer(media);
    	    MediaManager.getSoundPlayer().play();
    		// Show alert
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("Game Over");
    		alert.setHeaderText("Game Ended!");
    		if (player1.calculateScore() > player2.calculateScore()) {
    		    System.out.println("Player 1 wins.");
    		    System.out.println("Player 1's score is: " + player1.calculateScore());
    		    System.out.println("Player 2's score is: " + player2.calculateScore());
    		    alert.setContentText("Player 1 wins.\nPlayer 1's score is: " + player1.calculateScore() + "\nPlayer 2's score is: " + player2.calculateScore());
    		} else if (player1.calculateScore() < player2.calculateScore()) {
    		    System.out.println("Player 2 wins.");
    		    System.out.println("Player 1's score is: " + player1.calculateScore());
    		    System.out.println("Player 2's score is: " + player2.calculateScore());
    		    alert.setContentText("Player 2 wins.\nPlayer 1's score is: " + player1.calculateScore() + "\nPlayer 2's score is: " + player2.calculateScore());
    		} else {
    		    System.out.println("Draw");
    		    System.out.println("The score of both players is: " + player1.calculateScore());
    		    alert.setContentText("Draw\nThe score of both players is: " + player1.calculateScore());
    		}

    		ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
    		ButtonType quitButton = new ButtonType("Quit", ButtonData.CANCEL_CLOSE);

    		alert.getButtonTypes().setAll(okButton, quitButton);

    		alert.showAndWait().ifPresent(response -> {
    		    if (response == okButton) {
    		        alert.close(); // Close the alert, nothing extra needed since this is default behavior for OK button
    		    } else if (response == quitButton) {
    		        Platform.exit(); // Exit the application
    		    }
    		});
    	} else {
	    	if(player1.isInTurn()) {
				Player2.setTextFill(Color.web("#d92121"));
				Player1.setTextFill(Color.BLACK);
	    		player1.setInTurn(false);
	    		player2.setInTurn(true);
				if (checkEmpty(player2)) {
					int numDispatch = player2.getNumOfSmallPiecesCaptured();
					if(numDispatch < 5) {
						player2.borrowPiecesFrom(player1, 5 - numDispatch);
						borrowPieces(player2, 5- numDispatch);
					} 
					dispatchCells(player2);
					updateScore();
				}	
	    	} else {
				Player1.setTextFill(Color.web("#d92121"));
				Player2.setTextFill(Color.BLACK);
	    		player1.setInTurn(true);
	    		player2.setInTurn(false);
				if (checkEmpty(player1)) {
					int numDispatch = player1.getNumOfSmallPiecesCaptured();
					if(numDispatch < 5) {
						player1.borrowPiecesFrom(player2, 5 - numDispatch);
						borrowPieces(player1, 5 - numDispatch);
					} 
					dispatchCells(player1);
					updateScore();
				}	
	    	}
    	}
    }
    private void borrowPieces(Player p, int num) {
    	 if(p.equals(player1)) {
     	    List<ImageView> toRemove = new ArrayList<>();
     		int count = 0;
     		for (ImageView imgView : P2ScoreImageView) {
     			if(!imgView.equals(bigPiece1) && !imgView.equals(bigPiece2)) {
     				addPieceToCell(P1Score, imgView);
     				toRemove.add(imgView);
     				count++;
     				if(count == num) {
     					break;
     				}
     			}
     		}
     		P2ScoreImageView.removeAll(toRemove);
     	} else {
     	    List<ImageView> toRemove = new ArrayList<>();
     		int count = 0;
     		for (ImageView imgView : P1ScoreImageView) {
     			if(!imgView.equals(bigPiece1) && !imgView.equals(bigPiece2)) {
     				addPieceToCell(P2Score, imgView);
     				toRemove.add(imgView);
     				count++;
     				if(count == num) {
     					break;
     				}
     			}
     		}
     		P1ScoreImageView.removeAll(toRemove);
     	}
    }
	private boolean isEndGame(Board board, Player player1, Player player2) {
		int emptyHalfCircles = 0;
		for (Cell c : board.getCellList()) {
			if (c instanceof HalfCircle && c.isEmpty()) {
				emptyHalfCircles++;
			} 
		}
		if (emptyHalfCircles == 2) {
			// Capture the remaining pieces on player's side
			for (Cell c1 : player1.getCellsOnPlayerSide()) {
				if(!c1.isEmpty()) {
					player1.capturePiecesFrom(c1);
				}
			}
			for (Cell c2 : player2.getCellsOnPlayerSide()) {
				if(!c2.isEmpty()) {
					player2.capturePiecesFrom(c2);
				}
			}
			return true;
		} else {
			return false;
		}
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

    private void addPiecesToCell(Rectangle rectangle, int numPieces, List<ImageView> placedPieces) {
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

            pieceView.setX(xPos);
            pieceView.setY(yPos);

            root.getChildren().add(pieceView);
            placedPieces.add(pieceView);
        }
    }

    private boolean isOverlapping(double xPos, double yPos, ImageView piece1, ImageView piece2) {
        return xPos < piece2.getX() + piece2.getFitWidth() &&
               xPos + piece1.getFitWidth() > piece2.getX() &&
               yPos < piece2.getY() + piece2.getFitHeight() &&
               yPos + piece1.getFitHeight() > piece2.getY();
    }
    
    private void addBigPieceToHalfCircle() {
        bigPiece1 = new ImageView(bigPieceImage);
        bigPiece2 = new ImageView(bigPieceImage);
        
        bigPiece1.setFitHeight(30);  // Adjust size as \\needed
        bigPiece1.setFitWidth(30);   // Adjust size as needed
        double xPos = P1HalfCircle.getLayoutX() + random.nextDouble() * (P1HalfCircle.getWidth() - bigPiece1.getFitWidth());
        double yPos = P1HalfCircle.getLayoutY() + random.nextDouble() * (P1HalfCircle.getHeight() - bigPiece1.getFitHeight());
        bigPiece1.setX(xPos);
        bigPiece1.setY(yPos);
        cellImageViews.get(getIndex(cells[0])).add(bigPiece1);
        
        
        bigPiece2.setFitHeight(30);  // Adjust size as \\needed
        bigPiece2.setFitWidth(30);   // Adjust size as needed
        xPos = P2HalfCircle.getLayoutX() + random.nextDouble() * (P2HalfCircle.getWidth() - bigPiece2.getFitWidth());
        yPos = P2HalfCircle.getLayoutY() + random.nextDouble() * (P2HalfCircle.getHeight() - bigPiece2.getFitHeight());
        bigPiece2.setX(xPos);
        bigPiece2.setY(yPos);
        cellImageViews.get(getIndex(cells[6])).add(bigPiece2);
        root.getChildren().add(bigPiece1);
        root.getChildren().add(bigPiece2);
    }
    private int getIndex(Rectangle rectangle) {
       	int index = -1;
    	for(int i = 0; i < cells.length; i++) {
    		if (rectangle.equals(cells[i])) {
    			index = i;
    		}
    	}
    	return index;
    }
    private Rectangle getNextCellClockwise(Rectangle rectangle) {
    	if(getIndex(rectangle) == cells.length - 1) {
    		return cells[0];
    	} else {
    		return cells[getIndex(rectangle) + 1];
    	}
    }
    private Rectangle getNextCellCounterClockwise(Rectangle rectangle) {
    	if(getIndex(rectangle) == 0) {
    		return cells[cells.length - 1];
    	} else {
    		return cells[getIndex(rectangle) - 1];
    	}
    }
    private void addPieceToCell(Rectangle rectangle, ImageView imageView){
    	boolean overlapping;
        double xPos = 0, yPos = 0;
        if (!rectangle.equals(P1Score) && !rectangle.equals(P2Score)) {
	        do {
	            overlapping = false;
	
	            // Calculate random position within the rectangle
	            xPos = rectangle.getLayoutX() + random.nextDouble() * (rectangle.getWidth() - imageView.getFitWidth());
	            yPos = rectangle.getLayoutY() + random.nextDouble() * (rectangle.getHeight() - imageView.getFitHeight());
	
	            // Check for overlap with existing pieces
	            for (ImageView placedPiece : cellImageViews.get(getIndex(rectangle))) {
	                if (isOverlapping(xPos, yPos, imageView, placedPiece)) {
	                    overlapping = true;
	                    break;
	                }
	            }
	        } while (overlapping);
	        imageView.setX(xPos);
	        imageView.setY(yPos);
	        cellImageViews.get(getIndex(rectangle)).add(imageView);
        } else {
        	xPos = rectangle.getLayoutX() + random.nextDouble() * (rectangle.getWidth() - imageView.getFitWidth());
            yPos = rectangle.getLayoutY() + random.nextDouble() * (rectangle.getHeight() - imageView.getFitHeight());
	        imageView.setX(xPos);
	        imageView.setY(yPos);
	        if (rectangle.equals(P1Score)) {
	        	P1ScoreImageView.add(imageView);
	        } else {
	        	P2ScoreImageView.add(imageView);
	        }
        }
    }
    private void finishAction() {
        actionDone = true;
        Platform.runLater(this::changeTurn);
    }
    private void distributeCell(Rectangle cell, boolean clockwise, Rectangle target) {
        int index = getIndex(cell);
        List<ImageView> temp = cellImageViews.get(index);
        actionDone = false;
        if (temp.isEmpty()) {
        	finishAction();
        	return;
        }

        Timeline timeline = new Timeline();
        Rectangle[] currentCell = new Rectangle[]{cell};  // Use an array to hold the cell
        for (int i = 0; i < temp.size(); i++) {
            KeyFrame keyFrame = new KeyFrame(Duration.millis((i + 1) * 500), event -> {
                if (clockwise) {
                    currentCell[0] = getNextCellClockwise(currentCell[0]);
                } else {
                    currentCell[0] = getNextCellCounterClockwise(currentCell[0]);
                }
                addPieceToCell(currentCell[0], temp.remove(temp.size() - 1));
            });
            timeline.getKeyFrames().add(keyFrame);
        }
        timeline.setOnFinished(event -> {
        	if (clockwise) {
        		System.out.println(temp.size());
        		Rectangle nextCell = getNextCellClockwise(currentCell[0]);
	            if (! nextCell.equals(cells[0]) && !nextCell.equals(cells[6])) {
	                if (!cellImageViews.get(getIndex(nextCell)).isEmpty()) {
	                    distributeCell(nextCell, clockwise, target);
	                } else {
	                    captureCell(getNextCellClockwise(nextCell), target, clockwise);
	                    finishAction();
	                }
	            } else {
	            	 finishAction();
	            }
        	} else {
          		System.out.println(temp.size());
        		Rectangle nextCell = getNextCellCounterClockwise(currentCell[0]);
	            if (! nextCell.equals(cells[0]) && !nextCell.equals(cells[6])) {
	                if (!cellImageViews.get(getIndex(nextCell)).isEmpty()) {
	                    distributeCell(nextCell, clockwise, target);
	                } else {
	                    captureCell(getNextCellCounterClockwise(nextCell), target, clockwise);
	                    finishAction();
	                }
	            } else {
	            	 finishAction();
	            }
        	}
        });
        timeline.play();
    }

    private void captureCell(Rectangle cell, Rectangle target, boolean clockwise) {
        if (!cellImageViews.get(getIndex(cell)).isEmpty()){
        	// Use an array to hold the cell
        	for (ImageView imgView : cellImageViews.get(getIndex(cell))) {
        		addPieceToCell(target, imgView);
        	}
        	cellImageViews.get(getIndex(cell)).clear();
        	if(clockwise) {
        		Rectangle next_Cell = getNextCellClockwise(cell);
        		if (cellImageViews.get(getIndex(next_Cell)).isEmpty()) {
        			captureCell(getNextCellClockwise(next_Cell), target, true);
        		}
        	} else {
        		Rectangle next_Cell = getNextCellCounterClockwise(cell);
        		if (cellImageViews.get(getIndex(next_Cell)).isEmpty()) {
        			captureCell(getNextCellCounterClockwise(next_Cell), target, false);
        		}
        	}
        }
        
    }
    
    void dispatchCells(Player player) {
    	if (player.equals(player1)) {
    	    List<ImageView> toRemove = new ArrayList<>();
    		int count = 0;
    		int index = 1;
    		for (ImageView imgView : P1ScoreImageView) {
    			if(!imgView.equals(bigPiece1) && !imgView.equals(bigPiece2)) {
    				addPieceToCell(cells[index], imgView);
    				toRemove.add(imgView);
    				index++;
    				count++;
    				if(count == 5) {
    					break;
    				}
    			}
    		}
    		P1ScoreImageView.removeAll(toRemove);
    		player1.dispatch();
    	} else {
    		int count = 0;
    		int index = 7;
    	    List<ImageView> toRemove = new ArrayList<>();
    		for (ImageView imgView : P2ScoreImageView) {
    			if(!imgView.equals(bigPiece1) && !imgView.equals(bigPiece2)) {
    				addPieceToCell(cells[index], imgView);
    				toRemove.add(imgView);
    				index++;
    				count++;
    				if(count == 5) {
    					break;
    				}
    			}
    		}
    		P2ScoreImageView.removeAll(toRemove);
    		player2.dispatch();
    	}
    }
    boolean checkEmpty(Player player) {
    	if (player.equals(player1)) {
    		for (int i = 1; i <= 5; i++) {
    			if(!cellImageViews.get(i).isEmpty()) {
    				return false;
    			}
    		}
    		return true;
    	} else {
    		for (int i = 7; i <= 11; i++) {
    			if(!cellImageViews.get(i).isEmpty()) {
    				return false;
    			}
    		}
    		return true;
    	}
    }

    void changeCellColor(Rectangle rect, String color) {
		rect.setFill(Color.web(color));
	}
    
    @FXML
    void c10Clockwise(ActionEvent event) {
        player2.getPiecesFromCell(board.getCellList().get(10));
        player2.distributePieces(board.getCellList().get(10), "CLOCKWISE");
    	distributeCell(P2Cell10, true, P2Score);
		unselectCells();
    }

    @FXML
    void c10CounterClockwise(ActionEvent event) {
        player2.getPiecesFromCell(board.getCellList().get(10));
        player2.distributePieces(board.getCellList().get(10), "COUNTERCLOCKWISE");
    	distributeCell(P2Cell10, false, P2Score);
		unselectCells();
    }

    @FXML
    void c11Clockwise(ActionEvent event) {
        player2.getPiecesFromCell(board.getCellList().get(11));
        player2.distributePieces(board.getCellList().get(11), "CLOCKWISE");
    	distributeCell(P2Cell11, true, P2Score);
		unselectCells();
    }

    @FXML
    void c11CounterClockwise(ActionEvent event) {
        player2.getPiecesFromCell(board.getCellList().get(11));
        player2.distributePieces(board.getCellList().get(11), "COUNTERCLOCKWISE");
    	distributeCell(P2Cell11, false, P2Score);
		unselectCells();
    }

    @FXML
    void c1Clockwise(ActionEvent event) {
        player1.getPiecesFromCell(board.getCellList().get(1));
        player1.distributePieces(board.getCellList().get(1), "CLOCKWISE");
    	distributeCell(P1Cell1, true, P1Score);
		unselectCells();
    }

    @FXML
    void c1CounterClockwise(ActionEvent event) {
        player1.getPiecesFromCell(board.getCellList().get(1));
        player1.distributePieces(board.getCellList().get(1), "COUNTERCLOCKWISE");
    	distributeCell(P1Cell1, false, P1Score);
		unselectCells();
    }

    @FXML
    void c2Clockwise(ActionEvent event) {
        player1.getPiecesFromCell(board.getCellList().get(2));
        player1.distributePieces(board.getCellList().get(2), "CLOCKWISE");
    	distributeCell(P1Cell2, true, P1Score);
		unselectCells();
    }

    @FXML
    void c2CounterClockwise(ActionEvent event) {
        player1.getPiecesFromCell(board.getCellList().get(2));
        player1.distributePieces(board.getCellList().get(2), "COUNTERCLOCKWISE");
    	distributeCell(P1Cell2, false, P1Score);
		unselectCells();
    }

    @FXML
    void c3Clockwise(ActionEvent event) {
        player1.getPiecesFromCell(board.getCellList().get(3));
        player1.distributePieces(board.getCellList().get(3), "CLOCKWISE");
    	distributeCell(P1Cell3, true, P1Score);
		unselectCells();
    }

    @FXML
    void c3CounterClockwise(ActionEvent event) {
        player1.getPiecesFromCell(board.getCellList().get(3));
        player1.distributePieces(board.getCellList().get(3), "COUNTERCLOCKWISE");
    	distributeCell(P1Cell3, false, P1Score);
		unselectCells();
    }

    @FXML
    void c4Clockwise(ActionEvent event) {
        player1.getPiecesFromCell(board.getCellList().get(4));
        player1.distributePieces(board.getCellList().get(4), "CLOCKWISE");
    	distributeCell(P1Cell4, true, P1Score);
		unselectCells();
    }

    @FXML
    void c4CounterClockwise(ActionEvent event) {
        player1.getPiecesFromCell(board.getCellList().get(4));
        player1.distributePieces(board.getCellList().get(4), "COUNTERCLOCKWISE");
    	distributeCell(P1Cell4, false, P1Score);
		unselectCells();
    }

    @FXML
    void c5Clockwise(ActionEvent event) {
        player1.getPiecesFromCell(board.getCellList().get(5));
        player1.distributePieces(board.getCellList().get(5), "CLOCKWISE");
    	distributeCell(P1Cell5, true, P1Score);
		unselectCells();
    }

    @FXML
    void c5CounterClockwise(ActionEvent event) {
        player1.getPiecesFromCell(board.getCellList().get(5));
        player1.distributePieces(board.getCellList().get(5), "COUNTERCLOCKWISE");
    	distributeCell(P1Cell5, false, P1Score);
		unselectCells();
    }

    @FXML
    void c7Clockwise(ActionEvent event) {
        player2.getPiecesFromCell(board.getCellList().get(7));
        player2.distributePieces(board.getCellList().get(7), "CLOCKWISE");
    	distributeCell(P2Cell7, true, P2Score);
		unselectCells();
    }

    @FXML
    void c7CounterClockwise(ActionEvent event) {
        player2.getPiecesFromCell(board.getCellList().get(7));
        player2.distributePieces(board.getCellList().get(7), "COUNTERCLOCKWISE");
    	distributeCell(P2Cell7, false, P2Score);
		unselectCells();
    }

    @FXML
    void c8Clockwise(ActionEvent event) {
        player2.getPiecesFromCell(board.getCellList().get(8));
        player2.distributePieces(board.getCellList().get(8), "CLOCKWISE");
    	distributeCell(P2Cell8, true, P2Score);
		unselectCells();
    }

    @FXML
    void c8CounterClockwise(ActionEvent event) {
        player2.getPiecesFromCell(board.getCellList().get(8));
        player2.distributePieces(board.getCellList().get(8), "COUNTERCLOCKWISE");
    	distributeCell(P2Cell8, false, P2Score);
		unselectCells();
    }

    @FXML
    void c9Clockwise(ActionEvent event) {
        player2.getPiecesFromCell(board.getCellList().get(9));
        player2.distributePieces(board.getCellList().get(9), "CLOCKWISE");
    	distributeCell(P2Cell9, true, P2Score);
		unselectCells();
    }

    @FXML
    void c9CounterClockwise(ActionEvent event) {        
    	player2.getPiecesFromCell(board.getCellList().get(9));
    	player2.distributePieces(board.getCellList().get(9), "COUNTERCLOCKWISE");
    	distributeCell(P2Cell9, false, P2Score);
		unselectCells();
    }
    @FXML
    void selectCell1(MouseEvent event) {
    	if (isCellSelected == false && player1.isInTurn() && !cellImageViews.get(1).isEmpty()) {
    		isCellSelected = true;
    		unselectCells();
    		changeCellColor(P1Cell1, "#f9d97b");
        	Cell1ClockwiseButton.setVisible(true);
        	Cell1ClockwiseButton.toFront();
        	Cell1CounterClockwiseButton.setVisible(true);
        	Cell1CounterClockwiseButton.toFront();
    	} else {
    		unselectCells();
    		isCellSelected = false;
    	}
    }

    @FXML
    void selectCell10(MouseEvent event) {
    	if (isCellSelected == false &&  player2.isInTurn()&& !cellImageViews.get(10).isEmpty()){
    		isCellSelected = true;
    		unselectCells();
    		changeCellColor(P2Cell10, "#f9d97b");
        	Cell10ClockwiseButton.setVisible(true);
        	Cell10ClockwiseButton.toFront();
        	Cell10CounterClockwiseButton.setVisible(true);
        	Cell10CounterClockwiseButton.toFront();
    	} else {
    		unselectCells();
    		isCellSelected = false;
    	}
    }

    @FXML
    void selectCell11(MouseEvent event) {
    	if (isCellSelected == false&& player2.isInTurn()&& !cellImageViews.get(11).isEmpty()) {
    		isCellSelected = true;
    		unselectCells();
    		changeCellColor(P2Cell11, "#f9d97b");
        	Cell11ClockwiseButton.setVisible(true);
        	Cell11ClockwiseButton.toFront();
        	Cell11CounterClockwiseButton.setVisible(true);
        	Cell11CounterClockwiseButton.toFront();
    	} else {
    		unselectCells();
    		isCellSelected = false;
    	}
    }

    @FXML
    void selectCell2(MouseEvent event) {
    	if (isCellSelected == false && player1.isInTurn()&& !cellImageViews.get(2).isEmpty()) {
    		isCellSelected = true;
    		unselectCells();
    		changeCellColor(P1Cell2, "#f9d97b");
        	Cell2ClockwiseButton.setVisible(true);
        	Cell2ClockwiseButton.toFront();
        	Cell2CounterClockwiseButton.setVisible(true);
        	Cell2CounterClockwiseButton.toFront();
    	} else {
    		unselectCells();
    		isCellSelected = false;
    	}
    }

    @FXML
    void selectCell3(MouseEvent event) {
    	if (isCellSelected == false && player1.isInTurn()&& !cellImageViews.get(3).isEmpty()) {
    		isCellSelected = true;
    		unselectCells();
    		changeCellColor(P1Cell3, "#f9d97b");
        	Cell3ClockwiseButton.setVisible(true);
        	Cell3ClockwiseButton.toFront();;
        	Cell3CounterClockwiseButton.setVisible(true);
        	Cell3CounterClockwiseButton.toFront();
    	} else {
    		unselectCells();
    		isCellSelected = false;
    	}
    }

    @FXML
    void selectCell4(MouseEvent event) {
    	if (isCellSelected == false && player1.isInTurn()&& !cellImageViews.get(4).isEmpty()) {
    		isCellSelected = true;
    		unselectCells();
    		changeCellColor(P1Cell4, "#f9d97b");
        	Cell4ClockwiseButton.setVisible(true);
        	Cell4ClockwiseButton.toFront();
        	Cell4CounterClockwiseButton.setVisible(true);
        	Cell4CounterClockwiseButton.toFront();
    	} else {
    		unselectCells();
    		isCellSelected = false;
    	}
    }

    @FXML
    void selectCell5(MouseEvent event) {
    	if (isCellSelected == false && player1.isInTurn()&& !cellImageViews.get(5).isEmpty()) {
    		isCellSelected = true;
    		unselectCells();
    		changeCellColor(P1Cell5, "#f9d97b");
        	Cell5ClockwiseButton.setVisible(true);
        	Cell5ClockwiseButton.toFront();
        	Cell5CounterClockwiseButton.setVisible(true);
        	Cell5CounterClockwiseButton.toFront();
    	} else {
    		unselectCells();
    		isCellSelected = false;
    	}
    }

    @FXML
    void selectCell7(MouseEvent event) {
    	if (isCellSelected == false && player2.isInTurn()&& !cellImageViews.get(7).isEmpty()) {
    		isCellSelected = true;
    		unselectCells();
    		changeCellColor(P2Cell7, "#f9d97b");
        	Cell7ClockwiseButton.setVisible(true);
        	Cell7ClockwiseButton.toFront();
        	Cell7CounterClockwiseButton.setVisible(true);
        	Cell7CounterClockwiseButton.toFront();
    	} else {
    		unselectCells();
    		isCellSelected = false;
    	}
    }

    @FXML
    void selectCell8(MouseEvent event) {
    	if (isCellSelected == false && player2.isInTurn()&& !cellImageViews.get(8).isEmpty()) {
    		isCellSelected = true;
    		unselectCells();
    		changeCellColor(P2Cell8, "#f9d97b");
        	Cell8ClockwiseButton.setVisible(true);
        	Cell8ClockwiseButton.toFront();
        	Cell8CounterClockwiseButton.setVisible(true);
        	Cell8CounterClockwiseButton.toFront();
    	} else {
    		unselectCells();
    		isCellSelected = false;
    	}
    }

    @FXML
    void selectCell9(MouseEvent event) {
    	if (isCellSelected == false&& player2.isInTurn()&& !cellImageViews.get(9).isEmpty()) {
    		isCellSelected = true;
    		unselectCells();
    		changeCellColor(P2Cell9, "#f9d97b");
        	Cell9ClockwiseButton.setVisible(true);
        	Cell9ClockwiseButton.toFront();
        	Cell9CounterClockwiseButton.setVisible(true);
        	Cell9CounterClockwiseButton.toFront();
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
        for (Rectangle rect : cells) {
        	changeCellColor(rect, "#ced9e4");
        }
    }
}
