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

package com.iluwatar.abstractfactory;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 抽象工厂模式提供了一种方法，可以将一组具有共同主题的单个工厂封装起来，而无需指定它们的具体类。
 * 在正常用法中，客户端软件创建抽象工厂的具体实现，然后使用工厂的通用接口来创建属于该主题的具体对象。
 * 客户端不需要知道（也不关心）从这些内部工厂中获得的具体对象，因为它只使用产品的通用接口。
 * 这种模式将一组对象的实现细节与其通用用法分离开来，并且依赖于对象组合，
 * 因为对象创建是在工厂接口中暴露的方法中实现的。
 *
 * <p>抽象工厂模式的核心是一个工厂接口（{@link KingdomFactory}）及其实现（{@link ElfKingdomFactory}、{@link OrcKingdomFactory}）。
 * 示例使用这两种具体实现来创建国王、城堡和军队。
 */
@Slf4j
@Getter
public class App implements Runnable {

  // 定义一个王国对象，用于存储创建的王国相关对象
  private final Kingdom kingdom = new Kingdom();

  /**
   * 程序入口点。
   *
   * @param args 命令行参数
   */
  public static void main(String[] args) {
    // 创建App类的实例
    var app = new App();
    // 运行程序
    app.run();
  }

  @Override
  public void run() {
    // 创建精灵王国
    LOGGER.info("精灵王国");
    createKingdom(Kingdom.FactoryMaker.KingdomType.ELF);
    // 输出精灵王国的军队、城堡和国王的描述
    LOGGER.info(kingdom.getArmy().getDescription());
    LOGGER.info(kingdom.getCastle().getDescription());
    LOGGER.info(kingdom.getKing().getDescription());

    // 创建兽人王国
    LOGGER.info("兽人王国");
    createKingdom(Kingdom.FactoryMaker.KingdomType.ORC);
    // 输出兽人王国的军队、城堡和国王的描述
    LOGGER.info(kingdom.getArmy().getDescription());
    LOGGER.info(kingdom.getCastle().getDescription());
    LOGGER.info(kingdom.getKing().getDescription());
  }

  /**
   * 创建王国。
   *
   * @param kingdomType 王国类型
   */
  public void createKingdom(final Kingdom.FactoryMaker.KingdomType kingdomType) {
    // 根据王国类型，通过工厂制造器获取相应的王国工厂
    final KingdomFactory kingdomFactory = Kingdom.FactoryMaker.makeFactory(kingdomType);
    // 使用王国工厂创建国王、城堡和军队，并设置到王国对象中
    kingdom.setKing(kingdomFactory.createKing());
    kingdom.setCastle(kingdomFactory.createCastle());
    kingdom.setArmy(kingdomFactory.createArmy());
  }
}