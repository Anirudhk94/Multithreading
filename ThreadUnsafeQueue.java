import java.util.*;

class ThreadUnsafeQueue {
  private Queue<Integer> q;

  public ThreadUnsafeQueue() {
    this.q = new LinkedList<>();
  }

  public void enqueue(int i) {
    this.q.add(i);
  }

  public int dequeue() {
    System.out.println(q);
    return (!q.isEmpty()) ? this.q.remove() : -1;
  }

  public int size() {
    return this.q.size();
  }
}

public class Main {
  public static Random rand = new Random();
  
  public static void main(String[] args) throws InterruptedException {
    ThreadUnsafeQueue tuq = new ThreadUnsafeQueue();

    Thread t1 = new Thread(new Runnable() {
      public void run() {
        for (int i = 0 ; i < 10000 ; i++) {
          tuq.enqueue(i);
        }
      }
    }); 

    Thread t2 = new Thread(new Runnable() {
      public void run() {
        for (int i = 10000 ; i < 20000 ; i++) {
          tuq.enqueue(i);
        }
      }
    }); 

    Thread t3 = new Thread(new Runnable() {
      public void run() {
        for (int i = 20000 ; i < 30000 ; i++) {
          tuq.enqueue(i);
        }
      }
    }); 

    t1.start();
    t2.start();
    t3.start();
    
    t1.join();
    t2.join();
    t3.join();

    System.out.println("Queue size: " + tuq.size());
  }
}
