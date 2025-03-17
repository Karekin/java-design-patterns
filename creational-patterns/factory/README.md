# Java 中的工厂模式：简化对象创建

---

title: "Java 中的工厂模式：简化对象创建"
shortTitle: 工厂
description: "通过详细的示例和解释，学习 Java 中的工厂设计模式。了解如何使用工厂模式创建灵活且可扩展的代码。适用于希望提高面向对象设计技能的开发人员。"
category: 创建型
language: zh
tag:
- 抽象
- 封装
- 四人组
- 实例化
- 多态

---

## 工厂设计模式的意图

Java 中的工厂设计模式是一种创建型模式，它定义了一个创建对象的接口，但允许子类决定将创建的对象类型。这种模式有助于提高代码库的灵活性和可扩展性。

## 带有实际案例的工厂模式详细解释

现实世界中的例子

> 想象一家面包店使用工厂设计模式制作不同类型的蛋糕。面包店的 `CakeFactory` 负责整个制作过程，可以轻松添加新的蛋糕类型，而无需改变核心的蛋糕制作流程。`CakeFactory` 可以制作各种类型的蛋糕，如巧克力蛋糕、香草蛋糕和草莓蛋糕。面包店员工无需手动选择配料或遵循每种蛋糕的具体配方，而是使用 `CakeFactory` 来处理整个过程。顾客只需提出蛋糕类型的需求，`CakeFactory` 会确定使用哪些配料和配方，然后制作出特定类型的蛋糕。这种设置使得面包店可以在不修改核心蛋糕制作流程的情况下轻松添加新的蛋糕类型，从而实现灵活性和可扩展性。

维基百科说

> 工厂是一个用于创建其他对象的对象——正式来说，工厂是一个返回具有不同原型或类的对象的函数或方法。

## Java 中工厂模式的编程示例

想象一个炼金术士即将制造硬币。他必须能够创建金币和铜币，并且需要能够在它们之间切换，而无需修改现有源代码。工厂模式通过提供一个可以调用的静态构造方法来实现这一点，该方法可以带入相关参数。

在 Java 中，可以通过定义一个接口 `Coin` 及其实现类 `GoldCoin` 和 `CopperCoin` 来实现工厂模式。`CoinFactory` 类提供了一个静态方法 `getCoin`，用于根据类型创建硬币对象。

```java
public interface Coin {
  String getDescription();
}
```

```java
public class GoldCoin implements Coin {

  static final String DESCRIPTION = "这是一个金币。";

  @Override
  public String getDescription() {
    return DESCRIPTION;
  }
}
```

```java
public class CopperCoin implements Coin {
   
  static final String DESCRIPTION = "这是一个铜币。";

  @Override
  public String getDescription() {
    return DESCRIPTION;
  }
}
```

下面的枚举表示我们支持的硬币类型（`GoldCoin` 和 `CopperCoin`）。

```java
@RequiredArgsConstructor
@Getter
public enum CoinType {

  COPPER(CopperCoin::new),
  GOLD(GoldCoin::new);

  private final Supplier<Coin> constructor;
}
```

然后，我们有静态方法 `getCoin`，用于在工厂类 `CoinFactory` 中创建硬币对象。

```java
public class CoinFactory {

  public static Coin getCoin(CoinType type) {
    return type.getConstructor().get();
  }
}
```

现在，在客户端代码中，我们可以使用工厂类生成各种类型的硬币。

```java
public static void main(String[] args) {
    LOGGER.info("炼金术士开始工作。");
    var coin1 = CoinFactory.getCoin(CoinType.COPPER);
    var coin2 = CoinFactory.getCoin(CoinType.GOLD);
    LOGGER.info(coin1.getDescription());
    LOGGER.info(coin2.getDescription());
}
```

程序输出：

```
06:19:53.530 [main] INFO com.iluwatar.factory.App -- 炼金术士开始工作。
06:19:53.533 [main] INFO com.iluwatar.factory.App -- 这是一个铜币。
06:19:53.533 [main] INFO com.iluwatar.factory.App -- 这是一个金币。
```

## 在 Java 中何时使用工厂模式

* 当类在创建对象之前不知道所需的精确类型和依赖关系时，使用工厂设计模式。
* 当方法返回共享公共超类的几种可能类之一，并且希望封装创建哪个对象的逻辑时。
* 该模式通常用于设计框架或库，以提供最大的灵活性，并隔离具体类类型。

## 工厂模式在 Java 中的实际应用

* [java.util.Calendar#getInstance()](https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.html#getInstance--)
* [java.util.ResourceBundle#getBundle()](https://docs.oracle.com/javase/8/docs/api/java/util/ResourceBundle.html#getBundle-java.lang.String-)
* [java.text.NumberFormat#getInstance()](https://docs.oracle.com/javase/8/docs/api/java/text/NumberFormat.html#getInstance--)
* [java.nio.charset.Charset#forName()](https://docs.oracle.com/javase/8/docs/api/java/nio/charset/Charset.html#forName-java.lang.String-)
* [java.net.URLStreamHandlerFactory#createURLStreamHandler(String)](https://docs.oracle.com/javase/8/docs/api/java/net/URLStreamHandlerFactory.html)（根据协议返回不同的单例对象）
* [java.util.EnumSet#of()](https://docs.oracle.com/javase/8/docs/api/java/util/EnumSet.html#of(E))
* [javax.xml.bind.JAXBContext#createMarshaller()](https://docs.oracle.com/javase/8/docs/api/javax/xml/bind/JAXBContext.html#createMarshaller--) 和其他类似的方法。
* JavaFX 使用工厂模式创建各种 UI 控件，以适应用户环境的具体需求。

## 工厂模式的优势与权衡

优势：

* 在 Java 应用程序中实现工厂模式可以减少实现与所用类之间的耦合。
* 支持 [开闭原则](https://java-design-patterns.com/principles/#open-closed-principle)，因为系统可以在不更改现有代码的情况下引入新类型。

权衡：

* 由于引入了多个额外的类，代码可能会变得更加复杂。
* 如果对象创建的底层复杂性较低或不必要，过度使用会使代码的可读性降低。

## 相关 Java 设计模式

* [抽象工厂](https://java-design-patterns.com/patterns/abstract-factory/)：可以视为一种处理产品组的工厂。
* [单例](https://java-design-patterns.com/patterns/singleton/)：通常与工厂结合使用，以确保类只有一个实例。
* [建造者](https://java-design-patterns.com/patterns/builder/)：将复杂对象的构建与其表示分离，类似于工厂管理实例化的方式。
* [工厂套件](https://java-design-patterns.com/patterns/factory-kit/)：具有分离的构建器和工厂接口的不可变内容的工厂。

## 参考文献和致谢

* [设计模式：可重用面向对象软件的元素](https://amzn.to/3w0Rk5y)
* [Effective Java](https://amzn.to/4cGk2Jz)
* [Head First 设计模式：构建可扩展和可维护的面向对象软件](https://amzn.to/3UpTLrG)