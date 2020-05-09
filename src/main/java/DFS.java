import org.chocosolver.solver.constraints.nary.automata.FA.FiniteAutomaton;
import org.chocosolver.solver.constraints.nary.automata.FA.IAutomaton;
import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.*;
import org.chocosolver.solver.variables.IntVar;

public class DFS {
    public void DFA(IntVar[] cells, int[] rest, Model model){
        System.out.println(cells.length);
        StringBuilder regex = new StringBuilder("0*");
        int m = rest.length;
        for (int i = 0; i < m; i++) {
            regex.append('1').append('{').append(rest[i]).append('}');
            regex.append('0');
            regex.append(i == m - 1 ? '*' : '+');
        }

        IAutomaton auto = new FiniteAutomaton(regex.toString());
        model.regular(cells, auto).post();
    }
    public void DFA2(IntVar[] cells, int[] seq, Model model) {
        FiniteAutomaton auto = new FiniteAutomaton();
        int si = auto.addState();
        auto.setInitialState(si); // declare it as initial state
        int i0 = auto.addState();
        auto.addTransition(si, i0, 0); // first transition
        auto.addTransition(i0, i0, 0); // second transition
        int from = i0;
        int m = seq.length;
        for (int i = 0; i < m; i++) {
            int ii = auto.addState();
            // word can start with '1'
            if(i == 0){
                auto.addTransition(si, ii, 1);
            }
            auto.addTransition(from, ii, 1);
            from = ii;
            for(int j = 1; j < seq[i]; j++){
                int jj = auto.addState();
                auto.addTransition(from, jj, 1);
                from = jj;
            }
            int ii0 = auto.addState();
            auto.addTransition(from, ii0, 0);
            auto.addTransition(ii0, ii0, 0);
            // the word can end with '1' or '0'
            if(i == m - 1){
                auto.setFinal(from, ii0);
            }
            from = ii0;
        }
        model.regular(cells, auto).post();
    }
}
