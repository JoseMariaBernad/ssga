package ga.ssGA;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ExecutionsLauncher {

    public static final int NUM_EXECUTIONS = 30;

    public static void main(String[] args) throws IOException {
        List<Execution> executions = new ArrayList<>();
        // Problem size of 512
        executions.addAll(generateExecutions(512, 1, 0.8)); // Base line
        executions.addAll(generateExecutions(512, 1, 0.9)); // Changed Crossover
        executions.addAll(generateExecutions(512, 2, 0.8)); // Changed Mutation probability

        // Problem size of 1024
        executions.addAll(generateExecutions(1024, 2, 0.8)); // Base line
        executions.addAll(generateExecutions(1024, 2, 0.9)); // Changed Crossover
        executions.addAll(generateExecutions(1024, 4, 0.8)); // Changed Mutation probability


        executions
                .parallelStream()
                .forEach(execution -> {
            try {
                execution.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // Create CSV file with results of executions
        try (PrintStream out = new PrintStream(
                new FileOutputStream("C:\\git\\ssga\\Runs.csv", false))) {
            out.println("SolutionFound,Evaluations,Fitness,CrossoverProbability,MutationProbability(MutantGenes/GeneNumber),GeneNumber,DeviationFromOptimum(%),ExecutionNumber");
            for (Execution execution : executions) {
                out.println(execution.isSolutionFound()
                        + "," + execution.getEvaluations()
                        + "," + execution.getFitness()
                        + "," + execution.getCrossoverProbability()
                        + "," + execution.getMutationProbability()
                        + "," + execution.getGeneNumber()
                        + "," + execution.getDeviationFromOptimumPercentage()
                        + "," + execution.getExecutionNumber());
            }
        }
    }

    private static List<Execution> generateExecutions(int geneNumber, int mutantGenes, double crossoverProbability) {
        List<Execution> executions = new ArrayList<>();
        for (int x = 0; x< NUM_EXECUTIONS; x++) {
            executions.add(new Execution(x, geneNumber, mutantGenes, crossoverProbability));
        }
        return executions;
    }
}
