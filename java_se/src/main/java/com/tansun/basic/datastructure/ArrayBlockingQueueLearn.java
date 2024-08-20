package com.tansun.basic.datastructure;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * ArrayBlockingQueue
 * 基本要素
 *    有界队列：在创建 ArrayBlockingQueue 时，必须指定其容量，这个容量在队列的生命周期内是固定的。
 *    FIFO 顺序：元素按照先进先出 (FIFO) 的顺序进行处理。
 *    阻塞操作：当队列为空时，获取元素的操作会被阻塞；当队列已满时，插入元素的操作会被阻塞。
 *    线程安全：内部使用锁机制来保证线程安全。
 *
 * ArrayBlockingQueue 主要有以下几个核心方法：
 * 构造方法：
 *      ArrayBlockingQueue(int capacity): 创建一个指定容量的 ArrayBlockingQueue。
 *      ArrayBlockingQueue(int capacity, boolean fair): 创建一个指定容量的 ArrayBlockingQueue，
 *      并可以指定是否公平地获取锁。
 * 入队方法：
 *      void put(E e) throws InterruptedException: 将元素 e 添加到队列尾部，如果队列已满，则会阻塞当前线程直到队列中有可用空间。
 *      boolean offer(E e): 尝试将元素 e 添加到队列尾部，如果成功则返回 true，否则返回 false。
 *      boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException: 尝试将元素 e 添加到队列尾部，等待指定的时间，如果成功则返回 true，否则返回 false。
 * 出队方法：
 *      E take() throws InterruptedException: 从队列头部移除并返回一个元素，如果队列为空，则会阻塞当前线程直到队列中有可用元素。
 *      E poll(): 从队列头部移除并返回一个元素，如果队列为空，则返回 null。
 *      E poll(long timeout, TimeUnit unit) throws InterruptedException: 从队列头部移除并返回一个元素，等待指定的时间，如果成功则返回元素，否则返回 null。
 * 其他常用方法：
 *      int remainingCapacity(): 返回队列剩余的容量。
 *      int drainTo(Collection<? super E> c): 将队列中的所有元素移动到指定的集合中，并返回移动的元素数量。
 *      int size(): 返回队列中的元素数量。
 *      boolean isEmpty(): 如果队列为空则返回 true。
 *      Object[] toArray(): 将队列中的元素复制到一个新数组中。
 *      String toString(): 返回队列的字符串表示形式。
 * 内部实现细节
 * ArrayBlockingQueue 使用一个循环数组来存储队列中的元素，同时使用两个索引 head 和 tail 来跟踪队列的头部和尾部。
 * head 指向队列中的第一个元素，而 tail 指向队列中最后一个元素之后的位置。队列的头部和尾部都是通过模运算 % 来计算的，
 * 这样可以确保在数组的末尾达到时能够循环回数组的开头。
 *
 * ArrayBlockingQueue与LinkedBlockingQueue 有什么相同及不同之处，各自有什么优劣势？
 *   相同之处
 *      线程安全性：两者都是线程安全的，可以在多线程环境中安全地使用。
 *      阻塞特性：它们都支持阻塞操作，当队列为空或满时，相应的操作会阻塞调用线程。
 *      不允许 null 元素：这两个队列都不允许插入 null 元素。
 *   不同之处
 *      1.容量限制：
 *        ArrayBlockingQueue 是有界队列，在创建时必须指定队列的容量。
 *        LinkedBlockingQueue 默认是无界的，可以指定容量，如果不指定则默认为 Integer.MAX_VALUE。
 *      2.数据结构：
 *        ArrayBlockingQueue 使用数组作为底层数据结构。
 *        LinkedBlockingQueue 使用单向链表作为底层数据结构。
 *      3.锁机制：
 *        ArrayBlockingQueue 使用单个锁来保护队列的所有访问操作。
 *        LinkedBlockingQueue 使用两个锁来分别保护队列的插入和删除操作，这可以提高并发性能。
 *      4.性能：
 *        LinkedBlockingQueue 在高并发情况下通常表现更好，因为它的分离锁机制减少了锁竞争。
 *        ArrayBlockingQueue 在容量固定且不大时，由于数组的连续性，可能会有更好的性能。
 *      5.内存管理：
 *        ArrayBlockingQueue 预先分配了一段连续的内存空间，对于容量较小的队列来说，内存使用更加稳定。
 *        LinkedBlockingQueue 动态分配内存，适合于不确定队列大小的情况，但可能会导致更多的内存碎片。
 *
 *    优劣势
 *    ArrayBlockingQueue
 *      1.优势：
 *      容量固定，内存使用稳定。
 *      对于小容量的队列，由于数组的连续性，可能有更好的性能。
 *      2.劣势：
 *      必须在创建时指定容量。
 *      单一锁机制可能导致较高的锁竞争。
 *    LinkedBlockingQueue
 *      1.优势：
 *      默认无界，适用于不确定队列大小的情况。
 *      分离锁机制提高了并发性能。
 *      2.劣势：
 *      如果不指定容量，可能会导致内存溢出。
 *      动态内存分配可能导致内存碎片。
 *
 */
public class ArrayBlockingQueueLearn {
    public static void main(String[] args) {

        // 创建一个有界队列
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

        try {
            // 入队操作
            queue.put("item1");
            queue.put("item2");

            // 出队操作
            String item = queue.take();
            System.out.println("Removed item: " + item);

            // 查看队列状态
            System.out.println("Remaining capacity: " + queue.remainingCapacity());
            System.out.println("Size: " + queue.size());
            System.out.println("Is empty: " + queue.isEmpty());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Interrupted");
        }
    }
}
