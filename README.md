[RoundImageView](https://xuxuliooo.github.io/RoundImageView)
==
自定义ImageView，在原生ImageView上实现圆形显示和加入圆角并且加入边框功能
--


### 显示效果

![](https://github.com/xuxuliooo/RoundImageView/raw/master/image/sample.png)

修正关闭软件层硬件加速可能引起的黑色背景问题
--
1.0.8修改设置为圆形时，使用宽与高的最小值，如果最小值为0，则使用最大值
--

1.0.7添加标签文字支持字体，可自定义字体(setTypeface)
--

1.0.6取消对appcompat-v7包的依赖
--

1.0.5添加标签功能
--
    可以在图片上添加标签,如果标签文本超出标签绘制区域，将文本裁剪以"..."代替

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
    
* <b>displayLable</b>

        是否显示标签，默认不显示标签(false),显示为(true)

* <b>lableBackground</b>

        标签背景色，默认值为"#9FFF0000"
        
* <b>lableWidth</b>

        标签宽度，单位(dp)
        
* <b>startMargin</b>

        距离开始位置间距，单位(dp)

* <b>text</b>

        标签文本
        
* <b>textColor</b>

        标签文本颜色
        
* <b>textSize</b>

        标签文本文字大小
        
* <b>lableGravity</b>

        标签显示位置(默认在右上角)
        
    * <b>leftTop</b><font style="margin-left:15px">左上角显示</font>        
    * <b>rightTop</b><font style="margin-left:15px">右上角显示</font>        
    * <b>leftBottom</b><font style="margin-left:15px">左下角显示</font>        
    * <b>leftBottom</b><font style="margin-left:15px">右下角显示</font>        

* <b>typeface</b>

        标签文字字体
        
    * <b>normal</b><font style="margin-left:15px">默认字体类型</font>
    * <b>sans</b><font style="margin-left:15px">默认的sans字体</font>
    * <b>serif</b><font style="margin-left:15px">默认的serifs字体</font>
    * <b>monospace</b><font style="margin-left:15px">默认的monospace字体</font>
    
* <b>textStyle</b>

        字体样式
        
    * <b>normal</b><font style="margin-left:15px">正常字体</font>
    * <b>bold</b><font style="margin-left:15px">粗体</font>
    * <b>italic</b><font style="margin-left:15px">斜体</font>
    
动态设置字体
--

* <b>从assets目录加载字体</b>

        Typeface typeface = Typeface.createFromAsset(getAssets(), "assets目录下的字体文件(注意带文件后缀, *.ttf)");
        roundimageview.setTypeface(typeface);
    
* <b>从文件中加载字体</b>

        Typeface typeface = Typeface.createFromFile(new File("字体文件路径(注意后缀 *.ttf)"));
        或
        Typeface typeface = Typeface.createFromFile("字体文件路径(注意后缀 *.ttf)");
        roundimageview.setTypeface(typeface);

* <b>更多查看Typeface类</b>

项目引用方式：
--

* <b style="font-size: 18px">第一种方式：从jitpack存储库引入</b>    [![](https://jitpack.io/v/xuxuliooo/RoundImageView.svg)](https://jitpack.io/#xuxuliooo/RoundImageView)
   
    <b style="font-size: 16px">1. 在项目的build.gradle中<font style="color: red">(非app/build.gradle)</font>添加"maven { url 'https://jitpack.io' }"</b>

        allprojects {
            repositories {
                ...
                maven { url 'https://jitpack.io' }
            }
        }
   
    <b style="font-size: 16px">2. 在app/build.gradle中添加"implementation 'com.github.xuxuliooo:RoundImageView:1.0.9'"</b>

        dependencies {
            ...
            implementation 'com.github.xuxuliooo:RoundImageView:1.0.9'
        }

* <b style="font-size: 18px">第二种方式：从bintray存储库引入</b>    [![Download](https://api.bintray.com/packages/xuxuliooo/maven/RoundImageView/images/download.svg)](https://bintray.com/xuxuliooo/maven/RoundImageView/_latestVersion)
   
    <b style="font-size: 16px">直接在app/build.gradle中添加"implementation 'com.cbman:roundimageview:1.0.9'"</b>

        dependencies {
            ...
            implementation 'com.cbman:roundimageview:1.0.9'
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
            
* <b>矩形带标签使用方式</b>

        <com.cbman.roundimageview.RoundImageView
            android:layout_width="100dp"
            android:layout_height="100dp"
            android:scaleType="center"
            android:src="@drawable/img"
            app:displayLable="true"
            app:lableGravity="rightTop"
            app:lableWidth="20dp"
            app:textStyle="italic"
            app:typeface="normal"
            app:startMargin="60dp"
            app:text="标签文本"
            app:textColor="@android:color/white"
            app:lableBackground="@color/colorAccent"
            app:textSize="12sp"
            app:borderWidth="1dp"
            app:displayType="normal" />
            


<a href='https://bintray.com/xuxuliooo/maven/RoundImageView?source=watch' alt='Get automatic notifications about new "RoundImageView" versions'><img src='https://www.bintray.com/docs/images/bintray_badge_color.png'></a>
