package com.example.fileSystem.textBlock;

import com.example.fileSystem.error.errIndex;
import com.example.fileSystem.error.noLoad;
import com.example.fileSystem.fileSystem;

import java.io.Serializable;

public class stringBlock implements Serializable{
    private Integer _index;
    private int _status;
    private String _title;
    private StringBuilder _text;

    public stringBlock(Integer index){
        _index = index;
        _status = statusBit.load|statusBit.show|statusBit.change;
        _title = "";
        _text = new StringBuilder("");
    }
    public Integer getIndex(){
        return _index;
    }
    public void setIndex(Integer index){
        if((_status & statusBit.load)==0){
            throw new noLoad("text hasn't been loaded");
        }
        _index = index;
        _status = _status | statusBit.change;
    }
    public int getStatus(){
        return _status;
    }
    public void setStatus(int status){
        if((_status & statusBit.load)==0){
            throw new noLoad("text hasn't been loaded");
        }
        _status = status;
        _status = _status | statusBit.change;

    }
    public String getTitle(){
        return _title;
    }
    public void setTitle(String title){
        if((_status & statusBit.load)!=0){
            throw new noLoad("text hasn't been loaded");
        }
        _status = _status | statusBit.change;
        _title = title;
    }
    public StringBuilder getText(){
        if((_status & statusBit.load)==0){
            throw new noLoad("text hasn't been loaded");
        }
        return _text;
    }
    public void setText(StringBuilder text){
        if((_status & statusBit.load)==0){
            throw new noLoad("text hasn't been loaded");
        }
        _status = _status | statusBit.change;
        _text = text;
    }

    public boolean isLoaded(){
        return (_status & statusBit.load)!=0;
    }
    public void loading(stringBlock block){
        if(block.getIndex()!=_index){
            throw new errIndex("check index err");
        }
        _status = _status | statusBit.load;
        _title = block.getTitle();
        _text = block.getText();

    }
    public stringBlock toActive(){
        _text.ensureCapacity(fileSystem.getPageMax());
        return this;
    }
    public stringBlock toInactive(){
        _text=new StringBuilder(_text);
        return this;
    }
}

