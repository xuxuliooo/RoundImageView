# RoundImageView
自定义ImageView，在原生ImageView上实现圆形显示和加入圆角并且加入边框功能
 

使用方式：

        <com.cb.imageview.RoundImageView
            android:layout_width="100dp"
            android:layout_height="100dp"
            android:scaleType="center"
            android:src="@drawable/img"
            app:displayBorder="true"
            app:borderColor="#5def56"
            app:borderWidth="3dp"
            app:displayType="round_rect"
            app:leftBottomRadius="20dp"
            app:leftTopRadius="30dp"
            app:rightBottomRadius="15dp"
            app:rightTopRadius="5dp" />
            
