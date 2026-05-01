import java.util.Random;
public class CpuSchedulingAnalysis {
  public static void main(String[] args) {
    int runtime = 0;
    int maxIncrement = 0;
    int maxSimul = 0;
    try {
      runtime = Integer.parseInt(args[0]);
      maxIncrement = Integer.parseInt(args[1]);
      maxSimul = Integer.parseInt(args[2]);
    } catch(Exception e) {
      System.err.println("Please use \"java CpuSchedulingAnalysis <runtime (ms)> <max job arrival increment (ms)> <max simultaneous job arrivals>\"");
      System.exit(1);
    }
    long[][] jobs = generate(runtime * 5, maxSimul, maxIncrement);
    CpuSchedule.firstComeFirstServe(copyJobs(jobs), runtime).printStats();
    CpuSchedule.shortestJobFirst(copyJobs(jobs), runtime).printStats();
  }
  public static long[][] generate(int jobNum, int maxSimulArrival, int maxArrivalIncrement) {
    long[][] jobs = new long[jobNum][2];
    int index = 0;
    long arrivalTime = 0;
    Random rand = new Random();
    while(index < jobNum) {
      int indexInLoop = index;
      for(int i = 0; i < Math.min(rand.nextInt(1,maxSimulArrival), (jobNum-index)); i++) {
        jobs[indexInLoop][0] = arrivalTime;
        jobs[indexInLoop][1] = (rand.nextInt(2,43) * 1000000);
        indexInLoop++;
      }
      arrivalTime += ((rand.nextInt(1,maxArrivalIncrement)) * 1000000);
      index = indexInLoop;
    }
    System.out.println("Finished making the jobs");
    return jobs;
  }

  private static void printJobs(long[][] jobs) {
    for(int i = 0; i < jobs.length; i++) {
      System.out.println("Job #" + (i + 1) + ": Arrival - " + jobs[i][0] + " nano seconds, Burst - " + jobs[i][1] + " nano seconds");
    }
  }
  private static long[][] copyJobs(long[][] jobs) {
    long[][] newJobs = new long[jobs.length][2];
    for(int i = 0; i < jobs.length; i++){
      newJobs[i][0] = jobs[i][0];
      newJobs[i][1] = jobs[i][1];
    }
    return newJobs;
  }
}
