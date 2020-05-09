import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.util.tools.ArrayUtils;

public class Nonogram {
    int N;
    int M;
    int[][][] Blocks;
    Model model;
    IntVar[][] cells;
    DFS d = new DFS();
    public static IntVar[] getColumn(IntVar[][] array, int index,int size){
        IntVar[] column = new IntVar[size]; // Here I assume a rectangular 2D array!
        for(int i=0; i<column.length; i++){
            column[i] = array[i][index];
        }
        System.out.println(column.length);
        return column;
    }

    public Nonogram(int n, int m, int[][][] blocks) {
        N = n;
        M = m;
        Blocks = blocks;
        model  = new Model("Nonogram");
        cells = model.intVarMatrix( "c",M,N, 0,1);
        for (int i = 0; i < cells.length; i++) {
            System.out.printf("\t");
            for (int j = 0; j < cells[i].length; j++) {
                System.out.printf("#");
            }
            System.out.printf("\n");
        }
    }

    public void solver(){
        for (int i = 0; i < M; i++) {
            d.DFA(cells[i], Blocks[1][i], model);
        }
        for (int j = 0; j < N; j++) {
            d.DFA(ArrayUtils.getColumn(cells,j), Blocks[0][j], model);
        }
        if(model.getSolver().solve()){
            for (int i = 0; i < cells.length; i++) {
                System.out.printf("\t");
                for (int j = 0; j < cells[i].length; j++) {
                    System.out.printf(cells[i][j].getValue() == 1 ? "#" : " ");
                }
                System.out.printf("\n");
            }
        }else{
            System.out.println("Unsolvable");
        }
    }
}
