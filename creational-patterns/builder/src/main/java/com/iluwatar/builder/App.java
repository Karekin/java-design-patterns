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
package com.iluwatar.builder;

import com.iluwatar.builder.Hero.Builder;
import lombok.extern.slf4j.Slf4j;

/**
 * 建造者模式的目的是为望远镜式构造函数反模式提供解决方案。望远镜式构造函数反模式出现在对象构造函数参数组合增加时，
 * 导致构造函数列表呈指数级增长。与其使用多个构造函数，建造者模式使用另一个对象，即建造者，
 * 该对象逐步接收每个初始化参数，然后一次性返回构建完成的对象。
 *
 * <p>建造者模式还有另一个好处。它可用于包含平面数据的对象（如HTML代码、SQL查询、X.509证书等），
 * 也就是说，那些无法轻松编辑的数据。这种类型的数据不能逐步编辑，必须一次性编辑。构建这种对象的最佳方式是使用建造者类。
 *
 * <p>在这个例子中，我们采用的是 Joshua Bloch 在《Effective Java》第二版中描述的建造者模式变体。
 *
 * <p>我们想要构建 {@link Hero} 对象，但由于需要许多参数，其构造过程较为复杂。为了帮助用户，
 * 我们引入了 {@link Builder} 类。{@link Hero.Builder} 在其构造函数中接收构建 {@link Hero} 对象所需的最小参数。
 * 之后，可以使用流畅的 {@link Builder} 接口对 {@link Hero} 对象进行额外配置。当配置完成时，
 * 调用构建方法以获得最终的 {@link Hero} 对象。
 */
@Slf4j
public class App {

  /**
   * 程序入口点。
   *
   * @param args 命令行参数
   */
  public static void main(String[] args) {

    // 创建一个法师类型的 Hero 对象，名字是 Riobard，头发颜色是黑色，武器是匕首
    var mage = new Hero.Builder(Profession.MAGE, "Riobard")
        .withHairColor(HairColor.BLACK) // 设置头发颜色为黑色
        .withWeapon(Weapon.DAGGER) // 设置武器为匕首
        .build(); // 调用 build() 方法构建最终的 Hero 对象
    LOGGER.info(mage.toString()); // 输出法师对象的信息

    // 创建一个战士类型的 Hero 对象，名字是 Amberjill，头发颜色是金色，头发类型是长卷发，盔甲是锁子甲，武器是剑
    var warrior = new Hero.Builder(Profession.WARRIOR, "Amberjill")
        .withHairColor(HairColor.BLOND) // 设置头发颜色为金色
        .withHairType(HairType.LONG_CURLY) // 设置头发类型为长卷发
        .withArmor(Armor.CHAIN_MAIL) // 设置盔甲为锁子甲
        .withWeapon(Weapon.SWORD) // 设置武器为剑
        .build(); // 调用 build() 方法构建最终的 Hero 对象
    LOGGER.info(warrior.toString()); // 输出战士对象的信息

    // 创建一个盗贼类型的 Hero 对象，名字是 Desmond，头发类型是秃头，武器是弓
    var thief = new Hero.Builder(Profession.THIEF, "Desmond")
        .withHairType(HairType.BALD) // 设置头发类型为秃头
        .withWeapon(Weapon.BOW) // 设置武器为弓
        .build(); // 调用 build() 方法构建最终的 Hero 对象
    LOGGER.info(thief.toString()); // 输出盗贼对象的信息
  }
}