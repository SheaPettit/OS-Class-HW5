public class ScheduleStats {
  private String cpuAlg;
  private double cpuUtilization;
  private double averageThroughput;
  private double averageTurnaround;
  public ScheduleStats(String alg, long time, long lostTime, int numJobsCompleted, long[][] turnAround) {
    cpuAlg = alg;
    cpuUtilization = (((double) (time - lostTime)) * 100) / time;
    averageThroughput = numJobsCompleted / ((double) time / 1000000000);
    double totalTurnaround = 0.0;
    for(int i = 0; i < numJobsCompleted; i++){
      totalTurnaround += ((double) (turnAround[i][1] - turnAround[i][0]) / 1000000);
    }
    averageTurnaround = totalTurnaround / numJobsCompleted;
  }
  public void printStats() {
    System.out.println("Algorithm " + cpuAlg + " Statistics:");
    System.out.println("Cpu Utilization: " + cpuUtilization + "% of the time");
    System.out.println("Average Job Throughput: " + averageThroughput + " per second");
    System.out.println("Average Job Turnaround: " + averageTurnaround + " ms");
  }
}
