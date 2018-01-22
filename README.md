[RoundImageView](https://xuxuliooo.github.io/RoundImageView)
==
自定义ImageView，在原生ImageView上实现圆形显示和加入圆角并且加入边框功能
--


### 显示效果

![](https://github.com/xuxuliooo/RoundImageView/raw/master/image/sample.png)


修复1.0bug
--
    ListView或RecyclerView的adapter中，宽度固定或使用"match_parent"时，高度使用"wrap_content"，设置边框线宽度和圆角无效问题
    添加setRadius(float leftTopRadius, float rightTopRadius, float leftBottomRadius, float rightBottomRadius)方法
        

自定义属性介绍
--

* <b>borderWidth</b>

        边框线的宽度，默认对最大宽度做了限制，不超过宽与高的最小尺寸值的四分之一

* <b>borderColor</b>

        边框线的颜色，默认值为"#8A2BE2"

* <b>displayBorder</b>

        显示边框线，默认不显示(false)，显示则为(true)

* <b>radius</b>

        圆角矩形圆弧半径，默认为"0"，如果设置大于"0",
        则设置(leftTopRadius、rightTopRadius、leftBottomRadius、rightBottomRadius)属性会失效

* <b>leftTopRadius(左上角圆弧半径) <br> leftBottomRadius(左下角圆弧半径) <br> rightTopRadius(右上角圆弧半径) <br> rightBottomRadius(右下角圆弧半径)</b>

        矩形四角的圆弧半径，默认为"0"，如果设置"radius"属性时，则此属性值会取"radius"设置的值

* <b>displayType</b>

        显示类型，默认为矩形(normal)。
        
    * <b>normal</b><font style="margin-left:15px">矩形显示</font>
    * <b>circle</b><font style="margin-left:15px">圆形显示</font>
    * <b>round_rect</b><font style="margin-left:15px">圆角矩形显示</font>
    
    
项目引用方式：
---

* <b style="font-size: 18px">第一种方式：从jitpack存储库引入</b>    [![](https://jitpack.io/v/xuxuliooo/RoundImageView.svg)](https://jitpack.io/#xuxuliooo/RoundImageView)
   
    <b style="font-size: 16px">1. 在项目的build.gradle中<font style="color: red">(非app/build.gradle)</font>添加"maven { url 'https://jitpack.io' }"</b>

        allprojects {
            repositories {
                ...
                maven { url 'https://jitpack.io' }
            }
        }
   
    <b style="font-size: 16px">2. 在app/build.gradle中添加"compile 'com.github.xuxuliooo:RoundImageView:1.0.3'"</b>

        dependencies {
            ...
            compile 'com.github.xuxuliooo:RoundImageView:1.0.3'
        }

* <b style="font-size: 18px">第二种方式：从bintray存储库引入</b>    [![Download](https://api.bintray.com/packages/xuxuliooo/maven/RoundImageView/images/download.svg)](https://bintray.com/xuxuliooo/maven/RoundImageView/_latestVersion)
   
    <b style="font-size: 16px">直接在app/build.gradle中添加"compile 'com.cbman:roundimageview:1.0.3'"</b>

        dependencies {
            ...
            compile 'com.cbman:roundimageview:1.0.3'
        }


使用方式：
--

* <b>圆角矩形使用方式</b>
    * 自定义四角圆弧半径的大小
    
            <com.cbman.roundimageview.RoundImageView
                android:layout_width="100dp"
                android:layout_height="100dp"
                android:scaleType="center"
                android:src="@drawable/img"
                app:borderColor="#ff0000"
                app:borderWidth="3dp"
                app:displayBorder="true"
                app:displayType="round_rect"
                app:leftBottomRadius="30dp"
                app:leftTopRadius="10dp"
                app:rightBottomRadius="20dp"
                app:rightTopRadius="15dp" />
                            
    * 四角圆弧半径相同时直接使用下面方式即可
    
            <com.cbman.roundimageview.RoundImageView
                android:layout_width="100dp"
                android:layout_height="100dp"
                android:scaleType="center"
                android:src="@drawable/img"
                app:borderWidth="1dp"
                app:displayType="round_rect"
                app:radius="20dp" />

* <b>圆形使用方式</b>

        <com.cbman.roundimageview.RoundImageView
            android:layout_width="100dp"
            android:layout_height="wrap_content"
            android:scaleType="center"
            android:src="@drawable/img"
            app:borderWidth="1dp"
            app:displayType="circle" />
            
* <b>矩形使用方式</b>

        <com.cbman.roundimageview.RoundImageView
            android:layout_width="100dp"
            android:layout_height="100dp"
            android:scaleType="center"
            android:src="@drawable/img"
            app:borderWidth="1dp"
            app:displayType="normal" />
            


<a href='https://bintray.com/xuxuliooo/maven/RoundImageView?source=watch' alt='Get automatic notifications about new "RoundImageView" versions'><img src='https://www.bintray.com/docs/images/bintray_badge_color.png'></a>
