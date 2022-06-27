import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class JobQueue {
    
    public class Thread{
        public long startTime;
        public int workerNumber;
    
        public Thread(long start, int worker){
            startTime = start;
            workerNumber = worker;
        }
    }
    
    private int numWorkers;
    private int[] jobs;
    private List<Thread> result = new ArrayList<Thread>();

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(result.get(i).workerNumber + " " + result.get(i).startTime);
        }
    }

    private void assignJobs() {
        // TODO: replace this code with a faster algorithm.
        // assignedWorker = new int[jobs.length];
        // startTime = new long[jobs.length];
        // long[] nextFreeTime = new long[numWorkers];
        // for (int i = 0; i < jobs.length; i++) {
        //     int duration = jobs[i];
        //     int bestWorker = 0;
        //     for (int j = 0; j < numWorkers; ++j) {
        //         if (nextFreeTime[j] < nextFreeTime[bestWorker])
        //             bestWorker = j;
        //     }
        //     assignedWorker[i] = bestWorker;
        //     startTime[i] = nextFreeTime[bestWorker];
        //     nextFreeTime[bestWorker] += duration;
        // }       
        Thread[] heap = new Thread[numWorkers];
        for(int i = 0; i < numWorkers; i++){
            heap[i] = new Thread(0, i);
        }
        int size = numWorkers;
        for(int i = 0; i < jobs.length; i++){
            Thread extracted = extractMin(heap, size);
            result.add(extracted);
            insert(heap, extracted.workerNumber, size, (long)jobs[i] + extracted.startTime);
        }
    }

    
    private void insert(Thread[] heap, int priority, int size, long startTime){
        heap[size - 1] = new Thread(startTime, priority);
        siftUp(heap, size - 1);
    }
    private Thread extractMin(Thread[] heap, int size){
        Thread result = heap[0];
        heap[0] = heap[size - 1];
        siftDown(heap, 0);
        return result;

    }
    private void siftUp(Thread[] heap, int i){
        int parent = (i - 1) / 2;
        
        if(heap[parent].startTime == heap[i].startTime){
            if(heap[parent].workerNumber > heap[i].workerNumber){
                Thread temp = heap[i];
                heap[i] = heap[(i - 1) / 2];
                heap[(i - 1) / 2] = temp;
                i = (i - 1) / 2;
                siftUp(heap, i);
            }
        }
        else if(heap[parent].startTime != heap[i].startTime){
            if(heap[parent].startTime > heap[i].startTime){
                Thread temp = heap[i];
                heap[i] = heap[(i - 1) / 2];
                heap[(i - 1) / 2] = temp;
                i = (i - 1) / 2;
                siftUp(heap, i);
            }
        }
    }
    private void siftDown(Thread[] heap, int i){
        int max = i;
        int left = i*2 + 1;
        int right = i*2 + 2;

        if(left < heap.length && heap[left].startTime == heap[max].startTime){
            if(heap[left].workerNumber < heap[max].workerNumber){
                max = left;
            }
        }
        else if(left < heap.length && heap[left].startTime != heap[max].startTime){
            if(heap[left].startTime < heap[max].startTime){
                max = left;
            }
        }

        if(right < heap.length && heap[right].startTime == heap[max].startTime){
            if(heap[right].workerNumber < heap[max].workerNumber){
                max = right;
            }
        }
        else if(right < heap.length && heap[right].startTime != heap[max].startTime){
            if(heap[right].startTime < heap[max].startTime){
                max = right;
            }
        }

        if(i != max){
          Thread temp = heap[i];
          heap[i] = heap[max];
          heap[max] = temp;
          siftDown(heap, max);
        }
      }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
