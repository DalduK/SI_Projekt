import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import org.chocosolver.solver.variables.BoolVar;


public class NonogramAppearance extends Pane {

    private static final int Width = 1024;
    private static final int Heigth = 768;
    private Pane root;
    private static int TILE_SIZE = 40; // have to check how big is nonogram and set this value, for now 40

    private VBox[] topLabels;
    private HBox[] leftLabels;

    private static int X_TILES = 0;
    private static int Y_TILES = 0;

    private Tile[][] grid;

    Nonogram nonogram = setNonogram();

    public NonogramAppearance() {
        root = new Pane();
        initBoard();
        initLabels();

        getChildren().add(root);
    }


    private void initBoard() {

        nonogram.solver();
        System.out.println(nonogram.cells.length);
        System.out.println(nonogram.cells[0].length);
        Y_TILES = nonogram.cells.length;
        X_TILES = nonogram.cells[0].length;
        TILE_SIZE = Heigth / X_TILES;
        grid = new Tile[X_TILES][Y_TILES];

        for (int y = 0; y < X_TILES; y++) {
            for (int x = 0; x < Y_TILES; x++) {
                //setting tiles in nonogram shape
                Tile tile = new Tile(x, y, nonogram.cells[x][y]);
                grid[x][y] = tile;
                root.getChildren().add(tile);
            }
        }
        for (int y = 0; y < X_TILES; y++) {
            for (int x = 0; x < Y_TILES; x++) {
                Tile tile = grid[x][y];
            }
        }

    }

    private void initLabels() {
        topLabels = new VBox[X_TILES];//możliwe że odwrotnie
        leftLabels = new HBox[Y_TILES];
        for (int i = 0; i < X_TILES; i++) {
            topLabels[i] = new VBox();
            topLabels[i].setLayoutX(120 + i * (TILE_SIZE));

            for (int j = 0; j < nonogram.Blocks[1][i].length; j++) {
                String nValue = nonogram.Blocks[1][i][j].toString() + ", ";// przy każdej iteracji można zwięszać wielkość offsetu o rozmiar litery aby wszystkie się mieściły
                Label label = new Label(nValue);
                label.setFont(new Font(12.0));
                topLabels[i].getChildren().add(label);
            }
//            topLabels[i].getChildren().add(new Label("1"));
//            topLabels[i].getChildren().add(new Label("1"));
            root.getChildren().add(topLabels[i]);
        }
        for (int i = 0; i < Y_TILES; i++) {
            leftLabels[i] = new HBox();
            leftLabels[i].setLayoutY(100 + (TILE_SIZE / 2) + i * (TILE_SIZE));
            for (int j = 0; j < nonogram.Blocks[0][i].length; j++) {
                String nValue = nonogram.Blocks[0][i][j].toString() + ", ";
                Label label = new Label(nValue);
                label.setFont(new Font(12.0));
                leftLabels[i].getChildren().add(label);
            }
            root.getChildren().add(leftLabels[i]);
        }
    }


    public class Tile extends StackPane {
        private int x, y;
        private boolean isCorrect;
        private boolean isOpen = false;

        private Rectangle border = new Rectangle(TILE_SIZE - 1, TILE_SIZE - 1);

        public Tile(int x, int y, BoolVar isCorrect) {
            this.x = x;
            this.y = y;
            if (isCorrect.getValue() == 1) {
                this.isCorrect = true;
            } else if (isCorrect.getValue() == 0) {
                this.isCorrect = false;
            } else {
                System.out.println();
            }

            border.setStroke(Color.LIGHTGRAY);
            getChildren().addAll(border);

            setTranslateX(100 + y * TILE_SIZE);
            setTranslateY(100 + x * TILE_SIZE);
//            open();
            setOnMouseClicked(e -> open());

        }

        public void open() {
            if (isOpen) return;
            isOpen = true;
            if (isCorrect) border.setFill(Color.GREEN);
            else border.setFill(Color.RED);
        }
    }

    private Nonogram setNonogram() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Desktop\\chromedriver\\chromedriver.exe"); // sciezka do chrome drivera
//        Scrap s = new Scrap("7200"); //tutaj wpisujesz id ze strony https://webpbn.com/
//        System.out.println(s);
//        //przykłady 30463, 7200
//        Integer[][][] xml = s.getNon();
//        System.out.println(xml);
        Nonograms n = new Nonograms();
        Nonogram temp = new Nonogram(n.kotwica[1].length, n.kotwica[0].length, n.kotwica);

        return temp;
    }
}
