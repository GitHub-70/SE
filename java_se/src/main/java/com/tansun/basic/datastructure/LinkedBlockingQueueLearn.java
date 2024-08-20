package com.tansun.basic.datastructure;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * LinkedBlockingQueue 的基本要素
 * 单向链表：
 *    LinkedBlockingQueue 使用单向链表来存储元素。
 *    链表的每个节点包含一个元素和指向下一个节点的引用。
 * 头尾指针：
 *    head 指向队列的第一个节点。 head 的 next 指向自身，item为 null
 *    tail 指向队列的最后一个节点。
 * 容量限制：
 *    默认情况下，LinkedBlockingQueue 的容量是 Integer.MAX_VALUE，这意味着它可以存储非常大量的元素。
 *    可以通过构造函数指定一个有限的容量。
 * 线程安全：
 *    使用 ReentrantLock 来保证线程安全。
 *    通常使用两个锁：一个用于插入操作，另一个用于移除操作。
 * 阻塞操作：
 *    当队列满时，put 和 add 方法会阻塞调用线程，直到有足够的空间。
 *    当队列空时，take 和 poll 方法会阻塞调用线程，直到队列中有元素。
 * 非阻塞操作：
 *    offer 方法尝试在队列中添加元素，如果队列满则返回 false。
 *    peek 方法返回队列头部的元素，如果队列空则返回 null。
 * 公平性和非公平性：
 *    LinkedBlockingQueue 支持公平和非公平两种策略。
 *    公平策略意味着等待时间最长的线程优先获取锁。
 *    非公平策略可能会优先偏向于当前持有锁的线程。
 *    默认情况下，LinkedBlockingQueue 是非公平的。
 *
 * LinkedBlockingQueue 的实现方法
 *   1.插入操作：
 *      put(e)：将元素 e 插入队列尾部，如果队列已满则阻塞。
 *      add(e)：与 put(e) 相同，但可能抛出 InterruptedException。
 *      offer(e)：尝试将元素 e 插入队列尾部，如果队列已满则返回 false。(JDK1.8存在非预期结果问题,原因：加锁后，未检查队列容量)
 *   2.移除操作：
 *      take()：从队列头部移除并返回一个元素，如果队列为空则阻塞。
 *      poll()：尝试从队列头部移除并返回一个元素，如果队列为空则返回 null。
 *      poll(long timeout, TimeUnit unit)：在给定时间内等待队列头部的元素，超时后返回 null。
 *   3.检查操作：
 *      peek()：返回队列头部的元素，如果队列为空则返回 null。
 *      element()：返回队列头部的元素，如果队列为空则抛出 NoSuchElementException。
 *   4.其他操作：
 *      remainingCapacity()：返回队列剩余的容量。
 *      drainTo(Collection c)：将队列中的所有元素转移到指定集合 c 中。
 *      remove(Object o)：从队列中移除指定元素 o。
 *      contains(Object o)：判断队列中是否包含指定元素 o。
 *
 * 实现细节
 *   1.内部数据结构：
 *      Node 类：表示链表中的节点。
 *      Node 包含一个 item 字段存储元素，一个 next 字段指向下一个节点。
 *   2.锁机制：
 *      takeLock 和 putLock：两个 ReentrantLock 实例分别用于移除和插入操作。
 *      notEmpty 和 notFull：两个 Condition 实例用于等待队列非空和非满的条件。
 *   3.插入节点：
 *      新节点总是添加到队列尾部。
 *      使用 putLock 保证插入操作的原子性。
 *   4.移除节点：
 *      移除节点总是从队列头部开始。
 *      使用 takeLock 保证移除操作的原子性。
 *   5.阻塞逻辑：
 *      当队列满时，插入操作会调用 notFull.await() 阻塞当前线程。
 *      当队列空时，移除操作会调用 notEmpty.await() 阻塞当前线程。
 *      简单的实现细节详见 BufferQueue 类
 *
 * 为什么不直接使用 this.takeLock 而要使用 final ReentrantLock takeLock = this.takeLock？
 *   使用 final ReentrantLock takeLock = this.takeLock; 可以提高代码的执行效率，尤其是在多线程环境中。
 *   局部变量的使用有助于减少锁竞争，提高程序的并发性能。
 *   这种做法在实际开发中是推荐的，特别是在处理高并发场景时
 *
 * final ReentrantLock takeLock = this.takeLock; 不会改变 takeLock 对象本身的引用地址，怎么就提高程序的并发性能？
 *   1.减少锁重入成本:
 *      当多个线程交替执行时，如果一个线程已经获得了 takeLock，那么在同一个方法中再次获取 takeLock 时，
 *      由于局部变量的存在，可以直接从栈中获取，而不需要重新从堆中查找。
 *      这样可以减少锁重入的成本，因为从栈中获取变量比从堆中获取更快。
 *   2.减少锁竞争:
 *      虽然 takeLock 的引用没有变，但是在多线程环境下，不同的线程在执行 put 或者 take 方法时，会各自拥有自己的局部变量副本。
 *      这意味着每个线程在使用 takeLock 时，实际上是在使用自己栈中的局部变量，
 *      这可以减少因多次访问堆内存中的同一个对象而产生的锁竞争。
 *   3.减少内存访问:
 *      在多核处理器系统中，频繁地访问堆内存中的对象可能会导致缓存不一致的问题，从而增加内存访问的开销。
 *      使用局部变量可以减少对堆内存的访问次数，从而减少缓存不一致带来的性能损失。
 *   4.提高代码可读性和维护性:
 *      使用局部变量可以使代码更清晰，更容易理解。
 *      这样的代码风格也有助于未来的维护，特别是当方法变得复杂时。
 *
 * transient Node<E> head;为何要使用transient修饰符？
 *   1.序列化效率:
 *      由于 head 节点不包含实际数据，将其标记为 transient 可以避免序列化过程中的额外开销，从而提高序列化和反序列化的效率。
 *   2.内存占用:
 *      不序列化 head 节点可以减少序列化文件的大小，节省存储空间。
 *   3.链表重建:
 *      在反序列化时，可以通过重新构建链表来恢复队列的结构，而不需要依赖序列化流中的 head 节点信息。
 *   4.安全性:
 *      对于内部结构，如 head 节点，标记为 transient 可以防止外部直接访问或修改这些内部状态，增加了一定程度的安全性。
 *
 */
public class LinkedBlockingQueueLearn<E> {

    Node<E> head;
    Node<E> last;

    // 模拟 LinkedBlockingQueue 的构造方法，head 和 last 都指向同一个空 Node
    public LinkedBlockingQueueLearn(){
        // 由于 last 和 head 最初都指向同一个空节点(还未完全初始化)的地址值，
        // 因此当 last 被更新时（即为这片空的空间地址值赋值时），head 也会间接地指向新节点 node。
        head = last = new Node<E>(null);
    }
    public static void main(String[] args) {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(10);

        // 添加元素
        queue.offer("Hello");
        queue.offer("World");

        // 移除元素
        String item = queue.poll();
        System.out.println(item); // 输出 "Hello"

        // 检查队列头部
        String peekItem = queue.peek();
        System.out.println(peekItem); // 输出 "World"

        // 检查队列是否为空
        boolean isEmpty = queue.isEmpty();
        System.out.println(isEmpty); // 输出 false

        System.out.println("--------------------忽略以下结果---------------------------");

        LinkedBlockingQueueLearn linkedBlockingQueueLearn  = new LinkedBlockingQueueLearn();
        // DEBUG调试：模拟向链表尾部添加第一个元素时，last.next 指向新的 Node，同时 head.next 也指向第一个 Node
        Node<String> node = new Node<>("123");// 新开辟的空间对象@507 item指向123， next 指针为null
        linkedBlockingQueueLearn.last.next = node;// 初始化的对象@509的 next 指针指向了 对象@507
        linkedBlockingQueueLearn.last = node;// 初始化的对象@509 指向了 对象@507
        // 等同于上述 三个步骤
//        linkedBlockingQueueLearn.last = linkedBlockingQueueLearn.last.next = new Node<>("123");
        System.out.println("第一次添加 last "+linkedBlockingQueueLearn.last.item);
        // 关注 head.next.item
        System.out.println("第一次添加 head "+linkedBlockingQueueLearn.head.next.item);

        linkedBlockingQueueLearn.last = linkedBlockingQueueLearn.last.next = new Node<>("456");
        System.out.println("第二次添加 last "+linkedBlockingQueueLearn.last.item);
        // 关注 head.next.item
        System.out.println("第二次添加 head "+linkedBlockingQueueLearn.head.next.item);
    }


    static class Node<E> {
        E item;

        /**
         * One of:
         * - the real successor Node
         * - this Node, meaning the successor is head.next
         * - null, meaning there is no successor (this is the last node)
         */
        Node<E> next;

        Node(E x) { item = x; }
    }
}
