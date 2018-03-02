# kotlin-android-one-day-up

>一个短视频推荐应用，每日会定时收到一条精选短视频推荐推送。应用基于Kotlin开发，项目中使用了较多Kotlin语法糖及新特性，帮助大家快速熟悉Kotlin在Android中的开发。

项目短视频数据由Kotlin结合Spring-Boot开发的后端服务，帮助大家快速了解Kotlin在Spring-Boot中的支持运用，[项目传送门](https://github.com/magicsu/kotlin-spring-boot-one-day-up)。

![home](http://huichi.b0.upaiyun.com/ondayup.png)

## 相关Kotlin特性
### Kotlin与NPE
Kotlin使用多种方式避免NPE，但是通过什么样的途经来保证？

先来看看在Kotlin中有哪些方式可以产生NPE：
- 使用「!!」操作符
- 明确调用throws NullPointException()
- 外部Java代码或初始化某些数据不一致

#### 如何避免？

##### var:
在Kotlin中，明确区分可以指向null的引用与不可以指向null的引用

```
var a: String = "abc"
a = null // 编译错误

var b: String? = "abc"
b = null // ok
```

#### 安全调用操作符 ?.
在Kotlin中，允许使用“？.”操作符调用变量，其含义是如果b不是null,这个表达式将会返回b.length,否则返回null。如果使用了”?.”,其表达式的值也应为可为null的。

```
b?.length
```

##### Elvis操作符 ?:
如果 ?: 左侧的表达式值不是null, 就会返回表达式的的值,否则, 返回右侧表达式的值.

```
val l = b?.length ?: -1
```
如果b不为null，将返回b的长度，如果为null，将返回-1

#### 操作符 !!
对于b不为null的情况, 这个表达式将会返回这个非null值(比如, 在我们的例子中就是一个 String 类型值), 如果b是 null, 这个表达式就
会抛出一个 NPE:

```
val l = b!!.length()
```
当b为null时就抛出一个异常，你可以捕获它，而不是在不知道在某处调用时，才报出异常。

### apply
调用某对象的apply函数，在函数范围内，可以任意调用该对象的任意方法，并返回该对象。
`
fun <T> T.apply(f: T.() -> Unit): T { f(); return this }
`

使用示例:

```
// VideoFeedFragment.java
companion object {
    private const val TAG: String = "VideoDetailFragment"
    const val VIDEO_ITEM: String = "VIDEO_ITEM"

    fun newInstance(itemList: VideoResource) =
            VideoDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(VIDEO_ITEM, itemList)
                }
            }
}
```


### with
with函数和前面的几个函数使用方式略有不同，因为它不是以扩展的形式存在的。它是将某对象作为函数的参数，在函数块内可以通过 this 指代该对象。返回值为函数块的最后一行或指定return表达式。

`
fun <T, R> with(receiver: T, f: T.() -> R): R = receiver.f()
`

使用示例:

```
val root = inflater.inflate(R.layout.fragment_home_feed, container, false)
with(root) {
	 toolbar = findViewById(R.id.toolbar)
    galleryRecyclerView = findViewById(R.id.galleryRecyclerView)
}
```


### 延迟初始化-lateinit

使用示例:

```
// 在Activit/Feagment声明，在OnCreate中进行初始化
private lateinit var detailTitle: TextView

override fun onCreate(savedInstanceState: Bundle?) {
	super.onCreate(savedInstanceState)
	etContentView(R.layout.taskdetail_act)

	detailTitle = findViewById(R.id.detailTitle)
}

```

### 集合操作
Kotlin提供多种集合处理类：

- 列表：List/MutableList
- 集：Set/MutableSet
- 映射：Map/MutableMap
- 集合：Collection/MutableCollection
- 迭代器：Iterable/MutableIterable

每种集合都提供了多种常用操作符，使用起来相当方便。

使用示例：

```
// 使用sortedByDescending对集合按照分数进行倒序排列
var sortedVideoItemList = videoItemList?.sortedByDescending { it -> it.data?.score }
```

### 扩展
Kotlin提供了一种可以在不继承父类，也不使用类似装饰器这样的设计模式的方式下，对指定类进行扩展，通过一种扩展声明来实现。Kotlin支持**函数扩展**和**属性扩展**

#### 函数扩展
为了添加一个函数扩展，我们需要在函数前添加一个指定接收者类型作为前缀。
Kotlin的方法扩展并不是真正修改了对应的类文件，而是在编译器和IDE方面做得处理。使我们看起来像是扩展了方法。

使用示例：

```
// ActivityExt.java
// 对AppCompatActivity添加addFragmentToActivity(fragmewnt, tag)扩展
// 添加完成后就可以直接在AppCompatActivity或其子类对象中直接使用该扩展函数
fun AppCompatActivity.addFragmentToActivity(fragment: Fragment, tag: String) {
    supportFragmentManager.transact {
        add(fragment, tag)
    }
}
```

#### 属性扩展

```
val T List<T>.lastIndex: Int
	get() = size - 1
```