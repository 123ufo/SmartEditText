# SmartEditText

#一个可以限制输入字符的EditText
![](http://i.imgur.com/luZWZ3C.gif)
###xml:

	<com.ufo.smartedittextsimple.view.SmartEditText
        android:id="@+id/smartET"
        android:background="@android:color/white"
        android:layout_margin="10dp"
        smart:smart_maxLength="60"
        android:layout_width="match_parent"
        android:layout_height="200dp"/>



###属性：

	<resources>
	    <declare-styleable name="SmartEditText">
	        <attr name="smart_maxLength" format="integer"/>
	        <attr name="smart_cornerTextColor" format="color"/>
	        <attr name="smart_textColor" format="color"/>
	        <attr name="smart_textSize" format="dimension"/>
	        <attr name="smart_hint" format="reference"/>
	    </declare-styleable>
	</resources>

###代码：


	        SmartEditText smartEditText = (SmartEditText) findViewById(R.id.smartET);
		//        smartEditText.setMaxLength(60);
		//        smartEditText.setTextColor();
		//        smartEditText.setCornerTextColor();
		//        smartEditText.setTextSize();
        smartEditText.setOnInputOutLengthListener(new SmartEditText.OnInputOutLengthListener() {
            @Override
            public void onInputOutLength(int length) {
                Toast.makeText(MainActivity.this, "out Length:"+length, Toast.LENGTH_SHORT).show();
            }
        });