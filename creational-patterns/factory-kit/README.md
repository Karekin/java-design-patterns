# Java 中的工厂套件模式：打造灵活的组件组装

---

title: "Java 中的工厂套件模式：打造灵活的组件组装"
shortTitle: 工厂套件
description: "通过详细解释、实际案例和应用场景，了解 Java 中的工厂套件模式。通过我们的综合指南提升您的 Java 技能。"
category: 创建型
language: zh
tag:
- 抽象
- 解耦
- 封装
- 泛型
- 实例化
- 对象组合

---

## 又称

* 对象套件
* 工具套件

## 工厂套件模式的意图

Java 中的工厂套件模式是一种强大的设计模式，有助于创建具有分离构建器和工厂接口的工厂。这种模式对于管理复杂的对象创建场景至关重要。

## 带有实际案例的工厂套件模式详细解释

现实世界中的例子

> 工厂套件模式在现实世界中的一个类似例子是餐厅厨房，其中不同类型的菜肴被高效地准备。这种设置促进了灵活性和一致性，类似于 Java 中的工厂套件模式。想象厨房有一个中央站，其中注册了各种食材和不同菜肴的配方。当订单到来时，厨师会参考这个中央站来收集必要的食材并按照注册的配方准备菜肴。这种设置使得厨房能够高效地管理和切换不同的菜肴准备，而无需每个厨师都了解每种配方的具体细节，从而在烹饪过程中促进灵活性和一致性。

直白地说

> 工厂套件是一种可配置的对象构建器，一个创建工厂的工厂。

## Java 中工厂套件模式的编程示例

想象一个神奇的武器工厂，使用 Java 中的工厂套件模式可以创建任何所需的武器。这种模式允许使用可配置的对象构建器，在对象类型未知的情况下非常理想。

首先定义简单的 `Weapon` 层次结构。

```java
public interface Weapon {
}

public enum WeaponType {
    SWORD,
    AXE,
    BOW,
    SPEAR
}

public class Sword implements Weapon {
    @Override
    public String toString() {
        return "剑";
    }
}

// Axe, Bow, 和 Spear 的定义类似...
```

接下来，定义一个函数式接口，允许向工厂添加具有名称的构建器。

```java
public interface Builder {
    void add(WeaponType name, Supplier<Weapon> supplier);
}
```

示例的核心是 `WeaponFactory` 接口，它有效地实现了工厂套件模式。`#factory` 方法用于使用工厂需要能够构造的类来配置工厂。然后使用 `#create` 方法来创建对象实例。

```java
public interface WeaponFactory {

    static WeaponFactory factory(Consumer<Builder> consumer) {
        var map = new HashMap<WeaponType, Supplier<Weapon>>();
        consumer.accept(map::put);
        return name -> map.get(name).get();
    }

    Weapon create(WeaponType name);
}
```

现在，我们可以展示如何使用 `WeaponFactory`。

```java
  public static void main(String[] args) {
    var factory = WeaponFactory.factory(builder -> {
        builder.add(WeaponType.SWORD, Sword::new);
        builder.add(WeaponType.AXE, Axe::new);
        builder.add(WeaponType.SPEAR, Spear::new);
        builder.add(WeaponType.BOW, Bow::new);
    });
    var list = new ArrayList<Weapon>();
    list.add(factory.create(WeaponType.AXE));
    list.add(factory.create(WeaponType.SPEAR));
    list.add(factory.create(WeaponType.SWORD));
    list.add(factory.create(WeaponType.BOW));
    list.forEach(weapon -> LOGGER.info("{}", weapon.toString()));
}
```

运行示例时的控制台输出如下。

```
06:32:23.026 [main] INFO com.iluwatar.factorykit.App -- 斧头
06:32:23.029 [main] INFO com.iluwatar.factorykit.App -- 矛
06:32:23.029 [main] INFO com.iluwatar.factorykit.App -- 剑
06:32:23.029 [main] INFO com.iluwatar.factorykit.App -- 弓
```

## 在 Java 中何时使用工厂套件模式

在以下情况下使用工厂套件模式：

* 工厂类无法预见必须创建的对象类型，并且需要一个新的自定义构建器实例。
* 需要一个新的自定义构建器实例而不是全局的。
* 工厂能够构建的对象类型需要在类外部定义。
* 需要分离构建器和创建者接口。
* 游戏开发和其他具有用户自定义的应用程序。

## 工厂套件模式 Java 教程

* [工厂套件模式 (Diego Pacheco)](https://diego-pacheco.medium.com/factory-kit-pattern-66d5ccb0c405)

## 工厂套件模式在 Java 中的实际应用

* 在 Java 类库（如 JDK）中，根据运行时环境实例化不同的渲染引擎。
* 在 Spring 等框架或大量使用依赖注入的应用中，通常实现此模式以更灵活地管理对象创建。

## 工厂套件模式的优势与权衡

优势：

* 工厂套件模式通过消除将应用程序特定类绑定到代码中的需要，促进了松散耦合。
* 它通过将实例化的责任转移到工厂对象上，简化了代码，使开发过程更加高效。

权衡：

* 可能会通过需要额外的类和接口而引入代码复杂性。
* 如果管理不当，有时可能导致依赖问题。

## 相关 Java 设计模式

* [抽象工厂](https://java-design-patterns.com/patterns/abstract-factory/)：通常与工厂套件结合使用，以创建相关对象家族。
* [建造者](https://java-design-patterns.com/patterns/builder/)：可以使用类似的方法逐步构建复杂对象。
* [原型](https://java-design-patterns.com/patterns/prototype/)：通过克隆原型实例创建的对象通常使用工厂来管理。

## 参考文献和致谢

* [设计模式重载 (Remi Forax)](https://www.youtube.com/watch?v=-k2X7guaArU)