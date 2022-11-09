package ga.ssGA;

import java.util.ArrayList;
import java.util.List;

public class Comparator {

    public static final int NUM_EXECUTIONS = 30;

    public static void main(String[] args) throws Exception {
        List<Execution> executions = new ArrayList<>(NUM_EXECUTIONS);
        for (int x = 0; x< NUM_EXECUTIONS; x++) {
            executions.add(new Execution());
        }
        /*
        for(Execution execution : executions) {
            execution.execute();
        }
         */
        executions.parallelStream().forEach(execution -> {
            try {
                execution.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println("SolutionFound,Evaluations,Fitness");
        for (Execution execution : executions) {
            System.out.println(execution.isSolutionFound() + "," + execution.getEvaluations() + "," + execution.getFitness());
        }
    }
}
