# Java 中的单例模式：在 Java 应用中实现全局访问点

---

- title: "Java 中的单例模式：在 Java 应用中实现全局访问点"
- shortTitle: 单例
- description: "通过我们的综合指南，探索 Java 中的单例模式。学习如何为 Java 应用实现高效的对象管理，确保资源的最优使用和通过示例与详细解释实现轻松访问。"
- category: 创建型
- language: zh
- tag:
  - 四人组
  - 实例化
  - 懒加载
  - 资源管理

---

## 又称

* 单一实例

## 单例设计模式的意图

确保 Java 类只有一个实例，并提供一个全局访问点来访问这个单例实例。

## 带有实际案例的单例模式详细解释

现实世界中的例子

> 单例模式的一个现实类比是一个国家发放护照。在一个国家中，每个公民一次只能被发放一个有效的护照。护照办公室确保不会向同一个人发放重复的护照。每当公民需要旅行时，他们必须使用这个单一护照，它作为全球认可的旅行凭证。这种受控访问和唯一实例管理反映了单例模式如何在 Java 应用中确保高效的对象管理。

直白地说

> 确保某个类只创建一个对象实例。

维基百科说

> 在软件工程中，单例模式是一种软件设计模式，它限制了一个类只能被实例化为一个对象。当需要一个对象来协调系统中的动作时，这非常有用。

## Java 中单例模式的编程示例

Joshua Bloch，《Effective Java》第二版第 18 页

> 一个单元素枚举类型是实现单例的最佳方式

```java
public enum EnumIvoryTower {
  INSTANCE
}
```

然后使用如下：

```java
    var enumIvoryTower1 = EnumIvoryTower.INSTANCE;
    var enumIvoryTower2 = EnumIvoryTower.INSTANCE;
    LOGGER.info("enumIvoryTower1={}", enumIvoryTower1);
    LOGGER.info("enumIvoryTower2={}", enumIvoryTower2);
```

控制台输出

```
enumIvoryTower1=com.iluwatar.singleton.EnumIvoryTower@1221555852
enumIvoryTower2=com.iluwatar.singleton.EnumIvoryTower@1221555852
```

## 在 Java 中何时使用单例模式

在以下情况下使用单例模式

* 必须确保某个类只有一个实例，并且客户端需要从一个众所周知的访问点访问它
* 当唯一的实例应该通过子类化来扩展，并且客户端应该能够在不修改代码的情况下使用扩展的实例

## 单例模式在 Java 中的实际应用

* 日志记录类
* 许多应用程序中的配置类
* 连接池
* 文件管理器
* [java.lang.Runtime#getRuntime()](http://docs.oracle.com/javase/8/docs/api/java/lang/Runtime.html#getRuntime%28%29)
* [java.awt.Desktop#getDesktop()](http://docs.oracle.com/javase/8/docs/api/java/awt/Desktop.html#getDesktop--)
* [java.lang.System#getSecurityManager()](http://docs.oracle.com/javase/8/docs/api/java/lang/System.html#getSecurityManager--)

## 单例模式的优势与权衡

优势：

* 对唯一实例的受控访问。
* 减少命名空间污染。
* 允许细化操作和表示。
* 允许可变数量的实例（如果需要，可以超过一个）。
* 比类操作更灵活。

权衡：

* 由于全局状态的存在，测试难度增加。
* 可能需要更复杂的生命周期管理。
* 如果在并发上下文中使用而不进行仔细的同步，可能会引入瓶颈。

## 相关 Java 设计模式

* [抽象工厂](https://java-design-patterns.com/patterns/abstract-factory/)：通常用于确保某个类只有一个实例。
* [工厂方法](https://java-design-patterns.com/patterns/factory-method/)：可以使用工厂方法来封装单例模式的创建逻辑。
* [原型](https://java-design-patterns.com/patterns/prototype/)：避免需要创建实例，可以与单例模式一起管理唯一实例。

## 参考文献和致谢

* [设计模式：可重用面向对象软件的元素](https://amzn.to/3w0pvKI)
* [Effective Java](https://amzn.to/4cGk2Jz)
* [Head First 设计模式：构建可扩展和可维护的面向对象软件](https://amzn.to/49NGldq)
* [Java 设计模式：通过实际案例获得实践经验](https://amzn.to/3yhh525)
* [重构到模式](https://amzn.to/3VOO4F5)