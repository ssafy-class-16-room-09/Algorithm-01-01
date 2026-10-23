import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;

class Solution {
    public int solution(String[][] bookTime) {
        List<Reservation> reservations = new LinkedList<>();

        for (String[] time : bookTime) {
            int start = toMinutes(time[0]);
            int end = toMinutes(time[1]) + 10;
            reservations.add(new Reservation(start, end));
        }

        reservations.sort((a, b) -> Integer.compare(a.start, b.start));

        TreeMap<Integer, Reservation> roomsByEndTime = new TreeMap<>();
        Iterator<Reservation> iterator = reservations.iterator();
        int roomCount = 0;

        while (iterator.hasNext()) {
            Reservation reservation = iterator.next();
            Integer reusableEndTime = roomsByEndTime.floorKey(reservation.start);

            if (reusableEndTime == null) {
                roomCount++;
            } else {
                Reservation reusableRoom = roomsByEndTime.get(reusableEndTime);
                if (reusableRoom.count > 1) {
                    reusableRoom.count--;
                } else {
                    roomsByEndTime.remove(reusableEndTime);
                }
            }

            Reservation roomWithSameEndTime = roomsByEndTime.get(reservation.end);
            if (roomWithSameEndTime == null) {
                roomsByEndTime.put(reservation.end, reservation);
            } else {
                roomWithSameEndTime.count++;
            }

            iterator.remove();
        }

        return roomCount;
    }

    private int toMinutes(String time) {
        StringTokenizer st = new StringTokenizer(time, ":");
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());
        return hour * 60 + minute;
    }
}

class Reservation {
    final int start;
    final int end;
    int count = 1;

    Reservation(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
