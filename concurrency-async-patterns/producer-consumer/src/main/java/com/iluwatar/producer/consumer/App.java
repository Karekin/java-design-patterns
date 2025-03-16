/*
 * This project is licensed under the MIT license. Module model-view-viewmodel is using ZK framework licensed under LGPL (see lgpl-3.0.txt).
 *
 * The MIT License
 * Copyright © 2014-2022 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.iluwatar.producer.consumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

/**
 * Producer Consumer Design pattern is a classic concurrency or threading pattern which reduces
 * coupling between Producer and Consumer by separating Identification of work with Execution of
 * Work.
 *
 * <p>In producer consumer design pattern a shared queue is used to control the flow and this
 * separation allows you to code producer and consumer separately. It also addresses the issue of
 * different timing require to produce item or consuming item. by using producer consumer pattern
 * both Producer and Consumer Thread can work with different speed.
 */
@Slf4j
public class App {

  /**
   * 程序入口点。
   *
   * @param args 命令行参数
   */
  public static void main(String[] args) {

    // 创建一个共享队列，生产者和消费者将通过它进行通信
    ItemQueue queue = new ItemQueue();

    // 创建一个固定大小为 5 的线程池来运行生产者和消费者任务
    ExecutorService executorService = Executors.newFixedThreadPool(5);

    // 创建并启动两个生产者任务
    for (int i = 0; i < 2; i++) {
      Producer producer = new Producer("Producer_" + i, queue);
      executorService.submit(() -> {
        while (true) { // 在无限循环中不断生产项目
          producer.produce();
        }
      });
    }

    // 创建并启动三个消费者任务
    for (int i = 0; i < 3; i++) {
      Consumer consumer = new Consumer("Consumer_" + i, queue);
      executorService.submit(() -> {
        while (true) { // 在无限循环中不断消费项目
          consumer.consume();
        }
      });
    }

    // 尝试优雅地关闭线程池，不再接受新的任务
    executorService.shutdown();
    try {
      // 等待直到所有任务完成执行或者超时
      executorService.awaitTermination(10, TimeUnit.SECONDS);
      // 超时后强制关闭剩余的任务
      executorService.shutdownNow();
    } catch (InterruptedException e) {
      LOGGER.error("Error waiting for ExecutorService shutdown", e);
    }
  }
}
