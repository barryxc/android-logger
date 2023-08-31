## logger

无侵入 `Android` 日志工具，用于打印函数耗时和跟踪栈信息

## api

**打印函数耗时**

```
@MethodCost
```

**打印函数栈信息**


```
@MethodInspect
```

## usage

```groovy
buildScript {
    repository {
        maven {
            url "https://jitpack.io"
        }
    }
    dependencies {
        classpath "com.github.barryxc.logger:enhance-logger-plugin:${version}"
    }
}

apply plugin: "enhance-logger-plugin"

logger{
	enable = true
}

repository {
    maven {
        url "https://jitpack.io"
    }
}
dependencies {
    implementation "com.github.barryxc.logger:logger-api:${version}"
}
```





