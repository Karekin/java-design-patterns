# Java 中的多例模式：掌握高级单例变体

---

title: "Java 中的多例模式：掌握高级单例变体"
shortTitle: 多例
description: "了解 Java 中的多例模式如何确保唯一命名的实例并提供全局访问点。通过实现提示和代码示例来学习。"
category: 创建型
language: zh
tag:
- 解耦
- 实例化
- 对象组合

---

## 又称

* 单例注册表

## 多例设计模式的意图

Java 中的多例模式确保一个类只有唯一命名的实例，并提供一个全局访问点。每个命名实例通过唯一键访问，使其成为 Java 设计模式的重要组成部分。

## 带有实际案例的多例模式详细解释

现实世界中的例子

> 多例模式的一个现实例子是大型办公室中的打印机管理系统。办公室中有多个打印机，每个打印机服务于不同的部门。系统不每次打印请求都创建新的打印机对象，而是使用多例模式确保每个部门只有一个打印机实例。当特定部门的打印请求到来时，系统会检查打印机实例注册表，并检索该部门现有的打印机。如果该部门没有打印机，则创建一个，注册并返回。这确保了打印机资源的高效管理，避免了为同一部门创建多个不必要的打印机实例。

直白地说

> 多例模式是单例模式的扩展，提供了一种拥有唯一命名实例映射的方法，而不仅仅是一个实例。这使其成为高效管理命名实例的宝贵 Java 设计模式。

维基百科说

> 在软件工程中，多例模式是一种设计模式，它推广了单例模式。单例模式只允许创建一个类的一个实例，而多例模式允许多个实例的受控创建，并通过映射进行管理。

## Java 中多例模式的编程示例

在本教程中，我们将探讨如何在 Java 中实现多例模式，涵盖其结构、优势，并提供代码示例。通过遵循这些实现提示，你将能够有效利用这一 Java 设计模式。

纳兹古尔（Nazgûl），也被称为戒灵或九骑手，是索伦最可怕的手下。按定义，他们总是有九个。

`Nazgul` 是多例类。

```java
public enum NazgulName {

    KHAMUL, MURAZOR, DWAR, JI_INDUR, AKHORAHIL, HOARMURATH, ADUNAPHEL, REN, UVATHA
}

public final class Nazgul {

    private static final Map<NazgulName, Nazgul> nazguls;

    @Getter
    private final NazgulName name;

    static {
        nazguls = new ConcurrentHashMap<>();
        nazguls.put(NazgulName.KHAMUL, new Nazgul(NazgulName.KHAMUL));
        nazguls.put(NazgulName.MURAZOR, new Nazgul(NazgulName.MURAZOR));
        nazguls.put(NazgulName.DWAR, new Nazgul(NazgulName.DWAR));
        nazguls.put(NazgulName.JI_INDUR, new Nazgul(NazgulName.JI_INDUR));
        nazguls.put(NazgulName.AKHORAHIL, new Nazgul(NazgulName.AKHORAHIL));
        nazguls.put(NazgulName.HOARMURATH, new Nazgul(NazgulName.HOARMURATH));
        nazguls.put(NazgulName.ADUNAPHEL, new Nazgul(NazgulName.ADUNAPHEL));
        nazguls.put(NazgulName.REN, new Nazgul(NazgulName.REN));
        nazguls.put(NazgulName.UVATHA, new Nazgul(NazgulName.UVATHA));
    }

    private Nazgul(NazgulName name) {
        this.name = name;
    }

    public static Nazgul getInstance(NazgulName name) {
        return nazguls.get(name);
    }
}
```

以下是访问 `Nazgul` 实例的方法。

```java
  public static void main(String[] args) {
    // 饿汉式初始化的多例
    LOGGER.info("打印饿汉式初始化的多例内容");
    LOGGER.info("KHAMUL={}", Nazgul.getInstance(NazgulName.KHAMUL));
    LOGGER.info("MURAZOR={}", Nazgul.getInstance(NazgulName.MURAZOR));
    LOGGER.info("DWAR={}", Nazgul.getInstance(NazgulName.DWAR));
    LOGGER.info("JI_INDUR={}", Nazgul.getInstance(NazgulName.JI_INDUR));
    LOGGER.info("AKHORAHIL={}", Nazgul.getInstance(NazgulName.AKHORAHIL));
    LOGGER.info("HOARMURATH={}", Nazgul.getInstance(NazgulName.HOARMURATH));
    LOGGER.info("ADUNAPHEL={}", Nazgul.getInstance(NazgulName.ADUNAPHEL));
    LOGGER.info("REN={}", Nazgul.getInstance(NazgulName.REN));
    LOGGER.info("UVATHA={}", Nazgul.getInstance(NazgulName.UVATHA));

    // 枚举多例
    LOGGER.info("打印基于枚举的多例内容");
    LOGGER.info("KHAMUL={}", NazgulEnum.KHAMUL);
    LOGGER.info("MURAZOR={}", NazgulEnum.MURAZOR);
    LOGGER.info("DWAR={}", NazgulEnum.DWAR);
    LOGGER.info("JI_INDUR={}", NazgulEnum.JI_INDUR);
    LOGGER.info("AKHORAHIL={}", NazgulEnum.AKHORAHIL);
    LOGGER.info("HOARMURATH={}", NazgulEnum.HOARMURATH);
    LOGGER.info("ADUNAPHEL={}", NazgulEnum.ADUNAPHEL);
    LOGGER.info("REN={}", NazgulEnum.REN);
    LOGGER.info("UVATHA={}", NazgulEnum.UVATHA);
}
```

程序输出：

```
15:16:10.597 [main] INFO com.iluwatar.multiton.App -- 打印饿汉式初始化的多例内容
15:16:10.600 [main] INFO com.iluwatar.multiton.App -- KHAMUL=com.iluwatar.multiton.Nazgul@4141d797
15:16:10.600 [main] INFO com.iluwatar.multiton.App -- MURAZOR=com.iluwatar.multiton.Nazgul@38cccef
15:16:10.600 [main] INFO com.iluwatar.multiton.App -- DWAR=com.iluwatar.multiton.Nazgul@5679c6c6
15:16:10.600 [main] INFO com.iluwatar.multiton.App -- JI_INDUR=com.iluwatar.multiton.Nazgul@27ddd392
15:16:10.600 [main] INFO com.iluwatar.multiton.App -- AKHORAHIL=com.iluwatar.multiton.Nazgul@19e1023e
15:16:10.600 [main] INFO com.iluwatar.multiton.App -- HOARMURATH=com.iluwatar.multiton.Nazgul@7cef4e59
15:16:10.600 [main] INFO com.iluwatar.multiton.App -- ADUNAPHEL=com.iluwatar.multiton.Nazgul@64b8f8f4
15:16:10.600 [main] INFO com.iluwatar.multiton.App -- REN=com.iluwatar.multiton.Nazgul@2db0f6b2
15:16:10.600 [main] INFO com.iluwatar.multiton.App -- UVATHA=com.iluwatar.multiton.Nazgul@3cd1f1c8
15:16:10.600 [main] INFO com.iluwatar.multiton.App -- 打印基于枚举的多例内容
15:16:10.601 [main] INFO com.iluwatar.multiton.App -- KHAMUL=KHAMUL
15:16:10.601 [main] INFO com.iluwatar.multiton.App -- MURAZOR=MURAZOR
15:16:10.601 [main] INFO com.iluwatar.multiton.App -- DWAR=DWAR
15:16:10.601 [main] INFO com.iluwatar.multiton.App -- JI_INDUR=JI_INDUR
15:16:10.601 [main] INFO com.iluwatar.multiton.App -- AKHORAHIL=AKHORAHIL
15:16:10.601 [main] INFO com.iluwatar.multiton.App -- HOARMURATH=HOARMURATH
15:16:10.601 [main] INFO com.iluwatar.multiton.App -- ADUNAPHEL=ADUNAPHEL
15:16:10.601 [main] INFO com.iluwatar.multiton.App -- REN=REN
15:16:10.601 [main] INFO com.iluwatar.multiton.App -- UVATHA=UVATHA
```

## 在 Java 中何时使用多例模式

Java 中多例模式的使用场景

* 一个类必须有命名实例，但每个唯一键只能有一个实例。
* 需要全局访问这些实例，而无需使用全局变量。
* 想要按键管理共享资源。

## 多例模式在 Java 中的实际应用

* 在不同上下文中管理数据库连接。
* 在应用程序中为不同环境管理配置设置。

## 多例模式的优势与权衡

优势：

* 基于键确保对实例的受控访问。
* 通过封装实例管理减少全局状态的使用。

权衡：

* 如果管理不当，由于多个实例可能导致内存使用增加。
* 如果实现时未考虑线程安全性，可能会出现并发问题。

## 相关 Java 设计模式

* [单例](https://java-design-patterns.com/patterns/singleton/)：多例可以看作是单例模式的扩展，单例模式只允许一个类的一个实例，而多例模式允许多个实例，每个实例对应一个键。
* [工厂方法](https://java-design-patterns.com/patterns/factory-method/)：多例使用一种方法来创建或检索实例，类似于工厂方法如何控制对象的创建。

## 参考文献和致谢

* [设计模式：可重用面向对象软件的元素](https://amzn.to/3w0pvKI)