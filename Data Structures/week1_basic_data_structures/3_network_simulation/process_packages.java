import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

class Request {
    public Request(int arrival_time, int process_time) {
        this.arrival_time = arrival_time;
        this.process_time = process_time;
    }

    public int arrival_time;
    public int process_time;
}

class Response {
    public Response(boolean dropped, int start_time) {
        this.dropped = dropped;
        this.start_time = start_time;
    }

    public boolean dropped;
    public int start_time;
}

class Buffer {
    public Buffer(int size) {
        this.size = size;
        this.finish_time = new ArrayDeque<Integer>();
    }

    public Response Process(Request request) {
        if(finish_time.isEmpty()){
            finish_time.addLast(request.process_time);
            size--;
            return new Response(false, request.arrival_time);    
        }
        else{
            for(int i = 0; i < finish_time.size(); i++){
                if(request.arrival_time >= finish_time.getFirst()){
                    finish_time.pop();
                    size++;
                    break;
                }                         
            }
            if(size == 0) return new Response(true, -1);
            if(size > 0){
                int lastElementFinishTime;
                if(finish_time.size() == 0) lastElementFinishTime = request.arrival_time;
                else lastElementFinishTime = finish_time.getLast();
                finish_time.addLast(lastElementFinishTime + request.process_time);   
                size--;
                return new Response(false, lastElementFinishTime);
            }        
        }
        return new Response(true, -1);      
    }

    private int size;
    private Deque<Integer> finish_time;
}

class process_packages {
    private static ArrayList<Request> ReadQueries(Scanner scanner) throws IOException {
        int requests_count = scanner.nextInt();
        ArrayList<Request> requests = new ArrayList<Request>();
        for (int i = 0; i < requests_count; ++i) {
            int arrival_time = scanner.nextInt();
            int process_time = scanner.nextInt();
            requests.add(new Request(arrival_time, process_time));
        }
        return requests;
    }

    private static ArrayList<Response> ProcessRequests(ArrayList<Request> requests, Buffer buffer) {
        ArrayList<Response> responses = new ArrayList<Response>();
        for (int i = 0; i < requests.size(); ++i) {
            responses.add(buffer.Process(requests.get(i)));
        }
        return responses;
    }

    private static void PrintResponses(ArrayList<Response> responses) {
        for (int i = 0; i < responses.size(); ++i) {
            Response response = responses.get(i);
            if (response.dropped) {
                System.out.println(-1);
            } else {
                System.out.println(response.start_time);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int buffer_max_size = scanner.nextInt();
        Buffer buffer = new Buffer(buffer_max_size);

        ArrayList<Request> requests = ReadQueries(scanner);
        ArrayList<Response> responses = ProcessRequests(requests, buffer);
        PrintResponses(responses);
    }
}
