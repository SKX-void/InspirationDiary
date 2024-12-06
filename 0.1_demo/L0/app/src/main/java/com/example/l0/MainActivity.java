package com.example.l0;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fileSystem.*;
import com.example.fileSystem.textBlock.stringBlock;

import java.util.TreeMap;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //将xml加载到引擎
        setContentView(R.layout.activity_main);

        fileSystemObject obj=new fileSystemObject("/storage/emulated/0/Download/");


        RelativeLayout relativeLayout_index = findViewById(R.id.index);
        ScrollView ScrollView_index=findViewById(R.id.rollIndex);
        Button button_head_index =findViewById(R.id.indexButton);
        button_head_index.setOnClickListener(
                v -> {
                    if(ScrollView_index.getVisibility()==View.VISIBLE){
                        ScrollView_index.setVisibility(View.INVISIBLE);
                }else {
                        ScrollView_index.setVisibility(View.VISIBLE);
                    }
        }
        );



        indexLoader(relativeLayout_index, obj);
        EditText pageEdit = findViewById(R.id.pageEdit);
        pageEdit.setText(obj.activeStringBlock().getText());
/*
        Button clean = findViewById(R.id.cleantest);
        clean.setOnClickListener(v -> {
                    pageEdit.setText("");
                }
        );*/



        Button button_newPage=findViewById(R.id.newPage);
        button_newPage.setOnClickListener(
                v->{
                    obj.writePage(new StringBuilder(pageEdit.getText().toString()));
                    obj.appendPage();
                    pageEdit.setText("");
                    addPage(obj, relativeLayout_index);
                }
        );


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    TreeMap<Integer,Button> buttonList=new TreeMap<>();

    void indexLoader(RelativeLayout relativeLayout_index,fileSystemObject file){
        Button indexB=findViewById(R.id.index_template);
        buttonList.put(-1,indexB);
        for(Integer iter=0;iter<=file.fileSystem().list().lastKey();iter++)
        {
            addPage(file, relativeLayout_index);
        }
    }

    void addPage(fileSystemObject file, RelativeLayout relativeLayout_index) {
        Integer iter = file.fileSystem().list().lastKey();
        stringBlock page = file.fileSystem().list().get(iter);
        Button button = new Button(this);
        button.setId(View.generateViewId());
        button.setText(page.getIndex().toString() + "." + page.getText().toString());
        button.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        button.setTextColor(Color.parseColor("#FFFFFF"));
        button.setBackgroundColor(Color.parseColor("#CCCCCC"));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        button.setPadding(0, 0, 0, 0);
        layoutParams.setMargins(0, 0, 0, 0);
        layoutParams.addRule(RelativeLayout.BELOW, buttonList.get(iter - 1).getId());
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        button.setLayoutParams(layoutParams);
        button.setOnClickListener(v->{
            EditText pageEdit = findViewById(R.id.pageEdit);
            file.writePage(new StringBuilder(pageEdit.getText().toString()));
            file.set_activeStringBlock(iter);
            pageEdit.setText(file.activeStringBlock().getText());
        });
        relativeLayout_index.addView(button);

        buttonList.put(iter, button);
    }
    void openPage(Integer index){

    }
}
