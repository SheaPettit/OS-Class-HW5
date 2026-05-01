import java.util.concurrent.locks.LockSupport;
public class CpuSchedule{
  public static ScheduleStats firstComeFirstServe(long[][] jobs, int runtimeMs) {
    System.out.println("Running FCFS for " + runtimeMs + "ms");
    long runtimeNs = ((long) runtimeMs) * 1000000;
    int numJobs = jobs.length;
    int jobsCompleted = 0;
    int jobIndex = 0;
    long utilTime = 0;
    long totalTurnaround = 0;
    long startingTime = System.nanoTime();
    long endTime = startingTime + runtimeNs;
    long startBurst = System.nanoTime();
    long burstTime = startBurst + jobs[jobIndex][1];
    while(burstTime > System.nanoTime()){}
    while(true) {
      if(System.nanoTime() >= endTime) {
        utilTime += (Math.min(endTime, burstTime) - startBurst);
        break;
      }
      jobsCompleted++;
      utilTime += (burstTime - startBurst);
      totalTurnaround += (burstTime - (startingTime + jobs[jobIndex++][0]));
      long nextTime = startingTime + jobs[jobIndex][0];
      while(nextTime > System.nanoTime()){}
      startBurst = System.nanoTime();
      burstTime = startBurst + jobs[jobIndex][1];
      while(burstTime > System.nanoTime()){}
    }
    return new ScheduleStats("First come First serve",  runtimeNs, utilTime, totalTurnaround, jobsCompleted);
  }
  public static ScheduleStats shortestJobFirst(long[][] jobs, int runtimeMs) {
    System.out.println("Running SJF for " + runtimeMs + "ms");
    long runtimeNs = ((long) runtimeMs) * 1000000;
    int numJobs = jobs.length;
    int jobsCompleted = 0;
    int jobIndex = 0;
    long utilTime = 0;
    long totalTurnaround = 0;
    long startingTime = System.nanoTime();
    long endTime = startingTime + runtimeNs;
    long startBurst = System.nanoTime();
    long burstTime = startBurst + jobs[jobIndex][1];
    while(burstTime > System.nanoTime()){}
    while(true) {
      if(System.nanoTime() >= endTime) {
        utilTime += (Math.min(endTime, burstTime) - startBurst);
        break;
      }
      jobsCompleted++;
      utilTime += (burstTime - startBurst);
      totalTurnaround += (burstTime - (startingTime + jobs[jobIndex++][0]));
      long timeOffset = System.nanoTime() - startingTime;
      int swapIndex = jobIndex;
      for(int i = jobIndex; i < jobs.length; i++) {
        if(jobs[i][0] > timeOffset)
          break;
        if(jobs[i][1] < jobs[swapIndex][1])
          swapIndex = i;
      }
      if(swapIndex != jobIndex){
        long tempArrival = jobs[jobIndex][0];
        long tempBurst = jobs[jobIndex][1];
        jobs[jobIndex][0] = jobs[swapIndex][0];
        jobs[jobIndex][1] = jobs[swapIndex][1];
        jobs[swapIndex][0] = tempArrival;
        jobs[swapIndex][1] = tempBurst;
      }
      long nextTime = startingTime + jobs[jobIndex][0];
      while(nextTime > System.nanoTime()){}
      startBurst = System.nanoTime();
      burstTime = startBurst + jobs[jobIndex][1];
      while(burstTime > System.nanoTime()){}
    }
    return new ScheduleStats("Shortest job first",  runtimeNs, utilTime, totalTurnaround, jobsCompleted);
  }
}
