package com.tansun.basic.bit;

import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 布隆过滤器是一种用于快速检索一个元素是否存在于一个集合中的数据结构。
 * 它基于哈希函数和位数组实现，可以高效地判断一个元素是否在集合中，同时具有空间效率和查询效率高的特点。
 * Bloom Filter的效果受到哈希函数的质量、哈希函数的数量、位数组的大小等因素的影响
 *
 * 布隆过滤器的原理如下：
 *
 * 初始化：首先创建一个位数组，将所有的位都初始化为0。
 * 哈希函数：选择k个不同的哈希函数，每个哈希函数可以将输入映射到位数组的一个位置。
 * 插入元素：当要插入一个元素时，分别用k个哈希函数计算出元素的哈希值，并将对应的位数组位置置为1。
 * 查询元素：当要查询一个元素时，同样用k个哈希函数计算出元素的哈希值，如果所有的位数组位置都为1，
 *          则说明元素可能在集合中；如果有一个位置为0，则说明元素一定不在集合中。
 *
 * 布隆过滤器的优点是可以高效地判断一个元素是否在集合中，同时具有空间效率高的特点。
 * 但是由于哈希函数的原因，布隆过滤器可能存在一定的误判率，即可能会判断一个元素在集合中，
 * 但实际上并不在集合中。因此在应用中需要根据具体情况来选择合适的哈希函数数量和位数组大小，以降低误判率。
 *
 * 应用场景包括但不限于：
 *    1.缓存穿透问题：在缓存中查询一个不存在的数据时，如果频繁查询会导致缓存穿透问题，
 *                 布隆过滤器可以用来快速判断查询的数据是否可能存在，避免频繁查询数据库。
 *    2.防止恶意爬虫：用于快速判断URL是否已经被爬取过，避免重复爬取相同的URL。
 *    3.邮件服务器的垃圾邮件过滤：可以快速判断邮件是否是垃圾邮件，提高过滤效率。
 *    4.分布式系统中的数据同步：在分布式系统中，可以使用布隆过滤器快速判断数据是否需要同步到其他节点，
 *                         减少网络传输和数据处理的压力。
 *    5.网络安全领域：用于快速判断IP地址、域名等是否存在于黑名单中，提高网络安全性。
 *    6.数据库查询优化：可以在查询数据库之前使用布隆过滤器判断查询条件是否可能存在，减少不必要的数据库查询操作。
 */
public class BloomFilter {

    // 多个哈希函数的种子
    private final int[] ints = {6, 8, 16, 38, 58, 68};
    //统计当前对象数量
    private AtomicInteger currentBeanCount = new AtomicInteger(0);
    //你的布隆过滤器容量
    private int DEFAULT_SIZE = Integer.MAX_VALUE;
    /**
     * 位数组：其里面数值是在 BitSet 中的位置，位数组大小，影响其布隆过滤器的误判率，以及内存空间大小
     *        通常位数组越大，误判率越低，占用的内存也就越高
     * 后面hash函数会用到，用来生成不同的hash值，可以随便给，但别给奇数
     */
    private final BitSet bitSet = new BitSet(DEFAULT_SIZE);


    // 数据量小的时候，可用于优化布隆过滤器的查找
//    private final ConcurrentHashMap<Object, Boolean> concurrentMap = new ConcurrentHashMap();

    public BloomFilter() {
    }

    public static void main(String[] args) {
        //实例化
        BloomFilter filter = new BloomFilter();
        for (int i = 0; i < 20000000; i++) {
            //push到BloomFilter
            getPersonList(5).forEach(person -> filter.push(person));
        }
        //push一个确定的对象
        filter.push(getFixedPerson(now));
        //判断这个对象是否存在
        long start = System.currentTimeMillis();
        System.out.println(filter.contain(getFixedPerson(now)));
        long end = System.currentTimeMillis() - start;
        System.out.println("bloomFilter内对象数量：" + filter.getCurrentBeanCount());
        System.out.println("耗时(ms)：" + end + ",消耗内存(m)：" + (ObjectSizeCalculator.getObjectSize(filter) / (1024 * 1024)));
    }

    public BloomFilter(int size) {
        if (size > Integer.MAX_VALUE) throw new IllegalArgumentException("size is too large");
        if (size <= 0) throw new IllegalArgumentException("size is too small");
        DEFAULT_SIZE = size;
    }

    //获取当前过滤器的对象数量
    public Integer getCurrentBeanCount() {
        return currentBeanCount.get();
    }

    //计算出key的hash值，并将对应下标置为1
    public void push(Object key) {
//        concurrentMap.put(key, true);
        Arrays.stream(ints).forEach(i -> bitSet.set(hash(key, i), true));
        currentBeanCount.incrementAndGet();
    }

    //判断key是否存在，true不一定说明key存在，但是false一定说明不存在
    public boolean contain(Object key) {
//         // 利用并发Map优化性能
//        if(concurrentMap.containsKey(key)) return true;
        boolean result = true;
        for (int i : ints) {
            result = result && bitSet.get(hash(key, i));
        }
        return result;
    }

    //hash算法，借鉴了hashmap的算法，利用i对同个key生成一组不同的hash值
    private int hash(Object key, int i) {
        int h;
        int index = key == null ? 0 : (DEFAULT_SIZE - 1 - i) & ((h = key.hashCode()) ^ (h >>> 16));
        return index > 0 ? index : -index;
    }


    //获得一个确定的person，需传入一个date，什么作用这里先别管，后面一看就懂
    private static Person getFixedPerson(LocalDateTime date) {
        Person person = new Person();
        person.setName("薇娅");
        person.setPhone(18966666666L);
        person.setSalary(new BigDecimal(99999));
        person.setCompany("天猫");
        person.setIfSingle(0);
        person.setSex(0);
        person.setAddress("上海市徐家汇某栋写字楼");
        person.setCreateTime(date);
        person.setCreateUser("老马");
        return person;
    }

    private static Random random = new Random();
    private static String[] names = {"黄某人", "负债程序猿", "谭sir", "郭德纲", "李狗蛋", "铁蛋", "赵铁柱", "蔡徐鸡", "蔡徐母鸡"};
    private static String[] addrs = {"二仙桥", "成华大道", "春熙路", "锦里", "宽窄巷子", "双子塔", "天府大道", "软件园", "熊猫大道", "交子大道"};
    private static String[] companys = {"京东", "腾讯", "百度", "小米", "米哈游", "网易", "字节跳动", "美团", "蚂蚁", "完美世界"};
    private static LocalDateTime now = LocalDateTime.now();

    //获取指定数量person
    private static List<Person> getPersonList(int count) {
        List<Person> persons = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            persons.add(getPerson());
        }
        return persons;
    }

    //随机生成person
    private static Person getPerson() {
        Person person = new Person();
        person.setName(names[random.nextInt(names.length)]);
        person.setPhone(18800000000L + random.nextInt(88888888));
        person.setSalary(new BigDecimal(random.nextInt(99999)));
        person.setCompany(companys[random.nextInt(companys.length)]);
        person.setIfSingle(random.nextInt(2));
        person.setSex(random.nextInt(2));
        person.setAddress("四川省成都市" + addrs[random.nextInt(addrs.length)]);
        person.setCreateTime(LocalDateTime.now());
        person.setCreateUser(names[random.nextInt(names.length)]);
        return person;
    }
}