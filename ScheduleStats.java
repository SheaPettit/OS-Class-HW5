public class ScheduleStats {
  private String cpuAlg;
  private double cpuUtilization;
  private double averageThroughput;
  private double averageTurnaround;
  public ScheduleStats(String alg, long time, long lostTime, int numJobsCompleted, long[][] turnAround) {
    cpuAlg = alg;
    cpuUtilization = (double) (time - lostTime) / time;
    averageThroughput = numJobsCompleted / ((double) time / 1000000000);
    double totalTurnaround = 0.0;
    for(int i = 0; i < numJobsCompleted; i++){
      totalTurnaround += ((double) (turnAround[i][1] - turnAround[i][0]) / 1000000);
    }
    averageTurnaround = totalTurnaround / numJobsCompleted;
  }
  public String getAlg() {
    return cpuAlg;
  }
  public double getUtil() {
    return cpuUtilization;
  }
  public double getThroughput() {
    return averageThroughput;
  }
  public double getTurnaround() {
    return averageTurnaround;
  }
}
