public class CpuSchedule{
  public static ScheduleStats firstComeFirstServe(int[][] jobs, int runtimeMs) {
    long runtimeNs = runtimeMs * 1000;
    int numJobs = jobs.length;
    int jobsCompleted = 0;
    int jobIndex = 0;
    long[][] turnaround = new long[jobs.length][2];
    int burstTime;
    long arrivalTime;
    long lostTime = 0;
    long startingTime = System.nanoTime();
    long endTime = startingTime + runtimeNs;
    burstTime = jobs[jobIndex][1];
    arrivalTime = System.nanoTime();
    turnaround[jobIndex][0] = arrivalTime;
    lostTime += (System.nanoTime() - startingTime);
    try {
      Thread.sleep(burstTime);
    } catch (Exception e) {
      e.printStackTrace();
    }
    while(true) {
      long completionTime = System.nanoTime();
      if(completionTime > endTime) {
        break;
      }
      jobsCompleted++;
      turnaround[jobIndex++][1] = completionTime;
      long nextTime = startingTime + jobs[jobIndex][0];
      while(true){
        if(System.nanoTime() >= nextTime)
          break;
      }
      burstTime = jobs[jobIndex][1];
      arrivalTime = System.nanoTime();
      turnaround[jobIndex][0] = arrivalTime;
      lostTime += (System.nanoTime() - completionTime);
      try {
        Thread.sleep(burstTime);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return new ScheduleStats("First come First serve",  (runtimeMs * 1000), lostTime, jobsCompleted, turnaround);
  }
}
