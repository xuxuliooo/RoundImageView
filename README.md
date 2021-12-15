[RoundImageView](https://xuxuliooo.github.io/RoundImageView)
==
自定义ImageView，在原生ImageView上实现圆形显示和加入圆角并且加入边框功能
--


### 显示效果

![](https://github.com/xuxuliooo/RoundImageView/raw/master/image/sample.png)

1.1.2 添加渐变色属性，修改颜色使用，可根据View的状态改变使用指定状态的颜色。
--

自定义属性介绍
--
* <b>gradientContent</b>

        渐变色显示内容

    * <b>none</b><font style="margin-left:15px">不使用渐变色</font>
    * <b>border</b><font style="margin-left:15px">边框使用渐变色</font>
    * <b>label</b><font style="margin-left:15px">标签文字使用渐变色</font>
    * <b>labelBackground</b><font style="margin-left:15px">标签背景使用渐变色</font>
    * <b>all</b><font style="margin-left:15px">边框及标签背景使用渐变色</font>

* <b>type</b>

        渐变色类型

    * <b>linear</b><font style="margin-left:15px">线性渐变色</font>
    * <b>radial</b><font style="margin-left:15px">径向渐变色</font>
    * <b>sweep</b><font style="margin-left:15px">扫描渐变</font>
* <b>startColor</b>

        渐变色起始颜色

* <b>centerColor</b>

        渐变色中间颜色

* <b>endColor</b>

        渐变色结束颜色

* <b>orientation</b>

        使用线性渐变时，渐变方向，水平方向(horizontal)或垂直方向(vertical)

* <b>borderWidth</b>

        边框线的宽度，默认对最大宽度做了限制，不超过宽与高的最小尺寸值的四分之一

* <b>borderColor</b>

        边框线的颜色，默认值为"#8A2BE2"

* <b>displayBorder</b>

        显示边框线，默认不显示(false)，显示则为(true)

* <b>radius</b>

        圆角矩形圆弧半径，默认为"0"，如果设置大于"0",
        则设置(topLeftRadius、topRightRadius、bottomLeftRadius、bottomRightRadius)属性会失效

* <b>topLeftRadius(左上角圆弧半径) <br> bottomLeftRadius(左下角圆弧半径) <br> topRightRadius(右上角圆弧半径) <br> bottomRightRadius(右下角圆弧半径)</b>

        矩形四角的圆弧半径，默认为"0"，如果设置"radius"属性时，则此属性值会取"radius"设置的值

* <b>displayType</b>

        显示类型，默认为矩形(normal)。
        
    * <b>normal</b><font style="margin-left:15px">矩形显示</font>
    * <b>circle</b><font style="margin-left:15px">圆形显示</font>
    * <b>round_rect</b><font style="margin-left:15px">圆角矩形显示</font>
    
* <b>displayLabel</b>

        是否显示标签，默认不显示标签(false),显示为(true)

* <b>labelBackground</b>

        标签背景色，默认值为"#9FFF0000"
        
* <b>labelWidth</b>

        标签宽度，单位(dp)
        
* <b>startMargin</b>

        距离开始位置间距，单位(dp)

* <b>text</b>

        标签文本
        
* <b>textColor</b>

        标签文本颜色
        
* <b>textSize</b>

        标签文本文字大小
        
* <b>labelGravity</b>

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
        roundImageView.setTypeface(typeface);
    
* <b>从文件中加载字体</b>

        Typeface typeface = Typeface.createFromFile(new File("字体文件路径(注意后缀 *.ttf)"));
        或
        Typeface typeface = Typeface.createFromFile("字体文件路径(注意后缀 *.ttf)");
        roundImageView.setTypeface(typeface);

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
   
    <b style="font-size: 16px">2. 在app/build.gradle中添加"implementation 'com.github.xuxuliooo:RoundImageView:1.1.2'"</b>

        dependencies {
            ...
            implementation 'com.github.xuxuliooo:RoundImageView:1.1.2'
        }

* <b style="font-size: 18px">第二种方式：从bintray存储库引入</b>    [ ![Download](https://api.bintray.com/packages/xuxuliooo/maven/RoundImageView/images/download.svg?version=1.1.2) ](https://bintray.com/xuxuliooo/maven/RoundImageView/1.1.2/link)
   
    <b style="font-size: 16px">直接在app/build.gradle中添加"implementation 'com.cbman:roundimageview:1.1.2'"</b>

        dependencies {
            ...
            implementation 'com.cbman:roundimageview:1.1.2'
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
                android:bottomLeftRadius="30dp"
                android:topLeftRadius="10dp"
                android:bottomRightRadius="20dp"
                android:topRightRadius="15dp"
                app:borderColor="#ff0000"
                app:borderWidth="3dp"
                app:displayBorder="true"
                app:displayType="round_rect" />
                            
    * 四角圆弧半径相同时直接使用下面方式即可
    
            <com.cbman.roundimageview.RoundImageView
                android:layout_width="100dp"
                android:layout_height="100dp"
                android:scaleType="center"
                android:src="@drawable/img"
                android:radius="20dp"
                app:borderWidth="1dp"
                app:displayType="round_rect" />

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
            android:text="标签文本"
            android:textSize="12sp"
            android:textColor="@android:color/white"
            android:typeface="normal"
            android:textStyle="italic"
            app:displayLabel="true"
            app:labelGravity="rightTop"
            app:labelWidth="20dp"
            app:startMargin="60dp"
            app:labelBackground="@color/colorAccent"
            app:borderWidth="1dp"
            app:displayType="normal" />
            
* <b>边框渐变色使用方式</b>

        <com.cbman.roundimageview.RoundImageView
            android:id="@+id/r6"
            android:layout_width="100dp"
            android:layout_height="100dp"
            android:layout_below="@id/r4"
            android:layout_alignStart="@id/r4"
            android:layout_alignLeft="@id/r4"
            android:layout_marginTop="15dp"
            android:endColor="@color/colorPrimaryDark"
            android:orientation="vertical"
            android:scaleType="center"
            android:src="@drawable/img"
            android:startColor="@color/colorAccent"
            android:type="linear"
            app:borderWidth="5dp"
            app:displayBorder="true"
            app:displayType="circle"
            app:gradientContent="border" />

<a href='https://bintray.com/xuxuliooo/maven/RoundImageView?source=watch' alt='Get automatic notifications about new "RoundImageView" versions'><img src='https://www.bintray.com/docs/images/bintray_badge_color.png'></a>

License
-------

    Copyright 2020 xuxuliooo

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
