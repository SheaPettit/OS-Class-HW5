import java.util.Random;
public class CpuSchedulingAnalysis {
  public static void main(String[] args) {
    CpuSchedule.firstComeFirstServe(generate(50, 4, 16), 1000).printStats();
  }
  public static int[][] generate(int jobNum, int maxSimulArrival, int maxArrivalIncrement) {
    int[][] jobs = new int[jobNum][2];
    int index = 0;
    int arrivalTime = 0;
    Random rand = new Random();
    while(index < jobNum) {
      int indexInLoop = index;
      for(int i = 0; i < Math.min(rand.nextInt(1,maxSimulArrival), (jobNum-index)); i++) {
        jobs[indexInLoop][0] = arrivalTime;
        jobs[indexInLoop][1] = rand.nextInt(2,43);
        indexInLoop++;
      }
      arrivalTime += ((rand.nextInt(1,maxArrivalIncrement)) * 1000);
      index = indexInLoop;
    }
    return jobs;
  }

  private static void printJobs(int[][] jobs) {
    for(int i = 0; i < jobs.length; i++) {
      System.out.println("Job #" + (i + 1) + ": Arrival - " + jobs[i][0] + " nano seconds, Burst - " + jobs[i][1] + " ms");
    }
  }

  
}
