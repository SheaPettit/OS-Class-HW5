public class ScheduleStats {
  private String cpuAlg;
  private double cpuUtilizationPercent;
  private double averageThroughput;
  private double averageTurnaround;
  public ScheduleStats(String alg, long runTime, long utilTime, long totalTurnaround, int numJobsCompleted) {
    if(numJobsCompleted == 0)
      throw new IllegalArgumentException();
    cpuAlg = alg;
    cpuUtilizationPercent = (((double) utilTime / (double) runTime) * 100.0);
    averageThroughput = ((double) numJobsCompleted / ((double) runTime / 1000000000.0));
    averageTurnaround = (((double) totalTurnaround / 1000000.0) / (double) numJobsCompleted);
  }
  public void printStats() {
    System.out.println("Algorithm " + cpuAlg + " Statistics:");
    System.out.println("Cpu Utilization: " + cpuUtilizationPercent + "% of the time");
    System.out.println("Average Job Throughput: " + averageThroughput + " per second");
    System.out.println("Average Job Turnaround: " + averageTurnaround + " ms");
  }
}
