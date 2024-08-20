package com.tansun.thread.pool.status;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的状态
 * 线程池的状态是其运行和管理的关键组成部分，它决定了线程池如何接受和处理任务，以及如何响应外部的控制指令。
 * ThreadPoolExecutor 类使用一个名为 ctl 的字段来保存线程池的运行状态和线程数量。
 * ctl 字段实际上是一个整型变量，通过对它的位操作，可以同时存储线程池的状态和线程数量的信息。
 *
 * ThreadPoolExecutor 定义了以下几种线程池状态：
 *  1.RUNNING (0): 这是线程池的初始和主要状态，表示线程池处于正常运行状态，可以接受新的任务，
 *                  并处理已经在工作队列中的任务。
 *  2.SHUTDOWN (1): 当调用 shutdown() 方法时，线程池进入此状态。在 SHUTDOWN 状态下，线程池不再接受新的任务，
 *                  但会继续处理工作队列中已有的任务，直到它们完成。
 *  3.STOP (2): 当调用 shutdownNow() 方法时，线程池进入 STOP 状态。在这种状态下，线程池不仅停止接受新任务，
 *              还会尝试停止所有正在执行的任务，并清空工作队列。此外，shutdownNow() 方法会返回一个
 *              包含当前正在执行的任务列表的 List，这可以用于后续处理或清理。
 *  4.TIDYING (3): 当线程池中的所有任务都已完成，所有工作线程都已终止，并且工作队列为空时，
 *                  线程池会进入 TIDYING 状态。在 TIDYING(整理/清理) 状态下，线程池会执行一些清理工作，
 *                  如调用 terminated() 方法。   发音:ˈtaɪdiɪŋ
 *  5.TERMINATED (4): 这是线程池的最终状态，表示线程池的所有清理工作都已经完成，线程池完全终止。
 *                    一旦线程池进入 TERMINATED(终止) 状态，它将不再改变。  发音：ˈtɜːrməˌnet
 *
 * 线程池的状态是单向的，即从RUNNING到SHUTDOWN，再从SHUTDOWN到TIDYING，最后到TERMINATED，状态只能向前推进，不能逆向转换。
 *
 * terminated() 方法的作用
 * terminated() 方法主要用于执行以下操作：
 *      资源释放：释放线程池可能占用的任何资源，例如关闭数据库连接、网络连接或其他外部资源。
 *      状态报告：向外部系统报告线程池的终止状态，例如日志记录或通知监控系统。
 *      清理工作：执行任何必要的清理操作，例如清除临时文件或释放内存资源。
 * terminated() 方法的调用时机
 * terminated() 方法的调用时机是在线程池完成所有任务并且所有工作线程都已终止之后。这是线程池生命周期的最后阶段，
 * 当线程池从 TIDYING 状态过渡到 TERMINATED 状态时，terminated() 方法会被调用。
 *
 * 线程池状态的转换是由线程池内部的逻辑控制的，通常通过调用 shutdown() 或 shutdownNow() 方法触发状态变化。
 * 状态转换遵循一定的顺序，例如，从 RUNNING 到 SHUTDOWN 或 STOP，再从 TIDYING 到 TERMINATED。
 *
 * 状态转换的控制是通过原子操作完成的，以确保线程池状态的一致性和线程安全。
 * ThreadPoolExecutor 使用 AtomicInteger 类的 getAndIncrement 和 compareAndSet
 * 方法来实现状态的原子更新和检查。
 */
public class ThreadPoolStatus {

    /**
     * terminated() 方法在 ThreadPoolExecutor 中主要用于标记线程池的最终状态，
     * 即线程池已经完成了所有任务，所有工作线程都已经终止，并且线程池进入 TERMINATED 状态。
     * 从设计的角度来看，terminated() 方法的初衷是为了让线程池执行一些内部的清理工作，
     * 例如清理内部数据结构，但它并不是专门为资源释放设计的。
     * 然而，由于 terminated() 方法在线程池生命周期的末尾被调用，它确实提供了一个良好的时机来执行
     * 资源释放或清理工作。因此，许多开发者选择在重写的 terminated() 方法中加入资源释放的代码，
     * 这是一个常见的实践，特别是在需要确保外部资源（如数据库连接、文件句柄等）被正确关闭的情况下。
     *
     * 关于资源释放的几点说明：
     * 资源释放的最佳实践： 资源释放的最佳实践通常是确保每个打开的资源都有一个对应的关闭操作。
     * 例如，使用 try-with-resources 语句自动关闭资源，或者在 finally 块中显式关闭资源。
     * 这种方法比依赖 terminated() 方法更为可靠，因为资源应该在不再需要时立即释放，而不是等到线程池完全终止。
     *
     * terminated() 方法的局限性： terminated() 方法只能在线程池完全终止时调用一次，
     * 这意味着如果线程池需要重启，之前在 terminated() 方法中释放的资源需要再次手动创建。此外，
     * 如果 terminated() 方法执行时间过长，可能会影响线程池的正常终止过程。
     *
     * 资源管理器模式： 对于需要频繁管理的资源，考虑使用资源管理器模式，即创建一个专门的类来管理资源的生命周期，
     * 包括创建、使用和释放。这样可以将资源管理与线程池的生命周期解耦，提供更灵活和可靠的资源管理方案。
     *
     * 综上所述，虽然 terminated() 方法可以作为执行资源释放操作的一个时机，但它不应被视为资源管理的唯一或最佳方式。
     * 在设计系统时，应该结合使用多种资源管理策略，以确保资源的正确和及时释放。
     */
    private static ExecutorService executor = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        // 提交一些任务
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            executor.submit(() -> {
                try {
                    Thread.sleep(1000); // 模拟耗时操作
                    System.out.println("Task " + taskId + " completed.");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // 等待所有任务完成
        boolean allTasksCompleted = false;
        try {
            /**
             * 使用 awaitTermination 方法时，如果线程池中还有任务在执行，调用线程将被阻塞，
             * 直到所有任务完成或超时。因此，在高负载环境下，应谨慎使用，避免阻塞关键路径。
             * 如果线程池中存在死锁或无限期运行的任务，awaitTermination 方法可能永远不会返回，除非设置了超时。
             * 在调用 awaitTermination 之前，通常需要先调用 shutdown 或 shutdownNow 方法来停止接收新任务，
             * 否则 awaitTermination 可能永远不会返回，因为总会有新的任务被提交。
             */
            // 如果在超时前所有任务都已完成，则返回 true；如果超时，则返回 false。
            allTasksCompleted = executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (allTasksCompleted) {
            System.out.println("All tasks completed.");
        } else {
            System.out.println("Some tasks were still running after the timeout.");
        }

        // 关闭线程池
        executor.shutdown();

    }


}
