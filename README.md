RoundImageView
====
自定义ImageView，在原生ImageView上实现圆形显示和加入圆角并且加入边框功能
----

### 显示效果

![](https://github.com/xuxuliooo/RoundImageView/raw/master/image/样式图片.png)

### 自定义属性介绍

* <b>borderWidth</b>

        边框线的宽度，默认对最大宽度做了限制，不超过宽与高的最小尺寸值的四分之一

* <b>borderColor</b>

        边框线的颜色，默认值为"#8A2BE2"

* <b>displayBorder</b>

        显示边框线，默认显示(true)，不显示则为(false)

* <b>radius</b>

        圆角矩形圆弧半径，默认为"0"，如果设置大于"0"，则设置(leftTopRadius、rightTopRadius、leftBottomRadius、rightBottomRadius)属性会失效

* <b>leftTopRadius(左上角圆弧半径) <br> leftBottomRadius(左下角圆弧半径) <br> rightTopRadius(右上角圆弧半径) <br> rightBottomRadius(右下角圆弧半径)</b>

        矩形四角的圆弧半径，默认为"0"，如果设置"radius"属性时，则此属性值会取"radius"设置的值

* <b>displayType</b>

        显示类型，默认为圆形(circle)。
        
    * <b>normal</b><font style="margin-left:15px">矩形显示</font>
    * <b>circle</b><font style="margin-left:15px">圆形显示</font>
    * <b>round_rect</b><font style="margin-left:15px">圆角矩形显示</font>
    
### 使用方式：


* <b>圆角矩形</b>
    * 自定义四角圆弧半径的大小
    
            <com.cb.imageview.RoundImageView
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
    
            <com.cb.imageview.RoundImageView
                android:layout_width="100dp"
                android:layout_height="100dp"
                android:scaleType="center"
                android:src="@drawable/img"
                app:borderWidth="1dp"
                app:displayType="round_rect"
                app:radius="20dp" />

* <b>圆形</b>

        <com.cb.imageview.RoundImageView
            android:layout_width="100dp"
            android:layout_height="wrap_content"
            android:scaleType="center"
            android:src="@drawable/img"
            app:borderWidth="1dp"
            app:displayType="circle" />
            
* <b>矩形</b>

        <com.cb.imageview.RoundImageView
            android:layout_width="100dp"
            android:layout_height="100dp"
            android:scaleType="center"
            android:src="@drawable/img"
            app:borderWidth="1dp"
            app:displayType="normal" />
            


[我的博客](https://xuxuliooo.github.io)
--
