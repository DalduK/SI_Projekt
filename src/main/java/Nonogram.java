import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.util.tools.ArrayUtils;

public class Nonogram {
    int N;
    int M;
    Integer[][][] Blocks;
    Model model;
    BoolVar[][] cells;
    DFS d = new DFS();
    public static BoolVar[] getColumn(BoolVar[][] array, int index,int size){
        BoolVar[] column = new BoolVar[size]; // Here I assume a rectangular 2D array!
        for(int i=0; i<column.length; i++){
            column[i] = array[index][i];
        }
        return column;
    }

    public Nonogram(int n, int m, Integer[][][] blocks) {
        N = n;
        M = m;
        Blocks = blocks;
        model  = new Model("Nonogram");
        cells = model.boolVarMatrix("c",M,N);
    }

    public void solver(){
        for (int i = 0; i < M; i++) {
            d.DFA2(cells[i], Blocks[0][i], model);
        }
        for (int j = 0; j < N; j++) {
            d.DFA2(ArrayUtils.getColumn(cells,j), Blocks[1][j], model);
        }
        Solver solver = model.getSolver();
        if(solver.solve()){
            for (int i = 0; i < cells.length; i++) {
                System.out.printf("\t");
                for (int j = 0; j < cells[i].length; j++) {
                    System.out.printf(cells[i][j].getValue() == 1 ? "██ " : "   ");
                }
                System.out.printf("\n");
            }
        }else if(solver.hasEndedUnexpectedly()){
            System.out.println("The solver could not find a solution nor prove that none exists in the given limits");
        }else {
            System.out.println("The solver has proved the problem has no solution");
        }
    }
}
