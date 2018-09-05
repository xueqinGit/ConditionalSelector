package com.selector.my.conditionalselector;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.selector.my.mylibrary.view.OptionsPickerView;
import com.selector.my.mylibrary.view.lib.WheelView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {
    OptionsPickerView pickerView;
    List<String> dataList;
    TextView selectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initOptionPicker();
        initData();

    }

    private void initData() {
        dataList = new ArrayList<>();
        selectText = findViewById(R.id.select_text);

        for (int i = 0; i < 5; i++) {
            dataList.add("数据" + i);
        }
        pickerView.setPicker(dataList);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickerView.show();
            }
        });
    }

    /**
     * 初始化选择器
     */
    public void initOptionPicker() {
        pickerView = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                selectText.setText(dataList.get(options1));
            }
        }).setTitleText("请选择家庭成员")
                .setContentTextSize(20)//设置滚轮文字大小
                .setSelectTextSize(22)//设置选中文字大小
                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                .setDividerType(WheelView.DividerType.WRAP)
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.WHITE)
                .setTitleBgColor(Color.WHITE)
                .setTitleColor(Color.GRAY)
                .setCancelColor(Color.parseColor("#1474A9"))
                .setSubmitColor(Color.parseColor("#1474A9"))
                .setSubmitText("确定")
                .setCancelText("取消")
                .setTextColorCenter(Color.BLACK)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
//                .setCyclic(true,true,true)//是否循环
                .setAddClickListener(this)
                .build();
    }

    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        if (tag != null && tag.equals(OptionsPickerView.TAG_ADD)) {
            Toast.makeText(this, "左键点击", Toast.LENGTH_SHORT).show();
        }
    }
}
