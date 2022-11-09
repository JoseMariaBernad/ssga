package ga.ssGA;

import java.util.ArrayList;
import java.util.List;

public class Comparator {

    public static final int NUM_EXECUTIONS = 30;

    public static void main(String[] args) {
        List<Execution> executions = new ArrayList<>();
        executions.addAll(generateExecutions(512, 1, 0.8)); // Base line
        executions.addAll(generateExecutions(512, 1, 0.9)); // Changed Crossover
        executions.addAll(generateExecutions(512, 2, 0.8)); // Changed Mutation probability


        executions.addAll(generateExecutions(1024, 2, 0.8));
        executions.addAll(generateExecutions(1024, 2, 0.9));
        executions.addAll(generateExecutions(1024, 4, 0.8));


        executions.parallelStream().forEach(execution -> {
            try {
                execution.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println("SolutionFound,Evaluations,Fitness,CrossoverProbability,MutationProbability(MutantGenes/GeneNumber),GeneNumber,DeviationFromOptimum(%)");
        for (Execution execution : executions) {
            System.out.println(execution.isSolutionFound()
                    + "," + execution.getEvaluations()
                    + "," + execution.getFitness()
                    + "," + execution.getCrossoverProbability()
                    + "," + execution.getMutationProbability()
                    + "," + execution.getGeneNumber()
                    + "," + execution.getDeviationFromOptimumPercentage());
        }
    }

    private static List<Execution> generateExecutions(int geneNumber, int mutantGenes, double crossoverProbability) {
        List<Execution> executions = new ArrayList<>();
        for (int x = 0; x< NUM_EXECUTIONS; x++) {
            executions.add(new Execution(geneNumber, mutantGenes, crossoverProbability));
        }
        return executions;
    }
}
