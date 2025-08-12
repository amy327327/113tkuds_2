import java.util.*;

public class MeetingRoomScheduler {

    public static int minMeetingRooms(int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int[] meeting : meetings) {
            if (!minHeap.isEmpty() && minHeap.peek() <= meeting[0]) {
                minHeap.poll();
            }
            minHeap.offer(meeting[1]);
        }
        return minHeap.size();
    }

    // 如果只有一個會議室，最大總會議時間
    public static int maxTotalTimeWithOneRoom(int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[1], b[1]));
        int[] dp = new int[meetings.length];
        dp[0] = meetings[0][1] - meetings[0][0];

        for (int i = 1; i < meetings.length; i++) {
            int includeTime = meetings[i][1] - meetings[i][0];
            int lastNonConflict = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (meetings[j][1] <= meetings[i][0]) {
                    lastNonConflict = j;
                    break;
                }
            }
            if (lastNonConflict != -1) {
                includeTime += dp[lastNonConflict];
            }
            dp[i] = Math.max(dp[i - 1], includeTime);
        }
        return dp[meetings.length - 1];
    }

    public static void main(String[] args) {
        int[][] m1 = {{0,30},{5,10},{15,20}};
        System.out.println(minMeetingRooms(m1)); // 2

        int[][] m2 = {{9,10},{4,9},{4,17}};
        System.out.println(minMeetingRooms(m2)); // 2

        int[][] m3 = {{1,5},{8,9},{8,9}};
        System.out.println(minMeetingRooms(m3)); // 2

        int[][] m4 = {{1,4},{2,3},{4,6}};
        System.out.println(maxTotalTimeWithOneRoom(m4)); // 5
    }
}

