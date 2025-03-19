import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class B11000_강의실배정 {
    static class Lecture implements Comparable<Lecture>{
        int start;
        int end;
        public Lecture(int start, int end){
            this.start = start;
            this.end = end;
        }
        @Override
        public int compareTo(Lecture o){
            return this.start - o.start;
        }
    }
    static Lecture[] lectures;
    static int N;

    public static void main(String[] args) {
        input();
        Arrays.sort(lectures);
        int answer = calculateMinRoom();
        System.out.println(answer);
    }

    static void input(){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        lectures = new Lecture[N];
        for(int i = 0; i<N; i++){
            int st = sc.nextInt();
            int ed = sc.nextInt();
            lectures[i] = new Lecture(st, ed);
        }
    }

    static int calculateMinRoom(){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(lectures[0].end);

        for(int i = 1; i<N; i++){
            if(pq.peek() <= lectures[i].start) pq.poll();
            pq.add(lectures[i].end);
        }
        return pq.size();
    }
}
