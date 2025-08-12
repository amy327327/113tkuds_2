import java.util.*;

public class MultiLevelCacheSystem {
    private static class CacheItem {
        int key;
        String value;
        int frequency;      // 存取頻率
        long timestamp;     // 最新存取時間
        int level;          // 所在層級 1~3

        CacheItem(int key, String value, long timestamp, int level) {
            this.key = key;
            this.value = value;
            this.frequency = 1;
            this.timestamp = timestamp;
            this.level = level;
        }

        // 計算評分，頻率越高、時間越近、層級越低越好
        // 儲存成本會影響層級決策
        double score(int accessCost) {
            return frequency * 1000.0 - timestamp - accessCost * 100;
        }

        @Override
        public String toString() {
            return String.format("Key=%d Val=%s Freq=%d Level=%d", key, value, frequency, level);
        }
    }

    private final int[] capacities = {2, 5, 10};
    private final int[] costs = {1, 3, 10};
    private final Map<Integer, CacheItem> map = new HashMap<>();
    private final List<PriorityQueue<CacheItem>> levels = new ArrayList<>();
    private long globalTimestamp = 0;

    public MultiLevelCacheSystem() {
        for (int i = 0; i < 3; i++) {
            final int idx = i;
            PriorityQueue<CacheItem> pq = new PriorityQueue<>((a, b) -> {
                // 評分低的先出（最該淘汰）
                return Double.compare(a.score(costs[idx]), b.score(costs[idx]));
            });
            levels.add(pq);
        }
    }

    public String get(int key) {
        if (!map.containsKey(key)) return null;

        CacheItem item = map.get(key);
        item.frequency++;
        item.timestamp = ++globalTimestamp;

        // 可能需要升級層級
        adjustLevel(item);

        return item.value;
    }

    public void put(int key, String value) {
        if (map.containsKey(key)) {
            CacheItem item = map.get(key);
            item.value = value;
            item.frequency++;
            item.timestamp = ++globalTimestamp;
            adjustLevel(item);
            return;
        }

        // 新增到最低層
        CacheItem newItem = new CacheItem(key, value, ++globalTimestamp, 3);
        map.put(key, newItem);
        levels.get(2).offer(newItem);

        // 若超出容量，移除最差的
        evictIfNeeded(2);

        // 嘗試升級
        adjustLevel(newItem);
    }

    private void adjustLevel(CacheItem item) {
        int oldLevel = item.level - 1;
        int newLevel = oldLevel;

        // 看看是否該升級層級（往上）
        while (newLevel > 0) {
            CacheItem topInUpper = levels.get(newLevel - 1).peek();
            if (topInUpper == null) {
                newLevel--;
            } else if (item.score(costs[newLevel - 1]) > topInUpper.score(costs[newLevel - 1])) {
                newLevel--;
            } else {
                break;
            }
        }

        if (newLevel != oldLevel) {
            levels.get(oldLevel).remove(item);
            item.level = newLevel + 1;
            levels.get(newLevel).offer(item);

            evictIfNeeded(newLevel);
        }
    }

    private void evictIfNeeded(int levelIndex) {
        PriorityQueue<CacheItem> pq = levels.get(levelIndex);
        while (pq.size() > capacities[levelIndex]) {
            CacheItem evicted = pq.poll();
            map.remove(evicted.key);
            System.out.println("Evicted from L" + (levelIndex + 1) + ": " + evicted);
        }
    }

    public void printCacheStatus() {
        for (int i = 0; i < levels.size(); i++) {
            System.out.println("L" + (i + 1) + ": " + levels.get(i));
        }
    }

    public static void main(String[] args) {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem();

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        cache.printCacheStatus();

        System.out.println("get(1): " + cache.get(1));
        System.out.println("get(1): " + cache.get(1));
        System.out.println("get(2): " + cache.get(2));
        cache.printCacheStatus();

        cache.put(4, "D");
        cache.put(5, "E");
        cache.put(6, "F");
        cache.printCacheStatus();
    }
}

